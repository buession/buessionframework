/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core.providers;

import com.buession.redis.exception.RedisException;
import io.lettuce.core.ConnectionPool;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisConnectionException;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.resource.Delay;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lettuce Redis 哨兵连接池连接提供者
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class SentinelConnectionProvider<K, V> implements ConnectionProvider<K, V> {

	public final static long DEFAULT_SUBSCRIBE_RETRY_WAIT_TIME_MILLIS = 5000;

	public final static Delay DEFAULT_RESUBSCRIBE_DELAY = Delay.constant(
			Duration.ofMillis(DEFAULT_SUBSCRIBE_RETRY_WAIT_TIME_MILLIS));

	private final static Sleeper DEFAULT_SLEEPER = Thread::sleep;

	private volatile HostAndPort currentMaster;

	private volatile ConnectionPool<K, V> pool;

	private final String masterName;

	private final LettuceClientConfig masterClientConfig;

	private final GenericObjectPoolConfig<StatefulConnection<K, V>> masterPoolConfig;

	private final Collection<SentinelListener> sentinelListeners = new ArrayList<>();

	private final LettuceClientConfig sentinelClientConfig;

	private final RedisCodec<K, V> redisCodec;

	private final Delay resubscribeDelay;

	private final Lock initPoolLock = new ReentrantLock(true);

	private final SentinelConnectionFactory<String, String> sentinelConnectionFactory;

	private final Sleeper sleeper;

	private final static Logger logger = LoggerFactory.getLogger(SentinelConnectionProvider.class);

	public SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                  final LettuceClientConfig masterClientConfig,
	                                  final LettuceClientConfig sentinelClientConfig,
	                                  final RedisCodec<K, V> redisCodec) {
		this(sentinels, masterName, masterClientConfig, sentinelClientConfig, null, redisCodec);
	}

	public SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                  final LettuceClientConfig masterClientConfig,
	                                  final LettuceClientConfig sentinelClientConfig,
	                                  final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                  final RedisCodec<K, V> redisCodec) {
		this(sentinels, masterName, masterClientConfig, sentinelClientConfig, poolConfig, redisCodec,
				DEFAULT_RESUBSCRIBE_DELAY);
	}

	public SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                  final LettuceClientConfig masterClientConfig,
	                                  final LettuceClientConfig sentinelClientConfig,
	                                  final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                  final RedisCodec<K, V> redisCodec, final long subscribeRetryWaitTimeMillis) {
		this(sentinels, masterName, masterClientConfig, sentinelClientConfig, poolConfig, redisCodec,
				Delay.constant(Duration.ofMillis(subscribeRetryWaitTimeMillis)));
	}

	public SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                  final LettuceClientConfig masterClientConfig,
	                                  final LettuceClientConfig sentinelClientConfig,
	                                  final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                  final RedisCodec<K, V> redisCodec, final Delay resubscribeDelay) {
		this(sentinels, masterName, masterClientConfig, sentinelClientConfig, poolConfig, redisCodec, resubscribeDelay,
				null, null);
	}

	private SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                   final LettuceClientConfig masterClientConfig,
	                                   final LettuceClientConfig sentinelClientConfig,
	                                   final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                   final RedisCodec<K, V> redisCodec, final Delay resubscribeDelay,
	                                   final SentinelConnectionFactory<String, String> sentinelConnectionFactory,
	                                   final Sleeper sleeper) {
		this.masterName = masterName;

		this.masterClientConfig = masterClientConfig;
		this.sentinelClientConfig = sentinelClientConfig;

		this.masterPoolConfig = poolConfig;

		this.redisCodec = redisCodec;

		this.resubscribeDelay = resubscribeDelay;

		this.sentinelConnectionFactory =
				sentinelConnectionFactory != null ? sentinelConnectionFactory : defaultSentinelConnectionFactory();

		this.sleeper = sleeper != null ? sleeper : DEFAULT_SLEEPER;

		HostAndPort master = initSentinels(sentinels);
		initMaster(master);
	}

	@Override
	public StatefulConnection<K, V> getConnection() {
		return pool.getResource();
	}

	@Override
	public StatefulConnection<K, V> getConnection(CommandArgs<K, V> commandArgs) {
		return pool.getResource();
	}

	@Override
	public Map<?, ConnectionPool<K, V>> getConnectionMap() {
		return Collections.singletonMap(currentMaster, pool);
	}

	@Override
	public Map<?, ConnectionPool<K, V>> getPrimaryNodesConnectionMap() {
		return Collections.singletonMap(currentMaster, pool);
	}

	@Override
	public void close() {
		sentinelListeners.forEach(SentinelListener::shutdown);

		pool.close();
	}

	public HostAndPort getCurrentMaster() {
		return currentMaster;
	}

	private ConnectionPool<K, V> createNodePool(HostAndPort master) {
		if(masterPoolConfig == null){
			return new ConnectionPool<>(master, masterClientConfig, redisCodec);
		}else{
			return new ConnectionPool<>(master, masterClientConfig, masterPoolConfig, redisCodec);
		}
	}

	private void initMaster(HostAndPort master) {
		initPoolLock.lock();

		try{
			if(master.equals(currentMaster) == false){
				currentMaster = master;

				ConnectionPool<K, V> newPool = createNodePool(currentMaster);
				ConnectionPool<K, V> existingPool = pool;

				pool = newPool;
				logger.info("Created connection pool to master at {}.", master);

				if(existingPool != null){
					existingPool.close();
				}
			}
		}finally{
			initPoolLock.unlock();
		}
	}

	private HostAndPort initSentinels(Set<HostAndPort> sentinels) {
		HostAndPort master = null;
		boolean sentinelAvailable = false;

		logger.debug("Trying to find master from available sentinels...");

		for(HostAndPort sentinel : sentinels){
			logger.debug("Connecting to Sentinel {}...", sentinel);

			try(StatefulRedisSentinelConnection<String, String> sentinelConnection = sentinelConnectionFactory.createConnection(
					sentinel, sentinelClientConfig)){
				SocketAddress masterAddr = sentinelConnection.sync().getMasterAddrByName(masterName);

				if(masterAddr instanceof InetSocketAddress inetSocketAddress){
					master = toHostAndPort(inetSocketAddress);
					sentinelAvailable = true;
				}

				logger.debug("Redis master reported at {}.", master);
				break;
			}catch(Exception e){
				logger.warn("Could not get master address from {}.", sentinel, e);
			}
		}

		if(master == null){
			if(sentinelAvailable){
				// can connect to sentinel, but master name seems to not monitored
				throw new RedisException("Can connect to sentinel, but " + masterName + " seems to be not monitored.");
			}else{
				throw new RedisConnectionException(
						"All sentinels down, cannot determine where " + masterName + " is running.");
			}
		}

		logger.info("Redis master running at {}. Starting sentinel listeners...", master);

		for(HostAndPort sentinel : sentinels){
			SentinelListener listener = new SentinelListener(sentinel);
			sentinelListeners.add(listener);
			listener.start();
		}

		return master;
	}

	private static HostAndPort toHostAndPort(InetSocketAddress inetSocketAddress) {
		return HostAndPort.of(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
	}

	protected class SentinelListener extends Thread {

		protected final HostAndPort node;

		protected volatile StatefulRedisSentinelConnection<String, String> sentinelConnection;

		protected AtomicBoolean running = new AtomicBoolean(false);

		protected long subscribeAttempt = 0;

		public SentinelListener(HostAndPort node) {
			super(String.format("%s-LettuceRedisSentinelListener-[%s]", masterName, node.toString()));
			this.node = node;
			setDaemon(true);
		}

		@Override
		public void run() {
			running.set(true);

			while(running.get()){
				try{
					// double check that it is not being shutdown
					if(running.get() == false){
						break;
					}

					sentinelConnection = sentinelConnectionFactory.createConnection(node, sentinelClientConfig);
					RedisFuture<SocketAddress> future = sentinelConnection.async().getMasterAddrByName(masterName);

					future.thenAccept(masterAddr->{
						if(masterAddr == null){
							logger.warn("Can not get master {} address. Sentinel: {}.", masterName, node);
						}else{
							if(masterAddr instanceof InetSocketAddress inetSocketAddress){
								initMaster(toHostAndPort(inetSocketAddress));
							}
						}
					});


					/*
					sentinelJedis.subscribe(new JedisPubSub() {

						@Override
						public void onSubscribe(String channel, int subscribedChannels) {
							subscribeAttempt = 0;
							logger.debug("Successfully subscribed to {} on Sentinel {}. Reset attempt counter.",
									channel, node);
						}

						@Override
						public void onMessage(String channel, String message) {
							logger.debug("Sentinel {} published: {}.", node, message);

							String[] switchMasterMsg = message.split(" ");

							if(switchMasterMsg.length > 3){

								if(masterName.equals(switchMasterMsg[0])){
									initMaster(toHostAndPort(switchMasterMsg[3], switchMasterMsg[4]));
								}else{
									logger.debug("Ignoring message on +switch-master for master {}. Our master is {}.",
											switchMasterMsg[0], masterName);
								}

							}else{
								logger.error("Invalid message received on sentinel {} on channel +switch-master: {}.",
										node, message);
							}
						}
					}, "+switch-master");

					 */
				}catch(Exception e){
					if(running.get()){
						long subscribeRetryWaitTimeMillis = resubscribeDelay.createDelay(subscribeAttempt).toMillis();
						subscribeAttempt++;
						logger.warn("Lost connection to Sentinel {}. Sleeping {}ms and retrying.", node,
								subscribeRetryWaitTimeMillis, e);
						try{
							sleeper.sleep(subscribeRetryWaitTimeMillis);
						}catch(InterruptedException se){
							logger.error("Sleep interrupted.", se);
						}
					}else{
						logger.debug("Unsubscribing from sentinel {}.", node);
					}
				}finally{
					closeQuietly(sentinelConnection);
				}
			}
		}

		// must not throw exception
		public void shutdown() {
			try{
				logger.debug("Shutting down listener on {}.", node);
				running.set(false);
				// This isn't good, the Jedis object is not thread safe
				closeQuietly(sentinelConnection);
			}catch(RuntimeException e){
				logger.error("Error while shutting down.", e);
			}
		}

	}

	protected SentinelConnectionFactory<String, String> defaultSentinelConnectionFactory() {

		return (node, config)->{
			RedisURI redisURI = RedisURI.create(node.getHostText(), node.getPort());
			RedisClient redisClient = RedisClient.create(redisURI);
			return redisClient.connectSentinel(StringCodec.UTF8, redisURI);
		};

	}

	private void closeQuietly(AutoCloseable closeable) {
		if(closeable != null){
			try{
				closeable.close();
			}catch(Exception e){
				//
			}
		}
	}

	@FunctionalInterface
	interface Sleeper {

		void sleep(long millis) throws InterruptedException;

	}

	@FunctionalInterface
	protected interface SentinelConnectionFactory<K, V> {

		StatefulRedisSentinelConnection<K, V> createConnection(HostAndPort node, LettuceClientConfig config);

	}

}

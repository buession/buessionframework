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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core;

import com.buession.net.HostAndPort;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Lettuce 哨兵连接池
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceSentinelPool extends Pool<StatefulRedisSentinelConnection<byte[], byte[]>> {

	private LettuceSentinelFactory lettuceFactory;

	private LettuceClientConfig sentinelClientConfig;

	//protected final Collection<MasterListener> masterListeners = new ArrayList<>();

	private volatile HostAndPort currentMaster;

	private final Object initPoolLock = new Object();

	private final static Logger logger = LoggerFactory.getLogger(LettuceSentinelPool.class);

	/**
	 * 构造函数
	 *
	 * @param masterName
	 * 		Master 节点名称
	 * @param sentinels
	 * 		哨兵节点
	 */
	public LettuceSentinelPool(final String masterName, final Set<HostAndPort> sentinels) {
		this(masterName, sentinels, DefaultLettuceClientConfig.builder().build(),
				DefaultLettuceClientConfig.builder().build());
	}

	/**
	 * 构造函数
	 *
	 * @param masterName
	 * 		Master 节点名称
	 * @param sentinels
	 * 		哨兵节点
	 * @param lettuceClientConfig
	 * 		客户端配置
	 * @param sentinelClientConfig
	 * 		哨兵节点客户端配置
	 */
	public LettuceSentinelPool(final String masterName, final Set<HostAndPort> sentinels,
							   final LettuceClientConfig lettuceClientConfig,
							   final LettuceClientConfig sentinelClientConfig) {
		super(new LettuceSentinelFactory(masterName, sentinels, sentinelClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param masterName
	 * 		Master 节点名称
	 * @param poolConfig
	 * 		线程池配置
	 * @param sentinels
	 * 		哨兵节点
	 */
	public LettuceSentinelPool(final String masterName,
							   final GenericObjectPoolConfig<StatefulRedisSentinelConnection<byte[], byte[]>> poolConfig,
							   final Set<HostAndPort> sentinels) {
		this(masterName, sentinels, poolConfig, DefaultLettuceClientConfig.builder().build(),
				DefaultLettuceClientConfig.builder().build());
	}

	/**
	 * 构造函数
	 *
	 * @param masterName
	 * 		Master 节点名称
	 * @param sentinels
	 * 		哨兵节点
	 * @param poolConfig
	 * 		线程池配置
	 * @param lettuceClientConfig
	 * 		客户端配置
	 * @param sentinelClientConfig
	 * 		哨兵节点客户端配置
	 */
	public LettuceSentinelPool(final String masterName, final Set<HostAndPort> sentinels,
							   final GenericObjectPoolConfig<StatefulRedisSentinelConnection<byte[], byte[]>> poolConfig,
							   final LettuceClientConfig lettuceClientConfig,
							   final LettuceClientConfig sentinelClientConfig) {
		this(masterName, sentinels, poolConfig, new LettuceSentinelFactory(masterName, sentinels, lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param masterName
	 * 		Master 节点名称
	 * @param sentinels
	 * 		哨兵节点
	 * @param poolConfig
	 * 		线程池配置
	 * @param factory
	 * 		连接线对象工厂
	 */
	protected LettuceSentinelPool(final String masterName, final Set<HostAndPort> sentinels,
								  final GenericObjectPoolConfig<StatefulRedisSentinelConnection<byte[], byte[]>> poolConfig,
								  final PooledObjectFactory<StatefulRedisSentinelConnection<byte[], byte[]>> factory) {
		super(factory, poolConfig);

		HostAndPort master = initSentinels(masterName, sentinels);
		initMaster(master);
	}

	@Override
	public StatefulRedisSentinelConnection<byte[], byte[]> getResource() {
		StatefulRedisSentinelConnection<byte[], byte[]> connection = super.getResource();
		//jedis.setDataSource(this);
		return connection;
	}

	@Override
	public void returnResource(final StatefulRedisSentinelConnection<byte[], byte[]> resource) {
		if(resource != null){
			try{
				super.returnResource(resource);
			}catch(RuntimeException e){
				super.returnBrokenResource(resource);
				logger.warn("Resource is returned to the pool as broken", e);
			}
		}
	}

	@Override
	public void destroy() {
		//for(MasterListener m : masterListeners){
		//m.shutdown();
		//}

		super.destroy();
	}

	private void initMaster(final HostAndPort master) {
		synchronized(initPoolLock){
			if(master.equals(currentMaster) == false){
				currentMaster = master;
				//factory.setHostAndPort(currentHostMaster); although we clear the pool, we still have to check the
				// returned object in getResource, this call only clears idle instances, not borrowed instances
				super.clear();

				logger.info("Created LettuceSentinelPool to master at {}", master);
			}
		}
	}

	private HostAndPort initSentinels(final String masterName, final Set<HostAndPort> sentinels) {
		return null;
	}

	private static HostAndPort toHostAndPort(final List<String> getMasterAddrByNameResult) {
		String host = getMasterAddrByNameResult.get(0);
		int port = Integer.parseInt(getMasterAddrByNameResult.get(1));

		return new HostAndPort(host, port);
	}

	/*
	protected final class MasterListener extends Thread {

		private String masterName;

		private String host;

		private int port;

		private long subscribeRetryWaitTimeMillis = 5000;

		private volatile Jedis j;

		private final AtomicBoolean running = new AtomicBoolean(false);

		private final Logger logger = LoggerFactory.getLogger(MasterListener.class);

		private MasterListener() {
			super();
		}

		public MasterListener(String masterName, String host, int port) {
			super(String.format("MasterListener-%s-[%s:%d]", masterName, host, port));
			this.masterName = masterName;
			this.host = host;
			this.port = port;
		}

		public MasterListener(String masterName, String host, int port, long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
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

					final HostAndPort hostPort = new HostAndPort(host, port);
					StatefulRedisSentinelConnection<byte[], byte[]> connection =
							RedisClient.create(RedisURI.create(host, port)).connect(new ByteArrayCodec());
					RedisCommands<byte[], byte[]> commands = connection.sync();
					j = new Jedis(hostPort, sentinelClientConfig);

					// code for active refresh
					List<String> masterAddr = j.sentinelGetMasterAddrByName(masterName);
					if(masterAddr == null || masterAddr.size() != 2){
						logger.warn("Can not get master addr, master name: {}. Sentinel: {}.", masterName,
								hostPort);
					}else{
						initMaster(toHostAndPort(masterAddr));
					}

					j.subscribe(new JedisPubSub() {

						@Override
						public void onMessage(String channel, String message) {
							logger.debug("Sentinel {} published: {}.", hostPort, message);

							String[] switchMasterMsg = message.split(" ");

							if(switchMasterMsg.length > 3){

								if(masterName.equals(switchMasterMsg[0])){
									initMaster(toHostAndPort(Arrays.asList(switchMasterMsg[3], switchMasterMsg[4])));
								}else{
									if(logger.isDebugEnabled()){
										logger.debug(
												"Ignoring message on +switch-master for master name {}, our master name is {}",
												switchMasterMsg[0], masterName);
									}
								}

							}else{
								logger.error("Invalid message received on Sentinel {} on channel +switch-master: {}",
										hostPort, message);
							}
						}
					}, "+switch-master");

				}catch(JedisException e){
					if(running.get()){
						logger.error("Lost connection to Sentinel at {}:{}. Sleeping 5000ms and retrying.", host,
								port, e);
						try{
							Thread.sleep(subscribeRetryWaitTimeMillis);
						}catch(InterruptedException e1){
							logger.error("Sleep interrupted: ", e1);
						}
					}else{
						if(logger.isDebugEnabled()){
							logger.debug("Unsubscribing from Sentinel at {}:{}", host, port);
						}
					}
				}finally{
					if(j != null){
						j.close();
					}
				}
			}
		}

		public void shutdown() {
			try{
				if(logger.isDebugEnabled()){
					logger.debug("Shutting down listener on {}:{}", host, port);
				}
				running.set(false);
				// This isn't good, the Jedis object is not thread safe
				if(j != null){
					j.close();
				}
			}catch(RuntimeException e){
				logger.error("Caught exception while shutting down: ", e);
			}
		}

	}

	 */

}

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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis;

import com.buession.core.utils.ReflectUtils;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.utils.RedisClientUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.BinaryRedisPipeline;
import redis.clients.jedis.commands.RedisPipeline;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisClientUtils extends RedisClientUtils {

	private JedisClientUtils(){
	}

	public final static List<JedisShardInfo> createJedisShardInfo(final Set<ShardedRedisNode> redisNodes,
			final int database, final int connectionTimeout, final int soTimeout, final boolean useSsl,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		List<JedisShardInfo> shardInfos = new ArrayList<>();
		JedisShardInfo shardInfo;

		for(ShardedRedisNode node : redisNodes){
			shardInfo = new JedisShardInfo(node.getHost(), node.getName(), node.getPort(), 0, node.getWeight(), useSsl
					, sslSocketFactory, sslParameters, hostnameVerifier);

			shardInfo.setConnectionTimeout(connectionTimeout);
			shardInfo.setSoTimeout(soTimeout);

			ReflectUtils.setField(shardInfo, "db", database);

			shardInfos.add(shardInfo);
		}

		return shardInfos;
	}

	public final static Jedis getShard(final ShardedJedis shardedJedis, final String key){
		return shardedJedis.getShard(key);
	}

	public final static Jedis getShard(final ShardedJedis shardedJedis, final byte[] key){
		return shardedJedis.getShard(key);
	}

	public static boolean isInMulti(final Jedis jedis){
		return jedis.getClient().isInMulti();
	}

	public static boolean isInMulti(final ShardedJedis shardedJedis){
		return false;
	}

	public final static Object objectDebug(final ObjectCommand command, final Jedis jedis, final String key){
		switch(command){
			case ENCODING:
				return jedis.objectEncoding(key);
			case IDLETIME:
				return jedis.objectIdletime(key);
			case REFCOUNT:
				return jedis.objectRefcount(key);
			default:
				return null;
		}
	}

	public final static Object objectDebug(final ObjectCommand command, final Jedis jedis, final byte[] key){
		switch(command){
			case ENCODING:
				return jedis.objectEncoding(key);
			case IDLETIME:
				return jedis.objectIdletime(key);
			case REFCOUNT:
				return jedis.objectRefcount(key);
			default:
				return null;
		}
	}

	public final static Object objectDebug(final ObjectCommand command, final ShardedJedis shardedJedis,
			final String key){
		switch(command){
			case ENCODING:
				return SafeEncoder.encode(shardedJedis.objectEncoding(SafeEncoder.encode(key)));
			case IDLETIME:
				return shardedJedis.objectIdletime(SafeEncoder.encode(key));
			case REFCOUNT:
				return shardedJedis.objectRefcount(SafeEncoder.encode(key));
			default:
				return null;
		}
	}

	public final static Object objectDebug(final ObjectCommand command, final ShardedJedis shardedJedis,
			final byte[] key){
		switch(command){
			case ENCODING:
				return shardedJedis.objectEncoding(key);
			case IDLETIME:
				return shardedJedis.objectIdletime(key);
			case REFCOUNT:
				return shardedJedis.objectRefcount(key);
			default:
				return null;
		}
	}

	public final static Response<?> objectDebug(final ObjectCommand command, final RedisPipeline pipeline,
			final String key){
		switch(command){
			case ENCODING:
				return pipeline.objectEncoding(key);
			case IDLETIME:
				return pipeline.objectIdletime(key);
			case REFCOUNT:
				return pipeline.objectRefcount(key);
			default:
				return null;
		}
	}

	public final static Response<?> objectDebug(final ObjectCommand command, final BinaryRedisPipeline binaryPipeline,
			final byte[] key){
		switch(command){
			case ENCODING:
				return binaryPipeline.objectEncoding(key);
			case IDLETIME:
				return binaryPipeline.objectIdletime(key);
			case REFCOUNT:
				return binaryPipeline.objectRefcount(key);
			default:
				return null;
		}
	}

	public final static Object slowLog(final SlowLogCommand command, final Jedis jedis, final String... arguments){
		switch(command){
			case GET:
				return jedis.slowlogGet();
			case LEN:
				return jedis.slowlogLen();
			case RESET:
				return jedis.slowlogReset();
			default:
				return null;
		}
	}

	public final static Object slowLog(final SlowLogCommand command, final Jedis jedis, final byte[]... arguments){
		switch(command){
			case GET:
				return jedis.slowlogGet();
			case LEN:
				return jedis.slowlogLen();
			case RESET:
				return jedis.slowlogReset();
			default:
				return null;
		}
	}

}

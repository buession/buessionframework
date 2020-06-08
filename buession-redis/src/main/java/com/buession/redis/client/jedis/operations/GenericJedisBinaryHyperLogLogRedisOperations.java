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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import redis.clients.jedis.Jedis;

/**
 * @author Yong.Teng
 */
public class GenericJedisBinaryHyperLogLogRedisOperations extends AbstractJedisBinaryHyperLogLogRedisOperations<Jedis> {

	public GenericJedisBinaryHyperLogLogRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"keys", keys);

		if(isTransaction()){
			return execute((Jedis cmd)->statusForOK(getTransaction().pfmerge(destKey, keys).get()),
					ProtocolCommand.PFMERGE, args);
		}else{
			return execute((Jedis cmd)->statusForOK(cmd.pfmerge(destKey, keys)), ProtocolCommand.PFMERGE, args);
		}
	}

	@Override
	public Long pfCount(final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((Jedis cmd)->getTransaction().pfcount(keys).get(), ProtocolCommand.PFCOUNT, args);
		}else{
			return execute((Jedis cmd)->cmd.pfcount(keys), ProtocolCommand.PFCOUNT, args);
		}
	}

}

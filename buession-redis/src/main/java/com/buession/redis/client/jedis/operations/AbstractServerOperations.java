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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.Module;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.internal.convert.RedisServerTimeConverter;
import com.buession.redis.core.internal.convert.jedis.AclLogConverter;
import com.buession.redis.core.internal.convert.jedis.AclUserConverter;
import com.buession.redis.core.internal.convert.jedis.FlushModeConverter;
import com.buession.redis.core.internal.convert.jedis.ModuleConverter;
import com.buession.redis.core.internal.convert.jedis.SlowLogConverter;
import redis.clients.jedis.AccessControlLogEntry;
import redis.clients.jedis.util.Slowlog;

/**
 * Jedis 服务端命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractServerOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements ServerOperations<CMD> {

	protected final static AclUserConverter.AclUserExposeConverter USER_EXPOSE_CONVERTER = new AclUserConverter.AclUserExposeConverter();

	protected final static ListConverter<AccessControlLogEntry, AclLog> LIST_ACL_LOG_EXPOSE_CONVERTER = new ListConverter<>(
			new AclLogConverter.AclLogExposeConverter());

	protected final static FlushModeConverter.FlushModeJedisConverter FLUSH_MODE_JEDIS_CONVERTER = new FlushModeConverter.FlushModeJedisConverter();

	protected final static ListConverter<redis.clients.jedis.Module, Module> LIST_MODULE_EXPOSE_CONVERTER = new ListConverter<>(
			new ModuleConverter.ModuleExposeConverter());

	protected final static ListConverter<Slowlog, SlowLog> LIST_SLOW_LOG_EXPOSE_CONVERTER = new ListConverter<>(
			new SlowLogConverter.SlowLogExposeConverter());

	protected final static RedisServerTimeConverter REDIS_SERVER_TIME_CONVERTER = new RedisServerTimeConverter();

	public AbstractServerOperations(final JedisRedisClient client){
		super(client);
	}


}

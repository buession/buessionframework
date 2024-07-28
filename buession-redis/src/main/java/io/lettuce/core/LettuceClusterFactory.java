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

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.net.HostAndPort;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.codec.ByteArrayCodec;

import java.util.Set;

/**
 * Lettuce 集群连接对象工厂
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterFactory
		extends AbstractLettuceFactory<StatefulRedisClusterConnection<byte[], byte[]>, RedisClusterClient> {

	public LettuceClusterFactory(final Set<HostAndPort> nodes) {
		this(buildRedisURI(nodes, DefaultLettuceClientConfig.builder().build()));
	}

	public LettuceClusterFactory(final Set<HostAndPort> nodes, final LettuceClientConfig lettuceClientConfig) {
		this(buildRedisURI(nodes, lettuceClientConfig));
	}

	protected LettuceClusterFactory(final RedisURI uri) {
		super(RedisClusterClient.create(uri));
	}

	@Override
	public StatefulRedisClusterConnection<byte[], byte[]> create() throws Exception {
		return getClient().connect(new ByteArrayCodec());
	}

	private static RedisURI buildRedisURI(final Set<HostAndPort> nodes, final LettuceClientConfig lettuceClientConfig) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final RedisURI.Builder builder = RedisURI.builder().withDatabase(lettuceClientConfig.getDatabase())
				.withSsl(lettuceClientConfig.isSsl());

		propertyMapper.alwaysApplyingWhenNonNull().from(lettuceClientConfig.getConnectionTimeout())
				.to(builder::withTimeout);
		propertyMapper.from(lettuceClientConfig.getClientName()).to(builder::withClientName);
		nodes.forEach((node)->{
			builder.withHost(node.getHost()).withPort(node.getPort());
			if(Validate.hasText(lettuceClientConfig.getPassword())){
				if(Validate.hasText(lettuceClientConfig.getUser())){
					builder.withAuthentication(lettuceClientConfig.getUser(), lettuceClientConfig.getPassword());
				}else{
					builder.withPassword(lettuceClientConfig.getPassword());
				}
			}
		});

		return builder.build();
	}

}

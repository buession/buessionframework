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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.AclOperations;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.AclSetUserArgument;
import com.buession.redis.utils.SafeEncoder;

/**
 * Lettuce 权限命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractAclOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements AclOperations {

	public AbstractAclOperations(final C client) {
		super(client);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		return aclDelUser(SafeEncoder.encode(usernames));
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command) {
		return aclDryRun(SafeEncoder.encode(username), command);
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command, final byte[]... arguments) {
		return aclDryRun(SafeEncoder.encode(username), command, SafeEncoder.encode(arguments));
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		return aclGetUser(SafeEncoder.encode(username));
	}

	@Override
	public Status aclSetUser(final byte[] username, final AclSetUserArgument rules) {
		return aclSetUser(SafeEncoder.encode(username), rules);
	}

}
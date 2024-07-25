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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;

import java.util.List;

/**
 * Jedis 集群模式权限命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisClusterAclOperations extends AbstractAclOperations<JedisClusterClient> {

	public JedisClusterAclOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public List<AclCategory> aclCat() {
		return notCommand(client, Command.ACL_CAT);
	}

	@Override
	public List<Command> aclCat(final AclCategory aclCategory) {
		final CommandArguments args = CommandArguments.create("aclCategory", aclCategory);
		return notCommand(client, Command.ACL_CAT, args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return notCommand(client, Command.ACL_SETUSER, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return notCommand(client, Command.ACL_SETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return notCommand(client, Command.ACL_GETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return notCommand(client, Command.ACL_GETUSER, args);
	}

	@Override
	public List<String> aclUsers() {
		return notCommand(client, Command.ACL_USERS);
	}

	@Override
	public String aclWhoAmI() {
		return notCommand(client, Command.ACL_WHOAMI);
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return notCommand(client, Command.ACL_DELUSER, args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return notCommand(client, Command.ACL_DELUSER, args);
	}

	@Override
	public String aclGenPass() {
		return notCommand(client, Command.ACL_GENPASS);
	}

	@Override
	public List<String> aclList() {
		return notCommand(client, Command.ACL_LIST);
	}

	@Override
	public Status aclLoad() {
		return notCommand(client, Command.ACL_LOAD);
	}

	@Override
	public List<AclLog> aclLog() {
		return notCommand(client, Command.ACL_LOG);
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);
		return notCommand(client, Command.ACL_LOG, args);
	}

	@Override
	public Status aclLogReset() {
		return notCommand(client, Command.ACL_LOGREST);
	}

	@Override
	public Status aclLogSave() {
		return notCommand(client, Command.ACL_LOGSAVE);
	}

}

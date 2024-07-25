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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.args.AclSetUserArgument;

import java.util.List;

/**
 * 权限命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/acl/" target="_blank">https://redis.io/docs/latest/commands/acl/</a></p>
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface AclCommands extends RedisCommands {

	/**
	 * The command shows the available ACL categories
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 */
	List<AclCategory> aclCat();

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param aclCategory
	 * 		Acl Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 */
	List<Command> aclCat(final AclCategory aclCategory);

	/**
	 * Delete all the specified ACL users and terminate all the connections that are authenticated with such users
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-deluser/" target="_blank">https://redis.io/commands/acl-deluser/</a></p>
	 *
	 * @param usernames
	 * 		用户名
	 *
	 * @return 删除用户数量
	 */
	Long aclDelUser(final String... usernames);

	/**
	 * Delete all the specified ACL users and terminate all the connections that are authenticated with such users
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-deluser/" target="_blank">https://redis.io/commands/acl-deluser/</a></p>
	 *
	 * @param usernames
	 * 		用户名
	 *
	 * @return 删除用户数量
	 */
	Long aclDelUser(final byte[]... usernames);

	/**
	 * Simulate the execution of a given command by a given user.
	 * This command can be used to test the permissions of a given user without having to enable the user or cause the side effects of running the command.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/acl-dryrun/" target="_blank">https://redis.io/docs/latest/commands/acl-dryrun/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param command
	 * 		命令
	 *
	 * @return 操作结果
	 */
	Status aclDryRun(final String username, final Command command);

	/**
	 * Simulate the execution of a given command by a given user.
	 * This command can be used to test the permissions of a given user without having to enable the user or cause the side effects of running the command.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/acl-dryrun/" target="_blank">https://redis.io/docs/latest/commands/acl-dryrun/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param command
	 * 		命令
	 *
	 * @return 操作结果
	 */
	Status aclDryRun(final byte[] username, final Command command);

	/**
	 * Simulate the execution of a given command by a given user.
	 * This command can be used to test the permissions of a given user without having to enable the user or cause the side effects of running the command.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/acl-dryrun/" target="_blank">https://redis.io/docs/latest/commands/acl-dryrun/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param command
	 * 		命令
	 * @param arguments
	 * 		命令参数
	 *
	 * @return 操作结果
	 */
	Status aclDryRun(final String username, final Command command, final String... arguments);

	/**
	 * Simulate the execution of a given command by a given user.
	 * This command can be used to test the permissions of a given user without having to enable the user or cause the side effects of running the command.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/acl-dryrun/" target="_blank">https://redis.io/docs/latest/commands/acl-dryrun/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param command
	 * 		命令
	 * @param arguments
	 * 		命令参数
	 *
	 * @return 操作结果
	 */
	Status aclDryRun(final byte[] username, final Command command, final byte[]... arguments);

	/**
	 * ACL users need a solid password in order to authenticate to the server without security risks
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-genpass/" target="_blank">https://redis.io/commands/acl-genpass/</a></p>
	 *
	 * @return By default 64 bytes string representing 256 bits of pseudorandom data
	 */
	String aclGenPass();

	/**
	 * ACL users need a solid password in order to authenticate to the server without security risks
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-genpass/" target="_blank">https://redis.io/commands/acl-genpass/</a></p>
	 *
	 * @param bits
	 * 		位
	 *
	 * @return By default 64 bytes string representing 256 bits of pseudorandom data
	 */
	String aclGenPass(final int bits);

	/**
	 * The command returns all the rules defined for an existing ACL user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-getuser/" target="_blank">https://redis.io/commands/acl-getuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 *
	 * @return A list of ACL rule definitions for the user
	 */
	AclUser aclGetUser(final String username);

	/**
	 * The command returns all the rules defined for an existing ACL user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-getuser/" target="_blank">https://redis.io/commands/acl-getuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 *
	 * @return A list of ACL rule definitions for the user
	 */
	AclUser aclGetUser(final byte[] username);

	/**
	 * The command shows the currently active ACL rules in the Redis server.
	 * Each line in the returned array defines a different user, and the format is the same used in the redis.conf
	 * file or the external ACL file, so you can cut and paste what is returned by the ACL LIST command directly
	 * inside a configuration file if you wish (but make sure to check ACL SAVE).
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-list/" target="_blank">https://redis.io/commands/acl-list/</a></p>
	 *
	 * @return Acl List
	 */
	List<String> aclList();

	/**
	 * When Redis is configured to use an ACL file (with the aclfile configuration option),
	 * this command will reload the ACLs from the file, replacing all the current ACL rules with the ones defined in the file
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-load/" target="_blank">https://redis.io/commands/acl-load/</a></p>
	 *
	 * @return Status.SUCCESS
	 */
	Status aclLoad();

	/**
	 * The optional argument specifies how many entries to show. By default up to ten failures are returned
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-log/" target="_blank">https://redis.io/commands/acl-log/</a></p>
	 *
	 * @return A list of ACL security events
	 */
	List<AclLog> aclLog();

	/**
	 * The optional argument specifies how many entries to show. By default up to ten failures are returned
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-log/" target="_blank">https://redis.io/commands/acl-log/</a></p>
	 *
	 * @param count
	 * 		返回数量
	 *
	 * @return A list of ACL security events
	 */
	List<AclLog> aclLog(final int count);

	/**
	 * The optional argument specifies how many entries to show. By default up to ten failures are returned
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-log/" target="_blank">https://redis.io/commands/acl-log/</a></p>
	 *
	 * @return 日志重置成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status aclLogReset();

	/**
	 * When Redis is configured to use an ACL file (with the aclfile configuration option),
	 * this command will save the currently defined ACLs from the server memory to the ACL file
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-save/" target="_blank">https://redis.io/commands/acl-save/</a></p>
	 *
	 * @return 保存成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status aclSave();

	/**
	 * Create an ACL user with the specified rules or modify the rules of an existing user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-setuser/" target="_blank">https://redis.io/commands/acl-setuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param rules
	 * 		the specified rules
	 *
	 * @return 操作成功，返回 Status.Success；否则，返回 Status.Failure
	 */
	Status aclSetUser(final String username, final AclSetUserArgument rules);

	/**
	 * Create an ACL user with the specified rules or modify the rules of an existing user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-setuser/" target="_blank">https://redis.io/commands/acl-setuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param rules
	 * 		the specified rules
	 *
	 * @return 操作成功，返回 Status.Success；否则，返回 Status.Failure
	 */
	Status aclSetUser(final byte[] username, final AclSetUserArgument rules);

	/**
	 * The command shows a list of all the usernames of the currently configured users in the Redis ACL system
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-users/" target="_blank">https://redis.io/commands/acl-users/</a></p>
	 *
	 * @return A list of all the usernames of the currently configured users in the Redis ACL system
	 */
	List<String> aclUsers();

	/**
	 * Return the username the current connection is authenticated with
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-whoami/" target="_blank">https://redis.io/commands/acl-whoami/</a></p>
	 *
	 * @return The username of the current connection
	 */
	String aclWhoAmI();

}

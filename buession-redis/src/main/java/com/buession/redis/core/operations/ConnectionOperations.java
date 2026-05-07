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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.Client;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.connection.ClientInfoOption;
import com.buession.redis.core.command.args.connection.ClientPauseMode;
import com.buession.redis.core.command.args.connection.ClientReply;
import com.buession.redis.core.command.args.connection.ClientType;
import com.buession.redis.core.command.args.connection.ClientUnblockType;
import com.buession.redis.core.Hello;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.TrackingInfo;
import com.buession.redis.core.command.ConnectionCommands;
import com.buession.redis.core.command.args.connection.TrackingArgument;

import java.util.List;

/**
 * 连接相关命令
 *
 * <p>详情说明 <a href="http://www.redis.cn/commands.html#connection" target="_blank">http://www.redis.cn/commands.html#connection</a></p>
 *
 * @author Yong.Teng
 */
public interface ConnectionOperations extends ConnectionCommands, RedisOperations {

	@Override
	default Status auth(final String user, final String password) {
		return doExecute((cmd)->cmd.auth(user, password));
	}

	@Override
	default Status auth(final byte[] user, final byte[] password) {
		return doExecute((cmd)->cmd.auth(user, password));
	}

	@Override
	default Status auth(final String password) {
		return doExecute((cmd)->cmd.auth(password));
	}

	@Override
	default Status auth(final byte[] password) {
		return doExecute((cmd)->cmd.auth(password));
	}

	@Override
	default Status clientCaching(final boolean isYes) {
		return doExecute((cmd)->cmd.clientCaching(isYes));
	}

	@Override
	default String clientGetName() {
		return doExecute((cmd)->cmd.clientGetName());
	}

	@Override
	default Integer clientGetRedir() {
		return doExecute((cmd)->cmd.clientGetRedir());
	}

	@Override
	default Long clientId() {
		return doExecute((cmd)->cmd.clientId());
	}

	@Override
	default Client clientInfo() {
		return doExecute((cmd)->cmd.clientInfo());
	}

	@Override
	default Status clientKill(final String host, final int port) {
		return doExecute((cmd)->cmd.clientKill(host, port));
	}

	@Override
	default Status clientKill(final byte[] host, final int port) {
		return doExecute((cmd)->cmd.clientKill(host, port));
	}

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc.com/client_and_server/client_kill.html</a></p>
	 *
	 * @param server
	 * 		客户端地址
	 *
	 * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status clientKill(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null.");
		return clientKill(server.getHost(), server.getPort());
	}

	@Override
	default List<Client> clientList() {
		return doExecute((cmd)->cmd.clientList());
	}

	@Override
	default List<Client> clientList(final ClientType clientType) {
		return doExecute((cmd)->cmd.clientList(clientType));
	}

	@Override
	default List<Client> clientList(final long... clientIds) {
		return doExecute((cmd)->cmd.clientList(clientIds));
	}

	@Override
	default Status clientNoEvict(final boolean on) {
		return doExecute((cmd)->cmd.clientNoEvict(on));
	}

	@Override
	default Status clientNoTouch(final boolean on) {
		return doExecute((cmd)->cmd.clientNoTouch(on));
	}

	@Override
	default Status clientPause(final int timeout) {
		return doExecute((cmd)->cmd.clientPause(timeout));
	}

	@Override
	default Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		return doExecute((cmd)->cmd.clientPause(timeout, pauseMode));
	}

	@Override
	default Status clientReply(final ClientReply option) {
		return doExecute((cmd)->cmd.clientReply(option));
	}

	@Override
	default Status clientSetInfo(final ClientInfoOption option, final String value) {
		return doExecute((cmd)->cmd.clientSetInfo(option, value));
	}

	@Override
	default Status clientSetName(final String name) {
		return doExecute((cmd)->cmd.clientSetName(name));
	}

	@Override
	default Status clientSetName(final byte[] name) {
		return doExecute((cmd)->cmd.clientSetName(name));
	}

	@Override
	default Status clientTracking(final boolean on, final TrackingArgument argument) {
		return doExecute((cmd)->cmd.clientTracking(on, argument));
	}

	@Override
	default TrackingInfo clientTrackingInfo() {
		return doExecute((cmd)->cmd.clientTrackingInfo());
	}

	@Override
	default Status clientUnblock(final int clientId) {
		return doExecute((cmd)->cmd.clientUnblock(clientId));
	}

	@Override
	default Status clientUnblock(final int clientId, final ClientUnblockType type) {
		return doExecute((cmd)->cmd.clientUnblock(clientId, type));
	}

	@Override
	default Status clientUnpause() {
		return doExecute((cmd)->cmd.clientUnpause());
	}

	@Override
	default String echo(final String str) {
		return doExecute((cmd)->cmd.echo(str));
	}

	@Override
	default byte[] echo(final byte[] str) {
		return doExecute((cmd)->cmd.echo(str));
	}

	@Override
	default Hello hello() {
		return doExecute((cmd)->cmd.hello());
	}

	@Override
	default Hello hello(final int protover) {
		return doExecute((cmd)->cmd.hello(protover));
	}

	@Override
	default Hello hello(final int protover, final String password) {
		return doExecute((cmd)->cmd.hello(protover, password));
	}

	@Override
	default Hello hello(final int protover, final byte[] password) {
		return doExecute((cmd)->cmd.hello(protover, password));
	}

	@Override
	default Hello hello(final int protover, final String username, final String password) {
		return doExecute((cmd)->cmd.hello(protover, username, password));
	}

	@Override
	default Hello hello(final int protover, final byte[] username, final byte[] password) {
		return doExecute((cmd)->cmd.hello(protover, username, password));
	}

	@Override
	default Hello hello(final int protover, final String username, final String password, final String clientName) {
		return doExecute((cmd)->cmd.hello(protover, username, password, clientName));
	}

	@Override
	default Hello hello(final int protover, final byte[] username, final byte[] password, final byte[] clientName) {
		return doExecute((cmd)->cmd.hello(protover, username, password, clientName));
	}

	@Override
	default Status ping() {
		return doExecute((cmd)->cmd.ping());
	}

	@Override
	default Status quit() {
		return doExecute((cmd)->cmd.quit());
	}

	@Override
	default Status reset() {
		return doExecute((cmd)->cmd.reset());
	}

	@Override
	default Status select(final int db) {
		return doExecute((cmd)->cmd.select(db));
	}

	/**
	 * 切换到数据库 db 0
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/select.html" target="_blank">http://redisdoc.com/database/select.html</a></p>
	 *
	 * @return 切换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status select() {
		return select(0);
	}

	private <R> R doExecute(final Command.Executor<ConnectionCommands, R> executor) {
		return execute((client)->executor.execute(client.connectionCommands()));
	}

}

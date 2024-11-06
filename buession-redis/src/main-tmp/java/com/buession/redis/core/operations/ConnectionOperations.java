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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientAttributeOption;
import com.buession.redis.core.ClientPauseMode;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientTrackingInfo;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.command.ConnectionCommands;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.command.args.ClientTracking;
import com.buession.redis.core.command.args.HelloArgument;

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
		return execute((client)->client.connectionOperations().auth(user, password));
	}

	@Override
	default Status auth(final byte[] user, final byte[] password) {
		return execute((client)->client.connectionOperations().auth(user, password));
	}

	@Override
	default Status auth(final String password) {
		return execute((client)->client.connectionOperations().auth(password));
	}

	@Override
	default Status auth(final byte[] password) {
		return execute((client)->client.connectionOperations().auth(password));
	}

	@Override
	default Status clientCaching() {
		return execute((client)->client.connectionOperations().clientCaching());
	}

	@Override
	default Status clientCaching(final boolean isYes) {
		return execute((client)->client.connectionOperations().clientCaching(isYes));
	}

	@Override
	default String clientGetName() {
		return execute((client)->client.connectionOperations().clientGetName());
	}

	@Override
	default Integer clientGetRedir() {
		return execute((client)->client.connectionOperations().clientGetRedir());
	}

	@Override
	default Long clientId() {
		return execute((client)->client.connectionOperations().clientId());
	}

	@Override
	default Client clientInfo() {
		return execute((client)->client.connectionOperations().clientInfo());
	}

	@Override
	default Status clientKill(final String host, final int port) {
		return execute((client)->client.connectionOperations().clientKill(host, port));
	}

	@Override
	default Long clientKill(final ClientKillArgument... clientKillArguments) {
		return execute((client)->client.connectionOperations().clientKill(clientKillArguments));
	}

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc.com/client_and_server/client_kill.html</a></p>
	 *
	 * @param client
	 * 		客户端地址
	 *
	 * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status clientKill(final RedisNode client) {
		Assert.isNull(client, "Redis client cloud not be null.");
		return clientKill(client.getHost(), client.getPort());
	}

	@Override
	default List<Client> clientList() {
		return execute((client)->client.connectionOperations().clientList());
	}

	@Override
	default List<Client> clientList(final ClientType clientType) {
		return execute((client)->client.connectionOperations().clientList(clientType));
	}

	@Override
	default List<Client> clientList(final long... clientIds) {
		return execute((client)->client.connectionOperations().clientList(clientIds));
	}

	@Override
	default Status clientNoEvict(final boolean on) {
		return execute((client)->client.connectionOperations().clientNoEvict(on));
	}

	@Override
	default Status clientNoTouch(final boolean on) {
		return execute((client)->client.connectionOperations().clientNoTouch(on));
	}

	@Override
	default Status clientPause(final int timeout) {
		return execute((client)->client.connectionOperations().clientPause(timeout));
	}

	@Override
	default Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		return execute((client)->client.connectionOperations().clientPause(timeout, pauseMode));
	}

	@Override
	default Status clientReply(final ClientReply option) {
		return execute((client)->client.connectionOperations().clientReply(option));
	}

	@Override
	default Status clientSetInfo(final ClientAttributeOption clientAttributeOption, String value) {
		return execute((client)->client.connectionOperations().clientSetInfo(clientAttributeOption, value));
	}

	@Override
	default Status clientSetInfo(final ClientAttributeOption clientAttributeOption, byte[] value) {
		return execute((client)->client.connectionOperations().clientSetInfo(clientAttributeOption, value));
	}

	@Override
	default Status clientSetName(final String name) {
		return execute((client)->client.connectionOperations().clientSetName(name));
	}

	@Override
	default Status clientSetName(final byte[] name) {
		return execute((client)->client.connectionOperations().clientSetName(name));
	}

	@Override
	default Status clientTracking(final ClientTracking clientTracking) {
		return execute((client)->client.connectionOperations().clientTracking(clientTracking));
	}

	@Override
	default ClientTrackingInfo clientTrackingInfo() {
		return execute((client)->client.connectionOperations().clientTrackingInfo());
	}

	@Override
	default Status clientUnblock(final int clientId) {
		return execute((client)->client.connectionOperations().clientUnblock(clientId));
	}

	@Override
	default Status clientUnblock(final int clientId, final ClientUnblockType type) {
		return execute((client)->client.connectionOperations().clientUnblock(clientId, type));
	}

	@Override
	default Status clientUnPause() {
		return execute((client)->client.connectionOperations().clientUnPause());
	}

	@Override
	default String echo(final String str) {
		return execute((client)->client.connectionOperations().echo(str));
	}

	@Override
	default byte[] echo(final byte[] str) {
		return execute((client)->client.connectionOperations().echo(str));
	}

	@Override
	default RedisServer hello(final HelloArgument helloArgument) {
		return execute((client)->client.connectionOperations().hello(helloArgument));
	}

	@Override
	default Status ping() {
		return execute((client)->client.connectionOperations().ping());
	}

	@Override
	default Status quit() {
		return execute((client)->client.connectionOperations().quit());
	}

	@Override
	default Status reset() {
		return execute((client)->client.connectionOperations().reset());
	}

	@Override
	default Status select(final int db) {
		return execute((client)->client.connectionOperations().select(db));
	}

}

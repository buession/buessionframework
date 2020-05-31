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
package com.buession.redis.client;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.command.BinaryBitMapCommands;
import com.buession.redis.core.command.BinaryClientAndServerCommands;
import com.buession.redis.core.command.BinaryConfigureCommands;
import com.buession.redis.core.command.BinaryDatabaseCommand;
import com.buession.redis.core.command.BinaryDebugCommands;
import com.buession.redis.core.command.BinaryGeoCommands;
import com.buession.redis.core.command.BinaryHashCommands;
import com.buession.redis.core.command.BinaryHyperLogLogCommands;
import com.buession.redis.core.command.BinaryInternalCommands;
import com.buession.redis.core.command.BinaryKeyCommands;
import com.buession.redis.core.command.BinaryListCommands;
import com.buession.redis.core.command.BinaryLuaCommands;
import com.buession.redis.core.command.BinaryPersistenceCommand;
import com.buession.redis.core.command.BinaryPubSubCommands;
import com.buession.redis.core.command.BinaryReplicationCommands;
import com.buession.redis.core.command.BinarySetCommands;
import com.buession.redis.core.command.BinarySortedSetCommands;
import com.buession.redis.core.command.BinaryStringCommands;
import com.buession.redis.core.command.BinaryTransactionCommands;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.ClientAndServerCommands;
import com.buession.redis.core.command.ConfigureCommands;
import com.buession.redis.core.command.DatabaseCommand;
import com.buession.redis.core.command.DebugCommands;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.HashCommands;
import com.buession.redis.core.command.HyperLogLogCommands;
import com.buession.redis.core.command.InternalCommands;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.LuaCommands;
import com.buession.redis.core.command.PersistenceCommand;
import com.buession.redis.core.command.PubSubCommands;
import com.buession.redis.core.command.ReplicationCommands;
import com.buession.redis.core.command.SetCommands;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.TransactionCommands;

/**
 * @author Yong.Teng
 */
public interface RedisClient extends KeyCommands, BinaryKeyCommands, StringCommands, BinaryStringCommands,
		HashCommands, BinaryHashCommands, ListCommands, BinaryListCommands, SetCommands, BinarySetCommands/*,
		SortedSetCommands, BinarySortedSetCommands, HyperLogLogCommands, BinaryHyperLogLogCommands, GeoCommands,
		BinaryGeoCommands, BitMapCommands, BinaryBitMapCommands, TransactionCommands, BinaryTransactionCommands,
		PubSubCommands, BinaryPubSubCommands, DatabaseCommand, BinaryDatabaseCommand, LuaCommands, BinaryLuaCommands,
		PersistenceCommand, BinaryPersistenceCommand, ReplicationCommands, BinaryReplicationCommands,
		ClientAndServerCommands, BinaryClientAndServerCommands, ConfigureCommands, BinaryConfigureCommands,
		InternalCommands, BinaryInternalCommands, DebugCommands, BinaryDebugCommands*/ {

	RedisConnection getConnection();

	void setConnection(RedisConnection connection);

}

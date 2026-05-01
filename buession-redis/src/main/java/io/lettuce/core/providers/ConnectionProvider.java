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
package io.lettuce.core.providers;

import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.protocol.CommandArgs;

import java.util.Collections;
import java.util.Map;

/**
 * Lettuce Redis 连接提供者
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface ConnectionProvider<K, V> extends AutoCloseable {

	StatefulConnection<K, V> getConnection();

	StatefulConnection<K, V> getConnection(CommandArgs<K, V> commandArgs);

	default Map<?, ?> getConnectionMap() {
		final StatefulConnection<K, V> connection = getConnection();
		return Collections.singletonMap(connection.toString(), connection);
	}

	default Map<?, ?> getPrimaryNodesConnectionMap() {
		final StatefulConnection<K, V> connection = getConnection();
		return Collections.singletonMap(connection.toString(), connection);
	}


}

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
package io.lettuce.core;

import io.lettuce.core.api.sync.BaseRedisCommands;
import io.lettuce.core.api.sync.RediSearchCommands;
import io.lettuce.core.api.sync.RedisAclCommands;
import io.lettuce.core.api.sync.RedisFunctionCommands;
import io.lettuce.core.api.sync.RedisGeoCommands;
import io.lettuce.core.api.sync.RedisHLLCommands;
import io.lettuce.core.api.sync.RedisHashCommands;
import io.lettuce.core.api.sync.RedisJsonCommands;
import io.lettuce.core.api.sync.RedisKeyCommands;
import io.lettuce.core.api.sync.RedisListCommands;
import io.lettuce.core.api.sync.RedisScriptingCommands;
import io.lettuce.core.api.sync.RedisServerCommands;
import io.lettuce.core.api.sync.RedisSetCommands;
import io.lettuce.core.api.sync.RedisSortedSetCommands;
import io.lettuce.core.api.sync.RedisStreamCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.api.sync.RedisTransactionalCommands;
import io.lettuce.core.api.sync.RedisVectorSetCommands;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface RedisCommands<K, V> extends BaseRedisCommands<K, V>, RedisAclCommands<K, V>,
		RedisClusterCommands<K, V>, RedisFunctionCommands<K, V>, RedisGeoCommands<K, V>, RedisHashCommands<K, V>,
		RedisHLLCommands<K, V>, RedisKeyCommands<K, V>, RedisListCommands<K, V>, RedisScriptingCommands<K, V>,
		RedisServerCommands<K, V>, RedisSetCommands<K, V>, RedisSortedSetCommands<K, V>, RedisStreamCommands<K, V>,
		RedisStringCommands<K, V>, RedisTransactionalCommands<K, V>, RedisJsonCommands<K, V>,
		RedisVectorSetCommands<K, V>, RediSearchCommands<K, V> {

}

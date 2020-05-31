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

import com.buession.lang.Status;
import com.buession.redis.core.command.ListCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public interface ListRedisOperations extends RedisOperations, ListCommands {

	@Override
	default Status lSet(final String key, final int index, final String value){
		return lSet(key, (long) index, value);
	}

	@Override
	default String lIndex(final String key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	default Status lTrim(final String key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	@Override
	default Long lRem(final String key, final String value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	default List<String> lRange(final String key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

}

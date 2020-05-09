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
package com.buession.redis.core;

/**
 * @author Yong.Teng
 */
public enum MaxMemoryPolicy {

	/**
	 * 最近最少使用算法，从设置了过期时间的键中选择空转时间最长的键值对清除掉
	 */
	VOLATILE_LRU("volatile-lru"),

	/**
	 * 最近最不经常使用算法，从设置了过期时间的键中选择某段时间之内使用频次最小的键值对清除掉
	 */
	VOLATILE_LFU("volatile-lfu"),

	/**
	 * 从设置了过期时间的键中选择过期时间最早的键值对清除
	 */
	VOLATILE_TTL("volatile-ttl"),

	/**
	 * 从设置了过期时间的键中，随机选择键进行清除
	 */
	VOLATILE_RANDOM("volatile-random"),

	/**
	 * 最近最少使用算法，从所有的键中选择空转时间最长的键值对清除
	 */
	ALLKEYS_LRU("allkeys-lru"),

	/**
	 * 最近最不经常使用算法，从所有的键中选择某段时间之内使用频次最少的键值对清除
	 */
	ALLKEYS_LFU("allkeys-lfu"),

	/**
	 * 所有的键中，随机选择键进行删除
	 */
	ALLKEYS_RANDOM("allkeys-random"),

	/**
	 * 不做任何的清理工作，在redis的内存超过限制之后，所有的写入操作都会返回错误；但是读操作都能正常的进行
	 */
	NOEVICTION("noeviction");

	private String value;

	MaxMemoryPolicy(final String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

}

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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.dao.mongodb.core;

/**
 * 决定使用哪一个节点来满足正在发起的读请求
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public enum ReadPreference {

	/**
	 * 只选择主节点
	 */
	PRIMARY(com.mongodb.ReadPreference.primary()),

	/**
	 * 优先选择主节点，如果不可用则选择从节点
	 */
	PRIMARY_PREFERRED(com.mongodb.ReadPreference.primaryPreferred()),

	/**
	 * 只选择从节点
	 */
	SECONDARY(com.mongodb.ReadPreference.secondary()),

	/**
	 * 优先选择从节点，如果从节点不可用则选择主节点
	 */
	SECONDARY_PREFERRED(com.mongodb.ReadPreference.secondaryPreferred()),

	/**
	 * 选择最近的节点
	 */
	NEAREST(com.mongodb.ReadPreference.nearest());

	private final com.mongodb.ReadPreference value;

	ReadPreference(final com.mongodb.ReadPreference value) {
		this.value = value;
	}

	public com.mongodb.ReadPreference getValue() {
		return value;
	}

}

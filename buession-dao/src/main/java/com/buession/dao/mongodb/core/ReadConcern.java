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
 * 在 readPreference 选择了指定的节点后，readConcern 决定这个节点上的数据哪些是可读的
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public enum ReadConcern {

	DEFAULT(com.mongodb.ReadConcern.DEFAULT),

	/**
	 * 读取所有可用且属于当前分片的数据
	 */
	LOCAL(com.mongodb.ReadConcern.LOCAL),

	/**
	 * 读取在大多数节点上提交完成的数据
	 */
	MAJORITY(com.mongodb.ReadConcern.MAJORITY),

	/**
	 * 可线性化读取文档
	 */
	LINEARIZABLE(com.mongodb.ReadConcern.LINEARIZABLE),

	/**
	 * 读取最近快照中的数据
	 */
	SNAPSHOT(com.mongodb.ReadConcern.SNAPSHOT),

	/**
	 * 读取所有可用的数据
	 */
	AVAILABLE(com.mongodb.ReadConcern.AVAILABLE);

	private final com.mongodb.ReadConcern value;

	ReadConcern(final com.mongodb.ReadConcern value) {
		this.value = value;
	}

	public com.mongodb.ReadConcern getValue() {
		return value;
	}

}

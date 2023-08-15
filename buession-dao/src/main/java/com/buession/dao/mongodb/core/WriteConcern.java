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
 * 决定一个写操作落到多少个节点上才算成功
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public enum WriteConcern {

	ACKNOWLEDGED(com.mongodb.WriteConcern.ACKNOWLEDGED),

	W1(com.mongodb.WriteConcern.W1),

	W2(com.mongodb.WriteConcern.W2),

	W3(com.mongodb.WriteConcern.W3),

	UNACKNOWLEDGED(com.mongodb.WriteConcern.UNACKNOWLEDGED),

	JOURNALED(com.mongodb.WriteConcern.JOURNALED),

	MAJORITY(com.mongodb.WriteConcern.MAJORITY);

	private final com.mongodb.WriteConcern value;

	WriteConcern(final com.mongodb.WriteConcern value) {
		this.value = value;
	}

	public com.mongodb.WriteConcern getValue() {
		return value;
	}

}

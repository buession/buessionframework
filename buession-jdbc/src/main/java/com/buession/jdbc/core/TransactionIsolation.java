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
package com.buession.jdbc.core;

/**
 * 事务隔离级别
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public enum TransactionIsolation {

	/**
	 * 默认隔离级别
	 */
	DEFAULT(-1),

	/**
	 * 读未提交，即能够读取到没有被提交的数据
	 */
	READ_UNCOMMITTED(1),

	/**
	 * 读已提交，即能够读到那些已经提交的数据
	 */
	READ_COMMITTED(2),

	/**
	 * 重复读取，即在数据读出来之后加锁
	 */
	REPEATABLE_READ(4),

	/**
	 * 串行化，最高的事务隔离级别
	 */
	SERIALIZABLE(8);

	private final int value;

	TransactionIsolation(final int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

}

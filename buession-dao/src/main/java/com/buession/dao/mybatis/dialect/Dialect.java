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
package com.buession.dao.mybatis.dialect;

/**
 * 数据库方言
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public interface Dialect {

	/**
	 * 返回数据库类型
	 *
	 * @return 数据库类型
	 */
	DbType dbType();

	/**
	 * 返回数据库本身是否支持分页查询方式
	 *
	 * @return true / false
	 */
	default boolean supportsLimit() {
		return true;
	}

	/**
	 * 组装分页 SQL
	 *
	 * @param sql
	 * 		SQL 语句
	 * @param offset
	 * 		偏移量
	 * @param limit
	 * 		每页条数
	 *
	 * @return 分页 SQL
	 */
	default String buildPaginationSql(final String sql, final long offset, final long limit) {
		return null;
	}

}
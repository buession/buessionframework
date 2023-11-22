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

import com.buession.core.validator.Validate;
import com.buession.lang.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQLServer 2005 方言
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public final class SQLServer2005Dialect implements Dialect {

	private final static Pattern PATTERN = Pattern.compile("\\((.)*order by(.)*\\)");

	@Override
	public DbType dbType() {
		return DbType.SQLSERVER_2005;
	}

	@Override
	public String buildPaginationSql(final String sql, final int offset, final int limit) {
		StringBuilder pagingBuilder = new StringBuilder();
		String orderBy = getOrderByPart(sql);
		String distinctStr = Constants.EMPTY_STRING;

		String loweredString = sql.toLowerCase();
		String sqlPartString = sql;

		if(loweredString.trim().startsWith("select")){
			int index = 6;
			if(loweredString.startsWith("select distinct")){
				distinctStr = "DISTINCT ";
				index = 15;
			}
			sqlPartString = sqlPartString.substring(index);
		}
		pagingBuilder.append(sqlPartString);

		if(Validate.isBlank(orderBy)){
			orderBy = "ORDER BY CURRENT_TIMESTAMP";
		}

		final StringBuilder sb = new StringBuilder("WITH __TEMP__ AS (");

		sb.append("SELECT ").append(distinctStr).append(" TOP 100 PERCENT ");
		sb.append(" ROW_NUMBER() OVER (").append(orderBy).append(") as __row_number__, ").append(pagingBuilder)
				.append(") ");
		sb.append("SELECT * FROM __TEMP__ WHERE __row_number__ BETWEEN ").append(offset + 1).append(" AND ")
				.append(offset + limit).append(" ORDER BY __row_number__");

		return sb.toString();
	}

	private static String getOrderByPart(final String sql) {
		int lastIndex = sql.toLowerCase().lastIndexOf("order by");
		if(lastIndex == -1){
			return Constants.EMPTY_STRING;
		}

		Matcher matcher = PATTERN.matcher(sql);
		if(matcher.find() == false){
			return sql.substring(lastIndex);
		}

		int end = matcher.end();
		return lastIndex < end ? Constants.EMPTY_STRING : sql.substring(lastIndex);
	}

}

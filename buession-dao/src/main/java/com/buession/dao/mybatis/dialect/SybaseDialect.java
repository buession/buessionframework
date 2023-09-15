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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sybase 方言
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public final class SybaseDialect implements Dialect {

	private final static Pattern SELECT_PATTERN = Pattern.compile("SELECT ");

	private final static Pattern FROM_PATTERN = Pattern.compile(" FROM ");

	@Override
	public DbType dbType() {
		return DbType.SYBASE;
	}

	@Override
	public String buildPaginationSql(final String sql, final long offset, final long limit) {
		int index = findFrom(sql);

		if(index == -1){
			index = sql.toUpperCase().indexOf(" FROM ");
		}

		final StringBuilder sb = new StringBuilder("select top ");

		sb.append(offset + limit);
		sb.append(" row_num=identity(12),").append(sql, 6, index).append(" into #t ")
				.append(sql.substring(index));
		sb.append(" select * from #t where row_num > ").append(offset).append(" and row_num <= ").append(offset + limit)
				.append("drop table #t");

		return sb.toString();
	}

	private static int findFrom(final String sql) {
		String tempSql = sql.toUpperCase().replace("[\r\n\t]", " ");

		Matcher select = SELECT_PATTERN.matcher(tempSql);
		Matcher from = FROM_PATTERN.matcher(tempSql);

		List<Integer> selectIndex = new ArrayList<>(10);
		List<Integer> fromIndex = new ArrayList<>(10);

		while(select.find()){
			int start = select.start();
			if(start == 0 || tempSql.charAt(start - 1) == ' ' || tempSql.charAt(start - 1) == '('){
				selectIndex.add(start);
			}
		}

		while(from.find()){
			fromIndex.add(from.start());
		}

		List<Integer> indexList = new ArrayList<>(20);

		indexList.addAll(selectIndex);
		indexList.addAll(fromIndex);
		indexList.sort(Comparator.naturalOrder());

		if(indexList.size() < 2){
			return -1;
		}

		int selectCount = 1;
		for(int each : indexList){
			if(fromIndex.contains(each)){
				selectCount--;
			}else{
				selectCount++;
			}

			if(selectCount == 0){
				return each;
			}
		}

		return -1;
	}

}

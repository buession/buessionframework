/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package org.apache.ibatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public abstract class AbstractEnumSetTypeHandler<E extends Enum<E>> extends AbstractSetTypeHandler<E> {

	public AbstractEnumSetTypeHandler(Class<E> type){
		super(type);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Set<E> parameter, JdbcType jdbcType) throws
			SQLException{
		String result = parameter.stream().map(v->v.name()).collect(Collectors.joining(";"));
		ps.setString(i, result);
	}

	protected abstract E getValue(String str);

	@Override
	protected HashSet<E> parseResult(final String str){
		if(str == null){
			return null;
		}

		String[] temp = str.split(",");
		HashSet<E> data = new HashSet<>(temp.length);

		for(String s : temp){
			data.add(getValue(s));
		}

		return data;
	}

}

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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2018 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package org.apache.ibatis.type;

import com.buession.core.utils.Assert;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractSetTypeHandler<E> extends BaseTypeHandler<Set<E>> {

	protected Class<E> type;

	public AbstractSetTypeHandler(Class<E> type){
		Assert.isNull(type, "Type argument cannot be null.");
		this.type = type;
	}

	@Override
	public Set<E> getNullableResult(ResultSet rs, String columnName) throws SQLException{
		return parseResult(rs.getString(columnName));
	}

	@Override
	public Set<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
		return parseResult(rs.getString(columnIndex));
	}

	@Override
	public Set<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
		return parseResult(cs.getString(columnIndex));
	}

	protected abstract Set<E> parseResult(final String str);

}

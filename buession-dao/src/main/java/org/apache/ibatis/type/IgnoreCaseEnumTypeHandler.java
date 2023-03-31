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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package org.apache.ibatis.type;

import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 忽略大小写 Enum {@link TypeHandler}，将值忽略大小写转换为枚举字段
 *
 * @param <E>
 * 		枚举类型
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class IgnoreCaseEnumTypeHandler<E extends Enum<E>> extends AbstractEnumTypeHandler<E> {

	private final static Logger logger = LoggerFactory.getLogger(IgnoreCaseEnumTypeHandler.class);

	public IgnoreCaseEnumTypeHandler(Class<E> type){
		super(type);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException{
		if(jdbcType == null){
			ps.setString(i, parameter.name());
		}else{
			ps.setObject(i, parameter.name(), jdbcType.TYPE_CODE);
		}
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException{
		return parseResult(rs.getString(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
		return parseResult(rs.getString(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
		return parseResult(cs.getString(columnIndex));
	}

	@Override
	protected E parseResult(final String str) throws SQLException{
		if(Validate.hasText(str)){
			E result = EnumUtils.getEnumIgnoreCase(type, str);
			if(result == null && logger.isErrorEnabled()){
				logger.error("Database value '{}' convert to '{}' failure: No enum constant.", str, type.getName());
			}

			return result;
		}

		return null;
	}

}

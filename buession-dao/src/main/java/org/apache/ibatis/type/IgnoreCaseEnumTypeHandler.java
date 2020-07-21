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
 * | Copyright @ 2013-2017 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package org.apache.ibatis.type;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yong.Teng
 */
public class IgnoreCaseEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

	private final Class<E> type;

	private final static Logger logger = LoggerFactory.getLogger(IgnoreCaseEnumTypeHandler.class);

	public IgnoreCaseEnumTypeHandler(Class<E> type){
		Assert.isNull(type, "Type argument cannot be null.");
		this.type = type;
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
		return convert(rs.getString(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
		return convert(rs.getString(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
		return convert(cs.getString(columnIndex));
	}

	private final E convert(final String str){
		if(Validate.hasText(str) == false){
			return null;
		}

		try{
			logger.info("{} convert {} success.", str, type.getName());
			return Enum.valueOf(type, str);
		}catch(IllegalArgumentException e){
			try{
				logger.info("{} after toUpperCase convert {} success.", str, type.getName());
				return Enum.valueOf(type, str.toUpperCase());
			}catch(IllegalArgumentException ex){
				logger.warn("{} convert {} failure: {}.", str, type.getName(), ex.getMessage());
			}
		}

		return null;
	}

}

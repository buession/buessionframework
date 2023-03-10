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
package org.apache.ibatis.type;

import com.buession.core.serializer.JacksonJsonSerializer;
import com.buession.core.serializer.SerializerException;
import com.buession.core.validator.Validate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JSON 处理器，将 JSON 格式的字符串值和类型 &lt;E&gt; 进行转换
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DefaultJsonTypeHandler<E> extends AbstractJsonTypeHandler<E> {

	public DefaultJsonTypeHandler(Class<E> type){
		super(type);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException{
		JacksonJsonSerializer jsonSerializer = new JacksonJsonSerializer();

		try{
			if(jdbcType == null){
				ps.setString(i, jsonSerializer.serialize(parameter));
			}else{
				ps.setObject(i, jsonSerializer.serialize(parameter), jdbcType.TYPE_CODE);
			}
		}catch(SerializerException e){
			throw new SQLException(parameter + " cloud not be serialize to JSON String: " + e.getMessage());
		}
	}

	@Override
	protected E parseResult(final String str) throws SQLException{
		if(Validate.hasText(str)){
			JacksonJsonSerializer jsonSerializer = new JacksonJsonSerializer();
			try{
				return jsonSerializer.deserialize(str, type);
			}catch(SerializerException e){
				throw new SQLException(str + " cloud not be deserialize to " + type.getName() + ": " + e.getMessage());
			}
		}

		return null;
	}

}

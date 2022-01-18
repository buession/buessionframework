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

import com.buession.core.utils.ArrayUtils;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;

import java.sql.SQLException;
import java.util.Set;

/**
 * 默认 Set {@link TypeHandler}，将值以 "," 拆分转换为 Set&lt;String&gt;
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DefaultSetTypeHandler extends AbstractSetTypeHandler<String> {

	public DefaultSetTypeHandler(Class<String> type){
		super(type);
	}

	@Override
	protected Set<String> parseResult(final String str) throws SQLException{
		if(Validate.hasText(str)){
			return ArrayUtils.toSet(StringUtils.splitByWholeSeparatorPreserveAllTokens(str, DELIMITER));
		}

		return null;
	}

}

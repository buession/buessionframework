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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package org.apache.ibatis.type;

import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public class DefaultEnumSetTypeHandler<E extends Enum<E>> extends AbstractEnumSetTypeHandler<E> {

	private final static Logger logger = LoggerFactory.getLogger(DefaultEnumSetTypeHandler.class);

	public DefaultEnumSetTypeHandler(Class<E> type){
		super(type);
	}

	@Override
	protected E getValue(String str){
		if(Validate.hasText(str)){
			try{
				return Enum.valueOf(type, str.toUpperCase());
			}catch(IllegalArgumentException e){
				if(logger.isErrorEnabled()){
					logger.error("Database value '{}' convert to '{}' failure: {}", str, type.getName(),
							e.getMessage());
				}
			}
		}

		return null;
	}

}

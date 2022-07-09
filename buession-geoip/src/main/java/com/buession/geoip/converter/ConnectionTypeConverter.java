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
package com.buession.geoip.converter;

import com.buession.core.converter.Converter;
import com.buession.geoip.model.ConnectionType;
import com.maxmind.geoip2.model.ConnectionTypeResponse;

/**
 * 连接类型转换器
 *
 * @author Yong.Teng
 * @since 1.3.0
 */
class ConnectionTypeConverter implements Converter<ConnectionTypeResponse.ConnectionType,
		com.buession.geoip.model.ConnectionType> {

	@Override
	public ConnectionType convert(final ConnectionTypeResponse.ConnectionType source){
		if(source == null){
			return null;
		}

		switch(source){
			case DIALUP:
				return ConnectionType.DIALUP;
			case CABLE_DSL:
				return ConnectionType.CABLE_DSL;
			case CORPORATE:
				return ConnectionType.CORPORATE;
			case CELLULAR:
				return ConnectionType.CELLULAR;
			default:
				break;
		}

		return null;
	}

}

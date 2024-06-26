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
package com.buession.web.utils.useragentutils.devicetypefetcher;

import com.buession.core.utils.StringUtils;
import com.buession.lang.DeviceType;

/**
 * @author Yong.Teng
 * @since 2.2.1
 */
public class ContainsDeviceTypeFetcher implements DeviceTypeFetcher {

	private final String str;

	private final boolean ignoreCase;

	private final DeviceType deviceType;

	public ContainsDeviceTypeFetcher(final String str, final DeviceType deviceType){
		this(str, true, deviceType);
	}

	public ContainsDeviceTypeFetcher(final String str, final boolean ignoreCase, final DeviceType deviceType){
		this.str = str;
		this.ignoreCase = ignoreCase;
		this.deviceType = deviceType;
	}

	@Override
	public DeviceType fetch(final String userAgent){
		if(ignoreCase){
			return StringUtils.containsIgnoreCase(userAgent, str) ? deviceType : null;
		}else{
			return StringUtils.contains(userAgent, str) ? deviceType : null;
		}
	}

}

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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.net;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;
import com.buession.lang.NameValue;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * URL 参数
 *
 * @author Yong.Teng
 * @since 1.3.0
 */
public class UrlParameter extends NameValue<String, String> {

	private final static long serialVersionUID = 2759434143557141504L;

	/**
	 * 构造函数
	 */
	public UrlParameter() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		参数名称
	 * @param value
	 * 		参数值
	 */
	public UrlParameter(final String name, final String value) {
		setName(name);
		setValue(value);
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		参数名称
	 * @param value
	 * 		参数值
	 * @param encode
	 * 		是否编码
	 */
	public UrlParameter(final String name, final String value, final boolean encode) {
		setName(name);
		setValue(value, encode);
	}

	/**
	 * 设置参数名称
	 *
	 * @param name
	 * 		参数名称
	 */
	public void setName(final String name) {
		Assert.isBlank(name, "Parameter name cloud not be null or empty.");
		super.setName(name);
	}

	/**
	 * 设置参数值
	 *
	 * @param value
	 * 		参数值
	 */
	public void setValue(final String value) {
		setValue(value, true);
	}

	/**
	 * 设置参数值
	 *
	 * @param value
	 * 		参数值
	 * @param encode
	 * 		是否编码
	 */
	public void setValue(final String value, final boolean encode) {
		if(encode){
			super.setValue(value == null ? Constants.EMPTY_STRING : URLEncoder.encode(value, StandardCharsets.UTF_8));
		}else{
			super.setValue(Optional.ofNullable(value).orElse(Constants.EMPTY_STRING));
		}
	}

	@Override
	public String toString() {
		return Validate.hasText(getName()) ? getName() + '=' + getValue() : Constants.EMPTY_STRING;
	}

}

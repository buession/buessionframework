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
package com.buession.net;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.Optional;

/**
 * URL 参数
 *
 * @author Yong.Teng
 * @since 1.3.0
 */
public class UrlParameter {

	/**
	 * 参数名称
	 */
	private String name;

	/**
	 * 参数值
	 */
	private String value;

	/**
	 * 构造函数
	 */
	public UrlParameter(){
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		参数名称
	 * @param value
	 * 		参数值
	 */
	public UrlParameter(@NotNull final String name, final String value){
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
	public UrlParameter(@NotNull final String name, final String value, final boolean encode){
		setName(name);
		setValue(value, encode);
	}

	/**
	 * 设置参数名称
	 *
	 * @param name
	 * 		参数名称
	 */
	public void setName(@NotNull final String name){
		Assert.isBlank(name, "Parameter name cloud not be null or empty.");
		this.name = name;
	}

	/**
	 * 设置参数值
	 *
	 * @param value
	 * 		参数值
	 */
	public void setValue(final String value){
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
	public void setValue(final String value, final boolean encode){
		if(encode){
			try{
				this.value = value == null ? Constants.EMPTY_STRING : URLEncoder.encode(value, "UTF-8");
			}catch(UnsupportedEncodingException e){
				throw new RuntimeException("UTF-8 encoding does not support.");
			}
		}else{
			this.value = Optional.ofNullable(value).orElse(Constants.EMPTY_STRING);
		}
	}

	@Override
	public String toString(){
		return Validate.hasText(name) ? name + '=' + value : Constants.EMPTY_STRING;
	}

	@Override
	public int hashCode(){
		return Objects.hash(name, value);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof UrlParameter){
			UrlParameter that = (UrlParameter) obj;
			return Objects.equals(name, that.name) && Objects.equals(value, that.value);
		}

		return false;
	}

}

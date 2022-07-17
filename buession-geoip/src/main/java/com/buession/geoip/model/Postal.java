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
package com.buession.geoip.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 包含与 IP 地址关联的邮政信息数据。
 *
 * @author Yong.Teng
 */
public class Postal implements Serializable {

	private final static long serialVersionUID = -570124317632308660L;

	/**
	 * 邮政编码
	 */
	private final String code;

	/**
	 * The confidence for Postal Code
	 */
	private final Integer confidence;

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		邮政编码
	 * @param confidence
	 * 		The confidence for Postal Code
	 */
	public Postal(final String code, final Integer confidence){
		this.code = code;
		this.confidence = confidence;
	}

	/**
	 * 返回邮政编码
	 *
	 * @return 邮政编码
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Return the confidence for Postal Code
	 *
	 * @return The confidence for Postal Code
	 */
	public Integer getConfidence(){
		return confidence;
	}

	@Override
	public int hashCode(){
		return Objects.hash(code, confidence);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Postal){
			Postal that = (Postal) obj;
			return Objects.equals(code, that.code) && Objects.equals(confidence, that.confidence);
		}

		return false;
	}

	@Override
	public String toString(){
		return "Postal{" + "code='" + code + '\'' + ", confidence=" + confidence + '}';
	}

}

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
 * 包含与 IP 地址关联的洲记录的数据。
 *
 * @author Yong.Teng
 */
public final class Continent implements Serializable {

	private final static long serialVersionUID = -5463257987682894006L;

	/**
	 * 洲名称 ID
	 */
	private final Integer geoNameId;

	/**
	 * 洲编码
	 */
	private final String code;

	/**
	 * 洲原始名称
	 */
	private final String originalName;

	/**
	 * 洲名称
	 */
	private final String name;

	/**
	 * 构造函数
	 *
	 * @param geoNameId
	 * 		洲名称 ID
	 * @param code
	 * 		洲编码
	 * @param originalName
	 * 		洲原始名称
	 * @param name
	 * 		洲名称
	 */
	public Continent(final Integer geoNameId, final String code, final String originalName, final String name){
		this.geoNameId = geoNameId;
		this.code = code;
		this.originalName = originalName;
		this.name = name;
	}

	/**
	 * 返回洲名称 ID
	 *
	 * @return 洲名称 ID
	 */
	public Integer getGeoNameId(){
		return geoNameId;
	}

	/**
	 * 返回洲编码
	 *
	 * @return 洲编码
	 */
	public String getCode(){
		return code;
	}

	/**
	 * 返回洲原始名称
	 *
	 * @return 洲原始名称
	 */
	public String getOriginalName(){
		return originalName;
	}

	/**
	 * 返回洲名称
	 *
	 * @return 洲名称
	 */
	public String getName(){
		return name;
	}

	@Override
	public int hashCode(){
		return Objects.hash(geoNameId, code, originalName, name);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Continent){
			Continent that = (Continent) obj;
			return Objects.equals(geoNameId, that.geoNameId) && Objects.equals(code, that.code);
		}

		return false;
	}

	@Override
	public String toString(){
		return "Continent{" + "geoNameId=" + geoNameId + ", code=" + code + ", originalName=" + originalName +
				", name=" + name + '}';
	}

}

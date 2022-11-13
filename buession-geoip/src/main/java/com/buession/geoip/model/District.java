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
 * 包含与 IP 地址关联的城市数据
 *
 * @author Yong.Teng
 */
public final class District implements Serializable {

	private final static long serialVersionUID = -8098424244620246891L;

	/**
	 * 城市名称 ID
	 */
	private final Integer geoNameId;

	/**
	 * 城市编码
	 */
	private final Integer code;

	/**
	 * 城市原始名称
	 */
	private final String originalName;

	/**
	 * 城市名称
	 */
	private final String name;

	/**
	 * 城市名称全称
	 */
	private final String fullName;

	/**
	 * 城市邮政信息
	 */
	private final Postal postal;

	/**
	 * 城市上级区域
	 */
	private final District parent;

	/**
	 * 构造函数
	 *
	 * @param geoNameId
	 * 		城市名称 ID
	 * @param code
	 * 		城市编码
	 * @param originalName
	 * 		城市原始名称
	 * @param name
	 * 		城市名称
	 * @param postal
	 * 		城市邮政信息
	 * @param parent
	 * 		城市上级区域
	 */
	public District(final Integer geoNameId, final Integer code, final String originalName, final String name,
					final Postal postal, final District parent){
		this.geoNameId = geoNameId;
		this.code = code;
		this.originalName = originalName;
		this.name = name;
		this.postal = postal;
		this.parent = parent;

		if(name != null){
			this.fullName = parent != null ? parent.getName() + name : name;
		}else{
			this.fullName = null;
		}
	}

	/**
	 * 返回城市名称 ID
	 *
	 * @return 城市名称 ID
	 */
	public Integer getGeoNameId(){
		return geoNameId;
	}

	/**
	 * 返回城市编码
	 *
	 * @return 城市编码
	 */
	public Integer getCode(){
		return code;
	}

	/**
	 * 返回城市原始名称
	 *
	 * @return 城市原始名称
	 */
	public String getOriginalName(){
		return originalName;
	}

	/**
	 * 返回城市名称
	 *
	 * @return 城市名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 返回城市名称全称
	 *
	 * @return 城市名称全称
	 */
	public String getFullName(){
		return fullName;
	}

	/**
	 * 返回城市邮政信息
	 *
	 * @return 城市邮政信息
	 */
	public Postal getPostal(){
		return postal;
	}

	/**
	 * 返回城市上级区域
	 *
	 * @return 城市上级区域
	 */
	public District getParent(){
		return parent;
	}

	@Override
	public int hashCode(){
		return Objects.hash(geoNameId, code, originalName, name, fullName, postal, parent);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof District){
			District that = (District) obj;
			return Objects.equals(geoNameId, that.geoNameId) && Objects.equals(code, that.code) &&
					Objects.equals(originalName, that.originalName) && Objects.equals(name, that.name);
		}

		return false;
	}

	@Override
	public String toString(){
		return "District{" + "geoNameId=" + geoNameId + ", code=" + code + ", originalName=" + originalName +
				", name=" + name +  ", fullName=" + fullName + ", postal=" + postal + ", parent=" +
				parent + '}';
	}

}

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
 * | Copyright @ 2013-2025 Buession.com Inc.														|
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
public final class District extends AbstractConfidenceRecord implements Serializable {

	private final static long serialVersionUID = -8098424244620246891L;

	/**
	 * 行政区域 ISO Code
	 *
	 * @since 4.0.0
	 */
	private final String isoCode;

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
	 * @param isoCode
	 * 		行政区域 ISO Code
	 * @param originalName
	 * 		城市原始名称
	 * @param name
	 * 		城市名称
	 * @param confidence
	 * 		A value from 0-100 indicating MaxMind's confidence that the city is correct.
	 * @param postal
	 * 		城市邮政信息
	 * @param parent
	 * 		城市上级区域
	 */
	public District(final Long geoNameId, final String isoCode, final String originalName, final String name,
					final Integer confidence, final Postal postal, final District parent) {
		super(geoNameId, originalName, name, confidence);
		this.isoCode = isoCode;
		this.postal = postal;
		this.parent = parent;

		if(name != null){
			this.fullName = parent != null ? parent.getName() + name : name;
		}else{
			this.fullName = null;
		}
	}

	/**
	 * 返回行政区域 ISO Code
	 *
	 * @return ISO Code
	 *
	 * @since 4.0.0
	 */
	public String getIsoCode() {
		return isoCode;
	}

	/**
	 * 返回城市名称全称
	 *
	 * @return 城市名称全称
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 返回城市邮政信息
	 *
	 * @return 城市邮政信息
	 */
	public Postal getPostal() {
		return postal;
	}

	/**
	 * 返回城市上级区域
	 *
	 * @return 城市上级区域
	 */
	public District getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getGeoNameId(), getConfidence(), getOriginalName(), getName(), fullName, postal, parent);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof District){
			District that = (District) obj;
			return Objects.equals(getGeoNameId(), that.getGeoNameId()) &&
					Objects.equals(getIsoCode(), that.getIsoCode()) &&
					Objects.equals(getConfidence(), that.getConfidence()) &&
					Objects.equals(getOriginalName(), that.getOriginalName()) &&
					Objects.equals(getName(), that.getName());
		}

		return false;
	}

	@Override
	public String toString() {
		return "District{" + "geoNameId=" + getGeoNameId() + ", ISO Code=" + getIsoCode() + ", confidence=" +
				getConfidence() + ", originalName=" + getOriginalName() + ", name=" + getName() + ", fullName=" +
				fullName + ", postal=" + postal + ", parent=" + parent + '}';
	}

}

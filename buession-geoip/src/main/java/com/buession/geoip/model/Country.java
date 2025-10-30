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
 * 包含与 IP 地址关联的国家记录的数据。
 *
 * @author Yong.Teng
 */
public final class Country extends AbstractConfidenceRecord implements Serializable {

	private final static long serialVersionUID = -7665239490542479833L;

	/**
	 * 国家名称全称
	 */
	private final String fullName;

	/**
	 * 国家 ISO Code
	 *
	 * @since 4.0.0
	 */
	private final String isoCode;

	/**
	 * IP 地址是否在欧盟
	 *
	 * @since 1.3.0
	 */
	private final boolean isInEuropeanUnion;

	/**
	 * 构造函数
	 *
	 * @param geoNameId
	 * 		国家名称 ID
	 * @param isoCode
	 * 		国家 ISO Code
	 * @param originalName
	 * 		国家原始名称
	 * @param name
	 * 		国家名称
	 * @param fullName
	 * 		国家名称全称
	 * @param confidence
	 * 		A value from 0-100 indicating MaxMind's confidence that the country is correct.
	 * @param isInEuropeanUnion
	 * 		IP 地址是否在欧盟
	 */
	public Country(final Long geoNameId, final String isoCode, final String originalName, final String name,
				   final String fullName, final Integer confidence, final boolean isInEuropeanUnion) {
		super(geoNameId, originalName, name, confidence);
		this.isoCode = isoCode;
		this.fullName = fullName;
		this.isInEuropeanUnion = isInEuropeanUnion;
	}

	/**
	 * 返回国家 ISO Code
	 *
	 * @return 国家 ISO Code
	 *
	 * @since 4.0.0
	 */
	public String getIsoCode() {
		return isoCode;
	}

	/**
	 * 返回国家名称全称
	 *
	 * @return 国家名称全称
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 返回 IP 地址是否在欧盟
	 *
	 * @return IP 在欧盟，返回 true；否则，返回 false
	 *
	 * @since 1.3.0
	 */
	public boolean isInEuropeanUnion() {
		return isInEuropeanUnion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getGeoNameId(), getIsoCode(), getConfidence(), getOriginalName(), getName(), fullName,
				isInEuropeanUnion);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof Country){
			Country that = (Country) obj;
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
		return "Country{" + "geoNameId=" + getGeoNameId() + ", ISO code=" + getIsoCode() + ", confidence=" +
				getConfidence() + ", originalName=" + getOriginalName() + ", name=" + getName() + ", fullName=" +
				fullName + ", isInEuropeanUnion=" + isInEuropeanUnion + '}';
	}

}

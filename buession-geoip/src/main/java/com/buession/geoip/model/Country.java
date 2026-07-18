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
 * | Copyright @ 2013-2026 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 包含与 IP 地址关联的国家记录的数据。
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
 *
 * @author Yong.Teng
 */
public record Country(Long geoNameId, String isoCode, String originalName, String name, String fullName,
                      Integer confidence, boolean isInEuropeanUnion)
		implements NameRecord, ConfidenceRecord, Serializable {

	private final static long serialVersionUID = -7665239490542479833L;

	@Override
	public int hashCode() {
		return Objects.hash(geoNameId(), isoCode(), confidence(), originalName(), name(), fullName(),
				isInEuropeanUnion());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof Country that){
			return Objects.equals(geoNameId(), that.geoNameId()) &&
					Objects.equals(isoCode(), that.isoCode());
		}

		return false;
	}

	@Override
	public String toString() {
		return "Country{" + "geoNameId=" + geoNameId() + ", ISO code=" + isoCode() + ", confidence=" +
				confidence() + ", originalName=" + originalName() + ", name=" + name() + ", fullName=" +
				fullName() + ", isInEuropeanUnion=" + isInEuropeanUnion() + '}';
	}

}

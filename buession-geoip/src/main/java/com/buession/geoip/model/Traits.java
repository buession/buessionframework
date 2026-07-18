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

import com.maxmind.geoip2.model.ConnectionTypeResponse;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 包含与 IP 地址关联的特征记录的数据。
 *
 * @author Yong.Teng
 */
public record Traits(String ipAddress, String domain, String isp, Network network,
                     ConnectionTypeResponse.ConnectionType connectionType, Organization organization,
                     Organization autonomousSystemOrganization, Long autonomousSystemNumber,
                     String mobileCountryCode, String mobileNetworkCode, boolean isAnonymous,
                     boolean isAnonymousProxy, boolean isAnonymousVpn, boolean isHostingProvider,
                     boolean isLegitimateProxy, boolean isPublicProxy, boolean isResidentialProxy,
                     boolean isSatelliteProvider, boolean isTorExitNode, boolean isAnycast, Double ipRiskSnapshot,
                     String userType, Integer userCount, Double staticIpScore) implements Serializable {

	private final static long serialVersionUID = 8560280121212334774L;

	@Override
	public int hashCode() {
		return Objects.hash(ipAddress(), domain(), isp(), network(), connectionType(), organization(),
				autonomousSystemOrganization(), autonomousSystemNumber(), mobileCountryCode(), mobileNetworkCode(),
				isAnonymous(), isAnonymousProxy(), isAnonymousVpn(), isHostingProvider(), isLegitimateProxy(),
				isPublicProxy(), isResidentialProxy(), isSatelliteProvider(), isTorExitNode(), isAnycast(),
				ipRiskSnapshot(), userType(), userCount(), staticIpScore());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof Traits that){
			return Objects.equals(ipAddress(), that.ipAddress());
		}

		return false;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "Traits{", "}")
				.add("ipAddress='" + ipAddress + "'")
				.add("domain='" + domain + "'")
				.add("isp='" + isp + "'")
				.add("network=" + network)
				.add("connectionType=" + connectionType)
				.add("organization=" + organization)
				.add("autonomousSystemOrganization=" + autonomousSystemOrganization)
				.add("autonomousSystemNumber=" + autonomousSystemNumber)
				.add("mobileCountryCode='" + mobileCountryCode + "'")
				.add("mobileNetworkCode='" + mobileNetworkCode + "'")
				.add("isAnonymous=" + isAnonymous)
				.add("isAnonymousProxy=" + isAnonymousProxy)
				.add("isAnonymousVpn=" + isAnonymousVpn)
				.add("isHostingProvider=" + isHostingProvider)
				.add("isLegitimateProxy=" + isLegitimateProxy)
				.add("isPublicProxy=" + isPublicProxy)
				.add("isResidentialProxy=" + isResidentialProxy)
				.add("isSatelliteProvider=" + isSatelliteProvider)
				.add("isTorExitNode=" + isTorExitNode)
				.add("isAnycast=" + isAnycast)
				.add("ipRiskSnapshot=" + ipRiskSnapshot)
				.add("userType='" + userType + "'")
				.add("userCount=" + userCount)
				.add("staticIpScore=" + staticIpScore)
				.toString();
	}

}

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
package com.buession.geoip.converter;

import com.buession.geoip.model.Network;
import com.buession.geoip.model.Organization;
import com.buession.geoip.model.Traits;
import com.maxmind.geoip2.model.AsnResponse;

import java.util.Locale;
import java.util.Optional;

/**
 * 特征记录数据转换器
 *
 * @author Yong.Teng
 */
public class TraitsConverter extends AbstractConverter<Traits, com.maxmind.geoip2.record.Traits, Object> {

	private AsnResponse asnResponse;

	public AsnResponse getAsnResponse() {
		return asnResponse;
	}

	public void setAsnResponse(AsnResponse asnResponse) {
		this.asnResponse = asnResponse;
	}

	@Override
	public Traits converter(com.maxmind.geoip2.record.Traits traits) {
		if(traits == null){
			return null;
		}

		Network network;

		network = traits.network() == null ? null : new Network(traits.ipAddress(),
				traits.network().prefixLength(), traits.network().networkAddress());

		final Organization organization = traits.organization() == null ? null :
				new Organization(traits.organization());
		final Organization autonomousSystemOrganization = parseAutonomousSystemOrganization(traits);
		final Long autonomousSystemNumber = Optional.ofNullable(traits.autonomousSystemNumber())
				.orElse(asnResponse.autonomousSystemNumber());

		return new Traits(traits.ipAddress().getHostAddress(), traits.domain(), traits.isp(), network,
				traits.connectionType(), organization, autonomousSystemOrganization, autonomousSystemNumber,
				traits.mobileCountryCode(), traits.mobileNetworkCode(), traits.isAnonymous(), false,
				traits.isAnonymousVpn(), traits.isHostingProvider(), traits.isLegitimateProxy(),
				traits.isPublicProxy(), traits.isPublicProxy(), false, traits.isTorExitNode(), traits.isAnycast(),
				traits.ipRiskSnapshot(), traits.userType(), traits.userCount(), traits.staticIpScore());
	}

	@Override
	public Traits converter(com.maxmind.geoip2.record.Traits traits, Object response, Locale locale) {
		return converter(traits);
	}

	private Organization parseAutonomousSystemOrganization(com.maxmind.geoip2.record.Traits traits) {
		if(traits.autonomousSystemOrganization() == null){
			return asnResponse == null ? null : new Organization(asnResponse.autonomousSystemOrganization());
		}else{
			return new Organization(traits.autonomousSystemOrganization());
		}
	}

}

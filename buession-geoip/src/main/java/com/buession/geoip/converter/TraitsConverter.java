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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.converter;

import com.buession.geoip.model.Organization;
import com.buession.geoip.model.Traits;
import com.maxmind.geoip2.model.AbstractResponse;

import java.util.Locale;

/**
 * @author Yong.Teng
 */
@SuppressWarnings({"deprecation"})
public class TraitsConverter extends AbstractConverter<Traits, com.maxmind.geoip2.record.Traits, AbstractResponse> {

	@Override
	public Traits converter(com.maxmind.geoip2.record.Traits traits, Locale locale){
		if(traits == null){
			return null;
		}

		final Organization organization = traits.getOrganization() == null ? null : new Organization(traits
				.getOrganization());
		final Organization autonomousSystemOrganization = traits.getAutonomousSystemOrganization() == null ? null :
				new Organization(traits.getAutonomousSystemOrganization());

		return new Traits(traits.getIpAddress(), traits.getDomain(), traits.getIsp(), organization,
				autonomousSystemOrganization, traits.getAutonomousSystemNumber(), traits.isAnonymous(), traits
				.isAnonymousProxy(), traits.isAnonymousVpn(), traits.isHostingProvider(), traits.isLegitimateProxy(),
				traits.isPublicProxy(), traits.isSatelliteProvider(), traits.isTorExitNode());
	}

}

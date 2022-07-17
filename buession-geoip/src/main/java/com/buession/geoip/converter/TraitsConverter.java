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
package com.buession.geoip.converter;

import com.buession.geoip.model.ConnectionType;
import com.buession.geoip.model.Network;
import com.buession.geoip.model.Organization;
import com.buession.geoip.model.Traits;
import com.maxmind.geoip2.model.AbstractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 * 特征记录数据转换器
 *
 * @author Yong.Teng
 */
@SuppressWarnings({"deprecation"})
public class TraitsConverter extends AbstractConverter<Traits, com.maxmind.geoip2.record.Traits, AbstractResponse> {

	private final static ConnectionTypeConverter connectionTypeConverter = new ConnectionTypeConverter();

	private final static Logger logger = LoggerFactory.getLogger(TraitsConverter.class);

	@Override
	public Traits converter(com.maxmind.geoip2.record.Traits traits){
		if(traits == null){
			return null;
		}

		Network network;

		try{
			network = traits.getNetwork() == null ? null : new Network(traits.getIpAddress(),
					traits.getNetwork().getPrefixLength(), traits.getNetwork().getNetworkAddress());
		}catch(UnknownHostException e){
			logger.warn(e.getMessage());
			network = new Network((InetAddress) null, traits.getNetwork().getPrefixLength(),
					traits.getNetwork().getNetworkAddress());
		}

		final ConnectionType connectionType = connectionTypeConverter.convert(traits.getConnectionType());
		final Organization organization =
				traits.getOrganization() == null ? null : new Organization(traits.getOrganization());
		final Organization autonomousSystemOrganization = traits.getAutonomousSystemOrganization() == null ? null :
				new Organization(traits.getAutonomousSystemOrganization());

		return new Traits(traits.getIpAddress(), traits.getDomain(), traits.getIsp(), network, connectionType,
				organization, autonomousSystemOrganization, traits.getAutonomousSystemNumber(),
				traits.getMobileCountryCode(), traits.getMobileNetworkCode(), traits.isAnonymous(),
				traits.isAnonymousProxy(), traits.isAnonymousVpn(), traits.isHostingProvider(),
				traits.isLegitimateProxy(), traits.isPublicProxy(), traits.isPublicProxy(),
				traits.isSatelliteProvider(), traits.isTorExitNode(), traits.getUserType(), traits.getUserCount(),
				traits.getStaticIpScore());
	}

	@Override
	public Traits converter(com.maxmind.geoip2.record.Traits traits, AbstractResponse response, Locale locale){
		return converter(traits);
	}

}

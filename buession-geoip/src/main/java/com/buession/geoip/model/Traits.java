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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 包含与 IP 地址关联的特征记录的数据。
 *
 * @author Yong.Teng
 */
public final class Traits implements Serializable {

	private final static long serialVersionUID = 8560280121212334774L;

	private final String ipAddress;

	private final String domain;

	private final String isp;

	/**
	 * 网络
	 *
	 * @since 1.3.0
	 */
	private final Network network;

	/**
	 * 连接方式
	 *
	 * @since 1.3.0
	 */
	private final ConnectionType connectionType;

	private final Organization organization;

	private final Organization autonomousSystemOrganization;

	private final Integer autonomousSystemNumber;

	private final boolean isAnonymous;

	private final boolean isAnonymousProxy;

	private final boolean isAnonymousVpn;

	private final boolean isHostingProvider;

	private final boolean isLegitimateProxy;

	private final boolean isPublicProxy;

	private final boolean isResidentialProxy;

	private final boolean isSatelliteProvider;

	private final boolean isTorExitNode;

	/**
	 * 用户类型
	 *
	 * @since 1.3.0
	 */
	private final String userType;

	/**
	 * 用户数量
	 *
	 * @since 1.3.0
	 */
	private final Integer userCount;

	/**
	 * 静态 IP 评分
	 *
	 * @since 1.3.0
	 */
	private final Double staticIpScore;

	public Traits(final String ipAddress, final String domain, final String isp, final Network network,
				  final ConnectionType connectionType, final Organization organization,
				  final Organization autonomousSystemOrganization, final Integer autonomousSystemNumber,
				  final boolean isAnonymous, final boolean isAnonymousProxy, final boolean isAnonymousVpn,
				  final boolean isHostingProvider, final boolean isLegitimateProxy, final boolean isPublicProxy,
				  final boolean isResidentialProxy, final boolean isSatelliteProvider, final boolean isTorExitNode,
				  final String userType, final Integer userCount, final Double staticIpScore){
		this.ipAddress = ipAddress;
		this.domain = domain;
		this.isp = isp;
		this.network = network;
		this.connectionType = connectionType;
		this.organization = organization;
		this.autonomousSystemOrganization = autonomousSystemOrganization;
		this.autonomousSystemNumber = autonomousSystemNumber;
		this.isAnonymous = isAnonymous;
		this.isAnonymousProxy = isAnonymousProxy;
		this.isAnonymousVpn = isAnonymousVpn;
		this.isHostingProvider = isHostingProvider;
		this.isLegitimateProxy = isLegitimateProxy;
		this.isPublicProxy = isPublicProxy;
		this.isResidentialProxy = isResidentialProxy;
		this.isSatelliteProvider = isSatelliteProvider;
		this.isTorExitNode = isTorExitNode;
		this.userType = userType;
		this.userCount = userCount;
		this.staticIpScore = staticIpScore;
	}

	public String getIpAddress(){
		return ipAddress;
	}

	public String getDomain(){
		return domain;
	}

	public String getIsp(){
		return isp;
	}

	/**
	 * 返回网络
	 *
	 * @return 网络
	 *
	 * @since 1.3.0
	 */
	public Network getNetwork(){
		return network;
	}

	/**
	 * 返回连接方式
	 *
	 * @return 连接方式
	 *
	 * @since 1.3.0
	 */
	public ConnectionType getConnectionType(){
		return connectionType;
	}

	public Organization getOrganization(){
		return organization;
	}

	public Organization getAutonomousSystemOrganization(){
		return autonomousSystemOrganization;
	}

	public Integer getAutonomousSystemNumber(){
		return autonomousSystemNumber;
	}

	public boolean isAnonymous(){
		return isAnonymous;
	}

	public boolean isAnonymousProxy(){
		return isAnonymousProxy;
	}

	public boolean isAnonymousVpn(){
		return isAnonymousVpn;
	}

	public boolean isHostingProvider(){
		return isHostingProvider;
	}

	public boolean isLegitimateProxy(){
		return isLegitimateProxy;
	}

	public boolean isPublicProxy(){
		return isPublicProxy;
	}

	public boolean isResidentialProxy(){
		return isResidentialProxy;
	}

	public boolean isSatelliteProvider(){
		return isSatelliteProvider;
	}

	public boolean isTorExitNode(){
		return isTorExitNode;
	}

	/**
	 * 返回用户类型
	 *
	 * @return 用户类型
	 *
	 * @since 1.3.0
	 */
	public String getUserType(){
		return userType;
	}

	/**
	 * 返回用户数量
	 *
	 * @return 用户数量
	 *
	 * @since 1.3.0
	 */
	public Integer getUserCount(){
		return userCount;
	}

	/**
	 * 返回静态 IP 评分
	 *
	 * @return 静态 IP 评分
	 *
	 * @since 1.3.0
	 */
	public Double getStaticIpScore(){
		return staticIpScore;
	}

	@Override
	public int hashCode(){
		return Objects.hash(ipAddress, domain, isp, network, connectionType, organization,
				autonomousSystemOrganization, autonomousSystemNumber, isAnonymous, isAnonymousProxy, isAnonymousVpn,
				isHostingProvider, isLegitimateProxy, isPublicProxy, isResidentialProxy, isSatelliteProvider,
				isTorExitNode, userType, userCount, staticIpScore);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Traits){
			Traits that = (Traits) obj;

			if(isAnonymous == that.isAnonymous && isAnonymousProxy == that.isAnonymousProxy && isAnonymousVpn == that.isAnonymousVpn && isHostingProvider == that.isHostingProvider && isLegitimateProxy == that.isLegitimateProxy && isPublicProxy == that.isPublicProxy && isResidentialProxy == that.isResidentialProxy && isSatelliteProvider == that.isSatelliteProvider && isTorExitNode == that.isTorExitNode){
				return Objects.equals(ipAddress, that.ipAddress) && Objects.equals(domain, that.domain) && Objects.equals(isp, that.isp) && Objects.equals(network, that.network) && connectionType == that.connectionType && Objects.equals(organization, that.organization) && Objects.equals(autonomousSystemOrganization, that.autonomousSystemOrganization) && Objects.equals(autonomousSystemNumber, that.autonomousSystemNumber) && Objects.equals(userType, that.userType) && Objects.equals(userCount, that.userCount) && Objects.equals(staticIpScore, that.staticIpScore);
			}
		}

		return false;
	}

	@Override
	public String toString(){
		return "Traits{" + "ipAddress='" + ipAddress + '\'' + ", domain='" + domain + '\'' + ", isp='" + isp + '\'' +
				", network=" + network + ", connectionType=" + connectionType + ", organization=" + organization + ", "
				+ "autonomousSystemOrganization" + "=" + autonomousSystemOrganization + ", autonomousSystemNumber=" + autonomousSystemNumber + ", " + "isAnonymous=" + isAnonymous + ", " + "isAnonymousProxy=" + isAnonymousProxy + ", isAnonymousVpn=" + isAnonymousVpn + ", " + "isHostingProvider=" + isHostingProvider + ", isLegitimateProxy=" + isLegitimateProxy + ", " + "isPublicProxy=" + isPublicProxy + ", isSatelliteProvider=" + isSatelliteProvider + ", " + "isTorExitNode=" + isTorExitNode + ", userType='" + isTorExitNode + '\'' + ", userCount=" + userCount + ", staticIpScore=" + staticIpScore + '}';
	}

}

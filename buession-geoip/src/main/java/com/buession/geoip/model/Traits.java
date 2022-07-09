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
 * 包含与 IP 地址关联的特征记录的数据。
 *
 * @author Yong.Teng
 */
public final class Traits implements Serializable {

	private final static long serialVersionUID = 8560280121212334774L;

	/**
	 * IP 地址
	 */
	private final String ipAddress;

	/**
	 * 域名
	 */
	private final String domain;

	/**
	 * ISP 运营商
	 */
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

	/**
	 * 所属机构
	 */
	private final Organization organization;

	/**
	 * Autonomous 系统机构
	 */
	private final Organization autonomousSystemOrganization;

	/**
	 * Autonomous 系统号码
	 */
	private final Integer autonomousSystemNumber;

	/**
	 * 移动国家代码
	 */
	private final String mobileCountryCode;

	/**
	 * 移动网络代码
	 */
	private final String mobileNetworkCode;

	/**
	 * 是否是 Autonomous
	 */
	private final boolean isAnonymous;

	/**
	 * 是否是 Autonomous 代理
	 */
	private final boolean isAnonymousProxy;

	/**
	 * 是否是 Autonomous VPN
	 */
	private final boolean isAnonymousVpn;

	/**
	 * 是否是主机提供商
	 */
	private final boolean isHostingProvider;

	/**
	 * 是否是合法的代理
	 */
	private final boolean isLegitimateProxy;

	/**
	 * 是否是公共代理
	 */
	private final boolean isPublicProxy;

	/**
	 * 是否是住宅/家庭代理
	 */
	private final boolean isResidentialProxy;

	/**
	 * 是否是卫星提供商
	 */
	private final boolean isSatelliteProvider;

	/**
	 * Is tor exit node
	 */
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

	/**
	 * 构造函数
	 *
	 * @param ipAddress
	 * 		IP 地址
	 * @param domain
	 * 		域名
	 * @param isp
	 * 		ISP 运营商
	 * @param network
	 * 		网络
	 * @param connectionType
	 * 		连接方式
	 * @param organization
	 * 		所属机构
	 * @param autonomousSystemOrganization
	 * 		Autonomous 系统机构
	 * @param autonomousSystemNumber
	 * 		Autonomous 系统号码
	 * @param mobileCountryCode
	 * 		移动国家代码
	 * @param isAnonymous
	 * 		是否是 Autonomous
	 * @param isAnonymousProxy
	 * 		是否是 Autonomous 代理
	 * @param isAnonymousVpn
	 * 		是否是 Autonomous VPN
	 * @param isHostingProvider
	 * 		是否是主机提供商
	 * @param isLegitimateProxy
	 * 		是否是合法的代理
	 * @param isPublicProxy
	 * 		是否是公共代理
	 * @param isResidentialProxy
	 * 		是否是住宅/家庭代理
	 * @param isSatelliteProvider
	 * 		是否是卫星提供商
	 * @param isTorExitNode
	 * 		Is tor exit node
	 * @param userType
	 * 		用户类型
	 * @param userCount
	 * 		用户数量
	 * @param staticIpScore
	 * 		静态 IP 评分
	 */
	public Traits(final String ipAddress, final String domain, final String isp, final Network network,
				  final ConnectionType connectionType, final Organization organization,
				  final Organization autonomousSystemOrganization, final Integer autonomousSystemNumber,
				  final String mobileCountryCode, final String mobileNetworkCode,
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
		this.mobileCountryCode = mobileCountryCode;
		this.mobileNetworkCode = mobileNetworkCode;
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

	/**
	 * 返回 IP 地址
	 *
	 * @return IP 地址
	 */
	public String getIpAddress(){
		return ipAddress;
	}

	/**
	 * 返回域名
	 *
	 * @return 域名
	 */
	public String getDomain(){
		return domain;
	}

	/**
	 * 返回 ISP 运营商
	 *
	 * @return ISP 运营商
	 */
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

	/**
	 * 返回所属机构
	 *
	 * @return 所属机构
	 */
	public Organization getOrganization(){
		return organization;
	}

	/**
	 * 返回 Autonomous 系统机构
	 *
	 * @return Autonomous 系统机构
	 */
	public Organization getAutonomousSystemOrganization(){
		return autonomousSystemOrganization;
	}

	/**
	 * 返回 Autonomous 系统号码
	 *
	 * @return Autonomous 系统号码
	 */
	public Integer getAutonomousSystemNumber(){
		return autonomousSystemNumber;
	}

	/**
	 * 返回移动国家代码
	 *
	 * @return 移动国家代码
	 */
	public String getMobileCountryCode(){
		return mobileCountryCode;
	}

	/**
	 * 返回移动网络代码
	 *
	 * @return 移动网络代码
	 */
	public String getMobileNetworkCode(){
		return mobileNetworkCode;
	}

	/**
	 * 返回是否是 Autonomous
	 *
	 * @return 是否是 Autonomous
	 */
	public boolean isAnonymous(){
		return isAnonymous;
	}

	/**
	 * 返回是否是 Autonomous 代理
	 *
	 * @return 是否是 Autonomous 代理
	 */
	public boolean isAnonymousProxy(){
		return isAnonymousProxy;
	}

	/**
	 * 返回是否是 Autonomous VPN
	 *
	 * @return 是否是 Autonomous VPN
	 */
	public boolean isAnonymousVpn(){
		return isAnonymousVpn;
	}

	/**
	 * 返回是否是主机提供商
	 *
	 * @return 是否是主机提供商
	 */
	public boolean isHostingProvider(){
		return isHostingProvider;
	}

	/**
	 * 返回是否是合法的代理
	 *
	 * @return 是否是合法的代理
	 */
	public boolean isLegitimateProxy(){
		return isLegitimateProxy;
	}

	/**
	 * 返回是否是公共代理
	 *
	 * @return 是否是公共代理
	 */
	public boolean isPublicProxy(){
		return isPublicProxy;
	}

	/**
	 * 返回是否是住宅/家庭代理
	 *
	 * @return 是否是住宅/家庭代理
	 */
	public boolean isResidentialProxy(){
		return isResidentialProxy;
	}

	/**
	 * 返回是否是卫星提供商
	 *
	 * @return 是否是卫星提供商
	 */
	public boolean isSatelliteProvider(){
		return isSatelliteProvider;
	}

	/**
	 * Return s tor exit node
	 *
	 * @return Is tor exit node
	 */
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

			if(isAnonymous == that.isAnonymous && isAnonymousProxy == that.isAnonymousProxy &&
					isAnonymousVpn == that.isAnonymousVpn && isHostingProvider == that.isHostingProvider &&
					isLegitimateProxy == that.isLegitimateProxy && isPublicProxy == that.isPublicProxy &&
					isResidentialProxy == that.isResidentialProxy && isSatelliteProvider == that.isSatelliteProvider &&
					isTorExitNode == that.isTorExitNode){
				return Objects.equals(ipAddress, that.ipAddress) && Objects.equals(domain, that.domain) &&
						Objects.equals(isp, that.isp) && Objects.equals(network, that.network) &&
						connectionType == that.connectionType && Objects.equals(organization, that.organization) &&
						Objects.equals(autonomousSystemOrganization, that.autonomousSystemOrganization) &&
						Objects.equals(autonomousSystemNumber, that.autonomousSystemNumber) &&
						Objects.equals(userType, that.userType) && Objects.equals(userCount, that.userCount) &&
						Objects.equals(staticIpScore, that.staticIpScore);
			}
		}

		return false;
	}

	@Override
	public String toString(){
		return "Traits{" + "ipAddress='" + ipAddress + '\'' + ", domain='" + domain + '\'' + ", isp='" + isp + '\'' +
				", network=" + network + ", connectionType=" + connectionType + ", organization=" + organization + ", "
				+ "autonomousSystemOrganization" + "=" + autonomousSystemOrganization + ", autonomousSystemNumber=" +
				autonomousSystemNumber + ", " + "isAnonymous=" + isAnonymous + ", " + "isAnonymousProxy=" +
				isAnonymousProxy + ", isAnonymousVpn=" + isAnonymousVpn + ", " + "isHostingProvider=" +
				isHostingProvider + ", isLegitimateProxy=" + isLegitimateProxy + ", " + "isPublicProxy=" +
				isPublicProxy + ", isSatelliteProvider=" + isSatelliteProvider + ", " + "isTorExitNode=" +
				isTorExitNode + ", userType='" + isTorExitNode + '\'' + ", userCount=" + userCount +
				", staticIpScore=" + staticIpScore + '}';
	}

}

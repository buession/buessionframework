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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.model;

import java.io.Serializable;

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

    private final Organization organization;

    private final Organization autonomousSystemOrganization;

    private final int autonomousSystemNumber;

    private final boolean isAnonymous;

    private final boolean isAnonymousProxy;

    private final boolean isAnonymousVpn;

    private final boolean isHostingProvider;

    private final boolean isLegitimateProxy;

    private final boolean isPublicProxy;

    private final boolean isSatelliteProvider;

    private final boolean isTorExitNode;

    public Traits(final String ipAddress, final String domain, final String isp, final Organization organization,
                  final Organization autonomousSystemOrganization, final int autonomousSystemNumber, final boolean
                          isAnonymous, final boolean isAnonymousProxy, final boolean isAnonymousVpn, final boolean
                          isHostingProvider, final boolean isLegitimateProxy, final boolean isPublicProxy, final
                  boolean isSatelliteProvider, final boolean isTorExitNode){
        this.ipAddress = ipAddress;
        this.domain = domain;
        this.isp = isp;
        this.organization = organization;
        this.autonomousSystemOrganization = autonomousSystemOrganization;
        this.autonomousSystemNumber = autonomousSystemNumber;
        this.isAnonymous = isAnonymous;
        this.isAnonymousProxy = isAnonymousProxy;
        this.isAnonymousVpn = isAnonymousVpn;
        this.isHostingProvider = isHostingProvider;
        this.isLegitimateProxy = isLegitimateProxy;
        this.isPublicProxy = isPublicProxy;
        this.isSatelliteProvider = isSatelliteProvider;
        this.isTorExitNode = isTorExitNode;
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

    public Organization getOrganization(){
        return organization;
    }

    public Organization getAutonomousSystemOrganization(){
        return autonomousSystemOrganization;
    }

    public int getAutonomousSystemNumber(){
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

    public boolean isSatelliteProvider(){
        return isSatelliteProvider;
    }

    public boolean isTorExitNode(){
        return isTorExitNode;
    }

    @Override
    public String toString(){
        return "Traits{" + "ipAddress='" + ipAddress + '\'' + ", domain='" + domain + '\'' + ", isp='" + isp + '\'' +
                ", organization=" + organization + ", autonomousSystemOrganization=" + autonomousSystemOrganization +
                ", autonomousSystemNumber=" + autonomousSystemNumber + ", " + "isAnonymous=" + isAnonymous + ", " +
                "isAnonymousProxy=" + isAnonymousProxy + ", isAnonymousVpn=" + isAnonymousVpn + ", " +
                "isHostingProvider=" + isHostingProvider + ", isLegitimateProxy=" + isLegitimateProxy + ", " +
                "isPublicProxy=" + isPublicProxy + ", isSatelliteProvider=" + isSatelliteProvider + ", " +
                "isTorExitNode=" + isTorExitNode + '}';
    }

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.core.utils.Assert;

/**
 * @author Yong.Teng
 */
public class ProtocolVersion {

    protected String protocol;

    protected int major;

    protected int minor;

    public ProtocolVersion(){

    }

    public ProtocolVersion(String protocol, int major, int minor){
        this.protocol = protocol;
        this.major = major;
        this.minor = minor;
    }

    public String getProtocol(){
        return protocol;
    }

    public void setProtocol(String protocol){
        Assert.isBlank(protocol, "Protocol name cloud not be null or empty.");
        this.protocol = protocol;
    }

    public int getMajor(){
        return major;
    }

    public void setMajor(int major){
        Assert.isNegative(major, "Protocol major version cloud not be negative.");
        this.major = major;
    }

    public int getMinor(){
        return minor;
    }

    public void setMinor(int minor){
        Assert.isNegative(major, "Protocol minor version cloud not be negative.");
        this.minor = minor;
    }

    @Override
    public String toString(){
        final StringBuilder buffer = new StringBuilder();

        buffer.append(protocol);
        buffer.append('/');
        buffer.append(Integer.toString(major));
        buffer.append('.');
        buffer.append(Integer.toString(minor));

        return buffer.toString();
    }

}

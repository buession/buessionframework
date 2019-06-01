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
package com.buession.redis.core;

import com.buession.core.Geo;
import com.buession.redis.Constants;

import java.util.Arrays;

/**
 * @author Yong.Teng
 */
public class GeoRadius {

    private byte[] member;

    private double distance;

    private Geo geo;

    public byte[] getMember(){
        return member;
    }

    public void setMember(byte[] member){
        this.member = member;
    }

    public String getMemberAsString(){
        return new String(member, Constants.CHARSET);
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public Geo getGeo(){
        return geo;
    }

    public void setGeo(Geo geo){
        this.geo = geo;
    }

    @Override
    public String toString(){
        return "GeoRadius{" + "member=" + Arrays.toString(member) + ", distance=" + distance + ", geo=" + geo + '}';
    }
}

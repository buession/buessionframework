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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.lang.Geo;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class GeoRadius implements Serializable {

	private final static long serialVersionUID = 8391863034011700419L;

	private final byte[] member;

	private final double distance;

	private final Geo geo;

	public GeoRadius(final byte[] member, final double distance, final Geo geo){
		this.member = member;
		this.distance = distance;
		this.geo = geo;
	}

	public byte[] getMember(){
		return member;
	}

	public String getMemberAsString(){
		return new String(member, StandardCharsets.UTF_8);
	}

	public double getDistance(){
		return distance;
	}

	public Geo getGeo(){
		return geo;
	}

	@Override
	public int hashCode(){
		int result = Objects.hash(distance, geo);
		result = 31 * result + Arrays.hashCode(member);
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof GeoRadius){
			GeoRadius geoRadius = (GeoRadius) obj;
			return Double.compare(geoRadius.distance, distance) == 0 && Arrays.equals(member, geoRadius.member) &&
					Objects.equals(geo, geoRadius.geo);
		}

		return false;
	}

	@Override
	public String toString(){
		return "member=" + Arrays.toString(member) + ", distance=" + distance + ", geo=" + geo;
	}

}

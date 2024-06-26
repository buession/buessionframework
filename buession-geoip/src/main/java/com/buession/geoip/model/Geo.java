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
 * 包含与IP地址关联的经纬度记录的数据。
 *
 * @author Yong.Teng
 */
public final class Geo implements Serializable {

	private final static long serialVersionUID = 3166412069316195061L;

	/**
	 * 维度
	 */
	private final Double latitude;

	/**
	 * 经度
	 */
	private final Double longitude;

	/**
	 * 精确度
	 */
	private final Integer accuracyRadius;

	/**
	 * 构造函数
	 *
	 * @param latitude
	 * 		维度
	 * @param longitude
	 * 		经度
	 * @param accuracyRadius
	 * 		精确度
	 */
	public Geo(final Double latitude, final Double longitude, final Integer accuracyRadius){
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracyRadius = accuracyRadius;
	}

	/**
	 * 返回维度
	 *
	 * @return 维度
	 */
	public Double getLatitude(){
		return latitude;
	}

	/**
	 * 返回经度
	 *
	 * @return 经度
	 */
	public Double getLongitude(){
		return longitude;
	}

	/**
	 * 返回精确度
	 *
	 * @return 精确度
	 */
	public Integer getAccuracyRadius(){
		return accuracyRadius;
	}

	@Override
	public int hashCode(){
		return Objects.hash(latitude, longitude, accuracyRadius);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Geo){
			Geo that = (Geo) obj;
			return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
		}

		return false;
	}

	@Override
	public String toString(){
		return "Geo{" + "latitude=" + latitude + ", longitude=" + longitude + ", accuracyRadius=" + accuracyRadius +
				'}';
	}

}

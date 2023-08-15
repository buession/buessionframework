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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.lang;

import java.io.Serializable;
import java.util.Objects;

/**
 * 地理位置
 *
 * @author Yong.Teng
 */
public class Geo implements Serializable {

	private final static long serialVersionUID = 957160301954808183L;

	/**
	 * 经度
	 */
	private double longitude;

	/**
	 * 纬度
	 */
	private double latitude;

	/**
	 * 构造函数
	 */
	public Geo(){

	}

	/**
	 * 构造函数
	 *
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 */
	public Geo(double longitude, double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * 返回经度
	 *
	 * @return 经度
	 */
	public double getLongitude(){
		return longitude;
	}

	/**
	 * 设置经度
	 *
	 * @param longitude
	 * 		经度
	 */
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	/**
	 * 返回纬度
	 *
	 * @return 纬度
	 */
	public double getLatitude(){
		return latitude;
	}

	/**
	 * 设置纬度
	 *
	 * @param latitude
	 * 		纬度
	 */
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	@Override
	public int hashCode(){
		return Objects.hash(longitude, latitude);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Geo){
			Geo that = (Geo) obj;
			return that.getLongitude() == longitude && that.getLatitude() == latitude;
		}

		return false;
	}

	@Override
	public String toString(){
		return longitude + ", " + latitude;
	}

}

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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.lang;

import java.io.Serializable;
import java.time.Duration;

/**
 * 重试配置
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class Retry implements Serializable {

	private final static long serialVersionUID = -4074820402227945905L;

	/**
	 * 最大重数次数
	 */
	private int maxAttempts = 3;

	/**
	 * 第一次和第二次尝试重试时间间隔，默认：1 秒
	 */
	private Duration initialInterval = Duration.ofMillis(1000);

	/**
	 * 应用于上一重试间隔的乘数
	 */
	private double multiplier = 1.0;

	/**
	 * 最大重试时间间隔，默认：10 秒
	 */
	private Duration maxInterval = Duration.ofMillis(10000);

	/**
	 * 返回最大重数次数
	 *
	 * @return 最大重数次数
	 */
	public int getMaxAttempts(){
		return this.maxAttempts;
	}

	/**
	 * 设置最大重数次数
	 *
	 * @param maxAttempts
	 * 		最大重数次数
	 */
	public void setMaxAttempts(int maxAttempts){
		this.maxAttempts = maxAttempts;
	}

	/**
	 * 返回第一次和第二次尝试重试时间间隔
	 *
	 * @return 第一次和第二次尝试重试时间间隔
	 */
	public Duration getInitialInterval(){
		return this.initialInterval;
	}

	/**
	 * 设置第一次和第二次尝试重试时间间隔
	 *
	 * @param initialInterval
	 * 		第一次和第二次尝试重试时间间隔
	 */
	public void setInitialInterval(Duration initialInterval){
		this.initialInterval = initialInterval;
	}

	/**
	 * 返回应用于上一重试间隔的乘数
	 *
	 * @return 应用于上一重试间隔的乘数
	 */
	public double getMultiplier(){
		return this.multiplier;
	}

	/**
	 * 设置应用于上一重试间隔的乘数
	 *
	 * @param multiplier
	 * 		应用于上一重试间隔的乘数
	 */
	public void setMultiplier(double multiplier){
		this.multiplier = multiplier;
	}

	/**
	 * 返回最大重试时间间隔
	 *
	 * @return 最大重试时间间隔
	 */
	public Duration getMaxInterval(){
		return this.maxInterval;
	}

	/**
	 * 设置最大重试时间间隔
	 *
	 * @param maxInterval
	 * 		最大重试时间间隔
	 */
	public void setMaxInterval(Duration maxInterval){
		this.maxInterval = maxInterval;
	}

}

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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.jdbc.core;

/**
 * JMX 管理对象配置
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class Jmx {

	/**
	 * 是否启用 JMX
	 */
	private Boolean enabled;

	/**
	 * JMX 管理对象的名称
	 */
	private String name;

	/**
	 * 构造函数
	 */
	public Jmx() {
	}

	/**
	 * 构造函数
	 *
	 * @param enabled
	 * 		是否启用 JMX
	 */
	public Jmx(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		JMX 管理对象的名称
	 */
	public Jmx(String name) {
		this.name = name;
	}

	/**
	 * 构造函数
	 *
	 * @param enabled
	 * 		是否启用 JMX
	 * @param name
	 * 		JMX 管理对象的名称
	 */
	public Jmx(Boolean enabled, String name) {
		this.enabled = enabled;
		this.name = name;
	}

	/**
	 * 返回是否启用 JMX
	 *
	 * @return 是否启用 JMX
	 */
	public Boolean isEnabled() {
		return getEnabled();
	}

	/**
	 * 返回是否启用 JMX
	 *
	 * @return 是否启用 JMX
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * 设置是否启用 JMX
	 *
	 * @param enabled
	 * 		是否启用 JMX
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 返回 JMX 管理对象的名称
	 *
	 * @return JMX 管理对象的名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 JMX 管理对象的名称
	 *
	 * @param name
	 * 		JMX 管理对象的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}

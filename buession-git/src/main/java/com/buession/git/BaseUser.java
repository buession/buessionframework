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
package com.buession.git;

/**
 * 用户实体类
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
abstract class BaseUser implements Info {

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 用户 E-mail
	 */
	private String email;

	/**
	 * 构造函数
	 */
	public BaseUser(){
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		用户名称
	 * @param email
	 * 		用户 E-mail
	 */
	public BaseUser(String name, String email){
		this.name = name;
		this.email = email;
	}

	/**
	 * 返回用户名称
	 *
	 * @return 用户名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置用户名称
	 *
	 * @param name
	 * 		用户名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 返回用户 E-mail
	 *
	 * @return 用户 E-mail
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * 设置用户 E-mail
	 *
	 * @param email
	 * 		用户 E-mail
	 */
	public void setEmail(String email){
		this.email = email;
	}

}

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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.beans;

import com.buession.beans.annotations.DateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 */
public class User {

	private int id;

	private String username;

	private Integer age;

	private int weight;

	private Integer height;

	private boolean enable;

	private boolean disable;

	private Date lastLoginTime;

	private Status status;

	private Map<String, Object> map;

	private String[] arr;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public Integer getAge(){
		return age;
	}

	public void setAge(Integer age){
		this.age = age;
	}

	public int getWeight(){
		return weight;
	}

	public void setWeight(Integer weight){
		this.weight = weight;
	}

	public int getHeight(){
		return height;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public boolean isEnable(){
		return enable;
	}

	public void setEnable(boolean enable){
		this.enable = enable;
	}

	public boolean getDisable(){
		return disable;
	}

	public void setDisable(boolean disable){
		this.disable = disable;
	}

	public Date getLastLoginTime(){
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public Status getStatus(){
		return status;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Map<String, Object> getMap(){
		return map;
	}

	public void setMap(Map<String, Object> map){
		this.map = map;
	}

	public String[] getArr(){
		return arr;
	}

	public void setArr(String[] arr){
		this.arr = arr;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]").add("id=" + id).add("username='" + username + "'").add("age=" + age).add("weight=" + weight).add("height=" + height).add("enable=" + enable).add("disable=" + disable).add("lastLoginTime=" + lastLoginTime).add("status=" + status).add("map=" + map).add("arr=" + Arrays.toString(arr)).toString();
	}

	public enum Status {

		ON,

		OFF

	}

}

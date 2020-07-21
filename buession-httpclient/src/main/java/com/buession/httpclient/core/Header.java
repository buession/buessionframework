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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class Header {

	private String name;

	private String value;

	public Header(){
	}

	public Header(String name, String value){
		this.name = name;
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public void setName(final String name){
		this.name = name;
	}

	public String getValue(){
		return value;
	}

	public void setValue(final String value){
		this.value = value;
	}

	@Override
	public int hashCode(){
		return Objects.hash(name, value);
	}

	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}

		if(o instanceof Header){
			Header that = (Header) o;
			return Objects.equals(name, that.name) && Objects.equals(value, that.value);
		}

		return false;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder((name == null ? 0 : name.length()) + (value == null ? 0 :
				value.length()) + 2);
		sb.append(name).append(": ").append(value);
		return sb.toString();
	}

}

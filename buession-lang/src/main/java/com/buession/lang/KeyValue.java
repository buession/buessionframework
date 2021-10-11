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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.lang;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class KeyValue<K, V> implements Serializable {

	private final static long serialVersionUID = 7107772931418041867L;

	private K key;

	private V value;

	public KeyValue(){
	}

	public KeyValue(K key, V value){
		setKey(key);
		this.value = value;
	}

	public K getKey(){
		return key;
	}

	public void setKey(K key){
		if(key == null){
			throw new IllegalArgumentException("Key cloud not be null.");
		}
		this.key = key;
	}

	public V getValue(){
		return value;
	}

	public void setValue(V value){
		this.value = value;
	}

	@Override
	public int hashCode(){
		return Objects.hash(getKey(), getValue());
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof KeyValue){
			KeyValue that = (KeyValue) obj;
			return Objects.equals(key, that.key) && Objects.deepEquals(value, that.value);
		}

		return false;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("{key: ").append(key).append(", value: ").append(value).append('}');

		return sb.toString();
	}

}

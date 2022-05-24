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
package com.buession.lang;

import java.io.Serializable;
import java.util.Objects;

/**
 * Key =&lt; Value 键值对对象
 *
 * @param <K>
 * 		键类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 */
public class KeyValue<K, V> implements Serializable {

	private final static long serialVersionUID = 7107772931418041867L;

	/**
	 * 键名
	 */
	private K key;

	/**
	 * 值
	 */
	private V value;

	/**
	 * 构造函数
	 */
	public KeyValue(){
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		键名
	 * @param value
	 * 		值
	 */
	public KeyValue(K key, V value){
		setKey(key);
		this.value = value;
	}

	/**
	 * 返回键名
	 *
	 * @return 键名
	 */
	public K getKey(){
		return key;
	}

	/**
	 * 设置键名
	 *
	 * @param key
	 * 		键名
	 */
	public void setKey(K key){
		if(key == null){
			throw new IllegalArgumentException("Key cloud not be null.");
		}
		this.key = key;
	}

	/**
	 * 返回值
	 *
	 * @return 值
	 */
	public V getValue(){
		return value;
	}

	/**
	 * 设置值
	 *
	 * @param value
	 * 		值
	 */
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

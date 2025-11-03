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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.lang;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 名称 =&lt; Value 键值对对象
 *
 * @param <K>
 * 		名称类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class NameValue<K, V> implements Serializable {

	private final static long serialVersionUID = -8641226699277448976L;

	/**
	 * 名称
	 */
	private K name;

	/**
	 * 值
	 */
	private V value;

	/**
	 * 构造函数
	 */
	public NameValue() {
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		名称
	 * @param value
	 * 		值
	 */
	public NameValue(K name, V value) {
		setName(name);
		this.value = value;
	}

	/**
	 * 返回名称
	 *
	 * @return 名称
	 */
	public K getName() {
		return name;
	}

	/**
	 * 设置名称
	 *
	 * @param name
	 * 		名称
	 */
	public void setName(K name) {
		if(name == null){
			throw new IllegalArgumentException("Name cloud not be null.");
		}
		this.name = name;
	}

	/**
	 * 返回值
	 *
	 * @return 值
	 */
	public V getValue() {
		return value;
	}

	/**
	 * 设置值
	 *
	 * @param value
	 * 		值
	 */
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof NameValue){
			NameValue that = (NameValue) obj;
			return Objects.equals(name, that.name) && Objects.deepEquals(value, that.value);
		}

		return false;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add("name: " + name)
				.add("value: " + value)
				.toString();
	}

}

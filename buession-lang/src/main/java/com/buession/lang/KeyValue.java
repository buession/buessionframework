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
package com.buession.lang;

/**
 * @author Yong.Teng
 */
public class KeyValue<K, V> {

	private K key;

	private V value;

	public KeyValue(){
	}

	public KeyValue(K key, V value){
		this.key = key;
		this.value = value;
	}

	public K getKey(){
		return key;
	}

	public void setKey(K key){
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
		return 32 * key.hashCode() + 32 * value.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}

		if(!(obj instanceof KeyValue)){
			return false;
		}

		KeyValue that = (KeyValue) obj;

		if(this.key == null && that.getKey() != null){
			if(that.getKey().equals(this.key) == false){
				return false;
			}
		}else{
			if(this.key.equals(that.getKey()) == false){
				return false;
			}
		}

		if(this.value == null && that.getValue() != null){
			if(that.getValue().equals(this.value) == false){
				return false;
			}
		}else{
			if(this.value.equals(that.getValue()) == false){
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString(){
		return "KeyValue{" + "key=" + key + ", value=" + value + '}';
	}

}

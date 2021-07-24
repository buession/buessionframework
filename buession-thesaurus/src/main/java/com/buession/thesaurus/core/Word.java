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
package com.buession.thesaurus.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class Word implements Serializable {

	private final static long serialVersionUID = -515136897983248694L;

	/**
	 * 词条拼音首字母
	 */
	private char initial;

	/**
	 * 词条拼音首字母集合
	 */
	private char[] initials;

	/**
	 * 词条拼音
	 */
	private String pinyin;

	/**
	 * 词条
	 */
	private String value;

	/**
	 * 返回词条拼音首字母
	 *
	 * @return 词条拼音首字母
	 */
	public char getInitial(){
		return initial;
	}

	/**
	 * 设置词条拼音首字母
	 *
	 * @param initial
	 * 		词条拼音首字母
	 */
	public void setInitial(char initial){
		this.initial = initial;
	}

	/**
	 * 返回词条拼音首字母集合
	 *
	 * @return 词条拼音首字母集合
	 */
	public char[] getInitials(){
		return initials;
	}

	/**
	 * 设置词条拼音首字母
	 *
	 * @param initials
	 * 		词条拼音首字母
	 */
	public void setInitials(char[] initials){
		this.initials = initials;
	}

	/**
	 * 返回词条拼音
	 *
	 * @return 词条拼音
	 */
	public String getPinyin(){
		return pinyin;
	}

	/**
	 * 设置词条拼音
	 *
	 * @param pinyin
	 * 		词条拼音
	 */
	public void setPinyin(String pinyin){
		this.pinyin = pinyin;
	}

	/**
	 * 返回词条
	 *
	 * @return 词条
	 */
	public String getValue(){
		return value;
	}

	/**
	 * 设置词条
	 *
	 * @param value
	 * 		词条
	 */
	public void setValue(String value){
		this.value = value;
	}

	@Override
	public int hashCode(){
		int result = Objects.hash(initial, pinyin, value);
		result = 31 * result + Arrays.hashCode(initials);
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Word){
			Word word = (Word) obj;
			return Objects.equals(value, word.value);
		}

		return false;
	}

	@Override
	public String toString(){
		return value;
	}

}

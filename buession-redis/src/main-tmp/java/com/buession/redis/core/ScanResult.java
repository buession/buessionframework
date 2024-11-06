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
package com.buession.redis.core;

import com.buession.redis.utils.SafeEncoder;

import java.util.Arrays;

/**
 * 迭代结果
 *
 * @param <V>
 * 		结果数据类型
 *
 * @author Yong.Teng
 */
public class ScanResult<V> {

	/**
	 * 游标
	 */
	private final byte[] cursor;

	/**
	 * 游标的字符串形式
	 */
	private String cursorAsString;

	/**
	 * 结果集
	 */
	private final V results;

	/**
	 * 构造函数
	 *
	 * @param cursor
	 * 		游标
	 * @param results
	 * 		结果集
	 */
	public ScanResult(final byte[] cursor, final V results){
		this.cursor = cursor;
		this.results = results;
	}

	/**
	 * 构造函数
	 *
	 * @param cursor
	 * 		游标
	 * @param results
	 * 		结果集
	 */
	public ScanResult(final String cursor, final V results){
		this(SafeEncoder.encode(cursor), results);
		this.cursorAsString = cursor;
	}

	/**
	 * 返回游标
	 *
	 * @return 游标
	 */
	public byte[] getCursor(){
		return cursor;
	}

	/**
	 * 返回游标的字符串形式
	 *
	 * @return 游标字符串形式
	 */
	public String getCursorAsString(){
		if(cursorAsString == null){
			cursorAsString = SafeEncoder.encode(cursor);
		}

		return cursorAsString;
	}

	/**
	 * 返回结果集
	 *
	 * @return 结果集
	 */
	public V getResults(){
		return results;
	}

	/**
	 * 是否完成迭代
	 *
	 * @return 完成迭代返回 true；否则，返回 false
	 */
	public boolean isCompleteIteration(){
		return Arrays.equals(Constants.SCAN_POINTER_START_BINARY, getCursor());
	}

}

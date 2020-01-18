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
package com.buession.core.math;

import java.util.stream.LongStream;

/**
 * @author Yong.Teng
 */
public class Math {

	/**
	 * 计算两个数之间连续相加
	 *
	 * @param start
	 * 		起始数
	 * @param end
	 * 		结束数
	 *
	 * @return 两个数之间连续相加结果
	 */
	public final static long continuousAddition(final short start, final short end){
		return LongStream.rangeClosed(start, end).sum();
	}

	/**
	 * 计算两个数之间连续相加
	 *
	 * @param start
	 * 		起始数
	 * @param end
	 * 		结束数
	 *
	 * @return 两个数之间连续相加结果
	 */
	public final static long continuousAddition(final int start, final int end){
		return LongStream.rangeClosed(start, end).sum();
	}

	/**
	 * 计算两个数之间连续相加
	 *
	 * @param start
	 * 		起始数
	 * @param end
	 * 		结束数
	 *
	 * @return 两个数之间连续相加结果
	 */
	public final static long continuousAddition(final long start, final long end){
		return LongStream.rangeClosed(start, end).sum();
	}

}

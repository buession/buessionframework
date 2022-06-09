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
package com.buession.core.id;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;

/**
 * 随机 ID 生成器
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public class RandomIdGenerator implements IdGenerator<String> {

	/**
	 * 默认随机长度
	 */
	public final static int DEFAULT_LENGTH = 16;

	/**
	 * 随机长度
	 */
	private int length = DEFAULT_LENGTH;

	/**
	 * 构造函数，生成 16 位随机 ID
	 */
	public RandomIdGenerator(){
	}

	/**
	 * 构造函数
	 *
	 * @param length
	 * 		ID 长度
	 */
	public RandomIdGenerator(final int length){
		Assert.isNegative(length, "Id length can't be less than 0");
		this.length = length;
	}

	@Override
	public String nextId(){
		return StringUtils.random(length);
	}

}

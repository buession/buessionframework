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
package com.buession.core.id;

import com.buession.core.utils.Assert;
import com.buession.core.utils.RandomUtils;

/**
 * 随机数 ID 生成器
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public class RandomDigitIdGenerator implements IdGenerator<Long> {

	/**
	 * 最小值
	 */
	private long min = Long.MIN_VALUE;

	/**
	 * 最大值
	 */
	private long max = Long.MAX_VALUE;

	/**
	 * 构造函数，生成 1 到 Long.MAX_VALUE 间的随机 ID
	 */
	public RandomDigitIdGenerator() {
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 */
	public RandomDigitIdGenerator(final long min, final long max) {
		Assert.isTrue(min > max, "Id max value has to be greater than or equal to id min value.");
		this.min = min;
		this.max = max;
	}

	@Override
	public Long nextId() {
		return RandomUtils.nextLong(min, max);
	}

}

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
package com.buession.core;

import org.junit.Test;

/**
 * @author Yong.Teng
 */
public class MathTest {

	@Test
	public void continuousSum(){
		long startTimestamp = System.currentTimeMillis();
		long result = com.buession.core.math.Math.continuousSum(3, 9);

		System.out.println("结果：" + result + ", 耗时：" + (System.currentTimeMillis() - startTimestamp));

		startTimestamp = System.currentTimeMillis();
		result = com.buession.core.math.Math.continuousSum(10, 1000000);

		System.out.println("结果：" + result + ", 耗时：" + (System.currentTimeMillis() - startTimestamp));

		result = 0;
		startTimestamp = System.currentTimeMillis();
		for(int i = 12; i <= 1000002; i++){
			result += i;
		}
		System.out.println("结果：" + result + ", 耗时：" + (System.currentTimeMillis() - startTimestamp));
	}

	@Test
	public void yh(){
		int i = 10;
		System.out.println(Integer.toBinaryString(i));
	}

	@Test
	public void or(){
		int i = 10;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(i & 1);
	}

	@Test
	public void wy(){
		System.out.println(4 >> 1);
		System.out.println(5 >> 1);
		System.out.println(6 >> 1);
		System.out.println(7 >> 1);
		System.out.println(2 * 2);
		System.out.println(2 << 1);
		System.out.println(2 * 8);
		System.out.println(2 << 3);
	}

	@Test
	public void convert(){
		short i = (short) 1;

		long startTimestamp = System.currentTimeMillis();
		for(int j = 0; j < 10000; j++){
			long l = (long) i;
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - startTimestamp));

		startTimestamp = System.currentTimeMillis();
		for(int j = 0; j < 10000; j++){
			long l = i * 1L;
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - startTimestamp));

		char c = '1';
		System.out.println("c: " + (c - '0'));
		System.out.println("c: " + ((int) c));
	}

	@Test
	public void divide(){
		Object ret = 5 / 2;
		System.out.println(ret.getClass());
	}

}

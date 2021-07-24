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
package com.buession.core.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yong.Teng
 * @since 1.3.0
 */
public class ArrayUtilsTest {

	private final static byte[] byteArr = new byte[]{1, 2, 3};

	private final static char[] charArr = new char[]{'a', 'b', 'c'};

	private final static short[] shortArr = new short[]{1, 2, 3};

	private final static int[] intArr = new int[]{1, 2, 3};

	private final static float[] floatArr = new float[]{1, 2, 3, Float.MAX_VALUE};

	@Test
	public void containsTest(){
		System.out.println(ArrayUtils.contains(intArr, 1));
		System.out.println(ArrayUtils.contains(shortArr, 1));
	}

	@Test
	public void indexOfTest(){
		Assert.assertEquals(0, ArrayUtils.indexOf(byteArr, 1));
		Assert.assertEquals(0, ArrayUtils.indexOf(byteArr, (short) 1));
		Assert.assertEquals(-1, ArrayUtils.indexOf(byteArr, 4));

		Assert.assertEquals(2, ArrayUtils.indexOf(charArr, 'c'));
		Assert.assertEquals(-1, ArrayUtils.indexOf(charArr, 'l'));

		Assert.assertEquals(2, ArrayUtils.indexOf(shortArr, 3));
		Assert.assertEquals(-1, ArrayUtils.indexOf(shortArr, 4));

		Assert.assertEquals(1, ArrayUtils.indexOf(intArr, 2));

		Assert.assertEquals(1, ArrayUtils.indexOf(floatArr, 2));
		Assert.assertEquals(3, ArrayUtils.indexOf(floatArr, Float.MAX_VALUE));
		Assert.assertEquals(-1, ArrayUtils.indexOf(floatArr, Double.MAX_VALUE));
	}

	@Test
	public void toStringTest(){
		Assert.assertEquals("1, 2", ArrayUtils.toString(new byte[]{1, 2}));
		Assert.assertEquals("a, b", ArrayUtils.toString(new char[]{'a', 'b'}));
		Assert.assertEquals("1, 2", ArrayUtils.toString(new short[]{1, 2}));
		Assert.assertEquals("1, 2", ArrayUtils.toString(new int[]{1, 2}));
		Assert.assertEquals("1, 2", ArrayUtils.toString(new long[]{1L, 2L}));
		Assert.assertEquals("true, false", ArrayUtils.toString(new boolean[]{true, false}));
		Assert.assertEquals("A, B", ArrayUtils.toString(new String[]{"A", "B"}));
		Assert.assertEquals("1, 2", ArrayUtils.toString(new Integer[]{1, 2}));
	}

}

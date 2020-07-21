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
package com.buession.core.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ArrayUtilsTest {

	@Test
	public void arrayToString(){
		short[] sa = new short[]{1, 2, 3};
		System.out.println(ArrayUtils.toString(sa));

		char[] ca = new char[]{'a', 'b', 'c'};
		System.out.println(ArrayUtils.toString(ca));

		byte[] ba = new byte[]{'a', 'b', 'c'};
		System.out.println(ArrayUtils.toString(ba));

		boolean[] boola = new boolean[]{true, false};
		System.out.println(ArrayUtils.toString(boola));
	}

	@Test
	public void listToString(){
		List<Short> shortList = new ArrayList<>();
		shortList.add((short) 1);
		shortList.add((short) 2);
		System.out.println(ArrayUtils.toString(shortList));
	}

	@Test
	public void toList(){
		int[] sa = new int[]{1, 2, 3};
		List<Integer> set = ArrayUtils.toList(sa);

		for(Integer v : set){
			System.out.println(v);
		}
	}

	@Test
	public void toSet(){
		String[] strings = new String[]{"A", "B", "C"};
		Set<String> set = ArrayUtils.toSet(strings);

		for(String str : set){
			System.out.println(str);
		}
	}

	@Test
	public void indexOf(){
		String[] strings = new String[]{"A", "B", "B", "C"};

		System.out.println(ArrayUtils.indexOf(strings, "B"));
		System.out.println(ArrayUtils.lastIndexOf(strings, "B"));
	}

}

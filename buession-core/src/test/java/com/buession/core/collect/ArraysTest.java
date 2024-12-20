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
package com.buession.core.collect;

import com.buession.core.converter.ArrayConverter;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ArraysTest {

	@Test
	public void convert() {
		String[] data = new String[]{"1", "2"};
		ArrayConverter<String, Integer> converter = new ArrayConverter<>(Integer::parseInt,
				Integer.class);
		Integer[] result = converter.convert(data);

		for(Integer s : result){
			System.out.println(s);
		}
	}

	@Test
	public void repeatStr() {
		String[] data = Arrays.repeat("A", 3);

		for(String s : data){
			System.out.println(s);
		}
	}

	@Test
	public void merge() {
		int[] a = new int[]{1};
		int[] b = new int[]{2};

		for(int s : Arrays.merge(a, b)){
			System.out.println(s);
		}
	}

	@Test
	public void repeatInt() {
		int[] data = Arrays.repeat(1, 3);

		for(int s : data){
			System.out.println(s);
		}
	}

	@Test
	public void map() {
		Long[] data = new Long[]{1L, 2L};

		Integer[] result = Arrays.map(data, Integer.class, (v)->v.intValue());
	}

	@Test
	public void toSet() {
		Long[] data = new Long[]{1L, 2L};

		Set<Long> result = Arrays.toSet(data);

		for(Long v : result){
			System.out.println(v);
		}
	}

}

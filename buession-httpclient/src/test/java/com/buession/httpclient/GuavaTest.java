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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.core.utils.StringUtils;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GuavaTest {

	@Test
	public void testMultimap() {
		Multimap<String, String> map = HashMultimap.create();

		map.put("a", "A1");
		map.put("a", "A2");
	}

	@Test
	public void forEach() {
		Multimap<String, String> map = HashMultimap.create();

		map.put("a", "A1");
		map.put("a", "A2");
		map.put("b", "B1");

		Iterator<String> it = map.keySet().iterator();

		while(it.hasNext()){
			String name = it.next();
			System.out.println(name + ": " + StringUtils.join(map.get(name), "|"));
		}

		map.forEach((name, value)->{
			System.out.println(name + ": " + value);
		});
	}

}

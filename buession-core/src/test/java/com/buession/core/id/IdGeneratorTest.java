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

import org.junit.jupiter.api.Test;

/**
 * @author Yong.Teng
 * @since 1.3.1
 */
public class IdGeneratorTest {

	@Test
	public void snowflake() {
		SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(2L, 32L);

		for(int i = 1; i <= 10; i++){
			Long id = idGenerator.nextId();
			String sid = id.toString();
			System.out.println(id + ": " + sid + ": " + sid.length());
		}
	}

	@Test
	public void nanoId() {
		NanoIDIdGenerator idGenerator = new NanoIDIdGenerator(
				"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray());
		System.out.println(idGenerator.nextId());
	}

	@Test
	public void atomicUUID() {
		AtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();
		System.out.println(idGenerator.nextId());
		System.out.println(idGenerator.nextId());
	}

	@Test
	public void uuid() {
		UUIDIdGenerator idGenerator = new UUIDIdGenerator();
		System.out.println(idGenerator.nextId());
	}

	@Test
	public void simpleId() {
		SimpleIdGenerator idGenerator = new SimpleIdGenerator();
		System.out.println(idGenerator.nextId());
	}

}

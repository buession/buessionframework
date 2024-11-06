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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.serializer;

import com.buession.lang.Uptime;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class DefaultByteArraySerializerTest {

	private final static DefaultByteArraySerializer serializer = new DefaultByteArraySerializer();

	@Test
	public void serialize() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		String str = serializer.serialize(uptime);
		System.out.println(str);
	}

	@Test
	public void serializeWithCharsetName() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		String str = serializer.serialize(uptime, StandardCharsets.UTF_8.name());
		System.out.println(str);
	}

	@Test
	public void serializeWithCharset() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		String str = serializer.serialize(uptime, StandardCharsets.UTF_8);
		System.out.println(str);
	}

	@Test
	public void serializeAsBytes() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		byte[] str = serializer.serializeAsBytes(uptime);
		System.out.println(str);
	}

	@Test
	public void serializeAsBytesWithCharsetName() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		byte[] str = serializer.serializeAsBytes(uptime, StandardCharsets.UTF_8.name());
		System.out.println(str);
	}

	@Test
	public void serializeAsBytesWithCharset() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		byte[] str = serializer.serializeAsBytes(uptime, StandardCharsets.UTF_8);
		System.out.println(str);
	}

	@Test
	public void deserialize() throws SerializerException {
		Uptime uptime = new Uptime(5, 100);

		String str = serializer.serialize(uptime);
		System.out.println(str);
	}

}

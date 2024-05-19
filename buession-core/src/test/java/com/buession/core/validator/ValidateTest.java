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
package com.buession.core.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Yong.Teng
 * @since 1.3.0
 */
public class ValidateTest {

	@Test
	public void isBlank() {
		Assertions.assertEquals(true, Validate.isBlank(""));
		Assertions.assertEquals(true, Validate.isBlank(null));
		Assertions.assertEquals(true, Validate.isBlank("\r\n"));
		Assertions.assertEquals(true, Validate.isBlank(" "));
		Assertions.assertEquals(false, Validate.isBlank("\na"));
	}

	@Test
	public void isNotBlank() {
		Assertions.assertEquals(false, Validate.isNotBlank(""));
		Assertions.assertEquals(false, Validate.isNotBlank(null));
		Assertions.assertEquals(false, Validate.isNotBlank("\r\n"));
		Assertions.assertEquals(false, Validate.isNotBlank(" "));
		Assertions.assertEquals(true, Validate.isNotBlank("\na"));
	}

	@Test
	public void isEmail() {
		Assertions.assertFalse(Validate.isEmail(""));
		Assertions.assertTrue(Validate.isEmail("webmaster@buession.com"));
	}

	@Test
	public void isMimeType() {
		Assertions.assertEquals(true, Validate.isMimeType("application/rtf"));
		Assertions.assertEquals(true, Validate.isMimeType("application/vnd.wap.wmlc"));
		Assertions.assertEquals(true, Validate.isMimeType("application/x-rar-compressed"));
		Assertions.assertEquals(true,
				Validate.isMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
		Assertions.assertEquals(true, Validate.isMimeType("video/3gpp"));
		Assertions.assertEquals(false, Validate.isMimeType("video/-3gpp"));
		Assertions.assertEquals(false, Validate.isMimeType("video/3gpp-"));
		Assertions.assertEquals(false, Validate.isMimeType("application/x-rar--compressed"));
	}

}

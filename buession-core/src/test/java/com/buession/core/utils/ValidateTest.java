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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.core.validator.Validate;
import com.buession.core.validator.routines.DomainValidator;
import org.junit.Test;

/**
 * @author Yong.Teng
 */
public class ValidateTest {

	@Test
	public void tel(){
		System.out.println(Validate.isTel("86-757-91440600"));
		System.out.println(Validate.isTel("86757-91440600"));
		System.out.println(Validate.isTel("0757-91440600"));
		System.out.println(Validate.isTel("（0757）91440600"));
		System.out.println(Validate.isTel("（86757）91440600"));
		System.out.println(Validate.isTel("（86-757）91440600"));
	}

	@Test
	public void ip(){
		System.out.println(Validate.isIpV6("::13.1.68.3"));
		System.out.println(Validate.isIpV6(":::13.1.68.3"));
		System.out.println(Validate.isIpV6("::::13.1.68.3"));
		System.out.println(Validate.isIpV6("ffff:13.1.68.3"));
		System.out.println(Validate.isIpV6("2000:0000:0000:0000:0001:2345:6789:abcd"));
		System.out.println(Validate.isIpV6("2000:::1:2345:6789:abcd"));
		System.out.println(Validate.isIpV6("2000::1:2345:6789:abcd"));
		System.out.println(Validate.isIpV6("2000::1"));
		System.out.println(Validate.isIpV6("::c0a8:5909"));
		System.out.println(Validate.isIpV6("2001:DB8:2de::e13"));
		System.out.println(Validate.isIpV6("ffff::"));
		System.out.println(Validate.isIpV6("::8a2e:0:0370:7344"));
		System.out.println(Validate.isIpV6("::ffff:21:7.8.9.221"));
		System.out.println(Validate.isIpV6("2001:DB8::2de::e13"));

		long startTimestamp = System.currentTimeMillis();
		System.out.println(Validate.isIpV4("192.168.1.1"));
		System.out.println(Validate.isIpV4("0.0.0.0"));
		System.out.println(Validate.isIpV4("255.255.255.255"));
		System.out.println(Validate.isIpV4("255.155.255.255"));
		System.out.println(Validate.isIpV4("256.168.1.1"));
		long endTimestamp = System.currentTimeMillis();
		System.out.println(endTimestamp - startTimestamp);

	}

	@Test
	public void str(){
		String str = "abc";
		System.out.println(str.substring(0, 1));
		System.out.println(str.substring(1, 2));
	}

	@Test
	public void domain(){
		System.out.println(DomainValidator.isValid("www.domain.com"));
		System.out.println(DomainValidator.isValid("www.domain.fff"));
		System.out.println(DomainValidator.isValid("www.domain.com.cn"));
		System.out.println(DomainValidator.isValid("www.domain.com.ff"));
		System.out.println(DomainValidator.isValid("www.domain.hk"));
	}

}

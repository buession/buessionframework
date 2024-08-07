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
package com.buession.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class VersionTest {

	@Test
	public void create() {
		Version version = new Version("1.11.2");
		Assertions.assertEquals(version.toString(), "1.11.2");
	}

	@Test
	public void createBeta() {
		Version version = new Version("1.11.2beta");
		Assertions.assertEquals(version.toString(), "1.11.2beta");
	}

	@Test
	public void createRc() {
		Version version = new Version("1.11.2 RC");
		Assertions.assertEquals(version.toString(), "1.11.2 RC");
	}

	@Test
	public void createMajorVersionAndMinorVersion() {
		Version version = new Version("1.113");
		Assertions.assertEquals(version.toString(), "1.113");
	}

	@Test
	public void createMajorVersionAndMinorVersionRc() {
		Version version = new Version("1.113 Rc");
		Assertions.assertEquals(version.toString(), "1.113 Rc");
	}

	@Test
	public void compare() {
		Version version1 = new Version("1.11.2");
		Version version2 = new Version("1.11.2 Beta");
		Assertions.assertEquals(version1.compareTo(version2), 1);
	}

}

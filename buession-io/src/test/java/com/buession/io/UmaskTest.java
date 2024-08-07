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
package com.buession.io;

import org.junit.jupiter.api.Test;

/**
 * @author Yong.Teng
 */
public class UmaskTest {

	@Test
	public void mask() {
		int $perms = 0777;

		String $info = "";
		// Owner
		$info += (($perms & 0x0100) != 0 ? "r" : "-");
		$info += (($perms & 0x0080) != 0 ? "w" : "-");
		$info += (($perms & 0x0040) != 0 ? (($perms & 0x0800) != 0 ? "s" : "x") : (($perms & 0x0800) != 0 ? "s" : "-"));

		// Group
		$info += (($perms & 0x0020) != 0 ? "r" : "-");
		$info += (($perms & 0x0010) != 0 ? "w" : "-");
		$info += (($perms & 0x0008) != 0 ? (($perms & 0x0400) != 0 ? "s" : "x") : (($perms & 0x0400) != 0 ? "s" : "-"));

		// World
		$info += (($perms & 0x0004) != 0 ? "r" : "-");
		$info += (($perms & 0x0002) != 0 ? "w" : "-");
		$info += (($perms & 0x0001) != 0 ? (($perms & 0x0200) != 0 ? "t" : "x") : (($perms & 0x0200) != 0 ? "t" : "-"));
		System.out.println($perms + ": " + $info);
	}

}

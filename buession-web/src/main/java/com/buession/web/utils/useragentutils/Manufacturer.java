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
package com.buession.web.utils.useragentutils;

/**
 * 浏览器厂商
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public enum Manufacturer {

	MICROSOFT("Microsoft Corporation"),

	APPLE("Apple Inc."),

	SUN("Sun Microsystems, Inc."),

	SYMBIAN("Symbian Ltd."),

	NOKIA("Nokia Corporation"),

	BLACKBERRY("Research In Motion Limited"),

	HP("Hewlett Packard"),

	SONY_ERICSSON("Sony Ericsson Mobile Communications AB"),

	SAMSUNG("Samsung Electronics"),

	SONY("Sony Computer Entertainment, Inc."),

	NINTENDO("Nintendo"),

	OPERA("Opera Software ASA"),

	MOZILLA("Mozilla Foundation"),

	GOOGLE("Google Inc."),

	COMPUSERVE("CompuServe Interactive Services, Inc."),

	YAHOO("Yahoo Inc."),

	AOL("AOL LLC."),

	MMC("Mail.com Media Corporation"),

	AMAZON("Amazon.com, Inc."),

	ROKU("Roku, Inc."),

	ADOBE("Adobe Systems Inc."),

	CONONICAL("Canonical Ltd."),

	LINUX_FOUNDATION("Linux Foundation"),

	OTHER("Other");

	private final String name;

	Manufacturer(final String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

}

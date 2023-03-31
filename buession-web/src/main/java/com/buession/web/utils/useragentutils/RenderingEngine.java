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
 * 渲染引擎
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public enum RenderingEngine {
	/**
	 * EdgeHTML is a proprietary layout engine developed for the Microsoft Edge web browser, developed by Microsoft.
	 */
	EDGE_HTML("EdgeHTML"),

	/**
	 * Trident is the the Microsoft layout engine, mainly used by Internet Explorer.
	 */
	TRIDENT("Trident"),

	/**
	 * HTML parsing and rendering engine of Microsoft Office Word, used by some other products of the Office suite instead of Trident.
	 */
	WORD("Microsoft Office Word"),

	/**
	 * Open source and cross platform layout engine, used by Firefox and many other browsers.
	 */
	GECKO("Gecko"),

	/**
	 * Layout engine based on KHTML, used by Safari, Chrome and some other browsers.
	 */
	WEBKIT("WebKit"),

	/**
	 * Proprietary layout engine by Opera Software ASA
	 */
	PRESTO("Presto"),

	/**
	 * Original layout engine of the Mozilla browser and related products. Predecessor of Gecko.
	 */
	MOZILLA("Mozilla"),

	/**
	 * Layout engine of the KDE project
	 */
	KHTML("KHTML"),

	/**
	 * Other or unknown layout engine.
	 */
	BLINK("Blink"),

	/**
	 * Layout engine developed as part ofthe Chromium project. Fored from WebKit.
	 */
	OTHER("Other");

	private final String name;

	RenderingEngine(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString(){
		return name;
	}

}

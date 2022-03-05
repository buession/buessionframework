/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.core;

/**
 * 图片缩放模式
 *
 * @author Yong.Teng
 */
public enum ImageResizeMode {

	// 等比缩放，限制在指定w与h的矩形内的最大图片
	LFIT("lfit"),

	// 等比缩放，延伸出指定w与h的矩形框外的最小图片
	MFIT("mfit"),

	// 固定宽高，将延伸出指定w与h的矩形框外的最小图片进行居中裁剪
	FILL("fill"),

	// 固定宽高，缩放填充
	PAD("pad"),

	// 固定宽高，强制缩放
	FIXED("fixed");

	private final String value;

	ImageResizeMode(final String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
	public String toString(){
		return value;
	}

}

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
package com.buession.io;

import java.nio.file.Path;

/**
 * 文件未找到异常
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class FileNotFoundException extends java.io.FileNotFoundException {

	private final static long serialVersionUID = -7769153590931227681L;

	/**
	 * 构造函数
	 */
	public FileNotFoundException(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 */
	public FileNotFoundException(String message){
		super(message);
	}

	/**
	 * 构造函数
	 *
	 * @param path
	 * 		文件路径
	 */
	public FileNotFoundException(Path path){
		this(path, "not found");
	}

	/**
	 * 构造函数
	 *
	 * @param path
	 * 		文件路径
	 * @param message
	 * 		异常信息
	 */
	public FileNotFoundException(Path path, String message){
		super(String.format("%s %s", path, message));
	}

}

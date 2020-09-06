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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import java.nio.charset.Charset;

/**
 * 对象工具类 More {@link org.apache.commons.lang3.ObjectUtils}
 *
 * @author Yong.Teng
 * @see 1.3.0
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 将任意对象转换成 byte[]
	 *
	 * @param o
	 * 		待转换对象
	 *
	 * @return 转换结果
	 */
	public final static byte[] toByte(Object o){
		if(o == null){
			return null;
		}

		if(o instanceof char[]){
			return new String((char[]) o).getBytes();
		}else if(o instanceof byte[]){
			return (byte[]) o;
		}else{
			return o.toString().getBytes();
		}
	}

	/**
	 * 将任意对象转换成 byte[]
	 *
	 * @param o
	 * 		待转换对象
	 * @param charset
	 * 		字符集
	 *
	 * @return 转换结果
	 */
	public final static byte[] toByte(Object o, final Charset charset){
		if(o == null){
			return null;
		}

		if(o instanceof char[]){
			return new String((char[]) o).getBytes(charset);
		}else if(o instanceof byte[]){
			return (byte[]) o;
		}else{
			return o.toString().getBytes(charset);
		}
	}

}

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
package com.buession.common.utils;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱后缀和首页
 *
 * @author Yong.Teng
 */
public class EmailUtils {

	private final static Map<String, String> data = new HashMap<>();

	static{
		data.put("163.com", "http://mail.163.com/");
		data.put("vip.163.com", "http://vip.163.com/");
		data.put("126.com", "http://www.126.com/");
		data.put("vip.126.com", "http://vip.126.com/");
		data.put("yeah.net", "http://www.yeah.net/");

		data.put("sina.com.cn", "https://mail.sina.com.cn/");
		data.put("vip.sina.com", "https://vip.sina.com.cn/");
		data.put("sina.com", "https://mail.sina.com.cn/");
		data.put("sina.cn", "https://mail.sina.com.cn/");

		data.put("sohu.com", "https://mail.sohu.com/");
		data.put("vip.sohu.com", "https://vip.sohu.com/");

		data.put("qq.com", "https://mail.qq.com/");

		data.put("gmail.com", "http://www.gmail.com/");

		data.put("yahoo.com", "http://mail.yahoo.com");

		data.put("263.net", "https://www.263.net/");

		data.put("tom.com", "http://mail.tom.com/");
		data.put("vip.tom.cn", "http://www.163.net/webmail/login/index.action");
		data.put("163.net", "http://www.163.net/webmail/login/index.action");
	}

	/**
	 * 添加邮箱主页地址
	 *
	 * @param suffix
	 * 		邮箱后缀
	 * @param homepage
	 * 		主页地址
	 */
	public final static void addEmailHomePage(final String suffix, final String homepage){
		if(Validate.hasText(suffix) && Validate.hasText(homepage)){
			data.put(suffix, homepage);
		}
	}

	/**
	 * 批量添加邮箱主页地址
	 *
	 * @param homepages
	 * 		邮箱后缀和主页地址，Key 为邮箱后缀，Value 为邮箱主页地址
	 */
	public final static void addEmailHomePages(final Map<String, String> homepages){
		if(homepages != null){
			homepages.forEach((key, value)->{
				addEmailHomePage(key, value);
			});
		}
	}

	/**
	 * 根据邮箱后缀获取邮箱主页地址
	 *
	 * @param suffix
	 * 		邮箱后缀
	 *
	 * @return 邮箱主页地址
	 */
	public final static String getEmeilHomePage(final String suffix){
		return data.get(suffix);
	}

	/**
	 * 根据 E-mail 地址解析邮箱主页地址
	 *
	 * @param email
	 * 		E-mail
	 *
	 * @return 邮箱主页地址
	 */
	public final static String parseEmailHomePage(final String email){
		if(Validate.isEmail(email) == false){
			return null;
		}

		String[] groups = StringUtils.split(email, '@');
		return groups.length == 2 ? getEmeilHomePage(groups[1]) : null;
	}

}

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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.routines;

import com.buession.core.utils.EnumUtils;
import com.buession.lang.DomainTLD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 域名验证器
 *
 * @author Yong.Teng
 */
public class DomainValidator {

	private final static String DOMAIN_LABEL_REGEX = "\\p{Alnum}(?>[\\p{Alnum}-]*\\p{Alnum})*";

	private final static String TOP_LABEL_REGEX = "\\p{Alpha}{2,}";

	private final static String DOMAIN_NAME_REGEX = "^(?:" + DOMAIN_LABEL_REGEX + "\\.)+(" + TOP_LABEL_REGEX + ")$";

	private final static String[] LOCAL_TLDS = new String[]{
			// RFC2606 defined
			"localhost",
			// Also widely used as localhost.localdomain
			"localdomain"};

	private DomainValidator(){
	}

	public static boolean isValid(final CharSequence charSequence){
		if(charSequence == null){
			return false;
		}

		for(String tld : LOCAL_TLDS){
			if(tld.equalsIgnoreCase(charSequence.toString())){
				return true;
			}
		}

		Matcher matcher = Pattern.compile(DOMAIN_NAME_REGEX).matcher(charSequence);
		return matcher.matches() && isValidTld(matcher.group(1));
	}

	private static boolean isValidTld(final String tld){
		try{
			return EnumUtils.getEnumIgnoreCase(DomainTLD.class, chompLeadingDot(tld.toLowerCase())) != null;
		}catch(IllegalArgumentException e){
			return false;
		}
	}

	private static String chompLeadingDot(final String str){
		return str.startsWith(".") ? str.substring(1) : str;
	}

}

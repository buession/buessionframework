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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.routines;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 */
public class EmailValidator {

	private final static String WORD = "(([^\\s\\p{Cntrl}\\(\\)<>@,;:'\\\\\\\"\\.\\[\\]]|')+|(\"[^\"]*\"))";

	private final static Pattern MATCH_ASCII_PATTERN = Pattern.compile("^\\p{ASCII}+$");

	private final static Pattern EMAIL_PATTERN = Pattern.compile("^\\s*?(.+)@(.+?)\\s*$");

	private final static Pattern IP_DOMAIN_PATTERN = Pattern.compile("^\\[(.*)\\]$");

	private final static Pattern USER_PATTERN = Pattern.compile("^\\s*" + WORD + "(\\." + WORD + ")*$");

	private EmailValidator() {
	}

	public static boolean isValid(final CharSequence charSequence) {
		if(Validate.isBlank(charSequence)){
			return false;
		}

		if(StringUtils.endsWith(charSequence, '.')){
			return false;
		}

		if(StringUtils.contains(charSequence, '@') == false){
			return false;
		}

		Matcher asciiMatcher = MATCH_ASCII_PATTERN.matcher(charSequence);
		if(asciiMatcher.matches() == false){
			return false;
		}

		// Check the whole email address structure
		Matcher emailMatcher = EMAIL_PATTERN.matcher(charSequence);
		if(emailMatcher.matches() == false){
			return false;
		}

		return isValidUser(emailMatcher.group(1)) && isValidDomain(emailMatcher.group(2));
	}

	protected static boolean isValidUser(final String user) {
		return USER_PATTERN.matcher(user).matches();
	}

	protected static boolean isValidDomain(final String domain) {
		// see if domain is an IP address in brackets
		Matcher ipDomainMatcher = IP_DOMAIN_PATTERN.matcher(domain);
		return ipDomainMatcher.matches() ? IpValidator.isValid(ipDomainMatcher.group(1)) :
				DomainValidator.isValid(domain);
	}

}

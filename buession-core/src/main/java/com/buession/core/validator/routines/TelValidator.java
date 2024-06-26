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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 */
public class TelValidator {

	private final static String AREA_CODE_REGEX = "(86-?)|0)\\d{2,3}";

	private final static String TEL_REGEX = "[1-9]\\d{6,7}";

	private final static Pattern WITH_AREA_CODE_PATTERN = Pattern.compile(
			"^(((" + AREA_CODE_REGEX + "-?)|(（(" + AREA_CODE_REGEX + "）)|(\\((" + AREA_CODE_REGEX + "\\)))" +
					TEL_REGEX + "$");

	private final static Pattern WITHOUT_AREA_CODE_PATTERN = Pattern.compile("^" + TEL_REGEX + "$");

	private TelValidator() {
	}

	public static boolean isValid(final CharSequence charSequence, final AreaCodeType areaCodeType) {
		if(charSequence == null || charSequence.length() < 7){
			return false;
		}

		switch(areaCodeType){
			case NEED:
				return validHasAreaCode(charSequence);
			case NOT_NEED:
				return validNotHasAreaCode(charSequence);
			default:
				return validHasAreaCode(charSequence) || validNotHasAreaCode(charSequence);
		}
	}

	private static boolean validHasAreaCode(final CharSequence charSequence) {
		Matcher matcher = WITH_AREA_CODE_PATTERN.matcher(charSequence);
		return matcher.matches();
	}

	private static boolean validNotHasAreaCode(final CharSequence charSequence) {
		Matcher matcher = WITHOUT_AREA_CODE_PATTERN.matcher(charSequence);
		return matcher.matches();
	}

	public enum AreaCodeType {

		NEED,

		NOT_NEED,

		BOTH
	}

}
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

import com.buession.core.validator.Validate;

/**
 * @author Yong.Teng
 */
public class MobileValidator {

	private MobileValidator() {
	}

	public static boolean isValid(final CharSequence charSequence) {
		if(charSequence == null){
			return false;
		}

		int len = charSequence.length();
		if(len != 11){
			return false;
		}

		if(charSequence.charAt(0) != '1'){
			return false;
		}

		char c = charSequence.charAt(1);
		char c2 = charSequence.charAt(2);

		if(c == '3' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'){
			return Validate.isNumeric(charSequence.subSequence(2, len - 1));
		}else if(c == '4'){
			if(c2 != '4' && c2 != '5' && c2 != '7' && c2 != '8'){
				return false;
			}
		}else{
			return false;
		}

		return Validate.isNumeric(charSequence.subSequence(3, len - 1));
	}

}
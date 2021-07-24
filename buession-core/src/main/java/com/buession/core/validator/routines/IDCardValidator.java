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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.routines;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class IDCardValidator {

	private final static int IDCARD_LENGTH = 18;

	private final static int[] DIVISORS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

	private final static char[] CHECK_CODES = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

	private IDCardValidator(){

	}

	public static boolean isValid(final CharSequence charSequence, final boolean strict, final Date birthday){
		if(charSequence == null || charSequence.length() != IDCARD_LENGTH){
			return false;
		}else if(strict && birthday == null){
			return false;
		}

		int sum = 0;
		for(int i = 0; i < IDCARD_LENGTH - 1; i++){
			char c = charSequence.charAt(i);

			if(c < '0' || c > '9'){
				return false;
			}

			sum += (c - '0') * DIVISORS[i];
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		char check_code = CHECK_CODES[sum % 11];

		if(check_code == charSequence.charAt(IDCARD_LENGTH - 1)){
			if(strict){
				String s = charSequence.subSequence(6, 14).toString();

				try{
					return birthday.equals(simpleDateFormat.parse(s));
				}catch(ParseException e){
					return false;
				}
			}

			return true;
		}

		return false;
	}

}
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
 * | License: http://buession.buession.com.cn/LICENSE 												|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.routines;

import com.buession.core.utils.StringUtils;
import com.buession.lang.IpType;

/**
 * IP 验证器
 *
 * @author Yong.Teng
 */
public class IpValidator {

	private final static int IPV4_MIN_LENGTH = 7;

	private final static int IPV4_MAX_LENGTH = 15;

	private final static int IPV6_MIN_GROUP_SIZE = 2;

	private final static int IPV6_MAX_GROUP_SIZE = 8;

	private IpValidator(){
	}

	public final static boolean isValid(final CharSequence charSequence){
		if(charSequence == null || charSequence.length() == 0){
			return false;
		}

		String str = charSequence.toString();
		return isIpv4(str) || isIpv6(str);
	}

	public final static boolean isValid(final CharSequence charSequence, final IpType type){
		if(charSequence == null || charSequence.length() == 0){
			return false;
		}

		if(IpType.IP_V4.equals(type)){
			return isIpv4(charSequence.toString());
		}else if(IpType.IP_V6.equals(type)){
			return isIpv6(charSequence.toString());
		}else{
			return false;
		}
	}

	private final static boolean isIpv4(final String str){
		if(str == null){
			return false;
		}

		if("0.0.0.0".equals(str)){
			return true;
		}

		int len = str.length();
		if(len < IPV4_MIN_LENGTH || len > IPV4_MAX_LENGTH){
			return false;
		}

		String[] groups = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, ".");
		if(groups == null || groups.length != 4){
			return false;
		}

		for(String group : groups){
			char[] digits = group.toCharArray();
			if(IPV4_group_valid(digits, digits.length) == false){
				return false;
			}
		}

		return true;
	}

	private final static boolean IPV4_group_valid(final char[] digits, final int digitSize){
		switch(digitSize){
			case 1:
				return digits[0] >= '0' && digits[0] <= '9';
			case 2:
				return (digits[0] >= 1 && digits[0] <= 9) && (digits[1] >= '0' && digits[1] <= '9');
			case 3:
				if(digits[0] == '1'){
					return (digits[1] >= '0' && digits[1] <= '9') && (digits[2] >= '0' && digits[2] <= '9');
				}else if(digits[0] == '2'){
					if(digits[1] < '0' || digits[1] > '5'){
						return false;
					}else if(digits[1] == '5'){
						return digits[2] >= '0' && digits[2] <= '5';
					}else{
						return digits[2] >= '0' && digits[2] <= '9';
					}
				}
			default:
				return false;
		}
	}

	private final static boolean isIpv6(final String str){
		if(str == null){
			return false;
		}

		if("::".equals(str) || "::1".equals(str)){
			return true;
		}

		// 首选格式：xxxx.xxxx.xxxx.xxxx.xxxx.xxxx.xxxx.xxxx
		// 压缩格式："::" 只能出现一次
		// 内嵌 IPV4：X:X:X:X:X:X:d.d.d.d
		int dotIndex = str.indexOf('.');
		if(dotIndex > -1){
			int colonIndex = str.lastIndexOf(':');
			return isIpv6(str.substring(0, colonIndex + 1), 1, 6) && isIpv4(str.substring(colonIndex + 1));
		}else{
			return isIpv6(str, IPV6_MIN_GROUP_SIZE, IPV6_MAX_GROUP_SIZE);
		}
	}

	private final static boolean isIpv6(final String str, final int minGroup, final int maxGroup){
		if("::".equals(str)){
			return true;
		}

		String[] groups = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, ":");
		if(groups == null || groups.length < minGroup || groups.length > maxGroup){
			return false;
		}

		// 判断是否有多组 ::
		int strLen = str.length();
		int sc = 0;
		for(int j = 0; j < strLen; j++){
			if(sc > 1){
				return false;
			}

			if(str.charAt(j) == ':'){
				int nj = j + 1;
				if(nj < strLen && str.charAt(nj) == ':'){
					sc++;
				}
			}
		}

		for(String group : groups){
			char[] digits = group.toCharArray();
			if(IPV6_group_valid(digits, digits.length) == false){
				return false;
			}
		}

		return true;
	}

	private final static boolean IPV6_group_valid(final char[] digits, final int digitSize){
		if(digitSize < 1 || digitSize > 4){
			return false;
		}

		for(char c : digits){
			if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')){
				continue;
			}else{
				return false;
			}
		}

		return true;
	}

}

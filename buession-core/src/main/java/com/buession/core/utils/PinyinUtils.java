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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.core.validator.Validate;
import com.buession.lang.CaseType;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 *
 * @author Yong.Teng
 */
public class PinyinUtils {

	/**
	 * 获取汉字拼音
	 *
	 * @param str
	 * 		汉字
	 *
	 * @return 汉字拼音
	 *
	 * @throws BadHanyuPinyinOutputFormatCombination
	 * 		异常
	 */
	public static String getPinyin(final String str) throws BadHanyuPinyinOutputFormatCombination {
		return getPinyin(str, false, CaseType.LOWERCASE);
	}

	/**
	 * 获取汉字拼音
	 *
	 * @param str
	 * 		汉字
	 * @param hasTone
	 * 		是否包含声调
	 * @param caseType
	 * 		大写或小写
	 *
	 * @return 汉字拼音
	 *
	 * @throws net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination
	 * 		异常
	 */
	public static String getPinyin(final String str, final boolean hasTone, final CaseType caseType)
			throws BadHanyuPinyinOutputFormatCombination {
		if(Validate.isEmpty(str)){
			return str;
		}

		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

		if(hasTone == false){
			outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		}

		if(CaseType.LOWERCASE == caseType){
			outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		}else if(CaseType.UPERCASE == caseType){
			outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		}

		StringBuilder sb = new StringBuilder(str.length());

		try{
			char[] chars = str.toCharArray();

			for(char c : chars){
				// 如果包含有中文标点除号，需要使用正则表达式
				if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){
					sb.append(PinyinHelper.toHanyuPinyinStringArray(c, outputFormat)[0]);
				}else{
					sb.append(c);
				}
			}
		}catch(BadHanyuPinyinOutputFormatCombination e){
			throw new BadHanyuPinyinOutputFormatCombination("【" + str + "】转为拼音失败：" + e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * 提取每个汉字的首字母
	 *
	 * @param str
	 * 		汉字
	 *
	 * @return 拼音首字母集合
	 */
	public static String getPinYinFirstChar(final String str) {
		if(Validate.isEmpty(str)){
			return str;
		}

		char[] chars = str.toCharArray();
		String[] pinyinArray;
		StringBuilder sb = new StringBuilder(chars.length);

		for(char c : chars){
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
			if(pinyinArray != null){
				sb.append(pinyinArray[0].charAt(0));
			}else{
				sb.append(c);
			}
		}

		return sb.toString();
	}

}

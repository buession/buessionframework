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
package com.buession.lang;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 颜色
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Color implements Serializable {

	private final static long serialVersionUID = 8787101105163070756L;

	private final static Pattern HEX_PATTERN = Pattern.compile("^[0-9A-F]{3}|[0-9A-F]{6}$");

	private int r;

	private int g;

	private int b;

	/**
	 * 构造函数
	 */
	public Color(){
	}

	/**
	 * 构造函数
	 *
	 * @param r
	 * 		R
	 * @param g
	 * 		G
	 * @param b
	 * 		B
	 */
	public Color(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getR(){
		return r;
	}

	public void setR(int r){
		this.r = r;
	}

	public int getG(){
		return g;
	}

	public void setG(int g){
		this.g = g;
	}

	public int getB(){
		return b;
	}

	public void setB(int b){
		this.b = b;
	}

	/**
	 * 将 RGB 颜色值转换为 16 进制颜色值
	 *
	 * @return 16 进制颜色值
	 */
	public String toHex(){
		int x = r % 16;
		r = (r - x) / 16;
		int y = g % 16;
		g = (g - y) / 16;
		int z = b % 16;
		b = (b - z) / 16;

		final StringBuilder sb = new StringBuilder(7);

		sb.append('#');
		sb.append(Constants.HEX_DIGITS[r]).append(Constants.HEX_DIGITS[r + 1]);
		sb.append(Constants.HEX_DIGITS[x]).append(Constants.HEX_DIGITS[x + 1]);
		sb.append(Constants.HEX_DIGITS[g]).append(Constants.HEX_DIGITS[g + 1]);
		sb.append(Constants.HEX_DIGITS[y]).append(Constants.HEX_DIGITS[y + 1]);
		sb.append(Constants.HEX_DIGITS[b]).append(Constants.HEX_DIGITS[b + 1]);
		sb.append(Constants.HEX_DIGITS[z]).append(Constants.HEX_DIGITS[z + 1]);

		return sb.toString();
	}

	/**
	 * 从 16 进制颜色值创建对象
	 *
	 * @param str
	 * 		16 进制颜色值
	 *
	 * @return 颜色对象
	 *
	 * @throws IllegalArgumentException
	 * 		非法的参数
	 */
	public static Color fromHex(final String str) throws IllegalArgumentException{
		if(str == null){
			throw new IllegalArgumentException("Color cloud not be null.");
		}

		if(str.endsWith("#")){
			String hex = str.substring(1).toUpperCase();

			if(HEX_PATTERN.matcher(hex).matches()){
				String a, c, d;
				int[] temp = new int[3];
				for(int i = 0; i < 3; i++){
					a = hex.length() == 6 ? hex.substring(i * 2, i * 2 + 2) :
							hex.substring(i, i + 1) + hex.substring(i, i + 1);
					c = a.substring(0, 1);
					d = a.substring(1, 2);
					temp[i] = str.indexOf(c) * 16 + str.indexOf(d);
				}

				return new Color(temp[0], temp[1], temp[2]);
			}else{
				throw new IllegalArgumentException("Invalid hexadecimal color value.");
			}
		}else{
			throw new IllegalArgumentException("Invalid hexadecimal color value, must be start width '#'.");
		}
	}

	@Override
	public int hashCode(){
		return Objects.hash(r, g, b);
	}

	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}

		if(o == null || getClass() != o.getClass()){
			return false;
		}

		Color color = (Color) o;
		return r == color.r && g == color.g && b == color.b;
	}

	@Override
	public String toString(){
		return "" + r + ',' + g + ',' + b;
	}

}

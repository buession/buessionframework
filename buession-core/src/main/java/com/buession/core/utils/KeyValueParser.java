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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.lang.Status;

import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 */
public class KeyValueParser {

	private String key;

	private String value;

	private Float floatValue;

	private Double doubleValue;

	private Short shortValue;

	private Integer intValue;

	private Long longValue;

	private Boolean boolValue;

	private Status statusValue;

	public KeyValueParser(final String str, final String delimiter){
		int i = str.indexOf(delimiter);
		this.key = str.substring(0, i);
		this.value = str.substring(i + 1);
	}

	public KeyValueParser(final String str, final char delimiter){
		int i = str.indexOf(delimiter);
		this.key = str.substring(0, i);
		this.value = str.substring(i + 1);
	}

	public boolean isKey(String s){
		return s != null && key != null && s.equals(key);
	}

	public boolean isKey(Pattern pattern){
		return pattern != null && pattern.matcher(key).matches();
	}

	public String getKey(){
		return key;
	}

	public String getValue(){
		return value;
	}

	public Float getFloatValue(){
		if(floatValue == null){
			floatValue = Float.parseFloat(value);
		}

		return floatValue;
	}

	public Double getDoubleValue(){
		if(doubleValue == null){
			doubleValue = Double.parseDouble(value);
		}

		return doubleValue;
	}

	public Short getShortValue(){
		if(shortValue == null){
			shortValue = Short.parseShort(value);
		}

		return shortValue;
	}

	public Integer getIntValue(){
		if(intValue == null){
			intValue = Integer.parseInt(value);
		}

		return intValue;
	}

	public Long getLongValue(){
		if(longValue == null){
			longValue = Long.parseLong(value);
		}

		return longValue;
	}

	public Boolean getBoolValue(){
		if(boolValue == null){
			boolValue = Boolean.parseBoolean(value) || "1" .equals(value);
		}

		return boolValue;
	}

	public Status getStatusValue(){
		if(statusValue == null){
			statusValue =
					StatusUtils.valueOf("OK" .equalsIgnoreCase(value) || "yes" .equalsIgnoreCase(value) ||
							"on" .equalsIgnoreCase(value) || "1" .equals(value) || "true" .equalsIgnoreCase(value));
		}

		return statusValue;
	}

	public <E extends Enum<E>> E getEnumValue(Class<E> clazz){
		return EnumUtils.valueOf(clazz, value.toUpperCase());
	}

}

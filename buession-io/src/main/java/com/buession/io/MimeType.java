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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.io;

import com.buession.core.utils.Assert;
import com.buession.core.utils.KeyValueParser;
import com.buession.core.validator.Validate;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * MimeType
 *
 * @author Yong.Teng
 */
public final class MimeType {

	private final String type;

	private final String subtype;

	/**
	 * MimeType 描述
	 *
	 * @since 1.3.2
	 */
	private String description;

	/**
	 * 构造函数
	 *
	 * @param mimeType
	 * 		字符串形式 MimeType
	 *
	 * @since 1.3.2
	 */
	public MimeType(@NotEmpty String mimeType) {
		Assert.isBlank(mimeType, "MimeType string cloud empty or null.");

		if(Validate.isMimeType(mimeType)){
			KeyValueParser keyValueParser = new KeyValueParser(mimeType, '/');

			this.type = keyValueParser.getKey().toLowerCase();
			this.subtype = keyValueParser.getValue().toLowerCase();
		}else{
			throw new IllegalArgumentException("Illegal MimeType: " + mimeType);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		Type
	 * @param subtype
	 * 		子 Type
	 */
	public MimeType(@NotEmpty String type, @NotEmpty String subtype) {
		this.type = type.toLowerCase();
		this.subtype = subtype.toLowerCase();
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		Type
	 * @param subtype
	 * 		子 Type
	 * @param description
	 * 		MimeType 描述
	 *
	 * @since 1.3.2
	 */
	public MimeType(@NotEmpty String type, @NotEmpty String subtype, String description) {
		this(type, subtype);
		this.description = description;
	}

	/**
	 * 将字符串解析为 MimeType
	 *
	 * @param mimeType
	 * 		代解析字符串
	 *
	 * @return MimeType
	 */
	public static MimeType parse(String mimeType) {
		Assert.isBlank(mimeType, "MimeType string cloud empty or null.");
		KeyValueParser keyValueParser = new KeyValueParser(mimeType, '/');
		return new MimeType(keyValueParser.getKey(), keyValueParser.getValue());
	}

	public String getType() {
		return type;
	}

	public String getSubtype() {
		return subtype;
	}

	/**
	 * 返回 MimeType 描述
	 *
	 * @return MimeType 描述
	 *
	 * @since 1.3.2
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 MimeType 描述
	 *
	 * @param description
	 * 		MimeType 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return 16 * (type.hashCode() + subtype.hashCode());
	}

	@Override
	public boolean equals(Object object) {
		if(this == object){
			return true;
		}

		if(object instanceof MimeType){
			MimeType that = (MimeType) object;
			return Objects.equals(type, that.type) && Objects.equals(subtype, that.subtype);
		}

		return false;
	}

	@Override
	public String toString() {
		return type + '/' + subtype;
	}

}

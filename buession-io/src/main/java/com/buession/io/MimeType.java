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
package com.buession.io;

import com.buession.core.utils.Assert;
import com.buession.core.utils.KeyValueParser;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public final class MimeType {

	private String type;

	private String subtype;

	public MimeType(@NotEmpty String type, @NotEmpty String subtype){
		this.type = type.toLowerCase();
		this.subtype = subtype.toLowerCase();
	}

	public String getType(){
		return type;
	}

	public String getSubtype(){
		return subtype;
	}

	public static MimeType parse(String str){
		Assert.isBlank(str, "MimeType string cloud empty or null.");
		KeyValueParser keyValueParser = new KeyValueParser(str, '/');
		return new MimeType(keyValueParser.getKey(), keyValueParser.getValue());
	}

	@Override
	public int hashCode(){
		return 16 * (type.hashCode() + subtype.hashCode());
	}

	@Override
	public boolean equals(Object object){
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
	public String toString(){
		StringBuilder sb = new StringBuilder(type.length() + subtype.length() + 1);

		sb.append(type).append('/').append(subtype);

		return sb.toString();
	}

}

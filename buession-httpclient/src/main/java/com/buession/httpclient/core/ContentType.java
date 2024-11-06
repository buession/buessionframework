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
package com.buession.httpclient.core;

import com.buession.core.utils.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public final class ContentType {

	public final static ContentType APPLICATION_OBJECT_STREAM = new ContentType("application/octet-stream",
			StandardCharsets.ISO_8859_1);

	public final static ContentType APPLICATION_JSON = new ContentType("application/json", StandardCharsets.UTF_8);

	public final static ContentType APPLICATION_JAVASCRIPT = new ContentType("application/javascript",
			StandardCharsets.UTF_8);

	public final static ContentType TEXT_HTML = new ContentType("text/html", Charset.defaultCharset());

	public final static ContentType TEXT_XML = new ContentType("text/xml", Charset.defaultCharset());

	public final static ContentType TEXT_PLAIN = new ContentType("text/plain", Charset.defaultCharset());

	public final static ContentType APPLICATION_FORM_URLENCODED = new ContentType("application/x-www-form-urlencoded",
			StandardCharsets.ISO_8859_1);

	public final static ContentType MULTIPART_FORM_DATA = new ContentType("multipart/form-data",
			StandardCharsets.ISO_8859_1);

	private final String mimeType;

	private Charset charset;

	public ContentType(final String mimeType) {
		this.mimeType = mimeType;
	}

	public ContentType(final String mimeType, final Charset charset) {
		this.mimeType = mimeType;
		this.charset = charset;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Charset getCharset() {
		return charset;
	}

	public String valueOf() {
		return valueOf(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mimeType, charset);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof ContentType){
			ContentType that = (ContentType) obj;
			return StringUtils.equalsIgnoreCase(mimeType, that.mimeType) && Objects.equals(charset, that.charset);
		}

		return false;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(mimeType.length() + (charset == null ? 0 : 16));

		sb.append(mimeType);

		if(charset != null){
			sb.append("; charset=").append(charset.name());
		}

		return sb.toString();
	}

	public static String valueOf(ContentType contentType) {
		return contentType == null ? null : contentType.toString();
	}

}

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
package com.buession.httpclient.core;

import com.buession.core.utils.Assert;
import com.buession.lang.Constants;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Yong.Teng
 */
public class RequestBodyElement implements Serializable, Cloneable {

	public final static int HASH_SEED = 17;

	public final static int HASH_OFFSET = 37;

	private final String name;

	private final String value;

	public RequestBodyElement(final String name, final short value){
		this(name, Short.toString(value));
	}

	public RequestBodyElement(final String name, final int value){
		this(name, Integer.toString(value));
	}

	public RequestBodyElement(final String name, final long value){
		this(name, Long.toString(value));
	}

	public RequestBodyElement(final String name, final float value){
		this(name, Float.toString(value));
	}

	public RequestBodyElement(final String name, final double value){
		this(name, Double.toString(value));
	}

	public RequestBodyElement(final String name, final boolean value){
		this(name, Boolean.toString(value));
	}

	public RequestBodyElement(final String name, final String value){
		Assert.isBlank(name, "name cloud not be null or empty.");

		this.name = name;
		this.value = value;
	}

	public RequestBodyElement(final String name, final Object value){
		this(name, value == null ? null : value.toString());
	}

	public String getName(){
		return name;
	}

	public String getValue(){
		return value;
	}

	public String getOptionalValue(){
		return Optional.of(value).orElse(Constants.EMPTY_STRING);
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder(name.length() + 1);

		sb.append(name);
		sb.append('=');
		if(value != null){
			sb.append(value);
		}

		return sb.toString();
	}

	@Override
	public boolean equals(final Object object){
		if(this == object){
			return true;
		}

		if(object instanceof RequestBodyElement){
			final RequestBodyElement that = (RequestBodyElement) object;
			return Objects.equals(name, that.name) && Objects.equals(value, that.value);
		}

		return false;
	}

	@Override
	public int hashCode(){
		int hash = HASH_SEED;

		hash = hashCode(hash, name);
		hash = hashCode(hash, value);

		return hash;
	}

	protected static int hashCode(final int seed, final int hashcode){
		return seed * HASH_OFFSET + hashcode;
	}

	protected static int hashCode(final int seed, final Object object){
		return hashCode(seed, Objects.hashCode(object));
	}

}

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

import java.io.File;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class MultipartRequestBodyElement extends RequestBodyElement {

	private final File file;

	public MultipartRequestBodyElement(final String name, final File file){
		super(name, (Object) null);
		this.file = file;
	}

	public MultipartRequestBodyElement(final String name, final short value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final int value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final long value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final float value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final double value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final boolean value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final String value){
		super(name, value);
		this.file = null;
	}

	public MultipartRequestBodyElement(final String name, final Object value){
		super(name, value);
		this.file = null;
	}

	public File getFile(){
		return file;
	}

	@Override
	public boolean equals(final Object object){
		if(this == object){
			return true;
		}

		if(object instanceof MultipartRequestBodyElement){
			if(super.equals(object)){
				final MultipartRequestBodyElement that = (MultipartRequestBodyElement) object;
				return Objects.equals(file, that.file);
			}
		}

		return false;
	}

	@Override
	public int hashCode(){
		int hash = super.hashCode();

		hash = hashCode(hash, file);

		return hash;
	}

}

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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class MultipartRequestBodyElement extends RequestBodyElement {

	private final static long serialVersionUID = 5534971168870221225L;

	private File file;

	private InputStream inputStream;

	private String fileName;

	public MultipartRequestBodyElement(final String name, final short value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final int value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final long value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final float value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final double value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final boolean value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final String value){
		super(name, value);
	}

	public MultipartRequestBodyElement(final String name, final File file){
		super(name, null);
		this.file = file;
	}

	public MultipartRequestBodyElement(final String name, final InputStream inputStream){
		super(name, null);
		this.inputStream = inputStream;
	}

	public MultipartRequestBodyElement(final String name, final InputStream inputStream, final String fileName){
		this(name, inputStream);
		this.fileName = fileName;
	}

	public File getFile(){
		return file;
	}

	public InputStream getInputStream(){
		return inputStream;
	}

	public String getFileName(){
		return fileName;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder(getName().length() + 1);

		sb.append(getName()).append('=');
		if(getValue() != null){
			sb.append(getValue());
		}else if(file != null){
			sb.append(file);
		}else if(inputStream != null){
			sb.append(inputStream).append("; fileName=").append(fileName);
		}

		return sb.toString();
	}

	@Override
	public boolean equals(final Object object){
		if(this == object){
			return true;
		}

		if(object instanceof MultipartRequestBodyElement){
			final MultipartRequestBodyElement that = (MultipartRequestBodyElement) object;
			return Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue()) &&
					Objects.equals(getFile(), that.getFile()) &&
					Objects.equals(getInputStream(), that.getInputStream()) &&
					Objects.equals(getFileName(), that.getFileName());
		}

		return false;
	}

	@Override
	public int hashCode(){
		int hash = super.hashCode();

		hash = hashCode(hash, getFile());
		hash = hashCode(hash, getInputStream());
		hash = hashCode(hash, getFileName());

		return hash;
	}

}

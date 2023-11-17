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
package com.buession.beans.converters;

import com.buession.beans.AbstractPropertyConverter;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

/**
 * {@link URI} 类型 Bean 属性转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public final class URIPropertyConverter extends AbstractPropertyConverter<URI> {

	@Override
	protected URI convertToType(final Class<?> sourceType, final Object value, final Class<URI> targetType) {
		if(URL.class.isAssignableFrom(sourceType)){
			try{
				return ((URL) value).toURI();
			}catch(URISyntaxException e){
				throw conversionException(sourceType, value, URI.class, e.getMessage());
			}
		}else if(File.class.isAssignableFrom(sourceType)){
			return ((File) value).toURI();
		}else if(Path.class.isAssignableFrom(sourceType)){
			return ((Path) value).toUri();
		}else{
			try{
				return new URI(value.toString());
			}catch(URISyntaxException e){
				throw conversionException(sourceType, value, URI.class, e.getMessage());
			}
		}
	}

}

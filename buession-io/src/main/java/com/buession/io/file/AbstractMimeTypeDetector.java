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
package com.buession.io.file;

import com.buession.core.validator.Validate;
import com.buession.io.MimeType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;

/**
 * MimeType 探测器 抽象类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public abstract class AbstractMimeTypeDetector implements MimeTypeDetector {

	protected volatile boolean initialized = false;

	protected HashMap<String, MimeType> internalMimetypes = new HashMap<>();

	/**
	 * 构造函数
	 */
	public AbstractMimeTypeDetector(){
		initialize();
	}

	@Override
	public final MimeType probe(String path) throws IOException{
		if(Validate.hasText(path) == false){
			throw new NullPointerException("File path is null or empty.");
		}else{
			return implProbeMimeType(path);
		}
	}

	@Override
	public final MimeType probe(File file) throws IOException{
		if(file == null){
			throw new NullPointerException("'file' is null");
		}else{
			return probe(file.toPath());
		}
	}

	@Override
	public final MimeType probe(Path path){
		if(path == null){
			throw new NullPointerException("'file' is null");
		}else{
			return implProbeMimeType(path);
		}
	}

	protected abstract void initialize();

	protected static String getExtension(String path){
		String extension = "";

		if(Validate.isNotEmpty(path)){
			int temp = path.indexOf(46);
			if(temp >= 0 && temp < path.length() - 1){
				extension = path.substring(temp + 1);
			}
		}

		return extension;
	}

	protected abstract MimeType implProbeMimeType(final String path);

	protected abstract MimeType implProbeMimeType(final Path path);

	protected void putIfAbsent(final String extension, final String contentType, final String description){
		if(Validate.isNotEmpty(extension) && Validate.isNotEmpty(contentType) && internalMimetypes.containsKey(extension) == false){
			MimeType mimeType = MimeType.parse(contentType);

			mimeType.setDescription(description);
			internalMimetypes.put(extension, mimeType);
		}
	}

	protected static MimeType parse(final String s, final String description){
		int slash = s.indexOf('/');
		int semicolon = s.indexOf(';');

		if(slash < 0){
			return null;  // no subtype
		}

		String type = parseType(s.substring(0, slash));
		if(isValidToken(type) == false){
			return null;  // invalid type
		}

		String subtype = parseType((semicolon < 0) ? s.substring(slash + 1) : s.substring(slash + 1, semicolon));
		if(isValidToken(subtype) == false){
			return null;  // invalid subtype
		}

		return new MimeType(type, subtype, description);
	}

	private static String parseType(final String type){
		return type.trim().toLowerCase(Locale.ENGLISH);
	}

	private static boolean isTokenChar(char c){
		return c > ' ' && c < 127 && "()<>@,;:/[]?=\\\"".indexOf(c) < 0;
	}

	private static boolean isValidToken(String str){
		int strLength = str.length();

		if(strLength == 0){
			return false;
		}else{
			for(int i = 0; i < strLength; ++i){
				if(!isTokenChar(str.charAt(i))){
					return false;
				}
			}

			return true;
		}
	}

}

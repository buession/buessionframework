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
package com.buession.io.file;

import com.buession.core.validator.Validate;
import com.buession.io.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MimeType 探测器
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DefaultMimeTypeDetector extends AbstractMimeTypeDetector {

	private final static Logger logger = LoggerFactory.getLogger(DefaultMimeTypeDetector.class);

	/**
	 * 构造函数
	 */
	public DefaultMimeTypeDetector() {
		initialize();
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected MimeType implProbeMimeType(final String path) {
		if(Validate.isBlank(path)){
			return null;
		}

		String extension = getExtension(path);
		if(extension.isEmpty()){
			return null;
		}

		loadMimetypes();

		if(Validate.isEmpty(internalMimetypes)){
			return null;
		}

		// Case-sensitive search
		MimeType mimeType;
		do{
			mimeType = internalMimetypes.get(extension);
			if(mimeType == null){
				extension = getExtension(extension);
			}
		}while(mimeType == null && extension.isEmpty() == false);

		return mimeType;
	}

	@Override
	protected MimeType implProbeMimeType(final Path path) {
		Path fn = path.getFileName();

		if(fn == null){
			return null;
		}

		return implProbeMimeType(fn.toString());
	}

	private void loadMimetypes() {
		if(initialized == false){
			synchronized(this){
				if(initialized == false){
					InputStream is = DefaultMimeTypeDetector.class.getResourceAsStream("/mime.conf");
					BufferedReader br = null;

					try{
						br = new BufferedReader(new InputStreamReader(is));
						String line = null;
						String entry = "";

						while((line = br.readLine()) != null){
							entry += line;

							if(entry.endsWith("\\")){
								entry = entry.substring(0, entry.length() - 1);
								continue;
							}

							parseMimeEntry(entry);
							entry = "";
						}

						if(entry.isEmpty() == false){
							parseMimeEntry(entry);
						}
					}catch(Exception e){
						logger.error("Load mimetype error: {}", e.getMessage(), e);
					}finally{
						if(is != null){
							try{
								is.close();
							}catch(IOException e){
							}
						}

						if(br != null){
							try{
								br.close();
							}catch(IOException e){
							}
						}
					}

					initialized = true;
				}
			}
		}
	}

	private void parseMimeEntry(String entry) {
		entry = entry.trim();
		if(entry.isEmpty() || entry.charAt(0) == '#'){
			return;
		}

		entry = entry.replaceAll("\\s*#.*", "");
		int equalIdx = entry.indexOf('=');
		if(equalIdx > 0){
			Pattern typePattern = Pattern.compile("\\b(\"\\p{Graph}+?/\\p{Graph}+?\"|\\p{Graph}+/\\p{Graph" + "}+\\b)");

			// Parse a mime-types command having the key-value pair format
			Matcher typeMatcher = typePattern.matcher(entry);

			if(typeMatcher.find()){
				String type = typeMatcher.group();
				if(type.charAt(0) == '"'){
					type = type.substring(1, type.length() - 1);
				}

				final String EXTEQUAL = "extensions=";

				String extRegex = "\\b" + EXTEQUAL + "(\"[\\p{Graph}\\p{Blank}]+?\"|\\p{Graph}+\\b)";
				Pattern extPattern = Pattern.compile(extRegex);
				Matcher extMatcher = extPattern.matcher(entry);

				if(extMatcher.find()){
					String exts = extMatcher.group().substring(EXTEQUAL.length());

					if(exts.charAt(0) == '"'){
						exts = exts.substring(1, exts.length() - 1);
					}

					final String DESCRIPTIONEQUAL = "description=";
					String descriptionRegex = "\\b" + DESCRIPTIONEQUAL + "([\\s\\S]*;)";
					Pattern descriptionPattern = Pattern.compile(descriptionRegex);
					Matcher descriptionMatcher = descriptionPattern.matcher(entry);

					String description = descriptionMatcher.find() ? descriptionMatcher.group() : null;

					String[] extList = exts.split("[\\p{Blank}\\p{Punct}]+");
					for(String ext : extList){
						putIfAbsent(ext, type, description);
					}
				}
			}
		}
	}

}

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;

/**
 * MimeType 探测器
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class MimeTypeDetector {

	private volatile boolean initialized = false;

	private HashMap<String, String> internalMimetypes = new HashMap<>();

	/**
	 * 构造函数
	 */
	public MimeTypeDetector(){
		initialize();
	}

	public final String probeContentType(File file) throws IOException{
		if(file == null){
			throw new NullPointerException("'file' is null");
		}else{
			String contentType = implProbeContentType(file);

			/*
			if(contentType == null){
				Path fileName = path.getFileName();

				if(fileName != null){
					FileNameMap fileNameMap = URLConnection.getFileNameMap();
					contentType = fileNameMap.getContentTypeFor(fileName.toString());
				}
			}
			 */

			return Optional.ofNullable(contentType).orElse(parse(contentType));
		}
	}

	public final String probeContentType(Path path) throws IOException{
		if(path == null){
			throw new NullPointerException("'file' is null");
		}else{
			String contentType = implProbeContentType(path);

			/*
			if(contentType == null){
				Path fileName = path.getFileName();

				if(fileName != null){
					FileNameMap fileNameMap = URLConnection.getFileNameMap();
					contentType = fileNameMap.getContentTypeFor(fileName.toString());
				}
			}
			 */

			return Optional.ofNullable(contentType).orElse(parse(contentType));
		}
	}

	private void initialize(){
		if(initialized == false){
			synchronized(this){
				if(initialized == false){
					InputStream is = MimeTypeDetector.class.getResourceAsStream("/mime.conf");

					try{
						loadMimetypes(is);
					}catch(Exception e){

					}finally{
						if(is != null){
							try{
								is.close();
							}catch(IOException e){
							}
						}
					}

					initialized = true;
				}
			}
		}
	}

	private void loadMimetypes(final InputStream is) throws IOException{

	}

}

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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.git.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * JSON 格式 Git 解析器抽象类
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public class JsonGitParser extends AbstractGitParser {

	private final static Logger logger = LoggerFactory.getLogger(JsonGitParser.class);

	/**
	 * 构造函数
	 */
	public JsonGitParser() {
		super(new ClassPathResource("git.properties"));
	}

	/**
	 * 构造函数
	 *
	 * @param resource
	 * 		文件资源
	 */
	public JsonGitParser(final Resource resource) {
		super(resource);
	}

	/**
	 * 构造函数
	 *
	 * @param path
	 * 		Git 信息文件路径
	 */
	public JsonGitParser(final String path) {
		super(path);
	}

	@Override
	protected Properties loadData() {
		ObjectMapper objectMapper = new ObjectMapper();

		try{
			return objectMapper.readValue(resource.getInputStream(), new TypeReference<Properties>() {

			});
		}catch(Exception e){
			logger.error("Failed to load git properties", e);
		}

		return null;
	}

}

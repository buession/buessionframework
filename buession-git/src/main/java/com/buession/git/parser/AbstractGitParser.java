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
package com.buession.git.parser;

import com.buession.core.utils.Assert;
import com.buession.git.Git;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

import java.util.Properties;

/**
 * Git 解析器抽象类
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public abstract class AbstractGitParser implements GitParser {

	protected Resource resource;

	/**
	 * 构造函数
	 */
	public AbstractGitParser(){

	}

	/**
	 * 构造函数
	 *
	 * @param resource
	 * 		文件资源
	 */
	public AbstractGitParser(final Resource resource){
		Assert.isNull(resource, "Git properties resource cloud not be null.");
		this.resource = resource;
	}

	/**
	 * 构造函数
	 *
	 * @param path
	 * 		Git 信息文件路径
	 */
	public AbstractGitParser(final String path){
		Assert.isBlank(path, "Git properties path cloud not be empty or null.");
		this.resource = new ClassPathResource(path);
	}

	@Nullable
	@Override
	public Git parse(){
		final Git git = new Git();
		final Properties properties = loadData();
		final GitInfoParser infoParser = new GitInfoParser(properties);

		git.setBranch(properties.getProperty("git.branch"));
		git.setDirty(Boolean.parseBoolean(properties.getProperty("git.dirty")));

		git.setBuild(infoParser.build());
		git.setRemote(infoParser.remote());
		git.setLocal(infoParser.local());
		git.setCommit(infoParser.commit());
		git.setClosest(infoParser.closest());
		git.setTags(infoParser.tags());
		git.setTotal(infoParser.total());

		return git;
	}

	protected abstract Properties loadData();

}

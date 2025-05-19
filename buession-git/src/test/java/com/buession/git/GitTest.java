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
package com.buession.git;

import com.buession.git.parser.GitParser;
import com.buession.git.parser.PropertiesGitParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/**
 * @author Yong.Teng
 * @since 2.2.0
 */
public class GitTest {

	@Test
	public void gitToString() {
		Git git = new Git();

		git.setBranch("master");

		Build build = new Build();
		git.setBuild(build);

		System.out.println(git.toString());
	}

	@Test
	public void buildToString() {
		Build build = new Build();

		System.out.println(build.toString());
	}

	@Test
	public void gitJsonEncode() throws JsonProcessingException {
		Git git = new Git();

		git.setBranch("master");

		Build build = new Build();
		git.setBuild(build);

		Total total = new Total();
		Total.Commit totalCommit = new Total.Commit();

		total.setCommit(totalCommit);

		git.setTotal(total);

		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(git));
	}

	@Test
	public void totalJsonEncode() throws JsonProcessingException {
		Total total = new Total();

		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(total));
	}

	@Test
	public void parse() throws JsonProcessingException {
		GitParser gitParser = new PropertiesGitParser();

		System.out.println(gitParser.parse());
	}

}

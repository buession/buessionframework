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

import com.buession.core.collect.Arrays;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.git.Build;
import com.buession.git.Closest;
import com.buession.git.Commit;
import com.buession.git.Local;
import com.buession.git.Remote;
import com.buession.git.Total;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.Set;

/**
 * @author Yong.Teng
 * @since 2.2.0
 */
class GitInfoParser {

	private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

	private final Properties properties;

	private final static Logger logger = LoggerFactory.getLogger(GitInfoParser.class);

	public GitInfoParser(final Properties properties){
		this.properties = properties;
	}

	public Build build(){
		final Build build = new Build();

		build.setHost(getValue("build.host"));
		build.setTime(coercePropertyToZonedDateTime("build.time"));

		final Build.User user = new Build.User(getValue("build.user.name"), getValue("build.user.email"));

		build.setUser(user);
		build.setVersion(getValue("build.version"));

		return build;
	}

	public Remote remote(){
		final Remote remote = new Remote();

		final Remote.Origin remoteOrigin = new Remote.Origin(getValue("remote.origin.url"));

		remote.setOrigin(remoteOrigin);

		return remote;
	}

	public Local local(){
		final Local local = new Local();

		final Local.Branch localBranch = new Local.Branch(getValue("local.branch.ahead"),
				getValue("local.branch.behind"));

		local.setBranch(localBranch);

		return local;
	}

	public Commit commit(){
		final Commit commit = new Commit();

		commit.setTime(coercePropertyToZonedDateTime(getValue("commit.time")));

		final Commit.Id commitId = new Commit.Id();
		commitId.setValue(getValue("commit.id"));
		commitId.setAbbrev(getValue("commit.id.abbrev"));
		commitId.setDescribe(getValue("commit.id.describe"));
		commitId.setDescribeShort(getValue("commit.id.describe-short"));

		commit.setId(commitId);

		final Commit.User commitUser = new Commit.User(getValue("commit.user.name"), getValue("commit.user.email"));

		commit.setUser(commitUser);

		final Commit.Author commitAuthor = new Commit.Author(
				coercePropertyToZonedDateTime("commit.author.time"));

		commit.setAuthor(commitAuthor);

		final Commit.Committer commitCommitter = new Commit.Committer(
				coercePropertyToZonedDateTime("commit.committer.time"));

		commit.setCommitter(commitCommitter);

		final Commit.Message commitMessage = new Commit.Message(getValue("commit.message.full"),
				getValue("commit.message.short"));

		commit.setMessage(commitMessage);

		return commit;
	}

	public Closest closest(){
		final Closest closest = new Closest();

		final Closest.Tag closestTag = new Closest.Tag();
		closestTag.setName(getValue("closest.tag.name"));

		final Closest.Tag.Commit closestTagCommit = new Closest.Tag.Commit();
		final String s = getValue("closest.tag.commit.count");

		try{
			closestTagCommit.setCount(Integer.parseInt(s));
		}catch(NumberFormatException e){
			logger.warn("git.closest.tag.commit.count value: {} cloud not convert to int.", s);
		}

		closestTag.setCommit(closestTagCommit);

		closest.setTag(closestTag);

		return closest;
	}

	public Set<String> tags(){
		final String tags = getValue("tags");

		if(Validate.hasText(tags)){
			return Arrays.toSet(StringUtils.split(tags, ","));
		}else{
			return null;
		}
	}

	public Total total(){
		final Total total = new Total();

		final Total.Commit totalCommit = new Total.Commit();
		final String s = getValue("total.commit.count");

		try{
			totalCommit.setCount(Integer.parseInt(s));
		}catch(NumberFormatException e){
			logger.warn("git.total.commit.count value: {} cloud not convert to int.", s);
		}

		total.setCommit(totalCommit);

		return total;
	}

	private String getValue(final String key){
		return properties.getProperty("git." + key);
	}

	private ZonedDateTime coercePropertyToZonedDateTime(final String tag){
		final String value = getValue(tag);

		if(Validate.hasText(value)){
			final Instant instant = coerceToEpoch(value);

			if(instant == null){
				logger.warn("git.{} value: {} cloud not convert to ZonedDateTime.", tag, value);
				return null;
			}

			return instant.atZone(ZoneOffset.UTC);
		}else{
			return null;
		}
	}

	private static Instant coerceToEpoch(final String s){
		try{
			return Instant.ofEpochMilli(Long.parseLong(s) * 1000);
		}catch(NumberFormatException e){
			//
		}

		try{
			return DATE_TIME_FORMATTER.parse(s, Instant::from);
		}catch(DateTimeParseException e){
			return null;
		}
	}

}

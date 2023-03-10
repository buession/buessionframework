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
package com.buession.git;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Commit
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public final class Commit implements Info {

	private ZonedDateTime time;

	private Id id;

	private Author author;

	private Committer committer;

	/**
	 * Commit 用户
	 */
	private User user;

	/**
	 * Commit 信息
	 */
	private Message message;

	/**
	 * 构造函数
	 */
	public Commit(){
	}

	/**
	 * 构造函数
	 *
	 * @param time
	 * 		--
	 * @param id
	 * 		--
	 * @param author
	 * 		--
	 * @param committer
	 * 		--
	 * @param user
	 * 		--
	 * @param message
	 * 		--
	 */
	public Commit(ZonedDateTime time, Id id, Author author, Committer committer, User user,
				  Message message){
		this.time = time;
		this.id = id;
		this.author = author;
		this.committer = committer;
		this.user = user;
		this.message = message;
	}

	public ZonedDateTime getTime(){
		return time;
	}

	public void setTime(ZonedDateTime time){
		this.time = time;
	}

	public Id getId(){
		return id;
	}

	public void setId(Id id){
		this.id = id;
	}

	public Author getAuthor(){
		return author;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Committer getCommitter(){
		return committer;
	}

	public void setCommitter(Committer committer){
		this.committer = committer;
	}

	/**
	 * 返回 Commit 用户
	 *
	 * @return Commit 用户
	 */
	public User getUser(){
		return user;
	}

	/**
	 * 设置 Commit 用户
	 *
	 * @param user
	 * 		Commit 用户
	 */
	public void setUser(User user){
		this.user = user;
	}

	/**
	 * 返回 Commit 信息
	 *
	 * @return Commit 信息
	 */
	public Message getMessage(){
		return message;
	}

	/**
	 * 设置 Commit 信息
	 *
	 * @param message
	 * 		Commit 信息
	 */
	public void setMessage(Message message){
		this.message = message;
	}

	@Override
	public String toString(){
		final GitInfoBuilder builder = GitInfoBuilder.getInstance("commit")
				.append("time", time)
				.append("id", Optional.ofNullable(id).orElse(new Id()))
				.append("author", Optional.ofNullable(author).orElse(new Author()))
				.append("committer", Optional.ofNullable(committer).orElse(new Committer()))
				.append("user", Optional.ofNullable(user).orElse(new User()))
				.append("message", Optional.ofNullable(message).orElse(new Message()));

		return builder.build();
	}

	public final static class Id implements Info {

		private String value;

		private String abbrev;

		private String describe;

		private String describeShort;

		/**
		 * 构造函数
		 */
		public Id(){
		}

		/**
		 * 构造函数
		 *
		 * @param value
		 * 		--
		 * @param abbrev
		 * 		--
		 * @param describe
		 * 		--
		 * @param describeShort
		 * 		--
		 */
		public Id(String value, String abbrev, String describe, String describeShort){
			this.value = value;
			this.abbrev = abbrev;
			this.describe = describe;
			this.describeShort = describeShort;
		}

		public String getValue(){
			return value;
		}

		public void setValue(String value){
			this.value = value;
		}

		public String getAbbrev(){
			return abbrev;
		}

		public void setAbbrev(String abbrev){
			this.abbrev = abbrev;
		}

		public String getDescribe(){
			return describe;
		}

		public void setDescribe(String describe){
			this.describe = describe;
		}

		public String getDescribeShort(){
			return describeShort;
		}

		public void setDescribeShort(String describeShort){
			this.describeShort = describeShort;
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("commit.id")
					.append(null, value)
					.append("abbrev", abbrev)
					.append("describe", describe)
					.append("describe-short", describeShort);

			return builder.build();
		}

	}

	public final static class User extends BaseUser {

		/**
		 * 构造函数
		 */
		public User(){
			super();
		}

		/**
		 * 构造函数
		 *
		 * @param name
		 * 		用户名称
		 * @param email
		 * 		用户 E-mail
		 */
		public User(String name, String email){
			super(name, email);
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("commit.user")
					.append("name", getName())
					.append("email", getEmail());

			return builder.build();
		}

	}

	public final static class Author implements Info {

		/**
		 * 时间
		 */
		private ZonedDateTime time;

		/**
		 * 构造函数
		 */
		public Author(){
		}

		/**
		 * 构造函数
		 *
		 * @param time
		 * 		--
		 */
		public Author(ZonedDateTime time){
			this.time = time;
		}

		/**
		 * 返回时间
		 *
		 * @return 时间
		 */
		public ZonedDateTime getTime(){
			return time;
		}

		/**
		 * 设置时间
		 *
		 * @param time
		 * 		时间
		 */
		public void setTime(ZonedDateTime time){
			this.time = time;
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("commit.author")
					.append("time", time);

			return builder.build();
		}

	}

	public final static class Committer implements Info {

		/**
		 * 时间
		 */
		private ZonedDateTime time;

		/**
		 * 构造函数
		 */
		public Committer(){
		}

		/**
		 * 构造函数
		 *
		 * @param time
		 * 		--
		 */
		public Committer(ZonedDateTime time){
			this.time = time;
		}

		/**
		 * 返回时间
		 *
		 * @return 时间
		 */
		public ZonedDateTime getTime(){
			return time;
		}

		/**
		 * 设置时间
		 *
		 * @param time
		 * 		时间
		 */
		public void setTime(ZonedDateTime time){
			this.time = time;
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("commit.committer")
					.append("time", time);

			return builder.build();
		}

	}

	/**
	 * Commit 信息
	 */
	public final static class Message implements Info {

		private String full;

		@JsonProperty(value = "short")
		private String $short;

		/**
		 * 构造函数
		 */
		public Message(){
		}

		/**
		 * 构造函数
		 *
		 * @param full
		 * 		--
		 * @param $short
		 * 		--
		 */
		public Message(String full, String $short){
			this.full = full;
			this.$short = $short;
		}

		public String getShort(){
			return $short;
		}

		public void setShort(String $short){
			this.$short = $short;
		}

		public String getFull(){
			return full;
		}

		public void setFull(String full){
			this.full = full;
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("commit.message")
					.append("full", full)
					.append("short", $short);

			return builder.build();
		}

	}

}

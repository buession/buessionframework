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

import java.util.Optional;

/**
 * 统计信息
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public final class Total implements Info {

	/**
	 * Commit
	 */
	private Commit commit;

	/**
	 * 构造函数
	 */
	public Total(){
	}

	/**
	 * 构造函数
	 *
	 * @param commit
	 * 		Commit
	 */
	public Total(Commit commit){
		this.commit = commit;
	}

	/**
	 * 返回 Commit
	 *
	 * @return Commit
	 */
	public Commit getCommit(){
		return commit;
	}

	/**
	 * 设置 Commit
	 *
	 * @param commit
	 * 		Commit
	 */
	public void setCommit(Commit commit){
		this.commit = commit;
	}

	@Override
	public String toString(){
		final GitInfoBuilder builder = GitInfoBuilder.getInstance("total")
				.append("commit", Optional.ofNullable(commit).orElse(new Commit()));

		return builder.build();
	}

	/**
	 * Commit
	 */
	public final static class Commit extends BaseCommit {

		/**
		 * 构造函数
		 */
		public Commit(){
		}

		/**
		 * 构造函数
		 *
		 * @param count
		 * 		Commit 总数
		 */
		public Commit(int count){
			super(count);
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("total.commit")
					.append("count", getCount());

			return builder.build();
		}

	}

}

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
 * 本地信息
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public final class Local implements Info {

	/**
	 * 分支信息
	 */
	private Branch branch;

	/**
	 * 构造函数
	 */
	public Local(){
	}

	/**
	 * 构造函数
	 *
	 * @param branch
	 * 		分支信息
	 */
	public Local(Branch branch){
		this.branch = branch;
	}

	/**
	 * 返回分支信息
	 *
	 * @return 分支信息
	 */
	public Branch getBranch(){
		return branch;
	}

	/**
	 * 设置分支信息
	 *
	 * @param branch
	 * 		分支信息
	 */
	public void setBranch(Branch branch){
		this.branch = branch;
	}

	@Override
	public String toString(){
		final GitInfoBuilder builder = GitInfoBuilder.getInstance("local")
				.append("branch", Optional.ofNullable(branch).orElse(new Branch()));

		return builder.build();
	}

	/**
	 * 分支信息
	 */
	public final static class Branch implements Info {

		/**
		 * 落后情况
		 */
		private String ahead;

		/**
		 * 超前情况
		 */
		private String behind;

		/**
		 * 构造函数
		 */
		public Branch(){
		}

		/**
		 * 构造函数
		 *
		 * @param ahead
		 * 		落后情况
		 * @param behind
		 * 		超前情况
		 */
		public Branch(String ahead, String behind){
			this.ahead = ahead;
			this.behind = behind;
		}

		/**
		 * 返回落后情况
		 *
		 * @return 落后情况
		 */
		public String getAhead(){
			return ahead;
		}

		/**
		 * 设置落后情况
		 *
		 * @param ahead
		 * 		落后情况
		 */
		public void setAhead(String ahead){
			this.ahead = ahead;
		}

		/**
		 * 返回超前情况
		 *
		 * @return 超前情况
		 */
		public String getBehind(){
			return behind;
		}

		/**
		 * 设置超前情况
		 *
		 * @param behind
		 * 		超前情况
		 */
		public void setBehind(String behind){
			this.behind = behind;
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("local.branch")
					.append("ahead", ahead)
					.append("behind", behind);

			return builder.build();
		}

	}

}

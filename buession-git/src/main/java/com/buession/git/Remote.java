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
 * 远程信息
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public final class Remote implements Info {

	/**
	 * 原始信息
	 */
	private Origin origin;

	/**
	 * 构造函数
	 */
	public Remote(){
	}

	/**
	 * 构造函数
	 *
	 * @param origin
	 * 		原始信息
	 */
	public Remote(Origin origin){
		this.origin = origin;
	}

	/**
	 * 返回原始信息
	 *
	 * @return 原始信息
	 */
	public Origin getOrigin(){
		return origin;
	}

	/**
	 * 设置原始信息
	 *
	 * @param origin
	 * 		原始信息
	 */
	public void setOrigin(Origin origin){
		this.origin = origin;
	}

	@Override
	public String toString(){
		final GitInfoBuilder builder = GitInfoBuilder.getInstance("remote")
				.append("origin", Optional.ofNullable(origin).orElse(new Origin()));

		return builder.build();
	}

	/**
	 * 原始信息
	 */
	public final static class Origin implements Info {

		/**
		 * 远程仓库地址
		 */
		private String url;

		/**
		 * 构造函数
		 */
		public Origin(){
		}

		/**
		 * 构造函数
		 *
		 * @param url
		 * 		远程仓库地址
		 */
		public Origin(String url){
			this.url = url;
		}

		/**
		 * 返回远程仓库地址
		 *
		 * @return 远程仓库地址
		 */
		public String getUrl(){
			return url;
		}

		/**
		 * 设置远程仓库地址
		 *
		 * @param url
		 * 		远程仓库地址
		 */
		public void setUrl(String url){
			this.url = url;
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("remote.origin")
					.append("url", url);

			return builder.build();
		}

	}

}

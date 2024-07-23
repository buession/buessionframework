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
package com.buession.redis.core.command.args;

/**
 * {@code XREAD} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class XReadArgument {

	/**
	 * 返回数量
	 */
	private Integer count;

	/**
	 * 阻塞时间（单位：毫秒）
	 */
	private Integer block;

	/**
	 * 返回数量
	 *
	 * @return 返回数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置返回数量
	 *
	 * @param count
	 * 		返回数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 返回阻塞时间（单位：毫秒）
	 *
	 * @return 阻塞时间
	 */
	public Integer getBlock() {
		return block;
	}

	/**
	 * 设置阻塞时间
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 */
	public void setBlock(Integer block) {
		this.block = block;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(count != null){
			builder.add("COUNT", count);
		}

		if(block != null){
			builder.add("BLOCK", block);
		}

		return builder.build();
	}

}

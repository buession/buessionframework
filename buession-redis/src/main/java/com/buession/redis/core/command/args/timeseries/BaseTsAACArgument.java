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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args.timeseries;

import java.util.Map;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
abstract class BaseTsAACArgument {

	/**
	 * 数据的保留时间（单位：毫秒）
	 */
	private Long retention;

	/**
	 * 每个内存块的大小（单位：字节）
	 */
	private Long chunkSize;

	/**
	 * Policy that will define handling of duplicate samples.
	 */
	private DuplicatePolicy duplicatePolicy;

	/**
	 * Labels
	 */
	private Map<String, String> labels;

	/**
	 * 构造函数
	 */
	public BaseTsAACArgument() {
	}

	/**
	 * 返回数据的保留时间
	 *
	 * @return 数据的保留时间
	 */
	public Long getRetention() {
		return retention;
	}

	/**
	 * 设置数据的保留时间（单位：毫秒）
	 *
	 * @param retention
	 * 		数据的保留时间
	 *
	 * @return {@link BaseTsAACArgument}
	 */
	public BaseTsAACArgument setRetention(long retention) {
		this.retention = retention;
		return this;
	}

	/**
	 * 返回每个内存块的大小（单位：字节）
	 *
	 * @return 每个内存块的大小
	 */
	public Long getChunkSize() {
		return chunkSize;
	}

	/**
	 * 设置每个内存块的大小
	 *
	 * @param chunkSize
	 * 		每个内存块的大小（单位：字节）
	 *
	 * @return {@link BaseTsAACArgument}
	 */
	public BaseTsAACArgument setChunkSize(Long chunkSize) {
		this.chunkSize = chunkSize;
		return this;
	}

	/**
	 * Return policy that will define handling of duplicate samples.
	 *
	 * @return The policy that will define handling of duplicate samples.
	 */
	public DuplicatePolicy getDuplicatePolicy() {
		return duplicatePolicy;
	}

	/**
	 * Sets policy that will define handling of duplicate samples.
	 *
	 * @param duplicatePolicy
	 * 		Policy that will define handling of duplicate samples.
	 *
	 * @return {@link BaseTsAACArgument}
	 */
	public BaseTsAACArgument setDuplicatePolicy(DuplicatePolicy duplicatePolicy) {
		this.duplicatePolicy = duplicatePolicy;
		return this;
	}

	/**
	 * Return labels
	 *
	 * @return Labels
	 */
	public Map<String, String> getLabels() {
		return labels;
	}

	/**
	 * Sets labels
	 *
	 * @param labels
	 * 		Labels
	 *
	 * @return {@link BaseTsAACArgument}
	 */
	public BaseTsAACArgument setLabels(Map<String, String> labels) {
		this.labels = labels;
		return this;
	}

}

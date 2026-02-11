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
package com.buession.redis.core.command.args;

import com.buession.core.validator.Validate;
import com.buession.redis.core.RedisNode;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * FAILOVER 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class FailoverArgument {

	/**
	 * 目标主机地址
	 */
	private String host;

	/**
	 * 目标端口
	 */
	private Integer port;

	/**
	 * 是否强制
	 */
	private Boolean force;

	/**
	 * 是否终止
	 */
	private Boolean abort;

	/**
	 * 超时时间（单位：毫秒）
	 */
	private Long timeout;

	/**
	 * 构造函数
	 */
	public FailoverArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 */
	public FailoverArgument(final String host) {
		this.host = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param port
	 * 		目标端口
	 */
	public FailoverArgument(final String host, final Integer port) {
		this(host);
		this.port = port;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param port
	 * 		目标端口
	 * @param force
	 * 		是否强制
	 */
	public FailoverArgument(final String host, final Integer port, final Boolean force) {
		this(host, port);
		this.force = force;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param port
	 * 		目标端口
	 * @param force
	 * 		是否强制
	 * @param abort
	 * 		是否终止
	 */
	public FailoverArgument(final String host, final Integer port, final Boolean force, final Boolean abort) {
		this(host, port, force);
		this.abort = abort;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param port
	 * 		目标端口
	 * @param force
	 * 		是否强制
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final String host, final Integer port, final Boolean force, final Long timeout) {
		this(host, port, force);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param port
	 * 		目标端口
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final String host, final Integer port, final Long timeout) {
		this(host, port);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param port
	 * 		目标端口
	 * @param force
	 * 		是否强制
	 * @param abort
	 * 		是否终止
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final String host, final Integer port, final Boolean force, final Boolean abort,
							final Long timeout) {
		this(host, port, force, abort);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param force
	 * 		是否强制
	 */
	public FailoverArgument(final String host, final Boolean force) {
		this(host);
		this.force = force;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param force
	 * 		是否强制
	 * @param abort
	 * 		是否终止
	 */
	public FailoverArgument(final String host, final Boolean force, final Boolean abort) {
		this(host, force);
		this.abort = abort;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param force
	 * 		是否强制
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final String host, final Boolean force, final Long timeout) {
		this(host, force);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final String host, final Long timeout) {
		this(host);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		目标主机地址
	 * @param force
	 * 		是否强制
	 * @param abort
	 * 		是否终止
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final String host, final Boolean force, final Boolean abort, final Long timeout) {
		this(host, force, abort);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param redisNode
	 * 		目标主机地址和端口
	 * @param force
	 * 		是否强制
	 */
	public FailoverArgument(final RedisNode redisNode, final Boolean force) {
		this(redisNode.getHost(), redisNode.getPort(), force);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNode
	 * 		目标主机地址和端口
	 * @param force
	 * 		是否强制
	 * @param abort
	 * 		是否终止
	 */
	public FailoverArgument(final RedisNode redisNode, final Boolean force, final Boolean abort) {
		this(redisNode.getHost(), redisNode.getPort(), force, abort);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNode
	 * 		目标主机地址和端口
	 * @param force
	 * 		是否强制
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final RedisNode redisNode, final Boolean force, final Long timeout) {
		this(redisNode.getHost(), redisNode.getPort(), force, timeout);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNode
	 * 		目标主机地址和端口
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final RedisNode redisNode, final Long timeout) {
		this(redisNode.getHost(), redisNode.getPort(), timeout);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNode
	 * 		目标主机地址和端口
	 * @param force
	 * 		是否强制
	 * @param abort
	 * 		是否终止
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final RedisNode redisNode, final Boolean force, final Boolean abort,
							final Long timeout) {
		this(redisNode.getHost(), redisNode.getPort(), force, abort, timeout);
	}

	/**
	 * 构造函数
	 *
	 * @param abort
	 * 		是否终止
	 */
	public FailoverArgument(final Boolean abort) {
		this.abort = abort;
	}

	/**
	 * 构造函数
	 *
	 * @param abort
	 * 		是否终止
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final Boolean abort, final Long timeout) {
		this(abort);
		this.timeout = timeout;
	}

	/**
	 * 构造函数
	 *
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument(final Long timeout) {
		this.timeout = timeout;
	}

	/**
	 * 返回目标主机地址
	 *
	 * @return 目标主机地址
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 设置目标主机地址
	 *
	 * @param host
	 * 		目标主机地址
	 *
	 * @return {@link FailoverArgument}
	 */
	public FailoverArgument setHost(String host) {
		this.host = host;
		return this;
	}

	/**
	 * 返回目标端口
	 *
	 * @return 目标端口
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * 设置目标端口
	 *
	 * @param port
	 * 		目标端口
	 *
	 * @return {@link FailoverArgument}
	 */
	public FailoverArgument setPort(Integer port) {
		this.port = port;
		return this;
	}

	/**
	 * 返回是否强制
	 *
	 * @return 是否强制
	 */
	public Boolean getForce() {
		return force;
	}

	/**
	 * 设置是否强制
	 *
	 * @param force
	 * 		是否强制
	 *
	 * @return {@link FailoverArgument}
	 */
	public FailoverArgument setForce(Boolean force) {
		this.force = force;
		return this;
	}

	/**
	 * 返回是否终止
	 *
	 * @return 是否终止
	 */
	public Boolean getAbort() {
		return abort;
	}

	/**
	 * 设置是否终止
	 *
	 * @param abort
	 * 		是否终止
	 *
	 * @return {@link FailoverArgument}
	 */
	public FailoverArgument setAbort(Boolean abort) {
		this.abort = abort;
		return this;
	}

	/**
	 * 返回超时时间
	 *
	 * @return 超时时间（单位：毫秒）
	 */
	public Long getTimeout() {
		return timeout;
	}

	/**
	 * 设置超时时间
	 *
	 * @param timeout
	 * 		超时（单位：毫秒）
	 */
	public FailoverArgument setTimeout(Long timeout) {
		this.timeout = timeout;
		return this;
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create();

		if(Validate.isNotEmpty(host)){
			builder.append("TO").append(host).append(port);
			builder.append(Boolean.TRUE.equals(force) ? "FORCE" : null);
		}

		builder.append(Boolean.TRUE.equals(abort) ? "ABORT" : null);
		if(timeout != null){
			builder.append("TIMEOUT").append(timeout);
		}

		return builder.toString();
	}

}

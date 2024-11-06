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
package com.buession.httpclient.conn.nio;

/**
 * 异步 I/O 线程配置
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class IOReactorConfig implements Cloneable {

	public final static IOReactorConfig DEFAULT = new IOReactorConfig();

	/**
	 * 连接超时（单位：毫秒）
	 */
	private Integer connectTimeout;

	/**
	 * I/O 操作超时时间（单位：毫秒）
	 */
	private Integer soTimeout;

	/**
	 * 选择器的轮询间隔时间（单位：毫秒）
	 */
	private Long selectInterval;

	/**
	 * 优雅关闭等待时长（单位：毫秒）
	 */
	private Long shutdownGracePeriod;

	/**
	 * 是否启用兴趣操作队列
	 */
	private Boolean interestOpQueued;

	/**
	 * I/O 线程数量
	 */
	private Integer ioThreadCount;

	/**
	 * 是否启用 SO_REUSEADDR 套接字选项
	 */
	private Boolean soReuseAddress;

	/**
	 * SO_LINGER
	 */
	private Integer soLinger;

	/**
	 * 是否启用 TCP 的 keep-alive 套接字选项
	 */
	private Boolean soKeepAlive;

	/**
	 * 是否启用 TCP_NODELAY 选项
	 */
	private Boolean tcpNoDelay;

	/**
	 * 发送缓冲区大小（单位：字节）
	 */
	private Integer sndBufSize;

	/**
	 * 接收缓冲区大小（单位：字节）
	 */
	private Integer rcvBufSize;

	/**
	 * 服务端套接字的连接请求队列的大小
	 */
	private Integer backlogSize;

	/**
	 * 构造函数
	 */
	public IOReactorConfig() {
	}

	/**
	 * 构造函数
	 *
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		I/O 操作超时时间（单位：毫秒）
	 * @param selectInterval
	 * 		选择器的轮询间隔时间（单位：毫秒）
	 * @param shutdownGracePeriod
	 * 		优雅关闭等待时长（单位：毫秒）
	 * @param interestOpQueued
	 * 		是否启用兴趣操作队列
	 * @param ioThreadCount
	 * 		I/O 线程数量
	 * @param soReuseAddress
	 * 		是否启用 SO_REUSEADDR 套接字选项
	 * @param soLinger
	 * 		SO_LINGER
	 * @param soKeepAlive
	 * 		是否启用 TCP 的 keep-alive 套接字选项
	 * @param tcpNoDelay
	 * 		是否启用 TCP_NODELAY 选项
	 * @param sndBufSize
	 * 		发送缓冲区大小（单位：字节）
	 * @param rcvBufSize
	 * 		接收缓冲区大小（单位：字节）
	 * @param backlogSize
	 * 		服务端套接字的连接请求队列的大小
	 */
	public IOReactorConfig(Integer connectTimeout, Integer soTimeout, Long selectInterval, Long shutdownGracePeriod,
						   Boolean interestOpQueued, Integer ioThreadCount, Boolean soReuseAddress, Integer soLinger,
						   Boolean soKeepAlive, Boolean tcpNoDelay, Integer sndBufSize, Integer rcvBufSize,
						   Integer backlogSize) {
		this.connectTimeout = connectTimeout;
		this.soTimeout = soTimeout;
		this.selectInterval = selectInterval;
		this.shutdownGracePeriod = shutdownGracePeriod;
		this.interestOpQueued = interestOpQueued;
		this.ioThreadCount = ioThreadCount;
		this.soReuseAddress = soReuseAddress;
		this.soLinger = soLinger;
		this.soKeepAlive = soKeepAlive;
		this.tcpNoDelay = tcpNoDelay;
		this.sndBufSize = sndBufSize;
		this.rcvBufSize = rcvBufSize;
		this.backlogSize = backlogSize;
	}

	/**
	 * 构造函数
	 *
	 * @param selectInterval
	 * 		选择器的轮询间隔时间（单位：毫秒）
	 * @param shutdownGracePeriod
	 * 		优雅关闭等待时长（单位：毫秒）
	 * @param interestOpQueued
	 * 		是否启用兴趣操作队列
	 * @param ioThreadCount
	 * 		I/O 线程数量
	 * @param soTimeout
	 * 		I/O 操作超时时间（单位：毫秒）
	 * @param soReuseAddress
	 * 		是否启用 SO_REUSEADDR 套接字选项
	 * @param soLinger
	 * 		SO_LINGER
	 * @param soKeepAlive
	 * 		是否启用 TCP 的 keep-alive 套接字选项
	 * @param tcpNoDelay
	 * 		是否启用 TCP_NODELAY 选项
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param sndBufSize
	 * 		发送缓冲区大小（单位：字节）
	 * @param rcvBufSize
	 * 		接收缓冲区大小（单位：字节）
	 * @param backlogSize
	 * 		服务端套接字的连接请求队列的大小
	 */
	@Deprecated
	public IOReactorConfig(Long selectInterval, Long shutdownGracePeriod, Boolean interestOpQueued,
						   Integer ioThreadCount, Integer soTimeout, Boolean soReuseAddress, Integer soLinger,
						   Boolean soKeepAlive, Boolean tcpNoDelay, Integer connectTimeout, Integer sndBufSize,
						   Integer rcvBufSize, Integer backlogSize) {
		this(connectTimeout, soTimeout, selectInterval, shutdownGracePeriod, interestOpQueued, ioThreadCount,
				soReuseAddress, soLinger, soKeepAlive, tcpNoDelay, sndBufSize, rcvBufSize, backlogSize);
	}

	/**
	 * 返回连接超时（单位：毫秒）
	 *
	 * @return 连接超时
	 */
	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * 设置连接超时（单位：毫秒）
	 *
	 * @param connectTimeout
	 * 		连接超时
	 */
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * 返回 I/O 操作超时时间（单位：毫秒）
	 *
	 * @return I/O 操作超时时间
	 */
	public Integer getSoTimeout() {
		return soTimeout;
	}

	/**
	 * 设置 I/O 操作超时时间（单位：毫秒）
	 *
	 * @param soTimeout
	 * 		I/O 操作超时时间
	 */
	public void setSoTimeout(Integer soTimeout) {
		this.soTimeout = soTimeout;
	}

	/**
	 * 返回选择器的轮询间隔时间（单位：毫秒）
	 *
	 * @return 选择器的轮询间隔时间
	 */
	public Long getSelectInterval() {
		return selectInterval;
	}

	/**
	 * 设置选择器的轮询间隔时间（单位：毫秒）
	 *
	 * @param selectInterval
	 * 		选择器的轮询间隔时间
	 */
	public void setSelectInterval(Long selectInterval) {
		this.selectInterval = selectInterval;
	}

	/**
	 * 返回优雅关闭等待时长（单位：毫秒）
	 *
	 * @return 优雅关闭等待时长
	 */
	public Long getShutdownGracePeriod() {
		return shutdownGracePeriod;
	}

	/**
	 * 设置优雅关闭等待时长（单位：毫秒）
	 *
	 * @param shutdownGracePeriod
	 * 		优雅关闭等待时长
	 */
	public void setShutdownGracePeriod(Long shutdownGracePeriod) {
		this.shutdownGracePeriod = shutdownGracePeriod;
	}

	/**
	 * 返回是否启用兴趣操作队列
	 *
	 * @return true / false
	 */
	public Boolean isInterestOpQueued() {
		return getInterestOpQueued();
	}

	/**
	 * 返回是否启用兴趣操作队列
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean getInterestOpQueued() {
		return interestOpQueued;
	}

	/**
	 * 设置是否启用兴趣操作队列
	 *
	 * @param interestOpQueued
	 * 		true / false
	 */
	public void setInterestOpQueued(Boolean interestOpQueued) {
		this.interestOpQueued = interestOpQueued;
	}

	/**
	 * 返回 I/O 线程数量
	 *
	 * @return I/O 线程数量
	 */
	public Integer getIoThreadCount() {
		return ioThreadCount;
	}

	/**
	 * 设置 I/O 线程数量
	 *
	 * @param ioThreadCount
	 * 		I/O 线程数量
	 */
	public void setIoThreadCount(Integer ioThreadCount) {
		this.ioThreadCount = ioThreadCount;
	}

	/**
	 * 返回是否启用 SO_REUSEADDR 套接字选项
	 *
	 * @return true / false
	 */
	public Boolean isSoReuseAddress() {
		return getSoReuseAddress();
	}

	/**
	 * 返回是否启用 SO_REUSEADDR 套接字选项
	 *
	 * @return true / false
	 */
	public Boolean getSoReuseAddress() {
		return soReuseAddress;
	}

	/**
	 * 设置是否启用 SO_REUSEADDR 套接字选项
	 *
	 * @param soReuseAddress
	 * 		true / false
	 */
	public void setSoReuseAddress(Boolean soReuseAddress) {
		this.soReuseAddress = soReuseAddress;
	}

	/**
	 * 返回 SO_LINGER 值
	 *
	 * @return SO_LINGER 值
	 */
	public Integer getSoLinger() {
		return soLinger;
	}

	/**
	 * 设置 SO_LINGER 值
	 *
	 * @param soLinger
	 * 		SO_LINGER 值
	 */
	public void setSoLinger(Integer soLinger) {
		this.soLinger = soLinger;
	}

	/**
	 * 返回是否启用 TCP 的 keep-alive 套接字选项
	 *
	 * @return true / false
	 *
	 * @see #isSoKeepAlive()
	 */
	@Deprecated
	public Boolean isSoKeepalive() {
		return getSoKeepAlive();
	}

	/**
	 * 返回是否启用 TCP 的 keep-alive 套接字选项
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean isSoKeepAlive() {
		return getSoKeepAlive();
	}

	/**
	 * 返回是否启用 TCP 的 keep-alive 套接字选项
	 *
	 * @return true / false
	 *
	 * @since 3.0
	 */
	public Boolean getSoKeepAlive() {
		return soKeepAlive;
	}

	/**
	 * 设置是否启用 TCP 的 keep-alive 套接字选项
	 *
	 * @param soKeepAlive
	 * 		true / false
	 *
	 * @see #setSoKeepAlive(Boolean)
	 */
	@Deprecated
	public void setSoKeepalive(Boolean soKeepAlive) {
		setSoKeepAlive(soKeepAlive);
	}

	/**
	 * 设置是否启用 TCP 的 keep-alive 套接字选项
	 *
	 * @param soKeepAlive
	 * 		true / false
	 *
	 * @since 3.0.0
	 */
	public void setSoKeepAlive(Boolean soKeepAlive) {
		this.soKeepAlive = soKeepAlive;
	}

	/**
	 * 返回是否启用 TCP_NODELAY 选项
	 *
	 * @return true / false
	 */
	public Boolean isTcpNoDelay() {
		return getTcpNoDelay();
	}

	/**
	 * 返回是否启用 TCP_NODELAY 选项
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean getTcpNoDelay() {
		return tcpNoDelay;
	}

	/**
	 * 设置是否启用 TCP_NODELAY 选项
	 *
	 * @param tcpNoDelay
	 * 		true / false
	 */
	public void setTcpNoDelay(Boolean tcpNoDelay) {
		this.tcpNoDelay = tcpNoDelay;
	}

	/**
	 * 返回发送缓冲区大小（单位：字节）
	 *
	 * @return 发送缓冲区大小
	 */
	public Integer getSndBufSize() {
		return sndBufSize;
	}

	/**
	 * 设置发送缓冲区大小（单位：字节）
	 *
	 * @param sndBufSize
	 * 		发送缓冲区大小（单位：字节）
	 */
	public void setSndBufSize(Integer sndBufSize) {
		this.sndBufSize = sndBufSize;
	}

	/**
	 * 返回接收缓冲区大小（单位：字节）
	 *
	 * @return 接收缓冲区大小（单位：字节）
	 */
	public Integer getRcvBufSize() {
		return rcvBufSize;
	}

	/**
	 * 设置接收缓冲区大小（单位：字节）
	 *
	 * @param rcvBufSize
	 * 		接收缓冲区大小（单位：字节）
	 */
	public void setRcvBufSize(Integer rcvBufSize) {
		this.rcvBufSize = rcvBufSize;
	}

	/**
	 * 返回服务端套接字的连接请求队列的大小
	 *
	 * @return 服务端套接字的连接请求队列的大小
	 */
	public Integer getBacklogSize() {
		return backlogSize;
	}

	/**
	 * 设置服务端套接字的连接请求队列的大小
	 *
	 * @param backlogSize
	 * 		服务端套接字的连接请求队列的大小
	 */
	public void setBacklogSize(Integer backlogSize) {
		this.backlogSize = backlogSize;
	}

}

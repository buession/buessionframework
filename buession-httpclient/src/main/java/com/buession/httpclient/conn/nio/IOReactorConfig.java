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
package com.buession.httpclient.conn.nio;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class IOReactorConfig implements Cloneable {

	public final static IOReactorConfig DEFAULT = new IOReactorConfig();

	private Long selectInterval;

	private Long shutdownGracePeriod;

	private Boolean interestOpQueued;

	private Integer ioThreadCount;

	private Integer soTimeout;

	private Boolean soReuseAddress;

	private Integer soLinger;

	private Boolean soKeepAlive;

	private Boolean tcpNoDelay;

	private Integer connectTimeout;

	private Integer sndBufSize;

	private Integer rcvBufSize;

	private Integer backlogSize;

	public IOReactorConfig(){
	}

	public IOReactorConfig(Long selectInterval, Long shutdownGracePeriod, Boolean interestOpQueued,
						   Integer ioThreadCount, Integer soTimeout, Boolean soReuseAddress, Integer soLinger,
						   Boolean soKeepAlive, Boolean tcpNoDelay, Integer connectTimeout, Integer sndBufSize,
						   Integer rcvBufSize, Integer backlogSize){
		this.selectInterval = selectInterval;
		this.shutdownGracePeriod = shutdownGracePeriod;
		this.interestOpQueued = interestOpQueued;
		this.ioThreadCount = ioThreadCount;
		this.soTimeout = soTimeout;
		this.soReuseAddress = soReuseAddress;
		this.soLinger = soLinger;
		this.soKeepAlive = soKeepAlive;
		this.tcpNoDelay = tcpNoDelay;
		this.connectTimeout = connectTimeout;
		this.sndBufSize = sndBufSize;
		this.rcvBufSize = rcvBufSize;
		this.backlogSize = backlogSize;
	}

	public Long getSelectInterval(){
		return selectInterval;
	}

	public void setSelectInterval(Long selectInterval){
		this.selectInterval = selectInterval;
	}

	public Long getShutdownGracePeriod(){
		return shutdownGracePeriod;
	}

	public void setShutdownGracePeriod(Long shutdownGracePeriod){
		this.shutdownGracePeriod = shutdownGracePeriod;
	}

	public Boolean isInterestOpQueued(){
		return interestOpQueued;
	}

	public void setInterestOpQueued(Boolean interestOpQueued){
		this.interestOpQueued = interestOpQueued;
	}

	public Integer getIoThreadCount(){
		return ioThreadCount;
	}

	public void setIoThreadCount(Integer ioThreadCount){
		this.ioThreadCount = ioThreadCount;
	}

	public Integer getSoTimeout(){
		return soTimeout;
	}

	public void setSoTimeout(Integer soTimeout){
		this.soTimeout = soTimeout;
	}

	public Boolean isSoReuseAddress(){
		return soReuseAddress;
	}

	public void setSoReuseAddress(Boolean soReuseAddress){
		this.soReuseAddress = soReuseAddress;
	}

	public Integer getSoLinger(){
		return soLinger;
	}

	public void setSoLinger(Integer soLinger){
		this.soLinger = soLinger;
	}

	public Boolean isSoKeepalive(){
		return soKeepAlive;
	}

	public void setSoKeepalive(Boolean soKeepAlive){
		this.soKeepAlive = soKeepAlive;
	}

	public Boolean isTcpNoDelay(){
		return tcpNoDelay;
	}

	public void setTcpNoDelay(Boolean tcpNoDelay){
		this.tcpNoDelay = tcpNoDelay;
	}

	public Integer getConnectTimeout(){
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	public Integer getSndBufSize(){
		return sndBufSize;
	}

	public void setSndBufSize(Integer sndBufSize){
		this.sndBufSize = sndBufSize;
	}

	public Integer getRcvBufSize(){
		return rcvBufSize;
	}

	public void setRcvBufSize(Integer rcvBufSize){
		this.rcvBufSize = rcvBufSize;
	}

	public Integer getBacklogSize(){
		return backlogSize;
	}

	@Override
	protected IOReactorConfig clone() throws CloneNotSupportedException{
		return (IOReactorConfig) super.clone();
	}

}

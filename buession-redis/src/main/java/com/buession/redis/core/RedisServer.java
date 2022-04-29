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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.core.utils.Assert;
import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RedisServer extends RedisNode {

	private final static long serialVersionUID = 4843502163987630437L;

	/**
	 * Redis 服务器主机 IP 地址
	 */
	private final String ip;

	/**
	 * Redis 服务器信息
	 */
	private Properties properties;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 */
	public RedisServer(final String host){
		super(host);
		this.ip = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 */
	public RedisServer(final String host, final int port){
		super(host, port);
		this.ip = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param role
	 * 		节点角色
	 */
	public RedisServer(final String host, final Role role){
		super(host, role);
		this.ip = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 * @param role
	 * 		节点角色
	 */
	public RedisServer(final String host, final int port, final Role role){
		super(host, port, role);
		this.ip = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param properties
	 * 		Redis 服务器信息
	 */
	public RedisServer(final String host, final Properties properties){
		this(host);
		this.properties = properties;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 * @param properties
	 * 		Redis 服务器信息
	 */
	public RedisServer(final String host, final int port, final Properties properties){
		this(host, port);
		this.properties = properties;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param role
	 * 		节点角色
	 * @param properties
	 * 		Redis 服务器信息
	 */
	public RedisServer(final String host, final Role role, final Properties properties){
		this(host, role);
		this.properties = properties;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 * @param role
	 * 		节点角色
	 * @param properties
	 * 		Redis 服务器信息
	 */
	public RedisServer(final String host, final int port, final Role role, final Properties properties){
		this(host, port, role);
		this.properties = properties;
	}

	/**
	 * 获取 Redis 服务器主机 IP 地址
	 *
	 * @return Redis 服务器主机 IP 地址
	 */
	public String getIp(){
		return ip;
	}

	/**
	 * 返回 Redis 服务器信息
	 *
	 * @return Redis 服务器信息
	 */
	public Properties getProperties(){
		return properties;
	}

	public Long getQuorum(){
		return getLongValueOf(Key.QUORUM);
	}

	public String getRunId(){
		return get(Key.RUNID);
	}

	public Long getNumSlaves(){
		return getLongValueOf(Key.NUMBER_SLAVES);
	}

	public Long getNumOtherSentinels(){
		return getLongValueOf(Key.NUMBER_OTHER_SENTINELS);
	}

	public Long getParallelSyncs(){
		return getLongValueOf(Key.PARALLEL_SYNCS);
	}

	public Long getConfigEpoch(){
		return getLongValueOf(Key.CONFIG_EPOCH);
	}

	public Long getInfoRefresh(){
		return getLongValueOf(Key.INFO_REFRESH);
	}

	public Role getRoleReported(){
		String roleName = get(Key.ROLE_REPORTED);
		return Validate.hasText(roleName) ? EnumUtils.getEnum(Role.class, roleName) : null;
	}

	public Long getRoleReportedTime(){
		return getLongValueOf(Key.ROLE_REPORTED_TIME);
	}

	public Long getLastPingSent(){
		return getLongValueOf(Key.LAST_PING_SENT);
	}

	public Long getLastPingReply(){
		return getLongValueOf(Key.LAST_PING_REPLY);
	}

	public Long getLastOkPingReply(){
		return getLongValueOf(Key.LAST_OK_PING_REPLY);
	}

	public Long getLinkPendingCommands(){
		return getLongValueOf(Key.LINK_PENDING_COMMANDS);
	}

	public Long getFailoverTimeout(){
		return getLongValueOf(Key.FAILOVER_TIMEOUT);
	}

	public Long getLinkRefcount(){
		return getLongValueOf(Key.LINK_REFCOUNT);
	}

	public Long getODownTime(){
		return getLongValueOf(Key.O_DOWN_TIME);
	}

	public Long getSDownTime(){
		return getLongValueOf(Key.S_DOWN_TIME);
	}

	public Long getDownAfterMilliseconds(){
		return getLongValueOf(Key.DOWN_AFTER_MILLISECONDS);
	}

	public Set<String> getFlags(){
		String value = get(Key.FLAGS);

		if(value == null){
			return null;
		}else if(value.length() == 0){
			return Collections.emptySet();
		}else{
			return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(StringUtils.split(value, ','))));
		}
	}

	@Override
	public String asString(){
		final StringBuilder sb = new StringBuilder();

		if(getId() != null){
			sb.append(getId()).append(" ");
		}

		sb.append(getHost()).append(':').append(getPort());

		return sb.toString();
	}

	public String get(final Key key){
		Assert.isNull(key, "Cannot retrieve client information for 'null'.");
		return properties.getProperty(key.getKey());
	}

	private Long getLongValueOf(final Key key){
		String value = get(key);
		return value == null ? null : Long.valueOf(value);
	}

	public enum Key {

		NAME("name"),

		HOST("ip"),

		PORT("port"),

		RUNID("runid"),

		FLAGS("flags"),

		PENDING_COMMANDS("pending-commands"),

		LINK_PENDING_COMMANDS("link-pending-commands"),

		LAST_PING_SENT("last-ping-sent"),

		LAST_PING_REPLY("last-ping-reply"),

		LAST_OK_PING_REPLY("last-ok-ping-reply"),

		DOWN_AFTER_MILLISECONDS("down-after-milliseconds"),

		INFO_REFRESH("info-refresh"),

		ROLE_REPORTED("role-reported"),

		ROLE_REPORTED_TIME("role-reported-time"),

		CONFIG_EPOCH("config-epoch"),

		NUMBER_SLAVES("num-slaves"),

		NUMBER_OTHER_SENTINELS("num-other-sentinels"),

		BUFFER_LENGTH("qbuf"),

		BUFFER_FREE_SPACE("qbuf-free"),

		OUTPUT_BUFFER_LENGTH("obl"),

		OUTPUT_LIST_LENGTH("number-other-sentinels"),

		QUORUM("quorum"),

		FAILOVER_TIMEOUT("failover-timeout"),

		PARALLEL_SYNCS("parallel-syncs"),

		LINK_REFCOUNT("link-refcount"),

		O_DOWN_TIME("o-down-time"),

		S_DOWN_TIME("s-down-time");

		private final String key;

		Key(final String key){
			this.key = key;
		}

		public String getKey(){
			return key;
		}

	}

}

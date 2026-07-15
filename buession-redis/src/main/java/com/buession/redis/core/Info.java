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
package com.buession.redis.core;

import com.buession.lang.Constants;
import com.buession.redis.utils.ObjectStringBuilder;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Redis 服务器的各种信息和统计数值
 *
 * @param server
 * 		Redis 服务器的信息
 * @param clients
 * 		已连接客户端的信息
 * @param memory
 * 		Redis 服务器的内存信息
 * @param persistence
 * 		RDB 持久化和 AOF 持久化有关的信息
 * @param threads
 * 		线程信息
 * @param stats
 * 		一般统计信息
 * @param replication
 * 		主从复制信息
 * @param cpu
 * 		CPU 的计算量统计信息
 * @param commandStats
 * 		命令统计信息
 * @param latencyStats
 * 		延迟统计信息
 * @param sentinel
 * 		Sentinel 信息
 * @param cluster
 * 		集群有关的信息
 * @param modules
 * 		模块信息
 * @param keyspace
 * 		数据库相关的统计信息
 * @param keySizes
 * 		Key 大小信息
 * @param errorStats
 * 		错误统计信息
 * @param hotKeys
 * 		热点 Key
 *
 * @author Yong.Teng
 */
public record Info(Server server, Clients clients, Memory memory, Persistence persistence, Threads threads, Stats stats,
                   Replication replication, Cpu cpu, CommandStats commandStats, LatencyStats latencyStats,
                   Sentinel sentinel, Cluster cluster, Modules modules, Keyspace keyspace, KeySizes keySizes,
                   ErrorStats errorStats, HotKeys hotKeys) {

	public String toPrettyString() {
		final StringBuilder sb = new StringBuilder();
		int index = 0;

		index = addGroup(sb, "Server", server, index);
		index = addGroup(sb, "Clients", clients, index);
		index = addGroup(sb, "Memory", memory, index);
		index = addGroup(sb, "Persistence", persistence, index);
		index = addGroup(sb, "Threads", threads, index);
		index = addGroup(sb, "Stats", stats, index);
		index = addGroup(sb, "Replication", replication, index);
		index = addGroup(sb, "Cpu", cpu, index);
		index = addGroup(sb, "Commandstats", commandStats, index);
		index = addGroup(sb, "Latencystats", latencyStats, index);
		index = addGroup(sb, "Sentinel", sentinel, index);
		index = addGroup(sb, "Cluster", cluster, index);
		index = addGroup(sb, "Modules", modules, index);
		index = addGroup(sb, "Keyspace", keyspace, index);
		index = addGroup(sb, "Keysizes", keySizes, index);
		index = addGroup(sb, "Errorstats", errorStats, index);
		index = addGroup(sb, "Hotkeys", hotKeys, index);

		return sb.toString();
	}

	@Override
	public String toString() {
		final ObjectStringBuilder builder = ObjectStringBuilder.create();

		addGroup(builder, "Server", server);
		addGroup(builder, "Clients", clients);
		addGroup(builder, "Memory", memory);
		addGroup(builder, "Persistence", persistence);
		addGroup(builder, "Threads", threads);
		addGroup(builder, "Stats", stats);
		addGroup(builder, "Replication", replication);
		addGroup(builder, "Cpu", cpu);
		addGroup(builder, "Commandstats", commandStats);
		addGroup(builder, "Latencystats", latencyStats);
		addGroup(builder, "Sentinel", sentinel);
		addGroup(builder, "Cluster", cluster);
		addGroup(builder, "Modules", modules);
		addGroup(builder, "Keyspace", keyspace);
		addGroup(builder, "Keysizes", keySizes);
		addGroup(builder, "Errorstats", errorStats);
		addGroup(builder, "Hotkeys", hotKeys);

		return builder.build();
	}

	private static int addGroup(final StringBuilder builder, final String groupName, final BaseInfo info,
	                            int index) {
		if(info != null){
			if(index > 0){
				builder.append(System.lineSeparator()).append(System.lineSeparator());
			}
			builder.append("# ").append(groupName).append(System.lineSeparator()).append(info.toPrettyString());
			index++;
		}
		return index;
	}

	private static void addGroup(final ObjectStringBuilder builder, final String groupName, final BaseInfo info) {
		builder.add(groupName, info);
	}

	private static String toPrettyString(final Properties properties) {
		int max = properties.size() - 1;
		if(max == -1){
			return Constants.EMPTY_STRING;
		}

		final StringBuilder sb = new StringBuilder();

		Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
		for(int i = 0; ; i++){
			Map.Entry<Object, Object> element = iterator.next();
			Object key = element.getKey();
			Object value = element.getValue();

			if(value != null){
				sb.append(key).append(':').append(value);
			}

			if(i == max){
				return sb.toString();
			}

			sb.append(System.lineSeparator());
		}
	}

	private static String toString(final Properties properties) {
		int max = properties.size() - 1;
		if(max == -1){
			return Constants.EMPTY_STRING;
		}

		final StringBuilder sb = new StringBuilder();

		Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
		for(int i = 0; ; i++){
			Map.Entry<Object, Object> element = iterator.next();
			Object key = element.getKey();
			Object value = element.getValue();

			if(value != null){
				sb.append(key).append('=').append(value);
			}

			if(i == max){
				return sb.toString();
			}

			sb.append(", ");
		}
	}

	public enum Section implements Keyword {

		SERVER,

		CLIENTS,

		MEMORY,

		PERSISTENCE,

		THREADS,

		STATS,

		REPLICATION,

		CPU,

		COMMAND_STATS,

		LATENCY_STATS,

		SENTINEL,

		CLUSTER,

		MODULES,

		KEYSPACE,

		KEY_SIZES,

		ERROR_STATS,

		HOT_KEYS;

		@Override
		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

	private static abstract class BaseInfo extends Properties {

		protected final Properties properties;

		/**
		 * 构造函数
		 *
		 * @param properties
		 * 		属性
		 */
		public BaseInfo(final Properties properties) {
			this.properties = properties;
		}

		public String toPrettyString() {
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString() {
			return Info.toString(properties);
		}

	}

	/**
	 * Redis 服务器的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Server extends BaseInfo {

		public Server(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 已连接客户端的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Clients extends BaseInfo {

		public Clients(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * Redis 服务器的内存信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Memory extends BaseInfo {

		public Memory(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * RDB 持久化和 AOF 持久化有关的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Persistence extends BaseInfo {

		public Persistence(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 线程信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Threads extends BaseInfo {

		public Threads(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 一般统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Stats extends BaseInfo {

		public Stats(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 主从复制信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Replication extends BaseInfo {

		public Replication(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * CPU 的计算量统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Cpu extends BaseInfo {

		public Cpu(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 命令统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class CommandStats extends BaseInfo {

		public CommandStats(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 延迟统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class LatencyStats extends BaseInfo {

		public LatencyStats(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 哨兵信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Sentinel extends BaseInfo {

		public Sentinel(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 集群有关的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Cluster extends BaseInfo {

		public Cluster(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 模块信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Modules extends BaseInfo {

		public Modules(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 数据库相关的统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Keyspace extends BaseInfo {

		public Keyspace(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 键大小信息
	 *
	 * @author Yong.Teng
	 */
	public final static class KeySizes extends BaseInfo {

		public KeySizes(final Properties properties) {
			super(properties);
		}

	}

	/**
	 * 错误统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class ErrorStats extends BaseInfo {

		public ErrorStats(final Properties properties) {
			super(properties);
		}

	}

	/***
	 * 热键信息
	 *
	 * @author Yong.Teng
	 */
	public final static class HotKeys extends BaseInfo {

		public HotKeys(final Properties properties) {
			super(properties);
		}

	}

}

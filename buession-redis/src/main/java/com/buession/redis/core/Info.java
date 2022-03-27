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

import com.buession.core.utils.PropertiesUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;
import com.buession.net.Multiplexing;
import com.buession.lang.Status;
import com.buession.lang.Uptime;
import com.buession.net.HostAndPort;
import com.buession.redis.utils.InfoUtil;
import org.apache.commons.lang3.arch.Processor;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Redis 服务器的各种信息和统计数值
 *
 * @author Yong.Teng
 */
public class Info implements Serializable {

	private final static long serialVersionUID = -2772690110674245981L;

	/**
	 * Redis 服务器的信息
	 */
	private final Server server;

	/**
	 * 已连接客户端的信息
	 */
	private final Clients clients;

	/**
	 * Redis 服务器的内存信息
	 */
	private final Memory memory;

	/**
	 * RDB 持久化和 AOF 持久化有关的信息
	 */
	private final Persistence persistence;

	/**
	 * 一般统计信息
	 */
	private final Stats stats;

	/**
	 * 主从复制信息
	 */
	private final Replication replication;

	/**
	 * Sentinel 信息
	 */
	private final Sentinel sentinel;

	/**
	 * CPU 的计算量统计信息
	 */
	private final Cpu cpu;

	/**
	 * 集群有关的信息
	 */
	private final Cluster cluster;

	/**
	 * 数据库相关的统计信息
	 */
	private final List<Keyspace> keyspace;

	public Info(final Server server, final Clients clients, final Memory memory, final Persistence persistence,
				final Stats stats, final Replication replication, final Sentinel sentinel, final Cpu cpu,
				final Cluster cluster, final List<Keyspace> keyspaces){
		this.server = server;
		this.clients = clients;
		this.memory = memory;
		this.persistence = persistence;
		this.stats = stats;
		this.replication = replication;
		this.sentinel = sentinel;
		this.cpu = cpu;
		this.cluster = cluster;
		this.keyspace = keyspaces;
	}

	/**
	 * 获取 Redis 服务器的信息
	 *
	 * @return Redis 服务器的信息
	 */
	public Server getServer(){
		return server;
	}

	/**
	 * 获取已连接客户端的信息
	 *
	 * @return 已连接客户端的信息
	 */
	public Clients getClients(){
		return clients;
	}

	/**
	 * 获取 Redis 服务器的内存信息
	 *
	 * @return Redis 服务器的内存信息
	 */
	public Memory getMemory(){
		return memory;
	}

	/**
	 * 获取 RDB 持久化和 AOF 持久化有关的信息
	 *
	 * @return RDB 持久化和 AOF 持久化有关的信息
	 */
	public Persistence getPersistence(){
		return persistence;
	}

	/**
	 * 获取集群有关的信息
	 *
	 * @return 集群有关的信息
	 */
	public Cluster getCluster(){
		return cluster;
	}

	/**
	 * 获取一般统计信息
	 *
	 * @return 一般统计信息
	 */
	public Stats getStats(){
		return stats;
	}

	/**
	 * 获取主从复制信息
	 *
	 * @return 主从复制信息
	 */
	public Replication getReplication(){
		return replication;
	}

	/**
	 * 获取 Sentinel 信息
	 *
	 * @return Sentinel 信息
	 */
	public Sentinel getSentinel(){
		return sentinel;
	}

	/**
	 * 获取 CPU 的计算量统计信息
	 *
	 * @return CPU 的计算量统计信息
	 */
	public Cpu getCpu(){
		return cpu;
	}

	/**
	 * 获取数据库相关的统计信息
	 *
	 * @return 数据库相关的统计信息
	 */
	public List<Keyspace> getKeyspace(){
		return keyspace;
	}

	public String toPrettyString(){
		return InfoUtil.toPrettyString(server, clients, memory, persistence, stats, replication, sentinel, cpu, cluster,
				keyspace);
	}

	@Override
	public String toString(){
		return InfoUtil.asString(server, clients, memory, persistence, stats, replication, sentinel, cpu, cluster,
				keyspace);
	}

	protected static Double getPercent(final Properties properties, final String key){
		String str = properties.getProperty(key);
		return Validate.hasText(str) ? Double.parseDouble(str.substring(0, str.length() - 1)) : null;
	}

	protected static Boolean getBoolean(final Properties properties, final String key){
		String str = properties.getProperty(key);

		if(Validate.hasText(str) == false){
			return null;
		}

		if(Boolean.parseBoolean(str)){
			return true;
		}

		try{
			return Integer.parseInt(str) != 0;
		}catch(Exception e){
			return false;
		}
	}

	protected static Date getDate(final Properties properties, final String key, final boolean isUnixTimestamp){
		String str = properties.getProperty(key);

		if(Validate.hasText(str) == false){
			return null;
		}

		long timestamp = Long.parseLong(str);

		if(isUnixTimestamp){
			timestamp = timestamp * 1000;
		}

		return new Date(timestamp);
	}

	@SuppressWarnings("unchecked")
	protected static <E> E getObject(final Properties properties, final String key){
		return (E) properties.get(key);
	}

	protected static String toPrettyString(final Properties properties){
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

			sb.append(System.lineSeparator());
		}
	}

	protected static String toString(final Properties properties){
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

	public enum Section {

		SERVER,

		CLIENTS,

		MEMORY,

		PERSISTENCE,

		STATS,

		CPU,

		REPLICATION,

		CLUSTER,

		COMMAND_STATS,

		KEYSPACE

	}

	/**
	 * Redis 服务器的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Server implements Serializable {

		private final static long serialVersionUID = 2729543496870054983L;

		private final Properties properties;

		public Server(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取 Redis 架构
		 *
		 * @return Redis 架构
		 */
		public Processor.Arch getArch(){
			return getObject(properties, Key.ARCH.value);
		}

		/**
		 * 获取 Redis atomicvar API
		 *
		 * @return Redis atomicvar API
		 */
		public AtomicvarApi getAtomicvarApi(){
			return getObject(properties, Key.ATOMICVAR_API.value);
		}

		/**
		 * 获取 Redis build id
		 *
		 * @return Redis build id
		 */
		public String getBuildId(){
			return PropertiesUtils.get(properties, Key.BUILD_ID.value);
		}

		public Integer getConfiguredHz(){
			return PropertiesUtils.getInt(properties, Key.CONFIGURED_HZ.value);
		}

		/**
		 * 获取 Redis 可执行文件路径
		 *
		 * @return Redis 可执行文件路径
		 */
		public String getExecutable(){
			return PropertiesUtils.get(properties, Key.EXECUTABLE.value);
		}

		/**
		 * 获取 Redis 配置文件路径
		 *
		 * @return Redis 配置文件路径
		 */
		public String getConfigFile(){
			return PropertiesUtils.get(properties, Key.CONFIG_FILE.value);
		}

		/**
		 * 获取编译 Redis 时所使用的 GCC 版本
		 *
		 * @return 编译 Redis 时所使用的 GCC 版本
		 */
		public String getGccVersion(){
			return PropertiesUtils.get(properties, Key.GCC_VESION.value);
		}

		/**
		 * 获取 Redis Git dirty flag
		 *
		 * @return Redis Git dirty flag
		 */
		public String getGitDirty(){
			return PropertiesUtils.get(properties, Key.GIT_DIRTY.value);
		}

		/**
		 * 获取 Redis Git SHA1
		 *
		 * @return Redis Git SHA1
		 */
		public String getGitSha1(){
			return PropertiesUtils.get(properties, Key.GIT_SHA1.value);
		}

		/**
		 * 获取 Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
		 *
		 * @return Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
		 */
		public Integer getHz(){
			return PropertiesUtils.getInt(properties, Key.HZ.value);
		}

		/**
		 * 获取以分钟为单位进行自增的时钟，用于 LRU 管理
		 *
		 * @return 以分钟为单位进行自增的时钟，用于 LRU 管理
		 */
		public Integer getLruClock(){
			return PropertiesUtils.getInt(properties, Key.LRU_CLOCK.value);
		}

		/**
		 * 获取 Redis 运行模式
		 *
		 * @return Redis 运行模式
		 */
		public RedisMode getMode(){
			return getObject(properties, Key.MODE.value);
		}

		/**
		 * 获取 Redis 所使用的事件处理机制
		 *
		 * @return Redis 所使用的事件处理机制
		 */
		public Multiplexing getMultiplexingApi(){
			return getObject(properties, Key.MULTIPLEXING_API.value);
		}

		/**
		 * 获取 Redis 服务器的宿主操作系统
		 *
		 * @return Redis 服务器的宿主操作系统
		 */
		public String getOs(){
			return PropertiesUtils.get(properties, Key.OS.value);
		}

		/**
		 * 获取 Redis 服务器主机端口
		 *
		 * @return Redis 服务器主机端口
		 */
		public Integer getPort(){
			return PropertiesUtils.getInt(properties, Key.PORT.value);
		}

		/**
		 * 获取服务器进程的 PID
		 *
		 * @return 服务器进程的 PID
		 */
		public Integer getProcessId(){
			return PropertiesUtils.getInt(properties, Key.PROCESS_ID.value);
		}

		/**
		 * 获取 Redis 服务器的随机标识符（用于 Sentinel 和集群）
		 *
		 * @return Redis 服务器的随机标识符（用于 Sentinel 和集群）
		 */
		public String getRunId(){
			return PropertiesUtils.get(properties, Key.RUN_ID.value);
		}

		/**
		 * 获取自 Redis 服务器启动以来的运行时间
		 *
		 * @return 自 Redis 服务器启动以来的运行时间
		 */
		public Uptime getUptime(){
			return getObject(properties, Key.UPTIME.value);
		}

		/**
		 * 获取 Redis 服务器版本
		 *
		 * @return Redis 服务器版本
		 */
		public String getVersion(){
			return PropertiesUtils.get(properties, Key.VERSION.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			ARCH("arch_bits"),

			ATOMICVAR_API("atomicvar_api"),

			BUILD_ID("redis_build_id"),

			CONFIGURED_HZ("configured_hz"),

			CONFIG_FILE("config_file"),

			EXECUTABLE("executable"),

			GCC_VESION("gcc_version"),

			GIT_DIRTY("redis_git_dirty"),

			GIT_SHA1("redis_git_sha1"),

			HZ("hz"),

			LRU_CLOCK("lru_clock"),

			MODE("redis_mode"),

			MULTIPLEXING_API("multiplexing_api"),

			OS("os"),

			PORT("tcp_port"),

			PROCESS_ID("process_id"),

			RUN_ID("run_id"),

			UPTIME("uptime"),

			VERSION("redis_version");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * 已连接客户端的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Clients implements Serializable {

		private final static long serialVersionUID = 7746131798680346372L;

		private final Properties properties;

		public Clients(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取当前连接的客户端当中，最大输入缓存
		 *
		 * @return 当前连接的客户端当中，最大输入缓存
		 */
		public Integer getBiggestInputBuffer(){
			return PropertiesUtils.getInt(properties, Key.BIGGES_TINPUT_BUFFER.value);
		}

		/**
		 * 获取正在等待阻塞命令的客户端的数量
		 *
		 * @return 正在等待阻塞命令的客户端的数量
		 */
		public Integer getBlockeds(){
			return PropertiesUtils.getInt(properties, Key.BLOCKEDS.value);
		}

		/**
		 * 获取已连接客户端的数量
		 *
		 * @return 已连接客户端的数量
		 */
		public Integer getConnecteds(){
			return PropertiesUtils.getInt(properties, Key.CONNECTEDS.value);
		}

		/**
		 * 获取当前连接的客户端中最大输入
		 *
		 * @return 当前连接的客户端中最大输入
		 */
		public Integer getClientRecentMaxInputBuffer(){
			return PropertiesUtils.getInt(properties, Key.CLIENT_RECENT_MAX_INPUT_BUFFER.value);
		}

		/**
		 * 获取当前连接的客户端中最大输出
		 *
		 * @return 当前连接的客户端中最大输出
		 */
		public Integer getClientRecentMaxOutputBuffer(){
			return PropertiesUtils.getInt(properties, Key.CLIENT_RECENT_MAX_OUTPUT_BUFFER.value);
		}

		/**
		 * 获取当前连接的客户端当中，最长的输出列表
		 *
		 * @return 当前连接的客户端当中，最长的输出列表
		 */
		public Integer getLongestOutputList(){
			return PropertiesUtils.getInt(properties, Key.LONGEST_OUTPUT_LIST.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			BIGGES_TINPUT_BUFFER("client_biggest_input_buf"),

			BLOCKEDS("blocked_clients"),

			CONNECTEDS("connected_clients"),

			CLIENT_RECENT_MAX_INPUT_BUFFER("client_recent_max_input_buffer"),

			CLIENT_RECENT_MAX_OUTPUT_BUFFER("client_recent_max_output_buffer"),

			LONGEST_OUTPUT_LIST("client_longest_output_list");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * Redis 服务器的内存信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Memory implements Serializable {

		private final static long serialVersionUID = 1058962019266560745L;

		private final Properties properties;

		public Memory(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 检测内存整理是否处于活动状态
		 *
		 * @return 内存整理是否处于活动状态
		 */
		public Boolean isActiveDefragRunning(){
			return getActiveDefragRunning();
		}

		/**
		 * 检测内存整理是否处于活动状态
		 *
		 * @return 内存整理是否处于活动状态
		 */
		public Boolean getActiveDefragRunning(){
			return getBoolean(properties, Key.ACTIVE_DEFRAG_RUNNING.value);
		}

		/**
		 * 返回 Redis 中分配器活跃内存数(单位：byte)
		 *
		 * @return Redis 中分配器活跃内存数
		 */
		public Long getAllocatorActive(){
			return PropertiesUtils.getLong(properties, Key.ALLOCATOR_ACTIVE.value);
		}

		/**
		 * 返回 Redis 中分配器分配内存数(单位：byte)
		 *
		 * @return Redis 中分配器分配内存数
		 */
		public Long getAllocatorAllocated(){
			return PropertiesUtils.getLong(properties, Key.ALLOCATOR_ALLOCATED.value);
		}

		/**
		 * 返回分配器内存碎片大小(单位：byte)
		 *
		 * @return 分配器内存碎片大小
		 */
		public Long getAllocatorFragBytes(){
			return PropertiesUtils.getLong(properties, Key.ALLOCATOR_FRAG_BYTES.value);
		}

		/**
		 * 返回分配器碎片率
		 *
		 * @return 分配器碎片率
		 */
		public Double getAllocatorFragRatio(){
			return PropertiesUtils.getDouble(properties, Key.ALLOCATOR_FRAG_RATIO.value);
		}

		/**
		 * 返回 Redis 中分配器常驻内存数(单位：byte)
		 *
		 * @return Redis 中分配器常驻内存数
		 */
		public Long getAllocatorResident(){
			return PropertiesUtils.getLong(properties, Key.ALLOCATOR_RESIDENT.value);
		}

		/**
		 * 返回分配器常驻内存大小(单位：byte)
		 *
		 * @return 分配器常驻内存大小
		 */
		public Long getAllocatorRssBytes(){
			return PropertiesUtils.getLong(properties, Key.ALLOCATOR_RSS_BYTES.value);
		}

		/**
		 * 返回分配器常驻内存比例
		 *
		 * @return 分配器常驻内存比例
		 */
		public Double getAllocatorRssRatio(){
			return PropertiesUtils.getDouble(properties, Key.ALLOCATOR_RSS_RATIO.value);
		}

		/**
		 * 返回等待释放对象数，此值只会在使用 ASYNC 选项并调用 UNLINK 或 FLUSHDB 和 FLUSHALL 时存在
		 *
		 * @return 等待释放对象数
		 */
		public Integer getLazyfreePendingObjects(){
			return PropertiesUtils.getInt(properties, Key.LAZYFREE_PENDING_OBJECTS.value);
		}

		/**
		 * 返回 Redis 中可分配的最大内存数，默认为0不限制
		 *
		 * @return Redis 中可分配的最大内存数
		 */
		public Long getMaxMemory(){
			return PropertiesUtils.getLong(properties, Key.MAX_MEMORY.value);
		}

		/**
		 * 返回 Redis 中可分配的最大内存数的可读信息
		 *
		 * @return Redis 中可分配的最大内存数的可读信息
		 */
		public String getMaxMemoryHuman(){
			return PropertiesUtils.get(properties, Key.MAX_MEMORY_HUMAN.value);
		}

		/**
		 * 返回达到 Redis 最大内存数 maxmemory 后的处理策略
		 *
		 * @return 达到 Redis 最大内存数 maxmemory 后的处理策略
		 */
		public MaxMemoryPolicy getMaxMemoryPolicy(){
			return getObject(properties, Key.MAX_MEMORY_POLICY.value);
		}

		/**
		 * 返回 Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
		 *
		 * @return Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
		 */
		public String getMemAllocator(){
			return PropertiesUtils.get(properties, Key.MEM_ALLOCATOR.value);
		}

		/**
		 * 返回 Redis 使用 aof 持久化方式中 aof_buffer 缓冲区大小(单位：byte)
		 *
		 * @return Redis 使用 ao f持久化方式中 aof_buffer 缓冲区大小
		 */
		public Long getMemAofBuffer(){
			return PropertiesUtils.getLong(properties, Key.MEM_AOF_BUFFER.value);
		}

		public Integer getMemClientsNormal(){
			return PropertiesUtils.getInt(properties, Key.MEM_CLIENTS_NORMAL.value);
		}

		public Integer getMemClientsSlaves(){
			return PropertiesUtils.getInt(properties, Key.MEM_CLIENTS_SLAVES.value);
		}

		/**
		 * Redis 中内存碎片大小(单位：byte)
		 *
		 * @return Redis 中内存碎片大小
		 */
		public Long getMemFragmentationBytes(){
			return PropertiesUtils.getLong(properties, Key.MEM_FRAGMENTATION_BYTES.value);
		}

		/**
		 * 返回 Redis 中内存碎片率，此值为 (used_memory_rss/used_memory)*100%
		 *
		 * @return Redis 中内存碎片率
		 */
		public Double getMemFragmentationRatio(){
			return PropertiesUtils.getDouble(properties, Key.MEM_FRAGMENTATION_RATIO.value);
		}

		/**
		 * 返回被驱逐的内存大小
		 *
		 * @return 被驱逐的内存大小
		 */
		public Long getMemNotCountedForEvict(){
			return PropertiesUtils.getLong(properties, Key.MEM_NOT_COUNTED_FOR_EVICT.value);
		}

		/**
		 * 返回从服务其中 backlog 内存大小
		 *
		 * @return 从服务其中 backlog 内存大小
		 */
		public Long getMemReplicationBacklog(){
			return PropertiesUtils.getLong(properties, Key.MEM_REPLICATION_BACKLOG.value);
		}

		public Integer getNumberOfCachedScripts(){
			return PropertiesUtils.getInt(properties, Key.NUMBER_OF_CACHED_SCRIPTS.value);
		}

		/**
		 * 返回常驻内存开销比例
		 *
		 * @return 常驻内存开销比例
		 */
		public Double getRssOverheadRatio(){
			return PropertiesUtils.getDouble(properties, Key.RSS_OVERHEAD_RATIO.value);
		}

		/**
		 * 返回常驻内存开销大小(单位：byte)
		 *
		 * @return 常驻内存开销大小
		 */
		public Long getRssOverheadBytes(){
			return PropertiesUtils.getLong(properties, Key.RSS_OVERHEAD_BYTES.value);
		}

		/**
		 * 返回系统内存总数
		 *
		 * @return 系统内存总数
		 */
		public Long getTotalSystemMemory(){
			return PropertiesUtils.getLong(properties, Key.TOTAL_SYSTEM_MEMORY.value);
		}

		/**
		 * 返回系统内存总数的可读信息(单位：byte)
		 *
		 * @return 系统内存总数的可读信息
		 */
		public String getTotalSystemMemoryHuman(){
			return PropertiesUtils.get(properties, Key.TOTAL_SYSTEM_MEMORY_HUMAN.value);
		}

		/**
		 * 返回由 Redis 分配器分配的内存总量，包括使用的虚拟内存(单位：byte)
		 *
		 * @return 由 Redis 分配器分配的内存总量，包括使用的虚拟内存
		 */
		public Long getUsedMemory(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY.value);
		}

		/**
		 * 返回 Redis 中数据集所占内存数据大小，即：used_memory - used_memory_overhead(单位：byte)
		 *
		 * @return Redis 中数据集所占内存数据大小
		 */
		public Long getUsedMemoryDataset(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_DATASET.value);
		}

		/**
		 * used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比，即：
		 * (used_memory_dataset/(used_memory—used_memory_startup)) * 100%
		 *
		 * @return used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比
		 */
		public Double getUsedMemoryDatasetPerc(){
			return getPercent(properties, Key.USED_MEMORY_DATASET_PERC.value);
		}

		/**
		 * 由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
		 *
		 * @return 由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
		 */
		public String getUsedMemoryHuman(){
			return PropertiesUtils.get(properties, Key.USED_MEMORY_HUMAN.value);
		}

		/**
		 * 返回 lua 引擎使用内存量(单位：byte)
		 *
		 * @return lua 引擎使用内存量
		 */
		public Long getUsedMemoryLua(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_LUA.value);
		}

		/**
		 * 返回 lua 引擎使用内存量的可读信息
		 *
		 * @return lua 引擎使用内存量的可读信息
		 */
		public String getUsedMemoryLuaHuman(){
			return PropertiesUtils.get(properties, Key.USED_MEMORY_LUA_HUMAN.value);
		}

		/**
		 * 返回 Redis 维护整个内存数据集可用内部机制所需要的内存开销。
		 * 包括维护内部数据结构，所有客户端输出缓冲区，AOF 重写缓冲区，查询缓冲区，AOF 写入缓冲区和主从复制的 backlog(单位：byte)
		 *
		 * @return Redis 维护整个内存数据集可用内部机制所需要的内存开销
		 */
		public Long getUsedMemoryOverhead(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_OVERHEAD.value);
		}

		/**
		 * 返回 Redis 的内存消耗峰值，即：Redis 内存使用的最大值(单位：byte)
		 *
		 * @return Redis 的内存消耗峰值，即：Redis 内存使用的最大值
		 */
		public Long getUsedMemoryPeak(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_PEAK.value);
		}

		/**
		 * 返回 Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
		 *
		 * @return Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
		 */
		public String getUsedMemoryPeakHuman(){
			return PropertiesUtils.get(properties, Key.USED_MEMORY_PEAK_HUMAN.value);
		}

		/**
		 * 返回 used_memory_peak 在 used_memory 中所占的百分比
		 *
		 * @return used_memory_peak 在 used_memory 中所占的百分比
		 */
		public Double getUsedMemoryPeakPerc(){
			return getPercent(properties, Key.USED_MEMORY_PEAK_PERC.value);
		}

		/**
		 * 获取从操作系统角度返回 Redis 使用内存数，与 Linux 中 top，ps 命令返回数据一致
		 * 除了分配器分配的内存之外，used_memory_rss 还包括进程运行本身需要的内存、内存碎片等，但是不包括虚拟内存(单位：byte)
		 *
		 * @return Redis 使用内存数
		 */
		public Long getUsedMemoryRss(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_RSS.value);
		}

		/**
		 * 返回 Redis 使用内存数的可读信息
		 *
		 * @return Redis 使用内存数的可读信息
		 */
		public String getUsedMemoryRssHuman(){
			return PropertiesUtils.get(properties, Key.USED_MEMORY_RSS_HUMAN.value);
		}

		public Long getUsedMemoryScripts(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_SCRIPTS.value);
		}

		public String getUsedMemoryScriptsHuman(){
			return PropertiesUtils.get(properties, Key.USED_MEMORY_SCRIPTS_HUMAN.value);
		}

		/**
		 * 返回 Redis 消耗的初始内存值
		 *
		 * @return Redis 消耗的初始内存值
		 */
		public Long getUsedMemoryStartup(){
			return PropertiesUtils.getLong(properties, Key.USED_MEMORY_STARTUP.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			ACTIVE_DEFRAG_RUNNING("active_defrag_running"),

			ALLOCATOR_ACTIVE("allocator_active"),

			ALLOCATOR_ALLOCATED("allocator_allocated"),

			ALLOCATOR_FRAG_BYTES("allocator_frag_bytes"),

			ALLOCATOR_FRAG_RATIO("allocator_frag_ratio"),

			ALLOCATOR_RESIDENT("allocator_resident"),

			ALLOCATOR_RSS_BYTES("allocator_rss_bytes"),

			ALLOCATOR_RSS_RATIO("allocator_rss_ratio"),

			LAZYFREE_PENDING_OBJECTS("lazyfree_pending_objects"),

			MAX_MEMORY("maxmemory"),

			MAX_MEMORY_HUMAN("maxmemory_human"),

			MAX_MEMORY_POLICY("maxmemory_policy"),

			MEM_ALLOCATOR("mem_allocator"),

			MEM_AOF_BUFFER("mem_aof_buffer"),

			MEM_CLIENTS_NORMAL("mem_clients_normal"),

			MEM_CLIENTS_SLAVES("mem_clients_slaves"),

			MEM_FRAGMENTATION_BYTES("mem_fragmentation_bytes"),

			MEM_FRAGMENTATION_RATIO("mem_fragmentation_ratio"),

			MEM_NOT_COUNTED_FOR_EVICT("mem_not_counted_for_evict"),

			MEM_REPLICATION_BACKLOG("mem_replication_backlog"),

			NUMBER_OF_CACHED_SCRIPTS("number_of_cached_scripts"),

			RSS_OVERHEAD_BYTES("rss_overhead_bytes"),

			RSS_OVERHEAD_RATIO("rss_overhead_ratio"),

			TOTAL_SYSTEM_MEMORY("total_system_memory"),

			TOTAL_SYSTEM_MEMORY_HUMAN("total_system_memory_human"),

			USED_MEMORY("used_memory"),

			USED_MEMORY_DATASET("used_memory_dataset"),

			USED_MEMORY_DATASET_PERC("used_memory_dataset_perc"),

			USED_MEMORY_HUMAN("used_memory_human"),

			USED_MEMORY_LUA("used_memory_lua"),

			USED_MEMORY_LUA_HUMAN("used_memory_lua_human"),

			USED_MEMORY_OVERHEAD("used_memory_overhead"),

			USED_MEMORY_PEAK("used_memory_peak"),

			USED_MEMORY_PEAK_HUMAN("used_memory_peak_human"),

			USED_MEMORY_PEAK_PERC("used_memory_peak_perc"),

			USED_MEMORY_RSS("used_memory_rss"),

			USED_MEMORY_RSS_HUMAN("used_memory_rss_human"),

			USED_MEMORY_SCRIPTS("used_memory_scripts"),

			USED_MEMORY_SCRIPTS_HUMAN("used_memory_scripts_human"),

			USED_MEMORY_STARTUP("used_memory_startup");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * RDB 持久化和 AOF 持久化有关的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Persistence implements Serializable {

		private final static long serialVersionUID = 643244503594125194L;

		private final Properties properties;

		public Persistence(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
		 *
		 * @return 服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
		 */
		public Long getAofBaseSize(){
			return PropertiesUtils.getLong(properties, Key.AOF_BASE_SIZE.value);
		}

		/**
		 * 获取AOF 缓冲区的大小
		 *
		 * @return AOF 缓冲区的大小
		 */
		public Integer getAofBufferLength(){
			return PropertiesUtils.getInt(properties, Key.AOF_BUFFER_LENGTH.value);
		}

		/**
		 * 获取如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
		 *
		 * @return 如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
		 */
		public Integer getAofCurrentRewriteTimeSec(){
			return PropertiesUtils.getInt(properties, Key.AOF_CURRENT_REWRITE_TIME_SEC.value);
		}

		/**
		 * 获取AOF 文件目前的大小
		 *
		 * @return AOF 文件目前的大小
		 */
		public Long getAofCurrentSize(){
			return PropertiesUtils.getLong(properties, Key.AOF_CURRENT_SIZE.value);
		}

		/**
		 * 获取被延迟的 fsync 调用数量
		 *
		 * @return 被延迟的 fsync 调用数量
		 */
		public Integer getAofDelayedFsync(){
			return PropertiesUtils.getInt(properties, Key.AOF_DELAYED_FSYNC.value);
		}

		/**
		 * 获取AOF 是否处于打开状态
		 *
		 * @return AOF 是否处于打开状态
		 */
		public Boolean isAofEnabled(){
			return getAofEnabled();
		}

		/**
		 * 获取AOF 是否处于打开状态
		 *
		 * @return AOF 是否处于打开状态
		 */
		public Boolean getAofEnabled(){
			return getBoolean(properties, Key.AOF_ENABLED.value);
		}

		/**
		 * 获取后台 I/O 队列里面，等待执行的 fsync 调用数量
		 *
		 * @return 后台 I/O 队列里面，等待执行的 fsync 调用数量
		 */
		public Integer getAofPendingBioFsync(){
			return PropertiesUtils.getInt(properties, Key.AOF_PENDING_BIO_FSYNC.value);
		}

		/**
		 * 获取是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
		 *
		 * @return 是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
		 */
		public Boolean isAofPendingRewrite(){
			return getAofPendingRewrite();
		}

		/**
		 * 获取是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
		 *
		 * @return 是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
		 */
		public Boolean getAofPendingRewrite(){
			return getBoolean(properties, Key.AOF_PENDING_REWRITE.value);
		}

		/**
		 * 获取AOF 重写缓冲区的大小
		 *
		 * @return AOF 重写缓冲区的大小
		 */
		public Integer getAofRewriteBufferLength(){
			return PropertiesUtils.getInt(properties, Key.AOF_REWRITE_BUFFER_LENGTH.value);
		}

		/**
		 * 返回最后一次 bgrewriteaof 重写是否成功
		 *
		 * @return 最后一次 bgrewriteaof 重写是否成功
		 */
		public Status getAofLastBgRewriteStatus(){
			return getObject(properties, Key.AOF_LAST_BGREWRITE_STATUS.value);
		}

		/**
		 * 返回最后一次执行 AOF 重写操作期间 copy-on-write 分配的字节大小
		 *
		 * @return 最后一次执行 AOF 重写操作期间 copy-on-write 分配的字节大小
		 */
		public Long getAofLastCowSize(){
			return PropertiesUtils.getLong(properties, Key.AOF_LAST_COW_SIZE.value);
		}

		/**
		 * 获取最近一次创建 AOF 文件耗费的时长
		 *
		 * @return 最近一次创建 AOF 文件耗费的时长
		 */
		public Integer getAofLastRewriteTimeSec(){
			return PropertiesUtils.getInt(properties, Key.AOF_LAST_REWRITE_TIME_SEC.value);
		}

		/**
		 * 返回最后一次 AOF 重写是否成功
		 *
		 * @return 最后一次 AOF 重写是否成功
		 */
		public Status getAofLastWriteStatus(){
			return getObject(properties, Key.AOF_LAST_WRITE_STATUS.value);
		}

		/**
		 * 获取服务器是否正在创建 AOF 文件
		 *
		 * @return 服务器是否正在创建 AOF 文件
		 */
		public Boolean isAofRewriteInProgress(){
			return getAofRewriteInProgress();
		}

		/**
		 * 获取服务器是否正在创建 AOF 文件
		 *
		 * @return 服务器是否正在创建 AOF 文件
		 */
		public Boolean getAofRewriteInProgress(){
			return getBoolean(properties, Key.AOF_REWRITE_IN_PROGRESS.value);
		}

		/**
		 * 获取在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
		 *
		 * @return 在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
		 */
		public Boolean isAofRewriteScheduled(){
			return getAofRewriteScheduled();
		}

		/**
		 * 获取在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
		 *
		 * @return 在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
		 */
		public Boolean getAofRewriteScheduled(){
			return getBoolean(properties, Key.AOF_REWRITE_SCHEDULED.value);
		}

		/**
		 * 获取服务器是否正在载入持久化文件
		 *
		 * @return 服务器是否正在载入持久化文件
		 */
		public Boolean isLoading(){
			return getLoading();
		}

		/**
		 * 获取服务器是否正在载入持久化文件
		 *
		 * @return 服务器是否正在载入持久化文件
		 */
		public Boolean getLoading(){
			return getBoolean(properties, Key.LOADING.value);
		}

		/**
		 * 获取服务器是否正在创建 RDB 文件
		 *
		 * @return 服务器是否正在创建 RDB 文件
		 */
		public Boolean isRdbBgSaveInProgress(){
			return getRdbBgSaveInProgress();
		}

		/**
		 * 获取服务器是否正在创建 RDB 文件
		 *
		 * @return 服务器是否正在创建 RDB 文件
		 */
		public Boolean getRdbBgSaveInProgress(){
			return getBoolean(properties, Key.RDB_BGSAVE_IN_PROGRESS.value);
		}

		/**
		 * 获取距离最近一次成功创建持久化文件之后，经过了多少秒
		 *
		 * @return 距离最近一次成功创建持久化文件之后，经过了多少秒
		 */
		public Integer getRdbChangesSinceLastSave(){
			return PropertiesUtils.getInt(properties, Key.RDB_CHANGES_SINCE_LAST_SAVE.value);
		}

		/**
		 * 获取如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
		 *
		 * @return 如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
		 */
		public Integer getRdbCurrentBgSaveTimeSec(){
			return PropertiesUtils.getInt(properties, Key.RDB_CURRENT_BGSAVE_TIME_SEC.value);
		}

		/**
		 * 获取最近一次创建 RDB 文件的结果是成功还是失败
		 *
		 * @return 最近一次创建 RDB 文件的结果是成功还是失败
		 */
		public Status getRdbLastBgSaveStatus(){
			return getObject(properties, Key.RDB_LAST_BGSAVE_STATUS.value);
		}

		/**
		 * 获取最近一次创建 RDB 文件耗费的秒数
		 *
		 * @return 最近一次创建 RDB 文件耗费的秒数
		 */
		public Integer getRdbLastBgSaveTimeSec(){
			return PropertiesUtils.getInt(properties, Key.RDB_LAST_BGSAVE_TIME_SEC.value);
		}

		/**
		 * 最后一次执行 rdb 操作期间 copy-on-write 分配的字节大小(单位：byte)
		 *
		 * @return 最后一次执行 rdb 操作期间 copy-on-write 分配的字节大小
		 */
		public Long getRdbLastCowSize(){
			return PropertiesUtils.getLong(properties, Key.RDB_LAST_COW_SIZE.value);
		}

		/**
		 * 获取最近一次成功创建 RDB 文件的时间
		 *
		 * @return 最近一次成功创建 RDB 文件的时间
		 */
		public Date getRdbLastSaveTime(){
			return getDate(properties, Key.RDB_LAST_SAVE_TIME.getValue(), true);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			AOF_BASE_SIZE("aof_base_size"),

			AOF_BUFFER_LENGTH("aof_buffer_length"),

			AOF_CURRENT_REWRITE_TIME_SEC("aof_current_rewrite_time_sec"),

			AOF_CURRENT_SIZE("aof_current_size"),

			AOF_DELAYED_FSYNC("aof_delayed_fsync"),

			AOF_ENABLED("aof_enabled"),

			AOF_LAST_BGREWRITE_STATUS("aof_last_bgrewrite_status"),

			AOF_LAST_COW_SIZE("aof_last_cow_size"),

			AOF_LAST_REWRITE_TIME_SEC("aof_last_rewrite_time_sec"),

			AOF_LAST_WRITE_STATUS("aof_last_write_status"),

			AOF_PENDING_BIO_FSYNC("aof_pending_bio_fsync"),

			AOF_PENDING_REWRITE("aof_pending_rewrite"),

			AOF_REWRITE_BUFFER_LENGTH("aof_rewrite_buffer_length"),

			AOF_REWRITE_IN_PROGRESS("aof_rewrite_in_progress"),

			AOF_REWRITE_SCHEDULED("aof_rewrite_scheduled"),

			LOADING("loading"),

			RDB_BGSAVE_IN_PROGRESS("rdb_bgsave_in_progress"),

			RDB_CHANGES_SINCE_LAST_SAVE("rdb_changes_since_last_save"),

			RDB_CURRENT_BGSAVE_TIME_SEC("rdb_current_bgsave_time_sec"),

			RDB_LAST_BGSAVE_STATUS("rdb_last_bgsave_status"),

			RDB_LAST_BGSAVE_TIME_SEC("rdb_last_bgsave_time_sec"),

			RDB_LAST_COW_SIZE("rdb_last_cow_size"),

			RDB_LAST_SAVE_TIME("rdb_last_save_time");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * 一般统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Stats implements Serializable {

		private final static long serialVersionUID = 668423701291623945L;

		private final Properties properties;

		public Stats(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 返回主动垃圾碎片整理命中次数
		 *
		 * @return 主动垃圾碎片整理命中次数
		 */
		public Integer getActiveDefragHits(){
			return PropertiesUtils.getInt(properties, Key.ACTIVE_DEFRAG_HITS.value);
		}

		/**
		 * 返回主动垃圾碎片整理 key 命中次数
		 *
		 * @return 主动垃圾碎片整理 key 命中次数
		 */
		public Integer getActiveDefragKeyHits(){
			return PropertiesUtils.getInt(properties, Key.ACTIVE_DEFRAG_KEY_HITS.value);
		}

		/**
		 * 返回主动垃圾碎片整理 key 未命中次数
		 *
		 * @return 主动垃圾碎片整理 key 未命中次数
		 */
		public Integer getActiveDefragKeyMisses(){
			return PropertiesUtils.getInt(properties, Key.ACTIVE_DEFRAG_KEY_MISSES.value);
		}

		/**
		 * 返回主动垃圾碎片整理未命中
		 *
		 * @return 主动垃圾碎片整理未命中
		 */
		public Integer getActiveDefragMisses(){
			return PropertiesUtils.getInt(properties, Key.ACTIVE_DEFRAG_MISSES.value);
		}

		/**
		 * 获取因为过期而被自动删除的数据库键数量
		 *
		 * @return 因为过期而被自动删除的数据库键数量
		 */
		public Integer getExpiredKeys(){
			return PropertiesUtils.getInt(properties, Key.EXPIRED_KEYS.value);
		}

		/**
		 * 获取过期 key 占总 key 比率
		 *
		 * @return 过期 key 占总 key 比率
		 */
		public Double getExpiredStalePerc(){
			return getPercent(properties, Key.EXPIRED_STALE_PERC.value);
		}

		/**
		 * 返回过期计数
		 *
		 * @return 过期计数
		 */
		public Integer getExpiredTimeCapReachedCount(){
			return PropertiesUtils.getInt(properties, Key.EXPIRED_TIME_CAP_REACHED_COUNT.value);
		}

		/**
		 * 获取因为最大内存容量限制而被驱逐（evict）的键数量
		 *
		 * @return 因为最大内存容量限制而被驱逐（evict）的键数量
		 */
		public Integer getEvictedKeys(){
			return PropertiesUtils.getInt(properties, Key.EVICTED_KEYS.value);
		}

		/**
		 * 返回输入带宽，Redis 服务每秒读字节数（kbps）
		 *
		 * @return 输入带宽，Redis 服务每秒读字节数
		 */
		public Integer getInstantaneousInputKbps(){
			return PropertiesUtils.getInt(properties, Key.INSTANTANEOUS_INPUT.value);
		}

		/**
		 * 获取服务器每秒钟执行的命令数量
		 *
		 * @return 服务器每秒钟执行的命令数量
		 */
		public Integer getInstantaneousOpsPerSec(){
			return PropertiesUtils.getInt(properties, Key.INSTANTANEOUS_INPUT.value);
		}

		/**
		 * 返回输出带宽，Redis 服务每秒写字节数（kbps）
		 *
		 * @return 输出带宽，Redis 服务每秒写字节数
		 */
		public Integer getInstantaneousOutput(){
			return PropertiesUtils.getInt(properties, Key.INSTANTANEOUS_OUTPUT.value);
		}

		/**
		 * 获取查找数据库键成功的次数
		 *
		 * @return 查找数据库键成功的次数
		 */
		public Integer getKeyspaceHits(){
			return PropertiesUtils.getInt(properties, Key.KEYSPACE_HITS.value);
		}

		/**
		 * 获取查找数据库键失败的次数
		 *
		 * @return 查找数据库键失败的次数
		 */
		public Integer getKeyspaceMisses(){
			return PropertiesUtils.getInt(properties, Key.KEYSPACE_MISSES.value);
		}

		/**
		 * 获取 最近一次 fork() 操作耗费的毫秒数
		 *
		 * @return 最近一次 fork() 操作耗费的毫秒数
		 */
		public Integer getLatestForkUsec(){
			return PropertiesUtils.getInt(properties, Key.LATEST_FORK_USEC.value);
		}

		public Integer getMigrateCachedSockets(){
			return PropertiesUtils.getInt(properties, Key.MIGRATE_CACHED_SOCKETS.value);
		}

		/**
		 * 获取目前被订阅的频道数量
		 *
		 * @return 目前被订阅的频道数量
		 */
		public Integer getPubsubChannels(){
			return PropertiesUtils.getInt(properties, Key.PUBSUB_CHANNELS.value);
		}

		/**
		 * 获取目前被订阅的模式数量
		 *
		 * @return 目前被订阅的模式数量
		 */
		public Integer getPubsubPatterns(){
			return PropertiesUtils.getInt(properties, Key.PUBSUB_PATTERNS.value);
		}

		/**
		 * 获取因为最大客户端数量限制而被拒绝的连接请求数量
		 *
		 * @return 因为最大客户端数量限制而被拒绝的连接请求数量
		 */
		public Integer getRejectedConnections(){
			return PropertiesUtils.getInt(properties, Key.REJECTED_CONNECTIONS.value);
		}

		/**
		 * 返回从服务器中到期 key 数量
		 *
		 * @return 从服务器中到期 key 数量
		 */
		public Integer getSlaveExpiresTrackedKeys(){
			return PropertiesUtils.getInt(properties, Key.SLAVE_EXPIRES_TRACKED_KEYS.value);
		}

		/**
		 * 返回主从之间数据同步完全成功次数
		 *
		 * @return 主从之间数据同步完全成功次数
		 */
		public Integer getSyncFull(){
			return PropertiesUtils.getInt(properties, Key.SYNC_FULL.value);
		}

		/**
		 * 返回主从之间数据同步部分失败次数
		 *
		 * @return 主从之间数据同步部分失败次数
		 */
		public Integer getSyncPartialErr(){
			return PropertiesUtils.getInt(properties, Key.SYNC_PARTIAL_ERR.value);
		}

		/**
		 * 返回主从之间数据同步部分成功次数
		 *
		 * @return 主从之间数据同步部分成功次数
		 */
		public Integer getSyncPartialOk(){
			return PropertiesUtils.getInt(properties, Key.SYNC_PARTIAL_OK.value);
		}

		/**
		 * 获取服务器已执行的命令数量
		 *
		 * @return 服务器已执行的命令数量
		 */
		public Integer getTotalCommandsProcessed(){
			return PropertiesUtils.getInt(properties, Key.TOTAL_COMMANDS_PROCESSED.value);
		}

		/**
		 * 获取服务器已接受的连接请求数量
		 *
		 * @return 服务器已接受的连接请求数量
		 */
		public Integer getTotalConnectionsReceived(){
			return PropertiesUtils.getInt(properties, Key.TOTAL_CONNECTIONS_RECEIVED.value);
		}

		/**
		 * 返回 Redis 服务接受输入总数据量(单位: byte)
		 *
		 * @return Redis 服务接受输入总数据量
		 */
		public Integer getTotalNetInput(){
			return PropertiesUtils.getInt(properties, Key.TOTAL_NET_INPUT.value);
		}

		/**
		 * 返回 Redis 服务输出总数据量(单位: byte)
		 *
		 * @return Redis 服务输出总数据量
		 */
		public Integer getTotalNetOutput(){
			return PropertiesUtils.getInt(properties, Key.TOTAL_NET_OUTPUT.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			ACTIVE_DEFRAG_HITS("active_defrag_hits"),

			ACTIVE_DEFRAG_KEY_HITS("active_defrag_key_hits"),

			ACTIVE_DEFRAG_KEY_MISSES("active_defrag_key_misses"),

			ACTIVE_DEFRAG_MISSES("active_defrag_misses"),

			EXPIRED_KEYS("expired_keys"),

			EXPIRED_STALE_PERC("expired_stale_perc"),

			EXPIRED_TIME_CAP_REACHED_COUNT("expired_time_cap_reached_count"),

			EVICTED_KEYS("evicted_keys"),

			INSTANTANEOUS_INPUT("instantaneous_input_kbps"),

			INSTANTANEOUS_OPS_PER_SEC("instantaneous_ops_per_sec"),

			INSTANTANEOUS_OUTPUT("instantaneous_output_kbps"),

			KEYSPACE_HITS("keyspace_hits"),

			KEYSPACE_MISSES("keyspace_misses"),

			LATEST_FORK_USEC("latest_fork_usec"),

			MIGRATE_CACHED_SOCKETS("migrate_cached_sockets"),

			PUBSUB_CHANNELS("pubsub_channels"),

			PUBSUB_PATTERNS("pubsub_patterns"),

			REJECTED_CONNECTIONS("rejected_connections"),

			SLAVE_EXPIRES_TRACKED_KEYS("slave_expires_tracked_keys"),

			SYNC_FULL("sync_full"),

			SYNC_PARTIAL_ERR("sync_partial_err"),

			SYNC_PARTIAL_OK("sync_partial_ok"),

			TOTAL_COMMANDS_PROCESSED("total_commands_processed"),

			TOTAL_CONNECTIONS_RECEIVED("total_connections_received"),

			TOTAL_NET_INPUT("total_net_input_bytes"),

			TOTAL_NET_OUTPUT("total_net_output_bytes");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * 主从复制信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Replication implements Serializable {

		private final static long serialVersionUID = 5728324218040813770L;

		private final Properties properties;

		public Replication(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取已连接的从服务器数量
		 *
		 * @return 已连接的从服务器数量
		 */
		public Integer getConnectedSlaves(){
			return PropertiesUtils.getInt(properties, Key.CONNECTED_SLAVES.value);
		}

		/**
		 * 获取 Master 节点
		 *
		 * @return Master 节点
		 */
		public RedisNode getMaster(){
			return getObject(properties, Key.MASTER.value);
		}

		/**
		 * 获取 Slave 节点
		 *
		 * @return Slave 节点
		 */
		public List<RedisNode> getSlave(){
			return getObject(properties, Key.SLAVE.value);
		}

		/**
		 * 获取距离最近一次与主服务器进行通信已经过去了多少秒钟
		 *
		 * @return 距离最近一次与主服务器进行通信已经过去了多少秒钟
		 */
		public Integer getMasterLastIoSecondsAgo(){
			return PropertiesUtils.getInt(properties, Key.MASTER_LAST_IO_SECONDS_AGO.value);
		}

		/**
		 * 获取复制连接当前的状态，up 表示连接正常，down 表示连接断开
		 *
		 * @return 复制连接当前的状态，up 表示连接正常，down 表示连接断开
		 */
		public MasterLinkStatus getMasterLinkStatus(){
			return getObject(properties, Key.MASTER_LINK_STATUS.value);
		}

		/**
		 * 获取主服务器是否正在与这个从服务器进行同步
		 *
		 * @return 主服务器是否正在与这个从服务器进行同步
		 */
		public Boolean isMasterSyncInProgress(){
			return getMasterSyncInProgress();
		}

		/**
		 * 获取主服务器是否正在与这个从服务器进行同步
		 *
		 * @return 主服务器是否正在与这个从服务器进行同步
		 */
		public Boolean getMasterSyncInProgress(){
			return getBoolean(properties, Key.MASTER_SYNC_IN_PROGRESS.value);
		}

		/**
		 * 获取当前 master 记录的复制偏移量
		 *
		 * @return 当前 master 记录的复制偏移量
		 */
		public Integer getMasterReplOffset(){
			return PropertiesUtils.getInt(properties, Key.MASTER_REPL_OFFSET.value);
		}

		/**
		 * 获取服务器的复制 ID
		 *
		 * @return 服务器的复制 ID
		 */
		public String getMasterReplId(){
			return PropertiesUtils.get(properties, Key.MASTER_REPL_ID.value);
		}

		/**
		 * 获取第二服务器复制 ID
		 *
		 * @return 第二服务器复制 ID
		 */
		public String getMasterReplId2(){
			return PropertiesUtils.get(properties, Key.MASTER_REPL_ID2.value);
		}

		public ReplBacklog getReplBackLog(){
			return getObject(properties, Key.REPL_BACK_LOG.value);
		}

		/**
		 * 获取 Redis 服务器角色
		 *
		 * @return Redis 服务器角色
		 */
		public Role getRole(){
			return getObject(properties, Key.ROLE.value);
		}

		/**
		 * 获取第二服务器复制偏移量
		 *
		 * @return 第二服务器复制偏移量
		 */
		public Integer getSecondReplOffset(){
			return PropertiesUtils.getInt(properties, Key.SECOND_REPL_OFFSET.value);
		}

		public Integer getSlaveReplOffset(){
			return PropertiesUtils.getInt(properties, Key.SLAVE_REPL_OFFSET.value);
		}

		public Integer getSlavePriority(){
			return PropertiesUtils.getInt(properties, Key.SLAVE_PRIORITY.value);
		}

		/**
		 * 获取从节点是否为只读
		 *
		 * @return 从节点是否为只读
		 */
		public Boolean getSlaveReadOnly(){
			return getBoolean(properties, Key.SLAVE_READ_ONLY.value);
		}

		public Integer getReplBackLogActive(){
			return PropertiesUtils.getInt(properties, Key.REPL_BACKLOG_ACTIVE.value);
		}

		public Integer getReplBackLogSize(){
			return PropertiesUtils.getInt(properties, Key.REPL_BACKLOG_ACTIVE.value);
		}

		public Integer getReplBackLogByteOffset(){
			return PropertiesUtils.getInt(properties, Key.REPL_BACKLOG_FIRST_BYTE_OFFSET.value);
		}

		public Integer getReplBackLogHistLen(){
			return PropertiesUtils.getInt(properties, Key.REPL_BACKLOG_FIRST_BYTE_OFFSET.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			CONNECTED_SLAVES("connected_slaves"),

			MASTER("master"),

			SLAVE("slave"),

			MASTER_LAST_IO_SECONDS_AGO("master_last_io_seconds_ago"),

			MASTER_LINK_STATUS("master_link_status"),

			MASTER_SYNC_IN_PROGRESS("master_sync_in_progress"),

			MASTER_REPL_OFFSET("master_repl_offset"),

			MASTER_REPL_ID("master_replid"),

			MASTER_REPL_ID2("master_replid2"),

			REPL_BACK_LOG("repl_back_log"),

			ROLE("role"),

			SECOND_REPL_OFFSET("second_repl_offset"),

			SLAVE_REPL_OFFSET("slave_repl_offset"),

			SLAVE_PRIORITY("slave_priority"),

			SLAVE_READ_ONLY("slave_read_only"),

			REPL_BACKLOG_ACTIVE("repl_backlog_active"),

			REPL_BACKLOG_SIZE("repl_backlog_size"),

			REPL_BACKLOG_FIRST_BYTE_OFFSET("repl_backlog_first_byte_offset"),

			REPL_BACKLOG_HISTLEN("repl_backlog_histlen");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

		public enum MasterLinkStatus {

			UP("up"),

			DOWN("down");

			private final String value;

			MasterLinkStatus(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

		public final static class Slave extends RedisServer implements Serializable {

			private final static long serialVersionUID = 2989042827385297339L;

			private final Properties properties;

			public Slave(final Properties properties){
				super(PropertiesUtils.get(properties, Key.IP.value),
						PropertiesUtils.getInt(properties, Key.PORT.value));
				this.properties = properties;
			}

			/**
			 * 获取从服务器状态
			 *
			 * @return 从服务器状态
			 */
			public SlaveState getState(){
				return getObject(properties, Key.STATE.value);
			}

			/**
			 * 获取复制偏移量
			 *
			 * @return 复制偏移量
			 */
			public Integer getOffset(){
				return PropertiesUtils.getInt(properties, Key.OFFSET.value);
			}

			/**
			 * 获取复制延迟
			 *
			 * @return 复制延迟
			 */
			public Integer getLag(){
				return PropertiesUtils.getInt(properties, Key.LAG.value);
			}

			@Override
			public String toString(){
				return "ip=" + getIp() + ", port=" + getPort() + ", state=" + getState() + ", offset=" + getOffset() +
						", " + "lag=" + getLag();
			}

			public enum Key {

				IP("ip"),

				PORT("port"),

				STATE("state"),

				OFFSET("offset"),

				LAG("lag");

				private final String value;

				Key(final String value){
					this.value = value;
				}

				public String getValue(){
					return value;
				}

			}

			public enum SlaveState {

				ONLINE("online");

				private final String value;

				SlaveState(final String value){
					this.value = value;
				}

				public String getValue(){
					return value;
				}

			}

		}

		public final static class ReplBacklog implements Serializable {

			private final static long serialVersionUID = 1468810894033878876L;

			private final Properties properties;

			public ReplBacklog(final Properties properties){
				this.properties = properties;
			}

			public Integer getActive(){
				return PropertiesUtils.getInt(properties, Key.ACTIVE.value);
			}

			public Integer getSize(){
				return PropertiesUtils.getInt(properties, Key.SIZE.value);
			}

			public Integer getFirstByteOffset(){
				return PropertiesUtils.getInt(properties, Key.FIRST_BYTE_OFFSET.value);
			}

			public Integer getHistlen(){
				return PropertiesUtils.getInt(properties, Key.HISTLEN.value);
			}

			public String toPrettyString(){
				return Info.toPrettyString(properties);
			}

			@Override
			public String toString(){
				return Info.toString(properties);
			}

			public enum Key {

				ACTIVE("repl_backlog_active"),

				SIZE("repl_backlog_size"),

				FIRST_BYTE_OFFSET("repl_backlog_first_byte_offset"),

				HISTLEN("repl_backlog_histlen");

				private final String value;

				Key(final String value){
					this.value = value;
				}

				public String getValue(){
					return value;
				}

			}

		}

	}

	/**
	 * 哨兵信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Sentinel implements Serializable {

		private final static long serialVersionUID = 1740259200282640149L;

		private final Properties properties;

		public Sentinel(Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取 Master 节点列表
		 *
		 * @return Master 节点列表
		 */
		public List<Master> getMasters(){
			return getObject(properties, Key.MASTERS.value);
		}

		/**
		 * 获取 sentinel 正在运行的脚本数量
		 *
		 * @return sentinel 正在运行的脚本数量
		 */
		public Integer getRunningScripts(){
			return PropertiesUtils.getInt(properties, Key.RUNNING_SCRIPTS.value);
		}

		/**
		 * 获取 Master 数量
		 *
		 * @return Master 数量
		 */
		public Integer getSentinelMasters(){
			return PropertiesUtils.getInt(properties, Key.SENTINEL_MASTERS.value);
		}

		/**
		 * 获取是否进入了 tilt 模式
		 *
		 * @return 是否进入了 tilt 模式
		 */
		public Boolean isTilt(){
			return getTilt();
		}

		/**
		 * 获取是否进入了 tilt 模式
		 *
		 * @return 是否进入了 tilt 模式
		 */
		public Boolean getTilt(){
			return getBoolean(properties, Key.TILT.value);
		}

		/**
		 * 获取 sentinel 在队列中正在排队的脚本的个数
		 *
		 * @return sentinel 在队列中正在排队的脚本的个数
		 */
		public Integer getScriptsQueueLength(){
			return PropertiesUtils.getInt(properties, Key.SCRIPTS_QUEUE_LENGTH.value);
		}

		public Integer getSentinelSimulateFailureFlags(){
			return PropertiesUtils.getInt(properties, Key.SIMULATE_FAILURE_FLAGS.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			MASTERS("masters"),

			RUNNING_SCRIPTS("sentinel_running_scripts"),

			SENTINEL_MASTERS("sentinel_masters"),

			TILT("sentinel_tilt"),

			SCRIPTS_QUEUE_LENGTH("sentinel_scripts_queue_length"),

			SIMULATE_FAILURE_FLAGS("sentinel_simulate_failure_flags");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

		public final static class Master extends RedisNode implements Serializable {

			private final static long serialVersionUID = 5058163948650311361L;

			private final Properties properties;

			public Master(Properties properties){
				super(PropertiesUtils.get(properties, Key.HOST.value),
						PropertiesUtils.getInt(properties, Key.PORT.value));
				setName(PropertiesUtils.get(properties, Key.NAME.value));
				this.properties = properties;
			}

			/**
			 * 获取状态
			 *
			 * @return 状态
			 */
			public HostAndPort getAddress(){
				return getObject(properties, Key.ADDRESS.value);
			}

			/**
			 * 获取状态
			 *
			 * @return 状态
			 */
			public Status getStatus(){
				return getObject(properties, Key.STATUS.value);
			}

			/**
			 * 获取从节点数量
			 *
			 * @return 从节点数量
			 */
			public Integer getSlaves(){
				return PropertiesUtils.getInt(properties, Key.SENTINELS.value);
			}

			/**
			 * 获取哨兵节点数量
			 *
			 * @return 哨兵节点数量
			 */
			public Integer getSentinels(){
				return PropertiesUtils.getInt(properties, Key.SLAVES.value);
			}

			@Override
			public String toString(){
				return "name=" + getName() + ", status=" + getStatus() + ", address=" + getAddress() + ", slaves=" +
						getSlaves() + ", sentinels=" + getSentinels();
			}

			public enum Key {

				ADDRESS("address"),

				HOST("host"),

				NAME("name"),

				PORT("port"),

				STATUS("status"),

				SLAVES("slaves"),

				SENTINELS("sentinels");

				private final String value;

				Key(final String value){
					this.value = value;
				}

				public String getValue(){
					return value;
				}

			}

		}

		public enum Status {

			OK("ok"),

			SDOWN("sdown"),

			ODOWN("odown");

			private final String value;

			Status(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * CPU 的计算量统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Cpu implements Serializable {

		private final static long serialVersionUID = 7374909500664048450L;

		private final Properties properties;

		public Cpu(Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取 Redis 服务器耗费的系统 CPU
		 *
		 * @return Redis 服务器耗费的系统 CPU
		 */
		public Double getUsedSys(){
			return PropertiesUtils.getDouble(properties, Key.USED_SYS.value);
		}

		/**
		 * 获取 Redis 后台进程耗费的系统 CPU
		 *
		 * @return Redis 后台进程耗费的系统 CPU
		 */
		public Double getUsedSysChildren(){
			return PropertiesUtils.getDouble(properties, Key.USED_SYS_CHILDREN.value);
		}

		/**
		 * 获取 Redis 服务器耗费的用户 CPU
		 *
		 * @return Redis 服务器耗费的用户 CPU
		 */
		public Double getUsedCpuUser(){
			return PropertiesUtils.getDouble(properties, Key.USED_USER.value);
		}

		/**
		 * 获取 Redis 后台进程耗费的用户 CPU
		 *
		 * @return Redis 后台进程耗费的用户 CPU
		 */
		public Double getUsedUserChildren(){
			return PropertiesUtils.getDouble(properties, Key.USED_USER_CHILDREN.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			USED_SYS("used_cpu_sys"),

			USED_SYS_CHILDREN("used_cpu_sys_children"),

			USED_USER("used_cpu_user"),

			USED_USER_CHILDREN("used_cpu_user_children");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * 集群有关的信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Cluster implements Serializable {

		private final static long serialVersionUID = 1616257693468570678L;

		private final Properties properties;

		public Cluster(final Properties properties){
			this.properties = properties;
		}

		/**
		 * 获取集群功能是否已经开启
		 *
		 * @return 获取集群功能是否已经开启
		 */
		public Boolean isEnabled(){
			return getEnabled();
		}

		/**
		 * 获取集群功能是否已经开启
		 *
		 * @return 获取集群功能是否已经开启
		 */
		public Boolean getEnabled(){
			return getBoolean(properties, Key.ENABLED.value);
		}

		public String toPrettyString(){
			return Info.toPrettyString(properties);
		}

		@Override
		public String toString(){
			return Info.toString(properties);
		}

		public enum Key {

			ENABLED("cluster_enabled");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

	/**
	 * 数据库相关的统计信息
	 *
	 * @author Yong.Teng
	 */
	public final static class Keyspace implements Serializable {

		private final static long serialVersionUID = -5023410132423250601L;

		private final Properties properties;

		public Keyspace(final Properties properties){
			this.properties = properties;
		}

		public Long getAvgTtl(){
			return PropertiesUtils.getLong(properties, Key.AVG_TTL.value);
		}

		/**
		 * 获取数据库
		 *
		 * @return 数据库
		 */
		public Integer getDb(){
			return PropertiesUtils.getInt(properties, Key.DB.value);
		}

		/**
		 * 获取带有生存期的 Key 的数量
		 *
		 * @return 带有生存期的 Key 的数量
		 */
		public Integer getExpires(){
			return PropertiesUtils.getInt(properties, Key.EXPIRES.value);
		}

		/**
		 * 获取 Key 数量
		 *
		 * @return Key 数量
		 */
		public Integer getKeys(){
			return PropertiesUtils.getInt(properties, Key.KEYS.value);
		}

		public String toPrettyString(){
			return toString();
		}

		@Override
		public String toString(){
			final StringJoiner stringJoiner = new StringJoiner(", ");

			stringJoiner.add("db=" + getDb()).add("keys=" + getKeys()).add("expires=" + getExpires())
					.add("avg_ttl=" + getAvgTtl());

			return stringJoiner.toString();
		}

		public enum Key {

			AVG_TTL("avg_ttl"),

			DB("db"),

			EXPIRES("expires"),

			KEYS("keys");

			private final String value;

			Key(final String value){
				this.value = value;
			}

			public String getValue(){
				return value;
			}

		}

	}

}

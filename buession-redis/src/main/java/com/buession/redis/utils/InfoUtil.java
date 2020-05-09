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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.utils;

import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Arch;
import com.buession.lang.Multiplexing;
import com.buession.redis.core.Clients;
import com.buession.redis.core.Cluster;
import com.buession.redis.core.Cpu;
import com.buession.redis.core.Info;
import com.buession.redis.core.Keyspace;
import com.buession.redis.core.MaxMemoryPolicy;
import com.buession.redis.core.Memory;
import com.buession.redis.core.Persistence;
import com.buession.redis.core.Replication;
import com.buession.redis.core.Role;
import com.buession.redis.core.Server;
import com.buession.redis.core.Stats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 */
public class InfoUtil {

	private final static Pattern PATTERN = Pattern.compile("# (\\S+)[\\s]+([^#]+)");

	private final static String ROW_SEPARATOR = "\r\n";

	public final static Info convert(final String str){
		final Info info = new Info();
		final Server server = new Server();
		final Cluster cluster = new Cluster();
		final Replication replication = new Replication();
		final Clients clients = new Clients();
		final Cpu cpu = new Cpu();
		final Memory memory = new Memory();
		final Persistence persistence = new Persistence();
		final Stats stats = new Stats();
		final List<Keyspace> keyspace = new ArrayList<>();

		Matcher matcher = PATTERN.matcher(str);

		while(matcher.find()){
			String groupName = matcher.group(1);
			String groupValue = matcher.group(2);

			if("Server".equalsIgnoreCase(groupName)){
				final ServerParser serverParser = new ServerParser();

				info.setServer(serverParser.parse(groupValue, server));
			}else if("Clients".equalsIgnoreCase(groupName)){
				final ClientsParser clientsParser = new ClientsParser();

				info.setClients(clientsParser.parse(groupValue, clients));
			}else if("Cluster".equalsIgnoreCase(groupName)){
				final ClusterParser clusterParser = new ClusterParser();

				info.setCluster(clusterParser.parse(groupValue, cluster));
			}else if("CPU".equalsIgnoreCase(groupName)){
				final CpuParser cpuParser = new CpuParser();

				info.setCpu(cpuParser.parse(groupValue, cpu));
			}else if("Memory".equalsIgnoreCase(groupName)){
				final MemoryParser memoryParser = new MemoryParser();

				info.setMemory(memoryParser.parse(groupValue, memory));
			}else if("Persistence".equalsIgnoreCase(groupName)){
				final PersistenceParser persistenceParser = new PersistenceParser();

				info.setPersistence(persistenceParser.parse(groupValue, persistence));
			}else if("Stats".equalsIgnoreCase(groupName)){
				final StatsParser statsParser = new StatsParser();

				info.setStats(statsParser.parse(groupValue, stats));
			}else if("Replication".equalsIgnoreCase(groupName)){
				final ReplicationParser replicationParser = new ReplicationParser();

				info.setReplication(replicationParser.parse(groupValue, replication));

				server.setMaster(replication.getRole() == Role.MASTER);
			}else if("Keyspace".equalsIgnoreCase(groupName)){
				final KeyspaceParser keyspaceParser = new KeyspaceParser();

				info.setKeyspace(keyspaceParser.parse(groupValue, keyspace));
			}
		}

		return info;
	}

	protected interface Parser<T> {

		T parse(final String str, final T t);

	}

	protected final static class ServerParser implements Parser<Server> {

		@Override
		public Server parse(final String str, final Server server){
			Server.Uptime uptime = new Server.Uptime();
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("redis_version".equals(keyValueParser.getKey())){
					server.setVersion(keyValueParser.getValue());
				}else if("redis_git_sha1".equals(keyValueParser.getKey())){
					server.setGitSha1(keyValueParser.getValue());
				}else if("redis_git_dirty".equals(keyValueParser.getKey())){
					server.setGitDirty(keyValueParser.getValue());
				}else if("redis_build_id".equals(keyValueParser.getKey())){
					server.setBuildId(keyValueParser.getValue());
				}else if("redis_mode".equals(keyValueParser.getKey())){
					server.setMode(keyValueParser.getEnumValue(Server.Mode.class));
				}else if("os".equals(keyValueParser.getKey())){
					server.setOs(keyValueParser.getValue());
				}else if("arch_bits".equals(keyValueParser.getKey())){
					if("32".equals(keyValueParser.getValue())){
						server.setArch(Arch.ARCH_32);
					}else if("64".equals(keyValueParser.getValue())){
						server.setArch(Arch.ARCH_64);
					}
				}else if("multiplexing_api".equals(keyValueParser.getKey())){
					server.setMultiplexingApi(keyValueParser.getEnumValue(Multiplexing.class));
				}else if("gcc_version".equals(keyValueParser.getKey())){
					server.setGccVersion(keyValueParser.getValue());
				}else if("process_id".equals(keyValueParser.getKey())){
					server.setProcessId(keyValueParser.getIntValue());
				}else if("run_id".equals(keyValueParser.getKey())){
					server.setRunId(keyValueParser.getValue());
				}else if("tcp_port".equals(keyValueParser.getKey())){
					server.setPort(keyValueParser.getIntValue());
				}else if("uptime_in_seconds".equals(keyValueParser.getKey())){
					uptime.setSeconds(keyValueParser.getIntValue());
				}else if("uptime_in_days".equals(keyValueParser.getKey())){
					uptime.setDays(keyValueParser.getIntValue());
				}else if("hz".equals(keyValueParser.getKey())){
					server.setHz(keyValueParser.getIntValue());
				}else if("configured_hz".equals(keyValueParser.getKey())){
					server.setConfiguredHz(keyValueParser.getIntValue());
				}else if("lru_clock".equals(keyValueParser.getKey())){
					server.setLruClock(keyValueParser.getIntValue());
				}else if("executable".equals(keyValueParser.getKey())){
					server.setExecutable(keyValueParser.getValue());
				}else if("config_file".equals(keyValueParser.getKey())){
					server.setConfigFile(keyValueParser.getValue());
				}
			}

			if(uptime.getSeconds() > 0){
				server.setUptime(uptime);
			}

			return server;
		}
	}

	protected final static class ClientsParser implements Parser<Clients> {

		@Override
		public Clients parse(final String str, final Clients clients){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("connected_clients".equals(keyValueParser.getKey())){
					clients.setConnecteds(keyValueParser.getIntValue());
				}else if("client_longest_output_list".equals(keyValueParser.getKey())){
					clients.setLongestOutputList(keyValueParser.getIntValue());
				}else if("client_biggest_input_buf".equals(keyValueParser.getKey())){
					clients.setBiggestInputBuffer(keyValueParser.getIntValue());
				}else if("client_recent_max_input_buffer".equals(keyValueParser.getKey())){
					clients.setClientRecentMaxInputBuffer(keyValueParser.getIntValue());
				}else if("client_recent_max_output_buffer".equals(keyValueParser.getKey())){
					clients.setClientRecentMaxOutputBuffer(keyValueParser.getIntValue());
				}else if("blocked_clients".equals(keyValueParser.getKey())){
					clients.setBlockeds(keyValueParser.getIntValue());
				}
			}

			return clients;
		}
	}

	protected final static class ClusterParser implements Parser<Cluster> {

		@Override
		public Cluster parse(final String str, final Cluster cluster){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("cluster_enabled".equals(keyValueParser.getKey())){
					cluster.setEnabled(keyValueParser.getBoolValue());
				}
			}

			return cluster;
		}
	}

	protected final static class ReplicationParser implements Parser<Replication> {

		private final static Pattern SLAVE_PATTERN = Pattern.compile("slave\\d");

		@Override
		public Replication parse(final String str, final Replication replication){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			Replication.ReplBacklog replBacklog = new Replication.ReplBacklog();
			Replication.Master master = null;
			List<Replication.Slave> slaves = null;
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("role".equals(keyValueParser.getKey())){
					replication.setRole(keyValueParser.getEnumValue(Role.class));

					switch(replication.getRole()){
						case MASTER:
							master = new Replication.Master();
							break;
						case SLAVE:
							slaves = new ArrayList<>();
							break;
						default:
							break;
					}
				}else if(keyValueParser.isKey(SLAVE_PATTERN)){
					if(slaves == null){
						slaves = new ArrayList<>();
					}

					slaves.add(parseSlave(keyValueParser.getValue()));
				}else if("master_host".equals(keyValueParser.getKey())){
					if(master == null){
						master = new Replication.Master();
					}

					master.setHost(keyValueParser.getValue());
				}else if("master_port".equals(keyValueParser.getKey())){
					if(master == null){
						master = new Replication.Master();
					}

					master.setPort(keyValueParser.getIntValue());
				}else if("master_link_status".equals(keyValueParser.getKey())){
					if(master == null){
						master = new Replication.Master();
					}

					master.setMasterLinkStatus(keyValueParser.getEnumValue(Replication.MasterLinkStatus.class));
				}else if("master_last_io_seconds_ago".equals(keyValueParser.getKey())){
					if(master == null){
						master = new Replication.Master();
					}

					master.setLastIoSecondsAgo(keyValueParser.getIntValue());
				}else if("master_sync_in_progress".equals(keyValueParser.getKey())){
					if(master == null){
						master = new Replication.Master();
					}

					master.setSyncInProgress(keyValueParser.getBoolValue());
				}else if("connected_slaves".equals(keyValueParser.getKey())){
					replication.setConnectedSlaves(keyValueParser.getIntValue());
				}else if("master_replid".equals(keyValueParser.getKey())){
					replication.setMasterReplid(keyValueParser.getValue());
				}else if("master_replid2".equals(keyValueParser.getKey())){
					replication.setMasterReplid2(keyValueParser.getValue());
				}else if("master_repl_offset".equals(keyValueParser.getKey())){
					replication.setMasterReplOffset(keyValueParser.getIntValue());
				}else if("second_repl_offset".equals(keyValueParser.getKey())){
					replication.setSecondReplOffset(keyValueParser.getIntValue());
				}else if("repl_backlog_active".equals(keyValueParser.getKey())){
					replBacklog.setActive(keyValueParser.getIntValue());
				}else if("repl_backlog_size".equals(keyValueParser.getKey())){
					replBacklog.setSize(keyValueParser.getIntValue());
				}else if("repl_backlog_first_byte_offset".equals(keyValueParser.getKey())){
					replBacklog.setFirstByteOffset(keyValueParser.getIntValue());
				}else if("repl_backlog_histlen".equals(keyValueParser.getKey())){
					replBacklog.setHistlen(keyValueParser.getIntValue());
				}
			}

			replication.setMaster(master);
			replication.setSlaves(slaves);
			replication.setReplBacklog(replBacklog);

			return replication;
		}

		private final static Replication.Slave parseSlave(final String str){
			String[] groups = StringUtils.split(str, ',');
			KeyValueParser keyValueParser;

			Replication.Slave slave = new Replication.Slave();

			for(String group : groups){
				keyValueParser = new KeyValueParser(group, '=');

				if("ip".equals(keyValueParser.getKey())){
					slave.setIp(keyValueParser.getValue());
				}else if("port".equals(keyValueParser.getKey())){
					slave.setPort(keyValueParser.getIntValue());
				}else if("state".equals(keyValueParser.getKey())){
					slave.setState(keyValueParser.getEnumValue(Replication.SlaveState.class));
				}else if("offset".equals(keyValueParser.getKey())){
					slave.setOffset(keyValueParser.getIntValue());
				}else if("lag".equals(keyValueParser.getKey())){
					slave.setLag(keyValueParser.getIntValue());
				}
			}

			return slave;
		}

	}

	protected final static class CpuParser implements Parser<Cpu> {

		@Override
		public Cpu parse(final String str, final Cpu cpu){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("used_cpu_sys".equals(keyValueParser.getKey())){
					cpu.setUsedCpuSys(keyValueParser.getDoubleValue());
				}else if("used_cpu_user".equals(keyValueParser.getKey())){
					cpu.setUsedCpuUser(keyValueParser.getDoubleValue());
				}else if("used_cpu_sys_children".equals(keyValueParser.getKey())){
					cpu.setUsedCpuSysChildren(keyValueParser.getDoubleValue());
				}else if("used_cpu_user_children".equals(keyValueParser.getKey())){
					cpu.setUsedCpuUserChildren(keyValueParser.getDoubleValue());
				}
			}

			return cpu;
		}
	}

	protected final static class MemoryParser implements Parser<Memory> {

		@Override
		public Memory parse(final String str, final Memory memory){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("used_memory".equals(keyValueParser.getKey())){
					memory.setUsedMemory(keyValueParser.getLongValue());
				}else if("used_memory_human".equals(keyValueParser.getKey())){
					memory.setUsedMemoryHuman(keyValueParser.getValue());
				}else if("used_memory_rss".equals(keyValueParser.getKey())){
					memory.setUsedMemoryRss(keyValueParser.getLongValue());
				}else if("used_memory_rss_human".equals(keyValueParser.getKey())){
					memory.setUsedMemoryRssHuman(keyValueParser.getValue());
				}else if("used_memory_peak".equals(keyValueParser.getKey())){
					memory.setUsedMemoryPeak(keyValueParser.getLongValue());
				}else if("used_memory_peak_human".equals(keyValueParser.getKey())){
					memory.setUsedMemoryPeakHuman(keyValueParser.getValue());
				}else if("used_memory_peak_perc".equals(keyValueParser.getKey())){
					memory.setUsedMemoryPeakPerc(keyValueParser.getPercentValue());
				}else if("used_memory_overhead".equals(keyValueParser.getKey())){
					memory.setUsedMemoryOverhead(keyValueParser.getLongValue());
				}else if("used_memory_startup".equals(keyValueParser.getKey())){
					memory.setUsedMemoryStartup(keyValueParser.getLongValue());
				}else if("used_memory_dataset".equals(keyValueParser.getKey())){
					memory.setUsedMemoryDataset(keyValueParser.getLongValue());
				}else if("used_memory_dataset_perc".equals(keyValueParser.getKey())){
					memory.setUsedMemoryDatasetPerc(keyValueParser.getPercentValue());
				}else if("allocator_allocated".equals(keyValueParser.getKey())){
					memory.setAllocatorAllocated(keyValueParser.getLongValue());
				}else if("allocator_active".equals(keyValueParser.getKey())){
					memory.setAllocatorActive(keyValueParser.getLongValue());
				}else if("allocator_resident".equals(keyValueParser.getKey())){
					memory.setAllocatorResident(keyValueParser.getLongValue());
				}else if("total_system_memory".equals(keyValueParser.getKey())){
					memory.setTotalSystemMemory(keyValueParser.getLongValue());
				}else if("total_system_memory_human".equals(keyValueParser.getKey())){
					memory.setTotalSystemMemoryHuman(keyValueParser.getValue());
				}else if("used_memory_lua".equals(keyValueParser.getKey())){
					memory.setUsedMemoryLua(keyValueParser.getLongValue());
				}else if("used_memory_lua_human".equals(keyValueParser.getKey())){
					memory.setUsedMemoryLuaHuman(keyValueParser.getValue());
				}else if("used_memory_scripts".equals(keyValueParser.getKey())){
					memory.setUsedMemoryScripts(keyValueParser.getLongValue());
				}else if("used_memory_scripts_human".equals(keyValueParser.getKey())){
					memory.setUsedMemoryScriptsHuman(keyValueParser.getValue());
				}else if("number_of_cached_scripts".equals(keyValueParser.getKey())){
					memory.setNumberOfCachedScripts(keyValueParser.getIntValue());
				}else if("maxmemory".equals(keyValueParser.getKey())){
					memory.setMaxMemory(keyValueParser.getLongValue());
				}else if("maxmemory_human".equals(keyValueParser.getKey())){
					memory.setMaxMemoryHuman(keyValueParser.getValue());
				}else if("maxmemory_policy".equals(keyValueParser.getKey())){
					memory.setMaxMemoryPolicy(EnumUtils.valueOf(MaxMemoryPolicy.class, StringUtils.replace
							(keyValueParser.getValue().toUpperCase(), "-", "_")));
				}else if("allocator_frag_ratio".equals(keyValueParser.getKey())){
					memory.setAllocatorFragRatio(keyValueParser.getDoubleValue());
				}else if("allocator_frag_bytes".equals(keyValueParser.getKey())){
					memory.setAllocatorFragBytes(keyValueParser.getLongValue());
				}else if("allocator_rss_ratio".equals(keyValueParser.getKey())){
					memory.setAllocatorRssRatio(keyValueParser.getDoubleValue());
				}else if("allocator_rss_bytes".equals(keyValueParser)){
					memory.setAllocatorRssBytes(keyValueParser.getLongValue());
				}else if("rss_overhead_ratio".equals(keyValueParser)){
					memory.setRssOverheadRatio(keyValueParser.getDoubleValue());
				}else if("rss_overhead_bytes".equals(keyValueParser.getKey())){
					memory.setRssOverheadBytes(keyValueParser.getLongValue());
				}else if("mem_fragmentation_ratio".equals(keyValueParser.getKey())){
					memory.setMemFragmentationRatio(keyValueParser.getDoubleValue());
				}else if("mem_fragmentation_bytes".equals(keyValueParser.getKey())){
					memory.setMemFragmentationBytes(keyValueParser.getLongValue());
				}else if("mem_not_counted_for_evict".equals(keyValueParser.getKey())){
					memory.setMemNotCountedForEvict(keyValueParser.getLongValue());
				}else if("mem_replication_backlog".equals(keyValueParser.getKey())){
					memory.setMemReplicationBacklog(keyValueParser.getLongValue());
				}else if("mem_clients_slaves".equals(keyValueParser.getKey())){
					memory.setMemClientsSlaves(keyValueParser.getIntValue());
				}else if("mem_clients_normal".equals(keyValueParser.getKey())){
					memory.setMemClientsNormal(keyValueParser.getIntValue());
				}else if("mem_aof_buffer".equals(keyValueParser.getKey())){
					memory.setMemAofBuffer(keyValueParser.getLongValue());
				}else if("mem_allocator".equals(keyValueParser.getKey())){
					memory.setMemAllocator(keyValueParser.getValue());
				}else if("active_defrag_running".equals(keyValueParser.getKey())){
					memory.setActiveDefragRunning(keyValueParser.getBoolValue());
				}else if("lazyfree_pending_objects".equals(keyValueParser.getKey())){
					memory.setLazyfreePendingObjects(keyValueParser.getIntValue());
				}
			}

			return memory;
		}
	}

	protected final static class PersistenceParser implements Parser<Persistence> {

		@Override
		public Persistence parse(final String str, final Persistence persistence){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("loading".equals(keyValueParser.getKey())){
					persistence.setLoading(keyValueParser.getBoolValue());
				}else if("rdb_changes_since_last_save".equals(keyValueParser.getKey())){
					persistence.setRdbChangesSinceLastSave(keyValueParser.getIntValue());
				}else if("rdb_bgsave_in_progress".equals(keyValueParser.getKey())){
					persistence.setRdbBgSaveInProgress(keyValueParser.getBoolValue());
				}else if("rdb_last_save_time".equals(keyValueParser.getKey())){
					Date date = new Date();

					date.setTime(keyValueParser.getLongValue() * 1000L);
					persistence.setRdbLastSaveTime(date);
				}else if("rdb_last_bgsave_status".equals(keyValueParser.getKey())){
					persistence.setRdbLastBgSaveStatus(keyValueParser.getStatusValue());
				}else if("rdb_last_bgsave_time_sec".equals(keyValueParser.getKey())){
					persistence.setRdbLastBgSaveTimeSec(keyValueParser.getIntValue());
				}else if("rdb_current_bgsave_time_sec".equals(keyValueParser.getKey())){
					persistence.setRdbCurrentBgSaveTimeSec(keyValueParser.getIntValue());
				}else if("rdb_last_cow_size".equals(keyValueParser.getKey())){
					persistence.setRdbLastCowSize(keyValueParser.getLongValue());
				}else if("aof_enabled".equals(keyValueParser.getKey())){
					persistence.setAofEnabled(keyValueParser.getBoolValue());
				}else if("aof_rewrite_in_progress".equals(keyValueParser.getKey())){
					persistence.setAofRewriteInProgress(keyValueParser.getBoolValue());
				}else if("aof_rewrite_scheduled".equals(keyValueParser.getKey())){
					persistence.setAofRewriteScheduled(keyValueParser.getBoolValue());
				}else if("aof_last_rewrite_time_sec".equals(keyValueParser.getKey())){
					persistence.setAofLastRewriteTimeSec(keyValueParser.getIntValue());
				}else if("aof_current_rewrite_time_sec".equals(keyValueParser.getKey())){
					persistence.setAofCurrentRewriteTimeSec(keyValueParser.getIntValue());
				}else if("aof_last_bgrewrite_status".equals(keyValueParser.getKey())){
					persistence.setAofLastBgRewriteStatus(keyValueParser.getStatusValue());
				}else if("aof_last_write_status".equals(keyValueParser.getKey())){
					persistence.setAofLastWriteStatus(keyValueParser.getStatusValue());
				}else if("aof_last_cow_size".equals(keyValueParser.getKey())){
					persistence.setAofLastCowSize(keyValueParser.getLongValue());
				}else if("aof_current_size".equals(keyValueParser.getKey())){
					persistence.setAofCurrentSize(keyValueParser.getLongValue());
				}else if("aof_base_size".equals(keyValueParser.getKey())){
					persistence.setAofBaseSize(keyValueParser.getLongValue());
				}else if("aof_pending_rewrite".equals(keyValueParser.getKey())){
					persistence.setAofPendingRewrite(keyValueParser.getBoolValue());
				}else if("aof_buffer_length".equals(keyValueParser.getKey())){
					persistence.setAofBufferLength(keyValueParser.getIntValue());
				}else if("aof_rewrite_buffer_length".equals(keyValueParser.getKey())){
					persistence.setAofRewriteBufferLength(keyValueParser.getIntValue());
				}else if("aof_pending_bio_fsync".equals(keyValueParser.getKey())){
					persistence.setAofPendingBioFsync(keyValueParser.getIntValue());
				}else if("aof_delayed_fsync".equals(keyValueParser.getKey())){
					persistence.setAofDelayedFsync(keyValueParser.getIntValue());
				}
			}

			return persistence;
		}
	}

	protected final static class StatsParser implements Parser<Stats> {

		@Override
		public Stats parse(final String str, final Stats stats){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("total_connections_received".equals(keyValueParser.getKey())){
					stats.setTotalConnectionsReceived(keyValueParser.getIntValue());
				}else if("total_commands_processed".equals(keyValueParser.getKey())){
					stats.setTotalCommandsProcessed(keyValueParser.getIntValue());
				}else if("total_net_input_bytes".equals(keyValueParser.getKey())){
					stats.setTotalNetInputBytes(keyValueParser.getIntValue());
				}else if("total_net_output_bytes".equals(keyValueParser.getKey())){
					stats.setTotalNetOutputBytes(keyValueParser.getIntValue());
				}else if("instantaneous_input_kbps".equals(keyValueParser.getKey())){
					stats.setInstantaneousInputKbps(keyValueParser.getDoubleValue());
				}else if("instantaneous_output_kbps".equals(keyValueParser.getKey())){
					stats.setInstantaneousOutputKbps(keyValueParser.getDoubleValue());
				}else if("instantaneous_ops_per_sec".equals(keyValueParser.getKey())){
					stats.setInstantaneousOpsPerSec(keyValueParser.getIntValue());
				}else if("rejected_connections".equals(keyValueParser.getKey())){
					stats.setRejectedConnections(keyValueParser.getIntValue());
				}else if("sync_full".equals(keyValueParser.getKey())){
					stats.setSyncFull(keyValueParser.getIntValue());
				}else if("sync_partial_ok".equals(keyValueParser.getKey())){
					stats.setSyncPartialOk(keyValueParser.getIntValue());
				}else if("sync_partial_err".equals(keyValueParser.getKey())){
					stats.setSyncPartialErr(keyValueParser.getIntValue());
				}else if("expired_keys".equals(keyValueParser.getKey())){
					stats.setExpiredKeys(keyValueParser.getIntValue());
				}else if("expired_stale_perc".equals(keyValueParser.getKey())){
					stats.setExpiredStalePerc(keyValueParser.getDoubleValue());
				}else if("expired_time_cap_reached_count".equals(keyValueParser.getKey())){
					stats.setExpiredTimeCapReachedCount(keyValueParser.getIntValue());
				}else if("evicted_keys".equals(keyValueParser.getKey())){
					stats.setEvictedKeys(keyValueParser.getIntValue());
				}else if("keyspace_hits".equals(keyValueParser.getKey())){
					stats.setKeyspaceHits(keyValueParser.getIntValue());
				}else if("keyspace_misses".equals(keyValueParser.getKey())){
					stats.setKeyspaceMisses(keyValueParser.getIntValue());
				}else if("pubsub_channels".equals(keyValueParser.getKey())){
					stats.setPubsubChannels(keyValueParser.getIntValue());
				}else if("pubsub_patterns".equals(keyValueParser.getKey())){
					stats.setPubsubPatterns(keyValueParser.getIntValue());
				}else if("latest_fork_usec".equals(keyValueParser.getKey())){
					stats.setLatestForkUsec(keyValueParser.getIntValue());
				}else if("migrate_cached_sockets".equals(keyValueParser.getKey())){
					stats.setMigrateCachedSockets(keyValueParser.getIntValue());
				}else if("slave_expires_tracked_keys".equals(keyValueParser.getKey())){
					stats.setSlaveExpiresTrackedKeys(keyValueParser.getIntValue());
				}else if("active_defrag_hits".equals(keyValueParser.getKey())){
					stats.setActiveDefragHits(keyValueParser.getIntValue());
				}else if("active_defrag_misses".equals(keyValueParser.getKey())){
					stats.setActiveDefragMisses(keyValueParser.getIntValue());
				}else if("active_defrag_key_hits".equals(keyValueParser.getKey())){
					stats.setActiveDefragKeyHits(keyValueParser.getIntValue());
				}else if("active_defrag_key_misses".equals(keyValueParser.getKey())){
					stats.setActiveDefragKeyMisses(keyValueParser.getIntValue());
				}
			}

			return stats;
		}
	}

	protected final static class KeyspaceParser implements Parser<List<Keyspace>> {

		@Override
		public List<Keyspace> parse(final String str, final List<Keyspace> keyspaces){
			String[] rows = StringUtils.split(str, ROW_SEPARATOR);

			for(String row : rows){
				String[] v = StringUtils.split(row, ':');
				if(v == null || v.length < 2){
					continue;
				}

				final Keyspace keyspace = new Keyspace();
				String db = StringUtils.replace(v[0], "\n", "").substring(2);
				String[] params = StringUtils.split(v[1], ',');

				keyspace.setDb(Integer.parseInt(db));

				if(params == null){
					keyspaces.add(keyspace);
					continue;
				}

				KeyValueParser paramKeyValueParser;
				for(String s : params){
					paramKeyValueParser = new KeyValueParser(s, '=');

					if("keys".equals(paramKeyValueParser.getKey())){
						keyspace.setKeys(paramKeyValueParser.getIntValue());
					}else if("expires".equals(paramKeyValueParser.getKey())){
						keyspace.setExpires(paramKeyValueParser.getIntValue());
					}else if("avg_ttl".equals(paramKeyValueParser.getKey())){
						keyspace.setAvgTtl(paramKeyValueParser.getLongValue());
					}
				}

				keyspaces.add(keyspace);
			}

			return keyspaces;
		}

	}

	protected static class KeyValueParser extends com.buession.core.utils.KeyValueParser {

		private Double percentValue;

		public KeyValueParser(final String str, final String delimiter){
			super(str, delimiter);
		}

		public KeyValueParser(final String str, final char delimiter){
			super(str, delimiter);
		}

		public Double getPercentValue(){
			if(percentValue == null){
				percentValue = Double.parseDouble(getValue().substring(0, getValue().length() - 1));
			}

			return percentValue;
		}

	}

}

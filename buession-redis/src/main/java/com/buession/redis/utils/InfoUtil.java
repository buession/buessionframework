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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.utils;

import com.buession.core.Status;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.Clients;
import com.buession.redis.core.Cluster;
import com.buession.redis.core.Cpu;
import com.buession.redis.core.Info;
import com.buession.redis.core.Keyspace;
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
                final ServerCompiler serverCompiler = new ServerCompiler();

                info.setServer(serverCompiler.compile(groupValue, server));
            }else if("Cluster".equalsIgnoreCase(groupName)){
                final ClusterCompiler clusterCompiler = new ClusterCompiler();

                info.setCluster(clusterCompiler.compile(groupValue, cluster));
            }else if("Clients".equalsIgnoreCase(groupName)){
                final ClientsCompiler clientsCompiler = new ClientsCompiler();

                info.setClients(clientsCompiler.compile(groupValue, clients));
            }else if("CPU".equalsIgnoreCase(groupName)){
                final CpuCompiler cpuCompiler = new CpuCompiler();

                info.setCpu(cpuCompiler.compile(groupValue, cpu));
            }else if("Memory".equalsIgnoreCase(groupName)){
                final MemoryCompiler memoryCompiler = new MemoryCompiler();

                info.setMemory(memoryCompiler.compile(groupValue, memory));
            }else if("Persistence".equalsIgnoreCase(groupName)){
                final PersistenceCompiler persistenceCompiler = new PersistenceCompiler();

                info.setPersistence(persistenceCompiler.compile(groupValue, persistence));
            }else if("Stats".equalsIgnoreCase(groupName)){
                final StatsCompiler statsCompiler = new StatsCompiler();

                info.setStats(statsCompiler.compile(groupValue, stats));
            }else if("Replication".equalsIgnoreCase(groupName)){
                final ReplicationCompiler replicationCompiler = new ReplicationCompiler();

                info.setReplication(replicationCompiler.compile(groupValue, replication));

                server.setMaster(replication.getRole() == Role.MASTER);
            }else if("Keyspace".equalsIgnoreCase(groupName)){
                final KeyspaceCompiler keyspaceCompiler = new KeyspaceCompiler();

                info.setKeyspace(keyspaceCompiler.compile(groupValue, keyspace));
            }
        }

        return info;
    }

    protected interface Compiler<T> {

        T compile(final String str, final T t);

    }

    protected final static class ServerCompiler implements Compiler<Server> {

        @Override
        public Server compile(final String str, final Server server){
            Server.Uptime uptime = new Server.Uptime();
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("redis_version".equals(v[0])){
                    server.setVersion(v[1]);
                }else if("redis_git_sha1".equals(v[0])){
                    server.setGitSha1(v[1]);
                }else if("redis_git_dirty".equals(v[0])){
                    server.setGitDirty(v[1]);
                }else if("redis_build_id".equals(v[0])){
                    server.setBuildId(v[1]);
                }else if("redis_mode".equals(v[0])){
                    try{
                        server.setMode(Enum.valueOf(Server.Mode.class, v[1].toUpperCase()));
                    }catch(IllegalArgumentException e){

                    }
                }else if("os".equals(v[0])){
                    server.setOs(v[1]);
                }else if("arch_bits".equals(v[0])){
                    if("32".equals(v[1])){
                        server.setArch(Server.Arch.ARCH_32);
                    }else if("64".equals(v[1])){
                        server.setArch(Server.Arch.ARCH_64);
                    }
                }else if("multiplexing_api".equals(v[0])){
                    try{
                        server.setMultiplexingApi(Enum.valueOf(Server.Multiplexing.class, v[1].toUpperCase()));
                    }catch(IllegalArgumentException e){

                    }
                }else if("gcc_version".equals(v[0])){
                    server.setGccVersion(v[1]);
                }else if("process_id".equals(v[0])){
                    server.setProcessId(Integer.valueOf(v[1]));
                }else if("run_id".equals(v[0])){
                    server.setRunId(v[1]);
                }else if("tcp_port".equals(v[0])){
                    server.setPort(Integer.valueOf(v[1]));
                }else if("uptime_in_seconds".equals(v[0])){
                    uptime.setSeconds(Integer.valueOf(v[1]));
                }else if("uptime_in_days".equals(v[0])){
                    uptime.setDays(Integer.valueOf(v[1]));
                }else if("hz".equals(v[0])){
                    server.setHz(Integer.valueOf(v[1]));
                }else if("lru_clock".equals(v[0])){
                    server.setLruClock(Integer.valueOf(v[1]));
                }else if("executable".equals(v[0])){
                    server.setExecutable(v[1]);
                }else if("config_file".equals(v[0])){
                    server.setConfigFile(v[1]);
                }
            }

            if(uptime.getSeconds() > 0){
                server.setUptime(uptime);
            }

            return server;
        }
    }

    protected final static class ClusterCompiler implements Compiler<Cluster> {

        @Override
        public Cluster compile(final String str, final Cluster cluster){
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("cluster_enabled".equals(v[0])){
                    cluster.setEnabled(Boolean.valueOf(v[1]));
                }
            }

            return cluster;
        }
    }

    protected final static class ReplicationCompiler implements Compiler<Replication> {

        @Override
        public Replication compile(final String str, final Replication replication){
            Replication.ReplBacklog replBacklog = new Replication.ReplBacklog();
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("role".equals(v[0])){
                    try{
                        replication.setRole(Enum.valueOf(Role.class, v[1].toUpperCase()));
                    }catch(IllegalArgumentException e){

                    }
                }else if("connected_slaves".equals(v[0])){
                    replication.setConnectedSlaves(Integer.valueOf(v[1]));
                }else if("master_repl_offset".equals(v[0])){
                    replication.setMasterReplOffset(Integer.valueOf(v[1]));
                }else if("repl_backlog_active".equals(v[0])){
                    replBacklog.setActive(Integer.valueOf(v[1]));
                }else if("repl_backlog_size".equals(v[0])){
                    replBacklog.setSize(Integer.valueOf(v[1]));
                }else if("repl_backlog_first_byte_offset".equals(v[0])){
                    replBacklog.setFirstByteOffset(Integer.valueOf(v[1]));
                }else if("repl_backlog_histlen".equals(v[0])){
                    replBacklog.setHistlen(Integer.valueOf(v[1]));
                }
            }

            replication.setReplBacklog(replBacklog);

            return replication;
        }
    }

    protected final static class ClientsCompiler implements Compiler<Clients> {

        @Override
        public Clients compile(final String str, final Clients clients){
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("connected_clients".equals(v[0])){
                    clients.setConnecteds(Integer.valueOf(v[1]));
                }else if("client_longest_output_list".equals(v[0])){
                    clients.setLongestOutputList(Integer.valueOf(v[1]));
                }else if("client_biggest_input_buf".equals(v[0])){
                    clients.setBiggestInputBuffer(Integer.valueOf(v[1]));
                }else if("blocked_clients".equals(v[0])){
                    clients.setBlockeds(Integer.valueOf(v[1]));
                }
            }

            return clients;
        }
    }

    protected final static class CpuCompiler implements Compiler<Cpu> {

        @Override
        public Cpu compile(final String str, final Cpu cpu){
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("used_cpu_sys".equals(v[0])){
                    cpu.setUsedCpuSys(Double.valueOf(v[1]));
                }else if("used_cpu_user".equals(v[0])){
                    cpu.setUsedCpuUser(Double.valueOf(v[1]));
                }else if("used_cpu_sys_children".equals(v[0])){
                    cpu.setUsedCpuSysChildren(Double.valueOf(v[1]));
                }else if("blocked_clients".equals(v[0])){
                    cpu.setUsedCpuUserChildren(Double.valueOf(v[1]));
                }
            }

            return cpu;
        }
    }

    protected final static class MemoryCompiler implements Compiler<Memory> {

        @Override
        public Memory compile(final String str, final Memory memory){
            Memory.MemoryInfo usedMemory = new Memory.MemoryInfo();
            Memory.MemoryInfo rssMemory = new Memory.MemoryInfo();
            Memory.MemoryInfo peakMemory = new Memory.MemoryInfo();
            Memory.MemoryInfo totalSystemMemory = new Memory.MemoryInfo();
            Memory.MemoryInfo luaUsedMemory = new Memory.MemoryInfo();
            Memory.MemoryInfo maxMemory = new Memory.MemoryInfo();
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("used_memory".equals(v[0])){
                    usedMemory.setValue(Integer.valueOf(v[1]));
                }else if("used_memory_human".equals(v[0])){
                    usedMemory.setHuman(v[1]);
                }else if("used_memory_rss".equals(v[0])){
                    rssMemory.setValue(Integer.valueOf(v[1]));
                }else if("used_memory_rss_human".equals(v[0])){
                    rssMemory.setHuman(v[1]);
                }else if("used_memory_peak".equals(v[0])){
                    peakMemory.setValue(Integer.valueOf(v[1]));
                }else if("used_memory_peak_human".equals(v[0])){
                    peakMemory.setHuman(v[1]);
                }else if("total_system_memory".equals(v[0])){
                    totalSystemMemory.setValue(Integer.valueOf(v[1]));
                }else if("total_system_memory_human".equals(v[0])){
                    totalSystemMemory.setHuman(v[1]);
                }else if("used_memory_lua".equals(v[0])){
                    luaUsedMemory.setValue(Integer.valueOf(v[1]));
                }else if("used_memory_lua_human".equals(v[0])){
                    luaUsedMemory.setHuman(v[1]);
                }else if("maxmemory".equals(v[0])){
                    maxMemory.setValue(Integer.valueOf(v[1]));
                }else if("maxmemory_human".equals(v[0])){
                    maxMemory.setHuman(v[1]);
                }else if("maxmemory_policy".equals(v[0])){
                    memory.setMaxMemoryPolicy(v[1]);
                }else if("mem_fragmentation_ratio".equals(v[0])){
                    memory.setMemFragmentationTatio(v[1]);
                }else if("mem_allocator".equals(v[0])){
                    memory.setMemAllocator(v[1]);
                }
            }

            memory.setUsedMemory(usedMemory);
            memory.setUsedMemoryRss(rssMemory);
            memory.setUsedMemoryPeak(peakMemory);
            memory.setTotalSystemMemory(totalSystemMemory);
            memory.setUsedMemoryLua(luaUsedMemory);
            memory.setMaxMemory(maxMemory);

            return memory;
        }
    }

    protected final static class PersistenceCompiler implements Compiler<Persistence> {

        @Override
        public Persistence compile(final String str, final Persistence persistence){
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("loading".equals(v[0])){
                    persistence.setLoading(Boolean.valueOf(v[1]));
                }else if("rdb_changes_since_last_save".equals(v[0])){
                    persistence.setRdbChangesSinceLastSave(Integer.valueOf(v[1]));
                }else if("rdb_bgsave_in_progress".equals(v[0])){
                    persistence.setRdbBgSaveInProgress(Boolean.valueOf(v[1]));
                }else if("rdb_last_save_time".equals(v[0])){
                    Date date = new Date();

                    date.setTime(Integer.valueOf(v[1]) * 1000);
                    persistence.setRdbLastSaveTime(date);
                }else if("rdb_last_bgsave_status".equals(v[0])){
                    persistence.setRdbLastBgSaveStatus(Status.valueOf("OK".equalsIgnoreCase(v[1])));
                }else if("rdb_last_bgsave_time_sec".equals(v[0])){
                    persistence.setRdbLastBgSaveTimeSec(Integer.valueOf(v[1]));
                }else if("rdb_current_bgsave_time_sec".equals(v[0])){
                    persistence.setRdbCurrentBgSaveTimeSec(Integer.valueOf(v[1]));
                }else if("aof_enabled".equals(v[0])){
                    persistence.setAofEnabled(Boolean.valueOf(v[1]));
                }else if("aof_rewrite_in_progress".equals(v[0])){
                    persistence.setAofRewriteInProgress(Boolean.valueOf(v[1]));
                }else if("aof_rewrite_scheduled".equals(v[0])){
                    persistence.setAofRewriteScheduled(Boolean.valueOf(v[1]));
                }else if("aof_last_rewrite_time_sec".equals(v[0])){
                    persistence.setAofLastRewriteTimeSec(Integer.valueOf(v[1]));
                }else if("aof_current_rewrite_time_sec".equals(v[0])){
                    persistence.setAofCurrentRewriteTimeSec(Integer.valueOf(v[1]));
                }else if("aof_last_bgrewrite_status".equals(v[0])){
                    persistence.setAofLastBgRewriteStatus(Status.valueOf("OK".equalsIgnoreCase(v[1])));
                }else if("aof_last_write_status".equals(v[0])){
                    persistence.setAofLastWriteStatus(Status.valueOf("OK".equalsIgnoreCase(v[1])));
                }
            }

            return persistence;
        }
    }

    protected final static class StatsCompiler implements Compiler<Stats> {

        @Override
        public Stats compile(final String str, final Stats stats){
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");

                if("total_connections_received".equals(v[0])){
                    stats.setTotalConnectionsReceived(Integer.valueOf(v[1]));
                }else if("total_commands_processed".equals(v[0])){
                    stats.setTotalCommandsProcessed(Integer.valueOf(v[1]));
                }else if("total_net_input_bytes".equals(v[0])){
                    stats.setTotalNetInputBytes(Integer.valueOf(v[1]));
                }else if("total_net_output_bytes".equals(v[0])){
                    stats.setTotalNetOutputBytes(Integer.valueOf(v[1]));
                }else if("instantaneous_input_kbps".equals(v[0])){
                    stats.setInstantaneousInputKbps(Double.valueOf(v[1]));
                }else if("instantaneous_output_kbps".equals(v[0])){
                    stats.setInstantaneousOutputKbps(Double.valueOf(v[1]));
                }else if("instantaneous_ops_per_sec".equals(v[0])){
                    stats.setInstantaneousOpsPerSec(Integer.valueOf(v[1]));
                }else if("rejected_connections".equals(v[0])){
                    stats.setRejectedConnections(Integer.valueOf(v[1]));
                }else if("sync_full".equals(v[0])){
                    stats.setSyncFull(Integer.valueOf(v[1]));
                }else if("sync_partial_ok".equals(v[0])){
                    stats.setSyncPartialOk(Integer.valueOf(v[1]));
                }else if("sync_partial_err".equals(v[0])){
                    stats.setSyncPartialErr(Integer.valueOf(v[1]));
                }else if("expired_keys".equals(v[0])){
                    stats.setExpiredKeys(Integer.valueOf(v[1]));
                }else if("evicted_keys".equals(v[0])){
                    stats.setEvictedKeys(Integer.valueOf(v[1]));
                }else if("keyspace_hits".equals(v[0])){
                    stats.setKeyspaceHits(Integer.valueOf(v[1]));
                }else if("keyspace_misses".equals(v[0])){
                    stats.setKeyspaceMisses(Integer.valueOf(v[1]));
                }else if("pubsub_channels".equals(v[0])){
                    stats.setPubsubChannels(Integer.valueOf(v[1]));
                }else if("pubsub_patterns".equals(v[0])){
                    stats.setPubsubPatterns(Integer.valueOf(v[1]));
                }else if("latest_fork_usec".equals(v[0])){
                    stats.setLatestForkUsec(Integer.valueOf(v[1]));
                }else if("migrate_cached_sockets".equals(v[0])){
                    stats.setMigrateCachedSockets(Integer.valueOf(v[1]));
                }
            }

            return stats;
        }
    }

    protected final static class KeyspaceCompiler implements Compiler<List<Keyspace>> {

        @Override
        public List<Keyspace> compile(final String str, final List<Keyspace> keyspaces){
            String[] rows = str.split(ROW_SEPARATOR);

            for(String row : rows){
                String[] v = StringUtils.split(row, ":");
                if(v == null || v.length < 2){
                    continue;
                }

                final Keyspace keyspace = new Keyspace();
                String db = StringUtils.replace(StringUtils.replace(v[0], "\n", ""), "db", "");
                String[] params = v[1].split(",");

                keyspace.setDb(Integer.valueOf(db));

                if(params == null){
                    continue;
                }

                for(String s : params){
                    if(s.startsWith("keys=")){
                        s = StringUtils.replace(s, "keys=", "");
                        keyspace.setKeys(Integer.valueOf(s));
                    }else if(s.startsWith("expires=")){
                        s = StringUtils.replace(s, "expires=", "");
                        keyspace.setExpires(Integer.valueOf(s));
                    }else if(s.startsWith("avg_ttl=")){
                        s = StringUtils.replace(s, "avg_ttl=", "");
                        keyspace.setAvgTtl(Integer.valueOf(s));
                    }
                }

                keyspaces.add(keyspace);
            }

            return keyspaces;
        }

    }

}

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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.KeyValueParser;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Arch;
import com.buession.lang.Uptime;
import com.buession.net.Multiplexing;
import com.buession.redis.core.AtomicvarApi;
import com.buession.redis.core.Info;
import com.buession.redis.core.MaxMemoryPolicy;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.Role;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Info 转换
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class InfoConverter implements Converter<String, Info> {

	private final static Pattern PATTERN = Pattern.compile("# (\\S+)[\\s]+([^#]+)");

	public final static InfoConverter INSTANCE = new InfoConverter();

	@Override
	public Info convert(final String source) {
		Info.Server server = null;
		Info.Clients clients = null;
		Info.Memory memory = null;
		Info.Persistence persistence = null;
		Info.Stats stats = null;
		Info.Replication replication = null;
		Info.Cpu cpu = null;
		Info.Cluster cluster = null;
		Info.Sentinel sentinel = null;
		List<Info.Keyspace> keyspaces = null;

		Matcher matcher = PATTERN.matcher(source);

		while(matcher.find()){
			String groupName = matcher.group(1);
			String groupValue = matcher.group(2);

			if("Server".equalsIgnoreCase(groupName)){
				final ServerParser serverParser = new ServerParser();
				server = serverParser.parse(groupValue);
			}else if("Clients".equalsIgnoreCase(groupName)){
				final ClientsParser clientsParser = new ClientsParser();
				clients = clientsParser.parse(groupValue);
			}else if("Memory".equalsIgnoreCase(groupName)){
				final MemoryParser memoryParser = new MemoryParser();
				memory = memoryParser.parse(groupValue);
			}else if("Persistence".equalsIgnoreCase(groupName)){
				final PersistenceParser persistenceParser = new PersistenceParser();
				persistence = persistenceParser.parse(groupValue);
			}else if("Stats".equalsIgnoreCase(groupName)){
				final StatsParser statsParser = new StatsParser();
				stats = statsParser.parse(groupValue);
			}else if("Replication".equalsIgnoreCase(groupName)){
				final ReplicationParser replicationParser = new ReplicationParser();
				replication = replicationParser.parse(groupValue);
			}else if("CPU".equalsIgnoreCase(groupName)){
				final CpuParser cpuParser = new CpuParser();
				cpu = cpuParser.parse(groupValue);
			}else if("Cluster".equalsIgnoreCase(groupName)){
				final ClusterParser clusterParser = new ClusterParser();
				cluster = clusterParser.parse(groupValue);
			}else if("Sentinel".equalsIgnoreCase(groupName)){
				final SentinelParser keyspaceParser = new SentinelParser();
				sentinel = keyspaceParser.parse(groupValue);
			}else if("Keyspace".equalsIgnoreCase(groupName)){
				final KeyspaceParser keyspaceParser = new KeyspaceParser();
				keyspaces = keyspaceParser.parse(groupValue);
			}
		}

		return new Info(server, clients, memory, persistence, stats, replication, sentinel, cpu, cluster, keyspaces);
	}

	@FunctionalInterface
	private interface Parser<V> {

		@Nullable
		V parse(final String str);

		default String[] parseRows(final String str) {
			return StringUtils.split(str, "\r\n");
		}

	}

	private final static class ServerParser implements Parser<Info.Server> {

		@Override
		public Info.Server parse(final String str) {
			Uptime uptime = new Uptime();
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if(Info.Server.Key.ARCH.getValue().equals(keyValueParser.getKey())){
					if("32".equals(keyValueParser.getValue())){
						properties.put(keyValueParser.getKey(), Arch.BIT_32);
					}else if("64".equals(keyValueParser.getValue())){
						properties.put(keyValueParser.getKey(), Arch.BIT_64);
					}
				}else if(Info.Server.Key.ATOMICVAR_API.getValue().equals(keyValueParser.getKey())){
					for(AtomicvarApi v : AtomicvarApi.values()){
						if(v.getValue().equals(keyValueParser.getValue())){
							properties.put(keyValueParser.getKey(), v);
							break;
						}
					}
				}else if(Info.Server.Key.MODE.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(), keyValueParser.getEnumValue(RedisMode.class));
				}else if(Info.Server.Key.MULTIPLEXING_API.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(), keyValueParser.getEnumValue(Multiplexing.class));
				}else if("uptime_in_seconds".equals(keyValueParser.getKey())){
					uptime.setSeconds(keyValueParser.getIntValue());
				}else if("uptime_in_days".equals(keyValueParser.getKey())){
					uptime.setDays(keyValueParser.getIntValue());
				}else{
					properties.setProperty(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			properties.put(Info.Server.Key.UPTIME, uptime);

			return new Info.Server(properties);
		}

	}

	private final static class ClientsParser implements Parser<Info.Clients> {

		@Override
		public Info.Clients parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');
				properties.put(keyValueParser.getKey(), keyValueParser.getValue());
			}

			return new Info.Clients(properties);
		}

	}

	private final static class MemoryParser implements Parser<Info.Memory> {

		@Override
		public Info.Memory parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if(Info.Memory.Key.MAX_MEMORY_POLICY.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(), EnumUtils.valueOf(MaxMemoryPolicy.class,
							StringUtils.replace(keyValueParser.getValue().toUpperCase(), "-", "_")));
				}else{
					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			return new Info.Memory(properties);
		}

	}

	private final static class PersistenceParser implements Parser<Info.Persistence> {

		@Override
		public Info.Persistence parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if(Info.Persistence.Key.AOF_LAST_BGREWRITE_STATUS.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(), keyValueParser.getStatusValue());
				}else if(Info.Persistence.Key.RDB_LAST_BGSAVE_STATUS.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(), keyValueParser.getStatusValue());
				}else if(Info.Persistence.Key.AOF_LAST_WRITE_STATUS.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(), keyValueParser.getStatusValue());
				}else{
					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			return new Info.Persistence(properties);
		}

	}

	private final static class StatsParser implements Parser<Info.Stats> {

		@Override
		public Info.Stats parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');
				properties.put(keyValueParser.getKey(), keyValueParser.getValue());
			}

			return new Info.Stats(properties);
		}

	}

	private final static class ReplicationParser implements Parser<Info.Replication> {

		private final static Pattern SLAVE_PATTERN = Pattern.compile("slave\\d");

		@Override
		public Info.Replication parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			Properties replBackLogProperties = null;
			String masterHost = null;
			int masterPort = RedisNode.DEFAULT_PORT;

			List<Info.Replication.Slave> slaves = null;
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');

				if("master_host".equals(keyValueParser.getKey())){
					masterHost = keyValueParser.getValue();
				}else if("master_port".equals(keyValueParser.getKey())){
					masterPort = keyValueParser.getIntValue();
				}else if(keyValueParser.isKey(SLAVE_PATTERN)){
					if(slaves == null){
						slaves = new ArrayList<>();
					}

					slaves.add(parseSlave(keyValueParser.getValue()));
				}else if(Info.Replication.Key.MASTER_LINK_STATUS.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(),
							keyValueParser.getEnumValue(Info.Replication.MasterLinkStatus.class));
				}else if(Info.Replication.ReplBacklog.Key.ACTIVE.getValue().equals(keyValueParser.getKey())){
					if(replBackLogProperties == null){
						replBackLogProperties = new Properties();
					}

					replBackLogProperties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}else if(Info.Replication.ReplBacklog.Key.SIZE.getValue().equals(keyValueParser.getKey())){
					if(replBackLogProperties == null){
						replBackLogProperties = new Properties();
					}

					replBackLogProperties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}else if(Info.Replication.ReplBacklog.Key.FIRST_BYTE_OFFSET.getValue().equals(keyValueParser.getKey())){
					if(replBackLogProperties == null){
						replBackLogProperties = new Properties();
					}

					replBackLogProperties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}else if(Info.Replication.ReplBacklog.Key.HISTLEN.getValue().equals(keyValueParser.getKey())){
					if(replBackLogProperties == null){
						replBackLogProperties = new Properties();
					}

					replBackLogProperties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}else if(Info.Replication.Key.ROLE.getValue().equals(keyValueParser.getKey())){
					properties.put(Info.Replication.Key.ROLE.getValue(), keyValueParser.getEnumValue(Role.class));
				}else{
					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			if(masterHost != null){
				properties.put(Info.Replication.Key.MASTER.getValue(), new RedisNode(masterHost, masterPort));
			}

			if(slaves != null){
				properties.put(Info.Replication.Key.SLAVE.getValue(), slaves);
			}

			if(replBackLogProperties != null){
				properties.put(Info.Replication.Key.REPL_BACK_LOG.getValue(),
						new Info.Replication.ReplBacklog(replBackLogProperties));
			}

			return new Info.Replication(properties);
		}

		private static Info.Replication.Slave parseSlave(final String str) {
			String[] groups = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, ",");
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String group : groups){
				keyValueParser = new KeyValueParser(group, '=');

				if(Info.Replication.Slave.Key.STATE.getValue().equals(keyValueParser.getKey())){
					properties.put(keyValueParser.getKey(),
							keyValueParser.getEnumValue(Info.Replication.Slave.SlaveState.class));
				}else{
					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			return new Info.Replication.Slave(properties);
		}

	}

	private final static class CpuParser implements Parser<Info.Cpu> {

		@Override
		public Info.Cpu parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');
				properties.put(keyValueParser.getKey(), keyValueParser.getValue());
			}

			return new Info.Cpu(properties);
		}

	}

	private final static class ClusterParser implements Parser<Info.Cluster> {

		@Override
		public Info.Cluster parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');
				properties.put(keyValueParser.getKey(), keyValueParser.getBoolValue());
			}

			return new Info.Cluster(properties);
		}

	}

	private final static class SentinelParser implements Parser<Info.Sentinel> {

		private final static Pattern MASTER_PATTERN = Pattern.compile("master\\d");

		@Override
		public Info.Sentinel parse(final String str) {
			String[] rows = parseRows(str);
			Properties properties = new Properties();
			List<Info.Sentinel.Master> masters = null;
			KeyValueParser keyValueParser;

			for(String row : rows){
				keyValueParser = new KeyValueParser(row, ':');
				if(keyValueParser.isKey(MASTER_PATTERN)){
					if(masters == null){
						masters = new ArrayList<>();
					}

					masters.add(parseMaster(keyValueParser.getValue()));
				}else{
					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			return new Info.Sentinel(properties);
		}

		private static Info.Sentinel.Master parseMaster(final String str) {
			String[] groups = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, ",");
			Properties properties = new Properties();
			KeyValueParser keyValueParser;

			for(String group : groups){
				keyValueParser = new KeyValueParser(group, '=');

				if(Info.Sentinel.Master.Key.ADDRESS.getValue().equals(keyValueParser.getKey())){
					KeyValueParser addressKeyValueParser = new KeyValueParser(group, ':');

					properties.setProperty(Info.Sentinel.Master.Key.HOST.getValue(), addressKeyValueParser.getKey());
					properties.setProperty(Info.Sentinel.Master.Key.PORT.getValue(), addressKeyValueParser.getValue());

					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}else{
					properties.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}

			return new Info.Sentinel.Master(properties);
		}

	}

	private final static class KeyspaceParser implements Parser<List<Info.Keyspace>> {

		@Override
		public List<Info.Keyspace> parse(final String str) {
			String[] rows = parseRows(str);
			KeyValueParser paramKeyValueParser;
			Properties properties;
			List<Info.Keyspace> keyspaces = new ArrayList<>(rows.length);

			for(String row : rows){
				String[] v = StringUtils.splitByWholeSeparatorPreserveAllTokens(row, ":");
				if(v == null || v.length < 2){
					continue;
				}

				properties = new Properties();
				String db = StringUtils.replace(v[0], "\n", "").substring(2);
				String[] params = StringUtils.splitByWholeSeparatorPreserveAllTokens(v[1], ",");

				properties.put(Info.Keyspace.Key.DB.getValue(), db);

				if(params != null){
					for(String s : params){
						paramKeyValueParser = new KeyValueParser(s, '=');
						properties.put(paramKeyValueParser.getKey(), paramKeyValueParser.getValue());
					}
				}

				keyspaces.add(new Info.Keyspace(properties));
			}

			return keyspaces;
		}

	}

}

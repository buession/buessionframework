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
package com.buession.redis.core.internal.convert.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.Info;
import com.buession.redis.exception.RedisException;

import java.io.StringReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Info 转换
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class InfoConverter implements Converter<String, Info> {

	private final static Pattern PATTERN = Pattern.compile("# (\\S+)[\\s]+([^#]+)");

	@Override
	public Info convert(final String source) {
		Info.Server server = null;
		Info.Clients clients = null;
		Info.Memory memory = null;
		Info.Persistence persistence = null;
		Info.Threads threads = null;
		Info.Stats stats = null;
		Info.Replication replication = null;
		Info.Cpu cpu = null;
		Info.CommandStats commandStats = null;
		Info.LatencyStats latencyStats = null;
		Info.Sentinel sentinel = null;
		Info.Cluster cluster = null;
		Info.Modules modules = null;
		Info.Keyspace keyspace = null;
		Info.KeySizes keySizes = null;
		Info.ErrorStats errorStats = null;
		Info.HotKeys hotKeys = null;

		Matcher matcher = PATTERN.matcher(source);

		while(matcher.find()){
			String groupName = matcher.group(1);
			String groupValue = matcher.group(2);

			if("Server".equalsIgnoreCase(groupName)){
				server = new Info.Server(parse(groupValue));
			}else if("Clients".equalsIgnoreCase(groupName)){
				clients = new Info.Clients(parse(groupValue));
			}else if("Memory".equalsIgnoreCase(groupName)){
				memory = new Info.Memory(parse(groupValue));
			}else if("Persistence".equalsIgnoreCase(groupName)){
				persistence = new Info.Persistence(parse(groupValue));
			}else if("Threads".equalsIgnoreCase(groupName)){
				threads = new Info.Threads(parse(groupValue));
			}else if("Stats".equalsIgnoreCase(groupName)){
				stats = new Info.Stats(parse(groupValue));
			}else if("Replication".equalsIgnoreCase(groupName)){
				replication = new Info.Replication(parse(groupValue));
			}else if("CPU".equalsIgnoreCase(groupName)){
				cpu = new Info.Cpu(parse(groupValue));
			}else if("Commandstats".equalsIgnoreCase(groupName)){
				commandStats = new Info.CommandStats(parse(groupValue));
			}else if("Latencystats".equalsIgnoreCase(groupName)){
				latencyStats = new Info.LatencyStats(parse(groupValue));
			}else if("Sentinel".equalsIgnoreCase(groupName)){
				sentinel = new Info.Sentinel(parse(groupValue));
			}else if("Cluster".equalsIgnoreCase(groupName)){
				cluster = new Info.Cluster(parse(groupValue));
			}else if("Modules".equalsIgnoreCase(groupName)){
				modules = new Info.Modules(parse(groupValue));
			}else if("Keyspace".equalsIgnoreCase(groupName)){
				keyspace = new Info.Keyspace(parse(groupValue));
			}else if("Keysizes".equalsIgnoreCase(groupName)){
				keySizes = new Info.KeySizes(parse(groupValue));
			}else if("Errorstats".equalsIgnoreCase(groupName)){
				errorStats = new Info.ErrorStats(parse(groupValue));
			}else if("Hotkeys".equalsIgnoreCase(groupName)){
				hotKeys = new Info.HotKeys(parse(groupValue));
			}
		}

		return new Info(server, clients, memory, persistence, threads, stats, replication, cpu, commandStats,
				latencyStats, sentinel, cluster, modules, keyspace, keySizes, errorStats, hotKeys);
	}

	private static Properties parse(final String str) {
		final Properties info = new Properties();

		try(StringReader stringReader = new StringReader(str)){
			info.load(stringReader);
		}catch(Exception ex){
			throw new RedisException("Cannot read Redis info", ex);
		}

		return info;
	}

}

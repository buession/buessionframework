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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.MemoryStats;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Jedis memory-stats 命令结果转换
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class MemoryStatsConverter implements Converter<Map<String, Object>, MemoryStats> {

	@Override
	public MemoryStats convert(final Map<String, Object> source) {
		if(source == null){
			return null;
		}

		Long dataset = null;
		Double datasetPercentage = null;
		Long rssOverhead = null;
		Double rssOverheadRatio = null;
		Double peakPercentage = null;
		Long aofBuffer = null;
		Long keysBytesPerKey = null;
		Long allocatorAllocated = null;
		Long allocatorFragmentation = null;
		Double allocatorFragmentationRatio = null;
		Long allocatorActive = null;
		Long allocatorRss = null;
		Double allocatorRssRatio = null;
		Long allocatorResident = null;
		Long clientsNormal = null;
		Long clientsSlaves = null;
		Long fragmentation = null;
		Double fragmentationRatio = null;
		Long luaCaches = null;
		Long peakAllocated = null;
		Long totalAllocated = null;
		Long replicationBacklog = null;
		Long startupAllocated = null;
		Long overheadTotal = null;
		Long keysCount = null;
		Map<Integer, MemoryStats.Db> dbs = null;

		for(Map.Entry<String, Object> e : source.entrySet()){
			if("dataset.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					dataset = Long.parseLong(e.getValue().toString());
				}
			}else if("dataset.percentage".equals(e.getKey())){
				if(e.getValue() != null){
					datasetPercentage = Double.parseDouble(e.getValue().toString());
				}
			}else if("rss-overhead.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					rssOverhead = Long.parseLong(e.getValue().toString());
				}
			}else if("rss-overhead.ratio".equals(e.getKey())){
				if(e.getValue() != null){
					rssOverheadRatio = Double.parseDouble(e.getValue().toString());
				}
			}else if("peak.percentage".equals(e.getKey())){
				if(e.getValue() != null){
					peakPercentage = Double.parseDouble(e.getValue().toString());
				}
			}else if("aof.buffer".equals(e.getKey())){
				if(e.getValue() != null){
					aofBuffer = Long.parseLong(e.getValue().toString());
				}
			}else if("keys.bytes-per-key".equals(e.getKey())){
				if(e.getValue() != null){
					keysBytesPerKey = Long.parseLong(e.getValue().toString());
				}
			}else if("allocator.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorAllocated = Long.parseLong(e.getValue().toString());
				}
			}else if("allocator-fragmentation.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorFragmentation = Long.parseLong(e.getValue().toString());
				}
			}else if("allocator-fragmentation.ratio".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorFragmentationRatio = Double.parseDouble(e.getValue().toString());
				}
			}else if("allocator.active".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorActive = Long.parseLong(e.getValue().toString());
				}
			}else if("allocator-rss.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorRss = Long.parseLong(e.getValue().toString());
				}
			}else if("allocator-rss.ratio".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorRssRatio = Double.parseDouble(e.getValue().toString());
				}
			}else if("allocator.resident".equals(e.getKey())){
				if(e.getValue() != null){
					allocatorResident = Long.parseLong(e.getValue().toString());
				}
			}else if("clients.normal".equals(e.getKey())){
				if(e.getValue() != null){
					clientsNormal = Long.parseLong(e.getValue().toString());
				}
			}else if("clients.slaves".equals(e.getKey())){
				if(e.getValue() != null){
					clientsSlaves = Long.parseLong(e.getValue().toString());
				}
			}else if("fragmentation.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					fragmentation = Long.parseLong(e.getValue().toString());
				}
			}else if("fragmentation".equals(e.getKey())){
				if(e.getValue() != null){
					fragmentationRatio = Double.parseDouble(e.getValue().toString());
				}
			}else if("lua.caches".equals(e.getKey())){
				if(e.getValue() != null){
					luaCaches = Long.parseLong(e.getValue().toString());
				}
			}else if("peak.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					peakAllocated = Long.parseLong(e.getValue().toString());
				}
			}else if("total.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					totalAllocated = Long.parseLong(e.getValue().toString());
				}
			}else if("replication.backlog".equals(e.getKey())){
				if(e.getValue() != null){
					replicationBacklog = Long.parseLong(e.getValue().toString());
				}
			}else if("startup.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					startupAllocated = Long.parseLong(e.getValue().toString());
				}
			}else if("overhead.total".equals(e.getKey())){
				if(e.getValue() != null){
					overheadTotal = Long.parseLong(e.getValue().toString());
				}
			}else if("keys.count".equals(e.getKey())){
				if(e.getValue() != null){
					keysCount = Long.parseLong(e.getValue().toString());
				}
			}else if(e.getKey().startsWith("db.")){
				String[] nameParts = StringUtils.split(e.getKey(), '.');
				int db = Integer.parseInt(nameParts[1]);
				Long overheadHashTableMain = null;
				Long overheadHashTableExpires = null;

				dbs = new TreeMap<>();
				List<Object> dbStatTmp = (List<Object>) e.getValue();

				for(int j = 0, jl = dbStatTmp.size(); j < jl; j++){
					Object v = dbStatTmp.get(j);
					j++;

					if("overhead.hashtable.main".equals(v)){
						if(v instanceof Long){
							overheadHashTableMain = (Long) v;
						}
					}else if("overhead.hashtable.expires".equals(v)){
						if(v instanceof Long){
							overheadHashTableExpires = (Long) v;
						}
					}
				}

				dbs.put(db, new MemoryStats.Db(overheadHashTableMain, overheadHashTableExpires));
			}
		}

		return new MemoryStats(dataset, datasetPercentage, rssOverhead, rssOverheadRatio, peakPercentage, aofBuffer,
				keysBytesPerKey, allocatorAllocated, allocatorFragmentation, allocatorFragmentationRatio,
				allocatorActive, allocatorRss, allocatorRssRatio, allocatorResident, clientsNormal, clientsSlaves,
				fragmentation, fragmentationRatio, luaCaches, peakAllocated, totalAllocated, replicationBacklog,
				startupAllocated, overheadTotal, keysCount, dbs);
	}

}

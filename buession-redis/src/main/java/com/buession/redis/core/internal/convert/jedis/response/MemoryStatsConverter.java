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

		final MemoryStatsBuilder memoryStatsBuilder = new MemoryStatsBuilder();

		for(Map.Entry<String, Object> e : source.entrySet()){
			if("dataset.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setDataset(Long.parseLong(e.getValue().toString()));
				}
			}else if("dataset.percentage".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setDatasetPercentage(Double.parseDouble(e.getValue().toString()));
				}
			}else if("rss-overhead.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setRssOverhead(Long.parseLong(e.getValue().toString()));
				}
			}else if("rss-overhead.ratio".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setRssOverheadRatio(Double.parseDouble(e.getValue().toString()));
				}
			}else if("peak.percentage".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setPeakPercentage(Double.parseDouble(e.getValue().toString()));
				}
			}else if("aof.buffer".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAofBuffer(Long.parseLong(e.getValue().toString()));
				}
			}else if("keys.bytes-per-key".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setKeysBytesPerKey(Long.parseLong(e.getValue().toString()));
				}
			}else if("allocator.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorAllocated(Long.parseLong(e.getValue().toString()));
				}
			}else if("allocator-fragmentation.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorFragmentation(Long.parseLong(e.getValue().toString()));
				}
			}else if("allocator-fragmentation.ratio".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorFragmentationRatio(Double.parseDouble(e.getValue().toString()));
				}
			}else if("allocator.active".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorActive(Long.parseLong(e.getValue().toString()));
				}
			}else if("allocator-rss.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorRss(Long.parseLong(e.getValue().toString()));
				}
			}else if("allocator-rss.ratio".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorRssRatio(Double.parseDouble(e.getValue().toString()));
				}
			}else if("allocator.resident".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setAllocatorResident(Long.parseLong(e.getValue().toString()));
				}
			}else if("clients.normal".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setClientsNormal(Long.parseLong(e.getValue().toString()));
				}
			}else if("clients.slaves".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setClientsSlaves(Long.parseLong(e.getValue().toString()));
				}
			}else if("fragmentation.bytes".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setFragmentation(Long.parseLong(e.getValue().toString()));
				}
			}else if("fragmentation".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setFragmentationRatio(Double.parseDouble(e.getValue().toString()));
				}
			}else if("lua.caches".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setLuaCaches(Long.parseLong(e.getValue().toString()));
				}
			}else if("peak.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setPeakAllocated(Long.parseLong(e.getValue().toString()));
				}
			}else if("total.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setTotalAllocated(Long.parseLong(e.getValue().toString()));
				}
			}else if("replication.backlog".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setReplicationBacklog(Long.parseLong(e.getValue().toString()));
				}
			}else if("startup.allocated".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setStartupAllocated(Long.parseLong(e.getValue().toString()));
				}
			}else if("overhead.total".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setOverheadTotal(Long.parseLong(e.getValue().toString()));
				}
			}else if("keys.count".equals(e.getKey())){
				if(e.getValue() != null){
					memoryStatsBuilder.setKeysCount(Long.parseLong(e.getValue().toString()));
				}
			}else if(e.getKey().startsWith("db.")){
				String[] nameParts = StringUtils.split(e.getKey(), '.');
				int db = Integer.parseInt(nameParts[1]);
				Long overheadHashTableMain = null;
				Long overheadHashTableExpires = null;

				final Map<Integer, MemoryStats.Db> dbs = new TreeMap<>();
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
				memoryStatsBuilder.setDbs(dbs);
			}
		}

		return memoryStatsBuilder.build();
	}

	private final static class MemoryStatsBuilder {

		private Long dataset;

		private Double datasetPercentage;

		private Long rssOverhead;

		private Double rssOverheadRatio;

		private Double peakPercentage;

		private Long aofBuffer;

		private Long keysBytesPerKey;

		private Long allocatorAllocated;

		private Long allocatorFragmentation;

		private Double allocatorFragmentationRatio;

		private Long allocatorActive;

		private Long allocatorRss;

		private Double allocatorRssRatio;

		private Long allocatorResident;

		private Long clientsNormal;

		private Long clientsSlaves;

		private Long fragmentation;

		private Double fragmentationRatio;

		private Long luaCaches;

		private Long peakAllocated;

		private Long totalAllocated;

		private Long replicationBacklog;

		private Long startupAllocated;

		private Long overheadTotal;

		private Long keysCount;

		private Map<Integer, MemoryStats.Db> dbs;

		private MemoryStatsBuilder() {

		}

		public void setDataset(Long dataset) {
			this.dataset = dataset;
		}

		public void setDatasetPercentage(Double datasetPercentage) {
			this.datasetPercentage = datasetPercentage;
		}

		public void setRssOverhead(Long rssOverhead) {
			this.rssOverhead = rssOverhead;
		}

		public void setRssOverheadRatio(Double rssOverheadRatio) {
			this.rssOverheadRatio = rssOverheadRatio;
		}

		public void setPeakPercentage(Double peakPercentage) {
			this.peakPercentage = peakPercentage;
		}

		public void setAofBuffer(Long aofBuffer) {
			this.aofBuffer = aofBuffer;
		}

		public void setKeysBytesPerKey(Long keysBytesPerKey) {
			this.keysBytesPerKey = keysBytesPerKey;
		}

		public void setAllocatorAllocated(Long allocatorAllocated) {
			this.allocatorAllocated = allocatorAllocated;
		}

		public void setAllocatorFragmentation(Long allocatorFragmentation) {
			this.allocatorFragmentation = allocatorFragmentation;
		}

		public void setAllocatorFragmentationRatio(Double allocatorFragmentationRatio) {
			this.allocatorFragmentationRatio = allocatorFragmentationRatio;
		}

		public void setAllocatorActive(Long allocatorActive) {
			this.allocatorActive = allocatorActive;
		}

		public void setAllocatorRss(Long allocatorRss) {
			this.allocatorRss = allocatorRss;
		}

		public void setAllocatorRssRatio(Double allocatorRssRatio) {
			this.allocatorRssRatio = allocatorRssRatio;
		}

		public void setAllocatorResident(Long allocatorResident) {
			this.allocatorResident = allocatorResident;
		}

		public void setClientsNormal(Long clientsNormal) {
			this.clientsNormal = clientsNormal;
		}

		public void setClientsSlaves(Long clientsSlaves) {
			this.clientsSlaves = clientsSlaves;
		}

		public void setFragmentation(Long fragmentation) {
			this.fragmentation = fragmentation;
		}

		public void setFragmentationRatio(Double fragmentationRatio) {
			this.fragmentationRatio = fragmentationRatio;
		}

		public void setLuaCaches(Long luaCaches) {
			this.luaCaches = luaCaches;
		}

		public void setPeakAllocated(Long peakAllocated) {
			this.peakAllocated = peakAllocated;
		}

		public void setTotalAllocated(Long totalAllocated) {
			this.totalAllocated = totalAllocated;
		}

		public void setReplicationBacklog(Long replicationBacklog) {
			this.replicationBacklog = replicationBacklog;
		}

		public void setStartupAllocated(Long startupAllocated) {
			this.startupAllocated = startupAllocated;
		}

		public void setOverheadTotal(Long overheadTotal) {
			this.overheadTotal = overheadTotal;
		}

		public void setKeysCount(Long keysCount) {
			this.keysCount = keysCount;
		}

		public void setDbs(Map<Integer, MemoryStats.Db> dbs) {
			this.dbs = dbs;
		}

		public MemoryStats build() {
			return new MemoryStats(dataset, datasetPercentage, rssOverhead, rssOverheadRatio, peakPercentage, aofBuffer,
					keysBytesPerKey, allocatorAllocated, allocatorFragmentation, allocatorFragmentationRatio,
					allocatorActive, allocatorRss, allocatorRssRatio, allocatorResident, clientsNormal, clientsSlaves,
					fragmentation, fragmentationRatio, luaCaches, peakAllocated, totalAllocated, replicationBacklog,
					startupAllocated, overheadTotal, keysCount, dbs);
		}

	}

}

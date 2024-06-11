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
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.MemoryStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jedis memory-stats 命令结果转换
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class MemoryStatsConverter implements Converter<Map<String, Object>, MemoryStats> {

	@Override
	public MemoryStats convert(final Map<String, Object> source) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final MemoryStats memoryStats = new MemoryStats();

		source.forEach((name, value)->{
			if("dataset.bytes".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setDataset);
			}else if("dataset.percentage".equals(name)){
				propertyMapper.from(value).as((v)->Double.parseDouble(v.toString()))
						.to(memoryStats::setDatasetPercentage);
			}else if("rss-overhead.bytes".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setRssOverhead);
			}else if("rss-overhead.ratio".equals(name)){
				propertyMapper.from(value).as((v)->Double.parseDouble(v.toString()))
						.to(memoryStats::setRssOverheadRatio);
			}else if("peak.percentage".equals(name)){
				propertyMapper.from(value).as((v)->Double.parseDouble(v.toString())).to(memoryStats::setPeakPercentage);
			}else if("aof.buffer".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setAofBuffer);
			}else if("keys.bytes-per-key".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setKeysBytesPerKey);
			}else if("allocator.allocated".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setAllocatorAllocated);
			}else if("allocator-fragmentation.bytes".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setAllocatorFragmentation);
			}else if("allocator-fragmentation.ratio".equals(name)){
				propertyMapper.from(value).as((v)->Double.parseDouble(v.toString()))
						.to(memoryStats::setAllocatorFragmentationRatio);
			}else if("allocator.active".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setAllocatorActive);
			}else if("allocator-rss.bytes".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setAllocatorRss);
			}else if("allocator-rss.ratio".equals(name)){
				propertyMapper.from(value).as((v)->Double.parseDouble(v.toString()))
						.to(memoryStats::setAllocatorRssRatio);
			}else if("allocator.resident".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setAllocatorResident);
			}else if("clients.normal".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setClientsNormal);
			}else if("clients.slaves".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setClientsSlaves);
			}else if("fragmentation.bytes".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setFragmentation);
			}else if("fragmentation".equals(name)){
				propertyMapper.from(value).as((v)->Double.parseDouble(v.toString()))
						.to(memoryStats::setFragmentationRatio);
			}else if("lua.caches".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setLuaCaches);
			}else if("peak.allocated".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setPeakAllocated);
			}else if("total.allocated".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setTotalAllocated);
			}else if("replication.backlog".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setReplicationBacklog);
			}else if("startup.allocated".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setStartupAllocated);
			}else if("overhead.total".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setOverheadTotal);
			}else if("keys.count".equals(name)){
				propertyMapper.from(value).as((v)->(Long) v).to(memoryStats::setKeysCount);
			}else if(name.startsWith("db.")){
				final String[] nameParts = StringUtils.split(name, '.');
				final int db = Integer.parseInt(nameParts[1]);
				final MemoryStats.Db dbStat = new MemoryStats.Db();
				final List<Object> dbStatTmp = (List<Object>) value;

				if(memoryStats.getDbs() == null){
					memoryStats.setDbs(new HashMap<>(source.size()));
				}

				for(int j = 0, jl = dbStatTmp.size(); j < jl; j++){
					Object v = dbStatTmp.get(j);
					j++;

					if("overhead.hashtable.main".equals(v)){
						propertyMapper.from(dbStatTmp.get(j)).as((dv)->(Long) dv)
								.to(dbStat::setOverheadHashTableMain);
					}else if("overhead.hashtable.expires".equals(v)){
						propertyMapper.from(dbStatTmp.get(j)).as((dv)->(Long) dv)
								.to(dbStat::setOverheadHashTableExpires);
					}
				}

				memoryStats.getDbs().put(db, dbStat);
			}
		});

		return memoryStats;
	}

}

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
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.TimeSeriesInfo;
import com.buession.redis.core.command.args.timeseries.DuplicatePolicy;
import redis.clients.jedis.timeseries.TSInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Jedis {@link TSInfo} 转换为 {@link TimeSeriesInfo}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class TSInfoConverter implements Converter<TSInfo, TimeSeriesInfo> {

	@Override
	public TimeSeriesInfo convert(final TSInfo source) {
		if(source == null){
			return null;
		}

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final TimeSeriesInfoBuilder timeSeriesInfoBuilder = new TimeSeriesInfoBuilder();

		propertyMapper.from(source.getProperties().get("sourceKey")).as(Object::toString)
				.to(timeSeriesInfoBuilder::setSourceKey);
		propertyMapper.from(source.getProperties().get("keySelfName")).as(Object::toString)
				.to(timeSeriesInfoBuilder::setKeySelfName);
		propertyMapper.from(source.getProperties().get("totalSamples")).as((v)->(Long) v).as(Long::intValue)
				.to(timeSeriesInfoBuilder::setTotalSamples);
		propertyMapper.from(source.getProperties().get("memoryUsage")).as((v)->(Long) v)
				.to(timeSeriesInfoBuilder::setMemoryUsage);
		propertyMapper.from(source.getProperties().get("firstTimestamp")).as((v)->(Long) v)
				.to(timeSeriesInfoBuilder::setFirstTimestamp);
		propertyMapper.from(source.getProperties().get("lastTimestamp")).as((v)->(Long) v)
				.to(timeSeriesInfoBuilder::setLastTimestamp);
		propertyMapper.from(source.getProperties().get("retentionTime")).as((v)->(Long) v).as(Long::intValue)
				.to(timeSeriesInfoBuilder::setRetentionTime);
		propertyMapper.from(source.getProperties().get("chunkCount")).as((v)->(Long) v).as(Long::intValue)
				.to(timeSeriesInfoBuilder::setChunkCount);
		propertyMapper.from(source.getProperties().get("chunkSize")).as((v)->(Long) v).as(Long::intValue)
				.to(timeSeriesInfoBuilder::setChunkSize);

		Object chunkType = source.getProperties().get("chunkType");
		if(chunkType != null){
			String chunkTypeString = chunkType.toString();

			if(StringUtils.equalsIgnoreCase(chunkTypeString, "COMPRESSED")){
				timeSeriesInfoBuilder.setChunkType(TimeSeriesInfo.ChunkType.COMPRESSED);
			}else if(StringUtils.equalsIgnoreCase(chunkTypeString, "UNCOMPRESSED")){
				timeSeriesInfoBuilder.setChunkType(TimeSeriesInfo.ChunkType.UNCOMPRESSED);
			}
		}

		Object duplicatePolicy = source.getProperties().get("duplicatePolicy");
		if(duplicatePolicy != null){
			String duplicatePolicyString = duplicatePolicy.toString();

			timeSeriesInfoBuilder.setDuplicatePolicy(Enum.valueOf(DuplicatePolicy.class, duplicatePolicyString));
		}

		timeSeriesInfoBuilder.setLabels(source.getLabels());

		if(source.getRules() != null){
			for(Map.Entry<String, TSInfo.Rule> e : source.getRules().entrySet()){
				timeSeriesInfoBuilder.addRule(e.getKey(), e.getValue());
			}
		}

		if(source.getChunks() != null){
			timeSeriesInfoBuilder.setChunks(source.getChunks().stream()
					.map((item)->new TimeSeriesInfo.Chunk((Long) item.get("startTimestamp"),
							(Long) item.get("endTimestamp"), ((Long) item.get("samples")).intValue(),
							((Long) item.get("size")).intValue(), (Double) item.get("bytesPerSample")))
					.collect(Collectors.toList()));
		}

		return timeSeriesInfoBuilder.build();
	}

	private final static class TimeSeriesInfoBuilder {

		private String sourceKey;

		private String keySelfName;

		private Integer totalSamples;

		private Long memoryUsage;

		private Long firstTimestamp;

		private Long lastTimestamp;

		private Integer retentionTime;

		private Integer chunkCount;

		private Integer chunkSize;

		private TimeSeriesInfo.ChunkType chunkType;

		private DuplicatePolicy duplicatePolicy;

		private Map<String, String> labels;

		private Map<String, TimeSeriesInfo.Rule> rules;

		private List<TimeSeriesInfo.Chunk> chunks;

		private TimeSeriesInfoBuilder() {

		}

		public void setSourceKey(String sourceKey) {
			this.sourceKey = sourceKey;
		}

		public void setKeySelfName(String keySelfName) {
			this.keySelfName = keySelfName;
		}

		public void setTotalSamples(Integer totalSamples) {
			this.totalSamples = totalSamples;
		}

		public void setMemoryUsage(Long memoryUsage) {
			this.memoryUsage = memoryUsage;
		}

		public void setFirstTimestamp(Long firstTimestamp) {
			this.firstTimestamp = firstTimestamp;
		}

		public void setLastTimestamp(Long lastTimestamp) {
			this.lastTimestamp = lastTimestamp;
		}

		public void setRetentionTime(Integer retentionTime) {
			this.retentionTime = retentionTime;
		}

		public void setChunkCount(Integer chunkCount) {
			this.chunkCount = chunkCount;
		}

		public void setChunkSize(Integer chunkSize) {
			this.chunkSize = chunkSize;
		}

		public void setChunkType(TimeSeriesInfo.ChunkType chunkType) {
			this.chunkType = chunkType;
		}

		public void setDuplicatePolicy(DuplicatePolicy duplicatePolicy) {
			this.duplicatePolicy = duplicatePolicy;
		}

		public void setLabels(Map<String, String> labels) {
			this.labels = labels;
		}

		public void addRule(String key, TSInfo.Rule rule) {
			if(this.rules == null){
				this.rules = new LinkedHashMap<>();
			}

			final AggregationTypeConverter aggregationTypeConverter = new AggregationTypeConverter();
			this.rules.put(key, new TimeSeriesInfo.Rule(rule.getCompactionKey(), (int) rule.getBucketDuration(),
					aggregationTypeConverter.convert(rule.getAggregator()), rule.getAlignmentTimestamp()));
		}

		public void setRules(Map<String, TimeSeriesInfo.Rule> rules) {
			this.rules = rules;
		}

		public void setChunks(List<TimeSeriesInfo.Chunk> chunks) {
			this.chunks = chunks;
		}

		public TimeSeriesInfo build() {
			return new TimeSeriesInfo(sourceKey, keySelfName, totalSamples, memoryUsage, firstTimestamp,
					lastTimestamp, retentionTime, chunkCount, chunkSize, chunkType, duplicatePolicy, labels, rules,
					chunks);
		}

	}

}

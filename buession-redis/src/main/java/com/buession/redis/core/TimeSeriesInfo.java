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

import com.buession.redis.core.command.args.timeseries.DuplicatePolicy;
import com.buession.redis.utils.ObjectStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * <code>TS.INFO</code> 命令结果
 *
 * @param sourceKey
 * 		Key name for source time series in case the current series is a target of a compaction rule
 * @param keySelfName
 * 		Name of the key
 * @param totalSamples
 * 		Total number of samples in this time series
 * @param memoryUsage
 * 		Total number of bytes allocated for this time series, which is the sum of:
 * 		- The memory used for storing the series' configuration parameters (retention period, duplication policy, etc.)
 * 		- The memory used for storing the series' compaction rules
 * 		- The memory used for storing the series' labels (key-value pairs)
 * 		- The memory used for storing the chunks (chunk header + compressed/uncompressed data)
 * @param firstTimestamp
 * 		First timestamp present in this time series (Unix timestamp in milliseconds)
 * @param lastTimestamp
 * 		Last timestamp present in this time series (Unix timestamp in milliseconds)
 * @param retentionTime
 * 		The retention period, in milliseconds, for this time series
 * @param chunkCount
 * 		Number of chunks used for this time series
 * @param chunkSize
 * 		The initial allocation size, in bytes, for the data part of each new chunk.
 * @param chunkType
 * 		The chunks type: compressed or uncompressed
 * @param duplicatePolicy
 * 		The duplicate policy of this time series
 * @param labels
 * 		Metadata labels of this time series
 * @param rules
 * 		Compaction rules defined in this time series
 * @param chunks
 * 		The chunks
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record TimeSeriesInfo(String sourceKey, String keySelfName, Integer totalSamples, Long memoryUsage,
                             Long firstTimestamp, Long lastTimestamp, Integer retentionTime, Integer chunkCount,
                             Integer chunkSize, ChunkType chunkType, DuplicatePolicy duplicatePolicy,
                             Map<String, String> labels, Map<String, Rule> rules, List<Chunk> chunks) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("source key", sourceKey)
				.add("key self name", keySelfName)
				.add("total samples", totalSamples)
				.add("memory usage", memoryUsage)
				.add("first timestamp", firstTimestamp)
				.add("last timestamp", lastTimestamp)
				.add("retention time", retentionTime)
				.add("chunk count", chunkCount)
				.add("chunk size", chunkSize)
				.add("chunk type", chunkType)
				.add("duplicate policy", duplicatePolicy)
				.add("labels", labels)
				.add("rules", rules)
				.add("chunks", chunks)
				.build();
	}

	public enum ChunkType implements Keyword {

		COMPRESSED,

		UNCOMPRESSED;

		@Override
		public String getValue() {
			return name().toUpperCase();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

	public record Rule(String compactionKey, Integer bucketDuration, AggregationType aggregator, Long alignment) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create()
					.add("compaction key", compactionKey)
					.add("bucket duration", bucketDuration)
					.add("aggregator", aggregator)
					.add("alignment", alignment)
					.build();
		}

	}

	/**
	 *
	 * @param startTimestamp
	 * 		First timestamp present in the chunk
	 * @param endTimestamp
	 * 		Last timestamp present in the chunk
	 * @param samples
	 * 		Total number of samples in the chunk
	 * @param size
	 * 		The chunk's internal data size (without overheads) in bytes
	 * @param bytesPerSample
	 * 		Ratio of size and samples
	 */
	public record Chunk(Long startTimestamp, Long endTimestamp, Integer samples, Integer size, Double bytesPerSample) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create()
					.add("start timestamp", startTimestamp)
					.add("end timestamp", endTimestamp)
					.add("samples", samples)
					.add("size", size)
					.add("bytes per sample", bytesPerSample)
					.build();
		}

	}

}

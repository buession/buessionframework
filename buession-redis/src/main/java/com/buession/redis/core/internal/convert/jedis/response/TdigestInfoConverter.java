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
import com.buession.redis.core.TdigestInfo;

import java.util.Map;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class TdigestInfoConverter implements Converter<Map<String, Object>, TdigestInfo> {

	@Override
	public TdigestInfo convert(final Map<String, Object> source) {
		if(source == null){
			return null;
		}

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final TdigestInfoBuilder tdigestInfoBuilder = new TdigestInfoBuilder();

		propertyMapper.from(source.get("Compression")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setCompression);
		propertyMapper.from(source.get("Capacity")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setCapacity);
		propertyMapper.from(source.get("Merged nodes")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setMergedNodes);
		propertyMapper.from(source.get("Unmerged nodes")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setUnmergedNodes);
		propertyMapper.from(source.get("Merged weight")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setMergedWeight);
		propertyMapper.from(source.get("Unmerged weight")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setUnmergedWeight);
		propertyMapper.from(source.get("Observations")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setObservations);
		propertyMapper.from(source.get("Total compressions")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setTotalCompressions);
		propertyMapper.from(source.get("Memory usage")).as((v)->(Long) v).as(Long::intValue)
				.to(tdigestInfoBuilder::setMemoryUsage);

		return tdigestInfoBuilder.build();
	}

	private final static class TdigestInfoBuilder {

		private Integer compression;

		private Integer capacity;

		private Integer mergedNodes;

		private Integer unmergedNodes;

		private Integer mergedWeight;

		private Integer unmergedWeight;

		private Integer observations;

		private Integer totalCompressions;

		private Integer memoryUsage;

		private TdigestInfoBuilder() {

		}

		public void setCompression(Integer compression) {
			this.compression = compression;
		}

		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}

		public void setMergedNodes(Integer mergedNodes) {
			this.mergedNodes = mergedNodes;
		}

		public void setUnmergedNodes(Integer unmergedNodes) {
			this.unmergedNodes = unmergedNodes;
		}

		public void setMergedWeight(Integer mergedWeight) {
			this.mergedWeight = mergedWeight;
		}

		public void setUnmergedWeight(Integer unmergedWeight) {
			this.unmergedWeight = unmergedWeight;
		}

		public void setObservations(Integer observations) {
			this.observations = observations;
		}

		public void setTotalCompressions(Integer totalCompressions) {
			this.totalCompressions = totalCompressions;
		}

		public void setMemoryUsage(Integer memoryUsage) {
			this.memoryUsage = memoryUsage;
		}

		public TdigestInfo build() {
			return new TdigestInfo(compression, capacity, mergedNodes, unmergedNodes, mergedWeight, unmergedWeight,
					observations, totalCompressions, memoryUsage);
		}

	}

}

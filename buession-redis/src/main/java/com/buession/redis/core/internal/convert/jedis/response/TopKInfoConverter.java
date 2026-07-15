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
import com.buession.redis.core.TopKInfo;

import java.util.Map;
import java.util.Optional;

/**
 * <code>TOPK.INFO</code> 命令结果转换
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class TopKInfoConverter implements Converter<Map<String, Object>, TopKInfo> {

	@Override
	public TopKInfo convert(final Map<String, Object> source) {
		if(source == null){
			return null;
		}

		final TopKInfoBuilder topKInfoBuilder = new TopKInfoBuilder();

		Optional.ofNullable(source.get("k")).ifPresent((v)->topKInfoBuilder.setK(Integer.parseInt(v.toString())));
		Optional.ofNullable(source.get("size")).ifPresent((v)->topKInfoBuilder.setSize(Integer.parseInt(v.toString())));
		Optional.ofNullable(source.get("width"))
				.ifPresent((v)->topKInfoBuilder.setWidth(Integer.parseInt(v.toString())));
		Optional.ofNullable(source.get("depth"))
				.ifPresent((v)->topKInfoBuilder.setDepth(Integer.parseInt(v.toString())));
		Optional.ofNullable(source.get("decay"))
				.ifPresent((v)->topKInfoBuilder.setDecay(Double.parseDouble(v.toString())));

		return topKInfoBuilder.build();
	}

	private final static class TopKInfoBuilder {

		private Integer k;

		private Integer size;

		private Integer width;

		private Integer depth;

		private Double decay;

		private TopKInfoBuilder() {

		}

		public void setK(Integer k) {
			this.k = k;
		}

		public void setSize(Integer size) {
			this.size = size;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

		public void setDepth(Integer depth) {
			this.depth = depth;
		}

		public void setDecay(Double decay) {
			this.decay = decay;
		}

		public TopKInfo build() {
			return new TopKInfo(k, size, width, depth, decay);
		}

	}

}

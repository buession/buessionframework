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
package com.buession.redis.core.internal.convert.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.redis.core.CmsInfo;

import java.util.Map;

/**
 * CMS.INFO 命令结果转换器
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class CmsInfoConverter implements Converter<Map<String, Object>, CmsInfo> {

	@Override
	public CmsInfo convert(final Map<String, Object> source) {
		if(source == null){
			return null;
		}

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final CmsInfoBuilder cmsInfoBuilder = new CmsInfoBuilder();

		propertyMapper.from(source.get("width")).as((v)->(Integer) v).to(cmsInfoBuilder::setWidth);
		propertyMapper.from(source.get("depth")).as((v)->(Integer) v).to(cmsInfoBuilder::setDepth);
		propertyMapper.from(source.get("count")).as((v)->(Integer) v).to(cmsInfoBuilder::setCount);

		return cmsInfoBuilder.build();
	}

	private final static class CmsInfoBuilder {

		private Integer width;

		private Integer depth;

		private Integer count;

		private CmsInfoBuilder() {

		}

		public Integer getWidth() {
			return width;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

		public Integer getDepth() {
			return depth;
		}

		public void setDepth(Integer depth) {
			this.depth = depth;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public CmsInfo build() {
			return new CmsInfo(width, depth, count);
		}

	}

}

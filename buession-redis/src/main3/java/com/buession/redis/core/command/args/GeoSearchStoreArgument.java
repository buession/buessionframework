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
package com.buession.redis.core.command.args;

import com.buession.redis.core.Keyword;

/**
 * {@code GEOSEARCHSTORE} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class GeoSearchStoreArgument extends BaseGeoSearchArgument {

	private Boolean storeDist;

	/**
	 * 构造函数
	 */
	public GeoSearchStoreArgument() {
	}

	public Boolean isStoreDist() {
		return getStoreDist();
	}

	public Boolean getStoreDist() {
		return storeDist;
	}

	public void storeDist() {
		this.storeDist = true;
	}

	public void setStoreDist(Boolean storeDist) {
		this.storeDist = storeDist;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(getFromMode() instanceof BaseGeoSearchArgument.FromMember){
			builder.add(Keyword.Geo.FROMMEMBER.name(), getFromMode().toString());
		}else if(getFromMode() instanceof BaseGeoSearchArgument.FromLonLat){
			builder.add(Keyword.Geo.FROMLONLAT.name(), getFromMode().toString());
		}

		if(getPredicate() instanceof BaseGeoSearchArgument.RadiusPredicate){
			builder.add(Keyword.Geo.FROMLONLAT.BYRADIUS.name(), getPredicate().toString());
		}else if(getPredicate() instanceof BaseGeoSearchArgument.BoxPredicate){
			builder.add(Keyword.Geo.FROMLONLAT.BYBOX.name(), getPredicate().toString());
		}

		if(getOrder() != null){
			builder.append(getOrder());
		}

		if(getCount() != null){
			builder.add(Keyword.Common.COUNT, getCount());
			if(Boolean.TRUE.equals(isAny())){
				builder.append(Keyword.Common.ANY);
			}
		}

		return builder.toString();
	}

}

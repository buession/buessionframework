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

import com.buession.lang.Geo;
import com.buession.lang.Order;
import com.buession.redis.core.GeoUnit;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
class BaseGeoSearchArgument {

	/**
	 * {@link FromMode}
	 */
	private FromMode fromMode;

	/**
	 * BYRADIUS / BYBOX
	 */
	private Predicate predicate;

	/**
	 * 排序
	 */
	private Order order;

	/**
	 * 返回个数
	 */
	private Integer count;

	/**
	 * -
	 */
	private Boolean any;

	/**
	 * 构造函数
	 */
	public BaseGeoSearchArgument() {
	}

	/**
	 * 返回 {@link FromMode} 实例
	 *
	 * @return {@link FromMode} 实例
	 */
	public FromMode getFromMode() {
		return fromMode;
	}

	/**
	 * 设置 {@link FromMode} 实例
	 *
	 * @param fromMode
	 *        {@link FromMode} 实例
	 */
	public void setFromMode(FromMode fromMode) {
		this.fromMode = fromMode;
	}

	/**
	 * 返回 BYRADIUS / BYBOX
	 *
	 * @return BYRADIUS / BYBOX
	 */
	public Predicate getPredicate() {
		return predicate;
	}


	/**
	 * 设置 BYRADIUS / BYBOX
	 *
	 * @param predicate
	 * 		BYRADIUS / BYBOX
	 */
	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}

	/**
	 * 返回排序
	 *
	 * @return 排序
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置排序
	 *
	 * @param order
	 * 		排序
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 获取返回数量
	 *
	 * @return 返回数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置返回数量
	 *
	 * @param count
	 * 		返回数量
	 */
	public void count(final Integer count) {
		this.count = count;
	}

	/**
	 * 设置返回数量
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public void count(final Integer count, final Boolean any) {
		this.count = count;
		this.any = any;
	}

	/**
	 * -
	 *
	 * @return true / false
	 */
	public Boolean isAny() {
		return getAny();
	}

	/**
	 * -
	 *
	 * @return true / false
	 */
	public Boolean getAny() {
		return any;
	}

	public interface FromMode {

	}

	public final static class FromMember implements FromMode {

		private final String member;

		public FromMember(final String member) {
			this.member = member;
		}

		public String getMember() {
			return member;
		}

		@Override
		public String toString() {
			return getMember();
		}

	}

	public final static class FromLonLat implements FromMode {

		private final Geo geo;

		public FromLonLat(final Geo geo) {
			this.geo = geo;
		}

		public FromLonLat(final double longitude, final double latitude) {
			this.geo = new Geo(longitude, latitude);
		}

		public Geo getGeo() {
			return geo;
		}

		@Override
		public String toString() {
			final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

			builder.append(geo.getLongitude()).append(geo.getLatitude());

			return builder.build();
		}

	}

	public interface Predicate {

	}

	/**
	 * BYRADIUS
	 */
	public final static class RadiusPredicate implements Predicate {

		/**
		 * 距离范围
		 */
		private final Double radius;

		/**
		 * 距离单位
		 */
		private final GeoUnit unit;

		/**
		 * 构造函数
		 *
		 * @param radius
		 * 		距离范围
		 * @param unit
		 * 		距离单位
		 */
		public RadiusPredicate(final Double radius, final GeoUnit unit) {
			this.radius = radius;
			this.unit = unit;
		}

		/**
		 * 返回距离范围
		 *
		 * @return 距离范围
		 */
		public Double getRadius() {
			return radius;
		}

		/**
		 * 返回距离单位
		 *
		 * @return 距离单位
		 */
		public GeoUnit getUnit() {
			return unit;
		}

		@Override
		public String toString() {
			final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

			if(radius != null){
				builder.add(Double.toString(radius), unit);
			}

			return builder.build();
		}
	}

	/**
	 * BYBOX
	 */
	public final static class BoxPredicate implements Predicate {

		/**
		 * 宽度
		 */
		private final Double width;

		/**
		 * 高度
		 */
		private final Double height;

		/**
		 * 距离单位
		 */
		private final GeoUnit unit;

		/**
		 * 构造函数
		 *
		 * @param width
		 * 		宽度
		 * @param height
		 * 		高度
		 * @param unit
		 * 		单位
		 */
		public BoxPredicate(final Double width, final Double height, final GeoUnit unit) {
			this.width = width;
			this.height = height;
			this.unit = unit;
		}

		/**
		 * 返回宽度
		 *
		 * @return 宽度
		 */
		public Double getWidth() {
			return width;
		}

		/**
		 * 返回高度
		 *
		 * @return 高度
		 */
		public Double getHeight() {
			return height;
		}

		/**
		 * 返回距离单位
		 *
		 * @return 距离单位
		 */
		public GeoUnit getUnit() {
			return unit;
		}

		@Override
		public String toString() {
			final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

			if(width != null && height != null){
				builder.append(width).append(height).append(unit);
			}

			return builder.build();
		}
	}

}

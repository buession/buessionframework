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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.lang.Order;

/**
 * 排序参数
 *
 * @author Yong.Teng
 */
public class SortArgument {

	private String by;

	private Order order;

	private Limit limit;

	private String alpha;

	private SortArgument(){

	}

	/**
	 * 获取可以让 uid 按其他键的元素来排序模式
	 *
	 * @return 可以让 uid 按其他键的元素来排序模式
	 */
	public String getBy(){
		return by;
	}

	/**
	 * 获取排序方式
	 *
	 * @return 排序方式
	 */
	public Order getOrder(){
		return order;
	}

	/**
	 * 获取返回结果限制
	 *
	 * @return 返回结果限制
	 */
	public Limit getLimit(){
		return limit;
	}

	/**
	 * 使用对字符串进行排序
	 *
	 * @return 使用对字符串进行排序
	 */
	public String getAlpha(){
		return alpha;
	}

	public static class Builder {

		private SortArgument sortArgument = new SortArgument();

		private Builder(){
		}

		public final static Builder create(){
			return new Builder();
		}

		/**
		 * 设置可以让 uid 按其他键的元素来排序
		 *
		 * @param pattern
		 * 		模式
		 *
		 * @return Builder
		 */
		public Builder by(String pattern){
			sortArgument.by = pattern;
			return this;
		}

		/**
		 * 设置升序排序
		 *
		 * @return Builder
		 */
		public Builder asc(){
			sortArgument.order = Order.ASC;
			return this;
		}

		/**
		 * 设置降序排序
		 *
		 * @return Builder
		 */
		public Builder desc(){
			sortArgument.order = Order.DESC;
			return this;
		}

		/**
		 * 设置排序方式
		 *
		 * @param order
		 * 		排序方式
		 *
		 * @return Builder
		 */
		public Builder order(Order order){
			sortArgument.order = order;
			return this;
		}

		/**
		 * 设置返回偏移量为 offset 的 count 条数据
		 *
		 * @param offset
		 * 		偏移量
		 * @param count
		 * 		返回数量
		 *
		 * @return Builder
		 */
		public Builder limit(long offset, long count){
			sortArgument.limit = new Limit(offset, count);
			return this;
		}

		/**
		 * SORT 命令默认排序对象为数字，当需要对字符串进行排序时，需要显式地在 SORT 命令之后添加 ALPHA 修饰符
		 *
		 * @return 使用对字符串进行排序
		 */
		public Builder alpha(){
			sortArgument.alpha = "ALPHA";
			return this;
		}

		public SortArgument build(){
			return sortArgument;
		}

	}

}

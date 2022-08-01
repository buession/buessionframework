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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.bind;

import com.buession.core.builder.MapBuilder;

import java.util.Map;

/**
 * 排序
 *
 * @author Yong.Teng
 * @since 2.0.3
 */
public class Order {

	/**
	 * 排序字段
	 */
	private String field;

	/**
	 * 排序
	 */
	private com.buession.lang.Order order;

	/**
	 * 构造函数
	 */
	public Order(){
	}

	/**
	 * 构造函数
	 *
	 * @param field
	 * 		排序字段
	 * @param order
	 * 		排序
	 */
	public Order(final String field, final com.buession.lang.Order order){
		this.field = field;
		this.order = order;
	}

	/**
	 * 返回排序字段
	 *
	 * @return 排序字段
	 */
	public String getField(){
		return field;
	}

	/**
	 * 设置排序字段
	 *
	 * @param field
	 * 		排序字段
	 */
	public void setField(String field){
		this.field = field;
	}

	/**
	 * 返回排序
	 *
	 * @return 排序
	 */
	public com.buession.lang.Order getOrder(){
		return order;
	}

	/**
	 * 设置排序
	 *
	 * @param order
	 * 		排序
	 */
	public void setOrder(com.buession.lang.Order order){
		this.order = order;
	}

	/**
	 * 转换为 Map
	 *
	 * @return Map
	 */
	public Map<String, com.buession.lang.Order> asMap(){
		return MapBuilder.of(field, order);
	}

}

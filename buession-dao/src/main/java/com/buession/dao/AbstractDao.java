/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2025 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import com.buession.core.Pagination;
import com.buession.core.utils.Assert;
import com.buession.lang.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Data Access Object 抽象类
 *
 * @param <P>
 * 		注解类型
 * @param <E>
 * 		实体类
 *
 * @author Yong.Teng
 */
public abstract class AbstractDao<P, E> implements Dao<P, E> {

	@Override
	public List<Integer> batchInsert(List<E> data) {
		return data == null ? null : data.stream().map(this::insert).collect(Collectors.toList());
	}

	@Override
	public List<Integer> batchReplace(List<E> data) {
		return data == null ? null : data.stream().map(this::replace).collect(Collectors.toList());
	}

	@Override
	public E selectOne() {
		return selectOne(null, null);
	}

	@Override
	public E selectOne(Map<String, Object> conditions) {
		return selectOne(conditions, null);
	}

	@Override
	public E selectOne(Map<String, Object> conditions, int offset) {
		return selectOne(conditions, offset, null);
	}

	@Override
	public E selectOne(Map<String, Object> conditions, Map<String, Order> orders) {
		return selectOne(conditions, 0, orders);
	}

	@Override
	public List<E> select(Map<String, Object> conditions) {
		return select(conditions, null);
	}

	@Override
	public List<E> select(Map<String, Object> conditions, int size) {
		return select(conditions, size, null);
	}

	@Override
	public List<E> select(Map<String, Object> conditions, int offset, int size) {
		return select(conditions, offset, size, null);
	}

	@Override
	public List<E> select(Map<String, Object> conditions, int size, Map<String, Order> orders) {
		Assert.isZeroNegative(size, "Size argument value must be positive integer");
		return select(conditions, 0, size, orders);
	}

	@Override
	public Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize) {
		return paging(conditions, page, pagesize, null);
	}

}

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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import com.buession.core.Pagination;
import com.buession.core.utils.Assert;
import com.buession.lang.Order;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public abstract class AbstractDao<P, E> implements Dao<P, E> {

	/**
	 * 批量插入数据
	 *
	 * @param data
	 * 		实体类
	 *
	 * @return 主键值列表
	 */
	@Override
	public List<Integer> batchInsert(List<E> data){
		return data == null ? null : data.stream().map(this::insert).collect(Collectors.toList());
	}

	/**
	 * 批量替换数据
	 *
	 * @param data
	 * 		实体类
	 *
	 * @return 主键值列表
	 */
	@Override
	public List<Integer> batchReplace(List<E> data){
		return data == null ? null : data.stream().map(this::replace).collect(Collectors.toList());
	}

	/**
	 * 获取一条记录
	 *
	 * @return 查询结果
	 */
	@Override
	public E getOne(){
		return selectOne();
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 查询结果
	 */
	@Override
	public E getOne(Map<String, Object> conditions){
		return selectOne(conditions);
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 * @param offset
	 * 		偏移量
	 *
	 * @return 查询结果
	 */
	@Override
	public E getOne(Map<String, Object> conditions, int offset){
		return selectOne(conditions, offset);
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 * @param orders
	 * 		排序
	 *
	 * @return 查询结果
	 */
	@Override
	public E getOne(Map<String, Object> conditions, Map<String, Order> orders){
		return selectOne(conditions, orders);
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 * @param offset
	 * 		偏移量
	 * @param orders
	 * 		排序
	 *
	 * @return 查询结果
	 */
	@Override
	public E getOne(Map<String, Object> conditions, int offset, Map<String, Order> orders){
		return selectOne(conditions, offset, orders);
	}

	@Override
	public E selectOne(){
		return selectOne(null, null);
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 查询结果
	 */
	@Override
	public E selectOne(Map<String, Object> conditions){
		return selectOne(conditions, null);
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 * @param offset
	 * 		偏移量
	 *
	 * @return 查询结果
	 */
	@Override
	public E selectOne(Map<String, Object> conditions, int offset){
		return selectOne(conditions, offset, null);
	}

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 * @param orders
	 * 		排序
	 *
	 * @return 查询结果
	 */
	@Override
	public E selectOne(Map<String, Object> conditions, Map<String, Order> orders){
		return selectOne(conditions, 0, orders);
	}

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 结果集
	 */
	@Override
	public List<E> select(Map<String, Object> conditions){
		return select(conditions, null);
	}

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param size
	 * 		查询条数
	 *
	 * @return 结果集
	 */
	@Override
	public List<E> select(Map<String, Object> conditions, int size){
		return select(conditions, size, null);
	}

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param offset
	 * 		偏移量
	 * @param size
	 * 		查询条数
	 *
	 * @return 结果集
	 */
	@Override
	public List<E> select(Map<String, Object> conditions, int offset, int size){
		return select(conditions, offset, size, null);
	}

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param size
	 * 		查询条数
	 * @param orders
	 * 		排序
	 *
	 * @return 结果集
	 */
	@Override
	public List<E> select(Map<String, Object> conditions, int size, Map<String, Order> orders){
		Assert.isZeroNegative(size, "Size argument value must be positive integer");
		return select(conditions, 0, size, orders);
	}

	/**
	 * 数据分页查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param page
	 * 		页码
	 * @param pagesize
	 * 		每页大小
	 *
	 * @return Pagination
	 */
	@Override
	public Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize){
		return paging(conditions, page, pagesize, null);
	}

}

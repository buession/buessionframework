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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import java.util.List;
import java.util.Map;

import com.buession.core.Pagination;
import com.buession.lang.Order;

/**
 * Data Access Object 接口
 *
 * @param <P>
 * 		注解类型
 * @param <E>
 * 		实体类
 *
 * @author Yong.Teng
 */
public interface Dao<P, E> {

	/**
	 * 插入数据
	 *
	 * @param e
	 * 		实体类
	 *
	 * @return 影响的行数
	 */
	int insert(E e);

	/**
	 * 批量插入数据
	 *
	 * @param data
	 * 		实体类
	 *
	 * @return 每次插入影响的行数列表
	 */
	List<Integer> batchInsert(List<E> data);

	/**
	 * 替换数据
	 *
	 * @param e
	 * 		实体类
	 *
	 * @return 影响的行数
	 */
	int replace(E e);

	/**
	 * 批量替换数据
	 *
	 * @param data
	 * 		实体类
	 *
	 * @return 每次替换数据影响的行数列表
	 */
	List<Integer> batchReplace(List<E> data);

	/**
	 * 更新数据
	 *
	 * @param e
	 * 		新数据
	 * @param conditions
	 * 		更新条件
	 *
	 * @return 更新条数
	 */
	int update(E e, Map<String, Object> conditions);

	/**
	 * 根据主键更新数据
	 *
	 * @param primary
	 * 		主键值
	 * @param e
	 * 		新数据
	 *
	 * @return 更新条数
	 */
	int updateByPrimary(P primary, E e);

	/**
	 * 根据主键查询数据
	 *
	 * @param primary
	 * 		主键值
	 *
	 * @return 数据结果
	 */
	E getByPrimary(P primary);

	/**
	 * 获取一条记录
	 *
	 * @return 查询结果
	 */
	E getOne();

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 查询结果
	 */
	E getOne(Map<String, Object> conditions);

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
	E getOne(Map<String, Object> conditions, int offset);

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
	E getOne(Map<String, Object> conditions, Map<String, Order> orders);

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
	E getOne(Map<String, Object> conditions, int offset, Map<String, Order> orders);

	/**
	 * 获取一条记录
	 *
	 * @return 查询结果
	 */
	E selectOne();

	/**
	 * 获取一条记录
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 查询结果
	 */
	E selectOne(Map<String, Object> conditions);

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
	E selectOne(Map<String, Object> conditions, int offset);

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
	E selectOne(Map<String, Object> conditions, Map<String, Order> orders);

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
	E selectOne(Map<String, Object> conditions, int offset, Map<String, Order> orders);

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 结果集
	 */
	List<E> select(Map<String, Object> conditions);

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
	List<E> select(Map<String, Object> conditions, int size);

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
	List<E> select(Map<String, Object> conditions, int offset, int size);

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param orders
	 * 		排序
	 *
	 * @return 结果集
	 */
	List<E> select(Map<String, Object> conditions, Map<String, Order> orders);

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
	List<E> select(Map<String, Object> conditions, int size, Map<String, Order> orders);

	/**
	 * 数据查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param offset
	 * 		偏移量
	 * @param size
	 * 		查询条数
	 * @param orders
	 * 		排序
	 *
	 * @return 结果集
	 */
	List<E> select(Map<String, Object> conditions, int offset, int size, Map<String, Order> orders);

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
	Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize);

	/**
	 * 数据分页查询
	 *
	 * @param conditions
	 * 		查询条件
	 * @param page
	 * 		页码
	 * @param pagesize
	 * 		每页大小
	 * @param orders
	 * 		排序
	 *
	 * @return Pagination
	 */
	Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize, Map<String, Order> orders);

	/**
	 * 查询所有数据
	 *
	 * @return 结果集
	 */
	List<E> getAll();

	/**
	 * 数据记录总数
	 *
	 * @return 记录总数
	 */
	long count();

	/**
	 * 数据记录总数
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 记录总数
	 */
	long count(Map<String, Object> conditions);

	/**
	 * 删除数据
	 *
	 * @param conditions
	 * 		删除条件
	 *
	 * @return 影响条数
	 */
	int delete(Map<String, Object> conditions);

	/**
	 * 删除数据
	 *
	 * @param conditions
	 * 		删除条件
	 * @param size
	 * 		删除条数
	 *
	 * @return 影响条数
	 *
	 * @since 2.3.0
	 */
	int delete(Map<String, Object> conditions, int size);

	/**
	 * 根据主键删除数据
	 *
	 * @param primary
	 * 		主键值
	 *
	 * @return 影响条数
	 */
	int deleteByPrimary(P primary);

	/**
	 * 清空数据
	 *
	 * @return 影响条数
	 */
	int clear();

	/**
	 * 清空数据
	 *
	 * @return 影响条数
	 */
	int truncate();

}
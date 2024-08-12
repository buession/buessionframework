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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.buession.core.utils.FieldUtils;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.dao.mybatis.PageRowBounds;
import com.buession.lang.Order;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buession.core.Pagination;
import org.springframework.cglib.beans.BeanMap;

import javax.annotation.Resource;

/**
 * MyBatis Data Access Object 抽象类
 *
 * @param <P>
 * 		注解类型
 * @param <E>
 * 		实体类
 *
 * @author Yong.Teng
 */
public abstract class AbstractMyBatisDao<P, E> extends AbstractDao<P, E> implements MyBatisDao<P, E> {

	/**
	 * master SqlSessionTemplate
	 */
	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 返回  SqlSessionTemplate
	 *
	 * @return SqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	/**
	 * 设置 SqlSessionTemplate
	 *
	 * @param sqlSessionTemplate
	 * 		SqlSessionTemplate
	 */
	public void setSqlSessionTemplate(final SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int insert(E e) {
		return getSqlSessionTemplate().insert(getStatement(DML.INSERT), e);
	}

	@Override
	public int replace(E e) {
		return getSqlSessionTemplate().insert(getStatement(DML.REPLACE), e);
	}

	@Override
	@SuppressWarnings("unchecked")
	public int update(E e, Map<String, Object> conditions) {
		Assert.isNull(e, "The data could not be empty for update.");

		Map<String, Object> data = new HashMap<>(conditions == null ? 16 : conditions.size());
		if(conditions != null){
			data.putAll(conditions);
		}

		if(e instanceof Map){
			Map<Object, Object> eMap = (Map<Object, Object>) e;
			eMap.forEach((key, value)->data.put(key.toString(), value));
		}else{
			BeanMap beanMap = BeanMap.create(e);
			data.putAll(beanMap);
		}

		return getSqlSessionTemplate().update(getStatement(DML.UPDATE), data);
	}

	@Override
	public int updateByPrimary(P primary, E e) {
		updatePrimary(e, primary);
		return getSqlSessionTemplate().update(getStatement(DML.UPDATE_BY_PRIMARY), e);
	}

	@Override
	public E getByPrimary(P primary) {
		return getSqlSessionTemplate().selectOne(getStatement(DML.GET_BY_PRIMARY), primary);
	}

	@Override
	public E selectOne(Map<String, Object> conditions, int offset, Map<String, Order> orders) {
		final Map<String, Object> parameters = buildParameters(conditions);

		if(orders != null){
			parameters.put(ORDERS_PARAMETER_NAME, orders);
		}

		return getSqlSessionTemplate().selectOne(getStatement(DML.SELECT_ONE), parameters);
	}

	@Override
	public List<E> select(Map<String, Object> conditions, Map<String, Order> orders) {
		final Map<String, Object> parameters = buildParameters(conditions);

		if(orders != null){
			parameters.put(ORDERS_PARAMETER_NAME, orders);
		}

		return getSqlSessionTemplate().selectList(getStatement(DML.SELECT), parameters);
	}

	@Override
	public List<E> select(Map<String, Object> conditions, int offset, int size, Map<String, Order> orders) {
		Assert.isNegative(offset, "Offset argument value could not be negative integer");
		Assert.isZeroNegative(size, "Size argument value must be positive integer");

		final Map<String, Object> parameters = buildParameters(conditions);

		if(orders != null){
			parameters.put(ORDERS_PARAMETER_NAME, orders);
		}

		return getSqlSessionTemplate().selectList(getStatement(DML.SELECT), parameters, new RowBounds(offset, size));
	}

	@Override
	public Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize, Map<String, Order> orders) {
		Assert.isZeroNegative(page, "Page argument value must be positive integer");
		Assert.isZeroNegative(pagesize, "Pagesize argument value must be positive integer");

		long totalRecords = count(conditions);

		com.buession.dao.Pagination<E> pagination = new com.buession.dao.Pagination<>(page, pagesize, totalRecords);

		if(totalRecords > 0){
			final Map<String, Object> parameters = buildParameters(conditions);

			if(orders != null){
				parameters.put(ORDERS_PARAMETER_NAME, orders);
			}

			List<E> result = getSqlSessionTemplate().selectList(getStatement(DML.SELECT), parameters,
					new PageRowBounds(pagination.getOffset(), pagination.getPagesize()));
			pagination.setData(result);
		}

		return pagination;
	}

	@Override
	public List<E> getAll() {
		return getSqlSessionTemplate().selectList(getStatement(DML.GET_ALL));
	}

	@Override
	public long count() {
		return getSqlSessionTemplate().selectOne(getStatement("count"));
	}

	@Override
	public long count(Map<String, Object> conditions) {
		return getSqlSessionTemplate().selectOne(getStatement("count"), conditions);
	}

	@Override
	public int delete(Map<String, Object> conditions) {
		return getSqlSessionTemplate().delete(getStatement(DML.DELETE), conditions);
	}

	@Override
	public int delete(Map<String, Object> conditions, int size) {
		final Map<String, Object> parameters = conditions == null ? new HashMap<>(1) : new HashMap<>(conditions);

		parameters.put("SIZE", size);

		return getSqlSessionTemplate().delete(getStatement(DML.DELETE), parameters);
	}

	@Override
	public int deleteByPrimary(P primary) {
		return getSqlSessionTemplate().delete(getStatement(DML.DELETE_BY_PRIMARY), primary);
	}

	@Override
	public int clear() {
		return getSqlSessionTemplate().delete(getStatement(DML.CLEAR));
	}

	@Override
	public int truncate() {
		return getSqlSessionTemplate().delete(getStatement(DML.TRUNCATE));
	}

	protected void updatePrimary(E e, P primary) {
		final Collection<ResultMap> resultMaps = getSqlSessionTemplate().getConfiguration().getResultMaps();

		if(Validate.isEmpty(resultMaps)){
			return;
		}

		for(ResultMap resultMap : resultMaps){
			if(e.getClass().equals(resultMap.getType())){
				List<ResultMapping> resultMappings = resultMap.getIdResultMappings();

				if(Validate.isNotEmpty(resultMappings)){
					for(ResultMapping resultMapping : resultMappings){
						try{
							FieldUtils.writeField(e, resultMapping.getProperty(), primary, true);
						}catch(IllegalAccessException ex){
							if(logger.isErrorEnabled()){
								logger.error("Update resultMap[{}] id field '{}' with value: [{}] failure: {}",
										resultMap.getId(), resultMapping.getProperty(), primary, ex.getMessage());
							}
						}
					}
				}
				break;
			}
		}
	}

	protected static Map<String, Object> buildParameters(final Map<String, Object> conditions) {
		return conditions == null ? new LinkedHashMap<>(16) : new LinkedHashMap<>(conditions);
	}

	protected abstract String getStatement();

	protected String getStatement(final DML dml) {
		return getStatement(dml.toString());
	}

	protected String getStatement(final String dml) {
		return getStatement() + '.' + dml;
	}

}
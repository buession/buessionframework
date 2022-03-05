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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.buession.beans.BeanResolver;
import com.buession.beans.DefaultBeanResolver;
import com.buession.core.utils.FieldUtils;
import com.buession.core.utils.Assert;
import com.buession.core.utils.RandomUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Order;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buession.core.Pagination;
import com.buession.core.exception.OperationException;

/**
 * Data Access Object 抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractMyBatisDao<P, E> extends AbstractDao<P, E> implements MyBatisDao<P, E> {

	@Resource
	protected SqlSessionTemplate masterSqlSessionTemplate;

	@Resource
	protected List<SqlSessionTemplate> slaveSqlSessionTemplates;

	protected BeanResolver beanResolver = new DefaultBeanResolver();

	private final static Logger logger = LoggerFactory.getLogger(AbstractMyBatisDao.class);

	/**
	 * 返回 master SqlSessionTemplate
	 *
	 * @return master SqlSessionTemplate
	 */
	public SqlSessionTemplate getMasterSqlSessionTemplate(){
		return masterSqlSessionTemplate;
	}

	/**
	 * 设置 master SqlSessionTemplate
	 *
	 * @param masterSqlSessionTemplate
	 * 		master SqlSessionTemplate
	 */
	public void setMasterSqlSessionTemplate(final SqlSessionTemplate masterSqlSessionTemplate){
		this.masterSqlSessionTemplate = masterSqlSessionTemplate;
	}

	/**
	 * 返回 slave SqlSessionTemplate
	 *
	 * @return slave SqlSessionTemplate
	 */
	public List<SqlSessionTemplate> getSlaveSqlSessionTemplates(){
		return slaveSqlSessionTemplates;
	}

	/**
	 * 设置 slave SqlSessionTemplate
	 *
	 * @param slaveSqlSessionTemplates
	 * 		slave SqlSessionTemplate
	 */
	public void setSlaveSqlSessionTemplates(final List<SqlSessionTemplate> slaveSqlSessionTemplates){
		this.slaveSqlSessionTemplates = slaveSqlSessionTemplates;
	}

	/**
	 * 插入数据
	 *
	 * @param e
	 * 		实体类
	 *
	 * @return 主键值
	 */
	@Override
	public int insert(E e){
		return getMasterSqlSessionTemplate().insert(getStatement(DML.INSERT), e);
	}

	/**
	 * 替换数据
	 *
	 * @param e
	 * 		实体类
	 *
	 * @return 主键值
	 */
	@Override
	public int replace(E e){
		return getMasterSqlSessionTemplate().insert(getStatement(DML.REPLACE), e);
	}

	/**
	 * 更新数据
	 *
	 * @param e
	 * 		更新数据
	 * @param conditions
	 * 		更新条件
	 *
	 * @return 更新条数
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int update(E e, Map<String, Object> conditions){
		Assert.isNull(e, "The data could not be empty for update.");

		Map<String, Object> data = new HashMap<>(conditions == null ? 16 : conditions.size());
		if(conditions != null){
			data.putAll(conditions);
		}

		if(e instanceof Map){
			Map<Object, Object> eMap = (Map<Object, Object>) e;

			eMap.forEach((key, value)->{
				data.put(key.toString(), value);
			});
		}else{
			try{
				beanResolver.populate(data, e);
			}catch(IllegalAccessException ex){
				logger.error("Execute update command error: {}", ex.getMessage(), ex);
				return 0;
			}catch(NoSuchMethodException ex){
				logger.error("Execute update command error: {}", ex.getMessage(), ex);
				return 0;
			}catch(InvocationTargetException ex){
				logger.error("Execute update command error: {}", ex.getMessage(), ex);
				return 0;
			}
		}

		return getMasterSqlSessionTemplate().update(getStatement(DML.UPDATE), data);
	}

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
	@Override
	public int updateByPrimary(P primary, E e){
		updatePrimary(e, primary);
		return getMasterSqlSessionTemplate().update(getStatement(DML.UPDATE_BY_PRIMARY), e);
	}

	/**
	 * 根据主键查询数据
	 *
	 * @param primary
	 * 		主键值
	 *
	 * @return 数据结果
	 */
	@Override
	public E getByPrimary(P primary){
		try{
			return getSlaveSqlSessionTemplate().selectOne(getStatement(DML.GET_BY_PRIMARY), primary);
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return null;
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
	public E selectOne(Map<String, Object> conditions, int offset, Map<String, Order> orders){
		final Map<String, Object> parameters = buildParameters(conditions);

		if(orders != null){
			parameters.put(ORDERS_PARAMETER_NAME, orders);
		}

		try{
			return getSlaveSqlSessionTemplate().selectOne(getStatement(DML.SELECT_ONE), parameters);
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return null;
	}

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
	@Override
	public List<E> select(Map<String, Object> conditions, Map<String, Order> orders){
		final Map<String, Object> parameters = buildParameters(conditions);

		if(orders != null){
			parameters.put(ORDERS_PARAMETER_NAME, orders);
		}

		try{
			return getSlaveSqlSessionTemplate().selectList(getStatement(DML.SELECT), parameters);
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return null;
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
	 * @param orders
	 * 		排序
	 *
	 * @return 结果集
	 */
	@Override
	public List<E> select(Map<String, Object> conditions, int offset, int size, Map<String, Order> orders){
		Assert.isNegative(offset, "Offset argument value could not be negative integer");
		Assert.isZeroNegative(size, "Size argument value must be positive integer");

		final Map<String, Object> parameters = buildParameters(conditions);

		if(orders != null){
			parameters.put(ORDERS_PARAMETER_NAME, orders);
		}

		try{
			return getSlaveSqlSessionTemplate().selectList(getStatement(DML.SELECT), parameters, new RowBounds(offset, size));
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return null;
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
	 * @param orders
	 * 		排序
	 *
	 * @return Pagination
	 */
	@Override
	public Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize, Map<String, Order> orders){
		Assert.isZeroNegative(page, "Page argument value must be positive integer");
		Assert.isZeroNegative(pagesize, "Pagesize argument value must be positive integer");

		long totalRecords = count(conditions);

		com.buession.dao.Pagination<E> pagination = new com.buession.dao.Pagination<>(page, pagesize, totalRecords);

		if(totalRecords > 0){
			List<E> result = select(conditions, pagination.getOffset(), pagination.getPagesize(), orders);
			pagination.setData(result);
		}

		return pagination;
	}

	/**
	 * 查询所有数据
	 *
	 * @return 结果集
	 */
	@Override
	public List<E> getAll(){
		try{
			return getSlaveSqlSessionTemplate().selectList(getStatement(DML.GET_ALL));
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 数据记录总数
	 *
	 * @return 记录总数
	 */
	@Override
	public long count(){
		try{
			return getSlaveSqlSessionTemplate().selectOne(getStatement("count"));
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return 0L;
	}

	/**
	 * 数据记录总数
	 *
	 * @param conditions
	 * 		查询条件
	 *
	 * @return 记录总数
	 */
	@Override
	public long count(Map<String, Object> conditions){
		try{
			return getSlaveSqlSessionTemplate().selectOne(getStatement("count"), conditions);
		}catch(OperationException e){
			logger.error(e.getMessage());
		}

		return 0L;
	}

	/**
	 * 删除数据
	 *
	 * @param conditions
	 * 		删除条件
	 *
	 * @return 影响条数
	 */
	@Override
	public int delete(Map<String, Object> conditions){
		return getMasterSqlSessionTemplate().delete(getStatement(DML.DELETE), conditions);
	}

	/**
	 * 根据主键删除数据
	 *
	 * @param primary
	 * 		主键值
	 *
	 * @return 影响条数
	 */
	@Override
	public int deleteByPrimary(P primary){
		return getMasterSqlSessionTemplate().delete(getStatement(DML.DELETE_BY_PRIMARY), primary);
	}

	/**
	 * 清空数据
	 *
	 * @return 影响条数
	 */
	@Override
	public int clear(){
		return getMasterSqlSessionTemplate().delete(getStatement(DML.CLEAR));
	}

	/**
	 * 清空数据
	 *
	 * @return 影响条数
	 */
	@Override
	public int truncate(){
		return getMasterSqlSessionTemplate().delete(getStatement(DML.TRUNCATE));
	}

	protected final SqlSessionTemplate getSlaveSqlSessionTemplate(final int index) throws OperationException{
		if(Validate.isEmpty(slaveSqlSessionTemplates)){
			return getMasterSqlSessionTemplate();
		}else{
			SqlSessionTemplate sqlSessionTemplate = slaveSqlSessionTemplates.get(index);

			if(sqlSessionTemplate == null){
				throw new OperationException("Could not find the \"" + index + "\" slave SqlSessionTemplate.");
			}

			return sqlSessionTemplate;
		}
	}

	protected final SqlSessionTemplate getSlaveSqlSessionTemplate() throws OperationException{
		if(Validate.isEmpty(slaveSqlSessionTemplates)){
			return getMasterSqlSessionTemplate();
		}else if(slaveSqlSessionTemplates.size() == 1){
			return getSlaveSqlSessionTemplate(0);
		}else{
			int index = RandomUtils.nextInt(slaveSqlSessionTemplates.size());
			return getSlaveSqlSessionTemplate(index);
		}
	}

	@SuppressWarnings("unchecked")
	protected void updatePrimary(E e, P primary){
		final Collection<ResultMap> resultMaps = masterSqlSessionTemplate.getConfiguration().getResultMaps();

		if(Validate.isEmpty(resultMaps)){
			return;
		}

		for(ResultMap resultMap : resultMaps){
			if(resultMap.getType() == e.getClass()){
				List<ResultMapping> resultMappings = resultMap.getIdResultMappings();

				if(Validate.isNotEmpty(resultMappings)){
					for(ResultMapping resultMapping : resultMappings){
						try{
							FieldUtils.writeField(e, resultMapping.getProperty(), primary, true);
						}catch(IllegalAccessException ex){
						}
					}
				}
				break;
			}
		}
	}

	protected static Map<String, Object> buildParameters(final Map<String, Object> conditions){
		return conditions == null ? new LinkedHashMap<>(16) : new LinkedHashMap<>(conditions);
	}

	protected abstract String getStatement();

	protected String getStatement(final DML dml){
		return getStatement(dml.toString());
	}

	protected String getStatement(final String dml){
		final String statement = getStatement();
		final StringBuilder sb = new StringBuilder(statement.length() + dml.length() + 1);

		sb.append(statement).append('.').append(dml);

		return sb.toString();
	}

}
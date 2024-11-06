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

import com.buession.core.Pagination;
import com.buession.core.builder.MapBuilder;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.dao.mongodb.MongoDBOperatorUtils;
import com.buession.dao.mongodb.OrderToMongoDBSortDirectionConverter;
import com.buession.lang.Order;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MongoDB Data Access Object 抽象类
 *
 * @param <P>
 * 		注解类型
 * @param <E>
 * 		实体类
 *
 * @author Yong.Teng
 */
public abstract class AbstractMongoDBDao<P, E> extends AbstractDao<P, E> implements MongoDBDao<P, E> {

	/**
	 * {@link MongoTemplate}
	 *
	 * @since 2.3.3
	 */
	@Resource
	private MongoTemplate mongoTemplate;

	/**
	 * 返回 {@link MongoTemplate} 实例
	 *
	 * @return {@link MongoTemplate}
	 *
	 * @since 2.3.3
	 */
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * 设置 {@link MongoTemplate}
	 *
	 * @param mongoTemplate
	 *        {@link MongoTemplate}
	 *
	 * @since 2.3.3
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 插入数据
	 *
	 * @param e
	 * 		实体类
	 *
	 * @return 成功返回 1，失败返回 0
	 */
	@Override
	public int insert(E e) {
		getMongoTemplate().insert(e);
		return 1;
	}

	@Override
	public int update(E e, Map<String, Object> conditions) {
		final Criteria criteria = buildCriteria(conditions);
		final Query query = new Query(criteria);
		final Update update = new Update();

		if(e != null){
			BasicDBObject data = toDbObject(e);
			data.keySet().forEach((key)->update.set(key, data.get(key)));
		}

		UpdateResult writeResult = getMongoTemplate().updateFirst(query, update, getStatement());
		return (int) writeResult.getModifiedCount();
	}

	@Override
	public int updateByPrimary(P primary, E e) {
		return update(e, MapBuilder.of(PRIMARY_FIELD, primary));
	}

	@Override
	public E getByPrimary(P primary) {
		return selectOne(MapBuilder.of(PRIMARY_FIELD, primary));
	}

	@Override
	public E selectOne(Map<String, Object> conditions, int offset, Map<String, Order> orders) {
		final Criteria criteria = buildCriteria(conditions);
		final Query query = new Query(criteria);

		buildSort(query, orders);

		query.skip(offset);

		return getMongoTemplate().findOne(query, getStatement());
	}

	@Override
	public List<E> select(Map<String, Object> conditions, Map<String, Order> orders) {
		final Criteria criteria = buildCriteria(conditions);
		final Query query = new Query(criteria);

		buildSort(query, orders);

		return getMongoTemplate().find(query, getStatement());
	}

	@Override
	public List<E> select(Map<String, Object> conditions, int offset, int size, Map<String, Order> orders) {
		final Criteria criteria = buildCriteria(conditions);
		final Query query = new Query(criteria);

		return select(query, offset, size, orders);
	}

	public List<E> select(Query query, int offset, int size, Map<String, Order> orders) {
		Assert.isNull(query, "Query Argument could not be null");

		buildSort(query, orders);
		query.limit(size).skip(offset);

		return getMongoTemplate().find(query, getStatement());
	}

	@Override
	public Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize, Map<String, Order> orders) {
		final Criteria criteria = buildCriteria(conditions);
		final Query query = new Query(criteria);

		return paging(query, page, pagesize, orders);
	}

	public Pagination<E> paging(Query query, int page, int pagesize, Map<String, Order> orders) {
		Assert.isZeroNegative(page, "Page argument value must be positive integer");
		Assert.isZeroNegative(pagesize, "Pagesize argument value must be positive integer");

		long totalRecords = count(query);

		com.buession.dao.Pagination<E> pagination = new com.buession.dao.Pagination<>(page, pagesize, totalRecords);

		if(totalRecords > 0){
			List<E> result = select(query, pagination.getOffset(), pagination.getPagesize(), orders);
			pagination.setData(result);
		}

		return pagination;
	}

	@Override
	public List<E> getAll() {
		return getMongoTemplate().findAll(getStatement());
	}

	@Override
	public long count() {
		return count(new Query());
	}

	@Override
	public long count(Map<String, Object> conditions) {
		final Criteria criteria = buildCriteria(conditions);
		final Query query = new Query(criteria);

		return count(query);
	}

	public long count(Query query) {
		Assert.isNull(query, "Query Argument could not be null");
		return getMongoTemplate().count(query, getStatement());
	}

	@Override
	public int delete(Map<String, Object> conditions) {
		final Query query = new Query();
		return delete(query, conditions);
	}

	@Override
	public int delete(Map<String, Object> conditions, int size) {
		final Query query = new Query();

		query.limit(size);

		return delete(query, conditions);
	}

	@Override
	public int deleteByPrimary(P primary) {
		final Criteria criteria = Criteria.where(PRIMARY_FIELD).is(primary);
		final Query query = new Query(criteria);

		DeleteResult writeResult = getMongoTemplate().remove(query, getStatement());
		return (int) writeResult.getDeletedCount();
	}

	@Override
	public int clear() {
		DeleteResult writeResult = getMongoTemplate().remove(new Query(), getStatement());
		return (int) writeResult.getDeletedCount();
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getStatement() {
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	protected static Criteria buildCriteria(Map<String, Object> conditions) {
		if(conditions == null){
			return null;
		}

		Criteria criteria = new Criteria();

		conditions.forEach((field, value)->{
			if(value instanceof MongoOperation){
				MongoOperation mongoOperation = (MongoOperation) value;
				MongoDBOperatorUtils.operator(criteria, field, mongoOperation);
			}else{
				criteria.and(field).is(value);
			}
		});

		return criteria;
	}

	protected void buildSort(final Query query, final Map<String, Order> orders) {
		if(Validate.isNotEmpty(orders)){
			final OrderToMongoDBSortDirectionConverter orderToMongoDBSortDirectionConverter =
					new OrderToMongoDBSortDirectionConverter();
			final List<Sort.Order> sortOrders = new ArrayList<>(orders.size());

			orders.forEach((field, order)->{
				Sort.Direction direction = orderToMongoDBSortDirectionConverter.convert(order);

				if(direction != null){
					sortOrders.add(new Sort.Order(direction, field));
				}
			});

			query.with(Sort.by(sortOrders));
		}
	}

	private int delete(final Query query, final Map<String, Object> conditions) {
		query.addCriteria(buildCriteria(conditions));

		DeleteResult writeResult = getMongoTemplate().remove(query, getStatement());
		return (int) writeResult.getDeletedCount();
	}

	private BasicDBObject toDbObject(E e) {
		BasicDBObject doc = new BasicDBObject();
		getMongoTemplate().getConverter().write(e, doc);
		return doc;
	}

}
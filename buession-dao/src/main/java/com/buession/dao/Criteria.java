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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import com.buession.core.utils.Assert;
import com.buession.dao.exception.PersistenceException;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Central class for creating queries.
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class Criteria {

	public final static Object NOT_SET = new Object();

	/**
	 * 查询链
	 */
	private final List<Criteria> criteriaChain;

	private final LinkedHashMap<Operator, Object> criteria = new LinkedHashMap<>();

	/**
	 * 字段名称
	 */
	private String fieldName;

	@Nullable
	private Object isValue = NOT_SET;

	/**
	 * 构造函数
	 */
	public Criteria() {
		criteriaChain = new ArrayList<>();
	}

	/**
	 * 构造函数
	 *
	 * @param fieldName
	 * 		字段名称
	 */
	public Criteria(String fieldName) {
		this(new ArrayList<>(), fieldName);
	}

	/**
	 * 构造函数
	 *
	 * @param criteriaChain
	 * 		查询链
	 * @param fieldName
	 * 		字段名称
	 */
	protected Criteria(List<Criteria> criteriaChain, String fieldName) {
		Assert.isBlank(fieldName, "Field name can not be empty.");
		this.criteriaChain = criteriaChain;
		this.criteriaChain.add(this);
		this.fieldName = fieldName;
	}

	/**
	 * 返回字段名称
	 *
	 * @return 字段名称
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 返回查询链
	 *
	 * @return 查询链
	 */
	public List<Criteria> getCriteriaChain() {
		return criteriaChain;
	}

	/**
	 * 返回查询条件
	 *
	 * @return 查询条件
	 */
	public LinkedHashMap<Operator, Object> getCriteria() {
		return criteria;
	}

	/**
	 * Static factory method to create a Criteria using the provided key
	 *
	 * @param fieldName
	 * 		字段名称
	 *
	 * @return {@link Criteria}
	 */
	public Criteria and(String fieldName) {
		return new Criteria(this.criteriaChain, fieldName);
	}

	/**
	 * Creates a criterion using equality
	 *
	 * @param value
	 * 		can be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria is(@Nullable Object value) {
		if(NOT_SET.equals(isValue) == false){
			throw new PersistenceException(
					"Multiple 'is' values declared; You need to use 'and' with multiple criteria");
		}

		if(lastOperatorWasNot()){
			throw new PersistenceException("Invalid query: 'not' can't be used with 'is' - use 'ne' instead");
		}

		this.isValue = value;
		return this;
	}

	/**
	 * Creates a criterion using {@literal null} equality comparison which matches documents that either contain the item
	 * field whose value is {@literal null} or that do not contain the item field.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria isNull() {
		return is(null);
	}

	/**
	 * Creates a criterion using the {@literal $ne} operator.
	 *
	 * @param value
	 * 		can be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria ne(@Nullable Object value) {
		criteria.put(Operator.NOT_EQUAL, value);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $not} meta operator which affects the clause directly following
	 *
	 * @return {@link Criteria}
	 */
	public Criteria not() {
		return not(null);
	}

	/**
	 * Creates a criterion using the {@literal $not} operator.
	 *
	 * @param value
	 * 		can be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	private Criteria not(@Nullable Object value) {
		criteria.put(Operator.NOT, value);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $lt} operator.
	 *
	 * @param value
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria lt(@Nullable Object value) {
		criteria.put(Operator.LT, value);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $lte} operator.
	 *
	 * @param value
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria lte(@Nullable Object value) {
		criteria.put(Operator.LTE, value);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $gt} operator.
	 *
	 * @param value
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria gt(@Nullable Object value) {
		criteria.put(Operator.GT, value);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $gte} operator.
	 *
	 * @param value
	 * 		can be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria gte(@Nullable Object value) {
		criteria.put(Operator.GTE, value);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $in} operator.
	 *
	 * @param values
	 * 		the values to match against
	 *
	 * @return {@link Criteria}
	 */
	public Criteria in(Object... values) {
		if(values.length > 1 && values[1] instanceof Collection){
			throw new PersistenceException(
					"You can only pass in one argument of type " + values[1].getClass().getName());
		}
		criteria.put(Operator.IN, Arrays.asList(values));
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $in} operator.
	 *
	 * @param values
	 * 		the collection containing the values to match against
	 *
	 * @return {@link Criteria}
	 */
	public Criteria in(Collection<?> values) {
		criteria.put(Operator.IN, values);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal $nin} operator.
	 *
	 * @param values
	 * 		the values to not match against
	 *
	 * @return {@link Criteria}
	 */
	public Criteria nin(Object... values) {
		if(values.length > 1 && values[1] instanceof Collection){
			throw new PersistenceException(
					"You can only pass in one argument of type " + values[1].getClass().getName());
		}
		return nin(Arrays.asList(values));
	}

	/**
	 * Creates a criterion using the {@literal $nin} operator.
	 *
	 * @param values
	 * 		the collection containing the values to not match against
	 *
	 * @return {@link Criteria}
	 */
	public Criteria nin(Collection<?> values) {
		criteria.put(Operator.NIN, values);
		return this;
	}

	/**
	 * Creates a criterion using the {@literal like} operator.
	 *
	 * @param value
	 * 		can be {@literal null}.
	 *
	 * @return {@link Criteria}
	 */
	public Criteria like(@Nullable Object value) {
		criteria.put(Operator.LIKE, value);
		return this;
	}

	/**
	 * Creates a criteria using the {@code $or} operator for all of the provided criteria.
	 * <p>
	 * Note that MongoDB doesn't support an {@code $nor} operator to be wrapped in a {@code $not} operator.
	 *
	 * @param criteria
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 *
	 * @throws IllegalArgumentException
	 * 		if this method follows a {@link #not()} call directly.
	 */
	public Criteria orOperator(Criteria... criteria) {
		Assert.isNull(criteria, "Criteria must not be null");
		return orOperator(Arrays.asList(criteria));
	}

	/**
	 * Creates a criteria using the {@code $or} operator for all of the provided criteria.
	 * <p>
	 * Note that MongoDB doesn't support an {@code $nor} operator to be wrapped in a {@code $not} operator.
	 *
	 * @param criteria
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 *
	 * @throws IllegalArgumentException
	 * 		if this method follows a {@link #not()} call directly.
	 */
	public Criteria orOperator(Collection<Criteria> criteria) {
		Assert.isNull(criteria, "Criteria must not be null");
		return registerCriteriaChainElement(new Criteria("$or").is(criteria));
	}

	/**
	 * Creates a criteria using the {@code $nor} operator for all of the provided criteria.
	 * <p>
	 * Note that MongoDB doesn't support an {@code $nor} operator to be wrapped in a {@code $not} operator.
	 *
	 * @param criteria
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 *
	 * @throws IllegalArgumentException
	 * 		if this method follows a {@link #not()} call directly.
	 */
	public Criteria norOperator(Criteria... criteria) {
		Assert.isNull(criteria, "Criteria must not be null");
		return norOperator(Arrays.asList(criteria));
	}

	/**
	 * Creates a criteria using the {@code $nor} operator for all of the provided criteria.
	 * <p>
	 * Note that MongoDB doesn't support an {@code $nor} operator to be wrapped in a {@code $not} operator.
	 *
	 * @param criteria
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 *
	 * @throws IllegalArgumentException
	 * 		if this method follows a {@link #not()} call directly.
	 */
	public Criteria norOperator(Collection<Criteria> criteria) {
		Assert.isNull(criteria, "Criteria must not be null");
		return registerCriteriaChainElement(new Criteria("$nor").is(criteria));
	}

	/**
	 * Creates a criteria using the {@code $and} operator for all of the provided criteria.
	 * <p>
	 * Note that MongoDB doesn't support an {@code $and} operator to be wrapped in a {@code $not} operator.
	 *
	 * @param criteria
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 *
	 * @throws IllegalArgumentException
	 * 		if this method follows a {@link #not()} call directly.
	 */
	public Criteria andOperator(Criteria... criteria) {
		Assert.isNull(criteria, "Criteria must not be null");
		return andOperator(Arrays.asList(criteria));
	}

	/**
	 * Creates a criteria using the {@code $and} operator for all of the provided criteria.
	 * <p>
	 * Note that MongoDB doesn't support an {@code $and} operator to be wrapped in a {@code $not} operator.
	 *
	 * @param criteria
	 * 		must not be {@literal null}.
	 *
	 * @return {@link Criteria}
	 *
	 * @throws IllegalArgumentException
	 * 		if this method follows a {@link #not()} call directly.
	 */
	public Criteria andOperator(Collection<Criteria> criteria) {
		Assert.isNull(criteria, "Criteria must not be null");
		return registerCriteriaChainElement(new Criteria("$and").is(criteria));
	}

	private boolean lastOperatorWasNot() {
		return criteria.isEmpty() == false &&
				Operator.NOT_EQUAL.equals(criteria.keySet().toArray()[criteria.size() - 1]);
	}

	private Criteria registerCriteriaChainElement(Criteria criteria) {
		if(lastOperatorWasNot()){
			throw new IllegalArgumentException("operator $not is not allowed around criteria chain element.");
		}else{
			criteriaChain.add(criteria);
		}
		return this;
	}

}

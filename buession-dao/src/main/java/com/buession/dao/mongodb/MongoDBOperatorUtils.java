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
package com.buession.dao.mongodb;

import com.buession.core.utils.Assert;
import com.buession.dao.MongoOperation;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * MongoDB Operator 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class MongoDBOperatorUtils {

	/**
	 * Criteria 运算操作
	 *
	 * @param criteria
	 *        {@link Criteria}
	 * @param field
	 * 		字段名称
	 * @param mongoOperation
	 *        {@link MongoOperation}
	 */
	public static void operator(final Criteria criteria, final String field, final MongoOperation mongoOperation){
		Assert.isNull(criteria, "Criteria cloud not be null.");
		Assert.isBlank(field, "Field cloud not be null or empty.");
		Assert.isNull(mongoOperation, "MongoOperation cloud not be null.");

		operator(criteria, field, mongoOperation.getOperator(), mongoOperation.getValue());
	}

	/**
	 * Criteria 运算操作
	 *
	 * @param criteria
	 *        {@link Criteria}
	 * @param field
	 * 		字段名称
	 * @param operator
	 * 		运算方式
	 * @param value
	 * 		运算值
	 */
	public static void operator(final Criteria criteria, final String field, final MongoOperation.Operator operator, final Object value){
		Assert.isNull(criteria, "Criteria cloud not be null.");
		Assert.isBlank(field, "Field cloud not be null or empty.");
		Assert.isNull(operator, "MongoOperation Operator cloud not be null.");

		if(value == null){
			return;
		}

		switch(operator){
			/* 等于 */
			case EQUAL:
				criteria.and(field).is(value);
				break;
			/* 不等于 */
			case NOT_EQUAL:
				criteria.and(field).ne(value);
				break;
			/* 小于 */
			case LT:
				criteria.and(field).lt(value);
				break;
			/* 小于等于 */
			case LTE:
				criteria.and(field).lte(value);
				break;
			/* 大于 */
			case GT:
				criteria.and(field).gt(value);
				break;
			/* 大于等于 */
			case GTE:
				criteria.and(field).gte(value);
				break;
			/* IN */
			case IN:
				criteria.and(field).in(value);
				break;
			/* NOT IN */
			case NIN:
				criteria.and(field).nin(value);
				break;
			/* 正则 */
			case LIKE:
				criteria.and(field).regex(value.toString());
				break;
			default:
				break;
		}
	}

}

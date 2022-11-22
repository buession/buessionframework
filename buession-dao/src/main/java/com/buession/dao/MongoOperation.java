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

/**
 * @author Yong.Teng
 */
public final class MongoOperation {

	/**
	 * 运算符
	 */
	private Operator operator;

	/**
	 * 运算值
	 */
	private Object value;

	/**
	 * 构造函数，默认以 {@link Operator.EQUAL} 方式运算
	 *
	 * @param value
	 * 		运算值
	 */
	public MongoOperation(final Object value){
		this(Operator.EQUAL, value);
	}

	/**
	 * 构造函数
	 *
	 * @param operator
	 * 		运算符 {@link Operator}
	 * @param value
	 * 		运算值
	 */
	public MongoOperation(final Operator operator, final Object value){
		this.operator = operator;
		this.value = value;
	}

	/**
	 * 等于
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation eq(final Object value){
		return new MongoOperation(Operator.EQUAL, value);
	}

	/**
	 * 不等于
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation neq(final Object value){
		return new MongoOperation(Operator.NOT_EQUAL, value);
	}

	/**
	 * 小于
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation lt(final Object value){
		return new MongoOperation(Operator.LT, value);
	}

	/**
	 * 小于等于
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation lte(final Object value){
		return new MongoOperation(Operator.LTE, value);
	}

	/**
	 * 大于
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation gt(final Object value){
		return new MongoOperation(Operator.GT, value);
	}

	/**
	 * IN
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation in(final Object value){
		return new MongoOperation(Operator.IN, value);
	}

	/**
	 * Not IN
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation nin(final Object value){
		return new MongoOperation(Operator.NIN, value);
	}

	/**
	 * LIKE
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation like(final Object value){
		return new MongoOperation(Operator.LIKE, value);
	}

	/**
	 * 大于等于
	 *
	 * @param value
	 * 		值
	 *
	 * @return MongoOperation
	 *
	 * @since 1.3.1
	 */
	public static MongoOperation gte(final Object value){
		return new MongoOperation(Operator.GTE, value);
	}

	/**
	 * 返回运算符 {@link Operator}
	 *
	 * @return 运算符
	 */
	public Operator getOperator(){
		return operator;
	}

	/**
	 * 设置运算符 {@link Operator}
	 *
	 * @param operator
	 * 		运算符
	 */
	public void setOperator(Operator operator){
		this.operator = operator;
	}

	/**
	 * 返回运算值
	 *
	 * @return 运算值
	 */
	public Object getValue(){
		return value;
	}

	/**
	 * 设置运算值
	 *
	 * @param value
	 * 		运算值
	 */
	public void setValue(Object value){
		this.value = value;
	}

	/**
	 * 运算符
	 */
	public enum Operator {

		/**
		 * 等于
		 */
		EQUAL,

		/**
		 * 不等于
		 */
		NOT_EQUAL,

		/**
		 * 小于
		 */
		LT,

		/**
		 * 小于等于
		 */
		LTE,

		/**
		 * 大于
		 */
		GT,

		/**
		 * 单元等于
		 */
		GTE,

		/**
		 * IN
		 */
		IN,

		/**
		 * Not IN
		 */
		NIN,

		/**
		 * Like
		 */
		LIKE

	}

}

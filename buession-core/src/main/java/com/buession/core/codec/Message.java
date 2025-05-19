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
package com.buession.core.codec;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息
 *
 * @author Yong.Teng
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Message {

	String DEFAULT_CODE_FIELD = "code";

	String DEFAULT_TEXT_FIELD = "message";

	/**
	 * 消息 Key 名称
	 *
	 * @return 消息 Key 名称
	 */
	@AliasFor("value")
	String name() default "";

	/**
	 * 消息 Key 名称
	 *
	 * @return 消息 Key 名称
	 */
	@AliasFor("name")
	String value() default "";

	/**
	 * 错误码属性名称
	 *
	 * @return 错误码属性名称
	 */
	String codeField() default DEFAULT_CODE_FIELD;

	/**
	 * 错误文本消息属性名称
	 *
	 * @return 错误文本消息属性名称
	 */
	String textField() default DEFAULT_TEXT_FIELD;

	/**
	 * 是否必须
	 *
	 * @return true / false
	 */
	boolean required() default true;

}

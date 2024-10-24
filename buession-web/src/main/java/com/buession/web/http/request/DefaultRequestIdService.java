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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http.request;

import com.buession.core.id.IdGenerator;
import com.buession.core.id.UUIDIdGenerator;
import com.buession.core.utils.Assert;

/**
 * 默认请求 ID 服务
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class DefaultRequestIdService extends AbstractRequestIdService {

	/**
	 * ID 生成器
	 *
	 * @since 1.3.1
	 */
	private final IdGenerator<String> idGenerator;

	/**
	 * 构造函数，默认使用 {@link UUIDIdGenerator}
	 */
	public DefaultRequestIdService(){
		idGenerator = new UUIDIdGenerator();
	}

	/**
	 * 构造函数
	 *
	 * @param idGenerator
	 * 		ID 生成器
	 */
	public DefaultRequestIdService(final IdGenerator<String> idGenerator){
		Assert.isNull(idGenerator, "IdGenerator cloud not be null.");
		this.idGenerator = idGenerator;
	}

	@Override
	public String generate(){
		return idGenerator.nextId();
	}

}

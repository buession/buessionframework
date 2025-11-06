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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import java.io.Serializable;
import java.util.Map;

/**
 * Stream 消费者基类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
class BaseStreamConsumer implements Serializable {

	private final static long serialVersionUID = -5325953754460072820L;

	/**
	 * 消费者名称
	 */
	private final String name;

	private final Map<String, Object> infos;

	/**
	 * 返回消费者名称
	 *
	 * @param name
	 * 		消费者名称
	 * @param infos
	 * 		-
	 */
	public BaseStreamConsumer(final String name, final Map<String, Object> infos) {
		this.name = name;
		this.infos = infos;
	}

	/**
	 * 返回消费者名称
	 *
	 * @return 消费者名称
	 */
	public String getName() {
		return name;
	}

	public Map<String, Object> getInfos() {
		return infos;
	}

}

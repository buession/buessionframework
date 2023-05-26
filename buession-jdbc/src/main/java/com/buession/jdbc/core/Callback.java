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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.jdbc.core;

import com.buession.core.Configurer;

/**
 * {@link javax.sql.DataSource} 初始化回调
 *
 * @param <D>
 *        {@link javax.sql.DataSource}
 * @param <C>
 * 		配置
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
@FunctionalInterface
public interface Callback<D extends javax.sql.DataSource, C> extends Configurer<D, C> {

	/**
	 * {@link javax.sql.DataSource} 初始化回调
	 *
	 * @param dataSource
	 *        {@link javax.sql.DataSource} 实例
	 * @param properties
	 * 		配置
	 *
	 * @return {@link javax.sql.DataSource} 实例
	 */
	D apply(D dataSource, C properties);

	@Override
	default void configure(D dataSource, C properties){
		apply(dataSource, properties);
	}

}

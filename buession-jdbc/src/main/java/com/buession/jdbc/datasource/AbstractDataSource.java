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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.jdbc.datasource;

import com.buession.jdbc.datasource.config.PoolConfiguration;

/**
 * DataSource 抽象类
 *
 * @param <T>
 *        {@link javax.sql.DataSource} 数据源类型
 * @param <P>
 * 		连接池配置 {@link PoolConfiguration} 实现
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public abstract class AbstractDataSource<T extends javax.sql.DataSource, P extends PoolConfiguration> implements DataSource<T, P> {

	/**
	 * 连接池配置
	 */
	private P poolConfiguration;

	/**
	 * 构造函数
	 */
	public AbstractDataSource(){
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public AbstractDataSource(P poolConfiguration){
		this.poolConfiguration = poolConfiguration;
	}

	@Override
	public P getPoolConfiguration(){
		return poolConfiguration;
	}

	@Override
	public void setPoolConfiguration(P poolConfiguration){
		this.poolConfiguration = poolConfiguration;
	}

	protected void applyPoolConfiguration(final T dataSource, final P poolConfiguration){

	}

}

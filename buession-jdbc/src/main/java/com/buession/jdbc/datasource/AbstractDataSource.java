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
package com.buession.springboot.datasource.core.datasource;

import java.util.List;

/**
 * DataSource 抽象类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public abstract class AbstractDataSource<T extends javax.sql.DataSource> implements DataSource<T> {

	/**
	 * Master 数据源
	 */
	private T master;

	/**
	 * Slave 数据源
	 */
	private List<T> slaves;

	/**
	 * 构造函数
	 */
	public AbstractDataSource(){
	}

	/**
	 * 构造函数
	 *
	 * @param master
	 * 		Master 数据源
	 * @param slaves
	 * 		Slave 数据源
	 */
	public AbstractDataSource(T master, List<T> slaves){
		this.master = master;
		this.slaves = slaves;
	}

	@Override
	public T getMaster(){
		return master;
	}

	@Override
	public void setMaster(T master){
		this.master = master;
	}

	@Override
	public List<T> getSlaves(){
		return slaves;
	}

	@Override
	public void setSlaves(List<T> slaves){
		this.slaves = slaves;
	}

}

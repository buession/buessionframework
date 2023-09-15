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
package com.buession.dao.mybatis.interceptor;

import com.buession.dao.mybatis.dialect.Dialect;
import com.buession.dao.mybatis.utils.DialectUtils;
import com.buession.dao.mybatis.utils.JdbcUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;

/**
 * @author Yong.Teng
 * @since 2.3.1
 */
public abstract class AbstractInterceptor implements Interceptor {

	/**
	 * 数据库方言
	 */
	private Dialect dialect;

	/**
	 * 返回数据库方言
	 *
	 * @return 数据库方言
	 */
	public Dialect getDialect() {
		return dialect;
	}

	/**
	 * 获取分页方言类的逻辑
	 *
	 * @param executor
	 * 		Executor
	 *
	 * @return 分页方言类
	 */
	protected Dialect findIDialect(Executor executor) {
		if(dialect != null){
			return dialect;
		}

		dialect = DialectUtils.getDialect(JdbcUtils.getDbType(executor));
		return dialect;
	}

}

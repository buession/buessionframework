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
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * 分页拦截器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
@Intercepts(
		{
				@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
				@Signature(type = StatementHandler.class, method = "query", args = {Statement.class,
						ResultHandler.class}),
				@Signature(type = StatementHandler.class, method = "getBoundSql", args = {}),
				@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
						RowBounds.class, ResultHandler.class}),
				@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
						RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
		}
)
public class PaginationInterceptor extends AbstractInterceptor {

	@SuppressWarnings({"rawtypes"})
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		Object[] args = invocation.getArgs();

		if(target instanceof Executor){
			final MappedStatement statement = (MappedStatement) args[0];

			if(statement.getSqlCommandType() == SqlCommandType.SELECT){
				final Executor executor = (Executor) target;

				Object parameter = args[1];
				RowBounds rowBounds = (RowBounds) args[2];
				ResultHandler resultHandler = (ResultHandler) args[3];

				BoundSql boundSql = args.length == 4 ? statement.getBoundSql(parameter) : (BoundSql) args[5];

				Dialect dialect = findDialect(executor);

				if(dialect.supportsLimit()){
					boundSql = createPaginationBoundSql(dialect, statement, boundSql, rowBounds);
				}

				CacheKey cacheKey = executor.createCacheKey(statement, parameter, rowBounds, boundSql);
				return executor.query(statement, parameter, rowBounds, resultHandler, cacheKey, boundSql);
			}
		}else{
			// StatementHandler
			final StatementHandler sh = (StatementHandler) target;
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if(target instanceof Executor || target instanceof StatementHandler){
			return Plugin.wrap(target, this);
		}

		return target;
	}

	@Override
	public void setProperties(Properties properties) {
	}

	private static BoundSql createPaginationBoundSql(final Dialect dialect, final MappedStatement statement,
													 final BoundSql originalBoundSql, final RowBounds rowBounds) {
		final String sql = dialect.buildPaginationSql(originalBoundSql.getSql(), rowBounds.getOffset(),
				rowBounds.getLimit());
		return new BoundSql(statement.getConfiguration(), sql, originalBoundSql.getParameterMappings(),
				originalBoundSql.getParameterObject());
	}

}

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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
class ConnectionSplittingInterceptor implements org.aopalliance.intercept.MethodInterceptor,
		org.springframework.cglib.proxy.MethodInterceptor {

	private final RedisConnectionFactory factory;

	private final static Logger logger = LoggerFactory.getLogger(ConnectionSplittingInterceptor.class);

	public ConnectionSplittingInterceptor(RedisConnectionFactory factory){
		this.factory = factory;
	}

	@Override
	public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable{
		logger.debug("Invoke '{}' on unbound connection.", method.getName());

		RedisConnection connection = factory.getConnection();

		try{
			return invoke(method, connection, args);
		}finally{
			if(connection.isClosed() == false){
				connection.close();
			}
		}
	}

	private Object invoke(Method method, Object target, Object[] args) throws Throwable{
		try{
			return method.invoke(target, args);
		}catch(InvocationTargetException e){
			throw e.getCause();
		}
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable{
		return intercept(invocation.getThis(), invocation.getMethod(), invocation.getArguments(), null);
	}

}

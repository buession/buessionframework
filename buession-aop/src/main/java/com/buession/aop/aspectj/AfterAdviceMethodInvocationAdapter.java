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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.aop.aspectj;

import com.buession.aop.exception.SignatureIllegalArgumentException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.AdviceSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 后置通知，在方法执行之后执行
 *
 * @author Yong.Teng
 * @see org.aspectj.lang.annotation.After
 */
public class AfterAdviceMethodInvocationAdapter extends AbstractAdviceMethodInvocationAdapter {

	/**
	 * 构造函数
	 *
	 * @param object
	 * 		当前连接点静态部分的对象，一般指被代理的目标对象
	 * @param method
	 * 		正在被调用的方法的 {@link Method} 对象
	 * @param arguments
	 * 		调用目标方法的参数
	 */
	public AfterAdviceMethodInvocationAdapter(Object object, Method method, Object[] arguments) {
		super(object, method, arguments);
	}

	/**
	 * 从 AspectJ {@link JoinPoint} 创建 {@link AfterAdviceMethodInvocationAdapter} 实例
	 *
	 * @param joinPoint
	 * 		AspectJ {@link JoinPoint}
	 *
	 * @return {@link AfterAdviceMethodInvocationAdapter} 实例
	 */
	public static AfterAdviceMethodInvocationAdapter createFromJoinPoint(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();

		if(signature instanceof MethodSignature){
			return new AfterAdviceMethodInvocationAdapter(joinPoint.getThis(), ((MethodSignature) signature)
					.getMethod(), joinPoint.getArgs());
		}else if(signature instanceof AdviceSignature){
			return new AfterAdviceMethodInvocationAdapter(joinPoint.getThis(), ((AdviceSignature) signature)
					.getAdvice(), joinPoint.getArgs());
		}else{
			throw new SignatureIllegalArgumentException(signature);
		}
	}

}

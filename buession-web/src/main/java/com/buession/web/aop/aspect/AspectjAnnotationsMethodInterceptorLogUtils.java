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
package com.buession.web.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

import java.util.Arrays;

/**
 * @author Yong.Teng
 */
public class AspectjAnnotationsMethodInterceptorLogUtils {

	private AspectjAnnotationsMethodInterceptorLogUtils(){

	}

	public static void performAfterInterceptionDebug(final Logger logger, final JoinPoint joinPoint){
		if(logger.isTraceEnabled()){
			StringBuilder message = new StringBuilder(255);

			message.append("Invoking a method decorated with a Buession annotation\n");
			message.append("\tkind       : ").append(joinPoint.getKind()).append("\n");
			message.append("\tjoinPoint  : ").append(joinPoint).append("\n");
			message.append("\tannotations: ").append(Arrays.toString(((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations())).append("\n");
			message.append("\ttarget     : ").append(joinPoint.getTarget());

			logger.trace(message.toString());
		}
	}

}

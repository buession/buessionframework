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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class OnWebFluxCondition implements Condition {

	private static volatile Boolean result = null;

	@Override
	public boolean matches(@Nullable ConditionContext context, @Nullable AnnotatedTypeMetadata metadata) {
		if(result == null){
			synchronized(OnWebFluxCondition.class){
				if(result == null){
					ClassLoader classLoader = context == null || context.getClassLoader() == null ?
									OnWebFluxCondition.class.getClassLoader() : context.getClassLoader();
					result = ClassUtils.isPresent("org.springframework.web.reactive.config.WebFluxConfigurer",
							classLoader);
				}
			}
		}

		return result;
	}

}

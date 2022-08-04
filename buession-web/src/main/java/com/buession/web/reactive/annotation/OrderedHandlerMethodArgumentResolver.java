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
package com.buession.web.reactive.annotation;

import com.buession.core.utils.Assert;
import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import com.buession.web.bind.Order;
import com.buession.web.bind.annotation.Ordered;
import com.buession.web.bind.annotation.OrderedGroup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.result.method.annotation.AbstractNamedValueSyncArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法参数注解 {@link Ordered} 解析器
 *
 * @author Yong.Teng
 * @since 2.1.0
 */
public class OrderedHandlerMethodArgumentResolver extends AbstractNamedValueSyncArgumentResolver {

	public OrderedHandlerMethodArgumentResolver(@Nullable ConfigurableBeanFactory factory,
												ReactiveAdapterRegistry registry){
		super(factory, registry);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter){
		if(parameter.hasParameterAnnotation(Ordered.class) == false){
			return false;
		}

		Class<?> clazz = parameter.nestedIfOptional().getNestedParameterType();
		return Order[].class.isAssignableFrom(clazz);
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter){
		Ordered ordered = parameter.getParameterAnnotation(Ordered.class);
		Assert.isNull(ordered, "No Ordered annotation");
		return new OrderByNamedValueInfo(ordered);
	}

	@Nullable
	@Override
	protected Object resolveNamedValue(String name, MethodParameter parameter, ServerWebExchange exchange){
		Ordered ordered = parameter.getParameterAnnotation(Ordered.class);
		OrderedGroup[] orderedGroup = ordered.value();

		if(Validate.isEmpty(orderedGroup)){
			return new Order[]{};
		}

		List<Order> orders = new ArrayList<>(orderedGroup.length);
		String orderBy;
		String order;

		ServerHttpRequest request = exchange.getRequest();
		MultiValueMap<String, String> parameters = request.getQueryParams();

		for(OrderedGroup group : orderedGroup){
			orderBy = parameters.getFirst(group.field());
			order = parameters.getFirst(group.order());

			if(Validate.hasText(orderBy) && Validate.hasText(order)){
				com.buession.lang.Order orderValue = EnumUtils.getEnum(com.buession.lang.Order.class,
						order.toUpperCase());

				if(orderValue != null){
					orders.add(new Order(orderBy, orderValue));
				}
			}
		}

		return orders.toArray(new Order[]{});
	}

	private final static class OrderByNamedValueInfo extends NamedValueInfo {

		private OrderByNamedValueInfo(Ordered annotation){
			super(Ordered.class.getName(), annotation.required(), null);
		}

	}

}

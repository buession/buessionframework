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
package com.buession.web.servlet.annotation;

import com.buession.core.utils.Assert;
import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import com.buession.web.bind.Order;
import com.buession.web.bind.annotation.Ordered;
import com.buession.web.bind.annotation.OrderedGroup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法参数注解 {@link Ordered} 解析器
 *
 * @author Yong.Teng
 * @since 2.0.3
 */
public class OrderedHandlerMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

	public OrderedHandlerMethodArgumentResolver(){
		super();
	}

	public OrderedHandlerMethodArgumentResolver(
			@Nullable ConfigurableBeanFactory beanFactory){
		super(beanFactory);
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

	@Override
	@Nullable
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request){
		HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);

		Ordered ordered = parameter.getParameterAnnotation(Ordered.class);
		OrderedGroup[] orderedGroup = ordered.value();

		if(Validate.isEmpty(orderedGroup)){
			return new Order[]{};
		}

		List<Order> orders = new ArrayList<>(orderedGroup.length);
		String orderBy;
		String order;

		for(OrderedGroup group : orderedGroup){
			orderBy = servletRequest.getParameter(group.field());
			order = servletRequest.getParameter(group.order());

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

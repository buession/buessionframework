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
package com.buession.core.converter;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StatusUtils;
import com.buession.lang.Status;

import java.util.function.Predicate;

/**
 * 通过 {@link Predicate} 比较参数值转换为 {@link Status}
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class PredicateStatusConverter<S> implements Converter<S, Status> {

	private final Predicate<S> predicate;

	public PredicateStatusConverter(final Predicate<S> predicate){
		Assert.isNull(predicate, "Predicate cloud not be null.");
		this.predicate = predicate;
	}

	@Override
	public Status convert(final S source){
		return StatusUtils.valueOf(predicate.test(source));
	}

}
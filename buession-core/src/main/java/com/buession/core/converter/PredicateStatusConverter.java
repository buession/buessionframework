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
package com.buession.core.converter;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StatusUtils;
import com.buession.lang.Status;

import java.util.function.Predicate;

/**
 * 通过 {@link Predicate} 比较参数值转换为 {@link Status}
 *
 * @param <T>
 * 		谓词的输入类型
 *
 * @author Yong.Teng
 * @see Predicate
 * @since 1.2.1
 */
public class PredicateStatusConverter<T> implements Converter<T, Status> {

	private final Predicate<T> predicate;

	public PredicateStatusConverter(final Predicate<T> predicate){
		Assert.isNull(predicate, "Predicate cloud not be null.");
		this.predicate = predicate;
	}

	@Override
	public Status convert(final T source){
		return StatusUtils.valueOf(predicate.test(source));
	}

}

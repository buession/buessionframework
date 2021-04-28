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
package com.buession.redis.core;

import com.buession.core.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

/**
 * @author Yong.Teng
 */
public abstract class FutureResult<T, SV, TV> implements Supplier<SV> {

	private T resultHolder;

	private Converter<SV, TV> converter;

	@SuppressWarnings("unchecked")
	public FutureResult(final T resultHolder){
		this(resultHolder, val->(TV) val);
	}

	@SuppressWarnings("unchecked")
	public FutureResult(final T resultHolder, final Converter<SV, TV> converter){
		this.resultHolder = resultHolder;
		this.converter = converter != null ? converter : val->(TV) val;
	}

	public T getResultHolder(){
		return resultHolder;
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public TV convert(@Nullable SV result){
		return result == null ? null : converter.convert(result);
	}

}

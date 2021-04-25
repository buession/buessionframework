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
package com.buession.redis.core.convert;

import com.buession.core.converter.Converter;
import com.buession.redis.core.ScanResult;

/**
 * {@link redis.clients.jedis.ScanResult}&lt;S&gt; 转换为 {@link ScanResult}&lt;T&gt;
 *
 * @param <S>
 * 		原始类型，{@link redis.clients.jedis.ScanResult} 泛型参数
 * @param <T>
 * 		目标类型，{@link ScanResult} 泛型参数
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public interface ScanResultExposeConverter<S, T> extends Converter<redis.clients.jedis.ScanResult<S>, ScanResult<T>> {

}

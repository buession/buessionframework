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
package com.buession.redis.core.convert.jedis;

import com.buession.core.converter.ListConverter;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.convert.ScanResultJedisConverter;

import java.util.List;

/**
 * {@link java.util.List}&lt;Tuple&gt; 转换为 {@link redis.clients.jedis.Tuple}
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
final public class ListTupleScanResultJedisConverter implements ScanResultJedisConverter<List<Tuple>,
		redis.clients.jedis.Tuple> {

	@Override
	public redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple> convert(final ScanResult<List<Tuple>> source){
		final ListConverter<Tuple, redis.clients.jedis.Tuple> converter =
				new ListConverter<>((item)->new redis.clients.jedis.Tuple(item.getBinaryElement(), item.getScore()));
		return new redis.clients.jedis.ScanResult<>(source.getCursor(), converter.convert(source.getResults()));
	}

}

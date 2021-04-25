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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.Converter;
import com.buession.core.utils.NumberUtils;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.SortingParams;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Yong.Teng
 * @since 1.2.1
 */
public class SortArgumentExposeConverter implements Converter<SortingParams, KeyCommands.SortArgument> {

	@Override
	public KeyCommands.SortArgument convert(final SortingParams source){
		final KeyCommands.SortArgument.Builder builder = KeyCommands.SortArgument.Builder.create();

		Collection<byte[]> collections = source.getParams();
		Iterator<byte[]> iterator = collections.iterator();

		while(iterator.hasNext()){
			byte[] v = iterator.next();

			if(v == Protocol.Keyword.BY.getRaw()){
				v = iterator.next();
				builder.by(SafeEncoder.encode(v));
			}else if(v == Protocol.Keyword.ASC.getRaw()){
				builder.asc();
			}else if(v == Protocol.Keyword.DESC.getRaw()){
				builder.desc();
			}else if(v == Protocol.Keyword.LIMIT.getRaw()){
				byte[] start = iterator.next();
				byte[] end = iterator.next();

				builder.limit(NumberUtils.bytes2long(start), NumberUtils.bytes2long(end));
			}else if(v == Protocol.Keyword.ALPHA.getRaw()){
				builder.alpha();
			}
		}

		return builder.build();
	}

}

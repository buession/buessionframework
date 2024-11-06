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
package com.buession.redis.core.internal.convert.lettuce.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.Command;
import io.lettuce.core.protocol.CommandType;
import org.springframework.lang.Nullable;

/**
 * {@link Command} 转换为 Lettuce {@link CommandType}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class CommandConverter implements Converter<Command, CommandType> {

	@Nullable
	@Override
	public CommandType convert(final Command source) {
		if(source == null){
			return null;
		}else{
			switch(source){
				case BITCOUNT:
					return CommandType.BITCOUNT;
				case BITFIELD:
					return CommandType.BITFIELD;
				case BITFIELD_RO:
					break;
				case BITOP:
					return CommandType.BITOP;
				case BITPOS:
					return CommandType.BITPOS;
				case GETBIT:
					return CommandType.GETBIT;
				case SETBIT:
					return CommandType.SETBIT;
				case CLUSTER_MY_ID:
					return CommandType.CLUSTER;

			}
		}

		return null;
	}

}

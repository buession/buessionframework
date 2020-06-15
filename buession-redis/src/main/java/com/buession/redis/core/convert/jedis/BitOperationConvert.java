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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert.jedis;

import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.BitOP;

/**
 * @author Yong.Teng
 */
public class BitOperationConvert implements Convert<StringCommands.BitOperation, BitOP> {

	@Override
	public BitOP convert(final StringCommands.BitOperation source){
		switch(source){
			case AND:
				return BitOP.AND;
			case OR:
				return BitOP.OR;
			case NOT:
				return BitOP.NOT;
			case XOR:
				return BitOP.XOR;
			default:
				return null;
		}
	}

	@Override
	public StringCommands.BitOperation deconvert(final BitOP target){
		switch(target){
			case AND:
				return StringCommands.BitOperation.AND;
			case OR:
				return StringCommands.BitOperation.OR;
			case NOT:
				return StringCommands.BitOperation.NOT;
			case XOR:
				return StringCommands.BitOperation.XOR;
			default:
				return null;
		}
	}

}

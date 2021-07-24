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
package com.buession.redis.utils;

import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.KeyValueParser;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.core.Client;
import redis.clients.jedis.Protocol;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ClientUtil {

	private ClientUtil(){
	}

	public static List<Client> parse(final String str){
		if(Validate.hasText(str) == false){
			return null;
		}

		String[] clients = str.split("[\\r\\n]");
		List<Client> result = new ArrayList<>(clients.length);

		if(Validate.isNotEmpty(clients)){
			for(String s : clients){
				Client client = doParse(s);

				if(client != null){
					result.add(client);
				}
			}
		}

		return result;
	}

	private static Client doParse(final String str){
		String[] properties = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, " ");

		if(Validate.isEmpty(properties)){
			return null;
		}

		Client client = new Client();
		KeyValueParser keyValueParser;

		for(String property : properties){
			keyValueParser = new KeyValueParser(property, '=');

			if("id".equals(keyValueParser.getKey())){
				client.setId(keyValueParser.getIntValue());
			}else if("addr".equals(keyValueParser.getKey())){
				int ci = keyValueParser.getValue().indexOf(':');

				client.setAddr(keyValueParser.getValue());
				client.setHost(keyValueParser.getValue().substring(0, ci));
				client.setPort(Integer.parseInt(keyValueParser.getValue().substring(ci + 1)));
			}else if("name".equals(keyValueParser.getKey())){
				client.setName(keyValueParser.getValue());
			}else if("fd".equals(keyValueParser.getKey())){
				client.setFd(keyValueParser.getIntValue());
			}else if("age".equals(keyValueParser.getKey())){
				client.setAge(keyValueParser.getIntValue());
			}else if("idle".equals(keyValueParser.getKey())){
				client.setIdle(keyValueParser.getIntValue());
			}else if("flags".equals(keyValueParser.getKey())){
				String[] flagsArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(keyValueParser.getValue(), ",");

				if(flagsArr != null){
					Set<Client.Flag> flags = new LinkedHashSet<>(flagsArr.length);

					for(String s : flagsArr){
						Client.Flag flag = EnumUtils.valueOf(Client.Flag.class, s);

						if(flag != null){
							flags.add(flag);
						}
					}

					client.setFlags(flags);
				}
			}else if("db".equals(keyValueParser.getKey())){
				client.setDb(keyValueParser.getIntValue());
			}else if("sub".equals(keyValueParser.getKey())){
				client.setSub(keyValueParser.getIntValue());
			}else if("psub".equals(keyValueParser.getKey())){
				client.setPsub(keyValueParser.getIntValue());
			}else if("multi".equals(keyValueParser.getKey())){
				client.setMulti(keyValueParser.getIntValue());
			}else if("qbuf".equals(keyValueParser.getKey())){
				client.setQBuf(keyValueParser.getIntValue());
			}else if("qbuf-free".equals(keyValueParser.getKey())){
				client.setQBufFree(keyValueParser.getIntValue());
			}else if("obl".equals(keyValueParser.getKey())){
				client.setObl(keyValueParser.getIntValue());
			}else if("oll".equals(keyValueParser.getKey())){
				client.setOll(keyValueParser.getIntValue());
			}else if("omem".equals(keyValueParser.getKey())){
				client.setOmem(keyValueParser.getIntValue());
			}else if("events".equals(keyValueParser.getKey())){
				client.setEvents(keyValueParser.getEnumValue(Client.Event.class));
			}else if("cmd".equals(keyValueParser.getKey())){
				client.setCmd(keyValueParser.getEnumValue(Protocol.Command.class));
			}
		}

		return client;
	}

}

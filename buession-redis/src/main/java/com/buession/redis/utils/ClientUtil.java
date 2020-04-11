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
package com.buession.redis.utils;

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

	public final static List<Client> parse(final String str){
		if(Validate.hasText(str) == false){
			return null;
		}

		String[] clients = StringUtils.split(str, "[\\r\\n]");
		List<Client> result = new ArrayList<>(clients.length);

		if(Validate.isEmpty(clients) == false){
			for(String s : clients){
				Client client = doParse(s);

				if(client != null){
					result.add(client);
				}
			}
		}

		return result;
	}

	private final static Client doParse(final String str){
		String[] properties = StringUtils.split(str, " ");

		if(Validate.isEmpty(properties)){
			return null;
		}

		Client client = new Client();

		for(String property : properties){
			int ei = property.indexOf('=');
			String name = property.substring(0, ei);
			String value = property.length() == ei + 1 ? null : property.substring(ei + 1);

			if("id".equals(name)){
				client.setId(Integer.parseInt(value));
			}else if("addr".equals(name)){
				int ci = value.indexOf(':');

				client.setAddr(value);
				client.setHost(value.substring(0, ci));
				client.setPort(Integer.parseInt(value.substring(ci + 1)));
			}else if("name".equals(name)){
				client.setName(value);
			}else if("fd".equals(name)){
				client.setFd(Integer.parseInt(value));
			}else if("age".equals(name)){
				client.setAge(Integer.parseInt(value));
			}else if("idle".equals(name)){
				client.setIdle(Integer.parseInt(value));
			}else if("flags".equals(name)){
				String[] flagsArr = StringUtils.split(value, ',');

				if(flagsArr != null){
					Set<Client.Flag> flags = new LinkedHashSet<>(flagsArr.length);

					for(String s : flagsArr){
						try{
							flags.add(Enum.valueOf(Client.Flag.class, s));
						}catch(IllegalArgumentException e){

						}
					}

					client.setFlags(flags);
				}
			}else if("db".equals(name)){
				client.setDb(Integer.parseInt(value));
			}else if("sub".equals(name)){
				client.setSub(Integer.parseInt(value));
			}else if("psub".equals(name)){
				client.setPsub(Integer.parseInt(value));
			}else if("multi".equals(name)){
				client.setMulti(Integer.parseInt(value));
			}else if("qbuf".equals(name)){
				client.setQBuf(Integer.parseInt(value));
			}else if("qbuf-free".equals(name)){
				client.setQBufFree(Integer.parseInt(value));
			}else if("obl".equals(name)){
				client.setObl(Integer.parseInt(value));
			}else if("oll".equals(name)){
				client.setOll(Integer.parseInt(value));
			}else if("omem".equals(name)){
				client.setOmem(Integer.parseInt(value));
			}else if("events".equals(name)){
				try{
					client.setEvents(Enum.valueOf(Client.Event.class, value.toUpperCase()));
				}catch(IllegalArgumentException e){

				}
			}else if("cmd".equals(name)){
				try{
					client.setCmd(Enum.valueOf(Protocol.Command.class, value.toUpperCase()));
				}catch(IllegalArgumentException e){

				}
			}
		}

		return client;
	}

}

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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.CommandInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.RedisCommandGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <code>COMMAND INFO</code> 命令结果转换
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class CommandInfoConverter implements Converter<Object, CommandInfo> {

	@SuppressWarnings("unchecked")
	@Override
	public CommandInfo convert(final Object source) {
		if(source instanceof List){
			final List<Object> tmp = (List<Object>) source;
			int sourceSize = tmp.size();
			String name = tmp.get(0).toString();
			RedisCommand redisCommand = null;
			RedisCommandGroup group = null;
			Integer arity = null;
			Set<CommandInfo.Flag> flags = null;
			Integer firstKey = null;
			Integer lastKey = null;
			Integer step = null;
			Set<AclCategory> aclCategories = null;
			CommandInfo.Tips tips = null;
			List<CommandInfo.KeySpecification> keySpecifications = null;
			List<String> subcommands = null;

			for(RedisCommand command : RedisCommand.values()){
				if(StringUtils.equalsIgnoreCase(command.getName(), name)){
					redisCommand = command;
					group = command.getGroup();
					break;
				}
			}

			if(sourceSize >= 2){
				arity = ((Long) tmp.get(1)).intValue();

				if(sourceSize >= 3){
					List<String> flagsTemp = (List<String>) tmp.get(2);

					if(flagsTemp != null){
						flags = flagsTemp.stream().map((v)->Enum.valueOf(CommandInfo.Flag.class, v.toUpperCase()))
								.collect(Collectors.toSet());
					}
				}

				if(sourceSize >= 4){
					firstKey = ((Long) tmp.get(3)).intValue();
					if(sourceSize >= 5){
						lastKey = ((Long) tmp.get(4)).intValue();
						if(sourceSize >= 6){
							step = ((Long) tmp.get(5)).intValue();
							if(sourceSize >= 7){
								List<String> aclCategoriesTemp = (List<String>) tmp.get(6);
								if(aclCategoriesTemp != null){
									aclCategories = aclCategoriesTemp.stream().map((v)->Enum.valueOf(AclCategory.class,
											StringUtils.substr(v.toUpperCase(), 1))).collect(Collectors.toSet());
								}

								if(sourceSize >= 8){
									List<String> tipsTemp = (List<String>) tmp.get(7);
									tips = parseTips(tipsTemp);

									if(sourceSize >= 9){
										List<String> keySpecificationsTemp = tmp.get(8) != null ?
												(List<String>) tmp.get(8) : null;
										if(sourceSize >= 10){
											subcommands = tmp.get(9) != null ? (List<String>) tmp.get(9) : null;
										}
									}
								}
							}
						}
					}
				}
			}

			return new CommandInfo(name, redisCommand, group, arity, flags, firstKey, lastKey, step, aclCategories,
					tips, keySpecifications, subcommands);
		}else{
			return null;
		}
	}

	private static CommandInfo.Tips parseTips(final List<String> tips) {
		if(tips == null){
			return null;
		}

		Boolean nondeterministicOutput = null;
		Boolean nondeterministicOutputOrder = null;
		CommandInfo.Tips.RequestPolicy requestPolicy = null;
		CommandInfo.Tips.ResponsePolicy responsePolicy = null;

		for(String v : tips){
			if("nondeterministic_output".equals(v)){
				nondeterministicOutput = true;
			}else if("nondeterministic_output_order".equals(v)){
				nondeterministicOutputOrder = true;
			}else if(v.startsWith("request_policy:")){
				String s = v.substring("request_policy:".length());
				for(CommandInfo.Tips.RequestPolicy policy : CommandInfo.Tips.RequestPolicy.values()){
					if(StringUtils.equalsIgnoreCase(s, policy.name())){
						requestPolicy = policy;
						break;
					}
				}
			}else if(v.startsWith("response_policy:")){
				String s = v.substring("response_policy:".length());
				for(CommandInfo.Tips.ResponsePolicy policy : CommandInfo.Tips.ResponsePolicy.values()){
					if(StringUtils.equalsIgnoreCase(s, policy.name())){
						responsePolicy = policy;
						break;
					}
				}
			}
		}

		return new CommandInfo.Tips(nondeterministicOutput, nondeterministicOutputOrder, requestPolicy, responsePolicy);
	}

}

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
import com.buession.redis.core.CommandInfo;
import com.buession.redis.core.command.RedisCommandGroup;

import java.util.HashSet;
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

	@Override
	public CommandInfo convert(final Object source) {
		if(source instanceof List){
			final CommandInfoBuilder commandInfoBuilder = new CommandInfoBuilder();
			final List<Object> tmp = (List<Object>) source;

			commandInfoBuilder.setName(tmp.get(0).toString());
			commandInfoBuilder.setArity(((Long) tmp.get(1)).intValue());

			List<String> flagsTemp = (List<String>) tmp.get(2);

			if(flagsTemp != null){
				commandInfoBuilder.setFlags(
						flagsTemp.stream().map((v)->Enum.valueOf(CommandInfo.Flag.class, v.toUpperCase()))
								.collect(Collectors.toSet()));
			}

			commandInfoBuilder.setFirstKey(((Long) tmp.get(3)).intValue());
			commandInfoBuilder.setLastKey(((Long) tmp.get(4)).intValue());
			commandInfoBuilder.setStep(((Long) tmp.get(5)).intValue());
			commandInfoBuilder.setAclCategories(tmp.get(6) != null ? new HashSet<>((List<String>) tmp.get(6)) : null);

			return commandInfoBuilder.build();
		}else{
			return null;
		}
	}

	private final static class CommandInfoBuilder {

		private String name;

		private RedisCommandGroup group;

		private Integer arity;

		private Set<CommandInfo.Flag> flags;

		private Integer firstKey;

		private Integer lastKey;

		private Integer step;

		private Set<String> aclCategories;

		private CommandInfoBuilder() {

		}

		public void setName(String name) {
			this.name = name;
		}

		public void setGroup(RedisCommandGroup group) {
			this.group = group;
		}

		public void setArity(Integer arity) {
			this.arity = arity;
		}

		public void setFlags(Set<CommandInfo.Flag> flags) {
			this.flags = flags;
		}

		public void setFirstKey(Integer firstKey) {
			this.firstKey = firstKey;
		}

		public void setLastKey(Integer lastKey) {
			this.lastKey = lastKey;
		}

		public void setStep(Integer step) {
			this.step = step;
		}

		public void setAclCategories(Set<String> aclCategories) {
			this.aclCategories = aclCategories;
		}

		public CommandInfo build() {
			return null;//new CommandInfo(name, group, arity, flags, firstKey, lastKey, step, aclCategories);
		}

	}

}

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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;
import com.buession.redis.core.internal.convert.lettuce.params.AclCategoryConverter;
import com.buession.redis.core.internal.convert.lettuce.params.CommandConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.AclSetuserArgs;
import io.lettuce.core.protocol.ProtocolKeyword;

/**
 * Lettuce {@link AclSetuserArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceAclSetuserArgs extends AclSetuserArgs {

	private final static CommandConverter commandConverter = new CommandConverter();

	private final static AclCategoryConverter aclCategoryConverter = new AclCategoryConverter();

	/**
	 * 构造函数
	 */
	public LettuceAclSetuserArgs() {
		super();
	}

	/**
	 * 从 {@link AclSetUserArgument} 创建 {@link AclSetuserArgs} 实例
	 *
	 * @param aclSetUserArgument
	 *        {@link AclSetUserArgument}
	 *
	 * @return {@link LettuceAclSetuserArgs} 实例
	 */
	public static LettuceAclSetuserArgs from(final AclSetUserArgument aclSetUserArgument) {
		final LettuceAclSetuserArgs aclSetuserArgs = new LettuceAclSetuserArgs();

		for(AclSetUserArgument.Argument argument : aclSetUserArgument.getArguments()){
			if(argument instanceof AclSetUserArgument.State){
				AclSetUserArgument.State state = (AclSetUserArgument.State) argument;

				if(state == AclSetUserArgument.State.ON){
					aclSetuserArgs.on();
				}else if(state == AclSetUserArgument.State.OFF){
					aclSetuserArgs.off();
				}
			}else if(argument instanceof AclSetUserArgument.KeyPattern){
				aclSetuserArgs.keyPattern(((AclSetUserArgument.KeyPattern) argument).getValue());
			}else if(argument instanceof AclSetUserArgument.AllKeys){
				aclSetuserArgs.allKeys();
			}else if(argument instanceof AclSetUserArgument.ResetKeys){
				aclSetuserArgs.resetKeys();
			}else if(argument instanceof AclSetUserArgument.ChannelPattern){
				aclSetuserArgs.channelPattern(((AclSetUserArgument.ChannelPattern) argument).getValue());
			}else if(argument instanceof AclSetUserArgument.AllChannels){
				aclSetuserArgs.allChannels();
			}else if(argument instanceof AclSetUserArgument.ResetChannels){
				aclSetuserArgs.resetChannels();
			}else if(argument instanceof AclSetUserArgument.AddCommand){
				final AclSetUserArgument.AddCommand addCommand = (AclSetUserArgument.AddCommand) argument;

				aclSetuserArgs.addCommand(
						commandConverter.convert(addCommand.getCommand()), addCommand.getSubCommand() == null ? null
								: new LettuceCommandProtocolKeyword(addCommand.getSubCommand()));
			}else if(argument instanceof AclSetUserArgument.AllCommands){
				aclSetuserArgs.allCommands();
			}else if(argument instanceof AclSetUserArgument.RemoveCommand){
				final AclSetUserArgument.RemoveCommand removeCommand = (AclSetUserArgument.RemoveCommand) argument;

				aclSetuserArgs.removeCommand(
						commandConverter.convert(removeCommand.getCommand()),
						removeCommand.getSubCommand() == null ? null
								: new LettuceCommandProtocolKeyword(removeCommand.getSubCommand()));
			}else if(argument instanceof AclSetUserArgument.NoCommands){
				aclSetuserArgs.noCommands();
			}else if(argument instanceof AclSetUserArgument.AddCategory){
				aclSetuserArgs.addCategory(aclCategoryConverter.convert(
						((AclSetUserArgument.AddCategory) argument).getAclCategory()));
			}else if(argument instanceof AclSetUserArgument.RemoveCategory){
				aclSetuserArgs.removeCategory(aclCategoryConverter.convert(
						((AclSetUserArgument.RemoveCategory) argument).getAclCategory()));
			}else if(argument instanceof AclSetUserArgument.NoPass){
				aclSetuserArgs.nopass();
			}else if(argument instanceof AclSetUserArgument.ResetPass){
				aclSetuserArgs.resetpass();
			}else if(argument instanceof AclSetUserArgument.AddPassword){
				aclSetuserArgs.addPassword(((AclSetUserArgument.AddPassword) argument).getValue());
			}else if(argument instanceof AclSetUserArgument.RemovePassword){
				aclSetuserArgs.removePassword(((AclSetUserArgument.RemovePassword) argument).getValue());
			}else if(argument instanceof AclSetUserArgument.AddHashedPassword){
				aclSetuserArgs.addHashedPassword(((AclSetUserArgument.AddHashedPassword) argument).getValue());
			}else if(argument instanceof AclSetUserArgument.RemoveHashedPassword){
				aclSetuserArgs.removeHashedPassword(((AclSetUserArgument.RemoveHashedPassword) argument).getValue());
			}
		}

		return aclSetuserArgs;
	}

	protected static class LettuceCommandProtocolKeyword implements ProtocolKeyword {

		private final ProtocolCommand command;

		protected LettuceCommandProtocolKeyword(final ProtocolCommand command) {
			this.command = command;
		}

		@Override
		public byte[] getBytes() {
			return SafeEncoder.encode(name());
		}

		@Override
		public String name() {
			return command.getName();
		}

	}

}

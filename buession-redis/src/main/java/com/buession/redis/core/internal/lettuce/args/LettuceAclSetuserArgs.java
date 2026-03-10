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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;
import com.buession.redis.core.internal.convert.lettuce.params.AclCategoryConverter;
import io.lettuce.core.AclSetuserArgs;
import io.lettuce.core.BitFieldArgs;
import io.lettuce.core.FlushMode;
import io.lettuce.core.FunctionRestoreMode;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.StreamDeletionPolicy;
import io.lettuce.core.UnblockType;
import io.lettuce.core.protocol.CommandKeyword;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.search.arguments.PostProcessingArgs;

/**
 * Lettuce {@link AclSetuserArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceAclSetuserArgs extends AclSetuserArgs {

	/**
	 * 构造函数
	 */
	public LettuceAclSetuserArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param aclSetUserArgument
	 *        {@link AclSetUserArgument}
	 */
	public LettuceAclSetuserArgs(final AclSetUserArgument aclSetUserArgument) {
		super();

		if(aclSetUserArgument != null){
			for(AclSetUserArgument.Argument argument : aclSetUserArgument.getArguments()){
				if(argument instanceof AclSetUserArgument.State state){
					if(state == AclSetUserArgument.State.ON){
						on();
					}else if(state == AclSetUserArgument.State.OFF){
						off();
					}
				}else if(argument instanceof AclSetUserArgument.KeyPattern){
					keyPattern(argument.toString());
				}else if(argument instanceof AclSetUserArgument.ResetKeys){
					resetKeys();
				}else if(argument instanceof AclSetUserArgument.AllKeys){
					allKeys();
				}else if(argument instanceof AclSetUserArgument.ChannelPattern){
					channelPattern(argument.toString());
				}else if(argument instanceof AclSetUserArgument.AllChannels){
					allChannels();
				}else if(argument instanceof AclSetUserArgument.ResetChannels){
					resetChannels();
				}else if(argument instanceof AclSetUserArgument.AddCommand){
					addCommand(commandType(((AclSetUserArgument.AddCommand) argument).getCommand()),
							protocolKeyword(((AclSetUserArgument.AddCommand) argument).getSubCommand()));
				}else if(argument instanceof AclSetUserArgument.AllCommands){
					allCommands();
				}else if(argument instanceof AclSetUserArgument.RemoveCommand){
					removeCommand(commandType(((AclSetUserArgument.RemoveCommand) argument).getCommand()),
							protocolKeyword(((AclSetUserArgument.RemoveCommand) argument).getSubCommand()));
				}else if(argument instanceof AclSetUserArgument.NoCommands){
					noCommands();
				}else if(argument instanceof AclSetUserArgument.AddCategory){
					final AclCategoryConverter aclCategoryConverter = new AclCategoryConverter();
					addCategory(
							aclCategoryConverter.convert(((AclSetUserArgument.AddCategory) argument).getAclCategory()));
				}else if(argument instanceof AclSetUserArgument.RemoveCategory){
					final AclCategoryConverter aclCategoryConverter = new AclCategoryConverter();
					removeCategory(aclCategoryConverter.convert(
							((AclSetUserArgument.RemoveCategory) argument).getAclCategory()));
				}else if(argument instanceof AclSetUserArgument.NoPass){
					nopass();
				}else if(argument instanceof AclSetUserArgument.ResetPass){
					resetpass();
				}else if(argument instanceof AclSetUserArgument.AddPassword){
					addPassword(argument.toString());
				}else if(argument instanceof AclSetUserArgument.RemovePassword){
					removePassword(argument.toString());
				}else if(argument instanceof AclSetUserArgument.AddHashedPassword){
					addHashedPassword(argument.toString());
				}else if(argument instanceof AclSetUserArgument.RemoveHashedPassword){
					removeHashedPassword(argument.toString());
				}else if(argument instanceof AclSetUserArgument.Reset){
					reset();
				}
			}
		}
	}

	private static CommandType commandType(final Command command) {
		return Enum.valueOf(CommandType.class, command.getName());
	}

	private static ProtocolKeyword protocolKeyword(final SubCommand subCommand) {
		try{
			return Enum.valueOf(CommandKeyword.class, subCommand.getName());
		}catch(IllegalArgumentException e){
			try{
				return Enum.valueOf(FlushMode.class, subCommand.getName());
			}catch(IllegalArgumentException e1){
				try{
					return Enum.valueOf(FunctionRestoreMode.class, subCommand.getName());
				}catch(IllegalArgumentException e2){
					try{
						return Enum.valueOf(BitFieldArgs.OverflowType.class, subCommand.getName());
					}catch(IllegalArgumentException e3){
						try{
							return Enum.valueOf(PostProcessingArgs.ReduceFunction.class, subCommand.getName());
						}catch(IllegalArgumentException e4){
							try{
								return Enum.valueOf(StreamDeletionPolicy.class, subCommand.getName());
							}catch(IllegalArgumentException e5){
								try{
									return Enum.valueOf(UnblockType.class, subCommand.getName());
								}catch(IllegalArgumentException e6){
									try{
										return Enum.valueOf(GeoArgs.Unit.class, subCommand.getName());
									}catch(IllegalArgumentException e7){
										return null;
									}
								}
							}
						}
					}
				}
			}
		}
	}

}

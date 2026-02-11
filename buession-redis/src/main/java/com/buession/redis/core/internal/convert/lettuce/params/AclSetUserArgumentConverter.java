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
package com.buession.redis.core.internal.convert.lettuce.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;
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
import org.springframework.lang.Nullable;

/**
 * {@link AclSetUserArgument} 转换为 lettuce {@link AclSetuserArgs}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class AclSetUserArgumentConverter implements Converter<AclSetUserArgument, AclSetuserArgs> {

	@Nullable
	@Override
	public AclSetuserArgs convert(final AclSetUserArgument source) {
		if(source == null){
			return null;
		}

		final AclSetuserArgs aclSetuserArgs = new AclSetuserArgs();

		for(AclSetUserArgument.Argument argument : source.getArguments()){
			if(argument instanceof AclSetUserArgument.State state){
				if(state == AclSetUserArgument.State.ON){
					aclSetuserArgs.on();
				}else if(state == AclSetUserArgument.State.OFF){
					aclSetuserArgs.off();
				}
			}else if(argument instanceof AclSetUserArgument.KeyPattern){
				aclSetuserArgs.keyPattern(argument.toString());
			}else if(argument instanceof AclSetUserArgument.ResetKeys){
				aclSetuserArgs.resetKeys();
			}else if(argument instanceof AclSetUserArgument.AllKeys){
				aclSetuserArgs.allKeys();
			}else if(argument instanceof AclSetUserArgument.ChannelPattern){
				aclSetuserArgs.channelPattern(argument.toString());
			}else if(argument instanceof AclSetUserArgument.AllChannels){
				aclSetuserArgs.allChannels();
			}else if(argument instanceof AclSetUserArgument.ResetChannels){
				aclSetuserArgs.resetChannels();
			}else if(argument instanceof AclSetUserArgument.AddCommand){
				aclSetuserArgs.addCommand(commandType(((AclSetUserArgument.AddCommand) argument).getCommand())),
						protocolKeyword(((AclSetUserArgument.AddCommand) argument).getSubCommand()));
			}else if(argument instanceof AclSetUserArgument.AllCommands){
				aclSetuserArgs.allCommands();
			}else if(argument instanceof AclSetUserArgument.RemoveCommand){
				aclSetuserArgs.removeCommand(commandType(((AclSetUserArgument.RemoveCommand) argument).getCommand()),
						protocolKeyword(((AclSetUserArgument.RemoveCommand) argument).getSubCommand()));
			}else if(argument instanceof AclSetUserArgument.NoCommands){
				aclSetuserArgs.noCommands();
			}else if(argument instanceof AclSetUserArgument.AddCategory){
				final AclCategoryConverter aclCategoryConverter = new AclCategoryConverter();
				aclSetuserArgs.addCategory(
						aclCategoryConverter.convert(((AclSetUserArgument.AddCategory) argument).getAclCategory()));
			}else if(argument instanceof AclSetUserArgument.RemoveCategory){
				final AclCategoryConverter aclCategoryConverter = new AclCategoryConverter();
				aclSetuserArgs.removeCategory(
						aclCategoryConverter.convert(((AclSetUserArgument.RemoveCategory) argument).getAclCategory()));
			}else if(argument instanceof AclSetUserArgument.NoPass){
				aclSetuserArgs.nopass();
			}else if(argument instanceof AclSetUserArgument.ResetPass){
				aclSetuserArgs.resetpass();
			}else if(argument instanceof AclSetUserArgument.AddPassword){
				aclSetuserArgs.addPassword(argument.toString());
			}else if(argument instanceof AclSetUserArgument.RemovePassword){
				aclSetuserArgs.removePassword(argument.toString());
			}else if(argument instanceof AclSetUserArgument.AddHashedPassword){
				aclSetuserArgs.addHashedPassword(argument.toString());
			}else if(argument instanceof AclSetUserArgument.RemoveHashedPassword){
				aclSetuserArgs.removeHashedPassword(argument.toString());
			}else if(argument instanceof AclSetUserArgument.Reset){
				aclSetuserArgs.reset();
			}
		}

		return aclSetuserArgs;
	}

	private CommandType commandType(final Command command) {
		return Enum.valueOf(CommandType.class, command.getName());
	}

	private ProtocolKeyword protocolKeyword(final SubCommand subCommand) {
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

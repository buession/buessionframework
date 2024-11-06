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
package com.buession.redis.core.command.args;

import com.buession.core.utils.StringUtils;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.utils.SafeEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code ACL SETUSER} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class AclSetUserArgument implements ArrayArgument<String> {

	/**
	 * 参数列表
	 */
	private final List<Argument> arguments = new ArrayList<>();

	/**
	 * 构造函数
	 */
	public AclSetUserArgument() {
	}

	/**
	 * Set user active.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument on() {
		return state(State.ON);
	}

	/**
	 * Set user inactive.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument off() {
		return state(State.OFF);
	}

	/**
	 * Set user active or inactive.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument state(final State state) {
		if(state != null){
			this.arguments.add(state);
		}

		return this;
	}

	/**
	 * Adds accessible key pattern.
	 *
	 * @param keyPattern
	 * 		accessible key pattern
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument keyPattern(final String keyPattern) {
		this.arguments.add(new KeyPattern(keyPattern));
		return this;
	}

	/**
	 * Allows the user to access all the keys.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument allKeys() {
		this.arguments.add(new AllKeys());
		return this;
	}

	/**
	 * Removes all the key patterns from the list of key patterns the user can access.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument resetKeys() {
		this.arguments.add(new ResetKeys());
		return this;
	}

	/**
	 * Adds accessible channel pattern.
	 *
	 * @param channelPattern
	 * 		accessible channel pattern
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument channelPattern(final String channelPattern) {
		this.arguments.add(new ChannelPattern(channelPattern));
		return this;
	}

	/**
	 * Allows the user to access all the Pub/Sub channels.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument allChannels() {
		this.arguments.add(new AllChannels());
		return this;
	}

	/**
	 * Removes all channel patterns from the list of Pub/Sub channel patterns the user can access.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument resetChannels() {
		this.arguments.add(new ResetChannels());
		return this;
	}

	/**
	 * Adds this command to the list of the commands the user can call.
	 *
	 * @param command
	 * 		accessible command
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument addCommand(final Command command) {
		return addCommand(command, null);
	}

	/**
	 * Adds this command to the list of the commands the user can call.
	 *
	 * @param command
	 * 		accessible command
	 * @param subCommand
	 * 		accessible subcommand
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument addCommand(final Command command, final SubCommand subCommand) {
		this.arguments.add(new AddCommand(command, subCommand));
		return this;
	}

	/**
	 * Adds all the commands there are in the server.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument allCommands() {
		this.arguments.add(new AllCommands());
		return this;
	}

	/**
	 * Removes this command to the list of the commands the user can call.
	 *
	 * @param command
	 * 		inaccessible command
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument removeCommand(final Command command) {
		return removeCommand(command, null);
	}

	/**
	 * Removes the specified command to the list of the commands the user can execute.
	 *
	 * @param command
	 * 		inaccessible command
	 * @param subCommand
	 * 		inaccessible subcommand
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument removeCommand(final Command command, final SubCommand subCommand) {
		this.arguments.add(new RemoveCommand(command, subCommand));
		return this;
	}

	/**
	 * Removes all the commands the user can execute.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument noCommands() {
		this.arguments.add(new NoCommands());
		return this;
	}

	/**
	 * Adds all the commands in the specified category to the list of commands the user is able to execute.
	 *
	 * @param category
	 * 		specified category
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument addCategory(final AclCategory category) {
		this.arguments.add(new AddCategory(category));
		return this;
	}

	/**
	 * Removes all the commands in the specified category to the list of commands the user is able to execute.
	 *
	 * @param category
	 * 		specified category
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument removeCategory(final AclCategory category) {
		this.arguments.add(new RemoveCategory(category));
		return this;
	}

	/**
	 * Sets the user as a "no password".
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument noPass() {
		this.arguments.add(new NoPass());
		return this;
	}

	/**
	 * Flushes the list of allowed passwords and removes the "no password" status. After resetting the password there is no way
	 * to authenticate as the user without adding some password (or setting it as {@link #noPass()} later).
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument resetPass() {
		this.arguments.add(new ResetPass());
		return this;
	}

	/**
	 * Adds the specified clear text password as an hashed password in the list of the users passwords.
	 *
	 * @param password
	 * 		clear text password
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument addPassword(final String password) {
		this.arguments.add(new AddPassword(password));
		return this;
	}

	/**
	 * Removes the specified clear text password as an hashed password in the list of the users passwords.
	 *
	 * @param password
	 * 		clear text password
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument removePassword(final String password) {
		this.arguments.add(new RemovePassword(password));
		return this;
	}

	/**
	 * Adds the specified hashed password to the list of user passwords.
	 *
	 * @param hashedPassword
	 * 		hashed password
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument addHashedPassword(final String hashedPassword) {
		this.arguments.add(new AddHashedPassword(hashedPassword));
		return this;
	}

	/**
	 * Removes the specified hashed password to the list of user passwords.
	 *
	 * @param hashedPassword
	 * 		hashed password
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument removeHashedPassword(final String hashedPassword) {
		this.arguments.add(new RemoveHashedPassword(hashedPassword));
		return this;
	}

	/**
	 * Removes any capability from the user.
	 *
	 * @return {@code this}
	 */
	public AclSetUserArgument reset() {
		this.arguments.add(new Reset());
		return this;
	}

	public List<Argument> getArguments() {
		return arguments;
	}

	@Override
	public String[] toArray() {
		final String[] result = new String[arguments.size()];
		Argument argument;

		for(int i = 0, l = arguments.size(); i < l; i++){
			argument = arguments.get(i);

			if(argument instanceof State){
				result[i] = ((State) argument).name();
			}else{
				result[i] = argument.toString();
			}
		}

		return result;
	}

	@Override
	public byte[][] toBinaryArray() {
		final byte[][] result = new byte[arguments.size()][];
		Argument argument;

		for(int i = 0, l = arguments.size(); i < l; i++){
			argument = arguments.get(i);

			if(argument instanceof State){
				result[i] = SafeEncoder.encode(((State) argument).name());
			}else{
				result[i] = SafeEncoder.encode(argument.toString());
			}
		}

		return result;
	}

	@Override
	public String toString() {
		return StringUtils.join(arguments, " ");
	}

	public interface Argument {

	}

	public enum State implements Argument {
		ON,

		OFF
	}

	public static abstract class StringArgument implements Argument {

		private final String value;

		public StringArgument(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

	public static abstract class ProtocolCommandArgument implements Argument {

		private final ProtocolCommand command;

		public ProtocolCommandArgument(final ProtocolCommand command) {
			this.command = command;
		}

		public ProtocolCommand getCommand() {
			return command;
		}

		@Override
		public String toString() {
			return getCommand().getName();
		}

	}

	public static abstract class CommandArgument implements Argument {

		private final Command command;

		private final SubCommand subCommand;

		public CommandArgument(final Command command) {
			this(command, null);
		}

		public CommandArgument(final Command command, final SubCommand subCommand) {
			this.command = command;
			this.subCommand = subCommand;
		}

		public Command getCommand() {
			return command;
		}

		public SubCommand getSubCommand() {
			return subCommand;
		}

		@Override
		public String toString() {
			return subCommand == null ? command.getName() : command.getName() + "|" + subCommand;
		}

	}

	public static abstract class AclCategoryArgument implements Argument {

		private final AclCategory aclCategory;

		AclCategoryArgument(final AclCategory aclCategory) {
			this.aclCategory = aclCategory;
		}

		public AclCategory getAclCategory() {
			return aclCategory;
		}

		@Override
		public String toString() {
			return aclCategory.name();
		}

	}

	public final static class KeyPattern extends StringArgument {

		KeyPattern(final String keyPattern) {
			super(keyPattern);
		}

		@Override
		public String toString() {
			return "~" + super.toString();
		}

	}

	public final static class ResetKeys extends StringArgument {

		ResetKeys() {
			super("resetkeys");
		}

	}

	public final static class AllKeys extends StringArgument {

		AllKeys() {
			super("allkeys");
		}

	}

	public final static class ChannelPattern extends StringArgument {

		ChannelPattern(final String channelPattern) {
			super(channelPattern);
		}

		@Override
		public String toString() {
			return "&" + super.toString();
		}

	}

	public final static class AllChannels extends StringArgument {

		AllChannels() {
			super("allchannels");
		}

	}

	public final static class ResetChannels extends StringArgument {

		ResetChannels() {
			super("resetchannels");
		}

	}

	public final static class AddCommand extends CommandArgument {

		AddCommand(final Command command) {
			super(command);
		}

		AddCommand(final Command command, final SubCommand subCommand) {
			super(command, subCommand);
		}

		@Override
		public String toString() {
			return "+" + super.toString();
		}

	}

	public final static class AllCommands extends StringArgument {

		AllCommands() {
			super("allcommands");
		}

	}

	public final static class RemoveCommand extends CommandArgument {

		RemoveCommand(final Command command) {
			super(command);
		}

		RemoveCommand(final Command command, final SubCommand subCommand) {
			super(command, subCommand);
		}

		@Override
		public String toString() {
			return "-" + super.toString();
		}

	}

	public final static class NoCommands extends StringArgument {

		NoCommands() {
			super("nocommands");
		}

	}

	public final static class AddCategory extends AclCategoryArgument {

		AddCategory(final AclCategory category) {
			super(category);
		}

		@Override
		public String toString() {
			return "+@" + super.toString();
		}

	}

	public final static class RemoveCategory extends AclCategoryArgument {

		RemoveCategory(final AclCategory category) {
			super(category);
		}

		@Override
		public String toString() {
			return "-@" + super.toString();
		}

	}

	public final static class NoPass extends StringArgument {

		NoPass() {
			super("nopass");
		}

	}

	public final static class ResetPass extends StringArgument {

		ResetPass() {
			super("resetpass");
		}

	}

	public final static class AddPassword extends StringArgument {

		AddPassword(String password) {
			super(password);
		}

		@Override
		public String toString() {
			return ">" + super.toString();
		}

	}

	public final static class RemovePassword extends StringArgument {

		RemovePassword(String password) {
			super(password);
		}

		@Override
		public String toString() {
			return "<" + super.toString();
		}

	}

	public final static class AddHashedPassword extends StringArgument {

		AddHashedPassword(String password) {
			super(password);
		}

		@Override
		public String toString() {
			return "#" + super.toString();
		}

	}

	public final static class RemoveHashedPassword extends StringArgument {

		RemoveHashedPassword(String password) {
			super(password);
		}

		@Override
		public String toString() {
			return "!" + super.toString();
		}

	}

	public final static class Reset extends ProtocolCommandArgument {

		Reset() {
			super(SubCommand.RESET);
		}

	}

}

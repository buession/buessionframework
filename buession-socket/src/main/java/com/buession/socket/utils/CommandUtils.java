/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with the License. You may obtain 
 * a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * =================================================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * +------------------------------------------------------------------------------------------------+
 * | License: http://buession.buession.com.cn/LICENSE 												|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2015 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.socket.utils;

import io.netty.channel.Channel;

import org.kohsuke.args4j.CmdLineParser;

import com.buession.core.Status;
import com.buession.io.Console;
import com.buession.socket.codec.Command;
import com.buession.socket.server.Server;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public class CommandUtils {

	/**
	 * 执行服务命令
	 * 
	 * @param server
	 *            服务
	 * @param cmdLineParser
	 *            Command Line Argument 解析器
	 * @param command
	 *            命令
	 * @param isExit
	 *            是否终止程序
	 */
	public static void execute(final Server<Channel> server, final CmdLineParser cmdLineParser,
			final Command command, final boolean isExit) {
		if (command != null) {
			if (command.equals(Command.START) == true) {
				server.run();
				return;
			} else if (command.equals(Command.RELOAD) == true) {
				server.reload();
			} else if (command.equals(Command.RESTART) == true) {
				server.restart();
			} else if (command.equals(Command.STOP) == true) {
				server.stop();
			} else if (command.equals(Command.STATUS) == true) {
				if (server.status() == Status.SUCCESS) {
					Console.message("%s (pid: %s) is running", server.getName(), server.getPid());
				} else {
					Console.message("%s is stopped", server.getName());
				}
			} else if (command.equals(Command.VERSION) == true) {
				Console.message(server.getVersion());
			} else if (command.equals(Command.HELP) == true) {
				help(server, cmdLineParser);
			}
		}

		if (isExit == true) {
			System.exit(0);
		}
	}

	/**
	 * 帮着命令
	 * 
	 * @param server
	 *            服务
	 * @param cmdLineParser
	 *            Command Line Argument 解析器
	 */
	public static void help(final Server<Channel> server, final CmdLineParser cmdLineParser) {
		Console.message("usage: java -jar %s [ARG ...]", server.getName());
		Console.message("The most commonly used commands are:");

		cmdLineParser.printUsage(System.out);
	}

}
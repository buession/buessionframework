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
package com.buession.socket.server;

import io.netty.channel.Channel;

import org.kohsuke.args4j.CmdLineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buession.socket.codec.Command;
import com.buession.socket.utils.CommandUtils;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public abstract class AbstractBootstrap<T extends Channel> extends ShellCommand implements
		Bootstrap<T> {

	private final static Logger logger = LoggerFactory.getLogger(AbstractBootstrap.class);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(final Server<T> server, final String[] arguments) {
		if (server == null) {
			throw new IllegalArgumentException("Server could not be null.");
		}

		try {
			getCmdLineParser().parseArgument(arguments);

			Command command = getCommand();
			if (command == null) {
				if (isVersion() == true) {
					command = Command.VERSION;
				} else if (isHelp() == true) {
					command = Command.HELP;
				}
			}

			CommandUtils.execute((Server<Channel>) server, getCmdLineParser(), command, true);
		} catch (CmdLineException e) {
			logger.error("Arguments parse failure: {}", e.getMessage());
		}
	}

}
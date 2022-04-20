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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AclUser implements Serializable {

	private final static long serialVersionUID = -2237993389031684508L;

	private final List<String> flags = new ArrayList<>();

	private final List<String> keys = new ArrayList<>();

	private final List<String> passwords = new ArrayList<>();

	private final String commands;

	public AclUser(final List<String> flags, final List<String> keys, final List<String> passwords,
				   final String commands){
		if(flags != null){
			flags.addAll(flags);
		}

		if(keys != null){
			keys.addAll(keys);
		}

		if(passwords != null){
			passwords.addAll(passwords);
		}

		this.commands = commands;
	}

	public List<String> getFlags(){
		return flags;
	}

	public List<String> getKeys(){
		return keys;
	}

	public List<String> getPasswords(){
		return passwords;
	}

	public String getCommands(){
		return commands;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "{", "}")
				.add("flags=" + flags)
				.add("keys=" + keys)
				.add("passwords=" + passwords)
				.add("commands=" + commands)
				.toString();
	}

}

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
package com.buession.redis.core;

import com.buession.core.validator.Validate;
import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AclUser implements Serializable {

	private final static long serialVersionUID = -2237993389031684508L;

	private final String commands;

	private final Map<String, Object> userInfo = new HashMap<>();

	private final List<String> passwords = new ArrayList<>();

	private final List<String> flags = new ArrayList<>();

	private final List<String> keys = new ArrayList<>();

	private final List<String> channels = new ArrayList<>();

	private final List<String> selectors = new ArrayList<>();

	public AclUser(final String commands, final Map<String, Object> userInfo, final List<String> passwords,
				   final List<String> flags, final List<String> keys, final List<String> channels,
				   final List<String> selectors) {
		this.commands = commands;

		if(Validate.isNotEmpty(userInfo)){
			this.userInfo.putAll(userInfo);
		}

		if(passwords != null){
			this.passwords.addAll(passwords);
		}

		if(flags != null){
			this.flags.addAll(flags);
		}

		if(keys != null){
			this.keys.addAll(keys);
		}

		if(channels != null){
			this.channels.addAll(channels);
		}

		if(selectors != null){
			this.selectors.addAll(selectors);
		}
	}

	public String getCommands() {
		return commands;
	}

	public Map<String, Object> getUserInfo() {
		return userInfo;
	}

	public List<String> getPasswords() {
		return passwords;
	}

	public List<String> getFlags() {
		return flags;
	}

	public List<String> getKeys() {
		return keys;
	}

	public List<String> getChannels() {
		return channels;
	}

	public List<String> getSelectors() {
		return selectors;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("commands", commands)
				.add("userInfo", userInfo)
				.add("passwords", passwords)
				.add("flags", flags)
				.add("keys", keys)
				.add("channels", channels)
				.add("selectors", selectors)
				.build();
	}

}

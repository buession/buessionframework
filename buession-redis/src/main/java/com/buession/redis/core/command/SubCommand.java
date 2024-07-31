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
package com.buession.redis.core.command;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.utils.SafeEncoder;

/**
 * Redis 协议命令子命令
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public enum SubCommand implements ProtocolCommand {
	/**
	 * Common command start
	 **/
	GET("r"),

	INFO("r"),

	LIST("r"),

	LOAD("r"),

	NODES("rw"),

	RESET("w"),

	SAVE("w"),

	SLAVES("r"),
	/**
	 * Common command end
	 **/

	/**
	 * Cluster command start
	 **/
	CLUSTER_ADDSLOTS("w"),

	CLUSTER_ADDSLOTSRANGE("w"),

	CLUSTER_BUMPEPOCH("r"),

	CLUSTER_COUNTFAILUREREPORTS("rw"),

	CLUSTER_COUNTKEYSINSLOT("r"),

	CLUSTER_DELSLOTS("w"),

	CLUSTER_DELSLOTSRANGE("w"),

	CLUSTER_FAILOVER("rw"),

	CLUSTER_FLUSHSLOTS("w"),

	CLUSTER_FORGET("w"),

	CLUSTER_GETKEYSINSLOT("r"),

	CLUSTER_KEYSLOT("r"),

	CLUSTER_LINKS("r"),

	CLUSTER_MEET("w"),

	CLUSTER_MYID("r"),

	CLUSTER_MYSHARDID("r"),

	CLUSTER_REPLICAS("rw"),

	CLUSTER_REPLICATE("rw"),

	CLUSTER_SAVECONFIG("rw"),

	CLUSTER_SETCONFIGEPOCH("rw"),

	CLUSTER_SETSLOT("w"),

	CLUSTER_SHARDS("r"),

	CLUSTER_SLOTS("r"),
	/**
	 * Cluster command end
	 **/

	/**
	 * Client command start
	 **/
	CLIENT_CACHING("r"),

	CLIENT_GETNAME("r"),

	CLIENT_GETREDIR("r"),

	CLIENT_ID("r"),

	CLIENT_INFO("r"),

	CLIENT_KILL("w"),

	CLIENT_LIST("r"),

	CLIENT_NO_EVICT("r"),

	CLIENT_NO_TOUCH("r"),

	CLIENT_PAUSE("w"),

	CLIENT_REPLY("w"),

	CLIENT_SETINFO("w"),

	CLIENT_SETNAME("w"),

	CLIENT_TRACKING("r"),

	CLIENT_TRACKINGINFO("r"),

	CLIENT_UNBLOCK("w"),

	CLIENT_UNPAUSE("w"),
	/**
	 * Client command end
	 **/

	/**
	 * Key command start
	 **/
	OBJECT_ENCODING("r"),

	OBJECT_REFQ("r"),

	OBJECT_IDLETIME("r"),

	OBJECT_REFCOUNT("r"),
	/**
	 * Key command end
	 **/

	/**
	 * ACL command start
	 **/
	ACL_CAT("r"),

	ACL_DELUSER("w"),

	ACL_DRYRUN("w"),

	ACL_GENPASS("r"),

	ACL_GETUSER("r"),

	ACL_LOG("rw"),

	ACL_SETUSER("w"),

	ACL_USERS("r"),

	ACL_WHOAMI("r"),
	/**
	 * ACL command end
	 **/
	;

	private final String name;

	private final byte[] raw;

	/**
	 * 是否为读操作命令
	 */
	private final boolean read;

	/**
	 * 是否为写操作命令
	 */
	private final boolean write;

	SubCommand(final String mode) {
		String[] names = StringUtils.split(name(), "_", 2);

		name = names.length > 1 ? names[1] : name();

		raw = SafeEncoder.encode(name);
		if(Validate.hasText(mode)){
			String modeLower = mode.toLowerCase();
			this.read = modeLower.indexOf('r') > -1;
			this.write = modeLower.indexOf('w') > -1;
		}else{
			this.read = true;
			this.write = false;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public byte[] getRaw() {
		return raw;
	}

	@Override
	public boolean isRead() {
		return read;
	}

	@Override
	public boolean isWrite() {
		return write;
	}

	@Override
	public String toString() {
		return getName();
	}

}

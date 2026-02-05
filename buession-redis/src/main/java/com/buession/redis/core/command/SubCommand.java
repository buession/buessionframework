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
package com.buession.redis.core.command;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.utils.SafeEncoder;

/**
 * Redis 子命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public enum SubCommand implements ProtocolCommand {
	/**
	 * Cluster command start
	 **/
	CLUSTER_ADDSLOTS("w"),

	CLUSTER_ADDSLOTSRANGE("w"),

	CLUSTER_BUMPEPOCH("r"),

	CLUSTER_COUNTFAILUREREPORTS("COUNT-FAILURE-REPORTS", "rw"),

	CLUSTER_COUNTKEYSINSLOT("r"),

	CLUSTER_DELSLOTS("w"),

	CLUSTER_DELSLOTSRANGE("w"),

	CLUSTER_FAILOVER("rw"),

	CLUSTER_FLUSHSLOTS("w"),

	CLUSTER_FORGET("w"),

	CLUSTER_GETKEYSINSLOT("r"),

	CLUSTER_INFO("r"),

	CLUSTER_KEYSLOT("r"),

	CLUSTER_LINKS("r"),

	CLUSTER_MEET("w"),

	CLUSTER_MIGRATION("w"),

	CLUSTER_MYID("r"),

	CLUSTER_MYSHARDID("r"),

	CLUSTER_NODES("r"),

	CLUSTER_REPLICAS("r"),

	CLUSTER_REPLICATE("w"),

	CLUSTER_RESET("w"),

	CLUSTER_SAVECONFIG("w"),

	CLUSTER_SETCONFIGEPOCH("SET-CONFIG-EPOCH", "w"),

	CLUSTER_SETSLOT("w"),

	CLUSTER_SHARDS("r"),

	CLUSTER_SLAVES("r"),

	CLUSTER_SLOT_STATS("SLOT-STATS", "r"),

	CLUSTER_SLOTS("r"),
	/**
	 * Cluster command end
	 **/

	/**
	 * connection command start
	 */
	CLIENT_CACHING("w"),

	CLIENT_GETNAME("r"),

	CLIENT_GETREDIR("r"),

	CLIENT_ID("r"),

	CLIENT_INFO("r"),

	CLIENT_KILL("w"),

	CLIENT_LIST("r"),

	CLIENT_NO_EVICT("NO-EVICT", "r"),

	CLIENT_NO_TOUCH("NO-TOUCH", "r"),

	CLIENT_PAUSE("w"),

	CLIENT_REPLY("w"),

	CLIENT_SETINFO("w"),

	CLIENT_SETNAME("w"),

	CLIENT_TRACKING("w"),

	CLIENT_TRACKINGINFO("r"),

	CLIENT_UNBLOCK("w"),

	CLIENT_UNPAUSE("w"),
	/**
	 * connection command end
	 */

	/**
	 * json command start
	 **/

	JSON_DEBUG_MEMORY("MEMORY", "r"),
	/**
	 * json command end
	 **/
	;

	/**
	 * 命令名称
	 */
	private final String name;

	/**
	 * 命令名称 raw 格式
	 */
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
		this.name = StringUtils.split(name(), '_')[0];
		this.raw = SafeEncoder.encode(name);
		if(Validate.hasText(mode)){
			String modeLower = mode.toLowerCase();
			this.read = modeLower.indexOf('r') > -1;
			this.write = modeLower.indexOf('w') > -1;
		}else{
			this.read = true;
			this.write = false;
		}
	}

	SubCommand(final String name, final String mode) {
		this.name = name;
		this.raw = SafeEncoder.encode(name);
		if(Validate.hasText(mode)){
			String modeLower = mode.toLowerCase();
			this.read = modeLower.indexOf('r') > -1;
			this.write = modeLower.indexOf('w') > -1;
		}else{
			this.read = true;
			this.write = false;
		}
	}

	/**
	 * 返回命令名称
	 *
	 * @return 命令名称
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * 返回是否为读命令
	 *
	 * @return true / false
	 */
	@Override
	public boolean isRead() {
		return read;
	}

	/**
	 * 返回是否为写命令
	 *
	 * @return true / false
	 */
	@Override
	public boolean isWrite() {
		return write;
	}

	/**
	 * 返回命令名称 raw 格式
	 *
	 * @return 命令名称 raw 格式
	 */
	@Override
	public byte[] getRaw() {
		return raw;
	}

	@Override
	public String toString() {
		return name;
	}

}

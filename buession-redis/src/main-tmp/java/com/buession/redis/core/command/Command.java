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

import com.buession.core.validator.Validate;
import com.buession.redis.utils.SafeEncoder;

/**
 * Redis 协议命令
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public enum Command implements ProtocolCommand {


	/**
	 * Cluster command end
	 **/

	/**
	 * Connection command start
	 */

	CLIENT("rw", CommandGroup.CONNECTION, SubCommand.CLIENT_CACHING, SubCommand.CLIENT_GETNAME,
			SubCommand.CLIENT_GETREDIR, SubCommand.CLIENT_ID, SubCommand.CLIENT_INFO, SubCommand.CLIENT_KILL,
			SubCommand.CLIENT_LIST, SubCommand.CLIENT_NO_EVICT, SubCommand.CLIENT_NO_TOUCH, SubCommand.CLIENT_PAUSE,
			SubCommand.CLIENT_REPLY, SubCommand.CLIENT_SETINFO, SubCommand.CLIENT_SETNAME, SubCommand.CLIENT_TRACKING,
			SubCommand.CLIENT_TRACKINGINFO, SubCommand.CLIENT_UNBLOCK, SubCommand.CLIENT_UNPAUSE),


	HELLO("w", CommandGroup.CONNECTION),


	/**
	 * Connection command end
	 */

	/**
	 * Key command start
	 **/

	EXPIRETIME("r", CommandGroup.KEY),


	PEXPIRETIME("r", CommandGroup.KEY),

	/**
	 * Key command end
	 **/


	/**
	 * Hash command start
	 **/

	HEXPIRE("w", CommandGroup.HASH),

	HEXPIREAT("w", CommandGroup.HASH),

	HEXPIRETIME("r", CommandGroup.HASH),

	HPERSIST("w", CommandGroup.HASH),

	HPEXPIRE("w", CommandGroup.HASH),

	HPEXPIREAT("w", CommandGroup.HASH),

	HPEXPIRETIME("r", CommandGroup.HASH),

	HPTTL("r", CommandGroup.HASH),

	HTTL("r", CommandGroup.HASH),

	HVALS("r", CommandGroup.HASH),
	/**
	 * Hash command start
	 **/


	/**
	 * ACL command start
	 **/
	ACL("rw", CommandGroup.ACL, SubCommand.ACL_CAT, SubCommand.ACL_DELUSER, SubCommand.ACL_DRYRUN,
			SubCommand.ACL_GENPASS, SubCommand.ACL_GETUSER, SubCommand.LIST, SubCommand.LOAD, SubCommand.ACL_LOG,
			SubCommand.SAVE, SubCommand.ACL_SETUSER, SubCommand.ACL_USERS, SubCommand.ACL_WHOAMI),


}

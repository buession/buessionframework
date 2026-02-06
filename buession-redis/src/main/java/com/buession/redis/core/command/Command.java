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
 * Redis 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public enum Command implements ProtocolCommand {
	/**
	 * Bloom filter command start
	 */
	BF_ADD(CommandGroup.BLOOM_FILTER, "BF.ADD", "w"),

	BF_CARD(CommandGroup.BLOOM_FILTER, "BF.CARD", "r"),

	BF_EXISTS(CommandGroup.BLOOM_FILTER, "BF.EXISTS", "r"),

	BF_INFO(CommandGroup.BLOOM_FILTER, "BF.INFO", "r"),

	BF_INSERT(CommandGroup.BLOOM_FILTER, "BF.INSERT", "w"),

	BF_LOADCHUNK(CommandGroup.BLOOM_FILTER, "BF.LOADCHUNK", "r"),

	BF_MADD(CommandGroup.BLOOM_FILTER, "BF.MADD", "w"),

	BF_MEXISTS(CommandGroup.BLOOM_FILTER, "BF.MEXISTS", "r"),

	BF_RESERVE(CommandGroup.BLOOM_FILTER, "BF.RESERVE", "w"),

	BF_SCANDUMP(CommandGroup.BLOOM_FILTER, "BF.SCANDUMP", "w"),
	/**
	 * Bloom filter command end
	 */

	/**
	 * BitMap command start
	 */
	BITCOUNT(CommandGroup.BITMAP, "r"),

	BITFIELD(CommandGroup.BITMAP, "r"),

	BITFIELD_RO(CommandGroup.BITMAP, "r"),

	BITOP(CommandGroup.BITMAP, "r"),

	BITPOS(CommandGroup.BITMAP, "r"),

	GETBIT(CommandGroup.BITMAP, "r"),

	SETBIT(CommandGroup.BITMAP, "w"),
	/**
	 * BitMap command end
	 */

	/**
	 * Cuckoo filter command start
	 */
	CF_ADD(CommandGroup.CUCKOO_FILTER, "CF.ADD", "w"),

	CF_ADDNX(CommandGroup.CUCKOO_FILTER, "CF.ADDNX", "w"),

	CF_COUNT(CommandGroup.CUCKOO_FILTER, "CF.COUNT", "r"),

	CF_DEL(CommandGroup.CUCKOO_FILTER, "CF.DEL", "w"),

	CF_EXISTS(CommandGroup.CUCKOO_FILTER, "CF.EXISTS", "r"),

	CF_INFO(CommandGroup.CUCKOO_FILTER, "CF.INFO", "r"),

	CF_INSERT(CommandGroup.CUCKOO_FILTER, "CF.INSERT", "w"),

	CF_INSERTNX(CommandGroup.CUCKOO_FILTER, "CF.INSERTNX", "w"),

	CF_LOADCHUNK(CommandGroup.CUCKOO_FILTER, "CF.LOADCHUNK", "r"),

	CF_MEXISTS(CommandGroup.CUCKOO_FILTER, "CF.MEXISTS", "r"),

	CF_RESERVE(CommandGroup.CUCKOO_FILTER, "CF.RESERVE", "w"),

	CF_SCANDUMP(CommandGroup.CUCKOO_FILTER, "CF.SCANDUMP", "w"),
	/**
	 * Cuckoo filter command end
	 */

	/**
	 * Cluster command start
	 **/
	ASKING(CommandGroup.CLUSTER, "r"),

	CLUSTER(CommandGroup.CLUSTER, "rw", new SubCommand[]{SubCommand.CLUSTER_ADDSLOTS, SubCommand.CLUSTER_ADDSLOTSRANGE,
			SubCommand.CLUSTER_BUMPEPOCH, SubCommand.CLUSTER_COUNTFAILUREREPORTS, SubCommand.CLUSTER_COUNTKEYSINSLOT,
			SubCommand.CLUSTER_DELSLOTS, SubCommand.CLUSTER_DELSLOTSRANGE, SubCommand.CLUSTER_FAILOVER,
			SubCommand.CLUSTER_FLUSHSLOTS, SubCommand.CLUSTER_FORGET, SubCommand.CLUSTER_GETKEYSINSLOT,
			SubCommand.CLUSTER_INFO, SubCommand.CLUSTER_KEYSLOT, SubCommand.CLUSTER_LINKS, SubCommand.CLUSTER_MEET,
			SubCommand.CLUSTER_MIGRATION, SubCommand.CLUSTER_MYID, SubCommand.CLUSTER_MYSHARDID,
			SubCommand.CLUSTER_NODES, SubCommand.CLUSTER_REPLICAS, SubCommand.CLUSTER_REPLICATE,
			SubCommand.CLUSTER_RESET, SubCommand.CLUSTER_SAVECONFIG, SubCommand.CLUSTER_SETCONFIGEPOCH,
			SubCommand.CLUSTER_SETSLOT, SubCommand.CLUSTER_SHARDS, SubCommand.CLUSTER_SLAVES,
			SubCommand.CLUSTER_SLOT_STATS, SubCommand.CLUSTER_SLOTS}),

	READONLY(CommandGroup.CLUSTER, "w"),

	READWRITE(CommandGroup.CLUSTER, "w"),
	/**
	 * Cluster command end
	 **/

	/**
	 * Count-min sketch command start
	 **/
	CMS_INCRBY(CommandGroup.COUNT_MIN_SKETCH, "CMS.INCRBY", "w"),

	CMS_INFO(CommandGroup.COUNT_MIN_SKETCH, "CMS.INFO", "r"),

	CMS_INITBYDIM(CommandGroup.COUNT_MIN_SKETCH, "CMS.INITBYDIM", "r"),

	CMS_INITBYPROB(CommandGroup.COUNT_MIN_SKETCH, "CMS.INITBYPROB", "r"),

	CMS_MERGE(CommandGroup.COUNT_MIN_SKETCH, "CMS.MERGE", "r"),

	CMS_QUERY(CommandGroup.COUNT_MIN_SKETCH, "CMS.QUERY", "r"),
	/**
	 * Count-min sketch command end
	 **/

	/**
	 * connection command start
	 */
	AUTH(CommandGroup.CONNECTION, "r"),

	CLIENT(CommandGroup.CONNECTION, "rw",
			new SubCommand[]{SubCommand.CLIENT_CACHING, SubCommand.CLIENT_GETNAME, SubCommand.CLIENT_GETREDIR,
					SubCommand.CLIENT_ID, SubCommand.CLIENT_INFO, SubCommand.CLIENT_KILL, SubCommand.CLIENT_LIST,
					SubCommand.CLIENT_NO_EVICT, SubCommand.CLIENT_NO_TOUCH, SubCommand.CLIENT_PAUSE,
					SubCommand.CLIENT_REPLY, SubCommand.CLIENT_SETINFO, SubCommand.CLIENT_SETNAME,
					SubCommand.CLIENT_TRACKING, SubCommand.CLIENT_TRACKINGINFO, SubCommand.CLIENT_UNBLOCK,
					SubCommand.CLIENT_UNPAUSE}),

	ECHO(CommandGroup.CONNECTION, "w"),

	HELLO(CommandGroup.CONNECTION, "r"),

	PING(CommandGroup.CONNECTION, "r"),

	RESET(CommandGroup.CONNECTION, "w"),

	QUIT(CommandGroup.CONNECTION, "rw"),

	SELECT(CommandGroup.CONNECTION, "w"),
	/**
	 * connection command end
	 */

	/**
	 * generic command start
	 */
	WAIT(CommandGroup.GENERIC, "w"),

	WAITOF(CommandGroup.GENERIC, "w"),
	/**
	 * generic command end
	 */

	/**
	 * geo command start
	 **/
	GEOADD(CommandGroup.GEO, "w"),

	GEODIST(CommandGroup.GEO, "r"),

	GEOHASH(CommandGroup.GEO, "r"),

	GEOPOS(CommandGroup.GEO, "r"),

	GEORADIUS(CommandGroup.GEO, "r"),

	GEORADIUS_RO(CommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER(CommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER_RO(CommandGroup.GEO, "r"),

	GEOSEARCH(CommandGroup.GEO, "r"),

	GEOSEARCHSTORE(CommandGroup.GEO, "rw"),
	/**
	 * geo command end
	 **/

	/**
	 * hash command start
	 **/
	HDEL(CommandGroup.HASH, "w"),

	HEXISTS(CommandGroup.HASH, "r"),

	HEXPIRE(CommandGroup.HASH, "w"),

	HEXPIREAT(CommandGroup.HASH, "w"),

	HEXPIRETIME(CommandGroup.HASH, "w"),

	HGET(CommandGroup.HASH, "r"),

	HGETALL(CommandGroup.HASH, "r"),

	HGETDEL(CommandGroup.HASH, "rw"),

	HGETEX(CommandGroup.HASH, "rw"),

	HINCRBY(CommandGroup.HASH, "rw"),

	HINCRBYFLOAT(CommandGroup.HASH, "rw"),

	HKEYS(CommandGroup.HASH, "r"),

	HLEN(CommandGroup.HASH, "r"),

	HMGET(CommandGroup.HASH, "r"),

	HMSET(CommandGroup.HASH, "w"),

	HPERSIST(CommandGroup.HASH, "w"),

	HPEXPIRE(CommandGroup.HASH, "w"),

	HPEXPIREAT(CommandGroup.HASH, "w"),

	HPEXPIRETIME(CommandGroup.HASH, "w"),

	HPTTL(CommandGroup.HASH, "r"),

	HRANDFIELD(CommandGroup.HASH, "r"),

	HSCAN(CommandGroup.HASH, "r"),

	HSET(CommandGroup.HASH, "w"),

	HSETEX(CommandGroup.HASH, "w"),

	HSETNX(CommandGroup.HASH, "w"),

	HSTRLEN(CommandGroup.HASH, "r"),

	HTTL(CommandGroup.HASH, "r"),

	HVALS(CommandGroup.HASH, "r"),
	/**
	 * hash command end
	 **/

	/**
	 * hyperloglog command start
	 **/
	PFADD(CommandGroup.HYPERLOGLOG, "w"),

	PFCOUNT(CommandGroup.HYPERLOGLOG, "r"),

	PFMERGE(CommandGroup.HYPERLOGLOG, "w"),

	PFSELFTEST(CommandGroup.HYPERLOGLOG, "w"),
	/**
	 * hyperloglog command end
	 **/

	/**
	 * json command start
	 **/
	JSON_ARRAPPEND(CommandGroup.JSON, "JSON.ARRAPPEND", "w"),

	JSON_ARRINDEX(CommandGroup.JSON, "JSON.ARRINDEX", "r"),

	JSON_ARRINSERT(CommandGroup.JSON, "JSON.ARRINSERT", "w"),

	JSON_ARRLEN(CommandGroup.JSON, "JSON.ARRLEN", "r"),

	JSON_ARRPOP(CommandGroup.JSON, "JSON.ARRPOP", "r"),

	JSON_ARRTRIM(CommandGroup.JSON, "JSON.ARRTRIM", "w"),

	JSON_CLEAR(CommandGroup.JSON, "JSON.CLEAR", "w"),

	JSON_DEBUG(CommandGroup.JSON, "JSON.DEBUG", "r", new SubCommand[]{SubCommand.JSON_DEBUG_MEMORY}),

	JSON_DEL(CommandGroup.JSON, "JSON.DEL", "w"),

	JSON_FORGET(CommandGroup.JSON, "JSON.FORGET", "w"),

	JSON_GET(CommandGroup.JSON, "JSON.GET", "r"),

	JSON_MERGE(CommandGroup.JSON, "JSON.MERGE", "w"),

	JSON_MGET(CommandGroup.JSON, "JSON.MGET", "r"),

	JSON_MSET(CommandGroup.JSON, "JSON.MSET", "w"),

	JSON_NUMINCRBY(CommandGroup.JSON, "JSON.NUMINCRBY", "w"),

	JSON_NUMMULTBY(CommandGroup.JSON, "JSON.NUMMULTBY", "w"),

	JSON_OBJKEYS(CommandGroup.JSON, "JSON.OBJKEYS", "r"),

	JSON_OBJLEN(CommandGroup.JSON, "JSON.OBJLEN", "r"),

	JSON_RESP(CommandGroup.JSON, "JSON.RESP", "r"),

	JSON_SET(CommandGroup.JSON, "JSON.SET", "w"),

	JSON_STRAPPEND(CommandGroup.JSON, "JSON.STRAPPEND", "w"),

	JSON_STRLEN(CommandGroup.JSON, "JSON.STRLEN", "r"),

	JSON_TOGGLE(CommandGroup.JSON, "JSON.TOGGLE", "w"),

	JSON_TYPE(CommandGroup.JSON, "JSON.TYPE", "r"),
	/**
	 * json command end
	 **/
	;

	/**
	 * 命令分组
	 */
	private final CommandGroup group;

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

	/**
	 * 子命令
	 */
	private final SubCommand[] subCommands;

	/**
	 * 构造函数
	 *
	 * @param group
	 * 		命令分组
	 * @param mode
	 * 		命令模式
	 */
	Command(final CommandGroup group, final String mode) {
		this(group, mode, new SubCommand[0]);
	}

	/**
	 * 构造函数
	 *
	 * @param group
	 * 		命令分组
	 * @param mode
	 * 		命令模式
	 * @param subCommands
	 * 		子命令
	 */
	Command(final CommandGroup group, final String mode, final SubCommand[] subCommands) {
		this.group = group;
		this.name = name();
		raw = SafeEncoder.encode(name);
		if(Validate.hasText(mode)){
			String modeLower = mode.toLowerCase();
			this.read = modeLower.indexOf('r') > -1;
			this.write = modeLower.indexOf('w') > -1;
		}else{
			this.read = true;
			this.write = false;
		}
		this.subCommands = subCommands;
	}

	/**
	 * 构造函数
	 *
	 * @param group
	 * 		命令分组
	 * @param name
	 * 		命令名称
	 * @param mode
	 * 		命令模式
	 */
	Command(final CommandGroup group, final String name, final String mode) {
		this(group, name, mode, new SubCommand[0]);
	}

	/**
	 * 构造函数
	 *
	 * @param group
	 * 		命令分组
	 * @param name
	 * 		命令名称
	 * @param mode
	 * 		命令模式
	 * @param subCommands
	 * 		子命令
	 */
	Command(final CommandGroup group, final String name, final String mode, final SubCommand[] subCommands) {
		this.group = group;
		this.name = name;
		raw = SafeEncoder.encode(name);
		if(Validate.hasText(mode)){
			String modeLower = mode.toLowerCase();
			this.read = modeLower.indexOf('r') > -1;
			this.write = modeLower.indexOf('w') > -1;
		}else{
			this.read = true;
			this.write = false;
		}
		this.subCommands = subCommands;
	}

	/**
	 * 返回命令分组
	 *
	 * @return 命令分组
	 */
	public CommandGroup getGroup() {
		return group;
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

	/**
	 * 返回子命令
	 *
	 * @return 子命令
	 */
	public SubCommand[] getSubCommands() {
		return subCommands;
	}

	@Override
	public String toString() {
		return name + (Validate.isEmpty(subCommands) ? "" : StringUtils.join(subCommands, " "));
	}

}

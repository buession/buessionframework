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
	 * BitMap command start
	 **/
	BITCOUNT("r", CommandGroup.BITMAP),

	BITFIELD("r", CommandGroup.BITMAP),

	BITFIELD_RO("r", CommandGroup.BITMAP),

	BITOP("r", CommandGroup.BITMAP),

	BITPOS("r", CommandGroup.BITMAP),

	GETBIT("r", CommandGroup.BITMAP),

	SETBIT("w", CommandGroup.BITMAP),
	/**
	 * BitMap command end
	 **/

	/**
	 * Cluster command start
	 **/
	ASKING("r", CommandGroup.CLUSTER),

	CLUSTER("rw", CommandGroup.CLUSTER, SubCommand.CLUSTER_ADDSLOTS, SubCommand.CLUSTER_ADDSLOTSRANGE,
			SubCommand.CLUSTER_BUMPEPOCH, SubCommand.CLUSTER_COUNTFAILUREREPORTS, SubCommand.CLUSTER_COUNTKEYSINSLOT,
			SubCommand.CLUSTER_DELSLOTS, SubCommand.CLUSTER_DELSLOTSRANGE, SubCommand.CLUSTER_FAILOVER,
			SubCommand.CLUSTER_FLUSHSLOTS, SubCommand.CLUSTER_FORGET, SubCommand.CLUSTER_GETKEYSINSLOT,
			SubCommand.INFO, SubCommand.CLUSTER_KEYSLOT, SubCommand.CLUSTER_LINKS, SubCommand.CLUSTER_MEET,
			SubCommand.CLUSTER_MYID, SubCommand.CLUSTER_MYSHARDID, SubCommand.NODES, SubCommand.CLUSTER_REPLICAS,
			SubCommand.CLUSTER_REPLICATE, SubCommand.RESET, SubCommand.CLUSTER_SAVECONFIG,
			SubCommand.CLUSTER_SETCONFIGEPOCH, SubCommand.CLUSTER_SETSLOT, SubCommand.CLUSTER_SHARDS,
			SubCommand.SLAVES, SubCommand.CLUSTER_SLOTS),

	READONLY("r", CommandGroup.CLUSTER),

	READWRITE("r", CommandGroup.CLUSTER),
	/**
	 * Cluster command end
	 **/

	/**
	 * Connection command start
	 */
	AUTH("rw", CommandGroup.CONNECTION),

	CLIENT("rw", CommandGroup.CONNECTION, SubCommand.CLIENT_CACHING, SubCommand.CLIENT_GETNAME,
			SubCommand.CLIENT_GETREDIR, SubCommand.CLIENT_ID, SubCommand.CLIENT_INFO, SubCommand.CLIENT_KILL,
			SubCommand.CLIENT_LIST, SubCommand.CLIENT_NO_EVICT, SubCommand.CLIENT_NO_TOUCH, SubCommand.CLIENT_PAUSE,
			SubCommand.CLIENT_REPLY, SubCommand.CLIENT_SETINFO, SubCommand.CLIENT_SETNAME, SubCommand.CLIENT_TRACKING,
			SubCommand.CLIENT_TRACKINGINFO, SubCommand.CLIENT_UNBLOCK, SubCommand.CLIENT_UNPAUSE),

	ECHO("w", CommandGroup.CONNECTION),

	HELLO("w", CommandGroup.CONNECTION),

	PING("r", CommandGroup.CONNECTION),

	QUIT("rw", CommandGroup.CONNECTION),

	RESET("w", CommandGroup.CONNECTION),

	SELECT("w", CommandGroup.CONNECTION),
	/**
	 * Connection command end
	 */

	/**
	 * Key command start
	 **/
	COPY("rw", CommandGroup.KEY),

	DEL("rw", CommandGroup.KEY),

	DUMP("r", CommandGroup.KEY),

	EXISTS("r", CommandGroup.KEY),

	EXPIRE("w", CommandGroup.KEY),

	EXPIREAT("w", CommandGroup.KEY),

	EXPIRETIME("r", CommandGroup.KEY),

	KEYS("r", CommandGroup.KEY),

	MIGRATE("w", CommandGroup.KEY),

	MOVE("w", CommandGroup.KEY),

	OBJECT("rw", CommandGroup.KEY, SubCommand.OBJECT_ENCODING, SubCommand.OBJECT_REFQ, SubCommand.OBJECT_IDLETIME,
			SubCommand.OBJECT_REFCOUNT),

	PERSIST("w", CommandGroup.KEY),

	PEXPIRE("w", CommandGroup.KEY),

	PEXPIREAT("w", CommandGroup.KEY),

	PEXPIRETIME("r", CommandGroup.KEY),

	PTTL("r", CommandGroup.KEY),

	RANDOMKEY("r", CommandGroup.KEY),

	RENAME("w", CommandGroup.KEY),

	RENAMENX("w", CommandGroup.KEY),

	RESTORE("w", CommandGroup.KEY),

	SCAN("r", CommandGroup.KEY),

	SORT("rw", CommandGroup.KEY),

	SORT_RO("r", CommandGroup.KEY),

	TOUCH("w", CommandGroup.KEY),

	TTL("r", CommandGroup.KEY),

	TYPE("r", CommandGroup.KEY),

	UNLINK("w", CommandGroup.KEY),
	/**
	 * Key command end
	 **/

	/**
	 * Generic command start
	 **/
	WAIT("w", CommandGroup.KEY),

	WAITOF("w", CommandGroup.KEY),
	/**
	 * Generic command end
	 **/

	/**
	 * Geo command start
	 **/
	GEOADD("w", CommandGroup.GEO),

	GEODIST("r", CommandGroup.GEO),

	GEOHASH("r", CommandGroup.GEO),

	GEOPOS("r", CommandGroup.GEO),

	GEORADIUS("r", CommandGroup.GEO),

	GEORADIUS_RO("r", CommandGroup.GEO),

	GEORADIUSBYMEMBER("r", CommandGroup.GEO),

	GEORADIUSBYMEMBER_RO("r", CommandGroup.GEO),

	GEOSEARCH("r", CommandGroup.GEO),

	GEOSEARCHSTORE("rw", CommandGroup.GEO),
	/**
	 * Geo command end
	 **/

	/**
	 * Hash command start
	 **/
	HDEL("w", CommandGroup.HASH),

	HEXISTS("r", CommandGroup.HASH),

	HEXPIRE("w", CommandGroup.HASH),

	HEXPIREAT("w", CommandGroup.HASH),

	HEXPIRETIME("r", CommandGroup.HASH),

	HGET("r", CommandGroup.HASH),

	HGETALL("r", CommandGroup.HASH),

	HINCRBY("rw", CommandGroup.HASH),

	HINCRBYFLOAT("rw", CommandGroup.HASH),

	HKEYS("r", CommandGroup.HASH),

	HLEN("r", CommandGroup.HASH),

	HMGET("r", CommandGroup.HASH),

	HMSET("w", CommandGroup.HASH),

	HPERSIST("w", CommandGroup.HASH),

	HPEXPIRE("w", CommandGroup.HASH),

	HPEXPIREAT("w", CommandGroup.HASH),

	HPEXPIRETIME("r", CommandGroup.HASH),

	HPTTL("r", CommandGroup.HASH),

	HRANDFIELD("r", CommandGroup.HASH),

	HSCAN("r", CommandGroup.HASH),

	HSET("w", CommandGroup.HASH),

	HSETNX("w", CommandGroup.HASH),

	HSTRLEN("r", CommandGroup.HASH),

	HTTL("r", CommandGroup.HASH),

	HVALS("r", CommandGroup.HASH),
	/**
	 * Hash command start
	 **/

	/**
	 * Hyperloglog command start
	 **/
	PFADD("w", CommandGroup.HYPERLOGLOG),

	PFCOUNT("r", CommandGroup.HYPERLOGLOG),

	PFMERGE("w", CommandGroup.HYPERLOGLOG),
	/**
	 * Hyperloglog command end
	 **/

	/**
	 * ACL command start
	 **/
	ACL("rw", CommandGroup.ACL, SubCommand.ACL_CAT, SubCommand.ACL_DELUSER, SubCommand.ACL_DRYRUN,
			SubCommand.ACL_GENPASS, SubCommand.ACL_GETUSER, SubCommand.LIST, SubCommand.LOAD, SubCommand.ACL_LOG,
			SubCommand.SAVE, SubCommand.ACL_SETUSER, SubCommand.ACL_USERS, SubCommand.ACL_WHOAMI),
	/**
	 * ACL command end
	 **/

	/**
	 * list command start
	 **/
	BLPOP(CommandGroup.LIST),

	BRPOP(CommandGroup.LIST),

	BRPOPLPUSH(CommandGroup.LIST),

	LINDEX(CommandGroup.LIST),

	LINSERT(CommandGroup.LIST),

	LLEN(CommandGroup.LIST),

	LPOP(CommandGroup.LIST),

	LPOS(CommandGroup.LIST),

	LPUSH(CommandGroup.LIST),

	LPUSHX(CommandGroup.LIST),

	LRANGE(CommandGroup.LIST),

	LREM(CommandGroup.LIST),

	LSET(CommandGroup.LIST),

	LTRIM(CommandGroup.LIST),

	RPOP(CommandGroup.LIST),

	RPOPLPUSH(CommandGroup.LIST),

	RPUSH(CommandGroup.LIST),

	RPUSHX(CommandGroup.LIST),

	LMOVE(CommandGroup.LIST),

	BLMOVE(CommandGroup.LIST),
	/**
	 * list command end
	 **/

	/**
	 * pubsub command start
	 **/
	PSUBSCRIBE(CommandGroup.PUBSUB),

	PUBSUB_CHANNELS(CommandGroup.PUBSUB),

	PUBSUB_SHARDCHANNELS(CommandGroup.PUBSUB),

	PUBSUB_NUMPAT(CommandGroup.PUBSUB),

	PUBSUB_NUMSUB(CommandGroup.PUBSUB),

	PUBSUB_SHARDNUMSUB(CommandGroup.PUBSUB),

	PUBLISH(CommandGroup.PUBSUB),

	PUNSUBSCRIBE(CommandGroup.PUBSUB),

	SUBSCRIBE(CommandGroup.PUBSUB),

	UNSUBSCRIBE(CommandGroup.PUBSUB),
	/**
	 * pubsub command end
	 **/

	/**
	 * scripting command start
	 **/
	EVAL(CommandGroup.SCRIPTING),

	EVALSHA(CommandGroup.SCRIPTING),

	SCRIPT_EXISTS(CommandGroup.SCRIPTING),

	SCRIPT_FLUSH(CommandGroup.SCRIPTING),

	SCRIPT_KILL(CommandGroup.SCRIPTING),

	SCRIPT_LOAD(CommandGroup.SCRIPTING),
	/**
	 * scripting command end
	 **/

	/**
	 * server command start
	 **/


	BGREWRITEAOF(CommandGroup.SERVER),

	BGSAVE(CommandGroup.SERVER),

	CONFIG_GET(CommandGroup.SERVER),

	CONFIG_RESETSTAT(CommandGroup.SERVER),

	CONFIG_REWRITE(CommandGroup.SERVER),

	CONFIG_SET(CommandGroup.SERVER),

	DBSIZE(CommandGroup.SERVER),

	DEBUG(CommandGroup.SERVER),

	FLUSHALL(CommandGroup.SERVER),

	FLUSHDB(CommandGroup.SERVER),

	INFO(CommandGroup.SERVER),

	LASTSAVE(CommandGroup.SERVER),

	MEMORY_DOCTOR(CommandGroup.SERVER),

	MEMORY_MALLOCSTATS(CommandGroup.SERVER),

	MEMORY_PURGE(CommandGroup.SERVER),

	MEMORY_STATS(CommandGroup.SERVER),

	MEMORY_USAGE(CommandGroup.SERVER),

	MONITOR(CommandGroup.SERVER),

	REPLICAOF(CommandGroup.SERVER),

	ROLE(CommandGroup.SERVER),

	SAVE(CommandGroup.SERVER),

	SHUTDOWN(CommandGroup.SERVER),

	SLAVEOF(CommandGroup.SERVER),

	SLOWLOG_GET(CommandGroup.SERVER),

	SLOWLOG_LEN(CommandGroup.SERVER),

	SLOWLOG_RESET(CommandGroup.SERVER),

	SYNC(CommandGroup.SERVER),

	PSYNC(CommandGroup.SERVER),

	TIME(CommandGroup.SERVER),

	FAILOVER(CommandGroup.SERVER),

	MODULE_LIST(CommandGroup.SERVER),

	MODULE_LOAD(CommandGroup.SERVER),

	MODULE_UNLOAD(CommandGroup.SERVER),

	SWAPDB(CommandGroup.SERVER),
	/**
	 * server command end
	 **/

	/**
	 * set command start
	 **/
	SADD(CommandGroup.SET),

	SCARD(CommandGroup.SET),

	SDIFF(CommandGroup.SET),

	SDIFFSTORE(CommandGroup.SET),

	SINTER(CommandGroup.SET),

	SINTERSTORE(CommandGroup.SET),

	SISMEMBER(CommandGroup.SET),

	SMISMEMBER(CommandGroup.SET),

	SMEMBERS(CommandGroup.SET),

	SMOVE(CommandGroup.SET),

	SPOP(CommandGroup.SET),

	SRANDMEMBER(CommandGroup.SET),

	SREM(CommandGroup.SET),

	SSCAN(CommandGroup.SET),

	SUNION(CommandGroup.SET),

	SUNIONSTORE(CommandGroup.SET),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	ZADD(CommandGroup.SORTEDSET),

	ZCARD(CommandGroup.SORTEDSET),

	ZCOUNT(CommandGroup.SORTEDSET),

	ZINCRBY(CommandGroup.SORTEDSET),

	ZINTERSTORE(CommandGroup.SORTEDSET),

	ZLEXCOUNT(CommandGroup.SORTEDSET),

	ZMSCORE(CommandGroup.SORTEDSET),

	ZRANGE(CommandGroup.SORTEDSET),

	ZRANGEBYLEX(CommandGroup.SORTEDSET),

	ZRANGEBYSCORE(CommandGroup.SORTEDSET),

	ZRANGESTORE(CommandGroup.SORTEDSET),

	ZRANK(CommandGroup.SORTEDSET),

	ZPOPMAX(CommandGroup.SORTEDSET),

	ZPOPMIN(CommandGroup.SORTEDSET),

	ZREM(CommandGroup.SORTEDSET),

	ZREMRANGEBYLEX(CommandGroup.SORTEDSET),

	ZREMRANGEBYSCORE(CommandGroup.SORTEDSET),

	ZREMRANGEBYRANK(CommandGroup.SORTEDSET),

	ZREVRANGE(CommandGroup.SORTEDSET),

	ZREVRANGEBYLEX(CommandGroup.SORTEDSET),

	ZREVRANGEBYSCORE(CommandGroup.SORTEDSET),

	ZREVRANK(CommandGroup.SORTEDSET),

	ZSCAN(CommandGroup.SORTEDSET),

	ZSCORE(CommandGroup.SORTEDSET),

	ZUNIONSTORE(CommandGroup.SORTEDSET),

	BZPOPMIN(CommandGroup.SORTEDSET),

	BZPOPMAX(CommandGroup.SORTEDSET),

	ZDIFF(CommandGroup.SORTEDSET),

	ZDIFFSTORE(CommandGroup.SORTEDSET),

	ZINTER(CommandGroup.SORTEDSET),

	ZRANDMEMBER(CommandGroup.SORTEDSET),

	ZUNION(CommandGroup.SORTEDSET),
	/**
	 * sorted set command end
	 **/

	/**
	 * stream command start
	 **/
	XACK(CommandGroup.STREAM),

	XADD(CommandGroup.STREAM),

	XAUTOCLAIM(CommandGroup.STREAM),

	XCLAIM(CommandGroup.STREAM),

	XDEL(CommandGroup.STREAM),

	XGROUP_CREATE(CommandGroup.STREAM),

	XGROUP_CREATECONSUMER(CommandGroup.STREAM),

	XGROUP_DELCONSUMER(CommandGroup.STREAM),

	XGROUP_DESTROY(CommandGroup.STREAM),

	XGROUP_SETID(CommandGroup.STREAM),

	XINFO_CONSUMERS(CommandGroup.STREAM),

	XINFO_GROUPS(CommandGroup.STREAM),

	XINFO_STREAM(CommandGroup.STREAM),

	XLEN(CommandGroup.STREAM),

	XPENDING(CommandGroup.STREAM),

	XRANGE(CommandGroup.STREAM),

	XREAD(CommandGroup.STREAM),

	XREADGROUP(CommandGroup.STREAM),

	XREVRANGE(CommandGroup.STREAM),

	XSETID(CommandGroup.STREAM),

	XTRIM(CommandGroup.STREAM),
	/**
	 * stream command end
	 **/

	/**
	 * string command start
	 **/
	APPEND(CommandGroup.STRING),

	DECR(CommandGroup.STRING),

	DECRBY(CommandGroup.STRING),

	GET(CommandGroup.STRING),

	GETRANGE(CommandGroup.STRING),

	GETSET(CommandGroup.STRING),

	GETEX(CommandGroup.STRING),

	GETDEL(CommandGroup.STRING),

	INCR(CommandGroup.STRING),

	INCRBY(CommandGroup.STRING),

	INCRBYFLOAT(CommandGroup.STRING),

	MGET(CommandGroup.STRING),

	MSET(CommandGroup.STRING),

	MSETNX(CommandGroup.STRING),

	PSETEX(CommandGroup.STRING),

	SET(CommandGroup.STRING),

	SETEX(CommandGroup.STRING),

	SETNX(CommandGroup.STRING),

	SETRANGE(CommandGroup.STRING),

	STRLEN(CommandGroup.STRING),

	SUBSTR(CommandGroup.STRING),
	/** string command end **/

	/**
	 * transaction command start
	 **/
	DISCARD(CommandGroup.TRANSACTION),

	EXEC(CommandGroup.TRANSACTION),

	MULTI(CommandGroup.TRANSACTION),

	UNWATCH(CommandGroup.TRANSACTION),

	WATCH(CommandGroup.TRANSACTION);

	/**
	 * transaction command end
	 **/

	private final byte[] raw;

	/**
	 * 命令分组
	 */
	private final CommandGroup group;

	/**
	 * 子命令
	 */
	private final SubCommand[] subCommands;

	/**
	 * 是否为读操作命令
	 */
	private final boolean read;

	/**
	 * 是否为写操作命令
	 */
	private final boolean write;

	Command(final String mode, final CommandGroup group) {
		this(mode, group, new SubCommand[]{});
	}

	Command(final String mode, final CommandGroup group, final SubCommand... subCommands) {
		raw = SafeEncoder.encode(name());
		this.group = group;
		this.subCommands = subCommands;
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
		return name();
	}

	public CommandGroup getGroup() {
		return group;
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

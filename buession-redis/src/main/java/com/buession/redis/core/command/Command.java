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

/**
 * Redis 协议命令
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public enum Command implements ProtocolCommand {
	/**
	 * bitmat command start
	 **/
	BITCOUNT(CommandGroup.BITMAP),

	BITFIELD(CommandGroup.BITMAP),

	BITFIELD_RO(CommandGroup.BITMAP),

	BITOP(CommandGroup.BITMAP),

	BITPOS(CommandGroup.BITMAP),

	GETBIT(CommandGroup.BITMAP),

	SETBIT(CommandGroup.BITMAP),
	/**
	 * bitmat command end
	 **/

	/**
	 * cluster command start
	 **/
	CLUSTER_MY_ID(CommandGroup.CLUSTER),

	CLUSTER_ADDSLOTS(CommandGroup.CLUSTER),

	CLUSTER_SLOTS(CommandGroup.CLUSTER),

	CLUSTER_COUNTFAILUREREPORTS(CommandGroup.CLUSTER),

	CLUSTER_COUNTKEYSINSLOT(CommandGroup.CLUSTER),

	CLUSTER_DELSLOTS(CommandGroup.CLUSTER),

	CLUSTER_FLUSHSLOTS(CommandGroup.CLUSTER),

	CLUSTER_FAILOVER(CommandGroup.CLUSTER),

	CLUSTER_FORGET(CommandGroup.CLUSTER),

	CLUSTER_GETKEYSINSLOT(CommandGroup.CLUSTER),

	CLUSTER_KEYSLOT(CommandGroup.CLUSTER),

	CLUSTER_SETCONFIGEPOCH(CommandGroup.CLUSTER),

	CLUSTER_BUMPEPOCH(CommandGroup.CLUSTER),

	CLUSTER_INFO(CommandGroup.CLUSTER),

	CLUSTER_MEET(CommandGroup.CLUSTER),

	CLUSTER_NODES(CommandGroup.CLUSTER),

	CLUSTER_SLAVES(CommandGroup.CLUSTER),

	CLUSTER_REPLICAS(CommandGroup.CLUSTER),

	CLUSTER_REPLICATE(CommandGroup.CLUSTER),

	CLUSTER_RESET(CommandGroup.CLUSTER),

	CLUSTER_SAVECONFIG(CommandGroup.CLUSTER),

	CLUSTER_SETSLOT(CommandGroup.CLUSTER),

	ASKING(CommandGroup.CLUSTER),

	READWRITE(CommandGroup.CLUSTER),

	READONLY(CommandGroup.CLUSTER),
	/**
	 * cluster command end
	 **/

	/**
	 * connection command start
	 */
	AUTH(CommandGroup.CONNECTION),

	ECHO(CommandGroup.CONNECTION),

	PING(CommandGroup.CONNECTION),

	RESET(CommandGroup.CONNECTION),

	QUIT(CommandGroup.CONNECTION),

	SELECT(CommandGroup.CONNECTION),

	CLIENT_KILL(CommandGroup.CONNECTION),

	CLIENT_SETNAME(CommandGroup.CONNECTION),

	CLIENT_GETNAME(CommandGroup.CONNECTION),

	CLIENT_GETREDIR(CommandGroup.CONNECTION),

	CLIENT_CACHING(CommandGroup.CONNECTION),

	CLIENT_ID(CommandGroup.CONNECTION),

	CLIENT_LIST(CommandGroup.CONNECTION),

	CLIENT_INFO(CommandGroup.CONNECTION),

	CLIENT_SET_INFO(CommandGroup.CONNECTION),

	CLIENT_PAUSE(CommandGroup.CONNECTION),

	CLIENT_UNPAUSE(CommandGroup.CONNECTION),

	CLIENT_REPLY(CommandGroup.CONNECTION),

	CLIENT_UNBLOCK(CommandGroup.CONNECTION),

	CLIENT_NO_EVICT(CommandGroup.CONNECTION),

	CLIENT_NO_TOUCH(CommandGroup.CONNECTION),
	/**
	 * connection command end
	 */

	/**
	 * geo command start
	 **/
	GEOADD(CommandGroup.GEO),

	GEOHASH(CommandGroup.GEO),

	GEOPOS(CommandGroup.GEO),

	GEODIST(CommandGroup.GEO),

	GEORADIUS(CommandGroup.GEO),

	GEORADIUS_RO(CommandGroup.GEO),

	GEORADIUSBYMEMBER(CommandGroup.GEO),

	GEORADIUSBYMEMBER_RO(CommandGroup.TRANSACTION),
	/**
	 * geo command end
	 **/

	/**
	 * hash command start
	 **/
	HDEL(CommandGroup.HASH),

	HEXISTS(CommandGroup.HASH),

	HGET(CommandGroup.HASH),

	HGETALL(CommandGroup.HASH),

	HINCRBY(CommandGroup.HASH),

	HINCRBYFLOAT(CommandGroup.HASH),

	HKEYS(CommandGroup.HASH),

	HLEN(CommandGroup.HASH),

	HMGET(CommandGroup.HASH),

	HMSET(CommandGroup.HASH),

	HRANDFIELD(CommandGroup.HASH),

	HSCAN(CommandGroup.HASH),

	HSET(CommandGroup.HASH),

	HSETNX(CommandGroup.HASH),

	HSTRLEN(CommandGroup.HASH),

	HVALS(CommandGroup.HASH),
	/**
	 * hash command end
	 **/

	/**
	 * hyperloglog command start
	 **/
	PFADD(CommandGroup.HYPERLOGLOG),

	PFCOUNT(CommandGroup.HYPERLOGLOG),

	PFMERGE(CommandGroup.HYPERLOGLOG),
	/**
	 * hyperloglog command end
	 **/

	/**
	 * key command start
	 **/
	COPY(CommandGroup.KEY),

	DEL(CommandGroup.KEY),

	DUMP(CommandGroup.KEY),

	EXISTS(CommandGroup.KEY),

	EXPIRE(CommandGroup.KEY),

	EXPIREAT(CommandGroup.KEY),

	MIGRATE(CommandGroup.KEY),

	MOVE(CommandGroup.KEY),

	KEYS(CommandGroup.KEY),

	PERSIST(CommandGroup.KEY),

	PEXPIRE(CommandGroup.KEY),

	PEXPIREAT(CommandGroup.KEY),

	PTTL(CommandGroup.KEY),

	RANDOMKEY(CommandGroup.KEY),

	RENAME(CommandGroup.KEY),

	RENAMENX(CommandGroup.KEY),

	RESTORE(CommandGroup.KEY),

	SCAN(CommandGroup.KEY),

	SORT(CommandGroup.KEY),

	SORT_RO(CommandGroup.KEY),

	TTL(CommandGroup.KEY),

	TYPE(CommandGroup.KEY),

	TOUCH(CommandGroup.KEY),

	UNLINK(CommandGroup.KEY),

	WAIT(CommandGroup.KEY),

	OBJECT_ENCODING(CommandGroup.KEY),

	OBJECT_REFQ(CommandGroup.KEY),

	OBJECT_IDLETIME(CommandGroup.KEY),

	OBJECT_REFCOUNT(CommandGroup.KEY),
	/** key command end **/

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
	ACL_CAT(CommandGroup.SERVER),

	ACL_SETUSER(CommandGroup.SERVER),

	ACL_GETUSER(CommandGroup.SERVER),

	ACL_USERS(CommandGroup.SERVER),

	ACL_WHOAMI(CommandGroup.SERVER),

	ACL_DELUSER(CommandGroup.SERVER),

	ACL_GENPASS(CommandGroup.SERVER),

	ACL_LIST(CommandGroup.SERVER),

	ACL_LOAD(CommandGroup.SERVER),

	ACL_LOG(CommandGroup.SERVER),

	ACL_LOGREST(CommandGroup.SERVER),

	ACL_LOGSAVE(CommandGroup.SERVER),

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

	@Override
	public boolean isRead() {
		return read;
	}

	@Override
	public boolean isWrite() {
		return write;
	}

	public CommandGroup getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return StringUtils.replace(name(), "_", " ");
	}

}

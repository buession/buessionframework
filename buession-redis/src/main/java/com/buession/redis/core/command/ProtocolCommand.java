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

/**
 * Redis 协议命令
 *
 * @author Yong.Teng
 */
public enum ProtocolCommand {
	/**
	 * bitmat command start
	 **/
	BITCOUNT(ProtocolCommandGroup.BITMAP),

	BITFIELD(ProtocolCommandGroup.BITMAP),

	BITFIELD_RO(ProtocolCommandGroup.BITMAP),

	BITOP(ProtocolCommandGroup.BITMAP),

	BITPOS(ProtocolCommandGroup.BITMAP),

	GETBIT(ProtocolCommandGroup.BITMAP),

	SETBIT(ProtocolCommandGroup.BITMAP),
	/**
	 * bitmat command end
	 **/

	/**
	 * cluster command start
	 **/
	CLUSTER_MY_ID(ProtocolCommandGroup.CLUSTER),

	CLUSTER_ADDSLOTS(ProtocolCommandGroup.CLUSTER),

	CLUSTER_SLOTS(ProtocolCommandGroup.CLUSTER),

	CLUSTER_COUNTFAILUREREPORTS(ProtocolCommandGroup.CLUSTER),

	CLUSTER_COUNTKEYSINSLOT(ProtocolCommandGroup.CLUSTER),

	CLUSTER_DELSLOTS(ProtocolCommandGroup.CLUSTER),

	CLUSTER_FLUSHSLOTS(ProtocolCommandGroup.CLUSTER),

	CLUSTER_FAILOVER(ProtocolCommandGroup.CLUSTER),

	CLUSTER_FORGET(ProtocolCommandGroup.CLUSTER),

	CLUSTER_GETKEYSINSLOT(ProtocolCommandGroup.CLUSTER),

	CLUSTER_KEYSLOT(ProtocolCommandGroup.CLUSTER),

	CLUSTER_SETCONFIGEPOCH(ProtocolCommandGroup.CLUSTER),

	CLUSTER_BUMPEPOCH(ProtocolCommandGroup.CLUSTER),

	CLUSTER_INFO(ProtocolCommandGroup.CLUSTER),

	CLUSTER_MEET(ProtocolCommandGroup.CLUSTER),

	CLUSTER_NODES(ProtocolCommandGroup.CLUSTER),

	CLUSTER_SLAVES(ProtocolCommandGroup.CLUSTER),

	CLUSTER_REPLICAS(ProtocolCommandGroup.CLUSTER),

	CLUSTER_REPLICATE(ProtocolCommandGroup.CLUSTER),

	CLUSTER_RESET(ProtocolCommandGroup.CLUSTER),

	CLUSTER_SAVECONFIG(ProtocolCommandGroup.CLUSTER),

	CLUSTER_SETSLOT(ProtocolCommandGroup.CLUSTER),

	ASKING(ProtocolCommandGroup.CLUSTER),

	READWRITE(ProtocolCommandGroup.CLUSTER),

	READONLY(ProtocolCommandGroup.CLUSTER),
	/**
	 * cluster command end
	 **/

	/**
	 * connection command start
	 */
	AUTH(ProtocolCommandGroup.CONNECTION),

	ECHO(ProtocolCommandGroup.CONNECTION),

	PING(ProtocolCommandGroup.CONNECTION),

	RESET(ProtocolCommandGroup.CONNECTION),

	QUIT(ProtocolCommandGroup.CONNECTION),

	SELECT(ProtocolCommandGroup.CONNECTION),

	CLIENT_KILL(ProtocolCommandGroup.CONNECTION),

	CLIENT_SETNAME(ProtocolCommandGroup.CONNECTION),

	CLIENT_GETNAME(ProtocolCommandGroup.CONNECTION),

	CLIENT_GETREDIR(ProtocolCommandGroup.CONNECTION),

	CLIENT_CACHING(ProtocolCommandGroup.CONNECTION),

	CLIENT_ID(ProtocolCommandGroup.CONNECTION),

	CLIENT_LIST(ProtocolCommandGroup.CONNECTION),

	CLIENT_INFO(ProtocolCommandGroup.CONNECTION),

	CLIENT_SET_INFO(ProtocolCommandGroup.CONNECTION),

	CLIENT_PAUSE(ProtocolCommandGroup.CONNECTION),

	CLIENT_UNPAUSE(ProtocolCommandGroup.CONNECTION),

	CLIENT_REPLY(ProtocolCommandGroup.CONNECTION),

	CLIENT_UNBLOCK(ProtocolCommandGroup.CONNECTION),

	CLIENT_NO_EVICT(ProtocolCommandGroup.CONNECTION),

	CLIENT_NO_TOUCH(ProtocolCommandGroup.CONNECTION),
	/**
	 * connection command end
	 */

	/**
	 * geo command start
	 **/
	GEOADD(ProtocolCommandGroup.GEO),

	GEOHASH(ProtocolCommandGroup.GEO),

	GEOPOS(ProtocolCommandGroup.GEO),

	GEODIST(ProtocolCommandGroup.GEO),

	GEORADIUS(ProtocolCommandGroup.GEO),

	GEORADIUS_RO(ProtocolCommandGroup.GEO),

	GEORADIUSBYMEMBER(ProtocolCommandGroup.GEO),

	GEORADIUSBYMEMBER_RO(ProtocolCommandGroup.TRANSACTION),
	/**
	 * geo command end
	 **/

	/**
	 * hash command start
	 **/
	HDEL(ProtocolCommandGroup.HASH),

	HEXISTS(ProtocolCommandGroup.HASH),

	HGET(ProtocolCommandGroup.HASH),

	HGETALL(ProtocolCommandGroup.HASH),

	HINCRBY(ProtocolCommandGroup.HASH),

	HINCRBYFLOAT(ProtocolCommandGroup.HASH),

	HKEYS(ProtocolCommandGroup.HASH),

	HLEN(ProtocolCommandGroup.HASH),

	HMGET(ProtocolCommandGroup.HASH),

	HMSET(ProtocolCommandGroup.HASH),

	HRANDFIELD(ProtocolCommandGroup.HASH),

	HSCAN(ProtocolCommandGroup.HASH),

	HSET(ProtocolCommandGroup.HASH),

	HSETNX(ProtocolCommandGroup.HASH),

	HSTRLEN(ProtocolCommandGroup.HASH),

	HVALS(ProtocolCommandGroup.HASH),
	/**
	 * hash command end
	 **/

	/**
	 * hyperloglog command start
	 **/
	PFADD(ProtocolCommandGroup.HYPERLOGLOG),

	PFCOUNT(ProtocolCommandGroup.HYPERLOGLOG),

	PFMERGE(ProtocolCommandGroup.HYPERLOGLOG),
	/**
	 * hyperloglog command end
	 **/

	/**
	 * key command start
	 **/
	COPY(ProtocolCommandGroup.KEY),

	DEL(ProtocolCommandGroup.KEY),

	DUMP(ProtocolCommandGroup.KEY),

	EXISTS(ProtocolCommandGroup.KEY),

	EXPIRE(ProtocolCommandGroup.KEY),

	EXPIREAT(ProtocolCommandGroup.KEY),

	MIGRATE(ProtocolCommandGroup.KEY),

	MOVE(ProtocolCommandGroup.KEY),

	KEYS(ProtocolCommandGroup.KEY),

	PERSIST(ProtocolCommandGroup.KEY),

	PEXPIRE(ProtocolCommandGroup.KEY),

	PEXPIREAT(ProtocolCommandGroup.KEY),

	PTTL(ProtocolCommandGroup.KEY),

	RANDOMKEY(ProtocolCommandGroup.KEY),

	RENAME(ProtocolCommandGroup.KEY),

	RENAMENX(ProtocolCommandGroup.KEY),

	RESTORE(ProtocolCommandGroup.KEY),

	SCAN(ProtocolCommandGroup.KEY),

	SORT(ProtocolCommandGroup.KEY),

	SORT_RO(ProtocolCommandGroup.KEY),

	TTL(ProtocolCommandGroup.KEY),

	TYPE(ProtocolCommandGroup.KEY),

	TOUCH(ProtocolCommandGroup.KEY),

	UNLINK(ProtocolCommandGroup.KEY),

	WAIT(ProtocolCommandGroup.KEY),

	OBJECT_ENCODING(ProtocolCommandGroup.KEY),

	OBJECT_REFQ(ProtocolCommandGroup.KEY),

	OBJECT_IDLETIME(ProtocolCommandGroup.KEY),

	OBJECT_REFCOUNT(ProtocolCommandGroup.KEY),
	/** key command end **/

	/**
	 * list command start
	 **/
	BLPOP(ProtocolCommandGroup.LIST),

	BRPOP(ProtocolCommandGroup.LIST),

	BRPOPLPUSH(ProtocolCommandGroup.LIST),

	LINDEX(ProtocolCommandGroup.LIST),

	LINSERT(ProtocolCommandGroup.LIST),

	LLEN(ProtocolCommandGroup.LIST),

	LPOP(ProtocolCommandGroup.LIST),

	LPOS(ProtocolCommandGroup.LIST),

	LPUSH(ProtocolCommandGroup.LIST),

	LPUSHX(ProtocolCommandGroup.LIST),

	LRANGE(ProtocolCommandGroup.LIST),

	LREM(ProtocolCommandGroup.LIST),

	LSET(ProtocolCommandGroup.LIST),

	LTRIM(ProtocolCommandGroup.LIST),

	RPOP(ProtocolCommandGroup.LIST),

	RPOPLPUSH(ProtocolCommandGroup.LIST),

	RPUSH(ProtocolCommandGroup.LIST),

	RPUSHX(ProtocolCommandGroup.LIST),

	LMOVE(ProtocolCommandGroup.LIST),

	BLMOVE(ProtocolCommandGroup.LIST),
	/**
	 * list command end
	 **/

	/**
	 * pubsub command start
	 **/
	PSUBSCRIBE(ProtocolCommandGroup.PUBSUB),

	PUBSUB_CHANNELS(ProtocolCommandGroup.PUBSUB),

	PUBSUB_SHARDCHANNELS(ProtocolCommandGroup.PUBSUB),

	PUBSUB_NUMPAT(ProtocolCommandGroup.PUBSUB),

	PUBSUB_NUMSUB(ProtocolCommandGroup.PUBSUB),

	PUBSUB_SHARDNUMSUB(ProtocolCommandGroup.PUBSUB),

	PUBLISH(ProtocolCommandGroup.PUBSUB),

	PUNSUBSCRIBE(ProtocolCommandGroup.PUBSUB),

	SUBSCRIBE(ProtocolCommandGroup.PUBSUB),

	UNSUBSCRIBE(ProtocolCommandGroup.PUBSUB),
	/**
	 * pubsub command end
	 **/

	/**
	 * scripting command start
	 **/
	EVAL(ProtocolCommandGroup.SCRIPTING),

	EVALSHA(ProtocolCommandGroup.SCRIPTING),

	SCRIPT_EXISTS(ProtocolCommandGroup.SCRIPTING),

	SCRIPT_FLUSH(ProtocolCommandGroup.SCRIPTING),

	SCRIPT_KILL(ProtocolCommandGroup.SCRIPTING),

	SCRIPT_LOAD(ProtocolCommandGroup.SCRIPTING),
	/**
	 * scripting command end
	 **/

	/**
	 * server command start
	 **/
	ACL_CAT(ProtocolCommandGroup.SERVER),

	ACL_SETUSER(ProtocolCommandGroup.SERVER),

	ACL_GETUSER(ProtocolCommandGroup.SERVER),

	ACL_USERS(ProtocolCommandGroup.SERVER),

	ACL_WHOAMI(ProtocolCommandGroup.SERVER),

	ACL_DELUSER(ProtocolCommandGroup.SERVER),

	ACL_GENPASS(ProtocolCommandGroup.SERVER),

	ACL_LIST(ProtocolCommandGroup.SERVER),

	ACL_LOAD(ProtocolCommandGroup.SERVER),

	ACL_LOG(ProtocolCommandGroup.SERVER),

	ACL_LOGREST(ProtocolCommandGroup.SERVER),

	ACL_LOGSAVE(ProtocolCommandGroup.SERVER),

	BGREWRITEAOF(ProtocolCommandGroup.SERVER),

	BGSAVE(ProtocolCommandGroup.SERVER),

	CONFIG_GET(ProtocolCommandGroup.SERVER),

	CONFIG_RESETSTAT(ProtocolCommandGroup.SERVER),

	CONFIG_REWRITE(ProtocolCommandGroup.SERVER),

	CONFIG_SET(ProtocolCommandGroup.SERVER),

	DBSIZE(ProtocolCommandGroup.SERVER),

	DEBUG(ProtocolCommandGroup.SERVER),

	FLUSHALL(ProtocolCommandGroup.SERVER),

	FLUSHDB(ProtocolCommandGroup.SERVER),

	INFO(ProtocolCommandGroup.SERVER),

	LASTSAVE(ProtocolCommandGroup.SERVER),

	MEMORY_DOCTOR(ProtocolCommandGroup.SERVER),

	MEMORY_MALLOCSTATS(ProtocolCommandGroup.SERVER),

	MEMORY_PURGE(ProtocolCommandGroup.SERVER),

	MEMORY_STATS(ProtocolCommandGroup.SERVER),

	MEMORY_USAGE(ProtocolCommandGroup.SERVER),

	MONITOR(ProtocolCommandGroup.SERVER),

	REPLICAOF(ProtocolCommandGroup.SERVER),

	ROLE(ProtocolCommandGroup.SERVER),

	SAVE(ProtocolCommandGroup.SERVER),

	SHUTDOWN(ProtocolCommandGroup.SERVER),

	SLAVEOF(ProtocolCommandGroup.SERVER),

	SLOWLOG_GET(ProtocolCommandGroup.SERVER),

	SLOWLOG_LEN(ProtocolCommandGroup.SERVER),

	SLOWLOG_RESET(ProtocolCommandGroup.SERVER),

	SYNC(ProtocolCommandGroup.SERVER),

	PSYNC(ProtocolCommandGroup.SERVER),

	TIME(ProtocolCommandGroup.SERVER),

	FAILOVER(ProtocolCommandGroup.SERVER),

	MODULE_LIST(ProtocolCommandGroup.SERVER),

	MODULE_LOAD(ProtocolCommandGroup.SERVER),

	MODULE_UNLOAD(ProtocolCommandGroup.SERVER),

	SWAPDB(ProtocolCommandGroup.SERVER),
	/**
	 * server command end
	 **/

	/**
	 * set command start
	 **/
	SADD(ProtocolCommandGroup.SET),

	SCARD(ProtocolCommandGroup.SET),

	SDIFF(ProtocolCommandGroup.SET),

	SDIFFSTORE(ProtocolCommandGroup.SET),

	SINTER(ProtocolCommandGroup.SET),

	SINTERSTORE(ProtocolCommandGroup.SET),

	SISMEMBER(ProtocolCommandGroup.SET),

	SMISMEMBER(ProtocolCommandGroup.SET),

	SMEMBERS(ProtocolCommandGroup.SET),

	SMOVE(ProtocolCommandGroup.SET),

	SPOP(ProtocolCommandGroup.SET),

	SRANDMEMBER(ProtocolCommandGroup.SET),

	SREM(ProtocolCommandGroup.SET),

	SSCAN(ProtocolCommandGroup.SET),

	SUNION(ProtocolCommandGroup.SET),

	SUNIONSTORE(ProtocolCommandGroup.SET),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	ZADD(ProtocolCommandGroup.SORTEDSET),

	ZCARD(ProtocolCommandGroup.SORTEDSET),

	ZCOUNT(ProtocolCommandGroup.SORTEDSET),

	ZINCRBY(ProtocolCommandGroup.SORTEDSET),

	ZINTERSTORE(ProtocolCommandGroup.SORTEDSET),

	ZLEXCOUNT(ProtocolCommandGroup.SORTEDSET),

	ZMSCORE(ProtocolCommandGroup.SORTEDSET),

	ZRANGE(ProtocolCommandGroup.SORTEDSET),

	ZRANGEBYLEX(ProtocolCommandGroup.SORTEDSET),

	ZRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET),

	ZRANGESTORE(ProtocolCommandGroup.SORTEDSET),

	ZRANK(ProtocolCommandGroup.SORTEDSET),

	ZPOPMAX(ProtocolCommandGroup.SORTEDSET),

	ZPOPMIN(ProtocolCommandGroup.SORTEDSET),

	ZREM(ProtocolCommandGroup.SORTEDSET),

	ZREMRANGEBYLEX(ProtocolCommandGroup.SORTEDSET),

	ZREMRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET),

	ZREMRANGEBYRANK(ProtocolCommandGroup.SORTEDSET),

	ZREVRANGE(ProtocolCommandGroup.SORTEDSET),

	ZREVRANGEBYLEX(ProtocolCommandGroup.SORTEDSET),

	ZREVRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET),

	ZREVRANK(ProtocolCommandGroup.SORTEDSET),

	ZSCAN(ProtocolCommandGroup.SORTEDSET),

	ZSCORE(ProtocolCommandGroup.SORTEDSET),

	ZUNIONSTORE(ProtocolCommandGroup.SORTEDSET),

	BZPOPMIN(ProtocolCommandGroup.SORTEDSET),

	BZPOPMAX(ProtocolCommandGroup.SORTEDSET),

	ZDIFF(ProtocolCommandGroup.SORTEDSET),

	ZDIFFSTORE(ProtocolCommandGroup.SORTEDSET),

	ZINTER(ProtocolCommandGroup.SORTEDSET),

	ZRANDMEMBER(ProtocolCommandGroup.SORTEDSET),

	ZUNION(ProtocolCommandGroup.SORTEDSET),
	/**
	 * sorted set command end
	 **/

	/**
	 * stream command start
	 **/
	XACK(ProtocolCommandGroup.STREAM),

	XADD(ProtocolCommandGroup.STREAM),

	XAUTOCLAIM(ProtocolCommandGroup.STREAM),

	XCLAIM(ProtocolCommandGroup.STREAM),

	XDEL(ProtocolCommandGroup.STREAM),

	XGROUP_CREATE(ProtocolCommandGroup.STREAM),

	XGROUP_CREATECONSUMER(ProtocolCommandGroup.STREAM),

	XGROUP_DELCONSUMER(ProtocolCommandGroup.STREAM),

	XGROUP_DESTROY(ProtocolCommandGroup.STREAM),

	XGROUP_SETID(ProtocolCommandGroup.STREAM),

	XINFO_CONSUMERS(ProtocolCommandGroup.STREAM),

	XINFO_GROUPS(ProtocolCommandGroup.STREAM),

	XINFO_STREAM(ProtocolCommandGroup.STREAM),

	XLEN(ProtocolCommandGroup.STREAM),

	XPENDING(ProtocolCommandGroup.STREAM),

	XRANGE(ProtocolCommandGroup.STREAM),

	XREAD(ProtocolCommandGroup.STREAM),

	XREADGROUP(ProtocolCommandGroup.STREAM),

	XREVRANGE(ProtocolCommandGroup.STREAM),

	XSETID(ProtocolCommandGroup.STREAM),

	XTRIM(ProtocolCommandGroup.STREAM),
	/**
	 * stream command end
	 **/

	/**
	 * string command start
	 **/
	APPEND(ProtocolCommandGroup.STRING),

	DECR(ProtocolCommandGroup.STRING),

	DECRBY(ProtocolCommandGroup.STRING),

	GET(ProtocolCommandGroup.STRING),

	GETRANGE(ProtocolCommandGroup.STRING),

	GETSET(ProtocolCommandGroup.STRING),

	GETEX(ProtocolCommandGroup.STRING),

	GETDEL(ProtocolCommandGroup.STRING),

	INCR(ProtocolCommandGroup.STRING),

	INCRBY(ProtocolCommandGroup.STRING),

	INCRBYFLOAT(ProtocolCommandGroup.STRING),

	MGET(ProtocolCommandGroup.STRING),

	MSET(ProtocolCommandGroup.STRING),

	MSETNX(ProtocolCommandGroup.STRING),

	PSETEX(ProtocolCommandGroup.STRING),

	SET(ProtocolCommandGroup.STRING),

	SETEX(ProtocolCommandGroup.STRING),

	SETNX(ProtocolCommandGroup.STRING),

	SETRANGE(ProtocolCommandGroup.STRING),

	STRLEN(ProtocolCommandGroup.STRING),

	SUBSTR(ProtocolCommandGroup.STRING),
	/** string command end **/

	/**
	 * transaction command start
	 **/
	DISCARD(ProtocolCommandGroup.TRANSACTION),

	EXEC(ProtocolCommandGroup.TRANSACTION),

	MULTI(ProtocolCommandGroup.TRANSACTION),

	UNWATCH(ProtocolCommandGroup.TRANSACTION),

	WATCH(ProtocolCommandGroup.TRANSACTION);

	/**
	 * transaction command end
	 **/

	private final ProtocolCommandGroup group;

	ProtocolCommand(final ProtocolCommandGroup group) {
		this.group = group;
	}

	public ProtocolCommandGroup getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return StringUtils.replace(name(), "_", " ");
	}

}

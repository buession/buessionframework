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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Rawable;
import com.buession.redis.utils.SafeEncoder;

/**
 * Redis 协议命令
 *
 * @author Yong.Teng
 */
public enum ProtocolCommand implements Rawable {
	/**
	 * bitmat command start
	 **/
	BITCOUNT(ProtocolCommandGroup.BITMAP, "r"),

	BITFIELD(ProtocolCommandGroup.BITMAP, "r"),

	BITFIELD_RO(ProtocolCommandGroup.BITMAP, "r"),

	BITOP(ProtocolCommandGroup.BITMAP, "r"),

	BITPOS(ProtocolCommandGroup.BITMAP, "r"),

	GETBIT(ProtocolCommandGroup.BITMAP, "r"),

	SETBIT(ProtocolCommandGroup.BITMAP, "w"),
	/**
	 * bitmat command end
	 **/

	/**
	 * cluster command start
	 **/
	CLUSTER_MY_ID(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_ADDSLOTS(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_SLOTS(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_COUNTFAILUREREPORTS(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_COUNTKEYSINSLOT(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_DELSLOTS(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_FLUSHSLOTS(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_FAILOVER(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_FORGET(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_GETKEYSINSLOT(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_KEYSLOT(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_SETCONFIGEPOCH(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_BUMPEPOCH(ProtocolCommandGroup.CLUSTER, "rw"),

	CLUSTER_INFO(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_MEET(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_NODES(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_SLAVES(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_REPLICAS(ProtocolCommandGroup.CLUSTER, "r"),

	CLUSTER_REPLICATE(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_RESET(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_SAVECONFIG(ProtocolCommandGroup.CLUSTER, "w"),

	CLUSTER_SETSLOT(ProtocolCommandGroup.CLUSTER, "w"),

	ASKING(ProtocolCommandGroup.CLUSTER, "r"),

	READWRITE(ProtocolCommandGroup.CLUSTER, "r"),

	READONLY(ProtocolCommandGroup.CLUSTER, "r"),
	/**
	 * cluster command end
	 **/

	/**
	 * connection command start
	 */
	AUTH(ProtocolCommandGroup.CONNECTION, "rw"),

	ECHO(ProtocolCommandGroup.CONNECTION, "w"),

	PING(ProtocolCommandGroup.CONNECTION, "r"),

	RESET(ProtocolCommandGroup.CONNECTION, "w"),

	QUIT(ProtocolCommandGroup.CONNECTION, "rw"),

	SELECT(ProtocolCommandGroup.CONNECTION, "w"),

	CLIENT_KILL(ProtocolCommandGroup.CONNECTION, "w"),

	CLIENT_SETNAME(ProtocolCommandGroup.CONNECTION, "w"),

	CLIENT_GETNAME(ProtocolCommandGroup.CONNECTION, "r"),

	CLIENT_GETREDIR(ProtocolCommandGroup.CONNECTION, "r"),

	CLIENT_CACHING(ProtocolCommandGroup.CONNECTION, "w"),

	CLIENT_ID(ProtocolCommandGroup.CONNECTION, "r"),

	CLIENT_LIST(ProtocolCommandGroup.CONNECTION, "r"),

	CLIENT_INFO(ProtocolCommandGroup.CONNECTION, "r"),

	CLIENT_PAUSE(ProtocolCommandGroup.CONNECTION, "w"),

	CLIENT_REPLY(ProtocolCommandGroup.CONNECTION, "w"),

	CLIENT_UNBLOCK(ProtocolCommandGroup.CONNECTION, "w"),
	/**
	 * connection command end
	 */

	/**
	 * geo command start
	 **/
	GEOADD(ProtocolCommandGroup.GEO, "w"),

	GEOHASH(ProtocolCommandGroup.GEO, "r"),

	GEOPOS(ProtocolCommandGroup.GEO, "r"),

	GEODIST(ProtocolCommandGroup.GEO, "r"),

	GEORADIUS(ProtocolCommandGroup.GEO, "r"),

	GEORADIUS_RO(ProtocolCommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER(ProtocolCommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER_RO(ProtocolCommandGroup.GEO, "r"),

	GEOSEARCH(ProtocolCommandGroup.GEO, "r"),

	GEOSEARCHSTORE(ProtocolCommandGroup.GEO, "rw"),
	/**
	 * geo command end
	 **/

	/**
	 * hash command start
	 **/
	HDEL(ProtocolCommandGroup.HASH, "w"),

	HEXISTS(ProtocolCommandGroup.HASH, "r"),

	HGET(ProtocolCommandGroup.HASH, "r"),

	HGETALL(ProtocolCommandGroup.HASH, "r"),

	HINCRBY(ProtocolCommandGroup.HASH, "rw"),

	HINCRBYFLOAT(ProtocolCommandGroup.HASH, "rw"),

	HKEYS(ProtocolCommandGroup.HASH, "r"),

	HLEN(ProtocolCommandGroup.HASH, "r"),

	HMGET(ProtocolCommandGroup.HASH, "r"),

	HMSET(ProtocolCommandGroup.HASH, "w"),

	HRANDFIELD(ProtocolCommandGroup.HASH, "r"),

	HSCAN(ProtocolCommandGroup.HASH, "r"),

	HSET(ProtocolCommandGroup.HASH, "w"),

	HSETNX(ProtocolCommandGroup.HASH, "w"),

	HSTRLEN(ProtocolCommandGroup.HASH, "r"),

	HVALS(ProtocolCommandGroup.HASH, "r"),
	/**
	 * hash command end
	 **/

	/**
	 * hyperloglog command start
	 **/
	PFADD(ProtocolCommandGroup.HYPERLOGLOG, "w"),

	PFCOUNT(ProtocolCommandGroup.HYPERLOGLOG, "r"),

	PFMERGE(ProtocolCommandGroup.HYPERLOGLOG, "w"),
	/**
	 * hyperloglog command end
	 **/

	/**
	 * key command start
	 **/
	COPY(ProtocolCommandGroup.KEY, "w"),

	DEL(ProtocolCommandGroup.KEY, "w"),

	DUMP(ProtocolCommandGroup.KEY, "w"),

	EXISTS(ProtocolCommandGroup.KEY, "r"),

	EXPIRE(ProtocolCommandGroup.KEY, "w"),

	EXPIREAT(ProtocolCommandGroup.KEY, "w"),

	MIGRATE(ProtocolCommandGroup.KEY, "w"),

	MOVE(ProtocolCommandGroup.KEY, "w"),

	KEYS(ProtocolCommandGroup.KEY, "r"),

	PERSIST(ProtocolCommandGroup.KEY, "w"),

	PEXPIRE(ProtocolCommandGroup.KEY, "w"),

	PEXPIREAT(ProtocolCommandGroup.KEY, "w"),

	PTTL(ProtocolCommandGroup.KEY, "r"),

	RANDOMKEY(ProtocolCommandGroup.KEY, "r"),

	RENAME(ProtocolCommandGroup.KEY, "w"),

	RENAMENX(ProtocolCommandGroup.KEY, "w"),

	RESTORE(ProtocolCommandGroup.KEY, "w"),

	SCAN(ProtocolCommandGroup.KEY, "r"),

	SORT(ProtocolCommandGroup.KEY, "rw"),

	SORT_RO(ProtocolCommandGroup.KEY, "r"),

	TTL(ProtocolCommandGroup.KEY, "r"),

	TYPE(ProtocolCommandGroup.KEY, "r"),

	TOUCH(ProtocolCommandGroup.KEY, "w"),

	UNLINK(ProtocolCommandGroup.KEY, "w"),

	OBJECT_ENCODING(ProtocolCommandGroup.KEY, "r"),

	OBJECT_REFQ(ProtocolCommandGroup.KEY, "r"),

	OBJECT_IDLETIME(ProtocolCommandGroup.KEY, "r"),

	OBJECT_REFCOUNT(ProtocolCommandGroup.KEY, "r"),
	/**
	 * key command end
	 **/

	WAIT(ProtocolCommandGroup.GENERIC, "w"),

	WAITOF(ProtocolCommandGroup.GENERIC, "w"),

	/**
	 * list command start
	 **/
	BLPOP(ProtocolCommandGroup.LIST, "rw"),

	BRPOP(ProtocolCommandGroup.LIST, "rw"),

	BRPOPLPUSH(ProtocolCommandGroup.LIST, "rw"),

	LINDEX(ProtocolCommandGroup.LIST, "r"),

	LINSERT(ProtocolCommandGroup.LIST, "w"),

	LLEN(ProtocolCommandGroup.LIST, "r"),

	LPOP(ProtocolCommandGroup.LIST, "rw"),

	LPOS(ProtocolCommandGroup.LIST, "r"),

	LPUSH(ProtocolCommandGroup.LIST, "w"),

	LPUSHX(ProtocolCommandGroup.LIST, "w"),

	LRANGE(ProtocolCommandGroup.LIST, "r"),

	LREM(ProtocolCommandGroup.LIST, "rw"),

	LSET(ProtocolCommandGroup.LIST, "w"),

	LTRIM(ProtocolCommandGroup.LIST, "rw"),

	RPOP(ProtocolCommandGroup.LIST, "rw"),

	RPOPLPUSH(ProtocolCommandGroup.LIST, "rw"),

	RPUSH(ProtocolCommandGroup.LIST, "rw"),

	RPUSHX(ProtocolCommandGroup.LIST, "rw"),

	LMOVE(ProtocolCommandGroup.LIST, "w"),

	BLMOVE(ProtocolCommandGroup.LIST, "w"),
	/**
	 * list command end
	 **/

	/**
	 * pubsub command start
	 **/
	PSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "w"),

	PUBSUB_CHANNELS(ProtocolCommandGroup.PUBSUB, "r"),

	PUBSUB_NUMPAT(ProtocolCommandGroup.PUBSUB, "r"),

	PUBSUB_NUMSUB(ProtocolCommandGroup.PUBSUB, "r"),

	PUBLISH(ProtocolCommandGroup.PUBSUB, "w"),

	PUNSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "w"),

	SUBSCRIBE(ProtocolCommandGroup.PUBSUB, "w"),

	UNSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "w"),
	/**
	 * pubsub command end
	 **/

	/**
	 * scripting command start
	 **/
	EVAL(ProtocolCommandGroup.SCRIPTING, "w"),

	EVALSHA(ProtocolCommandGroup.SCRIPTING, "w"),

	SCRIPT_EXISTS(ProtocolCommandGroup.SCRIPTING, "r"),

	SCRIPT_FLUSH(ProtocolCommandGroup.SCRIPTING, "w"),

	SCRIPT_KILL(ProtocolCommandGroup.SCRIPTING, "w"),

	SCRIPT_LOAD(ProtocolCommandGroup.SCRIPTING, "r"),
	/**
	 * scripting command end
	 **/

	/**
	 * server command start
	 **/
	ACL_CAT(ProtocolCommandGroup.SERVER, "r"),

	ACL_SETUSER(ProtocolCommandGroup.SERVER, "w"),

	ACL_GETUSER(ProtocolCommandGroup.SERVER, "r"),

	ACL_USERS(ProtocolCommandGroup.SERVER, "r"),

	ACL_WHOAMI(ProtocolCommandGroup.SERVER, "r"),

	ACL_DELUSER(ProtocolCommandGroup.SERVER, "w"),

	ACL_GENPASS(ProtocolCommandGroup.SERVER, "r"),

	ACL_LIST(ProtocolCommandGroup.SERVER, "r"),

	ACL_LOAD(ProtocolCommandGroup.SERVER, "r"),

	ACL_LOG(ProtocolCommandGroup.SERVER, "r"),

	ACL_LOGREST(ProtocolCommandGroup.SERVER, "w"),

	ACL_LOGSAVE(ProtocolCommandGroup.SERVER, "w"),

	BGREWRITEAOF(ProtocolCommandGroup.SERVER, "w"),

	BGSAVE(ProtocolCommandGroup.SERVER, "w"),

	CONFIG_GET(ProtocolCommandGroup.SERVER, "r"),

	CONFIG_RESETSTAT(ProtocolCommandGroup.SERVER, "w"),

	CONFIG_REWRITE(ProtocolCommandGroup.SERVER, "w"),

	CONFIG_SET(ProtocolCommandGroup.SERVER, "w"),

	DBSIZE(ProtocolCommandGroup.SERVER, "r"),

	DEBUG(ProtocolCommandGroup.SERVER, "w"),

	FLUSHALL(ProtocolCommandGroup.SERVER, "w"),

	FLUSHDB(ProtocolCommandGroup.SERVER, "w"),

	INFO(ProtocolCommandGroup.SERVER, "r"),

	LASTSAVE(ProtocolCommandGroup.SERVER, "w"),

	MEMORY_DOCTOR(ProtocolCommandGroup.SERVER, "w"),

	MEMORY_MALLOCSTATS(ProtocolCommandGroup.SERVER, "r"),

	MEMORY_PURGE(ProtocolCommandGroup.SERVER, "w"),

	MEMORY_STATS(ProtocolCommandGroup.SERVER, "r"),

	MEMORY_USAGE(ProtocolCommandGroup.SERVER, "r"),

	MONITOR(ProtocolCommandGroup.SERVER, "w"),

	REPLICAOF(ProtocolCommandGroup.SERVER, "w"),

	ROLE(ProtocolCommandGroup.SERVER, "r"),

	SAVE(ProtocolCommandGroup.SERVER, "w"),

	SHUTDOWN(ProtocolCommandGroup.SERVER, "w"),

	SLAVEOF(ProtocolCommandGroup.SERVER, "w"),

	SLOWLOG_GET(ProtocolCommandGroup.SERVER, "r"),

	SLOWLOG_LEN(ProtocolCommandGroup.SERVER, "r"),

	SLOWLOG_RESET(ProtocolCommandGroup.SERVER, "w"),

	SYNC(ProtocolCommandGroup.SERVER, "w"),

	PSYNC(ProtocolCommandGroup.SERVER, "w"),

	TIME(ProtocolCommandGroup.SERVER, "r"),

	FAILOVER(ProtocolCommandGroup.SERVER, "w"),

	MODULE_LIST(ProtocolCommandGroup.SERVER, "r"),

	MODULE_LOAD(ProtocolCommandGroup.SERVER, "r"),

	MODULE_UNLOAD(ProtocolCommandGroup.SERVER, "w"),

	SWAPDB(ProtocolCommandGroup.SERVER, "w"),
	/**
	 * server command end
	 **/

	/**
	 * set command start
	 **/
	SADD(ProtocolCommandGroup.SET, "w"),

	SCARD(ProtocolCommandGroup.SET, "r"),

	SDIFF(ProtocolCommandGroup.SET, "r"),

	SDIFFSTORE(ProtocolCommandGroup.SET, "r"),

	SINTER(ProtocolCommandGroup.SET, "w"),

	SINTERSTORE(ProtocolCommandGroup.SET, "w"),

	SISMEMBER(ProtocolCommandGroup.SET, "r"),

	SMISMEMBER(ProtocolCommandGroup.SET, "r"),

	SMEMBERS(ProtocolCommandGroup.SET, "r"),

	SMOVE(ProtocolCommandGroup.SET, "w"),

	SPOP(ProtocolCommandGroup.SET, "rw"),

	SRANDMEMBER(ProtocolCommandGroup.SET, "r"),

	SREM(ProtocolCommandGroup.SET, "w"),

	SSCAN(ProtocolCommandGroup.SET, "r"),

	SUNION(ProtocolCommandGroup.SET, "r"),

	SUNIONSTORE(ProtocolCommandGroup.SET, "rw"),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	ZADD(ProtocolCommandGroup.SORTEDSET, "w"),

	ZCARD(ProtocolCommandGroup.SORTEDSET, "r"),

	ZCOUNT(ProtocolCommandGroup.SORTEDSET, "r"),

	ZINCRBY(ProtocolCommandGroup.SORTEDSET, "w"),

	ZINTERSTORE(ProtocolCommandGroup.SORTEDSET, "w"),

	ZLEXCOUNT(ProtocolCommandGroup.SORTEDSET, "r"),

	ZMSCORE(ProtocolCommandGroup.SORTEDSET, "w"),

	ZRANGE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANGESTORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANK(ProtocolCommandGroup.SORTEDSET, "r"),

	ZPOPMAX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZPOPMIN(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREM(ProtocolCommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYRANK(ProtocolCommandGroup.SORTEDSET, "w"),

	ZREVRANGE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANK(ProtocolCommandGroup.SORTEDSET, "r"),

	ZSCAN(ProtocolCommandGroup.SORTEDSET, "r"),

	ZSCORE(ProtocolCommandGroup.SORTEDSET, "w"),

	ZUNIONSTORE(ProtocolCommandGroup.SORTEDSET, "w"),

	BZPOPMIN(ProtocolCommandGroup.SORTEDSET, "w"),

	BZPOPMAX(ProtocolCommandGroup.SORTEDSET, "w"),

	ZDIFF(ProtocolCommandGroup.SORTEDSET, "w"),

	ZDIFFSTORE(ProtocolCommandGroup.SORTEDSET, "w"),

	ZINTER(ProtocolCommandGroup.SORTEDSET, "w"),

	ZRANDMEMBER(ProtocolCommandGroup.SORTEDSET, "r"),

	ZUNION(ProtocolCommandGroup.SORTEDSET, "w"),
	/**
	 * sorted set command end
	 **/

	/**
	 * stream command start
	 **/
	XACK(ProtocolCommandGroup.STREAM, "w"),

	XADD(ProtocolCommandGroup.STREAM, "w"),

	XAUTOCLAIM(ProtocolCommandGroup.STREAM, "w"),

	XCLAIM(ProtocolCommandGroup.STREAM, "w"),

	XDEL(ProtocolCommandGroup.STREAM, "w"),

	XGROUP_CREATE(ProtocolCommandGroup.STREAM, "w"),

	XGROUP_CREATECONSUMER(ProtocolCommandGroup.STREAM, "w"),

	XGROUP_DELCONSUMER(ProtocolCommandGroup.STREAM, "w"),

	XGROUP_DESTROY(ProtocolCommandGroup.STREAM, "w"),

	XGROUP_SETID(ProtocolCommandGroup.STREAM, "w"),

	XINFO_CONSUMERS(ProtocolCommandGroup.STREAM, "r"),

	XINFO_GROUPS(ProtocolCommandGroup.STREAM, "r"),

	XINFO_STREAM(ProtocolCommandGroup.STREAM, "w"),

	XLEN(ProtocolCommandGroup.STREAM, "r"),

	XPENDING(ProtocolCommandGroup.STREAM, "w"),

	XRANGE(ProtocolCommandGroup.STREAM, "w"),

	XREAD(ProtocolCommandGroup.STREAM, "r"),

	XREADGROUP(ProtocolCommandGroup.STREAM, "r"),

	XREVRANGE(ProtocolCommandGroup.STREAM, "w"),

	XSETID(ProtocolCommandGroup.STREAM, "w"),

	XTRIM(ProtocolCommandGroup.STREAM, "w"),
	/**
	 * stream command end
	 **/

	/**
	 * string command start
	 **/
	APPEND(ProtocolCommandGroup.STRING, "w"),

	DECR(ProtocolCommandGroup.STRING, "w"),

	DECRBY(ProtocolCommandGroup.STRING, "w"),

	GET(ProtocolCommandGroup.STRING, "r"),

	GETRANGE(ProtocolCommandGroup.STRING, "r"),

	GETSET(ProtocolCommandGroup.STRING, "rw"),

	GETEX(ProtocolCommandGroup.STRING, "r"),

	GETDEL(ProtocolCommandGroup.STRING, "rw"),

	INCR(ProtocolCommandGroup.STRING, "w"),

	INCRBY(ProtocolCommandGroup.STRING, "w"),

	INCRBYFLOAT(ProtocolCommandGroup.STRING, "w"),

	MGET(ProtocolCommandGroup.STRING, "r"),

	MSET(ProtocolCommandGroup.STRING, "w"),

	MSETNX(ProtocolCommandGroup.STRING, "w"),

	PSETEX(ProtocolCommandGroup.STRING, "w"),

	SET(ProtocolCommandGroup.STRING, "w"),

	SETEX(ProtocolCommandGroup.STRING, "w"),

	SETNX(ProtocolCommandGroup.STRING, "w"),

	SETRANGE(ProtocolCommandGroup.STRING, "w"),

	STRLEN(ProtocolCommandGroup.STRING, "r"),

	SUBSTR(ProtocolCommandGroup.STRING, "r"),
	/** string command end **/

	/**
	 * transaction command start
	 **/
	DISCARD(ProtocolCommandGroup.TRANSACTION, "w"),

	EXEC(ProtocolCommandGroup.TRANSACTION, "w"),

	MULTI(ProtocolCommandGroup.TRANSACTION, "w"),

	UNWATCH(ProtocolCommandGroup.TRANSACTION, "w"),

	WATCH(ProtocolCommandGroup.TRANSACTION, "w");

	/**
	 * transaction command end
	 **/

	/**
	 * 命令分组
	 */
	private final ProtocolCommandGroup group;

	/**
	 * 命令名称
	 *
	 * @since 4.0.0
	 */
	private final String name;

	/**
	 * 命令名称 raw 格式
	 *
	 * @since 4.0.0
	 */
	private final byte[] raw;

	/**
	 * 是否为读操作命令
	 *
	 * @since 4.0.0
	 */
	private final boolean read;

	/**
	 * 是否为写操作命令
	 *
	 * @since 4.0.0
	 */
	private final boolean write;

	/**
	 * 构造函数
	 *
	 * @param group
	 * 		命令分组
	 * @param mode
	 * 		命令模式
	 *
	 * @since 4.0.0
	 */
	ProtocolCommand(final ProtocolCommandGroup group, final String mode) {
		this.group = group;
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

	/**
	 * 返回命令分组
	 *
	 * @return 命令分组
	 */
	public ProtocolCommandGroup getGroup() {
		return group;
	}

	/**
	 * 返回命令名称
	 *
	 * @return 命令名称
	 *
	 * @since 4.0.0
	 */
	public String getName() {
		return name;
	}

	/**
	 * 返回是否为只读命令
	 *
	 * @return true / false
	 */
	public boolean isReadonly() {
		return isRead() && isWrite() == false;
	}

	/**
	 * 返回是否为读命令
	 *
	 * @return true / false
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * 返回是否为写命令
	 *
	 * @return true / false
	 */
	public boolean isWrite() {
		return write;
	}

	@Override
	public byte[] getRaw() {
		return raw;
	}

	@Override
	public String toString() {
		return StringUtils.replace(name(), "_", " ");
	}

}

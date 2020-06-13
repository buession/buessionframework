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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.core.validator.Validate;

/**
 * @author Yong.Teng
 */
public enum ProtocolCommand {

	/**
	 * key command start
	 **/
	EXISTS(ProtocolCommandGroup.KEY, "r"),

	TYPE(ProtocolCommandGroup.KEY, "r"),

	RENAME(ProtocolCommandGroup.KEY, "w"),

	RENAMENX(ProtocolCommandGroup.KEY, "w"),

	KEYS(ProtocolCommandGroup.KEY, "r"),

	EXPIRE(ProtocolCommandGroup.KEY, "rw"),

	EXPIREAT(ProtocolCommandGroup.KEY, "rw"),

	PEXPIRE(ProtocolCommandGroup.KEY, "rw"),

	PEXPIREAT(ProtocolCommandGroup.KEY, "rw"),

	RANDOMKEY(ProtocolCommandGroup.KEY, "r"),

	TTL(ProtocolCommandGroup.KEY, "r"),

	PTTL(ProtocolCommandGroup.KEY, "r"),

	PERSIST(ProtocolCommandGroup.KEY, "rw"),

	SCAN(ProtocolCommandGroup.KEY, "r"),

	SORT(ProtocolCommandGroup.KEY, "rw"),

	DUMP(ProtocolCommandGroup.KEY, "r"),

	RESTORE(ProtocolCommandGroup.KEY, "w"),

	MIGRATE(ProtocolCommandGroup.KEY, "rw"),

	TOUCH(ProtocolCommandGroup.KEY, "rw"),

	UNLINK(ProtocolCommandGroup.KEY, "rw"),

	DEL(ProtocolCommandGroup.KEY, "rw"),

	MOVE(ProtocolCommandGroup.KEY, "rw"),
	/** key command end **/

	/**
	 * string command start
	 **/
	SET(ProtocolCommandGroup.STRING, "w"),

	SETEX(ProtocolCommandGroup.STRING, "w"),

	PSETEX(ProtocolCommandGroup.STRING, "w"),

	SETNX(ProtocolCommandGroup.STRING, "w"),

	APPEND(ProtocolCommandGroup.STRING, "rw"),

	GET(ProtocolCommandGroup.STRING, "r"),

	GETSET(ProtocolCommandGroup.STRING, "rw"),

	MSET(ProtocolCommandGroup.STRING, "w"),

	MSETNX(ProtocolCommandGroup.STRING, "w"),

	MGET(ProtocolCommandGroup.STRING, "r"),

	INCR(ProtocolCommandGroup.STRING, "rw"),

	INCRBY(ProtocolCommandGroup.STRING, "rw"),

	INCRBYFLOAT(ProtocolCommandGroup.STRING, "rw"),

	DECR(ProtocolCommandGroup.STRING, "w"),

	DECRBY(ProtocolCommandGroup.STRING, "w"),

	SETRANGE(ProtocolCommandGroup.STRING, "rw"),

	GETRANGE(ProtocolCommandGroup.STRING, "r"),

	SUBSTR(ProtocolCommandGroup.STRING, "r"),

	STRLEN(ProtocolCommandGroup.STRING, "r"),
	/** string command end **/

	/**
	 * hash command start
	 **/
	HEXISTS(ProtocolCommandGroup.HASH, "r"),

	HKEYS(ProtocolCommandGroup.HASH, "r"),

	HVALS(ProtocolCommandGroup.HASH, "r"),

	HSET(ProtocolCommandGroup.HASH, "w"),

	HSETNX(ProtocolCommandGroup.HASH, "w"),

	HGET(ProtocolCommandGroup.HASH, "r"),

	HMSET(ProtocolCommandGroup.HASH, "w"),

	HMGET(ProtocolCommandGroup.HASH, "r"),

	HGETALL(ProtocolCommandGroup.HASH, "r"),

	HDEL(ProtocolCommandGroup.HASH, "rw"),

	HSTRLEN(ProtocolCommandGroup.HASH, "r"),

	HLEN(ProtocolCommandGroup.HASH, "r"),

	HINCRBY(ProtocolCommandGroup.HASH, "rw"),

	HINCRBYFLOAT(ProtocolCommandGroup.HASH, "rw"),

	HSCAN(ProtocolCommandGroup.HASH, "r"),
	/**
	 * hash command end
	 **/

	/**
	 * list command start
	 **/
	LPUSH(ProtocolCommandGroup.LIST, "rw"),

	LPUSHX(ProtocolCommandGroup.LIST, "rw"),

	RPUSH(ProtocolCommandGroup.LIST, "rw"),

	RPUSHX(ProtocolCommandGroup.LIST, "rw"),

	LPOP(ProtocolCommandGroup.LIST, "rw"),

	RPOP(ProtocolCommandGroup.LIST, "rw"),

	RPOPLPUSH(ProtocolCommandGroup.LIST, "rw"),

	LREM(ProtocolCommandGroup.LIST, "rw"),

	LLEN(ProtocolCommandGroup.LIST, "r"),

	LINDEX(ProtocolCommandGroup.LIST, "r"),

	LINSERT(ProtocolCommandGroup.LIST, "rw"),

	LSET(ProtocolCommandGroup.LIST, "w"),

	LRANGE(ProtocolCommandGroup.LIST, "r"),

	LTRIM(ProtocolCommandGroup.LIST, "w"),

	BLPOP(ProtocolCommandGroup.LIST, "rw"),

	BRPOP(ProtocolCommandGroup.LIST, "rw"),

	BRPOPLPUSH(ProtocolCommandGroup.LIST, "rw"),
	/**
	 * list command end
	 **/

	/**
	 * set command start
	 **/
	SADD(ProtocolCommandGroup.SET, "rw"),

	SISMEMBER(ProtocolCommandGroup.SET, "r"),

	SPOP(ProtocolCommandGroup.SET, "rw"),

	SRANDMEMBER(ProtocolCommandGroup.SET, "r"),

	SREM(ProtocolCommandGroup.SET, "rw"),

	SMOVE(ProtocolCommandGroup.SET, "rw"),

	SCARD(ProtocolCommandGroup.SET, "r"),

	SMEMBERS(ProtocolCommandGroup.SET, "r"),

	SSCAN(ProtocolCommandGroup.SET, "r"),

	SINTER(ProtocolCommandGroup.SET, "r"),

	SINTERSTORE(ProtocolCommandGroup.SET, "rw"),

	SUNION(ProtocolCommandGroup.SET, "r"),

	SUNIONSTORE(ProtocolCommandGroup.SET, "rw"),

	SDIFF(ProtocolCommandGroup.SET, "r"),

	SDIFFSTORE(ProtocolCommandGroup.SET, "rw"),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	ZADD(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZINCRBY(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZCARD(ProtocolCommandGroup.SORTEDSET, "r"),

	ZCOUNT(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANGE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANGE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANK(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANK(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREM(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREMRANGEBYRANK(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREMRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZLEXCOUNT(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREMRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREVRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZSCAN(ProtocolCommandGroup.SORTEDSET, "r"),

	ZINTERSTORE(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZUNIONSTORE(ProtocolCommandGroup.SORTEDSET, "rw"),
	/**
	 * sorted set command end
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
	 * geo command start
	 **/
	GEOADD(ProtocolCommandGroup.GEO, "w"),

	GEOPOS(ProtocolCommandGroup.GEO, "r"),

	GEODIST(ProtocolCommandGroup.GEO, "r"),

	GEORADIUS(ProtocolCommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER(ProtocolCommandGroup.GEO, "r"),

	GEOHASH(ProtocolCommandGroup.GEO, "r"),
	/**
	 * geo command end
	 **/

	/**
	 * bitmap command start
	 **/
	SETBIT("rw"),

	GETBIT("r"),

	BITPOS("r"),

	BITCOUNT("r"),

	BITOP("rw"),

	BITFIELD("rw"),
	/**
	 * bitmap command end
	 **/

	/**
	 * database command start
	 **/
	DBSIZE("r"),

	FLUSHDB("w"),

	FLUSHALL("w"),
	/**
	 * database command end
	 **/

	/**
	 * transaction command start
	 **/
	MULTI("rw"),

	EXEC("rw"),

	DISCARD("rw"),

	WATCH("rw"),

	UNWATCH("rw"),
	/**
	 * transaction command end
	 **/

	/**
	 * script command start
	 **/
	EVAL(ProtocolCommandGroup.SCRIPTING, "rw"),

	EVALSHA(ProtocolCommandGroup.SCRIPTING, "rw"),

	SCRIPT_LOAD(ProtocolCommandGroup.SCRIPTING, "rw"),

	SCRIPT_EXISTS(ProtocolCommandGroup.SCRIPTING, "r"),

	SCRIPT_FLUSH(ProtocolCommandGroup.SCRIPTING, "rw"),

	SCRIPT_KILL(ProtocolCommandGroup.SCRIPTING, "rw"),
	/**
	 * script command end
	 **/

	/**
	 * persistence command start
	 **/
	SAVE("rw"),

	BGSAVE("r"),

	BGREWRITEAOF("r"),

	LASTSAVE("r"),
	/**
	 * persistence command end
	 **/

	/**
	 * pubsub command start
	 **/
	PUBLISH(ProtocolCommandGroup.PUBSUB, "rw"),

	SUBSCRIBE(ProtocolCommandGroup.PUBSUB, "rw"),

	PSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "r"),

	UNSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "rw"),

	PUNSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "rw"),

	PUBSUB(ProtocolCommandGroup.PUBSUB, "rw"),
	/**
	 * pubsub command end
	 **/

	/**
	 * connection command start
	 */
	AUTH(ProtocolCommandGroup.CONNECTION, "rw"),

	ECHO(ProtocolCommandGroup.CONNECTION, "r"),

	PING(ProtocolCommandGroup.CONNECTION, "r"),

	QUIT(ProtocolCommandGroup.CONNECTION, "rw"),

	SELECT(ProtocolCommandGroup.CONNECTION, "rw"),

	SWAPDB(ProtocolCommandGroup.CONNECTION, "w"),
	/**
	 * connection command end
	 */

	/**
	 * replication command start
	 **/
	SLAVEOF("w"),

	ROLE("r"),
	/**
	 * replication command end
	 **/

	/**
	 * server command start
	 **/
	INFO(ProtocolCommandGroup.SERVER, "r"),

	SHUTDOWN(ProtocolCommandGroup.SERVER, "rw"),

	TIME(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_GETNAME(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_ID(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_KILL(ProtocolCommandGroup.SERVER, "rw"),

	CLIENT_LIST(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_SETNAME(ProtocolCommandGroup.SERVER, "w"),
	/**
	 * server command end
	 **/

	/**
	 * config command start
	 **/
	CONFIG_SET("w"),

	CONFIG_GET("r"),

	CONFIG_RESETSTAT("w"),

	CONFIG_REWRITE("rw"),
	/**
	 * config command end
	 **/

	/**
	 * cluster command start
	 */
	CLUSTER_ADDSLOTS(ProtocolCommandGroup.CLUSTER, "rw"),

	CLUSTER_COUNTKEYSINSLOT(ProtocolCommandGroup.CLUSTER, "r"),
	/**
	 * cluster command start
	 */

	/**
	 * debug command start
	 **/
	OBJECT("r"),

	SLOWLOG("rw"),

	MONITOR("rw"),

	DEBUG_OBJECT("rw"),

	DEBUG_SEGFAULT("rw"),
	/**
	 * debug command end
	 **/

	/**
	 * internal command start
	 **/
	SYNC("rw"),

	PSYNC("rw");

	/**
	 * internal command end
	 **/

	private ProtocolCommandGroup group;

	private boolean isRead = true;

	private boolean isWrite = true;

	ProtocolCommand(final ProtocolCommandGroup group, final String mode){
		this.group = group;
		if(Validate.hasText(mode)){
			this.isRead = mode.indexOf('r') > -1;
			this.isWrite = mode.indexOf('w') > -1;
		}
	}

	public ProtocolCommandGroup getGroup(){
		return group;
	}

	public boolean isRead(){
		return isRead;
	}

	public boolean isReadOnly(){
		return isRead && isWrite == false;
	}

	public boolean isWrite(){
		return isWrite;
	}

}

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
	 * geo command start
	 **/
	GEOADD(ProtocolCommandGroup.GEO, "w"),

	GEOHASH(ProtocolCommandGroup.GEO, "r"),

	GEOPOS(ProtocolCommandGroup.GEO, "r"),

	GEODIST(ProtocolCommandGroup.GEO, "r"),

	GEORADIUS(ProtocolCommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER(ProtocolCommandGroup.GEO, "r"),
	/**
	 * geo command end
	 **/

	/**
	 * hash command start
	 **/
	HDEL(ProtocolCommandGroup.HASH, "rw"),

	HEXISTS(ProtocolCommandGroup.HASH, "r"),

	HGET(ProtocolCommandGroup.HASH, "r"),

	HGETALL(ProtocolCommandGroup.HASH, "r"),

	HINCRBY(ProtocolCommandGroup.HASH, "rw"),

	HINCRBYFLOAT(ProtocolCommandGroup.HASH, "rw"),

	HKEYS(ProtocolCommandGroup.HASH, "r"),

	HLEN(ProtocolCommandGroup.HASH, "r"),

	HMGET(ProtocolCommandGroup.HASH, "r"),

	HMSET(ProtocolCommandGroup.HASH, "w"),

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
	DEL(ProtocolCommandGroup.KEY, "rw"),

	DUMP(ProtocolCommandGroup.KEY, "r"),

	EXISTS(ProtocolCommandGroup.KEY, "r"),

	EXPIRE(ProtocolCommandGroup.KEY, "rw"),

	EXPIREAT(ProtocolCommandGroup.KEY, "rw"),

	MIGRATE(ProtocolCommandGroup.KEY, "rw"),

	MOVE(ProtocolCommandGroup.KEY, "rw"),

	KEYS(ProtocolCommandGroup.KEY, "r"),

	PERSIST(ProtocolCommandGroup.KEY, "rw"),

	PEXPIRE(ProtocolCommandGroup.KEY, "rw"),

	PEXPIREAT(ProtocolCommandGroup.KEY, "rw"),

	PTTL(ProtocolCommandGroup.KEY, "r"),

	RANDOMKEY(ProtocolCommandGroup.KEY, "r"),

	RENAME(ProtocolCommandGroup.KEY, "w"),

	RENAMENX(ProtocolCommandGroup.KEY, "w"),

	RESTORE(ProtocolCommandGroup.KEY, "w"),

	SCAN(ProtocolCommandGroup.KEY, "r"),

	SORT(ProtocolCommandGroup.KEY, "rw"),

	TTL(ProtocolCommandGroup.KEY, "r"),

	TYPE(ProtocolCommandGroup.KEY, "r"),

	TOUCH(ProtocolCommandGroup.KEY, "rw"),

	UNLINK(ProtocolCommandGroup.KEY, "rw"),
	/** key command end **/

	/**
	 * list command start
	 **/
	BLPOP(ProtocolCommandGroup.LIST, "rw"),

	BRPOP(ProtocolCommandGroup.LIST, "rw"),

	BRPOPLPUSH(ProtocolCommandGroup.LIST, "rw"),

	LINDEX(ProtocolCommandGroup.LIST, "r"),

	LINSERT(ProtocolCommandGroup.LIST, "rw"),

	LLEN(ProtocolCommandGroup.LIST, "r"),

	LPOP(ProtocolCommandGroup.LIST, "rw"),

	LPUSH(ProtocolCommandGroup.LIST, "rw"),

	LPUSHX(ProtocolCommandGroup.LIST, "rw"),

	LRANGE(ProtocolCommandGroup.LIST, "r"),

	LREM(ProtocolCommandGroup.LIST, "rw"),

	LSET(ProtocolCommandGroup.LIST, "w"),

	LTRIM(ProtocolCommandGroup.LIST, "w"),

	RPOP(ProtocolCommandGroup.LIST, "rw"),

	RPOPLPUSH(ProtocolCommandGroup.LIST, "rw"),

	RPUSH(ProtocolCommandGroup.LIST, "rw"),

	RPUSHX(ProtocolCommandGroup.LIST, "rw"),
	/**
	 * list command end
	 **/

	/**
	 * pubsub command start
	 **/
	PSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "r"),

	PUBSUB(ProtocolCommandGroup.PUBSUB, "rw"),

	PUBLISH(ProtocolCommandGroup.PUBSUB, "rw"),

	PUNSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "rw"),

	SUBSCRIBE(ProtocolCommandGroup.PUBSUB, "rw"),

	UNSUBSCRIBE(ProtocolCommandGroup.PUBSUB, "rw"),
	/**
	 * pubsub command end
	 **/

	/**
	 * scripting command start
	 **/
	EVAL(ProtocolCommandGroup.SCRIPTING, "rw"),

	EVALSHA(ProtocolCommandGroup.SCRIPTING, "rw"),

	SCRIPT_EXISTS(ProtocolCommandGroup.SCRIPTING, "r"),

	SCRIPT_FLUSH(ProtocolCommandGroup.SCRIPTING, "rw"),

	SCRIPT_KILL(ProtocolCommandGroup.SCRIPTING, "rw"),

	SCRIPT_LOAD(ProtocolCommandGroup.SCRIPTING, "rw"),
	/**
	 * scripting command end
	 **/

	/**
	 * server command start
	 **/
	BGREWRITEAOF(ProtocolCommandGroup.SERVER, "r"),

	BGSAVE(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_KILL(ProtocolCommandGroup.SERVER, "rw"),

	CLIENT_GETNAME(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_ID(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_LIST(ProtocolCommandGroup.SERVER, "r"),

	CLIENT_PAUSE(ProtocolCommandGroup.SERVER, "rw"),

	CLIENT_REPLY(ProtocolCommandGroup.SERVER, "rw"),

	CLIENT_SETNAME(ProtocolCommandGroup.SERVER, "w"),

	CLIENT_UNBLOCK(ProtocolCommandGroup.SERVER, "rw"),

	CONFIG_GET(ProtocolCommandGroup.SERVER, "r"),

	CONFIG_RESETSTAT(ProtocolCommandGroup.SERVER, "w"),

	CONFIG_REWRITE(ProtocolCommandGroup.SERVER, "rw"),

	CONFIG_SET(ProtocolCommandGroup.SERVER, "w"),

	DBSIZE(ProtocolCommandGroup.SERVER, "r"),

	DEBUG_OBJECT(ProtocolCommandGroup.SERVER, "rw"),

	DEBUG_SEGFAULT(ProtocolCommandGroup.SERVER, "rw"),

	FLUSHALL(ProtocolCommandGroup.SERVER, "w"),

	FLUSHDB(ProtocolCommandGroup.SERVER, "w"),

	INFO(ProtocolCommandGroup.SERVER, "r"),

	LASTSAVE(ProtocolCommandGroup.SERVER, "r"),

	MEMORY_DOCTOR(ProtocolCommandGroup.SERVER, "r"),

	MONITOR(ProtocolCommandGroup.SERVER, "rw"),

	OBJECT(ProtocolCommandGroup.SERVER, "r"),

	REPLICAOF(ProtocolCommandGroup.SERVER, "rw"),

	ROLE(ProtocolCommandGroup.SERVER, "r"),

	SAVE(ProtocolCommandGroup.SERVER, "rw"),

	SHUTDOWN(ProtocolCommandGroup.SERVER, "rw"),

	SLAVEOF(ProtocolCommandGroup.SERVER, "rw"),

	SLOWLOG(ProtocolCommandGroup.SERVER, "rw"),

	SYNC(ProtocolCommandGroup.SERVER, "rw"),

	PSYNC(ProtocolCommandGroup.SERVER, "rw"),

	TIME(ProtocolCommandGroup.SERVER, "r"),
	/**
	 * server command end
	 **/

	/**
	 * set command start
	 **/
	SADD(ProtocolCommandGroup.SET, "rw"),

	SCARD(ProtocolCommandGroup.SET, "r"),

	SDIFF(ProtocolCommandGroup.SET, "r"),

	SDIFFSTORE(ProtocolCommandGroup.SET, "rw"),

	SINTER(ProtocolCommandGroup.SET, "r"),

	SINTERSTORE(ProtocolCommandGroup.SET, "rw"),

	SISMEMBER(ProtocolCommandGroup.SET, "r"),

	SMEMBERS(ProtocolCommandGroup.SET, "r"),

	SMOVE(ProtocolCommandGroup.SET, "rw"),

	SPOP(ProtocolCommandGroup.SET, "rw"),

	SRANDMEMBER(ProtocolCommandGroup.SET, "r"),

	SREM(ProtocolCommandGroup.SET, "rw"),

	SSCAN(ProtocolCommandGroup.SET, "r"),

	SUNION(ProtocolCommandGroup.SET, "r"),

	SUNIONSTORE(ProtocolCommandGroup.SET, "rw"),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	ZADD(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZCARD(ProtocolCommandGroup.SORTEDSET, "r"),

	ZCOUNT(ProtocolCommandGroup.SORTEDSET, "r"),

	ZINCRBY(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZINTERSTORE(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZLEXCOUNT(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZRANGE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZRANK(ProtocolCommandGroup.SORTEDSET, "r"),

	ZPOPMAX(ProtocolCommandGroup.SORTEDSET, "r"),

	ZPOPMIN(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREM(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREMRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREMRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREMRANGEBYRANK(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREVRANGE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYLEX(ProtocolCommandGroup.SORTEDSET, "rw"),

	ZREVRANGEBYSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZREVRANK(ProtocolCommandGroup.SORTEDSET, "r"),

	ZSCAN(ProtocolCommandGroup.SORTEDSET, "r"),

	ZSCORE(ProtocolCommandGroup.SORTEDSET, "r"),

	ZUNIONSTORE(ProtocolCommandGroup.SORTEDSET, "rw"),
	/**
	 * sorted set command end
	 **/

	/**
	 * string command start
	 **/
	APPEND(ProtocolCommandGroup.STRING, "rw"),

	BITCOUNT(ProtocolCommandGroup.STRING, "r"),

	BITFIELD(ProtocolCommandGroup.STRING, "rw"),

	BITOP(ProtocolCommandGroup.STRING, "rw"),

	BITPOS(ProtocolCommandGroup.STRING, "r"),

	DECR(ProtocolCommandGroup.STRING, "w"),

	DECRBY(ProtocolCommandGroup.STRING, "w"),

	GET(ProtocolCommandGroup.STRING, "r"),

	GETBIT(ProtocolCommandGroup.STRING, "r"),

	GETRANGE(ProtocolCommandGroup.STRING, "r"),

	GETSET(ProtocolCommandGroup.STRING, "rw"),

	INCR(ProtocolCommandGroup.STRING, "rw"),

	INCRBY(ProtocolCommandGroup.STRING, "rw"),

	INCRBYFLOAT(ProtocolCommandGroup.STRING, "rw"),

	MGET(ProtocolCommandGroup.STRING, "r"),

	MSET(ProtocolCommandGroup.STRING, "w"),

	MSETNX(ProtocolCommandGroup.STRING, "w"),

	PSETEX(ProtocolCommandGroup.STRING, "w"),

	SET(ProtocolCommandGroup.STRING, "w"),

	SETBIT(ProtocolCommandGroup.STRING, "rw"),

	SETEX(ProtocolCommandGroup.STRING, "w"),

	SETNX(ProtocolCommandGroup.STRING, "w"),

	SETRANGE(ProtocolCommandGroup.STRING, "rw"),

	STRLEN(ProtocolCommandGroup.STRING, "r"),

	SUBSTR(ProtocolCommandGroup.STRING, "r"),
	/** string command end **/

	/**
	 * transaction command start
	 **/
	DISCARD(ProtocolCommandGroup.TRANSACTION, "rw"),

	EXEC(ProtocolCommandGroup.TRANSACTION, "rw"),

	MULTI(ProtocolCommandGroup.TRANSACTION, "rw"),

	UNWATCH(ProtocolCommandGroup.TRANSACTION, "rw"),

	WATCH(ProtocolCommandGroup.TRANSACTION, "rw");

	/**
	 * transaction command end
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

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
	EXISTS("r"),

	TYPE("r"),

	RENAME("w"),

	RENAMENX("w"),

	KEYS("r"),

	EXPIRE("rw"),

	EXPIREAT("rw"),

	PEXPIRE("rw"),

	PEXPIREAT("rw"),

	RANDOMKEY("r"),

	TTL("r"),

	PTTL("r"),

	PERSIST("rw"),

	SCAN("r"),

	SORT("rw"),

	DUMP("r"),

	RESTORE("w"),

	MIGRATE("rw"),

	DEL("rw"),

	MOVE("rw"),
	/** key command end **/

	/**
	 * string command start
	 **/
	SET("w"),

	SETEX("w"),

	PSETEX("w"),

	SETNX("w"),

	APPEND("rw"),

	GET("r"),

	GETSET("rw"),

	MSET("w"),

	MSETNX("w"),

	MGET("r"),

	INCR("rw"),

	INCRBY("rw"),

	INCRBYFLOAT("rw"),

	DECR("w"),

	DECRBY("w"),

	SETRANGE("rw"),

	GETRANGE("r"),

	SUBSTR("r"),

	STRLEN("r"),
	/** string command end **/

	/**
	 * hash command start
	 **/
	HEXISTS("r"),

	HKEYS("r"),

	HVALS("r"),

	HSET("w"),

	HSETNX("w"),

	HGET("r"),

	HMSET("w"),

	HMGET("r"),

	HGETALL("r"),

	HDEL("rw"),

	HSTRLEN("r"),

	HLEN("r"),

	HINCRBY("rw"),

	HINCRBYFLOAT("rw"),

	HSCAN("r"),
	/**
	 * hash command end
	 **/

	/**
	 * list command start
	 **/
	LPUSH("rw"),

	LPUSHX("rw"),

	RPUSH("rw"),

	RPUSHX("rw"),

	LPOP("rw"),

	RPOP("rw"),

	RPOPLPUSH("rw"),

	LREM("rw"),

	LLEN("r"),

	LINDEX("r"),

	LINSERT("rw"),

	LSET("w"),

	LRANGE("r"),

	LTRIM("w"),

	BLPOP("rw"),

	BRPOP("rw"),

	BRPOPLPUSH("rw"),
	/**
	 * list command end
	 **/

	/**
	 * set command start
	 **/
	SADD("rw"),

	SISMEMBER("r"),

	SPOP("rw"),

	SRANDMEMBER("r"),

	SREM("rw"),

	SMOVE("rw"),

	SCARD("r"),

	SMEMBERS("r"),

	SSCAN("r"),

	SINTER("r"),

	SINTERSTORE("rw"),

	SUNION("r"),

	SUNIONSTORE("rw"),

	SDIFF("r"),

	SDIFFSTORE("rw"),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	ZADD("rw"),

	ZSCORE("r"),

	ZINCRBY("rw"),

	ZCARD("r"),

	ZCOUNT("r"),

	ZRANGE("r"),

	ZREVRANGE("r"),

	ZRANGEBYSCORE("r"),

	ZREVRANGEBYSCORE("r"),

	ZRANK("r"),

	ZREVRANK("r"),

	ZREM("rw"),

	ZREMRANGEBYRANK("rw"),

	ZREMRANGEBYSCORE("rw"),

	ZRANGEBYLEX("rw"),

	ZLEXCOUNT("rw"),

	ZREMRANGEBYLEX("rw"),

	ZREVRANGEBYLEX("rw"),

	ZSCAN("r"),

	ZINTERSTORE("rw"),

	ZUNIONSTORE("rw"),
	/**
	 * sorted set command end
	 **/

	/**
	 * hyperloglog command start
	 **/
	PFADD("w"),

	PFCOUNT("r"),

	PFMERGE("w"),
	/**
	 * hyperloglog command end
	 **/

	/**
	 * geo command start
	 **/
	GEOADD("w"),

	GEOPOS("r"),

	GEODIST("r"),

	GEORADIUS("r"),

	GEORADIUSBYMEMBER("r"),

	GEOHASH("r"),
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

	SELECT("rw"),

	SWAPDB("w"),
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
	 * lua command start
	 **/
	EVAL("rw"),

	EVALSHA("rw"),

	SCRIPT_LOAD("rw"),

	SCRIPT_EXISTS("r"),

	SCRIPT_FLUSH("rw"),

	SCRIPT_KILL("rw"),
	/**
	 * lua command end
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
	PUBLISH("rw"),

	SUBSCRIBE("rw"),

	PSUBSCRIBE("r"),

	UNSUBSCRIBE("rw"),

	PUNSUBSCRIBE("rw"),

	PUBSUB("rw"),
	/**
	 * pubsub command end
	 **/

	/**
	 * replication command start
	 **/
	SLAVEOF("w"),

	ROLE("r"),
	/**
	 * replication command end
	 **/

	/**
	 * client and server command start
	 **/
	AUTH("rw"),

	QUIT("rw"),

	INFO("r"),

	SHUTDOWN("rw"),

	TIME("r"),

	CLIENT_GETNAME("r"),

	CLIENT_KILL("rw"),

	CLIENT_LIST("r"),

	CLIENT_SETNAME("w"),
	/**
	 * client and server command end
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
	 * debug command start
	 **/
	PING("r"),

	ECHO("r"),

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

	private boolean isRead = true;

	private boolean isWrite = true;

	ProtocolCommand(String mode){
		if(Validate.hasText(mode)){
			this.isRead = mode.indexOf('r') > -1;
			this.isWrite = mode.indexOf('w') > -1;
		}
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

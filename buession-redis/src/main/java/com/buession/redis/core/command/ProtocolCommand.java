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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

/**
 * @author Yong.Teng
 */
public enum ProtocolCommand {

    /**
     * key command start
     **/
    EXISTS,

    TYPE,

    RENAME,

    RENAMENX,

    KEYS,

    EXPIRE,

    EXPIREAT,

    PEXPIRE,

    PEXPIREAT,

    RANDOMKEY,

    TTL,

    PTTL,

    PERSIST,

    SCAN,

    SORT,

    DUMP,

    RESTORE,

    MIGRATE,

    DEL,

    MOVE,
    /** key command end **/

    /**
     * string command start
     **/
    SET,

    SETEX,

    PSETEX,

    SETNX,

    APPEND,

    GET,

    GETSET,

    MSET,

    MSETNX,

    MGET,

    INCR,

    INCRBY,

    INCRBYFLOAT,

    DECR,

    DECRBY,

    SETRANGE,

    GETRANGE,

    SUBSTR,

    STRLEN,
    /** string command end **/

    /**
     * hash command start
     **/
    HEXISTS,

    HKEYS,

    HVALS,

    HSET,

    HSETNX,

    HGET,

    HMSET,

    HMGET,

    HGETALL,

    HDEL,

    HSTRLEN,

    HLEN,

    HINCRBY,

    HINCRBYFLOAT,

    HSCAN,
    /**
     * hash command end
     **/

    /**
     * list command start
     **/
    LPUSH,

    LPUSHX,

    RPUSH,

    RPUSHX,

    LPOP,

    RPOP,

    RPOPLPUSH,

    LREM,

    LLEN,

    LINDEX,

    LINSERT,

    LSET,

    LRANGE,

    LTRIM,

    BLPOP,

    BRPOP,

    BRPOPLPUSH,
    /**
     * list command end
     **/

    /**
     * set command start
     **/
    SADD,

    SISMEMBER,

    SPOP,

    SRANDMEMBER,

    SREM,

    SMOVE,

    SCARD,

    SMEMBERS,

    SSCAN,

    SINTER,

    SINTERSTORE,

    SUNION,

    SUNIONSTORE,

    SDIFF,

    SDIFFSTORE,
    /**
     * set command end
     **/

    /**
     * sorted set command start
     **/
    ZADD,

    ZSCORE,

    ZINCRBY,

    ZCARD,

    ZCOUNT,

    ZRANGE,

    ZREVRANGE,

    ZRANGEBYSCORE,

    ZREVRANGEBYSCORE,

    ZRANK,

    ZREVRANK,

    ZREM,

    ZREMRANGEBYRANK,

    ZREMRANGEBYSCORE,

    ZRANGEBYLEX,

    ZLEXCOUNT,

    ZREMRANGEBYLEX,

    ZREVRANGEBYLEX,

    ZSCAN,

    ZINTERSTORE,

    ZUNIONSTORE,
    /**
     * sorted set command end
     **/

    /**
     * hyperloglog command start
     **/
    PFADD,

    PFCOUNT,

    PFMERGE,
    /**
     * hyperloglog command end
     **/

    /**
     * geo command start
     **/
    GEOADD,

    GEOPOS,

    GEODIST,

    GEORADIUS,

    GEORADIUSBYMEMBER,

    GEOHASH,
    /**
     * geo command end
     **/

    /**
     * bitmap command start
     **/
    SETBIT,

    GETBIT,

    BITPOS,

    BITCOUNT,

    BITOP,

    BITFIELD,
    /**
     * bitmap command end
     **/

    /**
     * database command start
     **/
    DBSIZE,

    FLUSHDB,

    FLUSHALL,

    SELECT,

    SWAPDB,
    /**
     * database command end
     **/

    /**
     * transaction command start
     **/
    MULTI,

    EXEC,

    DISCARD,

    WATCH,

    UNWATCH,
    /**
     * transaction command end
     **/

    /**
     * lua command start
     **/
    EVAL,

    EVALSHA,

    SCRIPT_LOAD,

    SCRIPT_EXISTS,

    SCRIPT_FLUSH,

    SCRIPT_KILL,
    /**
     * lua command end
     **/

    /**
     * persistence command start
     **/
    SAVE,

    BGSAVE,

    BGREWRITEAOF,

    LASTSAVE,
    /**
     * persistence command end
     **/

    /**
     * pubsub command start
     **/
    PUBLISH,

    SUBSCRIBE,

    PSUBSCRIBE,

    UNSUBSCRIBE,

    PUNSUBSCRIBE,

    PUBSUB,
    /**
     * pubsub command end
     **/

    /**
     * replication command start
     **/
    SLAVEOF,

    ROLE,
    /**
     * replication command end
     **/

    /**
     * client and server command start
     **/
    AUTH,

    QUIT,

    INFO,

    SHUTDOWN,

    TIME,

    CLIENT_GETNAME,

    CLIENT_KILL,

    CLIENT_LIST,

    CLIENT_SETNAME,
    /**
     * client and server command end
     **/

    /**
     * config command start
     **/
    CONFIG_SET,

    CONFIG_GET,

    CONFIG_RESETSTAT,

    CONFIG_REWRITE,
    /**
     * config command end
     **/

    /**
     * debug command start
     **/
    PING,

    ECHO,

    OBJECT,

    SLOWLOG,

    MONITOR,

    DEBUG_OBJECT,

    DEBUG_SEGFAULT,
    /**
     * debug command end
     **/

    /**
     * internal command start
     **/
    SYNC,

    PSYNC,
    /**
     * internal command end
     **/

    /*UNLINK,
    RENAMEX,
    SENTINEL,
    CLIENT,
    WAIT,
    CLUSTER,
    ASKING,
    READONLY,
    GEORADIUS_RO,
    GEORADIUSBYMEMBER_RO,
    MODULE,
    TOUCH;*/
}

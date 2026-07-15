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
public enum RedisCommand implements ProtocolCommand {
	/**
	 * Auto-suggest start
	 */
	FT_SUGADD(RedisCommandGroup.AUTO_SUGGEST, "FT.SUGADD", "w"),

	FT_SUGDEL(RedisCommandGroup.AUTO_SUGGEST, "FT.SUGADD", "w"),

	FT_SUGGET(RedisCommandGroup.AUTO_SUGGEST, "FT.SUGGET", "r"),

	FT_SUGLEN(RedisCommandGroup.AUTO_SUGGEST, "FT.SUGLEN", "r"),
	/**
	 * Auto-suggest end
	 */

	/**
	 * Bloom filter command start
	 */
	BF_ADD(RedisCommandGroup.BLOOM_FILTER, "BF.ADD", "w"),

	BF_CARD(RedisCommandGroup.BLOOM_FILTER, "BF.CARD", "r"),

	BF_EXISTS(RedisCommandGroup.BLOOM_FILTER, "BF.EXISTS", "r"),

	BF_INFO(RedisCommandGroup.BLOOM_FILTER, "BF.INFO", "r"),

	BF_INSERT(RedisCommandGroup.BLOOM_FILTER, "BF.INSERT", "w"),

	BF_LOADCHUNK(RedisCommandGroup.BLOOM_FILTER, "BF.LOADCHUNK", "r"),

	BF_MADD(RedisCommandGroup.BLOOM_FILTER, "BF.MADD", "w"),

	BF_MEXISTS(RedisCommandGroup.BLOOM_FILTER, "BF.MEXISTS", "r"),

	BF_RESERVE(RedisCommandGroup.BLOOM_FILTER, "BF.RESERVE", "w"),

	BF_SCANDUMP(RedisCommandGroup.BLOOM_FILTER, "BF.SCANDUMP", "w"),
	/**
	 * Bloom filter command end
	 */

	/**
	 * BitMap command start
	 */
	BITCOUNT(RedisCommandGroup.BITMAP, "r"),

	BITFIELD(RedisCommandGroup.BITMAP, "r"),

	BITFIELD_RO(RedisCommandGroup.BITMAP, "r"),

	BITOP(RedisCommandGroup.BITMAP, "r"),

	BITPOS(RedisCommandGroup.BITMAP, "r"),

	GETBIT(RedisCommandGroup.BITMAP, "r"),

	SETBIT(RedisCommandGroup.BITMAP, "w"),
	/**
	 * BitMap command end
	 */

	/**
	 * Cuckoo filter command start
	 */
	CF_ADD(RedisCommandGroup.CUCKOO_FILTER, "CF.ADD", "w"),

	CF_ADDNX(RedisCommandGroup.CUCKOO_FILTER, "CF.ADDNX", "w"),

	CF_COUNT(RedisCommandGroup.CUCKOO_FILTER, "CF.COUNT", "r"),

	CF_DEL(RedisCommandGroup.CUCKOO_FILTER, "CF.DEL", "w"),

	CF_EXISTS(RedisCommandGroup.CUCKOO_FILTER, "CF.EXISTS", "r"),

	CF_INFO(RedisCommandGroup.CUCKOO_FILTER, "CF.INFO", "r"),

	CF_INSERT(RedisCommandGroup.CUCKOO_FILTER, "CF.INSERT", "w"),

	CF_INSERTNX(RedisCommandGroup.CUCKOO_FILTER, "CF.INSERTNX", "w"),

	CF_LOADCHUNK(RedisCommandGroup.CUCKOO_FILTER, "CF.LOADCHUNK", "r"),

	CF_MEXISTS(RedisCommandGroup.CUCKOO_FILTER, "CF.MEXISTS", "r"),

	CF_RESERVE(RedisCommandGroup.CUCKOO_FILTER, "CF.RESERVE", "w"),

	CF_SCANDUMP(RedisCommandGroup.CUCKOO_FILTER, "CF.SCANDUMP", "w"),
	/**
	 * Cuckoo filter command end
	 */

	/**
	 * Cluster command start
	 **/
	ASKING(RedisCommandGroup.CLUSTER, "r"),

	CLUSTER(RedisCommandGroup.CLUSTER, "rw",
			new RedisSubCommand[]{RedisSubCommand.CLUSTER_ADDSLOTS, RedisSubCommand.CLUSTER_ADDSLOTSRANGE,
					RedisSubCommand.CLUSTER_BUMPEPOCH, RedisSubCommand.CLUSTER_COUNTFAILUREREPORTS,
					RedisSubCommand.CLUSTER_COUNTKEYSINSLOT,
					RedisSubCommand.CLUSTER_DELSLOTS, RedisSubCommand.CLUSTER_DELSLOTSRANGE,
					RedisSubCommand.CLUSTER_FAILOVER,
					RedisSubCommand.CLUSTER_FLUSHSLOTS, RedisSubCommand.CLUSTER_FORGET,
					RedisSubCommand.CLUSTER_GETKEYSINSLOT,
					RedisSubCommand.CLUSTER_INFO, RedisSubCommand.CLUSTER_KEYSLOT, RedisSubCommand.CLUSTER_LINKS,
					RedisSubCommand.CLUSTER_MEET,
					RedisSubCommand.CLUSTER_MIGRATION, RedisSubCommand.CLUSTER_MYID, RedisSubCommand.CLUSTER_MYSHARDID,
					RedisSubCommand.CLUSTER_NODES, RedisSubCommand.CLUSTER_REPLICAS, RedisSubCommand.CLUSTER_REPLICATE,
					RedisSubCommand.CLUSTER_RESET, RedisSubCommand.CLUSTER_SAVECONFIG,
					RedisSubCommand.CLUSTER_SETCONFIGEPOCH,
					RedisSubCommand.CLUSTER_SETSLOT, RedisSubCommand.CLUSTER_SHARDS, RedisSubCommand.CLUSTER_SLAVES,
					RedisSubCommand.CLUSTER_SLOT_STATS, RedisSubCommand.CLUSTER_SLOTS}),

	READONLY(RedisCommandGroup.CLUSTER, "w"),

	READWRITE(RedisCommandGroup.CLUSTER, "w"),
	/**
	 * Cluster command end
	 **/

	/**
	 * Count-min sketch command start
	 **/
	CMS_INCRBY(RedisCommandGroup.COUNT_MIN_SKETCH, "CMS.INCRBY", "w"),

	CMS_INFO(RedisCommandGroup.COUNT_MIN_SKETCH, "CMS.INFO", "r"),

	CMS_INITBYDIM(RedisCommandGroup.COUNT_MIN_SKETCH, "CMS.INITBYDIM", "r"),

	CMS_INITBYPROB(RedisCommandGroup.COUNT_MIN_SKETCH, "CMS.INITBYPROB", "r"),

	CMS_MERGE(RedisCommandGroup.COUNT_MIN_SKETCH, "CMS.MERGE", "r"),

	CMS_QUERY(RedisCommandGroup.COUNT_MIN_SKETCH, "CMS.QUERY", "r"),
	/**
	 * Count-min sketch command end
	 **/

	/**
	 * connection command start
	 */
	AUTH(RedisCommandGroup.CONNECTION, "r"),

	CLIENT(RedisCommandGroup.CONNECTION, "rw",
			new RedisSubCommand[]{RedisSubCommand.CLIENT_CACHING, RedisSubCommand.CLIENT_GETNAME,
					RedisSubCommand.CLIENT_GETREDIR,
					RedisSubCommand.CLIENT_ID, RedisSubCommand.CLIENT_INFO, RedisSubCommand.CLIENT_KILL,
					RedisSubCommand.CLIENT_LIST,
					RedisSubCommand.CLIENT_NO_EVICT, RedisSubCommand.CLIENT_NO_TOUCH, RedisSubCommand.CLIENT_PAUSE,
					RedisSubCommand.CLIENT_REPLY, RedisSubCommand.CLIENT_SETINFO, RedisSubCommand.CLIENT_SETNAME,
					RedisSubCommand.CLIENT_TRACKING, RedisSubCommand.CLIENT_TRACKINGINFO,
					RedisSubCommand.CLIENT_UNBLOCK,
					RedisSubCommand.CLIENT_UNPAUSE}),

	ECHO(RedisCommandGroup.CONNECTION, "w"),

	HELLO(RedisCommandGroup.CONNECTION, "r"),

	PING(RedisCommandGroup.CONNECTION, "r"),

	RESET(RedisCommandGroup.CONNECTION, "w"),

	QUIT(RedisCommandGroup.CONNECTION, "rw"),

	SELECT(RedisCommandGroup.CONNECTION, "w"),
	/**
	 * connection command end
	 */

	/**
	 * generic command start
	 */
	WAIT(RedisCommandGroup.GENERIC, "w"),

	WAITOF(RedisCommandGroup.GENERIC, "w"),
	/**
	 * generic command end
	 */

	/**
	 * geo command start
	 **/
	GEOADD(RedisCommandGroup.GEO, "w"),

	GEODIST(RedisCommandGroup.GEO, "r"),

	GEOHASH(RedisCommandGroup.GEO, "r"),

	GEOPOS(RedisCommandGroup.GEO, "r"),

	GEORADIUS(RedisCommandGroup.GEO, "r"),

	GEORADIUS_RO(RedisCommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER(RedisCommandGroup.GEO, "r"),

	GEORADIUSBYMEMBER_RO(RedisCommandGroup.GEO, "r"),

	GEOSEARCH(RedisCommandGroup.GEO, "r"),

	GEOSEARCHSTORE(RedisCommandGroup.GEO, "rw"),
	/**
	 * geo command end
	 **/

	/**
	 * hash command start
	 **/
	HDEL(RedisCommandGroup.HASH, "w"),

	HEXISTS(RedisCommandGroup.HASH, "r"),

	HEXPIRE(RedisCommandGroup.HASH, "w"),

	HEXPIREAT(RedisCommandGroup.HASH, "w"),

	HEXPIRETIME(RedisCommandGroup.HASH, "w"),

	HGET(RedisCommandGroup.HASH, "r"),

	HGETALL(RedisCommandGroup.HASH, "r"),

	HGETDEL(RedisCommandGroup.HASH, "rw"),

	HGETEX(RedisCommandGroup.HASH, "rw"),

	HINCRBY(RedisCommandGroup.HASH, "rw"),

	HINCRBYFLOAT(RedisCommandGroup.HASH, "rw"),

	HKEYS(RedisCommandGroup.HASH, "r"),

	HLEN(RedisCommandGroup.HASH, "r"),

	HMGET(RedisCommandGroup.HASH, "r"),

	HMSET(RedisCommandGroup.HASH, "w"),

	HPERSIST(RedisCommandGroup.HASH, "w"),

	HPEXPIRE(RedisCommandGroup.HASH, "w"),

	HPEXPIREAT(RedisCommandGroup.HASH, "w"),

	HPEXPIRETIME(RedisCommandGroup.HASH, "w"),

	HPTTL(RedisCommandGroup.HASH, "r"),

	HRANDFIELD(RedisCommandGroup.HASH, "r"),

	HSCAN(RedisCommandGroup.HASH, "r"),

	HSET(RedisCommandGroup.HASH, "w"),

	HSETEX(RedisCommandGroup.HASH, "w"),

	HSETNX(RedisCommandGroup.HASH, "w"),

	HSTRLEN(RedisCommandGroup.HASH, "r"),

	HTTL(RedisCommandGroup.HASH, "r"),

	HVALS(RedisCommandGroup.HASH, "r"),
	/**
	 * hash command end
	 **/

	/**
	 * hyperloglog command start
	 **/
	PFADD(RedisCommandGroup.HYPERLOGLOG, "w"),

	PFCOUNT(RedisCommandGroup.HYPERLOGLOG, "r"),

	PFMERGE(RedisCommandGroup.HYPERLOGLOG, "w"),

	PFSELFTEST(RedisCommandGroup.HYPERLOGLOG, "w"),
	/**
	 * hyperloglog command end
	 **/

	/**
	 * json command start
	 **/
	JSON_ARRAPPEND(RedisCommandGroup.JSON, "JSON.ARRAPPEND", "w"),

	JSON_ARRINDEX(RedisCommandGroup.JSON, "JSON.ARRINDEX", "r"),

	JSON_ARRINSERT(RedisCommandGroup.JSON, "JSON.ARRINSERT", "w"),

	JSON_ARRLEN(RedisCommandGroup.JSON, "JSON.ARRLEN", "r"),

	JSON_ARRPOP(RedisCommandGroup.JSON, "JSON.ARRPOP", "r"),

	JSON_ARRTRIM(RedisCommandGroup.JSON, "JSON.ARRTRIM", "w"),

	JSON_CLEAR(RedisCommandGroup.JSON, "JSON.CLEAR", "w"),

	JSON_DEBUG(RedisCommandGroup.JSON, "JSON.DEBUG", "r", new RedisSubCommand[]{RedisSubCommand.JSON_DEBUG_MEMORY}),

	JSON_DEL(RedisCommandGroup.JSON, "JSON.DEL", "w"),

	JSON_FORGET(RedisCommandGroup.JSON, "JSON.FORGET", "w"),

	JSON_GET(RedisCommandGroup.JSON, "JSON.GET", "r"),

	JSON_MERGE(RedisCommandGroup.JSON, "JSON.MERGE", "w"),

	JSON_MGET(RedisCommandGroup.JSON, "JSON.MGET", "r"),

	JSON_MSET(RedisCommandGroup.JSON, "JSON.MSET", "w"),

	JSON_NUMINCRBY(RedisCommandGroup.JSON, "JSON.NUMINCRBY", "w"),

	JSON_NUMMULTBY(RedisCommandGroup.JSON, "JSON.NUMMULTBY", "w"),

	JSON_OBJKEYS(RedisCommandGroup.JSON, "JSON.OBJKEYS", "r"),

	JSON_OBJLEN(RedisCommandGroup.JSON, "JSON.OBJLEN", "r"),

	JSON_RESP(RedisCommandGroup.JSON, "JSON.RESP", "r"),

	JSON_SET(RedisCommandGroup.JSON, "JSON.SET", "w"),

	JSON_STRAPPEND(RedisCommandGroup.JSON, "JSON.STRAPPEND", "w"),

	JSON_STRLEN(RedisCommandGroup.JSON, "JSON.STRLEN", "r"),

	JSON_TOGGLE(RedisCommandGroup.JSON, "JSON.TOGGLE", "w"),

	JSON_TYPE(RedisCommandGroup.JSON, "JSON.TYPE", "r"),
	/**
	 * json command end
	 **/

	/**
	 * key command start
	 **/
	COPY(RedisCommandGroup.KEY, "w"),

	DEL(RedisCommandGroup.KEY, "w"),

	DUMP(RedisCommandGroup.KEY, "r"),

	EXISTS(RedisCommandGroup.KEY, "r"),

	EXPIRE(RedisCommandGroup.KEY, "w"),

	EXPIREAT(RedisCommandGroup.KEY, "w"),

	EXPIRETIME(RedisCommandGroup.KEY, "r"),

	KEYS(RedisCommandGroup.KEY, "r"),

	MIGRATE(RedisCommandGroup.KEY, "w"),

	MOVE(RedisCommandGroup.KEY, "w"),

	OBJECT(RedisCommandGroup.KEY, "rw",
			new RedisSubCommand[]{RedisSubCommand.OBJECT_ENCODING, RedisSubCommand.OBJECT_FREQ,
					RedisSubCommand.OBJECT_IDLETIME,
					RedisSubCommand.OBJECT_REFCOUNT}),

	PERSIST(RedisCommandGroup.KEY, "w"),

	PEXPIRE(RedisCommandGroup.KEY, "w"),

	PEXPIREAT(RedisCommandGroup.KEY, "w"),

	PEXPIRETIME(RedisCommandGroup.KEY, "r"),

	PTTL(RedisCommandGroup.KEY, "r"),

	RANDOMKEY(RedisCommandGroup.KEY, "r"),

	RENAME(RedisCommandGroup.KEY, "w"),

	RENAMENX(RedisCommandGroup.KEY, "w"),

	RESTORE(RedisCommandGroup.KEY, "w"),

	SCAN(RedisCommandGroup.KEY, "r"),

	SORT(RedisCommandGroup.KEY, "rw"),

	SORT_RO(RedisCommandGroup.KEY, "SORT_RO", "r"),

	TOUCH(RedisCommandGroup.KEY, "w"),

	TTL(RedisCommandGroup.KEY, "r"),

	TYPE(RedisCommandGroup.KEY, "r"),

	UNLINK(RedisCommandGroup.KEY, "w"),
	/**
	 * key command end
	 **/

	/**
	 * list command start
	 **/
	BLMOVE(RedisCommandGroup.LIST, "w"),

	BLMPOP(RedisCommandGroup.LIST, "rw"),

	BLPOP(RedisCommandGroup.LIST, "rw"),

	BRPOP(RedisCommandGroup.LIST, "rw"),

	BRPOPLPUSH(RedisCommandGroup.LIST, "rw"),

	LINDEX(RedisCommandGroup.LIST, "r"),

	LINSERT(RedisCommandGroup.LIST, "w"),

	LLEN(RedisCommandGroup.LIST, "r"),

	LMOVE(RedisCommandGroup.LIST, "w"),

	LMPOP(RedisCommandGroup.LIST, "rw"),

	LPOP(RedisCommandGroup.LIST, "rw"),

	LPOS(RedisCommandGroup.LIST, "r"),

	LPUSH(RedisCommandGroup.LIST, "w"),

	LPUSHX(RedisCommandGroup.LIST, "w"),

	LRANGE(RedisCommandGroup.LIST, "r"),

	LREM(RedisCommandGroup.LIST, "rw"),

	LSET(RedisCommandGroup.LIST, "w"),

	LTRIM(RedisCommandGroup.LIST, "rw"),

	RPOP(RedisCommandGroup.LIST, "rw"),

	RPOPLPUSH(RedisCommandGroup.LIST, "rw"),

	RPUSH(RedisCommandGroup.LIST, "rw"),

	RPUSHX(RedisCommandGroup.LIST, "rw"),
	/**
	 * list command end
	 **/

	/**
	 * pubsub command start
	 **/
	PSUBSCRIBE(RedisCommandGroup.PUBSUB, "w"),

	PUBLISH(RedisCommandGroup.PUBSUB, "w"),

	PUBSUB(RedisCommandGroup.PUBSUB, "rw",
			new RedisSubCommand[]{RedisSubCommand.PUBSUB_CHANNELS, RedisSubCommand.PUBSUB_NUMPAT,
					RedisSubCommand.PUBSUB_NUMSUB,
					RedisSubCommand.PUBSUB_SHARDCHANNELS}),

	PUNSUBSCRIBE(RedisCommandGroup.PUBSUB, "w"),

	SPUBLISH(RedisCommandGroup.PUBSUB, "w"),

	SSUBSCRIBE(RedisCommandGroup.PUBSUB, "w"),

	SUBSCRIBE(RedisCommandGroup.PUBSUB, "w"),

	SUNSUBSCRIBE(RedisCommandGroup.PUBSUB, "w"),

	UNSUBSCRIBE(RedisCommandGroup.PUBSUB, "w"),
	/**
	 * pubsub command end
	 **/

	/**
	 * scripting command start
	 **/
	EVAL(RedisCommandGroup.SCRIPTING, "rw"),

	EVAL_RO(RedisCommandGroup.SCRIPTING, "r"),

	EVALSHA(RedisCommandGroup.SCRIPTING, "rw"),

	EVALSHA_RO(RedisCommandGroup.SCRIPTING, "r"),

	FCALL(RedisCommandGroup.SCRIPTING, "rw"),

	FCALL_RO(RedisCommandGroup.SCRIPTING, "r"),

	FUNCTION(RedisCommandGroup.SCRIPTING, "rw",
			new RedisSubCommand[]{RedisSubCommand.FUNCTION_DELETE, RedisSubCommand.FUNCTION_DUMP,
					RedisSubCommand.FUNCTION_FLUSH,
					RedisSubCommand.FUNCTION_KILL, RedisSubCommand.FUNCTION_LIST, RedisSubCommand.FUNCTION_LOAD}),

	SCRIPT(RedisCommandGroup.SCRIPTING, "rw",
			new RedisSubCommand[]{RedisSubCommand.SCRIPT_DEBUG, RedisSubCommand.SCRIPT_EXISTS,
					RedisSubCommand.SCRIPT_FLUSH,
					RedisSubCommand.SCRIPT_KILL, RedisSubCommand.SCRIPT_LOAD}),
	/**
	 * scripting command end
	 **/

	/**
	 * Query Engine command start
	 **/
	FT_LIST(RedisCommandGroup.SEARCH, "FT._LIST", "r"),

	FT_AGGREGATE(RedisCommandGroup.SEARCH, "FT.AGGREGATE", "r"),
	/**
	 * Query Engine command end
	 **/

	/**
	 * server command start
	 **/
	ACL(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.ACL_CAT, RedisSubCommand.ACL_DELUSER, RedisSubCommand.ACL_DRYRUN,
					RedisSubCommand.ACL_GENPASS,
					RedisSubCommand.ACL_GETUSER, RedisSubCommand.ACL_LIST, RedisSubCommand.ACL_LOAD,
					RedisSubCommand.ACL_LOG,
					RedisSubCommand.ACL_SAVE, RedisSubCommand.ACL_SETUSER, RedisSubCommand.ACL_USERS,
					RedisSubCommand.ACL_WHOAMI}),

	BGREWRITEAOF(RedisCommandGroup.SERVER, "w"),

	BGSAVE(RedisCommandGroup.SERVER, "w"),

	COMMAND(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.COMMAND_COUNT, RedisSubCommand.COMMAND_DOCS,
					RedisSubCommand.COMMAND_GETKEYS,
					RedisSubCommand.COMMAND_GETKEYSANDFLAGS, RedisSubCommand.COMMAND_INFO,
					RedisSubCommand.COMMAND_LIST}),

	CONFIG_GET(RedisCommandGroup.SERVER, "r"),

	CONFIG_RESETSTAT(RedisCommandGroup.SERVER, "w"),

	CONFIG_REWRITE(RedisCommandGroup.SERVER, "w"),

	CONFIG_SET(RedisCommandGroup.SERVER, "w"),

	DBSIZE(RedisCommandGroup.SERVER, "r"),

	FAILOVER(RedisCommandGroup.SERVER, "w"),

	FLUSHALL(RedisCommandGroup.SERVER, "w"),

	FLUSHDB(RedisCommandGroup.SERVER, "w"),

	HOTKEYS(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.HOTKEYS_GET, RedisSubCommand.HOTKEYS_RESET,
					RedisSubCommand.HOTKEYS_START,
					RedisSubCommand.HOTKEYS_STOP}),

	INFO(RedisCommandGroup.SERVER, "r"),

	LASTSAVE(RedisCommandGroup.SERVER, "r"),

	LATENCY(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.LATENCY_DOCTOR, RedisSubCommand.LATENCY_GRAPH,
					RedisSubCommand.LATENCY_HISTOGRAM,
					RedisSubCommand.LATENCY_HISTORY, RedisSubCommand.LATENCY_LATEST, RedisSubCommand.LATENCY_RESET}),

	LOLWUT(RedisCommandGroup.SERVER, "w"),

	MEMORY(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.MEMORY_DOCTOR, RedisSubCommand.MEMORY_MALLOC_STATS,
					RedisSubCommand.MEMORY_PURGE,
					RedisSubCommand.MEMORY_STATS, RedisSubCommand.MEMORY_USAGE}),

	MODULE(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.MODULE_LIST, RedisSubCommand.MODULE_LOAD,
					RedisSubCommand.MODULE_LOADEX,
					RedisSubCommand.MODULE_UNLOAD}),

	MONITOR(RedisCommandGroup.SERVER, "rw"),

	PSYNC(RedisCommandGroup.SERVER, "w"),

	REPLCONF(RedisCommandGroup.SERVER, "w"),

	REPLICAOF(RedisCommandGroup.SERVER, "w"),

	RESTORE_ASKING(RedisCommandGroup.SERVER, "RESTORE-ASKING", "w"),

	ROLE(RedisCommandGroup.SERVER, "r"),

	SAVE(RedisCommandGroup.SERVER, "w"),

	SHUTDOWN(RedisCommandGroup.SERVER, "w"),

	SLAVEOF(RedisCommandGroup.SERVER, "w"),

	SLOWLOG(RedisCommandGroup.SERVER, "rw",
			new RedisSubCommand[]{RedisSubCommand.SLOWLOG_GET, RedisSubCommand.SLOWLOG_LEN,
					RedisSubCommand.SLOWLOG_RESET}),

	SWAPDB(RedisCommandGroup.SERVER, "w"),

	SYNC(RedisCommandGroup.SERVER, "w"),

	TIME(RedisCommandGroup.SERVER, "r"),
	/**
	 * server command end
	 **/

	/**
	 * set command start
	 **/
	SADD(RedisCommandGroup.SET, "w"),

	SCARD(RedisCommandGroup.SET, "r"),

	SDIFF(RedisCommandGroup.SET, "r"),

	SDIFFSTORE(RedisCommandGroup.SET, "r"),

	SINTER(RedisCommandGroup.SET, "w"),

	SINTERCARD(RedisCommandGroup.SET, "r"),

	SINTERSTORE(RedisCommandGroup.SET, "w"),

	SISMEMBER(RedisCommandGroup.SET, "r"),

	SMEMBERS(RedisCommandGroup.SET, "r"),

	SMISMEMBER(RedisCommandGroup.SET, "r"),

	SMOVE(RedisCommandGroup.SET, "w"),

	SPOP(RedisCommandGroup.SET, "rw"),

	SRANDMEMBER(RedisCommandGroup.SET, "r"),

	SREM(RedisCommandGroup.SET, "w"),

	SSCAN(RedisCommandGroup.SET, "r"),

	SUNION(RedisCommandGroup.SET, "r"),

	SUNIONSTORE(RedisCommandGroup.SET, "rw"),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	BZMPOP(RedisCommandGroup.SORTEDSET, "rw"),

	BZPOPMAX(RedisCommandGroup.SORTEDSET, "w"),

	BZPOPMIN(RedisCommandGroup.SORTEDSET, "w"),

	ZADD(RedisCommandGroup.SORTEDSET, "w"),

	ZCARD(RedisCommandGroup.SORTEDSET, "r"),

	ZCOUNT(RedisCommandGroup.SORTEDSET, "r"),

	ZDIFF(RedisCommandGroup.SORTEDSET, "r"),

	ZDIFFSTORE(RedisCommandGroup.SORTEDSET, "rw"),

	ZINCRBY(RedisCommandGroup.SORTEDSET, "w"),

	ZINTER(RedisCommandGroup.SORTEDSET, "r"),

	ZINTERCARD(RedisCommandGroup.SORTEDSET, "r"),

	ZINTERSTORE(RedisCommandGroup.SORTEDSET, "rw"),

	ZLEXCOUNT(RedisCommandGroup.SORTEDSET, "r"),

	ZMPOP(RedisCommandGroup.SORTEDSET, "rw"),

	ZMSCORE(RedisCommandGroup.SORTEDSET, "r"),

	ZPOPMAX(RedisCommandGroup.SORTEDSET, "rw"),

	ZPOPMIN(RedisCommandGroup.SORTEDSET, "rw"),

	ZRANDMEMBER(RedisCommandGroup.SORTEDSET, "r"),

	ZRANGE(RedisCommandGroup.SORTEDSET, "r"),

	ZRANGEBYLEX(RedisCommandGroup.SORTEDSET, "r"),

	ZRANGEBYSCORE(RedisCommandGroup.SORTEDSET, "r"),

	ZRANGESTORE(RedisCommandGroup.SORTEDSET, "w"),

	ZRANK(RedisCommandGroup.SORTEDSET, "r"),

	ZREM(RedisCommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYLEX(RedisCommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYRANK(RedisCommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYSCORE(RedisCommandGroup.SORTEDSET, "w"),

	ZREVRANGE(RedisCommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYLEX(RedisCommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYSCORE(RedisCommandGroup.SORTEDSET, "r"),

	ZREVRANK(RedisCommandGroup.SORTEDSET, "r"),

	ZSCAN(RedisCommandGroup.SORTEDSET, "r"),

	ZSCORE(RedisCommandGroup.SORTEDSET, "r"),

	ZUNION(RedisCommandGroup.SORTEDSET, "w"),

	ZUNIONSTORE(RedisCommandGroup.SORTEDSET, "w"),
	/**
	 * sorted set command end
	 **/

	/**
	 * stream command start
	 **/
	XACK(RedisCommandGroup.STREAM, "w"),

	XACKDEL(RedisCommandGroup.STREAM, "rw"),

	XADD(RedisCommandGroup.STREAM, "w"),

	XAUTOCLAIM(RedisCommandGroup.STREAM, "w"),

	XCFGSET(RedisCommandGroup.STREAM, "w"),

	XCLAIM(RedisCommandGroup.STREAM, "w"),

	XDEL(RedisCommandGroup.STREAM, "w"),

	XDELEX(RedisCommandGroup.STREAM, "w"),

	XGROUP(RedisCommandGroup.STREAM, "rw",
			new RedisSubCommand[]{RedisSubCommand.XGROUP_CREATE, RedisSubCommand.XGROUP_CREATECONSUMER,
					RedisSubCommand.XGROUP_DELCONSUMER,
					RedisSubCommand.XGROUP_DESTROY, RedisSubCommand.XGROUP_SETID}),

	XINFO(RedisCommandGroup.STREAM, "rw",
			new RedisSubCommand[]{RedisSubCommand.XINFO_CONSUMERS, RedisSubCommand.XINFO_GROUPS,
					RedisSubCommand.XINFO_STREAM}),

	XLEN(RedisCommandGroup.STREAM, "r"),

	XPENDING(RedisCommandGroup.STREAM, "w"),

	XRANGE(RedisCommandGroup.STREAM, "w"),

	XREAD(RedisCommandGroup.STREAM, "r"),

	XREADGROUP(RedisCommandGroup.STREAM, "r"),

	XREVRANGE(RedisCommandGroup.STREAM, "w"),

	XSETID(RedisCommandGroup.STREAM, "w"),

	XTRIM(RedisCommandGroup.STREAM, "w"),
	/**
	 * stream command end
	 **/

	/**
	 * string command start
	 **/
	APPEND(RedisCommandGroup.STRING, "w"),

	DECR(RedisCommandGroup.STRING, "w"),

	DECRBY(RedisCommandGroup.STRING, "w"),

	DELEX(RedisCommandGroup.STRING, "w"),

	DIGEST(RedisCommandGroup.STRING, "w"),

	GET(RedisCommandGroup.STRING, "r"),

	GETDEL(RedisCommandGroup.STRING, "rw"),

	GETEX(RedisCommandGroup.STRING, "r"),

	GETRANGE(RedisCommandGroup.STRING, "r"),

	GETSET(RedisCommandGroup.STRING, "rw"),

	INCR(RedisCommandGroup.STRING, "w"),

	INCRBY(RedisCommandGroup.STRING, "w"),

	INCRBYFLOAT(RedisCommandGroup.STRING, "w"),

	LCS(RedisCommandGroup.STRING, "r"),

	MGET(RedisCommandGroup.STRING, "r"),

	MSET(RedisCommandGroup.STRING, "w"),

	MSETEX(RedisCommandGroup.STRING, "w"),

	MSETNX(RedisCommandGroup.STRING, "w"),

	PSETEX(RedisCommandGroup.STRING, "w"),

	SET(RedisCommandGroup.STRING, "w"),

	SETEX(RedisCommandGroup.STRING, "w"),

	SETNX(RedisCommandGroup.STRING, "w"),

	SETRANGE(RedisCommandGroup.STRING, "w"),

	STRLEN(RedisCommandGroup.STRING, "r"),

	SUBSTR(RedisCommandGroup.STRING, "r"),
	/**
	 * string command end
	 **/

	/**
	 * T-Digest command start
	 **/
	TDIGEST_ADD(RedisCommandGroup.TDIGEST, "TDIGEST.ADD", "w"),

	TDIGEST_BYRANK(RedisCommandGroup.TDIGEST, "TDIGEST.BYRANK", "r"),

	TDIGEST_BYREVRANK(RedisCommandGroup.TDIGEST, "TDIGEST.BYREVRANK", "r"),

	TDIGEST_CDF(RedisCommandGroup.TDIGEST, "TDIGEST.CDF", "r"),

	TDIGEST_CREATE(RedisCommandGroup.TDIGEST, "TDIGEST.CREATE", "w"),

	TDIGEST_INFO(RedisCommandGroup.TDIGEST, "TDIGEST.INFO", "r"),

	TDIGEST_MAX(RedisCommandGroup.TDIGEST, "TDIGEST.MAX", "r"),

	TDIGEST_MERGE(RedisCommandGroup.TDIGEST, "TDIGEST.MERGE", "w"),

	TDIGEST_MIN(RedisCommandGroup.TDIGEST, "TDIGEST.MIN", "r"),

	TDIGEST_QUANTILE(RedisCommandGroup.TDIGEST, "TDIGEST.QUANTILE", "r"),

	TDIGEST_RANK(RedisCommandGroup.TDIGEST, "TDIGEST.RANK", "r"),

	TDIGEST_RESET(RedisCommandGroup.TDIGEST, "TDIGEST.RESET", "w"),

	TDIGEST_REVRANK(RedisCommandGroup.TDIGEST, "TDIGEST.REVRANK", "r"),

	TDIGEST_TRIMMED_MEAN(RedisCommandGroup.TDIGEST, "TDIGEST.TRIMMED_MEAN", "r"),
	/**
	 * T-Digest command end
	 **/

	/**
	 * Time Series command start
	 **/
	TS_ADD(RedisCommandGroup.TIME_SERIES, "TS.ADD", "w"),

	TS_ALTER(RedisCommandGroup.TIME_SERIES, "TS.ALTER", "w"),

	TS_CREATE(RedisCommandGroup.TIME_SERIES, "TS.CREATE", "w"),

	TS_CREATERULE(RedisCommandGroup.TIME_SERIES, "TS.CREATERULE", "w"),

	TS_DECRBY(RedisCommandGroup.TIME_SERIES, "TS.DECRBY", "w"),

	TS_DEL(RedisCommandGroup.TIME_SERIES, "TS.DEL", "w"),

	TS_DELETERULE(RedisCommandGroup.TIME_SERIES, "TS.DELETERULE", "w"),

	TS_GET(RedisCommandGroup.TIME_SERIES, "TS.GET", "r"),

	TS_INCRBY(RedisCommandGroup.TIME_SERIES, "TS.INCRBY", "w"),

	TS_INFO(RedisCommandGroup.TIME_SERIES, "TS.INFO", "r"),

	TS_MADD(RedisCommandGroup.TIME_SERIES, "TS.MADD", "w"),

	TS_MGET(RedisCommandGroup.TIME_SERIES, "TS.MGET", "r"),

	TS_MRANGE(RedisCommandGroup.TIME_SERIES, "TS.MRANGE", "w"),

	TS_MREVRANGE(RedisCommandGroup.TIME_SERIES, "TS.MREVRANGE", "w"),

	TS_QUERYINDEX(RedisCommandGroup.TIME_SERIES, "TS.QUERYINDEX", "r"),

	TS_RANGE(RedisCommandGroup.TIME_SERIES, "TS.RANGE", "w"),

	TS_REVRANGE(RedisCommandGroup.TIME_SERIES, "TS.REVRANGE", "w"),
	/**
	 * Time Series command end
	 **/

	/**
	 * Top-K command start
	 **/
	TOPK_ADD(RedisCommandGroup.TOP_K, "TOPK.ADD", "w"),

	TOPK_COUNT(RedisCommandGroup.TOP_K, "TOPK.COUNT", "r"),

	TOPK_INCRBY(RedisCommandGroup.TOP_K, "TOPK.INCRBY", "w"),

	TOPK_INFO(RedisCommandGroup.TOP_K, "TOPK.INFO", "r"),

	TOPK_LIST(RedisCommandGroup.TOP_K, "TOPK.LIST", "r"),

	TOPK_QUERY(RedisCommandGroup.TOP_K, "TOPK.QUERY", "r"),

	TOPK_RESERVE(RedisCommandGroup.TOP_K, "TOPK.RESERVE", "w"),
	/**
	 * Top-K command end
	 **/

	/**
	 * transaction command start
	 **/
	DISCARD(RedisCommandGroup.TRANSACTION, "w"),

	EXEC(RedisCommandGroup.TRANSACTION, "w"),

	MULTI(RedisCommandGroup.TRANSACTION, "w"),

	UNWATCH(RedisCommandGroup.TRANSACTION, "w"),

	WATCH(RedisCommandGroup.TRANSACTION, "w"),
	/**
	 * transaction command end
	 **/

	/**
	 * Vector Set command start
	 **/
	VADD(RedisCommandGroup.VECTOR_SET, "w"),

	VCARD(RedisCommandGroup.VECTOR_SET, "r"),

	VDIM(RedisCommandGroup.VECTOR_SET, "r"),

	VEMB(RedisCommandGroup.VECTOR_SET, "r"),

	VGETATTR(RedisCommandGroup.VECTOR_SET, "r"),

	VINFO(RedisCommandGroup.VECTOR_SET, "r"),

	VISMEMBER(RedisCommandGroup.VECTOR_SET, "r"),

	VLINKS(RedisCommandGroup.VECTOR_SET, "r"),

	VRANDMEMBER(RedisCommandGroup.VECTOR_SET, "r"),

	VRANGE(RedisCommandGroup.VECTOR_SET, "r"),

	VREM(RedisCommandGroup.VECTOR_SET, "w"),

	VSETATTR(RedisCommandGroup.VECTOR_SET, "w"),

	VSIM(RedisCommandGroup.VECTOR_SET, "r");
	/**
	 * Vector Set command end
	 **/

	/**
	 * 命令分组
	 */
	private final RedisCommandGroup group;

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
	private final RedisSubCommand[] subCommands;

	/**
	 * 构造函数
	 *
	 * @param group
	 * 		命令分组
	 * @param mode
	 * 		命令模式
	 */
	RedisCommand(final RedisCommandGroup group, final String mode) {
		this(group, mode, new RedisSubCommand[0]);
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
	RedisCommand(final RedisCommandGroup group, final String mode, final RedisSubCommand[] subCommands) {
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
	RedisCommand(final RedisCommandGroup group, final String name, final String mode) {
		this(group, name, mode, new RedisSubCommand[0]);
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
	RedisCommand(final RedisCommandGroup group, final String name, final String mode,
	             final RedisSubCommand[] subCommands) {
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
	public RedisCommandGroup getGroup() {
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
	public RedisSubCommand[] getSubCommands() {
		return subCommands;
	}

	@Override
	public String toString() {
		return name + (Validate.isEmpty(subCommands) ? "" : StringUtils.join(subCommands, " "));
	}

}

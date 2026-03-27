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
	 * Auto-suggest start
	 */
	FT_SUGADD(CommandGroup.AUTO_SUGGEST, "FT.SUGADD", "w"),

	FT_SUGDEL(CommandGroup.AUTO_SUGGEST, "FT.SUGADD", "w"),

	FT_SUGGET(CommandGroup.AUTO_SUGGEST, "FT.SUGGET", "r"),

	FT_SUGLEN(CommandGroup.AUTO_SUGGEST, "FT.SUGLEN", "r"),
	/**
	 * Auto-suggest end
	 */

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

	/**
	 * key command start
	 **/
	COPY(CommandGroup.KEY, "w"),

	DEL(CommandGroup.KEY, "w"),

	DUMP(CommandGroup.KEY, "r"),

	EXISTS(CommandGroup.KEY, "r"),

	EXPIRE(CommandGroup.KEY, "w"),

	EXPIREAT(CommandGroup.KEY, "w"),

	EXPIRETIME(CommandGroup.KEY, "r"),

	KEYS(CommandGroup.KEY, "r"),

	MIGRATE(CommandGroup.KEY, "w"),

	MOVE(CommandGroup.KEY, "w"),

	OBJECT(CommandGroup.KEY, "rw",
			new SubCommand[]{SubCommand.OBJECT_ENCODING, SubCommand.OBJECT_FREQ, SubCommand.OBJECT_IDLETIME,
					SubCommand.OBJECT_REFCOUNT}),

	PERSIST(CommandGroup.KEY, "w"),

	PEXPIRE(CommandGroup.KEY, "w"),

	PEXPIREAT(CommandGroup.KEY, "w"),

	PEXPIRETIME(CommandGroup.KEY, "r"),

	PTTL(CommandGroup.KEY, "r"),

	RANDOMKEY(CommandGroup.KEY, "r"),

	RENAME(CommandGroup.KEY, "w"),

	RENAMENX(CommandGroup.KEY, "w"),

	RESTORE(CommandGroup.KEY, "w"),

	SCAN(CommandGroup.KEY, "r"),

	SORT(CommandGroup.KEY, "rw"),

	SORT_RO(CommandGroup.KEY, "SORT_RO", "r"),

	TOUCH(CommandGroup.KEY, "w"),

	TTL(CommandGroup.KEY, "r"),

	TYPE(CommandGroup.KEY, "r"),

	UNLINK(CommandGroup.KEY, "w"),
	/**
	 * key command end
	 **/

	/**
	 * list command start
	 **/
	BLMOVE(CommandGroup.LIST, "w"),

	BLMPOP(CommandGroup.LIST, "rw"),

	BLPOP(CommandGroup.LIST, "rw"),

	BRPOP(CommandGroup.LIST, "rw"),

	BRPOPLPUSH(CommandGroup.LIST, "rw"),

	LINDEX(CommandGroup.LIST, "r"),

	LINSERT(CommandGroup.LIST, "w"),

	LLEN(CommandGroup.LIST, "r"),

	LMOVE(CommandGroup.LIST, "w"),

	LMPOP(CommandGroup.LIST, "rw"),

	LPOP(CommandGroup.LIST, "rw"),

	LPOS(CommandGroup.LIST, "r"),

	LPUSH(CommandGroup.LIST, "w"),

	LPUSHX(CommandGroup.LIST, "w"),

	LRANGE(CommandGroup.LIST, "r"),

	LREM(CommandGroup.LIST, "rw"),

	LSET(CommandGroup.LIST, "w"),

	LTRIM(CommandGroup.LIST, "rw"),

	RPOP(CommandGroup.LIST, "rw"),

	RPOPLPUSH(CommandGroup.LIST, "rw"),

	RPUSH(CommandGroup.LIST, "rw"),

	RPUSHX(CommandGroup.LIST, "rw"),
	/**
	 * list command end
	 **/

	/**
	 * pubsub command start
	 **/
	PSUBSCRIBE(CommandGroup.PUBSUB, "w"),

	PUBLISH(CommandGroup.PUBSUB, "w"),

	PUBSUB(CommandGroup.PUBSUB, "rw",
			new SubCommand[]{SubCommand.PUBSUB_CHANNELS, SubCommand.PUBSUB_NUMPAT, SubCommand.PUBSUB_NUMSUB,
					SubCommand.PUBSUB_SHARDCHANNELS}),

	PUNSUBSCRIBE(CommandGroup.PUBSUB, "w"),

	SPUBLISH(CommandGroup.PUBSUB, "w"),

	SSUBSCRIBE(CommandGroup.PUBSUB, "w"),

	SUBSCRIBE(CommandGroup.PUBSUB, "w"),

	SUNSUBSCRIBE(CommandGroup.PUBSUB, "w"),

	UNSUBSCRIBE(CommandGroup.PUBSUB, "w"),
	/**
	 * pubsub command end
	 **/

	/**
	 * scripting command start
	 **/
	EVAL(CommandGroup.SCRIPTING, "rw"),

	EVAL_RO(CommandGroup.SCRIPTING, "r"),

	EVALSHA(CommandGroup.SCRIPTING, "rw"),

	EVALSHA_RO(CommandGroup.SCRIPTING, "r"),

	FCALL(CommandGroup.SCRIPTING, "rw"),

	FCALL_RO(CommandGroup.SCRIPTING, "r"),

	FUNCTION(CommandGroup.SCRIPTING, "rw",
			new SubCommand[]{SubCommand.FUNCTION_DELETE, SubCommand.FUNCTION_DUMP, SubCommand.FUNCTION_FLUSH,
					SubCommand.FUNCTION_KILL, SubCommand.FUNCTION_LIST, SubCommand.FUNCTION_LOAD}),

	SCRIPT(CommandGroup.SCRIPTING, "rw",
			new SubCommand[]{SubCommand.SCRIPT_DEBUG, SubCommand.SCRIPT_EXISTS, SubCommand.SCRIPT_FLUSH,
					SubCommand.SCRIPT_KILL, SubCommand.SCRIPT_LOAD}),
	/**
	 * scripting command end
	 **/

	/**
	 * Query Engine command start
	 **/
	FT_LIST(CommandGroup.SEARCH, "FT._LIST", "r"),

	FT_AGGREGATE(CommandGroup.SEARCH, "FT.AGGREGATE", "r"),
	/**
	 * Query Engine command end
	 **/

	/**
	 * server command start
	 **/
	ACL(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.ACL_CAT, SubCommand.ACL_DELUSER, SubCommand.ACL_DRYRUN, SubCommand.ACL_GENPASS,
					SubCommand.ACL_GETUSER, SubCommand.ACL_LIST, SubCommand.ACL_LOAD, SubCommand.ACL_LOG,
					SubCommand.ACL_SAVE, SubCommand.ACL_SETUSER, SubCommand.ACL_USERS, SubCommand.ACL_WHOAMI}),

	BGREWRITEAOF(CommandGroup.SERVER, "w"),

	BGSAVE(CommandGroup.SERVER, "w"),

	COMMAND(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.COMMAND_COUNT, SubCommand.COMMAND_DOCS, SubCommand.COMMAND_GETKEYS,
					SubCommand.COMMAND_GETKEYSANDFLAGS, SubCommand.COMMAND_INFO, SubCommand.COMMAND_LIST}),

	CONFIG_GET(CommandGroup.SERVER, "r"),

	CONFIG_RESETSTAT(CommandGroup.SERVER, "w"),

	CONFIG_REWRITE(CommandGroup.SERVER, "w"),

	CONFIG_SET(CommandGroup.SERVER, "w"),

	DBSIZE(CommandGroup.SERVER, "r"),

	FAILOVER(CommandGroup.SERVER, "w"),

	FLUSHALL(CommandGroup.SERVER, "w"),

	FLUSHDB(CommandGroup.SERVER, "w"),

	HOTKEYS(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.HOTKEYS_GET, SubCommand.HOTKEYS_RESET, SubCommand.HOTKEYS_START,
					SubCommand.HOTKEYS_STOP}),

	INFO(CommandGroup.SERVER, "r"),

	LASTSAVE(CommandGroup.SERVER, "r"),

	LATENCY(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.LATENCY_DOCTOR, SubCommand.LATENCY_GRAPH, SubCommand.LATENCY_HISTOGRAM,
					SubCommand.LATENCY_HISTORY, SubCommand.LATENCY_LATEST, SubCommand.LATENCY_RESET}),

	LOLWUT(CommandGroup.SERVER, "w"),

	MEMORY(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.MEMORY_DOCTOR, SubCommand.MEMORY_MALLOC_STATS, SubCommand.MEMORY_PURGE,
					SubCommand.MEMORY_STATS, SubCommand.MEMORY_USAGE}),

	MODULE(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.MODULE_LIST, SubCommand.MODULE_LOAD, SubCommand.MODULE_LOADEX,
					SubCommand.MODULE_UNLOAD}),

	MONITOR(CommandGroup.SERVER, "rw"),

	PSYNC(CommandGroup.SERVER, "w"),

	REPLCONF(CommandGroup.SERVER, "w"),

	REPLICAOF(CommandGroup.SERVER, "w"),

	RESTORE_ASKING(CommandGroup.SERVER, "RESTORE-ASKING", "w"),

	ROLE(CommandGroup.SERVER, "r"),

	SAVE(CommandGroup.SERVER, "w"),

	SHUTDOWN(CommandGroup.SERVER, "w"),

	SLAVEOF(CommandGroup.SERVER, "w"),

	SLOWLOG(CommandGroup.SERVER, "rw",
			new SubCommand[]{SubCommand.SLOWLOG_GET, SubCommand.SLOWLOG_LEN, SubCommand.SLOWLOG_RESET}),

	SWAPDB(CommandGroup.SERVER, "w"),

	SYNC(CommandGroup.SERVER, "w"),

	TIME(CommandGroup.SERVER, "r"),
	/**
	 * server command end
	 **/

	/**
	 * set command start
	 **/
	SADD(CommandGroup.SET, "w"),

	SCARD(CommandGroup.SET, "r"),

	SDIFF(CommandGroup.SET, "r"),

	SDIFFSTORE(CommandGroup.SET, "r"),

	SINTER(CommandGroup.SET, "w"),

	SINTERCARD(CommandGroup.SET, "r"),

	SINTERSTORE(CommandGroup.SET, "w"),

	SISMEMBER(CommandGroup.SET, "r"),

	SMEMBERS(CommandGroup.SET, "r"),

	SMISMEMBER(CommandGroup.SET, "r"),

	SMOVE(CommandGroup.SET, "w"),

	SPOP(CommandGroup.SET, "rw"),

	SRANDMEMBER(CommandGroup.SET, "r"),

	SREM(CommandGroup.SET, "w"),

	SSCAN(CommandGroup.SET, "r"),

	SUNION(CommandGroup.SET, "r"),

	SUNIONSTORE(CommandGroup.SET, "rw"),
	/**
	 * set command end
	 **/

	/**
	 * sorted set command start
	 **/
	BZMPOP(CommandGroup.SORTEDSET, "rw"),

	BZPOPMAX(CommandGroup.SORTEDSET, "w"),

	BZPOPMIN(CommandGroup.SORTEDSET, "w"),

	ZADD(CommandGroup.SORTEDSET, "w"),

	ZCARD(CommandGroup.SORTEDSET, "r"),

	ZCOUNT(CommandGroup.SORTEDSET, "r"),

	ZDIFF(CommandGroup.SORTEDSET, "r"),

	ZDIFFSTORE(CommandGroup.SORTEDSET, "rw"),

	ZINCRBY(CommandGroup.SORTEDSET, "w"),

	ZINTER(CommandGroup.SORTEDSET, "r"),

	ZINTERCARD(CommandGroup.SORTEDSET, "r"),

	ZINTERSTORE(CommandGroup.SORTEDSET, "rw"),

	ZLEXCOUNT(CommandGroup.SORTEDSET, "r"),

	ZMPOP(CommandGroup.SORTEDSET, "rw"),

	ZMSCORE(CommandGroup.SORTEDSET, "r"),

	ZPOPMAX(CommandGroup.SORTEDSET, "rw"),

	ZPOPMIN(CommandGroup.SORTEDSET, "rw"),

	ZRANDMEMBER(CommandGroup.SORTEDSET, "r"),

	ZRANGE(CommandGroup.SORTEDSET, "r"),

	ZRANGEBYLEX(CommandGroup.SORTEDSET, "r"),

	ZRANGEBYSCORE(CommandGroup.SORTEDSET, "r"),

	ZRANGESTORE(CommandGroup.SORTEDSET, "w"),

	ZRANK(CommandGroup.SORTEDSET, "r"),

	ZREM(CommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYLEX(CommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYRANK(CommandGroup.SORTEDSET, "w"),

	ZREMRANGEBYSCORE(CommandGroup.SORTEDSET, "w"),

	ZREVRANGE(CommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYLEX(CommandGroup.SORTEDSET, "r"),

	ZREVRANGEBYSCORE(CommandGroup.SORTEDSET, "r"),

	ZREVRANK(CommandGroup.SORTEDSET, "r"),

	ZSCAN(CommandGroup.SORTEDSET, "r"),

	ZSCORE(CommandGroup.SORTEDSET, "r"),

	ZUNION(CommandGroup.SORTEDSET, "w"),

	ZUNIONSTORE(CommandGroup.SORTEDSET, "w"),
	/**
	 * sorted set command end
	 **/

	/**
	 * stream command start
	 **/
	XACK(CommandGroup.STREAM, "w"),

	XACKDEL(CommandGroup.STREAM, "rw"),

	XADD(CommandGroup.STREAM, "w"),

	XAUTOCLAIM(CommandGroup.STREAM, "w"),

	XCFGSET(CommandGroup.STREAM, "w"),

	XCLAIM(CommandGroup.STREAM, "w"),

	XDEL(CommandGroup.STREAM, "w"),

	XDELEX(CommandGroup.STREAM, "w"),

	XGROUP(CommandGroup.STREAM, "rw",
			new SubCommand[]{SubCommand.XGROUP_CREATE, SubCommand.XGROUP_CREATECONSUMER, SubCommand.XGROUP_DELCONSUMER,
					SubCommand.XGROUP_DESTROY, SubCommand.XGROUP_SETID}),

	XINFO(CommandGroup.STREAM, "rw",
			new SubCommand[]{SubCommand.XINFO_CONSUMERS, SubCommand.XINFO_GROUPS, SubCommand.XINFO_STREAM}),

	XLEN(CommandGroup.STREAM, "r"),

	XPENDING(CommandGroup.STREAM, "w"),

	XRANGE(CommandGroup.STREAM, "w"),

	XREAD(CommandGroup.STREAM, "r"),

	XREADGROUP(CommandGroup.STREAM, "r"),

	XREVRANGE(CommandGroup.STREAM, "w"),

	XSETID(CommandGroup.STREAM, "w"),

	XTRIM(CommandGroup.STREAM, "w"),
	/**
	 * stream command end
	 **/

	/**
	 * string command start
	 **/
	APPEND(CommandGroup.STRING, "w"),

	DECR(CommandGroup.STRING, "w"),

	DECRBY(CommandGroup.STRING, "w"),

	DELEX(CommandGroup.STRING, "w"),

	DIGEST(CommandGroup.STRING, "w"),

	GET(CommandGroup.STRING, "r"),

	GETDEL(CommandGroup.STRING, "rw"),

	GETEX(CommandGroup.STRING, "r"),

	GETRANGE(CommandGroup.STRING, "r"),

	GETSET(CommandGroup.STRING, "rw"),

	INCR(CommandGroup.STRING, "w"),

	INCRBY(CommandGroup.STRING, "w"),

	INCRBYFLOAT(CommandGroup.STRING, "w"),

	LCS(CommandGroup.STRING, "r"),

	MGET(CommandGroup.STRING, "r"),

	MSET(CommandGroup.STRING, "w"),

	MSETEX(CommandGroup.STRING, "w"),

	MSETNX(CommandGroup.STRING, "w"),

	PSETEX(CommandGroup.STRING, "w"),

	SET(CommandGroup.STRING, "w"),

	SETEX(CommandGroup.STRING, "w"),

	SETNX(CommandGroup.STRING, "w"),

	SETRANGE(CommandGroup.STRING, "w"),

	STRLEN(CommandGroup.STRING, "r"),

	SUBSTR(CommandGroup.STRING, "r"),
	/**
	 * string command end
	 **/

	/**
	 * T-Digest command start
	 **/
	TDIGEST_ADD(CommandGroup.TDIGEST, "TDIGEST.ADD", "w"),

	TDIGEST_BYRANK(CommandGroup.TDIGEST, "TDIGEST.BYRANK", "r"),

	TDIGEST_BYREVRANK(CommandGroup.TDIGEST, "TDIGEST.BYREVRANK", "r"),

	TDIGEST_CDF(CommandGroup.TDIGEST, "TDIGEST.CDF", "r"),

	TDIGEST_CREATE(CommandGroup.TDIGEST, "TDIGEST.CREATE", "w"),

	TDIGEST_INFO(CommandGroup.TDIGEST, "TDIGEST.INFO", "r"),

	TDIGEST_MAX(CommandGroup.TDIGEST, "TDIGEST.MAX", "r"),

	TDIGEST_MERGE(CommandGroup.TDIGEST, "TDIGEST.MERGE", "w"),

	TDIGEST_MIN(CommandGroup.TDIGEST, "TDIGEST.MIN", "r"),

	TDIGEST_QUANTILE(CommandGroup.TDIGEST, "TDIGEST.QUANTILE", "r"),

	TDIGEST_RANK(CommandGroup.TDIGEST, "TDIGEST.RANK", "r"),

	TDIGEST_RESET(CommandGroup.TDIGEST, "TDIGEST.RESET", "w"),

	TDIGEST_REVRANK(CommandGroup.TDIGEST, "TDIGEST.REVRANK", "r"),

	TDIGEST_TRIMMED_MEAN(CommandGroup.TDIGEST, "TDIGEST.TRIMMED_MEAN", "r"),
	/**
	 * T-Digest command end
	 **/

	/**
	 * Time Series command start
	 **/
	TS_ADD(CommandGroup.TIME_SERIES, "TS.ADD", "w"),

	TS_ALTER(CommandGroup.TIME_SERIES, "TS.ALTER", "w"),

	TS_CREATE(CommandGroup.TIME_SERIES, "TS.CREATE", "w"),

	TS_CREATERULE(CommandGroup.TIME_SERIES, "TS.CREATERULE", "w"),

	TS_DECRBY(CommandGroup.TIME_SERIES, "TS.DECRBY", "w"),

	TS_DEL(CommandGroup.TIME_SERIES, "TS.DEL", "w"),

	TS_DELETERULE(CommandGroup.TIME_SERIES, "TS.DELETERULE", "w"),

	TS_GET(CommandGroup.TIME_SERIES, "TS.GET", "r"),

	TS_INCRBY(CommandGroup.TIME_SERIES, "TS.INCRBY", "w"),

	TS_INFO(CommandGroup.TIME_SERIES, "TS.INFO", "r"),

	TS_MADD(CommandGroup.TIME_SERIES, "TS.MADD", "w"),

	TS_MGET(CommandGroup.TIME_SERIES, "TS.MGET", "r"),

	TS_MRANGE(CommandGroup.TIME_SERIES, "TS.MRANGE", "w"),

	TS_MREVRANGE(CommandGroup.TIME_SERIES, "TS.MREVRANGE", "w"),

	TS_QUERYINDEX(CommandGroup.TIME_SERIES, "TS.QUERYINDEX", "r"),

	TS_RANGE(CommandGroup.TIME_SERIES, "TS.RANGE", "w"),

	TS_REVRANGE(CommandGroup.TIME_SERIES, "TS.REVRANGE", "w"),
	/**
	 * Time Series command end
	 **/

	/**
	 * Top-K command start
	 **/
	TOPK_ADD(CommandGroup.TOP_K, "TOPK.ADD", "w"),

	TOPK_COUNT(CommandGroup.TOP_K, "TOPK.COUNT", "r"),

	TOPK_INCRBY(CommandGroup.TOP_K, "TOPK.INCRBY", "w"),

	TOPK_INFO(CommandGroup.TOP_K, "TOPK.INFO", "r"),

	TOPK_LIST(CommandGroup.TOP_K, "TOPK.LIST", "r"),

	TOPK_QUERY(CommandGroup.TOP_K, "TOPK.QUERY", "r"),

	TOPK_RESERVE(CommandGroup.TOP_K, "TOPK.RESERVE", "w"),
	/**
	 * Top-K command end
	 **/

	/**
	 * transaction command start
	 **/
	DISCARD(CommandGroup.TRANSACTION, "w"),

	EXEC(CommandGroup.TRANSACTION, "w"),

	MULTI(CommandGroup.TRANSACTION, "w"),

	UNWATCH(CommandGroup.TRANSACTION, "w"),

	WATCH(CommandGroup.TRANSACTION, "w"),
	/**
	 * transaction command end
	 **/

	/**
	 * Vector Set command start
	 **/
	VADD(CommandGroup.VECTOR_SET, "w"),

	VCARD(CommandGroup.VECTOR_SET, "r"),

	VDIM(CommandGroup.VECTOR_SET, "r"),

	VEMB(CommandGroup.VECTOR_SET, "r"),

	VGETATTR(CommandGroup.VECTOR_SET, "r"),

	VINFO(CommandGroup.VECTOR_SET, "r"),

	VISMEMBER(CommandGroup.VECTOR_SET, "r"),

	VLINKS(CommandGroup.VECTOR_SET, "r"),

	VRANDMEMBER(CommandGroup.VECTOR_SET, "r"),

	VRANGE(CommandGroup.VECTOR_SET, "r"),

	VREM(CommandGroup.VECTOR_SET, "w"),

	VSETATTR(CommandGroup.VECTOR_SET, "w"),

	VSIM(CommandGroup.VECTOR_SET, "r");
	/**
	 * Vector Set command end
	 **/

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

/**
 * @author Yong.Teng
 */
public class JedisClient extends AbstractJedisRedisClient<Jedis> implements SimpleRedisClient {

	@Override
	public Set<String> sInter(final String... keys){
		return execute(ProtocolCommand.SINTER, (Jedis client)->client.sinter(keys),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute(ProtocolCommand.SINTER, (Jedis client)->client.sinter(keys),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute(ProtocolCommand.SINTERSTORE, (Jedis client)->client.sinterstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute(ProtocolCommand.SINTERSTORE, (Jedis client)->client.sinterstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute(ProtocolCommand.SUNION, (Jedis client)->client.sunion(keys),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute(ProtocolCommand.SUNION, (Jedis client)->client.sunion(keys),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return execute(ProtocolCommand.SUNIONSTORE, (Jedis client)->client.sunionstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return execute(ProtocolCommand.SUNIONSTORE, (Jedis client)->client.sunionstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		return execute(ProtocolCommand.SMOVE, (Jedis client)->ReturnUtils.returnStatus(client.smove(source, destKey,
				member) > 0),
				OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("member",
						member));
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		return execute(ProtocolCommand.SMOVE, (Jedis client)->ReturnUtils.returnStatus(client.smove(source, destKey,
				member) > 0),
				OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("member",
						member));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute(ProtocolCommand.SSCAN,
				(Jedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key,
						cursor)), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(ProtocolCommand.SSCAN,
				(Jedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key,
						cursor, (new ScanParams()).match(pattern))), OperationsCommandArguments.getInstance().put("key"
						, key).put("cursor", cursor).put("pattern", pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		return execute(ProtocolCommand.SSCAN,
				(Jedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key,
						cursor, (new ScanParams()).count(count))), OperationsCommandArguments.getInstance().put("key",
						key).put("cursor", cursor).put("count", count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute(ProtocolCommand.SSCAN,
				(Jedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key,
						cursor, (new ScanParams()).match(pattern).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		return execute(ProtocolCommand.ZADD, (Jedis client)->client.zadd(key,
				(new MapNumberConvert.MapNumberDoubleConvert<byte[]>()).convert(members)),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return execute(ProtocolCommand.ZSCORE, (Jedis client)->client.zscore(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute(ProtocolCommand.ZCARD, (Jedis client)->client.zcard(key),
				OperationsCommandArguments.getInstance().put("key", key));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		return execute(ProtocolCommand.ZINCRBY, (Jedis client)->client.zincrby(key, increment, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("increment",
						increment));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZCOUNT, (Jedis client)->client.zcount(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZRANGE, (Jedis client)->client.zrange(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZRANGE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeWithScores(key, start,
						end)), OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end",
						end));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (Jedis client)->client.zrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (Jedis client)->client.zrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (Jedis client)->client.zrangeByScore(key, min, max, offset,
				count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put(
						"offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (Jedis client)->client.zrangeByScore(key, min, max, offset,
				count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put(
						"offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min
						, max)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
						max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min
						, max)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
						max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min
						, max, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("min",
						min).put("max", max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min
						, max, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("min",
						min).put("max", max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZRANGEBYLEX, (Jedis client)->client.zrangeByLex(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYLEX, (Jedis client)->client.zrangeByLex(key, min, max, offset, count),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return execute(ProtocolCommand.ZRANK, (Jedis client)->client.zrank(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return execute(ProtocolCommand.ZREVRANK, (Jedis client)->client.zrevrank(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return execute(ProtocolCommand.ZREM, (Jedis client)->client.zrem(key, members),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZREMRANGEBYRANK, (Jedis client)->client.zremrangeByRank(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZREMRANGEBYSCORE, (Jedis client)->client.zremrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREMRANGEBYSCORE, (Jedis client)->client.zremrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREMRANGEBYLEX, (Jedis client)->client.zremrangeByLex(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZREVRANGE, (Jedis client)->client.zrevrange(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZREVRANGE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeWithScores(key, start,
						end)), OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end",
						end));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (Jedis client)->client.zrevrangeByScore(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (Jedis client)->client.zrevrangeByScore(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (Jedis client)->client.zrevrangeByScore(key, max, min, offset
				, count),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (Jedis client)->client.zrevrangeByScore(key, max, min, offset
				, count),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key,
						max, min)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max"
						, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key,
						max, min)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max"
						, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key,
						max, min, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("max",
						max).put("min", min).put("offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(Jedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key,
						max, min, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("max",
						max).put("min", min).put("offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREVRANGEBYLEX, (Jedis client)->client.zrevrangeByLex(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYLEX, (Jedis client)->client.zrevrangeByLex(key, max, min, offset,
				count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put(
						"offset", offset).put("count", count));
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZLEXCOUNT, (Jedis client)->client.zlexcount(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys"
						, keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys"
						, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey,
				(new ZParams()).weights(weights), keys), OperationsCommandArguments.getInstance().put("destKey",
				destKey).put("weights", weights).put("keys", keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey,
				(new ZParams()).weights(weights), keys), OperationsCommandArguments.getInstance().put("destKey",
				destKey).put("weights", weights).put("keys", keys));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)).weights(weights), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
						keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute(ProtocolCommand.ZINTERSTORE, (Jedis client)->client.zinterstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)).weights(weights), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
						keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey, keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys"
						, keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys"
						, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey,
				(new ZParams()).weights(weights), keys), OperationsCommandArguments.getInstance().put("destKey",
				destKey).put("weights", weights).put("keys", keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey,
				(new ZParams()).weights(weights), keys), OperationsCommandArguments.getInstance().put("destKey",
				destKey).put("weights", weights).put("keys", keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)).weights(weights), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
						keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute(ProtocolCommand.ZUNIONSTORE, (Jedis client)->client.zunionstore(destKey,
				(new ZParams()).aggregate((new AggregateConvert()).convert(aggregate)).weights(weights), keys),
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
						keys));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return execute(ProtocolCommand.ZSCAN,
				(Jedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key,
						cursor)), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(ProtocolCommand.ZSCAN,
				(Jedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key,
						cursor, (new ScanParams()).match(pattern))), OperationsCommandArguments.getInstance().put("key"
						, key).put("cursor", cursor).put("pattern", pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		return execute(ProtocolCommand.ZSCAN,
				(Jedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key,
						cursor, (new ScanParams()).count(count))), OperationsCommandArguments.getInstance().put("key",
						key).put("cursor", cursor).put("count", count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		return execute(ProtocolCommand.ZSCAN,
				(Jedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key,
						cursor, (new ScanParams()).match(pattern).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return execute(ProtocolCommand.PFADD,
				(Jedis client)->ReturnUtils.returnStatus(client.pfadd(key, elements) > 0),
				OperationsCommandArguments.getInstance().put("key", key).put("elements", elements));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		return execute(ProtocolCommand.PFMERGE, (Jedis client)->ReturnUtils.returnForOK(client.pfmerge(destKey, keys))
				, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		return execute(ProtocolCommand.PFMERGE, (Jedis client)->ReturnUtils.returnForOK(client.pfmerge(destKey, keys))
				, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys));
	}

	@Override
	public Long pfCount(final String... keys){
		return execute(ProtocolCommand.PFCOUNT, (Jedis client)->client.pfcount(keys),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Long pfCount(final byte[]... keys){
		return execute(ProtocolCommand.PFCOUNT, (Jedis client)->client.pfcount(keys),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return execute(ProtocolCommand.GEOADD, (Jedis client)->client.geoadd(key, longitude, latitude, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("longitude",
						longitude).put("latitude", latitude));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return execute(ProtocolCommand.GEOADD, (Jedis client)->client.geoadd(key,
				(new GeoConvert.GeoMapConvert<byte[]>()).convert(memberCoordinates)),
				OperationsCommandArguments.getInstance().put("key", key).put("memberCoordinates", memberCoordinates));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return execute(ProtocolCommand.GEOPOS,
				(Jedis client)->(new GeoConvert.ListMapConvert()).deconvert(client.geopos(key, members)),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return execute(ProtocolCommand.GEODIST, (Jedis client)->client.geodist(key, member1, member2),
				OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2",
						member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return execute(ProtocolCommand.GEODIST, (Jedis client)->client.geodist(key, member1, member2,
				(new GeoConvert.GeoUnitConvert()).convert(unit)), OperationsCommandArguments.getInstance().put("key",
				key).put("member1", member1).put("member2", member2).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute(ProtocolCommand.GEORADIUS,
				(Jedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadius(key, longitude, latitude, radius, (new GeoConvert.GeoUnitConvert()).convert(unit))), OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		return execute(ProtocolCommand.GEORADIUS,
				(Jedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadius(key, longitude, latitude, radius, (new GeoConvert.GeoUnitConvert()).convert(unit), (new GeoArgumentConvert()).convert(geoArgument))), OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		return execute(ProtocolCommand.GEORADIUSBYMEMBER,
				(Jedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadiusByMember(key, member, radius, (new GeoConvert.GeoUnitConvert()).convert(unit))), OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute(ProtocolCommand.PFMERGE,
				(Jedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadiusByMember(key, member, radius, (new GeoConvert.GeoUnitConvert()).convert(unit), (new GeoArgumentConvert()).convert(geoArgument))), OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return execute(ProtocolCommand.PFMERGE, (Jedis client)->client.geohash(key, members),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		return execute(ProtocolCommand.SETBIT, (Jedis client)->ReturnUtils.returnStatus(client.setbit(key, offset,
				value)), OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value",
				value));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		return execute(ProtocolCommand.SETBIT, (Jedis client)->ReturnUtils.returnStatus(client.setbit(key, offset,
				value)), OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value",
				value));
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		return execute(ProtocolCommand.GETBIT, (Jedis client)->ReturnUtils.returnStatus(client.getbit(key, offset)),
				OperationsCommandArguments.getInstance().put("key", key).put("offset", offset));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		return execute(ProtocolCommand.BITPOS, (Jedis client)->client.bitpos(key, value),
				OperationsCommandArguments.getInstance().put("key", key).put("value", value));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		return execute(ProtocolCommand.BITPOS, (Jedis client)->client.bitpos(key, value, new BitPosParams(start, end))
				,
				OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put(
						"end", end));
	}

	@Override
	public Long bitOp(final Operation operation, final String destKey, final String... keys){
		return execute(ProtocolCommand.BITOP,
				(Jedis client)->client.bitop((new BitOperationConvert()).convert(operation), destKey, keys),
				OperationsCommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys"
						, keys));
	}

	@Override
	public Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
		return execute(ProtocolCommand.BITOP,
				(Jedis client)->client.bitop((new BitOperationConvert()).convert(operation), destKey, keys),
				OperationsCommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys"
						, keys));
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return execute(ProtocolCommand.BITFIELD, (Jedis client)->client.bitfield(key, arguments),
				OperationsCommandArguments.getInstance().put("key", key).put("arguments", arguments));
	}

	@Override
	public Long bitCount(final byte[] key){
		return execute(ProtocolCommand.BITCOUNT, (Jedis client)->client.bitcount(key),
				OperationsCommandArguments.getInstance().put("key", key));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.BITCOUNT, (Jedis client)->client.bitcount(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Transaction multi(){
		return execute(ProtocolCommand.MULTI, (Jedis client)->new JedisTransaction(client.multi()));
	}

	@Override
	public void exec(final Transaction transaction){
		execute(ProtocolCommand.EXEC, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				transaction.exec();
				return null;
			}

		});
	}

	@Override
	public void discard(final Transaction transaction){
		execute(ProtocolCommand.DISCARD, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				transaction.discard();
				return null;
			}

		});
	}

	@Override
	public Status watch(final String... keys){
		return execute(ProtocolCommand.WATCH, (Jedis client)->ReturnUtils.returnForOK(client.watch(keys)),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute(ProtocolCommand.WATCH, (Jedis client)->ReturnUtils.returnForOK(client.watch(keys)),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Status unwatch(){
		return execute(ProtocolCommand.UNWATCH, (Jedis client)->ReturnUtils.returnForOK(client.unwatch()));
	}

	@Override
	public Long publish(final String channel, final String message){
		return execute(ProtocolCommand.PUBLISH, (Jedis client)->client.publish(channel, message),
				OperationsCommandArguments.getInstance().put("channel", channel).put("message", message));
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		return execute(ProtocolCommand.PUBLISH, (Jedis client)->client.publish(channel, message),
				OperationsCommandArguments.getInstance().put("channel", channel).put("message", message));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		execute(ProtocolCommand.SUBSCRIBE, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
				return null;
			}

		}, OperationsCommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener));
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		execute(ProtocolCommand.SUBSCRIBE, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
				return null;
			}

		}, OperationsCommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener));
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		execute(ProtocolCommand.PSUBSCRIBE, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);

				return null;
			}

		}, OperationsCommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener));
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		execute(ProtocolCommand.PSUBSCRIBE, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);

				return null;
			}

		}, OperationsCommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener));
	}

	@Override
	public Status select(final int db){
		return execute(ProtocolCommand.SELECT, (Jedis client)->ReturnUtils.returnForOK(client.select(db)),
				OperationsCommandArguments.getInstance().put("db", db));
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		return execute(ProtocolCommand.SWAPDB, (Jedis client)->ReturnUtils.returnForOK(client.swapDB(db1, db2)),
				OperationsCommandArguments.getInstance().put("db1", db1).put("db2", db2));
	}

	@Override
	public Long dbSize(){
		return execute(ProtocolCommand.DBSIZE, (Jedis client)->client.dbSize());
	}

	@Override
	public Status flushDb(){
		return execute(ProtocolCommand.FLUSHDB, (Jedis client)->ReturnUtils.returnForOK(client.flushDB()));
	}

	@Override
	public Status flushAll(){
		return execute(ProtocolCommand.FLUSHALL, (Jedis client)->ReturnUtils.returnForOK(client.flushAll()));
	}

	@Override
	public Object eval(final String script){
		return execute(ProtocolCommand.EVAL, (Jedis client)->client.eval(script),
				OperationsCommandArguments.getInstance().put("script", script));
	}

	@Override
	public Object eval(final byte[] script){
		return execute(ProtocolCommand.EVAL, (Jedis client)->client.eval(script),
				OperationsCommandArguments.getInstance().put("script", script));
	}

	@Override
	public Object eval(final String script, final String... params){
		return execute(ProtocolCommand.EVAL, (Jedis client)->client.eval(script, params == null ? 0 : params.length,
				params), OperationsCommandArguments.getInstance().put("script", script).put("params", params));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		return execute(ProtocolCommand.EVAL, (Jedis client)->client.eval(script, params == null ? 0 : params.length,
				params), OperationsCommandArguments.getInstance().put("script", script).put("params", params));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] args){
		return execute(ProtocolCommand.EVAL, (Jedis client)->client.eval(script, Arrays.asList(keys),
				Arrays.asList(args)),
				OperationsCommandArguments.getInstance().put("script", script).put("keys", keys).put("args", args));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] args){
		return execute(ProtocolCommand.EVAL, (Jedis client)->client.eval(script, Arrays.asList(keys),
				Arrays.asList(args)),
				OperationsCommandArguments.getInstance().put("script", script).put("keys", keys).put("args", args));
	}

	@Override
	public Object evalSha(final String digest){
		return execute(ProtocolCommand.EVALSHA, (Jedis client)->client.evalsha(digest),
				OperationsCommandArguments.getInstance().put("digest", digest));
	}

	@Override
	public Object evalSha(final byte[] digest){
		return execute(ProtocolCommand.EVALSHA, (Jedis client)->client.evalsha(digest),
				OperationsCommandArguments.getInstance().put("digest", digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		return execute(ProtocolCommand.EVALSHA, (Jedis client)->client.evalsha(digest, params == null ? 0 :
				params.length, params), OperationsCommandArguments.getInstance().put("digest", digest).put("params",
				params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		return execute(ProtocolCommand.EVALSHA, (Jedis client)->client.evalsha(digest, params == null ? 0 :
				params.length, params), OperationsCommandArguments.getInstance().put("digest", digest).put("params",
				params));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] args){
		return execute(ProtocolCommand.EVALSHA, (Jedis client)->client.evalsha(digest, Arrays.asList(keys),
				Arrays.asList(args)),
				OperationsCommandArguments.getInstance().put("digest", digest).put("keys", keys).put("args", args));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] args){
		return execute(ProtocolCommand.EVALSHA, (Jedis client)->client.evalsha(digest, Arrays.asList(keys),
				Arrays.asList(args)),
				OperationsCommandArguments.getInstance().put("digest", digest).put("keys", keys).put("args", args));
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return execute(ProtocolCommand.SCRIPT_EXISTS, (Jedis client)->client.scriptExists(sha1),
				OperationsCommandArguments.getInstance().put("sha1", sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return execute(ProtocolCommand.SCRIPT_EXISTS, new Executor<Jedis, List<Boolean>>() {

			@Override
			public List<Boolean> execute(Jedis client){
				List<Long> ret = client.scriptExists(sha1);

				if(ret == null){
					return null;
				}else{
					List<Boolean> result = new ArrayList<>();

					for(Long v : ret){
						result.add(v == 1);
					}

					return result;
				}
			}

		}, OperationsCommandArguments.getInstance().put("sha1", sha1));
	}

	@Override
	public String scriptLoad(final String script){
		return execute(ProtocolCommand.SCRIPT_LOAD, (Jedis client)->client.scriptLoad(script),
				OperationsCommandArguments.getInstance().put("script", script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return execute(ProtocolCommand.SCRIPT_LOAD, (Jedis client)->client.scriptLoad(script),
				OperationsCommandArguments.getInstance().put("script", script));
	}

	@Override
	public Status scriptKill(){
		return execute(ProtocolCommand.SCRIPT_KILL, (Jedis client)->ReturnUtils.returnForOK(client.scriptKill()));
	}

	@Override
	public Status scriptFlush(){
		return execute(ProtocolCommand.SCRIPT_FLUSH, (Jedis client)->ReturnUtils.returnForOK(client.scriptFlush()));
	}

	@Override
	public Status save(){
		return execute(ProtocolCommand.SAVE, (Jedis client)->ReturnUtils.returnForOK(client.save()));
	}

	@Override
	public String bgSave(){
		return execute(ProtocolCommand.BGSAVE, (Jedis client)->client.bgsave());
	}

	@Override
	public String bgRewriteAof(){
		return execute(ProtocolCommand.BGREWRITEAOF, (Jedis client)->client.bgrewriteaof());
	}

	@Override
	public Long lastSave(){
		return execute(ProtocolCommand.LASTSAVE, (Jedis client)->client.lastsave());
	}

	@Override
	public Status slaveOf(final String host, final int port){
		return execute(ProtocolCommand.SLAVEOF, (Jedis client)->ReturnUtils.returnForOK(client.slaveof(host, port)),
				OperationsCommandArguments.getInstance().put("host", host).put("port", port));
	}

	@Override
	public Status auth(final String password){
		return execute(ProtocolCommand.AUTH, (Jedis client)->ReturnUtils.returnForOK(client.auth(password)),
				OperationsCommandArguments.getInstance().put("password", password));
	}

	@Override
	public Status auth(final byte[] password){
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public Info info(final InfoSection section){
		return execute(ProtocolCommand.INFO,
				(Jedis client)->InfoUtil.convert(client.info(section.name().toLowerCase())),
				OperationsCommandArguments.getInstance().put("section", section));
	}

	@Override
	public Info info(){
		return execute(ProtocolCommand.INFO, (Jedis client)->InfoUtil.convert(client.info()));
	}

	@Override
	public RedisServerTime time(){
		return execute(ProtocolCommand.TIME, (Jedis client)->ReturnUtils.returnRedisServerTime(client.time()));
	}

	@Override
	public Status clientSetName(final String name){
		return execute(ProtocolCommand.CLIENT_SETNAME,
				(Jedis client)->ReturnUtils.returnForOK(client.clientSetname(name)),
				OperationsCommandArguments.getInstance().put("name", name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		return execute(ProtocolCommand.CLIENT_SETNAME,
				(Jedis client)->ReturnUtils.returnForOK(client.clientSetname(name)),
				OperationsCommandArguments.getInstance().put("name", name));
	}

	@Override
	public String clientGetName(){
		return execute(ProtocolCommand.CLIENT_GETNAME, (Jedis client)->client.clientGetname());
	}

	@Override
	public List<Client> clientList(){
		return execute(ProtocolCommand.CLIENT_LIST, (Jedis client)->ClientUtil.parse(client.clientList()));
	}

	@Override
	public Status clientKill(final String host, final int port){
		return execute(ProtocolCommand.CLIENT_KILL, (Jedis client)->ReturnUtils.returnForOK(client.clientKill(host +
				":" + port)), OperationsCommandArguments.getInstance().put("host", host).put("port", port));
	}

	@Override
	public Status quit(){
		return execute(ProtocolCommand.QUIT, (Jedis client)->ReturnUtils.returnForOK(client.quit()));
	}

	@Override
	public void shutdown(){
		execute(ProtocolCommand.SHUTDOWN, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.shutdown();
				return null;
			}

		});
	}

	@Override
	public void shutdown(final boolean save){
		shutdown();
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return configSet(parameter, Float.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final float value){
		return configSet(parameter, NumberUtils.float2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return configSet(parameter, Double.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final double value){
		return configSet(parameter, NumberUtils.double2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return configSet(parameter, Integer.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final int value){
		return configSet(parameter, NumberUtils.int2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return configSet(parameter, Long.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final long value){
		return configSet(parameter, NumberUtils.long2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final String value){
		return execute(ProtocolCommand.CONFIG_SET, (Jedis client)->ReturnUtils.returnForOK(client.configSet(parameter,
				value)), OperationsCommandArguments.getInstance().put("parameter", parameter).put("value", value));
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		return execute(ProtocolCommand.CONFIG_SET, (Jedis client)->ReturnUtils.returnForOK(client.configSet(parameter,
				value)), OperationsCommandArguments.getInstance().put("parameter", parameter).put("value", value));
	}

	@Override
	public List<String> configGet(final String parameter){
		return execute(ProtocolCommand.CONFIG_GET, (Jedis client)->client.configGet(parameter),
				OperationsCommandArguments.getInstance().put("parameter", parameter));
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return execute(ProtocolCommand.CONFIG_GET, (Jedis client)->client.configGet(parameter),
				OperationsCommandArguments.getInstance().put("parameter", parameter));
	}

	@Override
	public Status configResetStat(){
		return execute(ProtocolCommand.CONFIG_RESETSTAT,
				(Jedis client)->ReturnUtils.returnForOK(client.configResetStat()));
	}

	@Override
	public Status configRewrite(){
		return execute(ProtocolCommand.CONFIG_REWRITE,
				(Jedis client)->ReturnUtils.returnForOK(client.configRewrite()));
	}

	@Override
	public Object sync(){
		return execute(ProtocolCommand.SYNC, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.sync();
				return null;
			}

		});
	}

	@Override
	public byte[] echo(byte[] str){
		return execute(ProtocolCommand.ECHO, (Jedis client)->client.echo(str),
				OperationsCommandArguments.getInstance().put("str", str));
	}

	@Override
	public Status ping(){
		return execute(ProtocolCommand.PING,
				(Jedis client)->ReturnUtils.returnStatus("PONG".equalsIgnoreCase(client.ping())));
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return execute(ProtocolCommand.OBJECT, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
				switch(command){
					case ENCODING:
						return client.objectEncoding(key);
					case IDLETIME:
						return client.objectIdletime(key);
					case REFCOUNT:
						return client.objectRefcount(key);
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("key", key));
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return execute(ProtocolCommand.OBJECT, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
				switch(command){
					case ENCODING:
						return client.objectEncoding(key);
					case IDLETIME:
						return client.objectIdletime(key);
					case REFCOUNT:
						return client.objectRefcount(key);
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("key", key));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... args){
		return execute(ProtocolCommand.SLOWLOG, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
				switch(command){
					case GET:
						return client.slowlogGet();
					case LEN:
						return client.slowlogLen();
					case RESET:
						return client.slowlogReset();
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("args", args));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... args){
		return execute(ProtocolCommand.SLOWLOG, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
				switch(command){
					case GET:
						return client.slowlogGet();
					case LEN:
						return client.slowlogLen();
					case RESET:
						return client.slowlogReset();
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("args", args));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		execute(ProtocolCommand.MONITOR, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.monitor(new JedisMonitor() {

					@Override
					public void onCommand(final String command){
						redisMonitor.onCommand(command);
					}
				});

				return null;
			}

		});
	}

	@Override
	public String debugObject(final String key){
		return execute(ProtocolCommand.DEBUG_OBJECT, (Jedis client)->client.debug(DebugParams.OBJECT(key)));
	}

	@Override
	public String debugSegfault(){
		return execute(ProtocolCommand.DEBUG_SEGFAULT, (Jedis client)->client.debug(DebugParams.SEGFAULT()));
	}

}

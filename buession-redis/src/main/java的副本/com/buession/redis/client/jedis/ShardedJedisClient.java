/**
 * @author Yong.Teng
 */
public class ShardedJedisClient extends AbstractJedisRedisClient<ShardedJedis> implements ShardedRedisClient {

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute(ProtocolCommand.SSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key, cursor)), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(ProtocolCommand.SSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key, cursor, (new ScanParams()).match(pattern))), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		return execute(ProtocolCommand.SSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key, cursor, (new ScanParams()).count(count))), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute(ProtocolCommand.SSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListScanResultConvert<byte[]>()).deconvert(client.sscan(key, cursor, (new ScanParams()).match(pattern).count(count))), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		return execute(ProtocolCommand.ZADD, (ShardedJedis client)->client.zadd(key,
				(new MapNumberConvert.MapNumberDoubleConvert<byte[]>()).convert(members)),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return execute(ProtocolCommand.ZSCORE, (ShardedJedis client)->client.zscore(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute(ProtocolCommand.ZCARD, (ShardedJedis client)->client.zcard(key),
				OperationsCommandArguments.getInstance().put("key", key));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		return execute(ProtocolCommand.ZINCRBY, (ShardedJedis client)->client.zincrby(key, increment, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("increment",
						increment));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZCOUNT, (ShardedJedis client)->client.zcount(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZRANGE, (ShardedJedis client)->client.zrange(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZRANGE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeWithScores(key,
						start, end)),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (ShardedJedis client)->client.zrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (ShardedJedis client)->client.zrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (ShardedJedis client)->client.zrangeByScore(key, min, max,
				offset, count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
				max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (ShardedJedis client)->client.zrangeByScore(key, min, max,
				offset, count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
				max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min, max)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min, max)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZRANGEBYLEX, (ShardedJedis client)->client.zrangeByLex(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYLEX, (ShardedJedis client)->client.zrangeByLex(key, min, max, offset,
				count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put(
						"offset", offset).put("count", count));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return execute(ProtocolCommand.ZRANK, (ShardedJedis client)->client.zrank(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return execute(ProtocolCommand.ZREVRANK, (ShardedJedis client)->client.zrevrank(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return execute(ProtocolCommand.ZREM, (ShardedJedis client)->client.zrem(key, members),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZREMRANGEBYRANK, (ShardedJedis client)->client.zremrangeByRank(key, start, end)
				, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZREMRANGEBYSCORE, (ShardedJedis client)->client.zremrangeByScore(key, min, max)
				, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREMRANGEBYSCORE, (ShardedJedis client)->client.zremrangeByScore(key, min, max)
				, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREMRANGEBYLEX, (ShardedJedis client)->client.zremrangeByLex(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZREVRANGE, (ShardedJedis client)->client.zrevrange(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.ZREVRANGE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeWithScores(key,
						start, end)),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (ShardedJedis client)->client.zrevrangeByScore(key, max, min)
				, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (ShardedJedis client)->client.zrevrangeByScore(key, max, min)
				, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (ShardedJedis client)->client.zrevrangeByScore(key, max, min,
				offset, count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
				max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (ShardedJedis client)->client.zrevrangeByScore(key, max, min,
				offset, count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
				max).put("offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max, min)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max, min)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(ShardedJedis client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count)), OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset", offset).put("count", count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZREVRANGEBYLEX, (ShardedJedis client)->client.zrevrangeByLex(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYLEX, (ShardedJedis client)->client.zrevrangeByLex(key, max, min,
				offset, count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
				max).put("offset", offset).put("count", count));
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return execute(ProtocolCommand.ZLEXCOUNT, (ShardedJedis client)->client.zlexcount(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return execute(ProtocolCommand.ZSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key
						, cursor)), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(ProtocolCommand.ZSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key
						, cursor, (new ScanParams()).match(pattern))), OperationsCommandArguments.getInstance().put(
								"key", key).put("cursor", cursor).put("pattern", pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		return execute(ProtocolCommand.ZSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key
						, cursor, (new ScanParams()).count(count))), OperationsCommandArguments.getInstance().put("key"
						, key).put("cursor", cursor).put("count", count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		return execute(ProtocolCommand.ZSCAN,
				(ShardedJedis client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key
						, cursor, (new ScanParams()).match(pattern).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return execute(ProtocolCommand.PFADD, (ShardedJedis client)->ReturnUtils.returnStatus(client.pfadd(key,
				elements) > 0), OperationsCommandArguments.getInstance().put("key", key).put("elements", elements));
	}

	@Override
	public Long pfCount(final String... keys){
		if(keys != null && keys.length > 1){
			logger.warn("{} only pfcount the first key.", ShardedJedisClient.class.getName());
		}

		return execute(ProtocolCommand.PFCOUNT, (ShardedJedis client)->(keys == null ? 0L : client.pfcount(keys[0])),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Long pfCount(final byte[]... keys){
		if(keys != null && keys.length > 1){
			logger.warn("{} only pfcount the first key.", ShardedJedisClient.class.getName());
		}

		return execute(ProtocolCommand.PFCOUNT, (ShardedJedis client)->(keys == null ? 0L : client.pfcount(keys[0])),
				OperationsCommandArguments.getInstance().put("keys", keys));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return execute(ProtocolCommand.GEOADD, (ShardedJedis client)->client.geoadd(key, longitude, latitude, member),
				OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude",
						latitude).put("member", member));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return execute(ProtocolCommand.GEOADD, (ShardedJedis client)->client.geoadd(key,
				(new GeoConvert.GeoMapConvert<byte[]>()).convert(memberCoordinates)),
				OperationsCommandArguments.getInstance().put("key", key).put("memberCoordinates", memberCoordinates));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return execute(ProtocolCommand.GEOPOS,
				(ShardedJedis client)->(new GeoConvert.ListMapConvert()).deconvert(client.geopos(key, members)),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return execute(ProtocolCommand.GEODIST, (ShardedJedis client)->client.geodist(key, member1, member2),
				OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2",
						member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return execute(ProtocolCommand.GEODIST, (ShardedJedis client)->client.geodist(key, member1, member2,
				(new GeoConvert.GeoUnitConvert()).convert(unit)), OperationsCommandArguments.getInstance().put("key",
				key).put("member1", member1).put("member2", member2).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute(ProtocolCommand.GEORADIUS,
				(ShardedJedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadius(key, longitude, latitude, radius, (new GeoConvert.GeoUnitConvert()).convert(unit))), OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		return execute(ProtocolCommand.GEORADIUS,
				(ShardedJedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadius(key, longitude, latitude, radius, (new GeoConvert.GeoUnitConvert()).convert(unit), (new GeoArgumentConvert()).convert(geoArgument))), OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		return execute(ProtocolCommand.GEORADIUSBYMEMBER,
				(ShardedJedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadiusByMember(key, member, radius, (new GeoConvert.GeoUnitConvert()).convert(unit))), OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute(ProtocolCommand.PFMERGE,
				(ShardedJedis client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadiusByMember(key, member, radius, (new GeoConvert.GeoUnitConvert()).convert(unit), (new GeoArgumentConvert()).convert(geoArgument))), OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return execute(ProtocolCommand.PFMERGE, (ShardedJedis client)->client.geohash(key, members),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		return execute(ProtocolCommand.SETBIT, (ShardedJedis client)->ReturnUtils.returnStatus(client.setbit(key,
				offset, value)), OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put(
						"value", value));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		return execute(ProtocolCommand.SETBIT, (ShardedJedis client)->ReturnUtils.returnStatus(client.setbit(key,
				offset, value)), OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put(
						"value", value));
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		return execute(ProtocolCommand.GETBIT, (ShardedJedis client)->ReturnUtils.returnStatus(client.getbit(key,
				offset)), OperationsCommandArguments.getInstance().put("key", key).put("offset", offset));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		return bitPos(SafeEncoder.encode(key), value);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		return bitPos(SafeEncoder.encode(key), value, start, end);
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return execute(ProtocolCommand.BITFIELD, (ShardedJedis client)->client.bitfield(key, arguments),
				OperationsCommandArguments.getInstance().put("key", key).put("arguments", arguments));
	}

	@Override
	public Long bitCount(final byte[] key){
		return execute(ProtocolCommand.BITCOUNT, (ShardedJedis client)->client.bitcount(key),
				OperationsCommandArguments.getInstance().put("key", key));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		return execute(ProtocolCommand.BITCOUNT, (ShardedJedis client)->client.bitcount(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public byte[] echo(final byte[] str){
		return execute(ProtocolCommand.ECHO, (ShardedJedis client)->client.echo(str),
				OperationsCommandArguments.getInstance().put("str", str));
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return object(command, SafeEncoder.encode(key));
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return execute(ProtocolCommand.OBJECT, new Executor<ShardedJedis, Object>() {

			@Override
			public Object execute(ShardedJedis client){
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

}

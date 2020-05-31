/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<T extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<T> {

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return execute(ProtocolCommand.SREM,
				(T client)->(new ScanResultConvert.ListScanResultConvert<String>()).deconvert(client.sscan(key,
						cursor)), OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return execute(ProtocolCommand.SSCAN,
				(T client)->(new ScanResultConvert.ListScanResultConvert<String>()).deconvert(client.sscan(key, cursor
						, (new ScanParams()).match(pattern))), OperationsCommandArguments.getInstance().put("key",
						key).put("cursor", cursor).put("pattern", pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return execute(ProtocolCommand.SSCAN,
				(T client)->(new ScanResultConvert.ListScanResultConvert<String>()).deconvert(client.sscan(key, cursor
						, (new ScanParams()).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute(ProtocolCommand.SSCAN,
				(T client)->(new ScanResultConvert.ListScanResultConvert<String>()).deconvert(client.sscan(key, cursor
						, (new ScanParams()).match(pattern).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		return execute(ProtocolCommand.ZADD, (T client)->client.zadd(key,
				(new MapNumberConvert.MapNumberDoubleConvert<String>()).convert(members)),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		Map<String, Number> data = new LinkedHashMap<>(members == null ? 0 : members.size());

		if(members != null){
			for(KeyValue<String, Number> member : members){
				data.put(member.getKey(), member.getValue());
			}
		}

		return zAdd(key, data);
	}

	@Override
	public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		Map<byte[], Number> data = new LinkedHashMap<>(members == null ? 0 : members.size());

		if(members != null){
			for(KeyValue<byte[], Number> member : members){
				data.put(member.getKey(), member.getValue());
			}
		}

		return zAdd(key, data);
	}

	@Override
	public Double zScore(final String key, final String member){
		return execute(ProtocolCommand.ZSCORE, (T client)->client.zscore(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zCard(final String key){
		return execute(ProtocolCommand.ZCARD, (T client)->client.zcard(key),
				OperationsCommandArguments.getInstance().put("key", key));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		return execute(ProtocolCommand.ZINCRBY, (T client)->client.zincrby(key, increment, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("increment",
						increment));
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		return execute(ProtocolCommand.ZCOUNT, (T client)->client.zcount(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		return execute(ProtocolCommand.ZRANGE, (T client)->client.zrange(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return execute(ProtocolCommand.ZRANGE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeWithScores(key, start, end)),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (T client)->client.zrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (T client)->client.zrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (T client)->client.zrangeByScore(key, min, max, offset, count),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE, (T client)->client.zrangeByScore(key, min, max, offset, count),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min,
						max)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
						max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min,
						max)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
						max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(String key, double min, double max, int offset, int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min,
						max, offset, count)),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrangeByScoreWithScores(key, min,
						max, offset, count)),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZRANGEBYLEX, (T client)->client.zrangeByLex(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZRANGEBYLEX, (T client)->client.zrangeByLex(key, min, max, offset, count),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
						offset).put("count", count));
	}

	@Override
	public Long zRank(final String key, final String member){
		return execute(ProtocolCommand.ZRANK, (T client)->client.zrank(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zRevRank(final String key, final String member){
		return execute(ProtocolCommand.ZREVRANK, (T client)->client.zrevrank(key, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member));
	}

	@Override
	public Long zRem(final String key, final String... members){
		return execute(ProtocolCommand.ZREM, (T client)->client.zrem(key, members),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		return execute(ProtocolCommand.ZREMRANGEBYRANK, (T client)->client.zremrangeByRank(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		return execute(ProtocolCommand.ZREMRANGEBYSCORE, (T client)->client.zremrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZREMRANGEBYSCORE, (T client)->client.zremrangeByScore(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZREMRANGEBYLEX, (T client)->client.zremrangeByLex(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		return execute(ProtocolCommand.ZREVRANGE, (T client)->client.zrevrange(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return execute(ProtocolCommand.ZREVRANGE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeWithScores(key, start, end)), OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double max, final double min){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (T client)->client.zrevrangeByScore(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String max, final String min){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (T client)->client.zrevrangeByScore(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double max, final double min, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (T client)->client.zrevrangeByScore(key, max, min, offset,
				count), OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put(
						"offset", offset).put("count", count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE, (T client)->client.zrevrangeByScore(key, max, min, offset,
				count), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put(
						"offset", offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max,
						min)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
						max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max,
						min)), OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max",
						max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max,
						min, offset, count)),
				OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		return execute(ProtocolCommand.ZREVRANGEBYSCORE,
				(T client)->(new TupleConvert.SetTupleConvert()).deconvert(client.zrevrangeByScoreWithScores(key, max,
						min, offset, count)),
				OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
						offset).put("count", count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZREVRANGEBYLEX, (T client)->client.zrevrangeByLex(key, max, min),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(ProtocolCommand.ZREVRANGEBYLEX, (T client)->client.zrevrangeByLex(key, max, min, offset, count)
				, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset"
						, offset).put("count", count));
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		return execute(ProtocolCommand.ZLEXCOUNT, (T client)->client.zlexcount(key, min, max),
				OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return execute(ProtocolCommand.ZSCAN,
				(T client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key, cursor)),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return execute(ProtocolCommand.ZSCAN,
				(T client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key, cursor,
						(new ScanParams()).match(pattern))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern",
						pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		return execute(ProtocolCommand.ZSCAN,
				(T client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key, cursor,
						(new ScanParams()).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		return execute(ProtocolCommand.ZSCAN,
				(T client)->(new ScanResultConvert.ListTupleScanResultConvert()).deconvert(client.zscan(key, cursor,
						(new ScanParams()).match(pattern).count(count))),
				OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count));
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		return execute(ProtocolCommand.PFADD, (T client)->ReturnUtils.returnStatus(client.pfadd(key, elements) > 0),
				OperationsCommandArguments.getInstance().put("key", key).put("elements", elements));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return execute(ProtocolCommand.GEOADD, (T client)->client.geoadd(key, longitude, latitude, member),
				OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("longitude",
						longitude).put("latitude", latitude));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return execute(ProtocolCommand.GEOADD, (T client)->client.geoadd(key,
				(new GeoConvert.GeoMapConvert<String>()).convert(memberCoordinates)),
				OperationsCommandArguments.getInstance().put("key", key).put("memberCoordinates", memberCoordinates));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return execute(ProtocolCommand.GEOPOS,
				(T client)->(new GeoConvert.ListMapConvert()).deconvert(client.geopos(key, members)),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		return execute(ProtocolCommand.GEODIST, (T client)->client.geodist(key, member1, member2),
				OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2",
						member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return execute(ProtocolCommand.GEODIST, (T client)->client.geodist(key, member1, member2,
				(new GeoConvert.GeoUnitConvert()).convert(unit)), OperationsCommandArguments.getInstance().put("key",
				key).put("member1", member1).put("member2", member2).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute(ProtocolCommand.GEORADIUS,
				(T client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadius(key,
						longitude, latitude, radius, (new GeoConvert.GeoUnitConvert()).convert(unit))),
				OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude",
						latitude).put("radius", radius).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, GeoArgument geoArgument){
		return execute(ProtocolCommand.GEORADIUS,
				(T client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadius(key,
						longitude, latitude, radius, (new GeoConvert.GeoUnitConvert()).convert(unit),
						(new GeoArgumentConvert()).convert(geoArgument))),
				OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude",
						latitude).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit){
		return execute(ProtocolCommand.GEORADIUSBYMEMBER,
				(T client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadiusByMember(key, member, radius, (new GeoConvert.GeoUnitConvert()).convert(unit))), OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute(ProtocolCommand.GEORADIUSBYMEMBER,
				(T client)->(new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert()).deconvert(client.georadiusByMember(key, member, radius, (new GeoConvert.GeoUnitConvert()).convert(unit), (new GeoArgumentConvert()).convert(geoArgument))), OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return execute(ProtocolCommand.GEOHASH, (T client)->client.geohash(key, members),
				OperationsCommandArguments.getInstance().put("key", key).put("members", members));
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		return execute(ProtocolCommand.SETBIT, (T client)->ReturnUtils.returnStatus(client.setbit(key, offset, value))
				, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		return execute(ProtocolCommand.SETBIT, (T client)->ReturnUtils.returnStatus(client.setbit(key, offset, value))
				, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
	}

	@Override
	public Status getBit(final String key, final long offset){
		return execute(ProtocolCommand.GETBIT, (T client)->ReturnUtils.returnStatus(client.getbit(key, offset)),
				OperationsCommandArguments.getInstance().put("key", key).put("offset", offset));
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		return execute(ProtocolCommand.BITPOS, (T client)->client.bitpos(key, value),
				OperationsCommandArguments.getInstance().put("key", key).put("value", value));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		return execute(ProtocolCommand.BITPOS, (T client)->client.bitpos(key, value, new BitPosParams(start, end)),
				OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put(
						"end", end));
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		return execute(ProtocolCommand.BITFIELD, (T client)->client.bitfield(key, arguments),
				OperationsCommandArguments.getInstance().put("key", key).put("arguments", arguments));
	}

	@Override
	public Long bitCount(final String key){
		return execute(ProtocolCommand.BITCOUNT, (T client)->client.bitcount(key),
				OperationsCommandArguments.getInstance().put("key", key));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		return execute(ProtocolCommand.BITCOUNT, (T client)->client.bitcount(key, start, end),
				OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
	}

	@Override
	public Object unSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public String echo(final String str){
		return execute(ProtocolCommand.ECHO, (T client)->client.echo(str),
				OperationsCommandArguments.getInstance().put("str", str));
	}

}

public class BaseRedisTemplate extends RedisAccessor implements SetCommands, SortedSetCommands, GeoCommands,
		BitMapCommands, TransactionCommands, PubSubCommands, LuaCommands {

	@Override
	public Long sAdd(final String key, final String... members){
		return execute((RedisClient client)->client.sAdd(makeRawKey(key), members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.sAdd(makeByteKey(key), members));
	}

	@Override
	public Long sCard(final String key){
		return execute((RedisClient client)->client.sCard(makeRawKey(key)));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute((RedisClient client)->client.sCard(makeByteKey(key)));
	}

	@Override
	public boolean sisMember(final String key, final String member){
		return execute((RedisClient client)->client.sisMember(makeRawKey(key), member));
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.sisMember(makeByteKey(key), member));
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute((RedisClient client)->client.sMembers(makeRawKey(key)));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute((RedisClient client)->client.sMembers(makeByteKey(key)));
	}

	@Override
	public String sPop(final String key){
		return execute((RedisClient client)->client.sPop(makeRawKey(key)));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute((RedisClient client)->client.sPop(makeByteKey(key)));
	}

	@Override
	public String sRandMember(final String key){
		return execute((RedisClient client)->client.sRandMember(makeRawKey(key)));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute((RedisClient client)->client.sRandMember(makeByteKey(key)));
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		return execute((RedisClient client)->client.sRandMember(makeRawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		return execute((RedisClient client)->client.sRandMember(makeByteKey(key), count));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return execute((RedisClient client)->client.sRandMember(makeRawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return execute((RedisClient client)->client.sRandMember(makeByteKey(key), count));
	}

	@Override
	public Long sRem(final String key, final String... members){
		return execute((RedisClient client)->client.sRem(makeRawKey(key), members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.sRem(makeByteKey(key), members));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute((RedisClient client)->client.sDiff(makeRawKeys(keys)));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute((RedisClient client)->client.sDiff(makeByteKeys(keys)));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute((RedisClient client)->client.sInter(makeRawKeys(keys)));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute((RedisClient client)->client.sInter(makeByteKeys(keys)));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute((RedisClient client)->client.sUnion(makeRawKeys(keys)));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute((RedisClient client)->client.sUnion(makeByteKeys(keys)));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.sUnionStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.sUnionStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		return execute((RedisClient client)->client.sMove(makeRawKey(source), makeRawKey(destKey), member));
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		return execute((RedisClient client)->client.sMove(makeByteKey(source), makeByteKey(destKey), member));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		return execute((RedisClient client)->client.zAdd(makeRawKey(key), members));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		return execute((RedisClient client)->client.zAdd(makeByteKey(key), members));
	}

	@Override
	public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		return execute((RedisClient client)->client.zAdd(makeRawKey(key), members));
	}

	@Override
	public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		return execute((RedisClient client)->client.zAdd(makeByteKey(key), members));
	}

	@Override
	public Double zScore(final String key, final String member){
		return execute((RedisClient client)->client.zScore(makeRawKey(key), member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.zScore(makeByteKey(key), member));
	}

	@Override
	public Long zCard(final String key){
		return execute((RedisClient client)->client.zCard(makeRawKey(key)));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute((RedisClient client)->client.zCard(makeByteKey(key)));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final float increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final int increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Long zCount(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRange(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Long zRank(final String key, final String member){
		return execute((RedisClient client)->client.zRank(makeRawKey(key), member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.zRank(makeByteKey(key), member));
	}

	@Override
	public Long zRevRank(final String key, final String member){
		return execute((RedisClient client)->client.zRevRank(makeRawKey(key), member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.zRevRank(makeByteKey(key), member));
	}

	@Override
	public Long zRem(final String key, final String... members){
		return execute((RedisClient client)->client.zRem(makeRawKey(key), members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.zRem(makeByteKey(key), members));
	}

	@Override
	public Long zRemRangeByRank(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeRawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeByteKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeRawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeByteKey(key), start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRange(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return execute(RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float max, final float min, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Long zLexCount(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), weights, makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), weights, makeByteKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), aggregate, weights,
				makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), aggregate, weights,
				makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), weights, makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), weights, makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), aggregate, weights,
				makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), aggregate, weights,
				makeByteKeys(keys)));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return execute((RedisClient client)->client.geoAdd(makeRawKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return execute((RedisClient client)->client.geoAdd(makeByteKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final String key, final String member, final Geo geo){
		return execute((RedisClient client)->client.geoAdd(makeRawKey(key), member, geo));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final Geo geo){
		return execute((RedisClient client)->client.geoAdd(makeByteKey(key), member, geo));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return execute((RedisClient client)->client.geoAdd(makeRawKey(key), memberCoordinates));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return execute((RedisClient client)->client.geoAdd(makeByteKey(key), memberCoordinates));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return execute((RedisClient client)->client.geoPos(makeRawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.geoPos(makeByteKey(key), members));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		return execute((RedisClient client)->client.geoDist(makeRawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return execute((RedisClient client)->client.geoDist(makeByteKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return execute((RedisClient client)->client.geoDist(makeRawKey(key), member1, member2, unit));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return execute((RedisClient client)->client.geoDist(makeByteKey(key), member1, member2, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), geo, radius));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), geo, radius));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), geo, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), geo, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius,
				geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(byte[] key, final double longitude, final double latitude, final double radius,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius,
				geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), geo, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), geo, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit,
				geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit,
				geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeRawKey(key), geo, radius, unit, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadius(makeByteKey(key), geo, radius, unit, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		return execute((RedisClient client)->client.geoRadiusByMember(makeRawKey(key), member, radius));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return execute((RedisClient client)->client.geoRadiusByMember(makeByteKey(key), member, radius));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit){
		return execute((RedisClient client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		return execute((RedisClient client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadiusByMember(makeRawKey(key), member, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadiusByMember(makeByteKey(key), member, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit,
				geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute((RedisClient client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit,
				geoArgument));
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return execute((RedisClient client)->client.geoHash(makeRawKey(key), members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.geoHash(makeByteKey(key), members));
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		return execute((RedisClient client)->client.setBit(makeRawKey(key), offset, value));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		return execute((RedisClient client)->client.setBit(makeByteKey(key), offset, value));
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		return execute((RedisClient client)->client.setBit(makeRawKey(key), offset, value));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		return execute((RedisClient client)->client.setBit(makeByteKey(key), offset, value));
	}

	@Override
	public Status getBit(final String key, final long offset){
		return execute((RedisClient client)->client.getBit(makeRawKey(key), offset));
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		return execute((RedisClient client)->client.getBit(makeByteKey(key), offset));
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		return execute((RedisClient client)->client.bitPos(makeRawKey(key), value));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		return execute((RedisClient client)->client.bitPos(makeByteKey(key), value));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		return execute((RedisClient client)->client.bitPos(makeRawKey(key), value, start, end));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		return execute((RedisClient client)->client.bitPos(makeByteKey(key), value, start, end));
	}

	@Override
	public Long bitOp(final Operation operation, final String destKey, final String... keys){
		return execute((RedisClient client)->client.bitOp(operation, makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.bitOp(operation, makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		return execute((RedisClient client)->client.bitField(makeRawKey(key), arguments));
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return execute((RedisClient client)->client.bitField(makeByteKey(key), arguments));
	}

	@Override
	public Long bitCount(final String key){
		return execute((RedisClient client)->client.bitCount(makeRawKey(key)));
	}

	@Override
	public Long bitCount(final byte[] key){
		return execute((RedisClient client)->client.bitCount(makeByteKey(key)));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		return execute((RedisClient client)->client.bitCount(makeRawKey(key), start, end));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.bitCount(makeByteKey(key), start, end));
	}

	@Override
	public Transaction multi(){
		return execute((RedisClient client)->client.multi());
	}

	@Override
	public void exec(final Transaction transaction){
		execute(new Executor<Void>() {

			@Override
			public Void execute(RedisClient client){
				client.exec(transaction);
				return null;
			}

		});
	}

	@Override
	public void discard(final Transaction transaction){
		execute(new Executor<Void>() {

			@Override
			public Void execute(RedisClient client){
				client.discard(transaction);
				return null;
			}

		});
	}

	@Override
	public Status watch(final String... keys){
		return execute((RedisClient client)->client.watch(makeRawKeys(keys)));
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute((RedisClient client)->client.watch(makeByteKeys(keys)));
	}

	@Override
	public Status unwatch(){
		return execute((RedisClient client)->client.unwatch());
	}

	@Override
	public Long publish(final String channel, final String message){
		return execute((RedisClient client)->client.publish(channel, message));
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		return execute((RedisClient client)->client.publish(channel, message));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		execute(new Executor<Void>() {

			@Override
			public Void execute(RedisClient client){
				client.subscribe(channels, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		execute(new Executor<Void>() {

			@Override
			public Void execute(RedisClient client){
				client.subscribe(channels, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		execute(new Executor<Void>() {

			@Override
			public Void execute(RedisClient client){
				client.pSubscribe(patterns, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		execute(new Executor<Void>() {

			@Override
			public Void execute(RedisClient client){
				client.pSubscribe(patterns, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public Object unSubscribe(){
		return execute((RedisClient client)->client.unSubscribe());
	}

	@Override
	public Object unSubscribe(final String... channels){
		return execute((RedisClient client)->client.unSubscribe(channels));
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		return execute((RedisClient client)->client.unSubscribe(channels));
	}

	@Override
	public Object pUnSubscribe(){
		return execute((RedisClient client)->client.pUnSubscribe());
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		return execute((RedisClient client)->client.pUnSubscribe(patterns));
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		return execute((RedisClient client)->client.pUnSubscribe(patterns));
	}

	@Override
	public Object eval(final String script){
		return execute((RedisClient client)->client.eval(script));
	}

	@Override
	public Object eval(final byte[] script){
		return execute((RedisClient client)->client.eval(script));
	}

	@Override
	public Object eval(final String script, final String... params){
		return execute((RedisClient client)->client.eval(script, params));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		return execute((RedisClient client)->client.eval(script, params));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] args){
		return execute((RedisClient client)->client.eval(script, keys, args));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] args){
		return execute((RedisClient client)->client.eval(script, keys, args));
	}

	@Override
	public Object evalSha(final String digest){
		return execute((RedisClient client)->client.evalSha(digest));
	}

	@Override
	public Object evalSha(final byte[] digest){
		return execute((RedisClient client)->client.evalSha(digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		return execute((RedisClient client)->client.evalSha(digest, params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		return execute((RedisClient client)->client.evalSha(digest, params));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] args){
		return execute((RedisClient client)->client.evalSha(digest, keys, args));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] args){
		return execute((RedisClient client)->client.evalSha(digest, keys, args));
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return execute((RedisClient client)->client.scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return execute((RedisClient client)->client.scriptExists(sha1));
	}

	@Override
	public String scriptLoad(final String script){
		return execute((RedisClient client)->client.scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return execute((RedisClient client)->client.scriptLoad(script));
	}

	@Override
	public Status scriptKill(){
		return execute((RedisClient client)->client.scriptKill());
	}

	@Override
	public Status scriptFlush(){
		return execute((RedisClient client)->client.scriptFlush());
	}

}

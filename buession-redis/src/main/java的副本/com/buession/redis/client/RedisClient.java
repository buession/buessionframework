/**
 * @author Yong.Teng
 */
public interface RedisClient {

	@Override
	default ScanResult<List<String>> sScan(final String key, final int cursor){
		return sScan(key, Integer.toString(cursor));
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return sScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final long cursor){
		return sScan(key, Long.toString(cursor));
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return sScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return sScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return sScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return sScan(key, Long.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return sScan(key, Integer.toString(cursor), count);
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return sScan(key, NumberUtils.int2bytes(cursor), count);
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return sScan(key, Long.toString(cursor), count);
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return sScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return sScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return sScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return sScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	default Double zIncrBy(final String key, final String member, final float increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final String key, final String member, final int increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final String key, final String member, final long increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Long zCount(final String key, final float min, final float max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final byte[] key, final float min, final float max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final String key, final int min, final int max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final byte[] key, final int min, final int max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final String key, final long min, final long max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final byte[] key, final long min, final long max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRange(final String key, final int start, final int end){
		return zRange(key, (long) start, (long) end);
	}

	@Override
	default Set<byte[]> zRange(final byte[] key, final int start, final int end){
		return zRange(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return zRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		return zRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final float min, final float max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final int min, final int max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final long min, final long max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final float min, final float max){
		return zRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max){
		return zRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final double min, final double max){
		return zRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final int min, final int max){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max){
		return zRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final long min, final long max){
		return zRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max), offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return zRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max), offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	default Long zRemRangeByRank(final String key, final int start, final int end){
		return zRemRangeByRank(key, (long) start, (long) end);
	}

	@Override
	default Long zRemRangeByRank(final byte[] key, final int start, final int end){
		return zRemRangeByRank(key, (long) start, (long) end);
	}

	@Override
	default Long zRemRangeByScore(final String key, final float min, final float max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final float min, final float max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final String key, final int min, final int max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final int min, final int max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final String key, final long min, final long max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByLex(final String key, final float min, final float max){
		return zRemRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final float min, final float max){
		return zRemRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Long zRemRangeByLex(final String key, final double min, final double max){
		return zRemRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return zRemRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Long zRemRangeByLex(final String key, final int min, final int max){
		return zRemRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final int min, final int max){
		return zRemRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Long zRemRangeByLex(final String key, final long min, final long max){
		return zRemRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return zRemRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default Set<String> zRevRange(final String key, final int start, final int end){
		return zRevRange(key, (long) start, (long) end);
	}

	@Override
	default Set<byte[]> zRevRange(final byte[] key, final int start, final int end){
		return zRevRange(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return zRevRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		return zRevRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max, final int offset
			, final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset
			, final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final float min, final float max){
		return zRevRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max){
		return zRevRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final double min, final double max){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final int min, final int max){
		return zRevRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max){
		return zRevRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final long min, final long max){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max), offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max), offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	default Long zLexCount(final String key, final float min, final float max){
		return zLexCount(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final float min, final float max){
		return zLexCount(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Long zLexCount(final String key, final double min, final double max){
		return zLexCount(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final double min, final double max){
		return zLexCount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Long zLexCount(final String key, final int min, final int max){
		return zLexCount(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final int min, final int max){
		return zLexCount(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Long zLexCount(final String key, final long min, final long max){
		return zLexCount(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final long min, final long max){
		return zLexCount(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return zScan(key, Integer.toString(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		return zScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return zScan(key, Long.toString(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return zScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return zScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		return zScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return zScan(key, Long.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return zScan(key, Integer.toString(cursor), Integer.toString(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		return zScan(key, NumberUtils.int2bytes(cursor), NumberUtils.int2bytes(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return zScan(key, Long.toString(cursor), Long.toString(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		return zScan(key, NumberUtils.long2bytes(cursor), NumberUtils.long2bytes(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return zScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return zScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return zScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(byte[] key, long cursor, byte[] pattern, int count){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	default Long geoAdd(final String key, final String member, final Geo geo){
		return geoAdd(key, member, geo.getLongitude(), geo.getLatitude());
	}

	@Override
	default Long geoAdd(final byte[] key, final byte[] member, final Geo geo){
		return geoAdd(key, member, geo.getLongitude(), geo.getLatitude());
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius,
			final GeoArgument geoArgument){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, GeoUnit.M, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius,
			final GeoArgument geoArgument){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, GeoUnit.M, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
			final GeoArgument geoArgument){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
			final GeoArgument geoArgument){
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoArgument geoArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoArgument geoArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoArgument);
	}

}

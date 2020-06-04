public class BaseRedisTemplate extends RedisAccessor implements SetCommands, GeoCommands, BitMapCommands,
		TransactionCommands, PubSubCommands, LuaCommands {

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

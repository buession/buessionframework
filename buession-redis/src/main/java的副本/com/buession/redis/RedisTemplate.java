public class RedisTemplate extends BaseRedisTemplate implements, GeoOperations, BitMapOperations,
		TransactionOperations, PubSubOperations, LuaOperations {

	@Override
	public Geo geoPos(final String key, final String member){
		return ReturnUtils.returnFirst(geoPos(key, new String[]{member}));
	}

	@Override
	public Geo geoPos(final byte[] key, final byte[] member){
		return ReturnUtils.returnFirst(geoPos(key, new byte[][]{member}));
	}

	@Override
	public String geoHash(final String key, final String member){
		return ReturnUtils.returnFirst(geoHash(key, new String[]{member}));
	}

	@Override
	public byte[] geoHash(final byte[] key, final byte[] member){
		return ReturnUtils.returnFirst(geoHash(key, new byte[][]{member}));
	}

	@Override
	public Long bitOp(final Operation operation, final String destKey, final String key){
		return bitOp(operation, destKey, new String[]{key});
	}

	@Override
	public Long bitOp(final Operation operation, final byte[] destKey, final byte[] key){
		return bitOp(operation, destKey, new byte[][]{key});
	}

	@Override
	public Status watch(final String key){
		return watch(new String[]{key});
	}

	@Override
	public Status watch(final byte[] key){
		return watch(new byte[][]{key});
	}

	@Override
	public void subscribe(final String channel, final PubSubListener<String> pubSubListener){
		subscribe(new String[]{channel}, pubSubListener);
	}

	@Override
	public void subscribe(final byte[] channel, final PubSubListener<byte[]> pubSubListener){
		subscribe(new byte[][]{channel}, pubSubListener);
	}

	@Override
	public void pSubscribe(final String pattern, final PubSubListener<String> pubSubListener){
		pSubscribe(new String[]{pattern}, pubSubListener);
	}

	@Override
	public void pSubscribe(final byte[] pattern, final PubSubListener<byte[]> pubSubListener){
		pSubscribe(new byte[][]{pattern}, pubSubListener);
	}

	@Override
	public Object unSubscribe(final String channel){
		return unSubscribe(new String[]{channel});
	}

	@Override
	public Object unSubscribe(final byte[] channel){
		return unSubscribe(new byte[][]{channel});
	}

	@Override
	public Object pUnSubscribe(final String pattern){
		return pUnSubscribe(new String[]{pattern});
	}

	@Override
	public Object pUnSubscribe(final byte[] pattern){
		return pUnSubscribe(new byte[][]{pattern});
	}

	@Override
	public Object eval(final String script, final String param){
		return eval(script, new String[]{param});
	}

	@Override
	public Object eval(final byte[] script, final byte[] param){
		return eval(script, new byte[][]{param});
	}

	@Override
	public Object evalSha(final String digest, final String param){
		return evalSha(digest, new String[]{param});
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[] param){
		return evalSha(digest, new byte[][]{param});
	}

	@Override
	public Boolean scriptExists(final String sha1){
		return ReturnUtils.returnFirst(scriptExists(new String[]{sha1}), false);
	}

	@Override
	public Boolean scriptExists(final byte[] sha1){
		return ReturnUtils.returnFirst(scriptExists(new byte[][]{sha1}), false);
	}

}

public class RedisTemplate extends BaseRedisTemplate implements, SetOperations, SortedSetOperations, GeoOperations,
		BitMapOperations, TransactionOperations, PubSubOperations, LuaOperations {


	@Override
	public Long sAdd(final String key, final String member){
		return sAdd(key, new String[]{member});
	}

	@Override
	public Long sAdd(final byte[] key, final byte[] member){
		return sAdd(key, new byte[][]{member});
	}

	@Override
	public <V> Long sAdd(final String key, final V member){
		return sAdd(key, serializer.serialize(member));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V member){
		return sAdd(key, serializer.serializeAsBytes(member));
	}

	@Override
	public <V> Long sAdd(final String key, final V... members){
		return sAdd(key, serializer(members));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V... members){
		return sAdd(key, serializerAsByte(members));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key){
		return ReturnUtils.returnObjectValueFromSetString(serializer, sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key){
		return ReturnUtils.returnObjectValueFromSetByte(serializer, sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromSetString(serializer, sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromSetByte(serializer, sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromSetString(serializer, sMembers(key), type);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromSetByte(serializer, sMembers(key), type);
	}

	@Override
	public <V> V sPopObject(final String key){
		return serializer.deserialize(sPop(key));
	}

	@Override
	public <V> V sPopObject(final byte[] key){
		return serializer.deserialize(sPop(key));
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz){
		return serializer.deserialize(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(sPop(key), type);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(sPop(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final String key){
		return serializer.deserialize(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key){
		return serializer.deserialize(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz){
		return serializer.deserialize(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(sRandMember(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(sRandMember(key), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count){
		return ReturnUtils.returnObjectValueFromListString(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count){
		return ReturnUtils.returnObjectValueFromListByte(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count){
		return ReturnUtils.returnObjectValueFromListString(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count){
		return ReturnUtils.returnObjectValueFromListByte(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromListString(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromListByte(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromListString(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromListByte(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromListString(serializer, sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromListByte(serializer, sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromListString(serializer, sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromListByte(serializer, sRandMember(key, count), type);
	}

	@Override
	public Long sRem(final String key, final String member){
		return sRem(key, new String[]{member});
	}

	@Override
	public Long sRem(final byte[] key, final byte[] member){
		return sRem(key, new byte[][]{member});
	}

	@Override
	public <V> Long sRem(final String key, final V member){
		return sRem(key, serializer.serialize(member));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V member){
		return sRem(key, serializer.serializeAsBytes(member));
	}

	@Override
	public <V> Long sRem(final String key, final V... members){
		return sRem(key, serializer(members));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V... members){
		return sRem(key, serializerAsByte(members));
	}

	@Override
	public Set<String> sDiff(final String key){
		return sDiff(new String[]{key});
	}

	@Override
	public Set<byte[]> sDiff(final byte[] key){
		return sDiff(new byte[][]{key});
	}

	@Override
	public <V> Set<V> sDiffObject(final String key){
		return ReturnUtils.returnObjectValueFromSetString(serializer, sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key){
		return ReturnUtils.returnObjectValueFromSetByte(serializer, sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromSetString(serializer, sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final Class<V> clazz){
		return ReturnUtils.returnObjectValueFromSetByte(serializer, sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromSetString(serializer, sDiff(key), type);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final TypeReference<V> type){
		return ReturnUtils.returnObjectValueFromSetByte(serializer, sDiff(key), type);
	}

	@Override
	public Long sDiffStore(final String destKey, final String key){
		return sDiffStore(destKey, new String[]{key});
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[] key){
		return sDiffStore(destKey, new byte[][]{key});
	}

	@Override
	public Set<String> sInter(final String key){
		return sInter(new String[]{key});
	}

	@Override
	public Set<byte[]> sInter(final byte[] key){
		return sInter(new byte[][]{key});
	}

	@Override
	public Long sInterStore(final String destKey, final String key){
		return sInterStore(destKey, new String[]{key});
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[] key){
		return sInterStore(destKey, new byte[][]{key});
	}

	@Override
	public Set<String> sUnion(final String key){
		return sUnion(new String[]{key});
	}

	@Override
	public Set<byte[]> sUnion(final byte[] key){
		return sUnion(new byte[][]{key});
	}

	@Override
	public Long sUnionStore(final String destKey, final String key){
		return sUnionStore(destKey, new String[]{key});
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[] key){
		return sUnionStore(destKey, new byte[][]{key});
	}

	@Override
	public Long zAdd(final String key, final float score, final String member){
		return zAdd(key, new Float(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final float score, final byte[] member){
		return zAdd(key, new Float(score), member);
	}

	@Override
	public Long zAdd(final String key, final double score, final String member){
		return zAdd(key, new Double(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final double score, final byte[] member){
		return zAdd(key, new Double(score), member);
	}

	@Override
	public Long zAdd(final String key, final int score, final String member){
		return zAdd(key, new Integer(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final int score, final byte[] member){
		return zAdd(key, new Integer(score), member);
	}

	@Override
	public Long zAdd(final String key, final long score, final String member){
		return zAdd(key, new Long(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final long score, final byte[] member){
		return zAdd(key, new Long(score), member);
	}

	@Override
	public Long zAdd(final String key, final Number score, final String member){
		final Map<String, Number> members = new HashMap<>(1);

		members.put(member, score);

		return zAdd(key, members);
	}

	@Override
	public Long zAdd(final byte[] key, final Number score, final byte[] member){
		final Map<byte[], Number> members = new HashMap<>(1);

		members.put(member, score);

		return zAdd(key, members);
	}

	@Override
	public Long zAdd(final String key, final KeyValue<String, Number> member){
		final List<KeyValue<String, Number>> members = new ArrayList<>();

		members.add(member);

		return zAdd(key, members);
	}

	@Override
	public Long zAdd(final byte[] key, final KeyValue<byte[], Number> member){
		final List<KeyValue<byte[], Number>> members = new ArrayList<>();

		members.add(member);

		return zAdd(key, members);
	}

	@Override
	public Double zIncr(final String key, final String member){
		return null;
	}

	@Override
	public Double zIncr(final byte[] key, final byte[] member){
		return null;
	}

	@Override
	public Long zRem(final String key, final String member){
		return zRem(key, new String[]{member});
	}

	@Override
	public Long zRem(final byte[] key, final byte[] member){
		return zRem(key, new byte[][]{member});
	}

	@Override
	public Long zInterStore(final String destKey, final String key){
		return zInterStore(destKey, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[] key){
		return zInterStore(destKey, new byte[][]{key});
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String key){
		return zInterStore(destKey, aggregate, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[] key){
		return zInterStore(destKey, aggregate, new byte[][]{key});
	}

	@Override
	public Long zInterStore(final String destKey, final double weight, final String key){
		return zInterStore(destKey, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double weight, final byte[] key){
		return zInterStore(destKey, new double[]{weight}, new byte[][]{key});
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double weight, final String key){
		return zInterStore(destKey, aggregate, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key){
		return zInterStore(destKey, aggregate, new double[]{weight}, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final String key){
		return zUnionStore(destKey, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[] key){
		return zUnionStore(destKey, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String key){
		return zUnionStore(destKey, aggregate, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[] key){
		return zUnionStore(destKey, aggregate, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final double weight, final String key){
		return zUnionStore(destKey, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double weight, final byte[] key){
		return zUnionStore(destKey, new double[]{weight}, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double weight, final String key){
		return zUnionStore(destKey, aggregate, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key){
		return zUnionStore(destKey, aggregate, new double[]{weight}, new byte[][]{key});
	}

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

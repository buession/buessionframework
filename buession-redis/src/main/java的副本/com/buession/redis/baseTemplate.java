

    @Override
    public Status set(final String key, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.set(makeRawKey(key), value), ProtocolCommand.SET, args);
    }

    @Override
    public Status set(final byte[] key, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.set(makeByteKey(key), value), ProtocolCommand.SET, args);
    }

    @Override
    public Status set(final String key, final String value, final SetArgument setArgument){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("setArgument", setArgument);
        return execute((client)->client.set(makeRawKey(key), value, setArgument), ProtocolCommand.SET, args);
    }

    @Override
    public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("setArgument", setArgument);
        return execute((client)->client.set(makeByteKey(key), value, setArgument), ProtocolCommand.SET, args);
    }

    @Override
    public Status setEx(final String key, final String value, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
        return execute((client)->client.setEx(makeRawKey(key), value, lifetime), ProtocolCommand.SETEX, args);
    }

    @Override
    public Status setEx(final byte[] key, final byte[] value, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
        return execute((client)->client.setEx(makeByteKey(key), value, lifetime), ProtocolCommand.SETEX, args);
    }

    @Override
    public Status pSetEx(final String key, final String value, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
        return execute((client)->client.pSetEx(makeRawKey(key), value, lifetime), ProtocolCommand.PSETEX, args);
    }

    @Override
    public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
        return execute((client)->client.pSetEx(makeByteKey(key), value, lifetime), ProtocolCommand.PSETEX, args);
    }

    @Override
    public Status setNx(final String key, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.setNx(makeRawKey(key), value), ProtocolCommand.SETNX, args);
    }

    @Override
    public Status setNx(final byte[] key, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.setNx(makeByteKey(key), value), ProtocolCommand.SETNX, args);
    }

    @Override
    public Long append(final String key, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.append(makeRawKey(key), value), ProtocolCommand.APPEND, args);
    }

    @Override
    public Long append(final byte[] key, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.append(makeByteKey(key), value), ProtocolCommand.APPEND, args);
    }

    @Override
    public String get(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.get(makeRawKey(key)), ProtocolCommand.GET, args);
    }

    @Override
    public byte[] get(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.get(makeByteKey(key)), ProtocolCommand.GET, args);
    }

    @Override
    public String getSet(final String key, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.getSet(makeRawKey(key), value), ProtocolCommand.GETSET, args);
    }

    @Override
    public byte[] getSet(final byte[] key, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.getSet(makeByteKey(key), value), ProtocolCommand.GETSET, args);
    }

    @Override
    public Status mSet(final Map<String, String> values){
        final CommandArguments args = CommandArguments.getInstance().put("values", values);
        return execute((client)->client.mSet(values), ProtocolCommand.MSET, args);
    }

    @Override
    public Status mSetNx(final Map<String, String> values){
        final CommandArguments args = CommandArguments.getInstance().put("values", values);
        return execute((client)->client.mSetNx(values), ProtocolCommand.MSET, args);
    }

    @Override
    public List<String> mGet(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.mGet(makeRawKeys(keys)), ProtocolCommand.MGET, args);
    }

    @Override
    public List<byte[]> mGet(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.mGet(makeByteKeys(keys)), ProtocolCommand.MGET, args);
    }

    @Override
    public Long incr(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.incr(makeRawKey(key)), ProtocolCommand.INCR, args);
    }

    @Override
    public Long incr(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.incr(makeByteKey(key)), ProtocolCommand.INCR, args);
    }

    @Override
    public Long incrBy(final String key, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrBy(makeRawKey(key), value), ProtocolCommand.INCRBY, args);
    }

    @Override
    public Long incrBy(final byte[] key, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrBy(makeByteKey(key), value), ProtocolCommand.INCRBY, args);
    }

    @Override
    public Long incrBy(final String key, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrBy(makeRawKey(key), value), ProtocolCommand.INCRBY, args);
    }

    @Override
    public Long incrBy(final byte[] key, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrBy(makeByteKey(key), value), ProtocolCommand.INCRBY, args);
    }

    @Override
    public Double incrByFloat(final String key, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrByFloat(makeRawKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
    }

    @Override
    public Double incrByFloat(final byte[] key, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrByFloat(makeByteKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
    }

    @Override
    public Double incrByFloat(final String key, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrByFloat(makeRawKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
    }

    @Override
    public Double incrByFloat(final byte[] key, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.incrByFloat(makeByteKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
    }

    @Override
    public Long decr(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.decr(makeRawKey(key)), ProtocolCommand.DECR, args);
    }

    @Override
    public Long decr(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.decr(makeByteKey(key)), ProtocolCommand.DECR, args);
    }

    @Override
    public Long decrBy(final String key, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.decrBy(makeRawKey(key), value), ProtocolCommand.DECRBY, args);
    }

    @Override
    public Long decrBy(final byte[] key, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.decrBy(makeByteKey(key), value), ProtocolCommand.DECRBY, args);
    }

    @Override
    public Long decrBy(final String key, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.decrBy(makeRawKey(key), value), ProtocolCommand.DECRBY, args);
    }

    @Override
    public Long decrBy(final byte[] key, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.decrBy(makeByteKey(key), value), ProtocolCommand.DECRBY, args);
    }

    @Override
    public Long setRange(final String key, final int offset, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setRange(makeRawKey(key), offset, value), ProtocolCommand.SETRANGE, args);
    }

    @Override
    public Long setRange(final byte[] key, final int offset, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setRange(makeByteKey(key), offset, value), ProtocolCommand.SETRANGE, args);
    }

    @Override
    public Long setRange(final String key, final long offset, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setRange(makeRawKey(key), offset, value), ProtocolCommand.SETRANGE, args);
    }

    @Override
    public Long setRange(final byte[] key, final long offset, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setRange(makeByteKey(key), offset, value), ProtocolCommand.SETRANGE, args);
    }

    @Override
    public String getRange(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.getRange(makeRawKey(key), start, end), ProtocolCommand.GETRANGE, args);
    }

    @Override
    public byte[] getRange(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.getRange(makeByteKey(key), start, end), ProtocolCommand.GETRANGE, args);
    }

    @Override
    public String getRange(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.getRange(makeRawKey(key), start, end), ProtocolCommand.GETRANGE, args);
    }

    @Override
    public byte[] getRange(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.getRange(makeByteKey(key), start, end), ProtocolCommand.GETRANGE, args);
    }

    @Override
    public String substr(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.substr(makeRawKey(key), start, end), ProtocolCommand.SUBSTR, args);
    }

    @Override
    public byte[] substr(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.substr(makeByteKey(key), start, end), ProtocolCommand.SUBSTR, args);
    }

    @Override
    public String substr(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.substr(makeRawKey(key), start, end), ProtocolCommand.SUBSTR, args);
    }

    @Override
    public byte[] substr(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.substr(makeByteKey(key), start, end), ProtocolCommand.SUBSTR, args);
    }

    @Override
    public Long strlen(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.strlen(makeRawKey(key)), ProtocolCommand.STRLEN, args);
    }

    @Override
    public Long strlen(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.strlen(makeByteKey(key)), ProtocolCommand.STRLEN, args);
    }

    @Override
    public Long sAdd(final String key, final String... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sAdd(makeRawKey(key), members), ProtocolCommand.SADD, args);
    }

    @Override
    public Long sAdd(final byte[] key, final byte[]... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sAdd(makeByteKey(key), members), ProtocolCommand.SADD, args);
    }

    @Override
    public Long sCard(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sCard(makeRawKey(key)), ProtocolCommand.SCARD, args);
    }

    @Override
    public Long sCard(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sCard(makeByteKey(key)), ProtocolCommand.SCARD, args);
    }

    @Override
    public boolean sisMember(final String key, final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.sisMember(makeRawKey(key), member), ProtocolCommand.SISMEMBER, args);
    }

    @Override
    public boolean sisMember(final byte[] key, final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.sisMember(makeByteKey(key), member), ProtocolCommand.SISMEMBER, args);
    }

    @Override
    public Set<String> sMembers(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sMembers(makeRawKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public Set<byte[]> sMembers(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sMembers(makeByteKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public String sPop(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sPop(makeRawKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public byte[] sPop(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sPop(makeByteKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public String sRandMember(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sRandMember(makeRawKey(key)), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public byte[] sRandMember(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sRandMember(makeByteKey(key)), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<String> sRandMember(final String key, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<byte[]> sRandMember(final byte[] key, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<String> sRandMember(final String key, final long count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<byte[]> sRandMember(final byte[] key, final long count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public Long sRem(final String key, final String... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sRem(makeRawKey(key), members), ProtocolCommand.SREM, args);
    }

    @Override
    public Long sRem(final byte[] key, final byte[]... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sRem(makeByteKey(key), members), ProtocolCommand.SREM, args);
    }

    @Override
    public Set<String> sDiff(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sDiff(makeRawKeys(keys)), ProtocolCommand.SDIFF, args);
    }

    @Override
    public Set<byte[]> sDiff(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sDiff(makeByteKeys(keys)), ProtocolCommand.SDIFF, args);
    }

    @Override
    public Long sDiffStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SDIFFSTORE, args);
    }

    @Override
    public Long sDiffStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SDIFFSTORE, args);
    }

    @Override
    public Set<String> sInter(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sInter(makeRawKeys(keys)), ProtocolCommand.SINTER, args);
    }

    @Override
    public Set<byte[]> sInter(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sInter(makeByteKeys(keys)), ProtocolCommand.SINTER, args);
    }

    @Override
    public Long sInterStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SINTERSTORE, args);
    }

    @Override
    public Long sInterStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SINTERSTORE, args);
    }

    @Override
    public Set<String> sUnion(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sUnion(makeRawKeys(keys)), ProtocolCommand.SUNION, args);
    }

    @Override
    public Set<byte[]> sUnion(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sUnion(makeByteKeys(keys)), ProtocolCommand.SUNION, args);
    }

    @Override
    public Long sUnionStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sUnionStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SUNIONSTORE, args);
    }

    @Override
    public Long sUnionStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sUnionStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SUNIONSTORE, args);
    }

    @Override
    public Status sMove(final String key, final String destKey, final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("member", member);
        return execute((client)->client.sMove(makeRawKey(key), makeRawKey(destKey), member), ProtocolCommand.SMOVE, args);
    }

    @Override
    public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("member", member);
        return execute((client)->client.sMove(makeByteKey(key), makeByteKey(destKey), member), ProtocolCommand.SMOVE, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
     final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute ((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public Long zAdd(final String key, final Map<String, Number> members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.zAdd(makeRawKey(key), members), ProtocolCommand.ZADD, args);
    }

    @Override
    public Long zAdd(final byte[] key, final Map<byte[], Number> members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.zAdd(makeByteKey(key), members), ProtocolCommand.ZADD, args);
    }

    @Override
    public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.zAdd(makeRawKey(key), members), ProtocolCommand.ZADD, args);
    }

    @Override
    public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.zAdd(makeByteKey(key), members), ProtocolCommand.ZADD, args);
    }

    @Override
    public Double zScore(final String key, final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.zScore(makeRawKey(key), member), ProtocolCommand.ZSCORE, args);
    }

    @Override
    public Double zScore(final byte[] key, final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.zScore(makeByteKey(key), member), ProtocolCommand.ZSCORE, args);
    }

    @Override
    public Long zCard(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.zCard(makeRawKey(key)), ProtocolCommand.ZCARD, args);
    }

    @Override
    public Long zCard(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.zCard(makeByteKey(key)), ProtocolCommand.ZCARD, args);
    }

    @Override
    public Double zIncrBy(final String key, final String member, final float increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final byte[] key, final byte[] member, final float increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final String key, final String member, final double increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final String key, final String member, final int increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final byte[] key, final byte[] member, final int increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final String key, final String member, final long increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
        return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
    }

    @Override
    public Long zCount(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Long zCount(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
    }

    @Override
    public Set<String> zRange(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRange(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<byte[]> zRange(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRange(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<String> zRange(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRange(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<byte[]> zRange(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRange(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
     final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]>zRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]>zRangeByScore(final byte[] key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max ,final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
        final int count){
            final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[]key,final long min,final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key,final String min,final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key,final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min,final byte[] max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRangeByLex(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key,final double min,final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRangeByLex(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRangeByLex(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRangeByLex(final byte[] key,final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override 
    public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max,final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
    }

    @Override
    public Long zRank(final String key, final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.zRank(makeRawKey(key), member), ProtocolCommand.ZRANK, args);
    }

    @Override
    public Long zRank(final byte[] key, final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.zRank(makeByteKey(key), member), ProtocolCommand.ZRANK, args);
    }

    @Override
    public Long zRevRank(final String key,final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.zRevRank(makeRawKey(key), member), ProtocolCommand.ZREVRANK, args);
    }

    @Override
    public Long zRevRank(final byte[] key,final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.zRevRank(makeByteKey(key), member), ProtocolCommand.ZREVRANK, args);
    }

    @Override
    public Long zRem(final String key, final String... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.zRem(makeRawKey(key), members), ProtocolCommand.ZREM, args);
    }

    @Override
    public Long zRem(final byte[] key, final byte[]... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.zRem(makeByteKey(key), members), ProtocolCommand.ZREM, args);
    }

    @Override
    public Long zRemRangeByRank(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRemRangeByRank(makeRawKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
    }

    @Override
    public Long zRemRangeByRank(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRemRangeByRank(makeByteKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
    }

    @Override
    public Long zRemRangeByRank(final String key,final long start,final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRemRangeByRank(makeRawKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
    }

    @Override
    public Long zRemRangeByRank(final byte[]key,final long start,final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRemRangeByRank(makeByteKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
    }

    @Override
    public Long zRemRangeByScore(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final byte[] key,final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final byte[] key,final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
    }

    @Override
    public Long zRemRangeByLex(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRange(final String key,final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRange(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<byte[]>zRevRange(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRange(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<String> zRevRange(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRange(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<byte[]>zRevRange(final byte[]key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRange(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.zRevRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]>zRevRangeByScore(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]>zRevRangeByScore(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]>zRevRangeByScore(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]>zRevRangeByScore(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset, final int count){
    final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key,final float min,final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key,final byte[] min,final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key,final float max,final float min,final int offset, final int count){
    final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key,final String min, final String max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRevRangeByLex(final byte[] key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key),min,max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRevRangeByLex(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRevRangeByLex(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]>zRevRangeByLex(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
        final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((cliet)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
        return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
    }

    @Override
    public Long zLexCount(final String key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final byte[]key, final float min, final float max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final String key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final byte[] key, final double min, final double max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final String key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final byte[] key, final int min, final int max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final String key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final byte[] key, final long min, final long max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final String key, final String min, final String max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
        return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
    }

    @Override
    public Long zInterStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.zInterStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.zInterStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
        return execute((client)->client.zInterStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final byte[]destKey,final Aggregate aggregate,final byte[]...keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
        return execute((client)->client.zInterStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final String destKey,final double[] weights,final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys", keys);
        return execute((client)->client.zInterStore(makeRawKey(destKey), weights, makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final byte[]destKey,final double[]weights,final byte[]...keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys", keys);
        return execute((client)->client.zInterStore(makeByteKey(destKey), weights, makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights, final String...keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("weights", weights).put("keys", keys);
        return execute((client)->client.zInterStore(makeRawKey(destKey), aggregate, weights, makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("weights", weights).put("keys", keys);
        return execute((client)->client.zInterStore(makeByteKey(destKey), aggregate, weights, makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
    }

    @Override
    public Long zUnionStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.zUnionStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.zUnionStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
        return execute((client)->client.zUnionStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
        return execute((client)->client.zUnionStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.zUnionStore(makeRawKey(destKey), weights, makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.zUnionStore(makeByteKey(destKey), weights, makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
        return execute((client)->client.zUnionStore(makeRawKey(destKey), aggregate, weights, makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
        return execute((client)->client.zUnionStore(makeByteKey(destKey), aggregate, weights, makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key,final long cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key,final String cursor,final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[]cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
    }

    @Override
    public Status setBit(final String key, final int offset, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final byte[] key, final int offset, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final String key, final long offset, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final byte[] key, final long offset, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final String key, final int offset, final boolean value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final byte[] key, final int offset, final boolean value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final String key, final long offset, final boolean value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status setBit(final byte[] key, final long offset, final boolean value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
        return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
    }

    @Override
    public Status getBit(final String key, final int offset){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset);
        return execute((client)->client.getBit(makeRawKey(key), offset), ProtocolCommand.GETBIT, args);
    }

    @Override
    public Status getBit(final byte[] key, final int offset){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset);
        return execute((client)->client.getBit(makeByteKey(key), offset), ProtocolCommand.GETBIT, args);
    }

    @Override
    public Status getBit(final String key, final long offset){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset);
        return execute((client)->client.getBit(makeRawKey(key), offset), ProtocolCommand.GETBIT, args);
    }

    @Override
    public Status getBit(final byte[] key, final long offset){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset);
        return execute((client)->client.getBit(makeByteKey(key), offset), ProtocolCommand.GETBIT, args);
    }

    @Override
    public Long bitPos(final String key, final boolean value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.bitPos(makeRawKey(key), value), ProtocolCommand.BITPOS, args);
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
        return execute((client)->client.bitPos(makeByteKey(key), value), ProtocolCommand.BITPOS, args);
    }

    @Override
    public Long bitPos(final String key, final boolean value, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put("end", end);
        return execute((client)->client.bitPos(makeRawKey(key), value, start, end), ProtocolCommand.BITPOS, args);
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put("end", end);
        return execute((client)->client.bitPos(makeByteKey(key), value, start, end), ProtocolCommand.BITPOS, args);
    }

    @Override
    public Long bitPos(final String key, final boolean value, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put("end", end);
        return execute((client)->client.bitPos(makeRawKey(key), value, start, end), ProtocolCommand.BITPOS, args);
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put("end", end);
        return execute((client)->client.bitPos(makeByteKey(key), value, start, end), ProtocolCommand.BITPOS, args);
    }

    @Override
    public Long bitOp(final Operation operation, final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys", keys);
        return execute((client)->client.bitOp(operation,makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.BITOP, args);
    }

    @Override
    public Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys", keys);
        return execute((client)->client.bitOp(operation,makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.BITOP, args);
    }

    @Override
    public List<Long> bitField(final String key, final String... arguments){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("arguments", arguments);
        return execute((client)->client.bitField(makeRawKey(key), arguments), ProtocolCommand.BITFIELD, args);
    }

    @Override
    public List<Long> bitField(final byte[] key, final byte[]... arguments){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("arguments", arguments);
        return execute((client)->client.bitField(makeByteKey(key), arguments), ProtocolCommand.BITFIELD, args);
    }

    @Override
    public Long bitCount(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.bitCount(makeRawKey(key)), ProtocolCommand.BITCOUNT, args);
    }

    @Override
    public Long bitCount(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.bitCount(makeByteKey(key)), ProtocolCommand.BITCOUNT, args);
    }

    @Override
    public Long bitCount(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.bitCount(makeRawKey(key), start, end), ProtocolCommand.BITCOUNT, args);
    }

    @Override
    public Long bitCount(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.bitCount(makeByteKey(key), start, end), ProtocolCommand.BITCOUNT, args);
    }

    @Override
    public Long bitCount(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.bitCount(makeRawKey(key), start, end), ProtocolCommand.BITCOUNT, args);
    }

    @Override
    public Long bitCount(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.bitCount(makeByteKey(key), start, end), ProtocolCommand.BITCOUNT, args);
    }

    @Override
    public Transaction multi(){
        return execute((client)->client.multi(), ProtocolCommand.MULTI);
    }

    @Override
    public void exec(final Transaction transaction){
        final CommandArguments args = CommandArguments.getInstance().put("transaction", transaction);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.exec(transaction);
                return null;
            }

        }, ProtocolCommand.EXEC, args);
    }

    @Override
    public void discard(final Transaction transaction){
        final CommandArguments args = CommandArguments.getInstance().put("transaction", transaction);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.discard(transaction);
                return null;
            }

        }, ProtocolCommand.DISCARD, args);
    }

    @Override
    public Status watch(final String... keys){
        return execute((client)->client.watch(makeRawKeys(keys)), ProtocolCommand.WATCH);
    }

    @Override
    public Status watch(final byte[]... keys){
        return execute((client)->client.watch(makeByteKeys(keys)), ProtocolCommand.WATCH);
    }

    @Override
    public Status unwatch(){
        return execute((client)->client.unwatch(), ProtocolCommand.UNWATCH);
    }

    @Override
    public Long publish(final String channel, final String message){
        final CommandArguments args = CommandArguments.getInstance().put("channel", channel).put("message", message);
        return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
    }

    @Override
    public Long publish(final byte[] channel, final byte[] message){
        final CommandArguments args = CommandArguments.getInstance().put("channel", channel).put("message", message);
        return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
    }

    @Override
    public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.subscribe(channels,pubSubListener);
                return null;
            }

        }, ProtocolCommand.SUBSCRIBE, args);
    }

    @Override
    public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.subscribe(channels,pubSubListener);
                return null;
            }

        }, ProtocolCommand.SUBSCRIBE, args);
    }

    @Override
    public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.pSubscribe(patterns, pubSubListener);
                return null;
            }

        }, ProtocolCommand.PSUBSCRIBE, args);
    }

    @Override
    public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.pSubscribe(patterns, pubSubListener);
                return null;
            }

        }, ProtocolCommand.PSUBSCRIBE, args);
    }

    @Override
    public Object unSubscribe(){
        return execute((client)->client.unSubscribe(), ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object unSubscribe(final String... channels){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
        return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE, args);
    }

    @Override
    public Object unSubscribe(final byte[]... channels){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
        return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE, args);
    }

    @Override
    public Object pUnSubscribe(){
        return execute((client)->client.pUnSubscribe(), ProtocolCommand.PUNSUBSCRIBE);
    }

    @Override
    public Object pUnSubscribe(final String... patterns){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns);
        return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE, args);
    }

    @Override
    public Object pUnSubscribe(final byte[]... patterns){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns);
        return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE, args);
    }

    @Override
    public Object eval(final String script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.eval(script), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final byte[] script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.eval(script), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final String script,final String...params){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);
        return execute((client)->client.eval(script, params), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final byte[] script, final byte[]... params){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);
        return execute((client)->client.eval(script, params), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final String script, final String[] keys, final String[] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.eval(script, keys, arguments), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.eval(script, keys, arguments), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object evalSha(final String digest){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest);
        return execute((client)->client.evalSha(digest), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final byte[] digest){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest);
        return execute((client)->client.evalSha(digest), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final String digest, final String... params){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);
        return execute((client)->client.evalSha(digest, params), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final byte[] digest ,final byte[]... params){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);
        return execute((client)->client.evalSha(digest, params), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final String digest, final String[] keys, final String[] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public List<Boolean> scriptExists(final String... sha1){
        final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);
        return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS, args);
    }

    @Override
    public List<Boolean> scriptExists(final byte[]... sha1){
        final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);
        return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS, args);
    }

    @Override
    public String scriptLoad(final String script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD, args);
    }

    @Override
    public byte[] scriptLoad(final byte[] script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD, args);
    }

    @Override
    public Status scriptKill(){
        return execute((client)->client.scriptKill(), ProtocolCommand.SCRIPT_KILL);
    }

    @Override
    public Status scriptFlush(){
        return execute((client)->client.scriptFlush(), ProtocolCommand.SCRIPT_FLUSH);
    }

    @Override
    public Info info(final InfoSection section){
        final CommandArguments args = CommandArguments.getInstance().put("section", section);
        return execute((client)->client.info(), ProtocolCommand.INFO, args);
    }

    @Override
    public Info info(){
        return execute((client)->client.info(), ProtocolCommand.INFO);
    }

    @Override
    public Role role(){
        return execute((client)->client.role(), ProtocolCommand.ROLE);
    }

    @Override
    public RedisServerTime time(){
        return execute((client)->client.time(), ProtocolCommand.TIME);
    }

    @Override
    public Status configSet(final String parameter, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public List<String> configGet(final String parameter){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter);
        return execute((client)->client.configGet(parameter), ProtocolCommand.CONFIG_GET, args);
    }

    @Override
    public List<byte[]> configGet(final byte[] parameter){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter);
        return execute((client)->client.configGet(parameter), ProtocolCommand.CONFIG_GET, args);
    }

    @Override
    public Status configResetStat(){
        return execute((client)->client.configResetStat(), ProtocolCommand.CONFIG_RESETSTAT);
    }

    @Override
    public Status configRewrite(){
        return execute((client)->client.configRewrite(), ProtocolCommand.CONFIG_REWRITE);
    }

    @Override
    public Status clientSetName(final String name){
        final CommandArguments args = CommandArguments.getInstance().put("name", name);
        return execute((client)->client.clientSetName(name), ProtocolCommand.CLIENT_SETNAME, args);
    }

    @Override
    public Status clientSetName(final byte[] name){
        final CommandArguments args = CommandArguments.getInstance().put("name", name);
        return execute((client)->client.clientSetName(name), ProtocolCommand.CLIENT_SETNAME, args);
    }

    @Override
    public String clientGetName(){
        return execute((client)->client.clientGetName(), ProtocolCommand.CLIENT_GETNAME);
    }

    @Override
    public String clientId(){
        return execute((client)->client.clientId(), ProtocolCommand.CLIENT_ID);
    }

    @Override
    public List<Client> clientList(){
        return execute((client)->client.clientList(), ProtocolCommand.CLIENT_LIST);
    }

    @Override
    public Status clientPause(final long timeout){
        final CommandArguments args = CommandArguments.getInstance().put("timeout", timeout);
        return execute((client)->client.clientPause(timeout), ProtocolCommand.CLIENT_PAUSE, args);
    }

    @Override
    public Status clientReply(final ClientReply option){
        final CommandArguments args = CommandArguments.getInstance().put("option", option);
        return execute((client)->client.clientReply(option), ProtocolCommand.CLIENT_REPLY, args);
    }

    @Override
    public Status clientKill(final String host, final int port){
        final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);
        return execute((client)->client.clientKill(host, port), ProtocolCommand.CLIENT_KILL, args);
    }

    @Override
    public Long dbSize(){
        return execute((client)->client.dbSize(), ProtocolCommand.DBSIZE);
    }

    @Override
    public Status flushDb(){
        return execute((client)->client.flushDb(), ProtocolCommand.FLUSHDB);
    }

    @Override
    public Status flushAll(){
        return execute((client)->client.flushAll(), ProtocolCommand.FLUSHALL);
    }

    @Override
    public String debugObject(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.debugObject(makeRawKey(key)), ProtocolCommand.DEBUG_OBJECT, args);
    }

    @Override
    public String debugSegfault(){
        return execute((client)->client.debugSegfault(), ProtocolCommand.DEBUG_SEGFAULT);
    }

    @Override
    public Object sync(){
        return execute((client)->client.sync(), ProtocolCommand.SYNC);
    }

    @Override
    public Object pSync(final String masterRunId, final int offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Object pSync(final byte[] masterRunId, final int offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Object pSync(final String masterRunId, final long offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Object pSync(final byte[] masterRunId, final long offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Status save(){
        return execute((client)->client.save(), ProtocolCommand.SAVE);
    }

    @Override
    public String bgSave(){
        return execute((client)->client.bgSave(), ProtocolCommand.BGSAVE);
    }

    @Override
    public String bgRewriteAof(){
        return execute((client)->client.bgSave(), ProtocolCommand.BGREWRITEAOF);
    }

    @Override
    public Long lastSave(){
        return execute((client)->client.lastSave(), ProtocolCommand.LASTSAVE);
    }

    @Override
    public Status slaveOf(final String host, final int port){
        final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);
        return execute((client)->client.slaveOf(host, port), ProtocolCommand.SLAVEOF, args);
    }

    @Override
    public Status replicaOf(final String host, final int port){
        final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);
        return execute((client)->client.replicaOf(host, port), ProtocolCommand.REPLICAOF, args);
    }

    @Override
    public Object slowLog(final SlowLogCommand command, final String... arguments){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("arguments", arguments);
        return execute((client)->client.slowLog(command, arguments), ProtocolCommand.SLOWLOG, args);
    }

    @Override
    public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("arguments", arguments);
        return execute((client)->client.slowLog(command, arguments), ProtocolCommand.SLOWLOG, args);
    }

    @Override
    public void monitor(final RedisMonitor redisMonitor){
        final CommandArguments args = CommandArguments.getInstance().put("redisMonitor", redisMonitor);

        execute(new Executor<Void>() {

            @Override
            public Void execute(RedisClient client){
                client.monitor(redisMonitor);
                return null;
            }

        }, ProtocolCommand.MONITOR, args);
    }

    @Override
    public void shutdown(){
        execute(new Executor<Void>() {

            @Override
            public Void execute(RedisClient client){
                client.shutdown();
                return null;
            }

        }, ProtocolCommand.SHUTDOWN);
    }

    @Override
    public void shutdown(final boolean save){
        final CommandArguments args = CommandArguments.getInstance().put("save", save);

        execute(new Executor<Void>() {

            @Override
            public Void execute(RedisClient client){
                client.shutdown();
                return null;
            }

        }, ProtocolCommand.SHUTDOWN, args);
    }

    @Override
    public Object object(final ObjectCommand command, final String key){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("key", key);
        return execute((client)->client.object(command, makeRawKey(key)), ProtocolCommand.OBJECT, args);
    }

    @Override
    public Object object(final DebugCommands.ObjectCommand command, final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("key", key);
        return execute((client)->client.object(command, makeByteKey(key)), ProtocolCommand.OBJECT, args);
    }
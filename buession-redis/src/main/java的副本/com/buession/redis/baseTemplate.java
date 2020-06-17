

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
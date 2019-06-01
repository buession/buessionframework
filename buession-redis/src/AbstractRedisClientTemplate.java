public abstract class AbstractRedisClientTemplate<T extends JedisCommands, C extends RedisClient<T>> extends
RedisAccessor<T, C> implements RedisClientTemplate<T> {

    @Override
    public <O> O brPoplPushObject(final String source, final String dest, final int timeout){
        return serializer.decode(brPoplPush(source, dest, timeout));
    }

    @Override
    public <O> O brPoplPushObject(final byte[] source, final byte[] dest, final int timeout){
        return serializer.decode(brPoplPush(source, dest, timeout));
    }

    @Override
    public <O> O brPoplPushObject(final String source, final String dest, final int timeout, final Class<O> clazz){
        return serializer.decode(brPoplPush(source, dest, timeout), clazz);
    }

    @Override
    public <O> O brPoplPushObject(final byte[] source, final byte[] dest, final int timeout, final Class<O> clazz){
        return serializer.decode(brPoplPush(source, dest, timeout), clazz);
    }

    @Override
    public <O> O brPoplPushObject(final String source, final String dest, final int timeout, final TypeReference<O>
            type){
        return serializer.decode(brPoplPush(source, dest, timeout), type);
    }

    @Override
    public <O> O brPoplPushObject(final byte[] source, final byte[] dest, final int timeout, final TypeReference<O>
            type){
        return serializer.decode(brPoplPush(source, dest, timeout), type);
    }

    @Override
    public <O> List<O> blPopObject(final String key, final int timeout){
        return ConvertUtil.listStrToListO(blPop(key, timeout), serializer);
    }

    @Override
    public <O> List<O> blPopObject(final byte[] key, final int timeout){
        return ConvertUtil.listByteToListO(blPop(key, timeout), serializer);
    }

    @Override
    public <O> List<O> blPopObject(final String key, final int timeout, final Class<O> clazz){
        return ConvertUtil.listStrToListO(blPop(key, timeout), clazz, serializer);
    }

    @Override
    public <O> List<O> blPopObject(final byte[] key, final int timeout, final Class<O> clazz){
        return ConvertUtil.listByteToListO(blPop(key, timeout), clazz, serializer);
    }

    @Override
    public <O> List<O> blPopObject(final String key, final int timeout, final TypeReference<O> type){
        return ConvertUtil.listStrToListO(blPop(key, timeout), type, serializer);
    }

    @Override
    public <O> List<O> blPopObject(final byte[] key, final int timeout, final TypeReference<O> type){
        return ConvertUtil.listByteToListO(blPop(key, timeout), type, serializer);
    }

    @Override
    public <O> List<O> brPopObject(final String key, final int timeout){
        return ConvertUtil.listStrToListO(brPop(key, timeout), serializer);
    }

    @Override
    public <O> List<O> brPopObject(final byte[] key, final int timeout){
        return ConvertUtil.listByteToListO(brPop(key, timeout), serializer);
    }

    @Override
    public <O> List<O> brPopObject(final String key, final int timeout, final Class<O> clazz){
        return ConvertUtil.listStrToListO(brPop(key, timeout), clazz, serializer);
    }

    @Override
    public <O> List<O> brPopObject(final byte[] key, final int timeout, final Class<O> clazz){
        return ConvertUtil.listByteToListO(brPop(key, timeout), clazz, serializer);
    }

    @Override
    public <O> List<O> brPopObject(final String key, final int timeout, final TypeReference<O> type){
        return ConvertUtil.listStrToListO(brPop(key, timeout), type, serializer);
    }

    @Override
    public <O> List<O> brPopObject(final byte[] key, final int timeout, final TypeReference<O> type){
        return ConvertUtil.listByteToListO(brPop(key, timeout), type, serializer);
    }


}

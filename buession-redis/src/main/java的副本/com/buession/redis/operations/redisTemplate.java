

    @Override
    public <V> Status set(final String key, final V value){
        return set(key, serializer.serialize(value));
    }

    @Override
    public <V> Status set(final byte[] key, final V value){
        return set(key, serializer.serializeAsBytes(value));
    }

    @Override
    public <V> Status set(final String key, final V value, final SetArgument setArgument){
        return set(key, serializer.serialize(value), setArgument);
    }

    @Override
    public <V> Status set(final byte[] key, final V value, final SetArgument setArgument){
        return set(key, serializer.serializeAsBytes(value), setArgument);
    }

    @Override
    public <V> Status setEx(final String key, final V value, final int lifetime){
        return setEx(key, serializer.serialize(value), lifetime);
    }

    @Override
    public <V> Status setEx(final byte[] key, final V value, final int lifetime){
        return setEx(key, serializer.serializeAsBytes(value), lifetime);
    }

    @Override
    public <V> Status pSetEx(final String key, final V value, final int lifetime){
        return pSetEx(key, serializer.serialize(value), lifetime);
    }

    @Override
    public <V> Status pSetEx(final byte[] key, final V value, final int lifetime){
        return pSetEx(key, serializer.serializeAsBytes(value), lifetime);
    }

    @Override
    public <V> Status setNx(final String key, final V value){
        return setNx(key, serializer.serialize(value));
    }

    @Override
    public <V> Status setNx(final byte[] key, final V value){
        return setNx(key, serializer.serializeAsBytes(value));
    }

    @Override
    public <V> V getObject(final String key){
        return serializer.deserialize(get(key));
    }

    @Override
    public <V> V getObject(final byte[] key){
        return serializer.deserialize(get(key));
    }

    @Override
    public <V> V getObject(final String key, final Class<V> clazz){
        return serializer.deserialize(get(key), clazz);
    }

    @Override
    public <V> V getObject(final byte[] key, final Class<V> clazz){
        return serializer.deserialize(get(key), clazz);
    }

    @Override
    public <V> V getObject(final String key, final TypeReference<V> type){
        return serializer.deserialize(get(key), type);
    }

    @Override
    public <V> V getObject(final byte[] key, final TypeReference<V> type){
        return serializer.deserialize(get(key), type);
    }

    @Override
    public <V> V getSet(final String key, final V value){
        return serializer.deserialize(getSet(key, serializer.serialize(value)));
    }

    @Override
    public <V> V getSet(final byte[] key, final V value){
        return serializer.deserialize(getSet(key, serializer.serialize(value)));
    }

    @Override
    public <V> V getSet(final String key, final V value, final Class<V> clazz){
        return serializer.deserialize(getSet(key, serializer.serialize(value)), clazz);
    }

    @Override
    public <V> V getSet(final byte[] key, final V value, final Class<V> clazz){
        return serializer.deserialize(getSet(key, serializer.serialize(value)), clazz);
    }

    @Override
    public <V> V getSet(final String key, final V value, final TypeReference<V> type){
        return serializer.deserialize(getSet(key, serializer.serialize(value)), type);
    }

    @Override
    public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type){
        return serializer.deserialize(getSet(key, serializer.serialize(value)), type);
    }

    @Override
    public <V> List<V> mGetObject(final String... keys){
        return ReturnUtils.objectFromListString(serializer, mGet(keys));
    }

    @Override
    public <V> List<V> mGetObject(final byte[]... keys){
        return ReturnUtils.objectFromListByte(serializer, mGet(keys));
    }

    @Override
    public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz){
        return ReturnUtils.objectFromListString(serializer, mGet(keys), clazz);
    }

    @Override
    public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz){
        return ReturnUtils.objectFromListByte(serializer, mGet(keys), clazz);
    }

    @Override
    public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type){
        return ReturnUtils.objectFromListString(serializer, mGet(keys), type);
    }

    @Override
    public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type){
        return ReturnUtils.objectFromListByte(serializer, mGet(keys), type);
    }
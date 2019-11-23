/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.buession.core.exception.SerializationException;
import com.buession.redis.Constants;
import com.buession.redis.utils.SafeEncoder;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Yong.Teng
 */
public class ByteArraySerializer implements Serializer {

    @Override
    public <O> String encode(O o) throws SerializerException{
        try{
            return com.buession.core.utils.Serialize.serialize(o, Constants.CHARSET);
        }catch(SerializationException e){
            throw new SerializerException(o + "encode failure", e);
        }
    }

    @Override
    public <O> byte[] encodeAsBytes(O o) throws SerializerException{
        return SafeEncoder.encode(encode(o));
    }

    @Override
    public <O> O decode(String str) throws SerializerException{
        return doDecode(str);
    }

    @Override
    public <O> O decode(String str, Class<O> clazz) throws SerializerException{
        return doDecode(str);
    }

    @Override
    public <O> O decode(String str, TypeReference<O> type) throws SerializerException{
        return doDecode(str);
    }

    @Override
    public <O> O decode(byte[] bytes) throws SerializerException{
        return bytes == null ? null : doDecode(new String(bytes, Constants.CHARSET));
    }

    @Override
    public <O> O decode(byte[] bytes, Class<O> clazz) throws SerializerException{
        return bytes == null ? null : doDecode(new String(bytes, Constants.CHARSET));
    }

    @Override
    public <O> O decode(byte[] bytes, TypeReference<O> type) throws SerializerException{
        return bytes == null ? null : doDecode(new String(bytes, Constants.CHARSET));
    }

    private final static <O> O doDecode(final String str) throws SerializerException{
        if(str == null){
            return null;
        }

        try{
            return com.buession.core.utils.Serialize.unserialize(str, Constants.CHARSET);
        }catch(SerializationException e){
            throw new SerializerException(e.getMessage(), e);
        }
    }

}

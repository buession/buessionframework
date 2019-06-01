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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public class ByteArraySerializer implements Serializer {

    private final static Logger logger = LoggerFactory.getLogger(ByteArraySerializer.class);

    @Override
    public <O> String encode(O o){
        try{
            return com.buession.core.utils.Serialize.serialize(o, Constants.CHARSET);
        }catch(SerializationException e){
            logger.error("{} encode failure: {}", o, e.getMessage());
            return null;
        }
    }

    @Override
    public <O> byte[] encodeAsBytes(O o){
        return SafeEncoder.encode(encode(o));
    }

    @Override
    public <O> O decode(String str){
        return doDecode(str);
    }

    @Override
    public <O> O decode(String str, Class<O> clazz){
        return doDecode(str);
    }

    @Override
    public <O> O decode(String str, TypeReference type){
        return doDecode(str);
    }

    @Override
    public <O> O decode(byte[] bytes){
        return bytes == null ? null : doDecode(new String(bytes, Constants.CHARSET));
    }

    @Override
    public <O> O decode(byte[] bytes, Class<O> clazz){
        return bytes == null ? null : doDecode(new String(bytes, Constants.CHARSET));
    }

    @Override
    public <O> O decode(byte[] bytes, TypeReference<O> type){
        return bytes == null ? null : doDecode(new String(bytes, Constants.CHARSET));
    }

    private final static <O> O doDecode(final String str){
        return str == null ? null : com.buession.core.utils.Serialize.unserialize(str, Constants.CHARSET);
    }

}

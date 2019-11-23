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

import com.buession.redis.utils.SafeEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class JSONSerializer implements Serializer {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static Logger logger = LoggerFactory.getLogger(JSONSerializer.class);

    static{
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <O> String encode(O o){
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.writeValueAsString(o);
        }catch(JsonProcessingException e){
            throw new SerializerException((o == null ? "null" : o.getClass().getName()) + " json encode failure", e);
        }
    }

    @Override
    public <O> byte[] encodeAsBytes(final O o){
        return SafeEncoder.encode(encode(o));
    }

    @Override
    public <O> O decode(String str){
        if(str == null){
            logger.debug("String is null.");
        }else{
            try{
                return OBJECT_MAPPER.readValue(str, new TypeReference<O>() {

                });
            }catch(IOException e){
                throw new SerializerException(str + " json encode to Object failure", e);
            }
        }

        return null;
    }

    @Override
    public <O> O decode(String str, Class<O> clazz){
        if(str == null){
            logger.debug("String is null.");
        }else{
            try{
                return OBJECT_MAPPER.readValue(str, clazz);
            }catch(IOException e){
                throw new SerializerException(str + " json decode to " + (clazz == null ? "null" : clazz.getName()) +
                        " failure", e);
            }
        }

        return null;
    }

    @Override
    public <O> O decode(String str, TypeReference<O> type){
        if(str == null){
            logger.debug("String is null.");
        }else{
            try{
                return OBJECT_MAPPER.readValue(str, type);
            }catch(IOException e){
                throw new SerializerException(str + " json decode to " + (type == null ? "null" : type.getType()
                        .getTypeName()) + " failure", e);
            }
        }

        return null;
    }

    @Override
    public <O> O decode(byte[] bytes){
        if(bytes == null){
            logger.debug("String is null.");
        }else{
            try{
                return OBJECT_MAPPER.readValue(bytes, new TypeReference<O>() {

                });
            }catch(IOException e){
                throw new SerializerException(bytes + " json decode to Object failure", e);
            }
        }

        return null;
    }

    @Override
    public <O> O decode(byte[] bytes, Class<O> clazz){
        if(bytes == null){
            logger.debug("String is null.");
        }else{
            try{
                return OBJECT_MAPPER.readValue(bytes, clazz);
            }catch(IOException e){
                throw new SerializerException(bytes + " json decode to " + (clazz == null ? "null" : clazz.getName())
                        + " failure", e);
            }
        }

        return null;
    }

    @Override
    public <O> O decode(byte[] bytes, TypeReference<O> type){
        if(bytes == null){
            logger.debug("String is null.");
        }else{
            try{
                return OBJECT_MAPPER.readValue(bytes, type);
            }catch(IOException e){
                throw new SerializerException(bytes + " json decode to " + (type == null ? "null" : type.getType()
                        .getTypeName()) + " failure", e);
            }
        }

        return null;
    }

}

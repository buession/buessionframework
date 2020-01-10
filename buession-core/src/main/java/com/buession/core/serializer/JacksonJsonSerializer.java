/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.serializer;

import com.buession.core.serializer.type.JacksonTypeReference;
import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 */
public class JacksonJsonSerializer extends AbstractJsonSerializer {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static{
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <V> String serialize(final V object) throws SerializerException{
        Assert.isNull(object, "Object cloud not be null.");

        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.writeValueAsString(object);
        }catch(JsonProcessingException e){
            throw new SerializerException((object == null ? "null" : object.getClass().getName()) + " json encode " +
                    "failure", e);
        }
    }

    @Override
    public <V> byte[] serializeAsBytes(final V object) throws SerializerException{
        Assert.isNull(object, "Object cloud not be null.");

        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.writeValueAsBytes(object);
        }catch(JsonProcessingException e){
            throw new SerializerException((object == null ? "null" : object.getClass().getName()) + " json encode " +
                    "failure", e);
        }
    }

    @Override
    public <V> String serialize(final V object, final String charsetName) throws SerializerException{
        return serialize(object, Charset.forName(charsetName));
    }

    @Override
    public <V> V deserialize(final String str) throws SerializerException{
        Assert.isNull(str, "String cloud not be null.");

        try{
            return OBJECT_MAPPER.readValue(str, new JacksonTypeReference<V>() {

            });
        }catch(IOException e){
            throw new SerializerException(str + " json decode to Object failure", e);
        }
    }

    @Override
    public <V> V deserialize(final byte[] bytes) throws SerializerException{
        Assert.isNull(bytes, "Bytes cloud not be null.");

        try{
            return OBJECT_MAPPER.readValue(bytes, new JacksonTypeReference<V>() {

            });
        }catch(IOException e){
            throw new SerializerException(bytes + " json decode to Object failure", e);
        }
    }

    @Override
    public <V> V deserialize(final String str, final Class<V> clazz) throws SerializerException{
        Assert.isNull(str, "String cloud not be null.");

        try{
            return OBJECT_MAPPER.readValue(str, clazz);
        }catch(IOException e){
            throw new SerializerException(str + " json decode to " + (clazz == null ? "null" : clazz.getName()) + " "
                    + "failure", e);
        }
    }

    @Override
    public <V> V deserialize(final byte[] bytes, final Class<V> clazz) throws SerializerException{
        Assert.isNull(bytes, "Bytes cloud not be null.");

        try{
            return OBJECT_MAPPER.readValue(bytes, clazz);
        }catch(IOException e){
            throw new SerializerException(bytes + " json decode to Object failure", e);
        }
    }

    @Override
    public <V> V deserialize(final String str, final TypeReference<V> type) throws SerializerException{
        Assert.isNull(str, "String cloud not be null.");

        try{
            return OBJECT_MAPPER.readValue(str, new JacksonTypeReference<>());
        }catch(IOException e){
            throw new SerializerException(str + " json decode to " + (type == null ? "null" : type.getType()) + " " +
                    "failure", e);
        }
    }

    @Override
    public <V> V deserialize(final byte[] bytes, final TypeReference<V> type) throws SerializerException{
        Assert.isNull(bytes, "Bytes cloud not be null.");

        try{
            return OBJECT_MAPPER.readValue(bytes, new JacksonTypeReference<>());
        }catch(IOException e){
            throw new SerializerException(bytes + " json decode to " + (type == null ? "null" : type.getType()) + " "
                    + "failure", e);
        }
    }

}

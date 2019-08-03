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
package com.buession.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.buession.core.exception.SerializationException;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化、反序列化
 *
 * @author Yong.Teng
 */
public final class Serialize {

    private final static int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 128;

    private final static Logger logger = LoggerFactory.getLogger(Serialize.class);

    /**
     * 对象序列化
     *
     * @param object
     *         待序列化对象
     * @param bytes
     *         序列化后的字节
     *
     * @return 序列化后的字节
     *
     * @throws SerializationException
     *         序列化异常
     */
    public static byte[] serialize(final Object object, byte[] bytes) throws SerializationException{
        byte[] result = new byte[0];

        if(object == null){
            return result;
        }

        if((object instanceof Serializable) == false){
            throw new SerializationException("requires a Serializable payload but received an object of type [" +
                    object.getClass().getName() + "]");
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(BYTE_ARRAY_OUTPUT_STREAM_SIZE);
        ObjectOutputStream objectOutputStream = null;

        try{
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }catch(IOException e){
            throw new SerializationException("serializer the instance of " + object.getClass().getName() + " " +
                    "failure", e);
        }finally{
            if(byteArrayOutputStream != null){
                try{
                    byteArrayOutputStream.close();
                }catch(IOException e){
                    logger.error("{} close error.", ByteArrayOutputStream.class.getName(), e);
                }
            }
            if(objectOutputStream != null){
                try{
                    objectOutputStream.close();
                }catch(IOException e){
                    logger.error("{} close error.", ObjectOutputStream.class.getName(), e);
                }
            }
        }
    }

    /**
     * 对象序列化
     *
     * @param object
     *         待序列化对象
     *
     * @return 序列化后的字符串
     *
     * @throws SerializationException
     *         序列化异常
     */
    public static String serialize(final Object object) throws SerializationException{
        return serialize(object, StandardCharsets.UTF_8.name());
    }

    /**
     * 对象序列化
     *
     * @param object
     *         待序列化对象
     * @param charsetName
     *         字符编码
     *
     * @return 序列化后的字符串
     *
     * @throws SerializationException
     *         序列化异常
     */
    public static String serialize(final Object object, final String charsetName) throws SerializationException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;

        try{
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(object);
            objectOutputStream.flush();

            String str = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
            return URLEncoder.encode(str, charsetName);
        }catch(IOException e){
            throw new SerializationException("serializer the instance of " + object.getClass().getName() + " " +
                    "failure", e);
        }finally{
            if(byteArrayOutputStream != null){
                try{
                    byteArrayOutputStream.close();
                }catch(IOException e){
                    logger.error("{} close error.", ObjectOutputStream.class.getName(), e);
                }
            }
            if(objectOutputStream != null){
                try{
                    objectOutputStream.close();
                }catch(IOException e){
                    logger.error("{} close error.", ObjectOutputStream.class.getName(), e);
                }
            }
        }
    }

    /**
     * 对象序列化
     *
     * @param object
     *         待序列化对象
     * @param charset
     *         字符编码
     *
     * @return 序列化后的字符串
     *
     * @throws SerializationException
     *         序列化异常
     */
    public static String serialize(final Object object, final Charset charset) throws SerializationException{
        return charset == null ? serialize(object) : serialize(object, charset.name());
    }

    /**
     * 字节反序列化
     *
     * @param bytes
     *         待反序列化的字节
     * @param <T>
     *         类
     *
     * @return 反序列化后的对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] bytes) throws SerializationException{
        if(Validate.isEmpty(bytes)){
            return null;
        }

        try{
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);

            return (T) objectInputStream.readObject();
        }catch(ClassNotFoundException e){
            throw new SerializationException("Failed to deserialize object type", e);
        }catch(Exception e){
            throw new SerializationException("Failed to deserialize", e);
        }
    }

    /**
     * 字节反序列化
     *
     * @param bytes
     *         待反序列化的字节
     * @param <T>
     *         类
     *
     * @return 反序列化后的对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    public static <T> T unserialize(byte[] bytes) throws SerializationException{
        return deserialize(bytes);
    }

    /**
     * 字符串反序列化
     *
     * @param str
     *         待反序列化字符串
     * @param <T>
     *         类
     *
     * @return 反序列化后对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    public static <T> T deserialize(final String str) throws SerializationException{
        return deserialize(str, StandardCharsets.UTF_8.name());
    }

    /**
     * 字符串反序列化
     *
     * @param str
     *         待反序列化字符串
     * @param <T>
     *         类
     *
     * @return 反序列化后对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    public static <T> T unserialize(final String str) throws SerializationException{
        return deserialize(str);
    }

    /**
     * 字符串反序列化
     *
     * @param str
     *         待反序列化字符串
     * @param charsetName
     *         字符编码
     * @param <T>
     *         类
     *
     * @return 反序列化后对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(final String str, final String charsetName) throws SerializationException{
        if(str == null || str.length() == 0){
            try{
                throw new IllegalArgumentException("String could not be empty or null");
            }catch(IllegalArgumentException e){
                logger.warn("deserialize the string is " + (str == null ? "null" : "empty") + ".", e);
            }
            return null;
        }

        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try{
            String s = URLDecoder.decode(str, charsetName);
            byteArrayInputStream = new ByteArrayInputStream(s.getBytes(StandardCharsets.ISO_8859_1));
            objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return  (T) objectInputStream.readObject();
        }catch(UnsupportedEncodingException e){
            throw new SerializationException("deserialize the string " + str + " failure.", e);
        }catch(ClassNotFoundException e){
            throw new SerializationException("deserialize the string " + str + " failure.", e);
        }catch(IOException e){
            throw new SerializationException("deserialize the string " + str + " failure.", e);
        }finally{
            if(byteArrayInputStream != null){
                try{
                    byteArrayInputStream.close();
                }catch(IOException e){
                    logger.error("{} close error.", ByteArrayOutputStream.class.getName(), e);
                }
            }
            if(objectInputStream != null){
                try{
                    objectInputStream.close();
                }catch(IOException e){
                    logger.error("{} close error.", ObjectInputStream.class.getName(), e);
                }
            }
        }
    }

    /**
     * 字符串反序列化
     *
     * @param str
     *         待反序列化字符串
     * @param charsetName
     *         字符编码
     * @param <T>
     *         类
     *
     * @return 反序列化后对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T unserialize(final String str, final String charsetName) throws SerializationException{
        return deserialize(str, charsetName);
    }

    /**
     * 字符串反序列化
     *
     * @param str
     *         待反序列化字符串
     * @param charset
     *         字符编码
     * @param <T>
     *         类
     *
     * @return 反序列化后对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(final String str, final Charset charset) throws SerializationException{
        return charset == null ? unserialize(str) : unserialize(str, charset.name());
    }

    /**
     * 字符串反序列化
     *
     * @param str
     *         待反序列化字符串
     * @param charset
     *         字符编码
     * @param <T>
     *         类
     *
     * @return 反序列化后对象
     *
     * @throws SerializationException
     *         反序列化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T unserialize(final String str, final Charset charset) throws SerializationException{
        return deserialize(str, charset);
    }

}
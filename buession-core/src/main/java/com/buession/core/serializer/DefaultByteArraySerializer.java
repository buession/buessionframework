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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.serializer;

import com.buession.core.utils.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 默认 byte 序列化
 *
 * @author Yong.Teng
 */
public class DefaultByteArraySerializer extends AbstractByteArraySerializer {

	private final static int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 128;

	@Override
	public <V> String serialize(final V object, final String charsetName) throws SerializerException {
		try{
			return URLEncoder.encode(baosWrite(object), charsetName);
		}catch(IOException e){
			throw new SerializerException("serializer the instance of " + object.getClass().getName() + " " + "failure"
					, e);
		}
	}

	@Override
	public <V> String serialize(final V object, final Charset charset) throws SerializerException {
		return serialize(object, charset.name());
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object, final String charsetName) throws SerializerException {
		Assert.isNull(object, "Object cloud not be null.");

		if((object instanceof Serializable) == false){
			throw new SerializerException(
					"Required a Serializable payload but received an object of type [" + object.getClass().getName() +
							"]");
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(BYTE_ARRAY_OUTPUT_STREAM_SIZE);
		ObjectOutputStream objectOutputStream = null;

		try{
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		}catch(IOException e){
			throw new SerializerException("serializer the instance of " + object.getClass().getName() + " " + "failure"
					, e);
		}finally{
			closeStream(byteArrayOutputStream);
			closeStream(objectOutputStream);
		}
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object, final Charset charset) throws SerializerException {
		return serializeAsBytes(object, charset.name());
	}

	protected static <V> String baosWrite(final V value) throws IOException {
		Assert.isNull(value, "Object cloud not be null.");

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = null;

		try{
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			objectOutputStream.writeObject(value);
			objectOutputStream.flush();

			return byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
		}catch(IOException e){
			throw e;
		}finally{
			closeStream(byteArrayOutputStream);
			closeStream(objectOutputStream);
		}
	}

}

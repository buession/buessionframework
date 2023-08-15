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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.deserializer;

import com.buession.core.utils.Assert;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 默认 byte 反序列化
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class DefaultByteArrayDeserializer extends AbstractByteArrayDeserializer {

	@Override
	public <V> V deserialize(final String str, final String charsetName) throws DeserializerException{
		Assert.isNull(str, "String cloud not be null.");

		try{
			String s = URLDecoder.decode(str, charsetName);
			return doDeserialize(s.getBytes(StandardCharsets.ISO_8859_1));
		}catch(UnsupportedEncodingException e){
			throw new DeserializerException("deserialize the string " + str + " failure.", e);
		}
	}

	@Override
	public <V> V deserialize(final String str, final Charset charset) throws DeserializerException{
		return deserialize(str, charset.name());
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final String charsetName) throws DeserializerException{
		Assert.isNull(bytes, "Bytes cloud not be null.");
		return doDeserialize(bytes);
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final Charset charset) throws DeserializerException{
		return deserialize(bytes, charset.name());
	}

	@SuppressWarnings("unchecked")
	protected static <V> V doDeserialize(final byte[] bytes) throws DeserializerException{
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;

		try{
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);

			return (V) objectInputStream.readObject();
		}catch(Exception e){
			throw new DeserializerException("deserialize the bytes " + Arrays.toString(bytes) + " failure.", e);
		}finally{
			closeStream(byteArrayInputStream);
			closeStream(objectInputStream);
		}
	}

}

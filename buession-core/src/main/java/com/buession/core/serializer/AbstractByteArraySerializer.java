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

import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 */
public abstract class AbstractByteArraySerializer extends AbstractSerializer implements ByteArraySerializer {

	@Override
	public <V> String serialize(final V object) throws SerializerException{
		return serialize(object, DEFAULT_CHARSET_NAME);
	}

	@Override
	public <V> String serialize(final V object, final Charset charset) throws SerializerException{
		return new String(serializeAsBytes(object, charset), charset);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object) throws SerializerException{
		return serializeAsBytes(object, DEFAULT_CHARSET);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object, final Charset charset) throws SerializerException{
		return serialize(object, charset).getBytes(charset);
	}

	@Override
	public <V> V deserialize(final String str) throws SerializerException{
		return deserialize(str, DEFAULT_CHARSET_NAME);
	}

	@Override
	public <V> V deserialize(final String str, final Charset charset) throws SerializerException{
		return deserialize(str, charset.name());
	}

	@Override
	public <V> V deserialize(final byte[] bytes) throws SerializerException{
		return deserialize(bytes, DEFAULT_CHARSET_NAME);
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final Charset charset) throws SerializerException{
		return deserialize(bytes, charset.name());
	}

}

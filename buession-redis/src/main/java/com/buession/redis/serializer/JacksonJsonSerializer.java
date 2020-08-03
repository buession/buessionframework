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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.buession.core.serializer.SerializerException;
import com.buession.core.serializer.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public class JacksonJsonSerializer extends AbstractSerializer {

	private final static com.buession.core.serializer.JacksonJsonSerializer serializer =
			new com.buession.core.serializer.JacksonJsonSerializer();

	private final static Logger logger = LoggerFactory.getLogger(JacksonJsonSerializer.class);

	public JacksonJsonSerializer(){
		super(serializer);
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz){
		if(str != null){
			try{
				return serializer.deserialize(str, clazz);
			}catch(SerializerException e){
				logger.error("{} deserialize to {} error.", str, clazz.getName(), e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final Class<V> clazz){
		if(bytes != null){
			try{
				return serializer.deserialize(bytes, clazz);
			}catch(SerializerException e){
				logger.error("{} deserialize to {} error.", bytes, clazz.getName(), e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserialize(final String str, final TypeReference<V> type){
		if(str != null){
			try{
				return serializer.deserialize(str, type);
			}catch(SerializerException e){
				logger.error("{} deserialize to {} error.", str, type.getType().getTypeName(), e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final TypeReference<V> type){
		if(bytes != null){
			try{
				return serializer.deserialize(bytes, type);
			}catch(SerializerException e){
				logger.error("{} deserialize to {} error.", bytes, type.getType().getTypeName(), e);
			}
		}
		
		return null;
	}

}

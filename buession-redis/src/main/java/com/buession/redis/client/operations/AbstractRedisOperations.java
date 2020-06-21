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
package com.buession.redis.client.operations;

import com.buession.core.converter.HashConverter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.client.RedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.Converters;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisOperations implements RedisOperations {

	protected final static ListConverter<String, byte[]> STRING_TO_BINARY_LIST_CONVERTER =
			Converters.stringToBinaryListConverter();

	protected final static HashConverter<String, String, byte[], byte[]> STRING_TO_BINARY_HASH_CONVERTER =
			Converters.stringToBinaryHashConverter();

	protected RedisClient client;

	public AbstractRedisOperations(final RedisClient client){
		this.client = client;
	}

	protected boolean isTransaction(){
		return client.getConnection().isTransaction();
	}

	protected boolean isPipeline(){
		return client.getConnection().isPipeline();
	}

	protected void pipelineAndTransactionNotSupportedException(final ProtocolCommand command){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(command);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(command);
		}else{
			throw new NotSupportedCommandException(command);
		}
	}

	protected void commandAllNotSupportedException(final ProtocolCommand command){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(command);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(command);
		}else{
			throw new NotSupportedCommandException(command);
		}
	}

}

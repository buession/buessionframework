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
package com.buession.redis.client.connection;

import com.buession.core.utils.Assert;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.ResourceHolderSupport;

/**
 * @author Yong.Teng
 */
public final class RedisConnectionHolder extends ResourceHolderSupport {

	@Nullable
	private RedisConnection connection;

	private boolean transactionActive;

	public RedisConnectionHolder(@Nullable RedisConnection connection) {
		this.connection = connection;
	}

	public boolean isTransactionActive() {
		return getTransactionActive();
	}

	public boolean getTransactionActive() {
		return transactionActive;
	}

	public void setTransactionActive(boolean transactionActive) {
		this.transactionActive = transactionActive;
	}

	public boolean hasConnection() {
		return connection != null;
	}

	@Nullable
	public RedisConnection getConnection() {
		return connection;
	}

	public RedisConnection getRequiredConnection() {
		RedisConnection connection = getConnection();

		Assert.isNull(connection, ()->new IllegalStateException("No active redis connection."));

		return connection;
	}

	public void setConnection(@Nullable RedisConnection connection) {
		this.connection = connection;
	}

	@Override
	public void released() {
		super.released();
		if(isOpen() == false){
			setConnection(null);
		}
	}

	@Override
	public void clear() {
		super.clear();
		this.transactionActive = false;
	}

}

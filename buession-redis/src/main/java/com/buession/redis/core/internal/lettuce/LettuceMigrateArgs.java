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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.core.MigrateOperation;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.MigrateArgs;

/**
 * Lettuce {@link MigrateArgs} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class LettuceMigrateArgs<T> extends MigrateArgs<T> {

	public LettuceMigrateArgs() {
		super();
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation) {
		super();

		if(migrateOperation != null){
			switch(migrateOperation){
				case COPY:
					copy();
					break;
				case REPLACE:
					replace();
					break;
				default:
					break;
			}
		}
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final T[] keys) {
		this(migrateOperation);
		keys(keys);
	}

	public LettuceMigrateArgs(final String password) {
		super();
		auth(password);
	}

	public LettuceMigrateArgs(final byte[] password) {
		this(SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final String username, final String password) {
		super();
		auth(password);
	}

	public LettuceMigrateArgs(final byte[] username, final byte[] password) {
		this(SafeEncoder.encode(username), SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final T[] keys, final String password) {
		this(keys);
		auth(password);
	}

	public LettuceMigrateArgs(final T[] keys, final byte[] password) {
		this(keys, SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final T[] keys, final String username, final String password) {
		this(keys, password);
	}

	public LettuceMigrateArgs(final T[] keys, final byte[] username, final byte[] password) {
		this(keys, SafeEncoder.encode(username), SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final String password) {
		this(migrateOperation);
		auth(password);
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final byte[] password) {
		this(migrateOperation, SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final String username, final String password) {
		this(migrateOperation);
		auth(password);
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final byte[] username, final byte[] password) {
		this(migrateOperation, SafeEncoder.encode(username), SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final T[] keys, final String password) {
		this(migrateOperation, keys);
		auth(password);
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final T[] keys, final byte[] password) {
		this(migrateOperation, keys, SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final T[] keys, final String username,
							  final String password) {
		this(migrateOperation, keys);
		auth(password);
	}

	public LettuceMigrateArgs(final MigrateOperation migrateOperation, final T[] keys, final byte[] username,
							  final byte[] password) {
		this(migrateOperation, keys, SafeEncoder.encode(username), SafeEncoder.encode(password));
	}

	public LettuceMigrateArgs(final T[] keys) {
		super();
		keys(keys);
	}

}

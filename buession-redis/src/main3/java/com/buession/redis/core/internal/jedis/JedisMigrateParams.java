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
package com.buession.redis.core.internal.jedis;

import com.buession.core.validator.Validate;
import com.buession.redis.core.command.args.MigrateArgument;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.params.MigrateParams;

/**
 * Jedis {@link MigrateParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisMigrateParams extends MigrateParams {

	/**
	 * 构造函数
	 */
	public JedisMigrateParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param migrateOperation
	 * 		迁移方式
	 */
	public JedisMigrateParams(final MigrateArgument.Mode migrateOperation) {
		super();
		mode(this, migrateOperation);
	}

	/**
	 * 构造函数
	 *
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final String password) {
		super();
		auth(password);
	}

	/**
	 * 构造函数
	 *
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final byte[] password) {
		this(SafeEncoder.encode(password));
	}

	/**
	 * 构造函数
	 *
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final String username, final String password) {
		super();
		auth2(username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final byte[] username, final byte[] password) {
		this(SafeEncoder.encode(username), SafeEncoder.encode(password));
	}

	/**
	 * 构造函数
	 *
	 * @param migrateOperation
	 * 		迁移方式
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final MigrateArgument.Mode migrateOperation, final String password) {
		this(migrateOperation);
		auth(password);
	}

	/**
	 * 构造函数
	 *
	 * @param migrateOperation
	 * 		迁移方式
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final MigrateArgument.Mode migrateOperation, final byte[] password) {
		this(migrateOperation, SafeEncoder.encode(password));
	}

	/**
	 * 构造函数
	 *
	 * @param migrateOperation
	 * 		迁移方式
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final MigrateArgument.Mode migrateOperation, final String username,
							  final String password) {
		this(migrateOperation);
		auth2(username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param migrateOperation
	 * 		迁移方式
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public JedisMigrateParams(final MigrateArgument.Mode migrateOperation, final byte[] username,
							  final byte[] password) {
		this(migrateOperation, SafeEncoder.encode(username), SafeEncoder.encode(password));
	}

	/**
	 * 从 {@link MigrateArgument} 创建 {@link MigrateParams} 实例
	 *
	 * @param migrateArgument
	 *        {@link MigrateArgument}
	 *
	 * @return {@link JedisMigrateParams} 实例
	 *
	 * @since 3.0.0
	 */
	public static JedisMigrateParams from(final MigrateArgument migrateArgument) {
		final JedisMigrateParams migrateParams = new JedisMigrateParams();

		mode(migrateParams, migrateArgument.getMode());

		if(Validate.hasText(migrateArgument.getPassword())){
			if(Validate.hasText(migrateArgument.getUsername())){
				migrateParams.auth2(migrateArgument.getUsername(), migrateArgument.getPassword());
			}else{
				migrateParams.auth(migrateArgument.getPassword());
			}
		}

		return migrateParams;
	}

	private static void mode(final MigrateParams migrateParams, final MigrateArgument.Mode migrateOperation) {
		if(migrateOperation == MigrateArgument.Mode.COPY){
			migrateParams.copy();
		}else if(migrateOperation == MigrateArgument.Mode.REPLACE){
			migrateParams.replace();
		}
	}

}
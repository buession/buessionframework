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
package com.buession.redis.exception;

import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.ProtocolCommand;

/**
 * 命令不支持异常
 *
 * @author Yong.Teng
 */
public class NotSupportedCommandException extends RedisException {

	private final static long serialVersionUID = -3098408677930013922L;

	private Type type;

	public NotSupportedCommandException() {
		super();
	}

	public NotSupportedCommandException(ProtocolCommand command) {
		super("Not supported command: " + command);
	}

	public NotSupportedCommandException(Type type, ProtocolCommand command) {
		super("Not supported command: " + command + " in " + type);
	}

	public NotSupportedCommandException(RedisMode mode, ProtocolCommand command) {
		super("Not supported command: " + command + " with " + mode + " mode");
	}

	public NotSupportedCommandException(RedisMode mode, Type type, ProtocolCommand command) {
		super("Not supported command: " + command + " in " + type + " with " + mode + " mode");
	}

	public NotSupportedCommandException(String message) {
		super(message);
	}

	public NotSupportedCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSupportedCommandException(Throwable cause) {
		super(cause);
	}

	public enum Type {

		TRANSACTION(8),

		PIPELINE(4),

		NORMAL(2),

		ALL(1);

		private final int code;

		Type(final int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

	}

}

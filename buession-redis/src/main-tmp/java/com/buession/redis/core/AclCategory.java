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
package com.buession.redis.core;

import com.buession.core.validator.Validate;
import com.buession.redis.utils.SafeEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Redis ACL categories.
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public enum AclCategory implements Keyword {

	/**
	 * command affects keyspace
	 */
	KEYSPACE,

	/**
	 * read command
	 */
	READ,

	/**
	 * write command
	 */
	WRITE,

	/**
	 * command for sets
	 */
	SET,

	/**
	 * command for sorted sets
	 */
	SORTEDSET,

	/**
	 * command for lists
	 */
	LIST,

	/**
	 * command for hash ops
	 */
	HASH,

	/**
	 * command for strings
	 */
	STRING,

	/**
	 * command for bitmaps
	 */
	BITMAP,

	/**
	 * command for hyperloglog
	 */
	HYPERLOGLOG,

	/**
	 * geo command
	 */
	GEO,

	/**
	 * streaming command
	 */
	STREAM,

	/**
	 * pubsub command
	 */
	PUBSUB,

	/**
	 * admin command
	 */
	ADMIN,

	/**
	 * fast command
	 */
	FAST,

	/**
	 * slow command
	 */
	SLOW,

	/**
	 * blocking command
	 */
	BLOCKING,

	/**
	 * dangerous command
	 */
	DANGEROUS,

	/**
	 * connection-establishing command
	 */
	CONNECTION,

	/**
	 * transactional command
	 */
	TRANSACTION,

	/**
	 * scripting command
	 */
	SCRIPTING;

	private final byte[] raw;

	AclCategory() {
		this.raw = name().getBytes(StandardCharsets.US_ASCII);
	}

	@Override
	public String getValue() {
		return name();
	}

	@Override
	public byte[] getRaw() {
		return raw;
	}

	public static AclCategory from(final String str) {
		if(Validate.hasText(str)){
			String upperStr = str.toUpperCase();

			for(AclCategory category : values()){
				if(category.getValue().equals(upperStr)){
					return category;
				}
			}
		}

		return null;
	}

	public static AclCategory from(final byte[] raw) {
		if(raw != null){
			for(AclCategory category : values()){
				if(Objects.equals(category.getRaw(), raw)){
					return category;
				}
			}
		}

		return null;
	}

}
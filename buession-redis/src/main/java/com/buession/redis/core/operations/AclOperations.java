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
package com.buession.redis.core.operations;

import com.buession.core.converter.BinaryEnumConverter;
import com.buession.core.converter.EnumConverter;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.command.AclCommands;
import com.buession.redis.core.command.Command;

import java.util.List;

/**
 * 权限运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/acl/" target="_blank">https://redis.io/docs/latest/commands/acl/</a></p>
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface AclOperations extends AclCommands, RedisOperations {

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param categoryName
	 * 		Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 *
	 * @since 3.0.0
	 */
	default List<Command> aclCat(final String categoryName) {
		final AclCategory aclCategory = (new EnumConverter<>(AclCategory.class)).convert(categoryName);
		return aclCat(aclCategory);
	}

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param categoryName
	 * 		Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 *
	 * @since 3.0.0
	 */
	default List<Command> aclCat(final byte[] categoryName) {
		final AclCategory aclCategory = (new BinaryEnumConverter<>(AclCategory.class)).convert(categoryName);
		return aclCat(aclCategory);
	}

}

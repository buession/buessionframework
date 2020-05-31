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
package com.buession.redis.core.command;

/**
 * 内部命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/internal/index.html" target="_blank">http://redisdoc.com/internal/index.html</a>
 * </p>
 *
 * @author Yong.Teng
 */
public interface BinaryInternalCommands extends BinaryRedisCommands {

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync
	 * .html</a></p>
	 *
	 * @param masterRunId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final byte[] masterRunId, final int offset);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync
	 * .html</a></p>
	 *
	 * @param masterRunId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final byte[] masterRunId, final long offset);

}

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
package com.buession.redis.core.operations;

import com.buession.lang.Status;
import com.buession.redis.core.command.TransactionCommands;

/**
 * 事务运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/transaction/index.html" target="_blank">http://redisdoc.com/transaction/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface TransactionOperations extends TransactionCommands, RedisOperations {

	/**
	 * 监视一 key ，如果在事务执行之前这个 key 被其他命令所改动，那么事务将被打断
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/transaction/watch.html" target="_blank">http://redisdoc.com/transaction/watch.html</a></p>
	 *
	 * @param key
	 * 		key
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status watch(final String key){
		return watch(new String[]{key});
	}

	/**
	 * 监视一 key ，如果在事务执行之前这个 key 被其他命令所改动，那么事务将被打断
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/transaction/watch.html" target="_blank">http://redisdoc.com/transaction/watch.html</a></p>
	 *
	 * @param key
	 * 		key
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status watch(final byte[] key){
		return watch(new byte[][]{key});
	}

}

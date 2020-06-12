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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.redis.core.command.BinaryBitMapCommands;
import com.buession.redis.core.command.BitMapCommands;

/**
 * 位图运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/bitmap/index.html" target="_blank">http://redisdoc.com/bitmap/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface BitMapOperations extends BitMapCommands, BinaryBitMapCommands, RedisOperations {

	/**
	 * 对一个二进制位的字符串 key 进行位元操作，并将结果保存到 destKey 上，
	 * 除了 Operation.NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入
	 *
	 * @param operation
	 * 		运算操作
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的字符串的长度
	 */
	default Long bitOp(final Operation operation, final String destKey, final String key){
		return bitOp(operation, destKey, new String[]{key});
	}

	/**
	 * 对一个二进制位的字符串 key 进行位元操作，并将结果保存到 destKey 上，
	 * 除了 Operation.NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入
	 *
	 * @param operation
	 * 		运算操作
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的字符串的长度
	 */
	default Long bitOp(final Operation operation, final byte[] destKey, final byte[] key){
		return bitOp(operation, destKey, new byte[][]{key});
	}

}

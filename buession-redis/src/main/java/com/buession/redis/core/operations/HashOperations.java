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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.StatusUtils;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.command.HashCommands;

import java.util.List;
import java.util.Map;

/**
 * 哈希表运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/hash/index.html" target="_blank">http://redisdoc.com/hash/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface HashOperations extends HashCommands, RedisOperations {

	/**
	 * 删除哈希表 key 中的指定域
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 操作结果，成功删除的域的数量 为 0 时，返回 Status.FAILURE；否则返回 Status.SUCCESS
	 */
	default Status hDel(final String key, final String field){
		return StatusUtils.valueOf(hDel(key, new String[]{field}) > 0);
	}

	/**
	 * 删除哈希表 key 中的指定域
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 操作结果，成功删除的域的数量 为 0 时，返回 Status.FAILURE；否则返回 Status.SUCCESS
	 */
	default Status hDel(final byte[] key, final byte[] field){
		return StatusUtils.valueOf(hDel(key, new byte[][]{field}) > 0);
	}

	/**
	 * 删除哈希表 key 中的指定域
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 操作结果，成功删除的域的数量 为 0 时，返回 Status.FAILURE；否则返回 Status.SUCCESS
	 */
	default Status hDelete(final String key, final String field){
		return hDel(key, field);
	}

	/**
	 * 删除哈希表 key 中的指定域
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 操作结果，成功删除的域的数量 为 0 时，返回 Status.FAILURE；否则返回 Status.SUCCESS
	 */
	default Status hDelete(final byte[] key, final byte[] field){
		return hDel(key, field);
	}

	/**
	 * 删除哈希表 key 中的一个或多个指定域
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 被成功删除的域的数量
	 */
	default Long hDelete(final String key, final String... fields){
		return hDel(key, fields);
	}

	/**
	 * 删除哈希表 key 中的一个或多个指定域
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 被成功删除的域的数量
	 */
	default Long hDelete(final byte[] key, final byte[]... fields){
		return hDel(key, fields);
	}

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final String key, final String field);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final byte[] key, final byte[] field);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 *
	 * @see java.lang.Class
	 */
	<V> V hGetObject(final String key, final String field, final Class<V> clazz);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 *
	 * @see java.lang.Class
	 */
	<V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V hGetObject(final String key, final String field, final TypeReference<V> type);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<String, V> hGetAllObject(final String key);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<byte[], V> hGetAllObject(final byte[] key);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 *
	 * @see java.lang.Class
	 */
	<V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 *
	 * @see java.lang.Class
	 */
	<V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> Map<byte[], V> hGetAllObject(final byte[] key, TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> hMGetObject(final String key, final String... fields);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> hMGetObject(final byte[] key, final byte[]... fields);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hMSet(final String key, final List<KeyValue<String, V>> data);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hMSet(final byte[] key, final List<KeyValue<byte[], V>> data);

	/**
	 * 将哈希表 key 中域 field 的值设置为 value。
	 * 如果给定的哈希表并不存在，那么一个新的哈希表；
	 * 如果域 field 已经存在于哈希表中，那么 value 将覆盖旧值
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果
	 */
	<V> Status hSet(final String key, final String field, final V value);

	/**
	 * 将哈希表 key 中域 field 的值设置为 value。
	 * 如果给定的哈希表并不存在，那么一个新的哈希表；
	 * 如果域 field 已经存在于哈希表中，那么 value 将覆盖旧值
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果
	 */
	<V> Status hSet(final byte[] key, final byte[] field, final V value);

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，在给定域已经存在而放弃执行设置操作时返回 Status.FAILURE
	 */
	<V> Status hSetNx(final String key, final String field, final V value);

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，在给定域已经存在而放弃执行设置操作时返回 Status.FAILURE
	 */
	<V> Status hSetNx(final byte[] key, final byte[] field, final V value);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final String key);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final byte[] key);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> hValsObject(final String key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> hValsObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> hValsObject(final String key, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> hValsObject(final byte[] key, final TypeReference<V> type);

}

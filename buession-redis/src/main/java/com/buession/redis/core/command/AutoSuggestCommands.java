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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.Suggestion;
import com.buession.redis.core.command.args.autosuggest.SugGetArgument;

import java.util.List;

/**
 * 自动提示命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=suggestion" target="_blank">https://redis.io/docs/latest/commands/?group=suggestion</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface AutoSuggestCommands extends RedisCommands {

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final String key, final String value, final double score);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final byte[] key, final byte[] value, final double score);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 * @param incr
	 * 		为 true 时且词条已存在，则将提供的 score 累加到现有分数上，而不是覆盖它
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final String key, final String value, final double score, final boolean incr);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 * @param incr
	 * 		为 true 时且词条已存在，则将提供的 score 累加到现有分数上，而不是覆盖它
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 * @param incr
	 * 		为 true 时且词条已存在，则将提供的 score 累加到现有分数上，而不是覆盖它
	 * @param payload
	 * 		与词条关联的额外数据
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final String key, final String value, final double score, final boolean incr, final String payload);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 * @param incr
	 * 		为 true 时且词条已存在，则将提供的 score 累加到现有分数上，而不是覆盖它
	 * @param payload
	 * 		与词条关联的额外数据
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr, final byte[] payload);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 * @param payload
	 * 		与词条关联的额外数据
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final String key, final String value, final double score, final String payload);

	/**
	 * 将一个建议词条添加到自动补全索引
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugadd/" target="_blank">https://redis.io/docs/latest/commands/ft.sugadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 * @param score
	 * 		分值
	 * @param payload
	 * 		与词条关联的额外数据
	 *
	 * @return The number of elements added to the suggestion dictionary.
	 */
	Long ftSugAdd(final byte[] key, final byte[] value, final double score, final byte[] payload);

	/**
	 * 从自动补全索引中删除词条
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugdel/" target="_blank">https://redis.io/docs/latest/commands/ft.sugdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 *
	 * @return 删除结果
	 */
	Status ftSugDel(final String key, final String value);

	/**
	 * 从自动补全索引中删除词条
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugdel/" target="_blank">https://redis.io/docs/latest/commands/ft.sugdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		词条
	 *
	 * @return 删除结果
	 */
	Status ftSugDel(final byte[] key, final byte[] value);

	/**
	 * 根据用户输入的前缀，从索引中检索匹配的建议词条列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugget/" target="_blank">https://redis.io/docs/latest/commands/ft.sugget/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param prefix
	 * 		词条前缀
	 *
	 * @return 匹配的建议词条
	 */
	List<Suggestion> ftSugGet(final String key, final String prefix);

	/**
	 * 根据用户输入的前缀，从索引中检索匹配的建议词条列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugget/" target="_blank">https://redis.io/docs/latest/commands/ft.sugget/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param prefix
	 * 		词条前缀
	 *
	 * @return 匹配的建议词条
	 */
	List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix);

	/**
	 * 根据用户输入的前缀，从索引中检索匹配的建议词条列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugget/" target="_blank">https://redis.io/docs/latest/commands/ft.sugget/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param prefix
	 * 		词条前缀
	 * @param argument
	 * 		参数
	 *
	 * @return 匹配的建议词条
	 */
	List<Suggestion> ftSugGet(final String key, final String prefix, final SugGetArgument argument);

	/**
	 * 根据用户输入的前缀，从索引中检索匹配的建议词条列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.sugget/" target="_blank">https://redis.io/docs/latest/commands/ft.sugget/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param prefix
	 * 		词条前缀
	 * @param argument
	 * 		参数
	 *
	 * @return 匹配的建议词条
	 */
	List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix, final SugGetArgument argument);

	/**
	 * 获取自动补全索引中词条总数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.suglen/" target="_blank">https://redis.io/docs/latest/commands/ft.suglen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 自动补全索引中词条总数
	 */
	Long ftSugLen(final String key);

	/**
	 * 获取自动补全索引中词条总数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ft.suglen/" target="_blank">https://redis.io/docs/latest/commands/ft.suglen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 自动补全索引中词条总数
	 */
	Long ftSugLen(final byte[] key);

}

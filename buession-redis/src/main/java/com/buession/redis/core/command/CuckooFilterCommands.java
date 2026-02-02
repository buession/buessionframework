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
import com.buession.redis.utils.ArgStringBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 布谷鸟过滤器命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=cf" target="_blank">https://redis.io/docs/latest/commands/?group=cf</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface CuckooFilterCommands extends RedisCommands {

	/**
	 * 向布谷鸟过滤器中添加一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.add/" target="_blank">https://redis.io/docs/latest/commands/cf.add/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return SUCCESS 表示添加成功，FAILURE 表示元素已经存在
	 */
	Status cfAdd(final String key, final String item);

	/**
	 * 向布谷鸟过滤器中添加一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.add/" target="_blank">https://redis.io/docs/latest/commands/cf.add/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return SUCCESS 表示添加成功，FAILURE 表示元素已经存在
	 */
	Status cfAdd(final byte[] key, final byte[] item);

	/**
	 * 向布谷鸟过滤器中添加一个元素，当元素不存在时
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.addnx/" target="_blank">https://redis.io/docs/latest/commands/cf.addnx/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return SUCCESS 表示添加成功，FAILURE 表示元素已经存在
	 */
	Status cfAddNx(final String key, final String item);

	/**
	 * 向布谷鸟过滤器中添加一个元素，当元素不存在时
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.addnx/" target="_blank">https://redis.io/docs/latest/commands/cf.addnx/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return SUCCESS 表示添加成功，FAILURE 表示元素已经存在
	 */
	Status cfAddNx(final byte[] key, final byte[] item);

	/**
	 * 获取布谷鸟过滤器中元素添加次数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.count/" target="_blank">https://redis.io/docs/latest/commands/cf.count/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return 元素添加次数
	 */
	Long cfCount(final String key, final String item);

	/**
	 * 获取布谷鸟过滤器中元素添加次数
	 *
	 * <p>详情说明 <a href="hhttps://redis.io/docs/latest/commands/cf.count/" target="_blank">https://redis.io/docs/latest/commands/cf.count/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return 元素添加次数
	 */
	Long cfCount(final byte[] key, final byte[] item);

	/**
	 * 获取布谷鸟过滤器中元素添加次数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.del/" target="_blank">https://redis.io/docs/latest/commands/cf.del/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return 操作结果
	 */
	Status cfDel(final String key, final String item);

	/**
	 * 获取布谷鸟过滤器中元素添加次数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.del/" target="_blank">https://redis.io/docs/latest/commands/cf.del/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return 操作结果
	 */
	Status cfDel(final byte[] key, final byte[] item);

	/**
	 * 检测元素 item 是否存在布谷鸟过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.exists/" target="_blank"https://redis.io/docs/latest/commands/cf.exists/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		待检测的元素
	 *
	 * @return true / false
	 */
	Boolean cfExists(final String key, final String item);

	/**
	 * 检测元素 item 是否存在布谷鸟过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.exists/" target="_blank">https://redis.io/docs/latest/commands/cf.exists/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param item
	 * 		待检测的元素
	 *
	 * @return true / false
	 */
	Boolean cfExists(final byte[] key, final byte[] item);

	/**
	 * 获取指定布谷鸟过滤器 key 的详细元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.info/" target="_blank">https://redis.io/docs/latest/commands/cf.info/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 *
	 * @return 布谷鸟过滤器 key 的详细元信息
	 */
	Map<String, Object> cfInfo(final String key);

	/**
	 * 获取指定布谷鸟过滤器 key 的详细元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.info/" target="_blank">https://redis.io/docs/latest/commands/cf.info/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 *
	 * @return 布谷鸟过滤器 key 的详细元信息
	 */
	Map<String, Object> cfInfo(final byte[] key);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insert/" target="_blank">https://redis.io/docs/latest/commands/cf.insert/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsert(final String key, final String... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insert/" target="_blank">https://redis.io/docs/latest/commands/cf.insert/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsert(final byte[] key, final byte[]... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insert/" target="_blank">https://redis.io/docs/latest/commands/cf.insert/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param argument
	 * 		CFInsert 参数
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsert(final String key, final CFInsertArgument argument, final String... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insert/" target="_blank">https://redis.io/docs/latest/commands/cf.insert/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param argument
	 * 		CFInsert 参数
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsert(final byte[] key, final CFInsertArgument argument, final byte[]... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素，仅当元素尚未存在于布谷鸟过滤器中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insertnx/" target="_blank">https://redis.io/docs/latest/commands/cf.insertnx/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsertNx(final String key, final String... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素，仅当元素尚未存在于布谷鸟过滤器中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insertnx/" target="_blank">https://redis.io/docs/latest/commands/cf.insertnx/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsertNx(final byte[] key, final byte[]... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素，仅当元素尚未存在于布谷鸟过滤器中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insertnx/" target="_blank">https://redis.io/docs/latest/commands/cf.insertnx/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param argument
	 * 		CFInsert 参数
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsertNx(final String key, final CFInsertArgument argument, final String... items);

	/**
	 * 向布谷鸟过滤器 key 中批量添加元素，仅当元素尚未存在于布谷鸟过滤器中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.insertnx/" target="_blank">https://redis.io/docs/latest/commands/cf.insertnx/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param argument
	 * 		CFInsert 参数
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfInsertNx(final byte[] key, final CFInsertArgument argument, final byte[]... items);

	/**
	 * 从外部导入布谷鸟过滤器数据块
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.loadchunk/" target="_blank">https://redis.io/docs/latest/commands/cf.loadchunk/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的目标名称
	 * @param iterator
	 * 		迭代器编号
	 * @param data
	 * 		由 BF.SCANDUMP 返回的二进制数据块
	 *
	 * @return 加载结果
	 */
	Status cfLoadchunk(final String key, final long iterator, final byte[] data);

	/**
	 * 从外部导入布谷鸟过滤器数据块
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.loadchunk/" target="_blank">https://redis.io/docs/latest/commands/cf.loadchunk/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的目标名称
	 * @param iterator
	 * 		迭代器编号
	 * @param data
	 * 		由 BF.SCANDUMP 返回的二进制数据块
	 *
	 * @return 加载结果
	 */
	Status cfLoadchunk(final byte[] key, final long iterator, final byte[] data);

	/**
	 * 批量检测元素是否存在于布谷鸟过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.mexists/" target="_blank">https://redis.io/docs/latest/commands/cf.mexists/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param items
	 * 		待检测的元素
	 *
	 * @return 返回一个数组，每个元素对应元素的检测结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfMExists(final String key, final String... items);

	/**
	 * 批量检测元素是否存在于布谷鸟过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.mexists/" target="_blank">https://redis.io/docs/latest/commands/cf.mexists/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param items
	 * 		待检测的元素
	 *
	 * @return 返回一个数组，每个元素对应元素的检测结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> cfMExists(final byte[] key, final byte[]... items);

	/**
	 * 控制布谷鸟过滤器的关键参数：预期容量（capacity）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.reserve/" target="_blank">https://redis.io/docs/latest/commands/cf.reserve/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param argument
	 * 		CF.RESERVE 参数
	 *
	 * @return 操作结果
	 */
	Status cfReserve(final String key, final CFReserveArgument argument);

	/**
	 * 控制布谷鸟过滤器的关键参数：预期容量（capacity）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.reserve/" target="_blank">https://redis.io/docs/latest/commands/cf.reserve/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param argument
	 * 		CF.RESERVE 参数
	 *
	 * @return 操作结果
	 */
	Status cfReserve(final byte[] key, final CFReserveArgument argument);

	/**
	 * 采用分块迭代方式导出布谷鸟过滤器 key 的数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.scandump/" target="_blank">https://redis.io/docs/latest/commands/cf.scandump/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param iterator
	 * 		迭代器编号
	 *
	 * @return 二进制数据块
	 */
	Map<Long, byte[]> cfScandump(final String key, final long iterator);

	/**
	 * 采用分块迭代方式导出布谷鸟过滤器 key 的数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cf.scandump/" target="_blank">https://redis.io/docs/latest/commands/cf.scandump/</a></p>
	 *
	 * @param key
	 * 		布谷鸟过滤器的名称
	 * @param iterator
	 * 		迭代器编号
	 *
	 * @return 二进制数据块
	 */
	Map<Long, byte[]> cfScandump(final byte[] key, final long iterator);

	/**
	 * CF.INSERT 参数
	 */
	final class CFInsertArgument {

		/**
		 * 初始容量
		 */
		private Long capacity;

		/**
		 * 是否当 key 不存在时不自动创建
		 */
		private Boolean noCreate;

		private CFInsertArgument() {
		}

		/**
		 * 获取初始容量
		 *
		 * @return 初始容量
		 */
		public Long getCapacity() {
			return capacity;
		}

		/**
		 * 获取是否当 key 不存在时不自动创建
		 *
		 * @return 是否当 key 不存在时不自动创建
		 */
		public Boolean isNoCreate() {
			return noCreate;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().add("CAPACITY", capacity)
					.append(Objects.equals(noCreate, true) ? "NOCREATE" : null).build();
		}

		public static class Builder extends BaseArgumentBuilder<CFInsertArgument> {

			/**
			 * 构造函数
			 */
			private Builder() {
				super(new CFInsertArgument());
			}

			/**
			 * 创建 {@link CFInsertArgument} 构建器 {@link Builder}
			 *
			 * @return {@link CFInsertArgument} 构建器 {@link Builder}
			 */
			public static Builder create() {
				return new Builder();
			}

			/**
			 * 设置初始容量
			 *
			 * @param capacity
			 * 		初始容量
			 *
			 * @return {@link Builder}
			 */
			public Builder capacity(final Long capacity) {
				arguament.capacity = capacity;
				return this;
			}

			/**
			 * 设置当 key 不存在时不自动创建
			 *
			 * @return {@link Builder}
			 */
			public Builder noCreate() {
				arguament.noCreate = true;
				return this;
			}

		}

	}

	/**
	 * CF.RESERVE 参数
	 */
	final class CFReserveArgument {

		/**
		 * 初始容量
		 */
		private final long capacity;

		/**
		 * 每个桶能存储的指纹数量
		 */
		private Long bucketSize;

		/**
		 * 插入时最大“踢出”尝试次数
		 */
		private Integer maxIterations;

		/**
		 * 扩容倍数
		 */
		private Integer expansion;

		/**
		 * 构造函数
		 *
		 * @param capacity
		 * 		预计要插入的元素数量上限
		 */
		private CFReserveArgument(final long capacity) {
			this.capacity = capacity;
		}

		/**
		 * 获取初始容量
		 *
		 * @return 初始容量
		 */
		public Long getCapacity() {
			return capacity;
		}

		/**
		 * 获取每个桶能存储的指纹数量
		 *
		 * @return 每个桶能存储的指纹数量
		 */
		public Long getBucketSize() {
			return bucketSize;
		}

		/**
		 * 获取插入时最大“踢出”尝试次数
		 *
		 * @return 插入时最大“踢出”尝试次数
		 */
		public Integer getMaxIterations() {
			return maxIterations;
		}

		/**
		 * 获取扩容倍数
		 *
		 * @return 扩容倍数
		 */
		public Integer getExpansion() {
			return expansion;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().append(capacity).add("BUCKETSIZE", bucketSize)
					.add("MAXITERATIONS", maxIterations).add("EXPANSION", expansion).build();
		}

		public static class Builder extends BaseArgumentBuilder<CFReserveArgument> {

			/**
			 * 构造函数
			 *
			 * @param capacity
			 * 		预计要插入的元素数量上限
			 */
			private Builder(final long capacity) {
				super(new CFReserveArgument(capacity));
			}

			/**
			 * 创建 {@link CFReserveArgument} 构建器 {@link Builder}
			 *
			 * @param capacity
			 * 		预计要插入的元素数量上限
			 *
			 * @return {@link CFReserveArgument} 构建器 {@link Builder}
			 */
			public static Builder create(final long capacity) {
				return new Builder(capacity);
			}

			/**
			 * 设置每个桶能存储的指纹数量
			 *
			 * @param bucketSize
			 * 		每个桶能存储的指纹数量
			 *
			 * @return {@link Builder}
			 */
			public Builder bucketSize(final Long bucketSize) {
				arguament.bucketSize = bucketSize;
				return this;
			}

			/**
			 * 设置插入时最大“踢出”尝试次数
			 *
			 * @param maxIterations
			 * 		插入时最大“踢出”尝试次数
			 *
			 * @return {@link Builder}
			 */
			public Builder maxIterations(final Integer maxIterations) {
				arguament.maxIterations = maxIterations;
				return this;
			}

			/**
			 * 设置扩容倍数
			 *
			 * @param expansion
			 * 		扩容倍数
			 *
			 * @return {@link Builder}
			 */
			public Builder expansion(final Integer expansion) {
				arguament.expansion = expansion;
				return this;
			}

		}

	}

}

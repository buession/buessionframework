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
 * 布隆过滤器命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=bf" target="_blank">https://redis.io/docs/latest/commands/?group=bf</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface BloomFilterCommands extends RedisCommands {

	/**
	 * 向布隆过滤器中添加一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.add/" target="_blank">https://redis.io/docs/latest/commands/bf.add/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return SUCCESS 表示添加成功，FAILURE 表示元素已经存在
	 */
	Status bfAdd(final String key, final String item);

	/**
	 * 向布隆过滤器中添加一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.add/" target="_blank">https://redis.io/docs/latest/commands/bf.add/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param item
	 * 		要添加的元素
	 *
	 * @return SUCCESS 表示添加成功，FAILURE 表示元素已经存在
	 */
	Status bfAdd(final byte[] key, final byte[] item);

	/**
	 * 获取指定布隆过滤器 key 中已插入元素的大致数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.card/" target="_blank">https://redis.io/docs/latest/commands/bf.card/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 *
	 * @return 如果 key 不存在，返回 0；如果 key 存在但不是布隆过滤器，返回错误；否则，该布隆过滤器中当前估计的元素数量
	 */
	Long bfCard(final String key);

	/**
	 * 获取指定布隆过滤器 key 中已插入元素的大致数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.card/" target="_blank">https://redis.io/docs/latest/commands/bf.card/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 *
	 * @return 如果 key 不存在，返回 0；如果 key 存在但不是布隆过滤器，返回错误；否则，该布隆过滤器中当前估计的元素数量
	 */
	Long bfCard(final byte[] key);

	/**
	 * 检测元素 item 是否存在布隆过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.exists/" target="_blank">https://redis.io/docs/latest/commands/bf.exists/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param item
	 * 		待检测的元素
	 *
	 * @return true / false
	 */
	Boolean bfExists(final String key, final String item);

	/**
	 * 检测元素 item 是否存在布隆过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.exists/" target="_blank">https://redis.io/docs/latest/commands/bf.exists/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param item
	 * 		待检测的元素
	 *
	 * @return true / false
	 */
	Boolean bfExists(final byte[] key, final byte[] item);

	/**
	 * 获取指定布隆过滤器 key 的详细元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.info/" target="_blank">https://redis.io/docs/latest/commands/bf.info/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 *
	 * @return 布隆过滤器 key 的详细元信息
	 */
	Map<String, Object> bfInfo(final String key);

	/**
	 * 获取指定布隆过滤器 key 的详细元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.info/" target="_blank">https://redis.io/docs/latest/commands/bf.info/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 *
	 * @return 布隆过滤器 key 的详细元信息
	 */
	Map<String, Object> bfInfo(final byte[] key);

	/**
	 * 向布隆过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.insert/" target="_blank">https://redis.io/docs/latest/commands/bf.insert/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfInsert(final String key, final String... items);

	/**
	 * 向布隆过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.insert/" target="_blank">https://redis.io/docs/latest/commands/bf.insert/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfInsert(final byte[] key, final byte[]... items);

	/**
	 * 向布隆过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.insert/" target="_blank">https://redis.io/docs/latest/commands/bf.insert/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param bfInsertArgument
	 * 		BFInsert 参数
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfInsert(final String key, final BFInsertArgument bfInsertArgument, final String... items);

	/**
	 * 向布隆过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.insert/" target="_blank">https://redis.io/docs/latest/commands/bf.insert/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param bfInsertArgument
	 * 		BFInsert 参数
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfInsert(final byte[] key, final BFInsertArgument bfInsertArgument, final byte[]... items);

	/**
	 * 从外部导入布隆过滤器数据块
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.loadchunk/" target="_blank">https://redis.io/docs/latest/commands/bf.loadchunk/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的目标名称
	 * @param iterator
	 * 		迭代器编号
	 * @param data
	 * 		由 BF.SCANDUMP 返回的二进制数据块
	 *
	 * @return 加载结果
	 */
	Status bfLoadchunk(final String key, final long iterator, final byte[] data);

	/**
	 * 从外部导入布隆过滤器数据块
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.loadchunk/" target="_blank">https://redis.io/docs/latest/commands/bf.loadchunk/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的目标名称
	 * @param iterator
	 * 		迭代器编号
	 * @param data
	 * 		由 BF.SCANDUMP 返回的二进制数据块
	 *
	 * @return 加载结果
	 */
	Status bfLoadchunk(final byte[] key, final long iterator, final byte[] data);

	/**
	 * 向布隆过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.madd/" target="_blank">https://redis.io/docs/latest/commands/bf.madd/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfMAdd(final String key, final String... items);

	/**
	 * 向布隆过滤器 key 中批量添加元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.madd/" target="_blank">https://redis.io/docs/latest/commands/bf.madd/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param items
	 * 		待添加的元素
	 *
	 * @return 返回一个数组，每个元素对应输入项的插入结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfMAdd(final byte[] key, final byte[]... items);

	/**
	 * 批量检测元素是否存在于布隆过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.mexists/" target="_blank">https://redis.io/docs/latest/commands/bf.mexists/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param items
	 * 		待检测的元素
	 *
	 * @return 返回一个数组，每个元素对应元素的检测结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfMExists(final String key, final String... items);

	/**
	 * 批量检测元素是否存在于布隆过滤器 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.mexists/" target="_blank">https://redis.io/docs/latest/commands/bf.mexists/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param items
	 * 		待检测的元素
	 *
	 * @return 返回一个数组，每个元素对应元素的检测结果；true 表示添加成功，false 表示添加失败
	 */
	List<Boolean> bfMExists(final byte[] key, final byte[]... items);

	/**
	 * 控制布隆过滤器的关键参数：预期容量（capacity） 和 可接受的误判率（error rate）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.reserve/" target="_blank">https://redis.io/docs/latest/commands/bf.reserve/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param bfInsertArgument
	 * 		BF.RESERVE 参数
	 *
	 * @return 操作结果
	 */
	Status bfReserve(final String key, final BFReserveArgument bfInsertArgument);

	/**
	 * 控制布隆过滤器的关键参数：预期容量（capacity） 和 可接受的误判率（error rate）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.reserve/" target="_blank">https://redis.io/docs/latest/commands/bf.reserve/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param bfInsertArgument
	 * 		BF.RESERVE 参数
	 *
	 * @return 操作结果
	 */
	Status bfReserve(final byte[] key, final BFReserveArgument bfInsertArgument);

	/**
	 * 采用分块迭代方式导出布隆过滤器 key 的数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.scandump/" target="_blank">https://redis.io/docs/latest/commands/bf.scandump/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param iterator
	 * 		迭代器编号
	 *
	 * @return 二进制数据块
	 */
	Map<Long, byte[]> bfScandump(final String key, final long iterator);

	/**
	 * 采用分块迭代方式导出布隆过滤器 key 的数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/bf.scandump/" target="_blank">https://redis.io/docs/latest/commands/bf.scandump/</a></p>
	 *
	 * @param key
	 * 		布隆过滤器的名称
	 * @param iterator
	 * 		迭代器编号
	 *
	 * @return 二进制数据块
	 */
	Map<Long, byte[]> bfScandump(final byte[] key, final long iterator);

	/**
	 * BF.INSERT 参数
	 */
	class BFInsertArgument {

		/**
		 * 初始容量
		 */
		private Long capacity;

		/**
		 * 期望的误判率
		 */
		private Double errorRate;

		/**
		 * 扩容倍数
		 */
		private Integer expansion;

		/**
		 * 是否当 key 不存在时不自动创建
		 */
		private Boolean noCreate;

		/**
		 * 是否禁用自动扩容
		 */
		private Boolean nonScaling;

		private BFInsertArgument() {
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
		 * 获取期望的误判率
		 *
		 * @return 期望的误判率
		 */
		public Double getErrorRate() {
			return errorRate;
		}

		/**
		 * -
		 *
		 * @return -
		 */
		public Integer getExpansion() {
			return expansion;
		}

		/**
		 * 获取是否当 key 不存在时不自动创建
		 *
		 * @return 是否当 key 不存在时不自动创建
		 */
		public Boolean isNoCreate() {
			return noCreate;
		}

		/**
		 * 获取是否禁用自动扩容
		 *
		 * @return 是否是否禁用自动扩容
		 */
		public Boolean isNonScaling() {
			return nonScaling;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().add("CAPACITY", capacity).add("ERROR", errorRate)
					.append(Objects.equals(noCreate, true) ? "NOCREATE" : null)
					.append(Objects.equals(nonScaling, true) ? "NONSCALING" : null).build();
		}

		public static class Builder extends BaseArgumentBuilder<BFInsertArgument> {

			/**
			 * 构造函数
			 */
			private Builder() {
				super(new BFInsertArgument());
			}

			/**
			 * 创建 {@link BFInsertArgument} 构建器 {@link Builder}
			 *
			 * @return {@link BFInsertArgument} 构建器 {@link Builder}
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
			 * 设置期望的误判率
			 *
			 * @param errorRate
			 * 		期望的误判率
			 *
			 * @return {@link Builder}
			 */
			public Builder errorRate(final Double errorRate) {
				arguament.errorRate = errorRate;
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

			/**
			 * 设置当 key 不存在时不自动创建
			 *
			 * @return {@link Builder}
			 */
			public Builder noCreate() {
				arguament.noCreate = true;
				return this;
			}

			/**
			 * 设置禁用自动扩容
			 *
			 * @return {@link Builder}
			 */
			public Builder nonScaling() {
				arguament.nonScaling = true;
				return this;
			}

		}

	}

	/**
	 * BF.RESERVE 参数
	 */
	class BFReserveArgument {

		/**
		 * 期望的误判率
		 */
		private final double errorRate;

		/**
		 * 初始容量
		 */
		private final long capacity;

		/**
		 * 扩容倍数
		 */
		private Integer expansion;

		/**
		 * 是否禁用自动扩容
		 */
		private Boolean nonScaling;

		/**
		 * 构造函数
		 *
		 * @param errorRate
		 * 		期望的最大误判概率
		 * @param capacity
		 * 		预计要插入的元素数量上限
		 */
		private BFReserveArgument(final double errorRate, final long capacity) {
			this.errorRate = errorRate;
			this.capacity = capacity;
		}

		/**
		 * 构造函数
		 *
		 * @param errorRate
		 * 		期望的最大误判概率
		 * @param capacity
		 * 		预计要插入的元素数量上限
		 * @param expansion
		 * 		扩容倍数
		 */
		private BFReserveArgument(final double errorRate, final long capacity, final Integer expansion) {
			this(errorRate, capacity);
			this.expansion = expansion;
		}

		/**
		 * 构造函数
		 *
		 * @param errorRate
		 * 		期望的最大误判概率
		 * @param capacity
		 * 		预计要插入的元素数量上限
		 * @param nonScaling
		 * 		是否禁用自动扩容
		 */
		private BFReserveArgument(final double errorRate, final long capacity, final Boolean nonScaling) {
			this(errorRate, capacity);
			this.nonScaling = nonScaling;
		}

		/**
		 * 构造函数
		 *
		 * @param errorRate
		 * 		期望的最大误判概率
		 * @param capacity
		 * 		预计要插入的元素数量上限
		 * @param expansion
		 * 		扩容倍数
		 * @param nonScaling
		 * 		是否禁用自动扩容
		 */
		private BFReserveArgument(final double errorRate, final long capacity, final Integer expansion,
								  final Boolean nonScaling) {
			this(errorRate, capacity, expansion);
			this.nonScaling = nonScaling;
		}

		/**
		 * 获取期望的误判率
		 *
		 * @return 期望的误判率
		 */
		public Double getErrorRate() {
			return errorRate;
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
		 * -
		 *
		 * @return -
		 */
		public Integer getExpansion() {
			return expansion;
		}

		/**
		 * 获取是否禁用自动扩容
		 *
		 * @return 是否是否禁用自动扩容
		 */
		public Boolean isNonScaling() {
			return nonScaling;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().append(errorRate).append(capacity)
					.append(Objects.equals(nonScaling, true) ? "NONSCALING" : null).build();
		}

		public static class Builder extends BaseArgumentBuilder<BFReserveArgument> {

			/**
			 * 构造函数
			 *
			 * @param errorRate
			 * 		期望的误判率
			 * @param capacity
			 * 		预计要插入的元素数量上限
			 */
			private Builder(final double errorRate, final long capacity) {
				super(new BFReserveArgument(errorRate, capacity));
			}

			/**
			 * 创建 {@link BFReserveArgument} 构建器 {@link Builder}
			 *
			 * @param errorRate
			 * 		期望的误判率
			 * @param capacity
			 * 		预计要插入的元素数量上限
			 *
			 * @return {@link BFReserveArgument} 构建器 {@link Builder}
			 */
			public static Builder create(final double errorRate, final long capacity) {
				return new Builder(errorRate, capacity);
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

			/**
			 * 设置禁用自动扩容
			 *
			 * @return {@link Builder}
			 */
			public Builder nonScaling() {
				arguament.nonScaling = true;
				return this;
			}

		}

	}

}

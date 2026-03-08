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
package com.buession.redis.core.command.args;

import com.buession.redis.core.StreamDeletionPolicy;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 * {@code XADD} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class XAddArgument {

	/**
	 * 如果流不存在，不自动创建它
	 */
	private Boolean noMkStream;

	/**
	 * 删除策略
	 */
	private StreamDeletionPolicy deletionPolicy;

	/**
	 * {@link BaseIdmp}
	 */
	private BaseIdmp idmp;

	/**
	 * {@link MaxLenMinId}
	 */
	private MaxLenMinId<?> maxLenMinId;

	/**
	 * 构造函数
	 */
	public XAddArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * 		-
	 */
	public XAddArgument(final boolean noMkStream) {
		this.noMkStream = noMkStream;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param deletionPolicy
	 * 		删除策略
	 */
	public XAddArgument(final boolean noMkStream, final StreamDeletionPolicy deletionPolicy) {
		this.noMkStream = noMkStream;
		this.deletionPolicy = deletionPolicy;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param deletionPolicy
	 * 		删除策略
	 * @param idmp
	 *        {@link BaseIdmp}
	 */
	public XAddArgument(final boolean noMkStream, final StreamDeletionPolicy deletionPolicy, final BaseIdmp idmp) {
		this(noMkStream, deletionPolicy);
		this.idmp = idmp;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param deletionPolicy
	 * 		删除策略
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final boolean noMkStream, final StreamDeletionPolicy deletionPolicy,
						final MaxLenMinId<?> maxLenMinId) {
		this(noMkStream, deletionPolicy);
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param deletionPolicy
	 * 		删除策略
	 * @param idmp
	 *        {@link BaseIdmp}
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final boolean noMkStream, final StreamDeletionPolicy deletionPolicy, final BaseIdmp idmp,
						final MaxLenMinId<?> maxLenMinId) {
		this(noMkStream, deletionPolicy, idmp);
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param idmp
	 *        {@link BaseIdmp}
	 */
	public XAddArgument(final boolean noMkStream, final BaseIdmp idmp) {
		this(noMkStream);
		this.idmp = idmp;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param idmp
	 *        {@link BaseIdmp}
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final boolean noMkStream, final BaseIdmp idmp, final MaxLenMinId<?> maxLenMinId) {
		this(noMkStream, idmp);
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		如果流不存在，不自动创建它
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final boolean noMkStream, final MaxLenMinId<?> maxLenMinId) {
		this.noMkStream = noMkStream;
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param deletionPolicy
	 * 		删除策略
	 * @param idmp
	 *        {@link BaseIdmp}
	 */
	public XAddArgument(final StreamDeletionPolicy deletionPolicy, final BaseIdmp idmp) {
		this.deletionPolicy = deletionPolicy;
		this.idmp = idmp;
	}

	/**
	 * 构造函数
	 *
	 * @param deletionPolicy
	 * 		删除策略
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final StreamDeletionPolicy deletionPolicy, final MaxLenMinId<?> maxLenMinId) {
		this.deletionPolicy = deletionPolicy;
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param deletionPolicy
	 * 		删除策略
	 * @param idmp
	 *        {@link BaseIdmp}
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final StreamDeletionPolicy deletionPolicy, final BaseIdmp idmp,
						final MaxLenMinId<?> maxLenMinId) {
		this(deletionPolicy, idmp);
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param idmp
	 *        {@link BaseIdmp}
	 */
	public XAddArgument(final BaseIdmp idmp) {
		this.idmp = idmp;
	}

	/**
	 * 构造函数
	 *
	 * @param idmp
	 *        {@link BaseIdmp}
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final BaseIdmp idmp, final MaxLenMinId<?> maxLenMinId) {
		this.idmp = idmp;
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public XAddArgument(final MaxLenMinId<?> maxLenMinId) {
		this.maxLenMinId = maxLenMinId;
	}

	public Boolean isNoMkStream() {
		return getNoMkStream();
	}

	public Boolean getNoMkStream() {
		return noMkStream;
	}

	public XAddArgument noMkStream() {
		return setNoMkStream(true);
	}

	public XAddArgument setNoMkStream(boolean noMkStream) {
		this.noMkStream = noMkStream;
		return this;
	}

	public StreamDeletionPolicy getDeletionPolicy() {
		return deletionPolicy;
	}

	public XAddArgument setDeletionPolicy(StreamDeletionPolicy deletionPolicy) {
		this.deletionPolicy = deletionPolicy;
		return this;
	}

	public BaseIdmp getIdmp() {
		return idmp;
	}

	public XAddArgument setIdmp(BaseIdmp idmp) {
		this.idmp = idmp;
		return this;
	}

	public MaxLenMinId<?> getMaxLenMinId() {
		return maxLenMinId;
	}

	public XAddArgument setMaxLenMinId(MaxLenMinId<?> maxLenMinId) {
		this.maxLenMinId = maxLenMinId;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(Boolean.TRUE.equals(getNoMkStream()) ? "NOMKSTREAM" : null)
				.append(getDeletionPolicy())
				.append(getIdmp())
				.append(getMaxLenMinId())
				.build();
	}

	public static abstract class BaseIdmp {

		private final String producerId;

		public BaseIdmp(final String producerId) {
			this.producerId = producerId;
		}

		public BaseIdmp(final byte[] producerId) {
			this.producerId = SafeEncoder.encode(producerId);
		}

		public String getProducerId() {
			return producerId;
		}

		public static class IdmpAuto extends BaseIdmp {

			public IdmpAuto(final String producerId) {
				super(producerId);
			}

			public IdmpAuto(final byte[] producerId) {
				super(producerId);
			}

			@Override
			public String toString() {
				return ArgStringBuilder.create().add("IDMPAUTO", getProducerId()).build();
			}

		}

		public static class Idmp extends BaseIdmp {

			private final String idempotentId;

			public Idmp(final String producerId, final String idempotentId) {
				super(producerId);
				this.idempotentId = idempotentId;
			}

			public Idmp(final byte[] producerId, final byte[] idempotentId) {
				super(producerId);
				this.idempotentId = SafeEncoder.encode(idempotentId);
			}

			public String getIdempotentId() {
				return idempotentId;
			}

			@Override
			public String toString() {
				return ArgStringBuilder.create().add("IDMP", getProducerId()).append(getIdempotentId()).build();
			}

		}

	}

}

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

import com.buession.redis.serializer.Serializer;
import com.buession.redis.serializer.ByteArraySerializer;
import com.buession.redis.serializer.FastJsonJsonSerializer;
import com.buession.redis.serializer.GsonJsonSerializer;
import com.buession.redis.serializer.JacksonJsonSerializer;

import java.util.Optional;

/**
 * 配置选项
 *
 * @author Yong.Teng
 */
public class Options {

	/**
	 * Key 前缀
	 */
	private String prefix;

	/**
	 * 序列化实例
	 *
	 * @see Serializer
	 * @see ByteArraySerializer
	 * @see FastJsonJsonSerializer
	 * @see GsonJsonSerializer
	 * @see JacksonJsonSerializer
	 */
	private Serializer serializer;

	/**
	 * 是否开启事务支持
	 */
	private boolean enableTransactionSupport;

	/**
	 * 返回 Key 前缀
	 *
	 * @return Key 前缀
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * 设置 Key 前缀
	 *
	 * @param prefix
	 * 		Key 前缀
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * 返回序列化实例
	 *
	 * @return 序列化实例
	 *
	 * @see Serializer
	 * @see ByteArraySerializer
	 * @see FastJsonJsonSerializer
	 * @see GsonJsonSerializer
	 * @see JacksonJsonSerializer
	 */
	public Serializer getSerializer() {
		return serializer;
	}

	/**
	 * 设置序列化实例
	 *
	 * @param serializer
	 * 		序列化实例
	 *
	 * @see Serializer
	 * @see ByteArraySerializer
	 * @see FastJsonJsonSerializer
	 * @see GsonJsonSerializer
	 * @see JacksonJsonSerializer
	 */
	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	/**
	 * 返回是否开启事务支持
	 *
	 * @return 是否开启事务支持
	 */
	public boolean isEnableTransactionSupport() {
		return getEnableTransactionSupport();
	}

	/**
	 * 返回是否开启事务支持
	 *
	 * @return 是否开启事务支持
	 */
	public boolean getEnableTransactionSupport() {
		return enableTransactionSupport;
	}

	/**
	 * 设置是否开启事务支持
	 *
	 * @param enableTransactionSupport
	 * 		是否开启事务支持
	 */
	public void setEnableTransactionSupport(boolean enableTransactionSupport) {
		this.enableTransactionSupport = enableTransactionSupport;
	}

	/**
	 * {@link Builder} 构建器
	 *
	 * @author yong.teng
	 * @since 2.0.0
	 */
	public final static class Builder {

		private final Options options = new Options();

		private Builder() {

		}

		/**
		 * 获取 {@link Builder} 实例
		 *
		 * @return {@link Builder} 实例
		 */
		public static Builder getInstance() {
			return new Builder();
		}

		/**
		 * 设置 Key 前缀
		 *
		 * @param prefix
		 * 		Key 前缀
		 *
		 * @return {@link Builder} 实例
		 */
		public Builder prefix(String prefix) {
			options.setPrefix(prefix);
			return this;
		}

		/**
		 * 设置序列化实例
		 *
		 * @param serializer
		 * 		序列化实例
		 *
		 * @return {@link Builder} 实例
		 *
		 * @see Serializer
		 * @see ByteArraySerializer
		 * @see FastJsonJsonSerializer
		 * @see GsonJsonSerializer
		 * @see JacksonJsonSerializer
		 */
		public Builder serializer(Serializer serializer) {
			options.setSerializer(serializer);
			return this;
		}

		/**
		 * 设置是否开启事务支持
		 *
		 * @param enableTransactionSupport
		 * 		是否开启事务支持
		 *
		 * @return {@link Builder} 实例
		 */
		public Builder enableTransactionSupport(Boolean enableTransactionSupport) {
			Optional.ofNullable(enableTransactionSupport).ifPresent(options::setEnableTransactionSupport);
			return this;
		}

		/**
		 * 构建 {@link Options}
		 *
		 * @return {@link Options} 实例
		 */
		public Options build() {
			return options;
		}

	}

}

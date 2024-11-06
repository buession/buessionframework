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

import com.buession.redis.core.Keyword;
import com.buession.redis.core.Type;

/**
 * {@code SCAN} 参数基类
 *
 * @param <T>
 * 		模式值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ScanArgument<T> extends BaseScanArgument<T> {

	/**
	 * 类型
	 */
	private Type type;

	/**
	 * 构造函数
	 */
	public ScanArgument() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 */
	public ScanArgument(final T pattern) {
		super(pattern);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回个数
	 */
	public ScanArgument(final int count) {
		super(count);
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		类型
	 */
	public ScanArgument(final Type type) {
		this.type = type;
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param type
	 * 		类型
	 */
	public ScanArgument(final T pattern, final Type type) {
		super(pattern);
		this.type = type;
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		类型
	 */
	public ScanArgument(final int count, final Type type) {
		super(count);
		this.type = type;
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param count
	 * 		返回个数
	 */
	public ScanArgument(final T pattern, final int count) {
		super(pattern, count);
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		类型
	 */
	public ScanArgument(final T pattern, final int count, final Type type) {
		super(pattern, count);
		this.type = type;
	}

	/**
	 * 返回类型
	 *
	 * @return 类型
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 *
	 * @param type
	 * 		类型
	 */
	public void setType(final Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(getPattern() != null){
			builder.add(Keyword.Key.MATCH, getPattern());
		}

		if(getCount() != null){
			builder.add(Keyword.Common.COUNT, getCount());
		}

		if(type != null){
			builder.add(Keyword.Common.TYPE, type);
		}

		return builder.build();
	}

	public static class StringScanArgument extends ScanArgument<String> {

		/**
		 * 构造函数
		 */
		public StringScanArgument() {
			super();
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 */
		public StringScanArgument(final String pattern) {
			super(pattern);
		}

		/**
		 * 构造函数
		 *
		 * @param count
		 * 		返回个数
		 */
		public StringScanArgument(final int count) {
			super(count);
		}

		/**
		 * 构造函数
		 *
		 * @param type
		 * 		类型
		 */
		public StringScanArgument(final Type type) {
			super(type);
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 * @param type
		 * 		类型
		 */
		public StringScanArgument(final String pattern, final Type type) {
			super(pattern, type);
		}

		/**
		 * 构造函数
		 *
		 * @param count
		 * 		返回个数
		 * @param type
		 * 		类型
		 */
		public StringScanArgument(final int count, final Type type) {
			super(count, type);
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 * @param count
		 * 		返回个数
		 */
		public StringScanArgument(final String pattern, final int count) {
			super(pattern, count);
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 * @param count
		 * 		返回个数
		 * @param type
		 * 		类型
		 */
		public StringScanArgument(final String pattern, final int count, final Type type) {
			super(pattern, count, type);
		}

		@Override
		public String toString() {
			return super.toString();
		}

	}

	public static class ByteScanArgument extends ScanArgument<byte[]> {

		/**
		 * 构造函数
		 */
		public ByteScanArgument() {
			super();
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 */
		public ByteScanArgument(final byte[] pattern) {
			super(pattern);
		}

		/**
		 * 构造函数
		 *
		 * @param count
		 * 		返回个数
		 */
		public ByteScanArgument(final int count) {
			super(count);
		}

		/**
		 * 构造函数
		 *
		 * @param type
		 * 		类型
		 */
		public ByteScanArgument(final Type type) {
			super(type);
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 * @param type
		 * 		类型
		 */
		public ByteScanArgument(final byte[] pattern, final Type type) {
			super(pattern, type);
		}

		/**
		 * 构造函数
		 *
		 * @param count
		 * 		返回个数
		 * @param type
		 * 		类型
		 */
		public ByteScanArgument(final int count, final Type type) {
			super(count, type);
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 * @param count
		 * 		返回个数
		 */
		public ByteScanArgument(final byte[] pattern, final int count) {
			super(pattern, count);
		}

		/**
		 * 构造函数
		 *
		 * @param pattern
		 * 		模式
		 * @param count
		 * 		返回个数
		 * @param type
		 * 		类型
		 */
		public ByteScanArgument(final byte[] pattern, final int count, final Type type) {
			super(pattern, count, type);
		}

		@Override
		public String toString() {
			return super.toString();
		}

	}

}

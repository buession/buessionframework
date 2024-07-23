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

import com.buession.redis.core.NxXx;

/**
 * {@code SET} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class SetArgument {

	/**
	 * 过期时间方式
	 */
	private SetType type;

	/**
	 * 过期时间
	 */
	private Long expires;

	/**
	 * 只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	private NxXx nxXx;

	/**
	 * 是否获取 key 的过期时间
	 */
	private Boolean keepTtl;

	/**
	 * 构造函数
	 */
	public SetArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 */
	public SetArgument(final SetType type, final Long expires) {
		this.type = type;
		this.expires = expires;
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public SetArgument(final SetType type, final Long expires, final NxXx nxXx) {
		this(type, expires);
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public SetArgument(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 *
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public SetArgument(final Boolean keepTtl) {
		this.keepTtl = keepTtl;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public SetArgument(final NxXx nxXx, final Boolean keepTtl) {
		this.nxXx = nxXx;
		this.keepTtl = keepTtl;
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public SetArgument(final SetType type, final long expires, final NxXx nxXx, final Boolean keepTtl) {
		this(type, expires, nxXx);
		this.keepTtl = keepTtl;
	}

	/**
	 * 返回过期时间方式
	 *
	 * @return 过期时间方式
	 */
	public SetType getType() {
		return type;
	}

	/**
	 * 设置过期时间方式
	 *
	 * @param type
	 * 		过期时间方式
	 */
	public void setType(final SetType type) {
		this.type = type;
	}

	/**
	 * 返回过期时间
	 *
	 * @return 过期时间
	 */
	public Long getExpires() {
		return expires;
	}

	/**
	 * 设置过期时间
	 *
	 * @param expires
	 * 		过期时间
	 */
	public void setExpires(final Long expires) {
		this.expires = expires;
	}

	/**
	 * 返回只有键 key 不存在/存在的时候才会设置 key 的值
	 *
	 * @return 只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public NxXx getNxXx() {
		return nxXx;
	}

	/**
	 * 设置只有键 key 不存在/存在的时候才会设置 key 的值
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public void setNxXx(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 返回是否获取 key 的过期时间
	 *
	 * @return 是否获取 key 的过期时间
	 */
	public Boolean isKeepTtl() {
		return getKeepTtl();
	}

	/**
	 * 返回是否获取 key 的过期时间
	 *
	 * @return 是否获取 key 的过期时间
	 */
	public Boolean getKeepTtl() {
		return keepTtl;
	}

	/**
	 * 设置获取 key 的过期时间
	 */
	public void keepTtl() {
		this.keepTtl = true;
	}

	/**
	 * 设置是否获取 key 的过期时间
	 *
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public void setKeepTtl(final Boolean keepTtl) {
		this.keepTtl = keepTtl;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(type != null){
			builder.add(type);
		}
		builder.append(expires).add(nxXx);

		if(Boolean.TRUE.equals(keepTtl)){
			builder.append("KEEPTTL");
		}

		return builder.build();
	}

	/**
	 * 设置过期时间方式
	 */
	public enum SetType {
		/**
		 * 设置指定的过期时间，以秒为单位
		 */
		EX,

		/**
		 * 设置指定的过期 Unix 时间戳，以秒为单位
		 */
		EXAT,

		/**
		 * 设置指定的过期时间，以毫秒为单位
		 */
		PX,

		/**
		 * 设置指定的过期 Unix 时间戳，以毫秒为单位
		 */
		PXAT
	}

}

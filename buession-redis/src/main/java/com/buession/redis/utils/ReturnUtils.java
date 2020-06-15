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
package com.buession.redis.utils;

import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.core.RedisServerTime;

import java.util.Date;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class ReturnUtils {

	public final static Status statusForOK(final String str){
		return Status.valueOf("OK".equalsIgnoreCase(str));
	}

	public final static Status statusForOK(final byte[] str){
		return statusForOK(SafeEncoder.encode(str));
	}

	public final static Status statusForBool(final boolean value){
		return Status.valueOf(value);
	}

	public final static <V extends Enum<V>> V enumValueOf(final String str, final Class<V> enumType){
		return EnumUtils.valueOf(enumType, str.toUpperCase());
	}

	public final static RedisServerTime redisServerTime(final List<String> ret){
		if(ret == null){
			return null;
		}

		RedisServerTime time = new RedisServerTime();

		Date date = new Date();
		date.setTime(Long.parseLong(ret.get(0)) * 1000L);

		time.setDate(date);
		time.setUsec(Long.parseLong(ret.get(1)));

		return time;
	}

	public final static <V> V listFirst(final List<V> data){
		return listFirst(data, null);
	}

	public final static <V> V listFirst(final List<V> data, final V defaultValue){
		return Validate.isEmpty(data) ? defaultValue : data.get(0);
	}

}

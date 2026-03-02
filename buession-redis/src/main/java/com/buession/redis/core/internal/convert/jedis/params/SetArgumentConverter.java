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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.args.SetArgument;
import redis.clients.jedis.params.SetParams;

/**
 * {@link SetArgument} 转换为 jedis {@link SetParams}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class SetArgumentConverter implements Converter<SetArgument, SetParams> {

	@Override
	public SetParams convert(final SetArgument source) {
		if(source == null){
			return null;
		}

		final SetParams setParams = new SetParams();

		if(source.getNxXx() != null){
			switch(source.getNxXx()){
				case NX -> setParams.nx();
				case XX -> setParams.xx();
			}
		}

		if(source.getType() != null && source.getExpires() != null){
			switch(source.getType()){
				case EX -> setParams.ex(source.getExpires());
				case PX -> setParams.px(source.getExpires());
				case EXAT -> setParams.exAt(source.getExpires());
				case PXAT -> setParams.pxAt(source.getExpires());
			}
		}

		if(Boolean.TRUE.equals(source.getKeepTtl())){
			setParams.keepTtl();
		}

		return setParams;
	}

}

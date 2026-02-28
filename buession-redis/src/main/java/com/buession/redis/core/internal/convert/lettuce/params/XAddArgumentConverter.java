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
package com.buession.redis.core.internal.convert.lettuce.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.args.XAddArgument;
import io.lettuce.core.XAddArgs;

/**
 * {@link XAddArgument} 转换为 lettuce {@link XAddArgs}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class XAddArgumentConverter implements Converter<XAddArgument, XAddArgs> {

	@Override
	public XAddArgs convert(final XAddArgument source) {
		if(source == null){
			return null;
		}

		final XAddArgs xAddArgs = new XAddArgs();

		if(Boolean.TRUE.equals(source.getNoMkStream())){
			xAddArgs.nomkstream();
		}

		if(source.getDeletionPolicy() != null){
			final StreamDeletionPolicyConverter xDeletionPolicyConverter = new StreamDeletionPolicyConverter();
			xAddArgs.trimmingMode(xDeletionPolicyConverter.convert(source.getDeletionPolicy()));
		}

		if(source.getIdmp() != null){

		}

		if(source.getMaxLenMinId() != null){
			XAddArgument.MaxLenMinId<?> maxLenMinId = source.getMaxLenMinId();

			if(maxLenMinId instanceof XAddArgument.MaxLenMinId.MaxLen){
				xAddArgs.maxlen(((XAddArgument.MaxLenMinId.MaxLen) maxLenMinId).getThreshold());
			}else if(maxLenMinId instanceof XAddArgument.MaxLenMinId.MinId){
				xAddArgs.minId(((XAddArgument.MaxLenMinId.MinId) maxLenMinId).getThreshold().toString());
			}

			if(maxLenMinId.getApproximateExactTrimming() != null){
				switch(maxLenMinId.getApproximateExactTrimming()){
					case APPROXIMATE -> xAddArgs.approximateTrimming();
					case EXACT -> xAddArgs.exactTrimming();
				}
			}

			if(maxLenMinId.getCount() != null){
				xAddArgs.limit(maxLenMinId.getCount());
			}
		}

		return xAddArgs;
	}

}

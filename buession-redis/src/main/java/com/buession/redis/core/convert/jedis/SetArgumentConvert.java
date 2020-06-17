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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert.jedis;

import com.buession.core.convert.Convert;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.params.SetParams;

/**
 * @author Yong.Teng
 */
public class SetArgumentConvert implements Convert<StringCommands.SetArgument, SetParams> {

	@Override
	public SetParams encode(final StringCommands.SetArgument source){
		if(source == null){
			return null;
		}

		final SetParams setParams = new SetParams();

		if(source.getEx() != null){
			setParams.ex(source.getEx().intValue());
		}

		if(source.getPx() != null){
			setParams.px(source.getPx().intValue());
		}

		if(source.getNxXx() == NxXx.NX){
			setParams.nx();
		}else if(source.getNxXx() == NxXx.XX){
			setParams.xx();
		}

		return setParams;
	}

	@Override
	public StringCommands.SetArgument decode(final SetParams target){
		if(target == null){
			return null;
		}

		final StringCommands.SetArgument.Builder setArgumentBuilder = StringCommands.SetArgument.Builder.create();
		byte[][] params = target.getByteParams();

		for(int i = 0; i < params.length; i++){
			String s = SafeEncoder.encode(params[i]);

			if("ex".equals(s)){
				setArgumentBuilder.ex(Integer.valueOf(SafeEncoder.encode(params[++i])));
			}else if("px".equals(s)){
				setArgumentBuilder.px(Integer.valueOf(SafeEncoder.encode(params[++i])));
			}else if("nx".equals(s)){
				setArgumentBuilder.nxXX(NxXx.NX);
			}else if("xx".equals(s)){
				setArgumentBuilder.nxXX(NxXx.XX);
			}
		}

		return setArgumentBuilder.build();
	}

}

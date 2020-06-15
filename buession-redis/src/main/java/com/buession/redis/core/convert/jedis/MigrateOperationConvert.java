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
package com.buession.redis.core.convert.jedis;

import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.params.MigrateParams;

/**
 * @author Yong.Teng
 */
public class MigrateOperationConvert implements Convert<MigrateOperation, MigrateParams> {

	@Override
	public MigrateParams convert(final MigrateOperation source){
		switch(source){
			case COPY:
				return MigrateParams.migrateParams().copy();
			case REPLACE:
				return MigrateParams.migrateParams().replace();
			default:
				return null;
		}
	}

	@Override
	public MigrateOperation deconvert(final MigrateParams target){
		if(target.getParam(MigrateOperation.COPY.name())){
			return MigrateOperation.COPY;
		}else if(target.getParam(MigrateOperation.REPLACE.name())){
			return MigrateOperation.REPLACE;
		}else{
			return null;
		}
	}

}

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

import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.params.MigrateParams;

/**
 * @author Yong.Teng
 */
public class MigrateOperationConvert implements Convert<KeyCommands.MigrateOperation, MigrateParams> {

    @Override
    public MigrateParams convert(KeyCommands.MigrateOperation source){
        if(source == KeyCommands.MigrateOperation.COPY){
            return MigrateParams.migrateParams().copy();
        }else if(source == KeyCommands.MigrateOperation.REPLACE){
            return MigrateParams.migrateParams().replace();
        }else{
            return null;
        }
    }

    @Override
    public KeyCommands.MigrateOperation deconvert(MigrateParams target){
        if(target.getParam(KeyCommands.MigrateOperation.COPY.name())){
            return KeyCommands.MigrateOperation.COPY;
        }else if(target.getParam(KeyCommands.MigrateOperation.REPLACE.name())){
            return KeyCommands.MigrateOperation.REPLACE;
        }else{
            return null;
        }
    }
}

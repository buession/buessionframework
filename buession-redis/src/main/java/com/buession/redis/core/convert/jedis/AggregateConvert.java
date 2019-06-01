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

import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.ZParams;

/**
 * @author Yong.Teng
 */
public class AggregateConvert implements Convert<SortedSetCommands.Aggregate, ZParams.Aggregate> {

    @Override
    public ZParams.Aggregate convert(SortedSetCommands.Aggregate source){
        if(source == SortedSetCommands.Aggregate.MIN){
            return ZParams.Aggregate.MIN;
        }else if(source == SortedSetCommands.Aggregate.MAX){
            return ZParams.Aggregate.MAX;
        }else if(source == SortedSetCommands.Aggregate.SUM){
            return ZParams.Aggregate.SUM;
        }else{
            return null;
        }
    }

    @Override
    public SortedSetCommands.Aggregate deconvert(ZParams.Aggregate target){
        if(target == ZParams.Aggregate.MIN){
            return SortedSetCommands.Aggregate.MIN;
        }else if(target == ZParams.Aggregate.MAX){
            return SortedSetCommands.Aggregate.MAX;
        }else if(target == ZParams.Aggregate.SUM){
            return SortedSetCommands.Aggregate.SUM;
        }else{
            return null;
        }
    }
}

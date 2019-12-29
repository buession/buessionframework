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

import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.BitOP;

/**
 * @author Yong.Teng
 */
public class BitOperationConvert implements Convert<BitMapCommands.Operation, BitOP> {

    @Override
    public BitOP convert(final BitMapCommands.Operation source){
        if(source == BitMapCommands.Operation.AND){
            return BitOP.AND;
        }else if(source == BitMapCommands.Operation.OR){
            return BitOP.OR;
        }else if(source == BitMapCommands.Operation.NOT){
            return BitOP.NOT;
        }else if(source == BitMapCommands.Operation.XOR){
            return BitOP.XOR;
        }else{
            return null;
        }
    }

    @Override
    public BitMapCommands.Operation deconvert(final BitOP target){
        if(target == BitOP.AND){
            return BitMapCommands.Operation.AND;
        }else if(target == BitOP.OR){
            return BitMapCommands.Operation.OR;
        }else if(target == BitOP.NOT){
            return BitMapCommands.Operation.NOT;
        }else if(target == BitOP.XOR){
            return BitMapCommands.Operation.XOR;
        }else{
            return null;
        }
    }

}

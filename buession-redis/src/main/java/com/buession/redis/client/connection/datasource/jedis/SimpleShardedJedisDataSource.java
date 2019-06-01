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
package com.buession.redis.client.connection.datasource.jedis;

import redis.clients.jedis.ShardedJedis;

/**
 * @author Yong.Teng
 */
public class SimpleShardedJedisDataSource extends AbstractJedisRedisDataSource<ShardedJedis> implements
        ShardedJedisDataSource {

    private ShardedJedis shardedJedis;

    public ShardedJedis getShardedJedis(){
        return shardedJedis;
    }

    public void setShardedJedis(ShardedJedis shardedJedis){
        this.shardedJedis = shardedJedis;
    }

    @Override
    public ShardedJedis getRedisClient(){
        return getShardedJedis();
    }

    @Override
    public void disconnect(){
        if(shardedJedis != null){
            shardedJedis.disconnect();
        }
    }

    @Override
    public boolean isClosed(){
        return shardedJedis == null ? true : false;
    }

    @Override
    public void close(){
        if(shardedJedis != null){
            shardedJedis.close();
        }
    }

}

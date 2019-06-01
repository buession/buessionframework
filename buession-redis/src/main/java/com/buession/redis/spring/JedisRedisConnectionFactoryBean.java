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
package com.buession.redis.spring;

import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.datasource.jedis.JedisPoolDataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisPoolDataSource;
import com.buession.redis.client.connection.datasource.jedis.SimpleJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.SimpleShardedJedisDataSource;
import com.buession.redis.client.connection.jedis.JedisPoolConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisPoolConnection;
import com.buession.redis.client.connection.jedis.SimpleJedisConnection;
import com.buession.redis.client.connection.jedis.SimpleShardedJedisConnection;
import com.buession.redis.exception.PoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.util.Pool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisRedisConnection> {

    private final static Method SET_TIMEOUT_METHOD;

    private final static Method GET_TIMEOUT_METHOD;

    private JedisPoolConfig poolConfig = new JedisPoolConfig();

    private Pool<? extends JedisCommands> pool;

    private boolean useShardInfo;

    private final static Logger logger = LoggerFactory.getLogger(JedisRedisConnectionFactoryBean.class);

    static{
        // We need to configure Jedis socket timeout via reflection since the method-name was changed between
        // releases.
        Method setJedisShardInfoTimeoutMethod = ReflectionUtils.findMethod(JedisShardInfo.class, "setTimeout", int
                .class);
        if(setJedisShardInfoTimeoutMethod == null){
            // Jedis V 2.7.x changed the setTimeout method to setSoTimeout
            setJedisShardInfoTimeoutMethod = ReflectionUtils.findMethod(JedisShardInfo.class, "setSoTimeout", int
                    .class);
        }
        SET_TIMEOUT_METHOD = setJedisShardInfoTimeoutMethod;

        Method getJedisShardInfoTimeoutMethod = ReflectionUtils.findMethod(JedisShardInfo.class, "getTimeout");
        if(getJedisShardInfoTimeoutMethod == null){
            getJedisShardInfoTimeoutMethod = ReflectionUtils.findMethod(JedisShardInfo.class, "getSoTimeout");
        }

        GET_TIMEOUT_METHOD = getJedisShardInfoTimeoutMethod;
    }

    public JedisPoolConfig getPoolConfig(){
        JedisPoolConfig poolConfig = null;

        if(poolConfig == null){
            poolConfig = new JedisPoolConfig();
            setPoolConfig(poolConfig);
        }

        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig){
        this.poolConfig = poolConfig;
    }

    public Pool<? extends JedisCommands> getPool(){
        return pool;
    }

    public void setPool(Pool<? extends JedisCommands> pool){
        this.pool = pool;
    }

    public boolean isUseShardInfo(){
        return getUseShardInfo();
    }

    public boolean getUseShardInfo(){
        return useShardInfo;
    }

    public void setUseShardInfo(boolean useShardInfo){
        this.useShardInfo = useShardInfo;
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        if(isUsePool()){
            if(isUseShardInfo()){
                final ShardedJedisPoolDataSource dataSource = new ShardedJedisPoolDataSource();

                dataSource.setPool(createShardedJedisPool());
                setPool(dataSource.getPool());
                setConnection(new ShardedJedisPoolConnection(dataSource));
            }else{
                final JedisPoolDataSource dataSource = new JedisPoolDataSource();

                dataSource.setPool(createPool());
                setPool(dataSource.getPool());
                setConnection(new JedisPoolConnection(dataSource));
            }
        }else{
            if(isUseShardInfo()){
                final SimpleShardedJedisDataSource dataSource = new SimpleShardedJedisDataSource();

                setConnection(new SimpleShardedJedisConnection(dataSource));
            }else{
                final SimpleJedisDataSource dataSource = new SimpleJedisDataSource();

                setConnection(new SimpleJedisConnection(dataSource));
            }
        }
    }

    protected JedisPool createPool(){
        JedisPool pool = new JedisPool(getPoolConfig(), getHost(), getPort(), getTimeout(), getPassword(),
                getDatabase(), getClientName(), isUseSsl());

        logger.info("Jedis pool bean init width db {} success, name: {}.", getDatabase(), getClientName());

        return pool;
    }

    protected ShardedJedisPool createShardedJedisPool(){
        List<JedisShardInfo> shardInfos = new ArrayList<>(1);
        JedisShardInfo shardInfo = new JedisShardInfo(getHost(), getPort(), getClientName(), isUseSsl());

        if(Validate.hasText(getPassword()) == false){
            shardInfo.setPassword(getPassword());
        }

        if(getTimeout() > 0){
            ReflectionUtils.invokeMethod(SET_TIMEOUT_METHOD, shardInfo, getTimeout());
        }

        shardInfos.add(shardInfo);

        ShardedJedisPool pool = new ShardedJedisPool(getPoolConfig(), shardInfos);

        logger.info("Sharded jedis pool bean init width for {} shard info.", shardInfos.size());

        return pool;
    }

    @Override
    protected void doDestroy(JedisRedisConnection connection){
        if(isUsePool() && getPool() != null){
            try{
                getPool().destroy();
            }catch(Exception e){
                logger.warn("Cannot properly close Jedis pool", e);
                throw new PoolException(e.getMessage(), e);
            }
            setPool(null);
        }
    }
}

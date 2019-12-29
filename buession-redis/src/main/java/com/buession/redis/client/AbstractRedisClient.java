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
package com.buession.redis.client;

import com.buession.core.Executor;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.RedisException;
import com.buession.redis.utils.SafeEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

    private RedisConnection connection;

    private final static Logger logger = LoggerFactory.getLogger(AbstractRedisClient.class);

    public AbstractRedisClient(){
        super();
    }

    public AbstractRedisClient(RedisConnection connection){
        this.connection = connection;
    }

    @Override
    public RedisConnection getConnection(){
        return connection;
    }

    @Override
    public void setConnection(RedisConnection connection){
        this.connection = connection;
    }

    protected <C, R> R doExecute(final ProtocolCommand command, final Executor<C, R> executor) throws RedisException{
        try{
            logger.debug("Execute command '{}'", command);
            return connection.execute(command, executor);
        }catch(RedisException e){
            logger.error("Execute command '{}', failure: {}", command, e.getMessage());
            throw e;
        }
    }

    protected <C, R> R doExecute(final ProtocolCommand command, final Executor<C, R> executor, final
    OperationsCommandArguments arguments){
        try{
            logger.debug("Execute command '{}'<{}>", command, commandParametersToSting(arguments));
            return connection.execute(command, executor);
        }catch(RedisException e){
            logger.error("Execute command '{}'<{}>, failure: {}", command, commandParametersToSting(arguments), e
                    .getMessage());
            throw e;
        }
    }

    protected final static Status returnStatus(final boolean value){
        return Status.valueOf(value);
    }

    protected final static <O extends Enum<O>> O returnEnum(final String str, final Class<O> enumType){
        return Enum.valueOf(enumType, str.toUpperCase());
    }

    protected final static Status returnForOK(final String str){
        return Status.valueOf("OK".equalsIgnoreCase(str));
    }

    protected final static Status returnForOK(final byte[] str){
        return returnForOK(SafeEncoder.encode(str));
    }

    protected final static RedisServerTime returnRedisServerTime(final List<String> ret){
        if(ret == null){
            return null;
        }

        RedisServerTime time = new RedisServerTime();

        Date date = new Date();
        date.setTime(Long.valueOf(ret.get(0)) * 1000L);

        time.setDate(date);
        time.setUsec(Long.valueOf(ret.get(1)));

        return time;
    }

    protected void close(){
        try{
            connection.close();
        }catch(IOException e){
            logger.error("RedisConnection close error: {}", e.getMessage());
        }
    }

    private final static String commandParametersToSting(final OperationsCommandArguments arguments){
        StringBuffer sb = new StringBuffer();

        if(Validate.isEmpty(arguments.getParameters()) == false){
            int i = 0;

            for(Map.Entry<String, Object> e : arguments.getParameters().entrySet()){
                if(i > 0){
                    sb.append(", ");
                }

                sb.append(e.getKey());
                sb.append(" => ");
                sb.append(e.getValue());

                i++;
            }
        }

        return sb.toString();
    }

}

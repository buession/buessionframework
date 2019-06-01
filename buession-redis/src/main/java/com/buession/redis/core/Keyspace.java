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
package com.buession.redis.core;

/**
 * 数据库相关的统计信息
 *
 * @author Yong.Teng
 */
public class Keyspace {

    /**
     * 数据库
     */
    private int db;

    /**
     * Key 数量
     */
    private int keys;

    /**
     * 带有生存期的 Key 的数量
     */
    private int expires;

    private int avgTtl;

    /**
     * 获取数据库
     *
     * @return 数据库
     */
    public int getDb(){
        return db;
    }

    /**
     * 设置数据库
     *
     * @param db
     *         数据库
     */
    public void setDb(int db){
        this.db = db;
    }

    /**
     * 获取 Key 数量
     *
     * @return Key 数量
     */
    public int getKeys(){
        return keys;
    }

    /**
     * 设置 Key 数量
     *
     * @param keys
     *         Key 数量
     */
    public void setKeys(int keys){
        this.keys = keys;
    }

    /**
     * 获取带有生存期的 Key 的数量
     *
     * @return 带有生存期的 Key 的数量
     */
    public int getExpires(){
        return expires;
    }

    /**
     * 设置带有生存期的 Key 的数量
     *
     * @param expires
     *         带有生存期的 Key 的数量
     */
    public void setExpires(int expires){
        this.expires = expires;
    }

    public int getAvgTtl(){
        return avgTtl;
    }

    public void setAvgTtl(int avgTtl){
        this.avgTtl = avgTtl;
    }

    @Override
    public String toString(){
        return "Keyspace{" + "db=" + db + ", keys=" + keys + ", expires=" + expires + ", avgTtl=" + avgTtl + '}';
    }
}

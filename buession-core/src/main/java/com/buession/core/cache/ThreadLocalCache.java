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
package com.buession.core.cache;

import com.buession.core.validator.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ThreadLocalCache<K, V> {

    private ThreadLocal<Map<K, V>> cache = new ThreadLocal<>();

    public ThreadLocalCache(){
        cache.set(new HashMap<>(0));
    }

    public ThreadLocalCache(Map<K, V> data){
        cache.set(data == null ? new HashMap<>(0) : data);
    }

    public ThreadLocalCache(int initialCapacity){
        cache.set(new HashMap<>(initialCapacity));
    }

    public V get(K key){
        Map<K, V> map = cache.get();
        return map == null ? null : map.get(key);
    }

    public V put(K key, V value){
        Map<K, V> map = cache.get();

        if(map == null){
            map = new HashMap<>(1);
        }

        V result = map.put(key, value);

        cache.set(map);

        return result;
    }

    public void putAll(Map<K, V> data){
        if(Validate.isEmpty(data) == false){
            Map<K, V> map = cache.get();

            if(map == null){
                cache.set(new HashMap<>(data));
            }else{
                map.putAll(data);
            }
        }
    }

    public Map<K, V> getAll(){
        return cache.get();
    }

    public V remove(K key){
        Map<K, V> map = cache.get();
        return map == null ? null : map.remove(key);
    }

    public void clear(K key){
        Map<K, V> map = cache.get();

        if(map != null){
            map.clear();
        }
    }

}

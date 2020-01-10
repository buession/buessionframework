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

import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.convert.Convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public interface ScanResultConvert<S, T> extends Convert<S, T> {

    class MapScanResultConvert<K, V> implements ScanResultConvert<ScanResult<Map<K, V>>, redis.clients.jedis
            .ScanResult<Map.Entry<K, V>>> {

        @Override
        public redis.clients.jedis.ScanResult<Map.Entry<K, V>> convert(final ScanResult<Map<K, V>> source){
            if(source == null){
                return null;
            }

            final List<Map.Entry<K, V>> results = new ArrayList<>(source.getResults().entrySet());
            return new redis.clients.jedis.ScanResult<>(source.getCursor(), results);
        }

        @Override
        public ScanResult<Map<K, V>> deconvert(final redis.clients.jedis.ScanResult<Map.Entry<K, V>> target){
            if(target == null){
                return null;
            }

            Iterator<Map.Entry<K, V>> iterator = target.getResult().iterator();
            Map<K, V> data = new LinkedHashMap<>(target.getResult().size());

            while(iterator.hasNext()){
                Map.Entry<K, V> entry = iterator.next();
                data.put(entry.getKey(), entry.getValue());
            }

            return new ScanResult<>(target.getCursorAsBytes(), data);
        }
    }

    class ListScanResultConvert<V> implements ScanResultConvert<ScanResult<List<V>>, redis.clients.jedis
            .ScanResult<V>> {

        @Override
        public redis.clients.jedis.ScanResult<V> convert(final ScanResult<List<V>> source){
            return source == null ? null : new redis.clients.jedis.ScanResult<>(source.getCursor(), source.getResults
                    ());
        }

        @Override
        public ScanResult<List<V>> deconvert(redis.clients.jedis.ScanResult<V> target){
            return target == null ? null : new ScanResult<>(target.getCursorAsBytes(), target.getResult());
        }
    }

    class ListTupleScanResultConvert implements ScanResultConvert<ScanResult<List<Tuple>>, redis.clients.jedis
            .ScanResult<redis.clients.jedis.Tuple>> {

        @Override
        public redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple> convert(final ScanResult<List<Tuple>> source){
            if(source == null){
                return null;
            }

            List<redis.clients.jedis.Tuple> tuples = source.getResults() == null ? null : source.getResults().stream
                    ().map(tuple->new redis.clients.jedis.Tuple(tuple.getBinaryElement(), tuple.getScore())).collect
                    (Collectors.toList());

            return new redis.clients.jedis.ScanResult<>(source.getCursor(), tuples);
        }

        @Override
        public ScanResult<List<Tuple>> deconvert(final redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple>
                                                                 target){
            if(target == null){
                return null;
            }

            List<Tuple> tuples = target.getResult() == null ? null : target.getResult().stream().map(tuple->new Tuple
                    (tuple.getBinaryElement(), tuple.getScore())).collect(Collectors.toList());

            return new ScanResult<>(target.getCursor(), tuples);
        }
    }

    class SetTupleScanResultConvert implements ScanResultConvert<ScanResult<Set<Tuple>>, redis.clients.jedis
            .ScanResult<redis.clients.jedis.Tuple>> {

        @Override
        public redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple> convert(final ScanResult<Set<Tuple>> source){
            if(source == null){
                return null;
            }

            List<redis.clients.jedis.Tuple> tuples = source.getResults() == null ? null : source.getResults().stream
                    ().map(tuple->new redis.clients.jedis.Tuple(tuple.getBinaryElement(), tuple.getScore())).collect
                    (Collectors.toList());

            return new redis.clients.jedis.ScanResult<>(source.getCursor(), tuples);
        }

        @Override
        public ScanResult<Set<Tuple>> deconvert(final redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple> target){
            if(target == null){
                return null;
            }

            Set<Tuple> tuples = target.getResult() == null ? null : target.getResult().stream().map(tuple->new Tuple
                    (tuple.getBinaryElement(), tuple.getScore())).collect(Collectors.toCollection(LinkedHashSet::new));

            return new ScanResult<>(target.getCursor(), tuples);
        }
    }

}

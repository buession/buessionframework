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
package com.buession.redis;

import com.buession.redis.core.Cpu;
import com.buession.redis.core.Info;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class ReturnUtilsTest {

    @Test
    public void list(){
        //Serializer serializer = null;//new JSONSerializer();
        List<String> infos = new ArrayList<>(1);

        Info info = new Info();
        info.setCpu(new Cpu());

        //infos.add(serializer.encode(info));

        List<Info> infos1 = null;//retval(serializer, infos);// ReturnUtils.returnObjectValueFromListString(serializer,
        // infos);
        System.out.println(infos1.get(0).getCpu());
    }

    /*private <V> List<V> retval(Serializer serializer, List<String> data){
        final List<V> result = new ArrayList<>(data.size());

        for(String value : data){
            result.add(serializer.decode(value, new TypeReference<V>() {

            }));
        }

        return result;
    }*/

}

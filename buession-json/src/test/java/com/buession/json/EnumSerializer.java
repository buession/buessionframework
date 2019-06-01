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
 * | License: http://buession.buession.com.cn/LICENSE 												       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class EnumSerializer {

    @Test
    public void test(){
        User user = new User();

        user.setId(100);
        user.setUsername("admin");
        user.setType(Type.ACTIVE);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper objectMapper1 = new ObjectMapper();

        try{
            String str = objectMapper.writeValueAsString(user);
            System.out.println(str);
            objectMapper1.readValue(str, User.class);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public final static class User {

        private int id;

        private String username;

        private Type type;

        public int getId(){
            return id;
        }

        public void setId(final int id){
            this.id = id;
        }

        public String getUsername(){
            return username;
        }

        public void setUsername(final String username){
            this.username = username;
        }

        public Type getType(){
            return type;
        }

        public void setType(final Type type){
            this.type = type;
        }
    }

    public enum Type {

        REGISTER("register", "注册模板"),

        FIND_PASSWORD("find_password", "找回密码"),

        ACTIVE("active", "激活账号");

        private String value;

        private String description;

        Type(String value, String description){
            this.value = value;
            this.description = description;
        }

        public String getValue(){
            return value;
        }

        public String getDescription(){
            return description;
        }

    }

}

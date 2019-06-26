/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.thesaurus.spring;

import com.buession.thesaurus.core.Type;

/**
 * @author Yong.Teng
 */
public class ThesaurusFactory {

    private Type type;

    public ThesaurusFactory(){
    }

    public ThesaurusFactory(Type type){
        this.type = type;
    }

    public ThesaurusFactory(String type){
        try{
            this.type = Enum.valueOf(Type.class, type);
        }catch(Exception e){
            for(Type row : Type.values()){
                if(row.getId().equals(type)){
                    this.type = row;
                    break;
                }
            }
        }
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }
}

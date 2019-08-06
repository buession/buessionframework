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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.model;

import java.io.Serializable;

/**
 * 与IP地址相关联的城市数据
 *
 * @author Yong.Teng
 */
public final class District implements Serializable {

    private final static long serialVersionUID = -8098424244620246891L;

    private final int geoNameId;

    private final int code;

    private final String originalName;

    private final String name;

    private final String fullName;

    private final Postal postal;

    private final District parent;

    public District(final int geoNameId, final int code, final String originalName, final String name, final Postal
            postal, final District parent){
        this.geoNameId = geoNameId;
        this.code = code;
        this.originalName = originalName;
        this.name = name;
        this.postal = postal;
        this.parent = parent;

        if(name != null){
            this.fullName = parent != null ? parent.getName() + name : name;
        }else{
            this.fullName = null;
        }
    }

    public int getGeoNameId(){
        return geoNameId;
    }

    public int getCode(){
        return code;
    }

    public String getOriginalName(){
        return originalName;
    }

    public String getName(){
        return name;
    }

    public String getFullName(){
        return fullName;
    }

    public Postal getPostal(){
        return postal;
    }

    public District getParent(){
        return parent;
    }

    @Override
    public String toString(){
        return "District{" + "geoNameId=" + geoNameId + ", code=" + code + ", originalName='" + originalName + '\'' +
                ", name='" + name + '\'' + ", fullName='" + fullName + '\'' + ", postal=" + postal + ", parent=" +
                parent + '}';
    }
}

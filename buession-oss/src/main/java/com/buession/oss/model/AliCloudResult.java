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
package com.buession.oss.model;

/**
 * @author Yong.Teng
 */
public class AliCloudResult {

    private String status;

    private String bucket;

    private String object;

    private Integer fileSize;

    public String getStatus(){
        return status;
    }

    public void setStatus(final String status){
        this.status = status;
    }

    public String getBucket(){
        return bucket;
    }

    public void setBucket(final String bucket){
        this.bucket = bucket;
    }

    public String getObject(){
        return object;
    }

    public void setObject(final String object){
        this.object = object;
    }

    public Integer getFileSize(){
        return fileSize;
    }

    public void setFileSize(final Integer fileSize){
        this.fileSize = fileSize;
    }
}

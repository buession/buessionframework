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
package com.buession.oss;

import com.buession.core.validator.Validate;
import com.buession.oss.core.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public abstract class AbstractOSSClient implements OSSClient {

    protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String endpoint;

    private String accessKeyId;

    private String secretAccessKey;

    private String baseUrl;

    public AbstractOSSClient(){

    }

    public AbstractOSSClient(String endpoint, String accessKeyId, String secretAccessKey){
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
    }

    public String getEndpoint(){
        return endpoint;
    }

    public void setEndpoint(String endpoint){
        this.endpoint = endpoint;
    }

    public String getAccessKeyId(){
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId){
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey(){
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey){
        this.secretAccessKey = secretAccessKey;
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public void setBaseUrl(final String baseUrl){
        this.baseUrl = baseUrl;
    }

    /**
     * 上传文件
     *
     * @param bucketName
     *         Bucket 名字
     * @param file
     *         文件
     * @param path
     *         上传路径
     *
     * @return 上传结果
     */
    @Override
    public Result upload(final String bucketName, final File file, final String path){
        Result result = doUpload(bucketName, file, path);
        return buildResult(bucketName, path, result);
    }

    /**
     * 上传文件
     *
     * @param bucketName
     *         Bucket 名字
     * @param file
     *         文件
     * @param path
     *         上传路径
     *
     * @return 上传结果
     */
    @Override
    public Result upload(final String bucketName, final Path file, final String path){
        return upload(bucketName, file.toFile(), path);
    }

    /**
     * 上传文件
     *
     * @param bucketName
     *         Bucket 名字
     * @param stream
     *         文件流
     * @param path
     *         上传路径
     *
     * @return 上传结果
     */
    @Override
    public Result upload(final String bucketName, final InputStream stream, final String path){
        Result result = doUpload(bucketName, stream, path);
        return buildResult(bucketName, path, result);
    }

    protected abstract Result doUpload(final String bucketName, final File file, final String path);

    protected abstract Result doUpload(final String bucketName, final InputStream stream, final String path);

    protected abstract String getDefaultBaseUrl(final String bucketName);

    private Result buildResult(final String bucketName, final String path, final Result result){
        result.setPath(path);

        String baseUrl = getBaseUrl();

        if(Validate.hasText(baseUrl) == false){
            baseUrl = getDefaultBaseUrl(bucketName);
        }

        if(baseUrl.endsWith(PATH_SEPARATOR) == false){
            if(path.startsWith(PATH_SEPARATOR)){
                result.setUrl(baseUrl + path.substring(1));
            }else{
                result.setUrl(baseUrl + path);
            }
        }else{
            if(path.startsWith(PATH_SEPARATOR)){
                result.setUrl(baseUrl + path);
            }else{
                result.setUrl(baseUrl + PATH_SEPARATOR + path);
            }
        }

        return result;
    }
}

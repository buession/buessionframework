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

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.ProcessObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.buession.oss.core.Result;
import com.buession.oss.model.AliCloudResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;

/**
 * @author Yong.Teng
 */
public class AliCloudOSSClient extends AbstractOSSClient {

    public final static String SUCCESSFUL_VALUE = "OK";

    private OSS ossClient = null;

    private final static Logger logger = LoggerFactory.getLogger(AliCloudOSSClient.class);

    public AliCloudOSSClient(){
        super();
    }

    public AliCloudOSSClient(String endpoint, String accessKeyId, String secretAccessKey){
        super(endpoint, accessKeyId, secretAccessKey);
    }

    protected OSS getOssClient(){
        if(ossClient == null){
            ossClient = new OSSClientBuilder().build(getEndpoint(), getAccessKeyId(), getSecretAccessKey());
        }

        return ossClient;
    }

    /**
     * 图片裁剪
     *
     * @param bucketName
     *         Bucket 名字
     * @param path
     *         图片路径
     * @param width
     *         宽度
     * @param height
     *         高度
     * @param x
     *         X 坐标起点
     * @param y
     *         Y 坐标起点
     *
     * @return 裁剪结果
     */
    @Override
    public Result crop(final String bucketName, final String path, final int width, final int height, final int x,
                       final int y){
        final String objectKey = path.startsWith("/") ? path.substring(1) : path;
        final StringBuilder sbStyle = new StringBuilder();
        final Formatter styleFormatter = new Formatter(sbStyle);

        sbStyle.append("image/crop");
        sbStyle.append(",w_");
        sbStyle.append(width);
        sbStyle.append(",h_");
        sbStyle.append(height);
        sbStyle.append(",x_");
        sbStyle.append(x);
        sbStyle.append(",y_");
        sbStyle.append(y);
        sbStyle.append(",r_1");

        styleFormatter.format("|sys/saveas,o_%s,b_%s", BinaryUtil.toBase64String(objectKey.getBytes()), BinaryUtil
                .toBase64String(bucketName.getBytes()));

        ProcessObjectRequest request = new ProcessObjectRequest(bucketName, objectKey, sbStyle.toString());
        GenericResult genericResult = getOssClient().processObject(request);

        try{
            byte[] readStreamAsByteArray = IOUtils.readStreamAsByteArray(genericResult.getResponse().getContent());
            genericResult.getResponse().getContent().close();

            ObjectMapper objectMapper = new ObjectMapper();
            AliCloudResult aliCloudResult = objectMapper.readValue(readStreamAsByteArray, AliCloudResult.class);

            if(aliCloudResult == null){
                return null;
            }

            if(SUCCESSFUL_VALUE.equals(aliCloudResult.getStatus())){
                Result result = buildResult(genericResult, path);

                result.setSize(aliCloudResult.getFileSize());

                return result;
            }
        }catch(IOException e){
            logger.error("Crop images '{}' error.", path, e);
        }

        return null;
    }

    @Override
    public void close(){
        getOssClient().shutdown();
    }

    @Override
    protected Result doUpload(final String bucketName, final File file, final String path){
        PutObjectResult putObjectResult = getOssClient().putObject(bucketName, path, file);
        return buildResult(putObjectResult, path);
    }

    @Override
    protected Result doUpload(final String bucketName, final InputStream stream, final String path){
        PutObjectResult putObjectResult = getOssClient().putObject(bucketName, path, stream);
        return buildResult(putObjectResult, path);
    }

    @Override
    protected String getDefaultBaseUrl(final String bucketName){
        final StringBuffer sb = new StringBuffer();

        sb.append("https://");
        sb.append(bucketName);
        sb.append('.');
        sb.append(getEndpoint());
        sb.append('/');

        return sb.toString();
    }

    private Result buildResult(final GenericResult genericResult, final String path){
        if(genericResult == null){
            return null;
        }

        Result result = new Result();

        result.setPath(path);

        return result;
    }
}
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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.operations.s3;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.ProcessObjectRequest;
import com.buession.core.utils.Assert;
import com.buession.oss.exception.AuthorizationException;
import com.buession.oss.exception.BucketAlreadyExistException;
import com.buession.oss.exception.BucketNotExistException;
import com.buession.oss.exception.OSSException;
import com.buession.oss.operations.AbstractOperations;
import com.buession.oss.operations.alicloud.AliCloudOperations;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractAliCloudOperations extends AbstractOperations implements AliCloudOperations {

	protected OSS ossClient = null;

	public AbstractAliCloudOperations(final OSS ossClient){
		Assert.isNull(ossClient, "OSS client cloud not be null.");
		this.ossClient = ossClient;
	}

	protected <T> T processObject(final ProcessObjectRequest request, final Class<T> clazz) throws IOException,
			OSSException{
		GenericResult genericResult = null;

		try{
			genericResult = ossClient.processObject(request);
			if(genericResult == null){
				return null;
			}

			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			return objectMapper.readValue(genericResult.getResponse().getContent(), clazz);
		}catch(IOException e){
			throw e;
		}catch(Exception e){
			throw convertException(e);
		}finally{
			if(genericResult != null){
				try{
					genericResult.getResponse().getContent().close();
				}catch(IOException e){
				}
			}
		}
	}

	protected static OSSException convertException(Exception e){
		return convertException(null, e);
	}

	protected static OSSException convertException(String bucketName, Exception e){
		if(e instanceof ClientException){
			ClientException ex = (ClientException) e;
			return new OSSException(e.getMessage(), ex, ex.getRequestId(), ex.getErrorCode());
		}else if(e instanceof com.aliyun.oss.OSSException){
			com.aliyun.oss.OSSException ex = (com.aliyun.oss.OSSException) e;

			if("SignatureDoesNotMatch".equals(ex.getErrorCode())){
				return new AuthorizationException(ex.getErrorMessage(), ex, ex.getRequestId(), ex.getErrorCode());
			}else if("BucketAlreadyExists".equals(ex.getErrorCode())){
				return new BucketAlreadyExistException(bucketName, ex, ex.getRequestId(), ex.getErrorCode());
			}else if("NoSuchBucket".equals(ex.getErrorCode())){
				return new BucketNotExistException(bucketName, ex, ex.getRequestId(), ex.getErrorCode());
			}else{
				return new OSSException(ex.getErrorMessage(), ex, ex.getRequestId(), ex.getErrorCode());
			}
		}else{
			return new OSSException(e.getMessage());
		}
	}

}

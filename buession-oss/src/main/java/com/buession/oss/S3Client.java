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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.buession.core.utils.Assert;
import com.buession.oss.operations.BucketOperations;
import com.buession.oss.operations.ImageOperations;
import com.buession.oss.operations.ObjectOperations;
import com.buession.oss.operations.alicloud.AliCloudBucketOperations;
import com.buession.oss.operations.alicloud.AliCloudImageOperations;
import com.buession.oss.operations.alicloud.AliCloudObjectOperations;

/**
 * 阿里云 OSS 客户端
 *
 * @author Yong.Teng
 */
public class AliCloudOSSClient extends AbstractOSSClient {

	private OSS ossClient = null;

	public AliCloudOSSClient(String endpoint, String accessKeyId, String secretAccessKey){
		super(endpoint, accessKeyId, secretAccessKey);
	}

	protected OSS getOssClient(){
		Assert.isBlank(getEndpoint(), "OSS endpoint cloud not be null or empty.");
		Assert.isBlank(getAccessKeyId(), "OSS access key id cloud not be null or empty.");
		Assert.isBlank(getSecretAccessKey(), "OSS secret access key cloud not be null or empty.");

		if(ossClient == null){
			ossClient = new OSSClientBuilder().build(getEndpoint(), getAccessKeyId(), getSecretAccessKey());
		}

		return ossClient;
	}

	@Override
	public void close(){
		getOssClient().shutdown();
	}

	@Override
	protected BucketOperations createBucketOperations(){
		return new AliCloudBucketOperations(getOssClient());
	}

	@Override
	protected ObjectOperations createObjectOperations(){
		return new AliCloudObjectOperations(getOssClient());
	}

	@Override
	protected ImageOperations createImageOperations(){
		return new AliCloudImageOperations(getOssClient());
	}

}
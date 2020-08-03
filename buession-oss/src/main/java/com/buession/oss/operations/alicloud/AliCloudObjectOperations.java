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
package com.buession.oss.operations.alicloud;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.oss.model.File;
import com.buession.oss.exception.OSSException;
import com.buession.oss.operations.ObjectOperations;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public class AliCloudObjectOperations extends AbstractAliCloudOperations implements ObjectOperations {

	public AliCloudObjectOperations(OSS ossClient){
		super(ossClient);
	}

	@Override
	public boolean exists(String bucketName, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "File path cloud not be null or empty.");

		try{
			return ossClient.doesObjectExist(bucketName, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(file, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, file);
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, Path file, String path) throws OSSException{
		return upload(bucketName, file.toFile(), path);
	}

	@Override
	public File upload(String bucketName, InputStream stream, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(stream, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, stream);
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path, boolean overwrite) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(file, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");

		ObjectMetadata objectMetadata = new ObjectMetadata();

		objectMetadata.setHeader("x-oss-forbid-overwrite", overwrite == false);

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, file, objectMetadata);
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, Path file, String path, boolean overwrite) throws OSSException{
		return upload(bucketName, file.toFile(), path, overwrite);
	}

	@Override
	public File upload(String bucketName, InputStream stream, String path, boolean overwrite) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(stream, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");

		ObjectMetadata objectMetadata = new ObjectMetadata();

		objectMetadata.setHeader("x-oss-forbid-overwrite", overwrite == false);

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, stream, objectMetadata);
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Status delete(String bucketName, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Delete file path cloud not be null or empty.");

		try{
			ossClient.deleteObject(bucketName, path);
			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	private File buildResult(final GenericResult genericResult, final String path){
		File file = new File();

		file.setPath(path);

		return file;
	}

}

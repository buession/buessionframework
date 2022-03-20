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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.operations.s3;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.TagSet;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.buession.core.utils.ArrayUtils;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.oss.core.Acl;
import com.buession.oss.exception.OSSException;
import com.buession.oss.model.File;
import com.buession.oss.operations.ObjectOperations;
import com.buession.oss.operations.alicloud.ObjectMetadataBuilder;
import com.buession.oss.operations.s3.convert.AclConverter;
import com.google.common.collect.Maps;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class AliCloudObjectOperations extends AbstractAliCloudOperations implements ObjectOperations {

	public AliCloudObjectOperations(final OSS ossClient){
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
	public Status setAcl(String bucketName, String path, Acl acl) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "File path cloud not be null or empty.");

		AclConverter.BuessionToAliCloudOSSConverter bucketAclConverter = new AclConverter.BuessionToAliCloudOSSConverter();

		try{
			ossClient.setObjectAcl(bucketName, path, bucketAclConverter.convert(acl));
			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Status setTags(String bucketName, String path, Map<String, String> tags) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "File path cloud not be null or empty.");

		try{
			ossClient.setObjectTagging(bucketName, path, tags);
			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Map<String, String> getTags(String bucketName, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "File path cloud not be null or empty.");

		try{
			TagSet tagSet = ossClient.getObjectTagging(bucketName, path);
			return tagSet.getAllTags();
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Status deleteTags(String bucketName, String path, String... tags) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "File path cloud not be null or empty.");

		try{
			if(Validate.isEmpty(tags)){
				ossClient.deleteObjectTagging(bucketName, path);
			}else{
				TagSet tagSet = ossClient.getObjectTagging(bucketName, path);
				Map<String, String> oldTags = tagSet.getAllTags();

				ossClient.deleteObjectTagging(bucketName, path);

				Map<String, String> newTags = Maps.filterKeys(oldTags, key->ArrayUtils.contains(tags, key) == false);

				ossClient.setObjectTagging(bucketName, path, newTags);
			}

			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(file, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");

		com.buession.io.file.File bFile = new com.buession.io.file.File(file);
		ObjectMetadataBuilder objectMetadataBuilder = ObjectMetadataBuilder.create()
				.setContentType(bFile.getMimeType().toString());

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, file,
					objectMetadataBuilder.build());
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
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

		ObjectMetadataBuilder objectMetadataBuilder = ObjectMetadataBuilder.create()
				.setHeader("x-oss-forbid-overwrite", overwrite == false);

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, file,
					objectMetadataBuilder.build());
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, InputStream stream, String path, boolean overwrite) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(stream, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");

		ObjectMetadataBuilder objectMetadataBuilder = ObjectMetadataBuilder.create()
				.setHeader("x-oss-forbid-overwrite", overwrite == false);

		try{
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, stream,
					objectMetadataBuilder.build());
			return buildResult(putObjectResult, path);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path, long partSize) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(file, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");
		Assert.isZeroNegative(partSize, "PartSize cloud not equal or less than 0.");

		try{
			return doPartUpload(bucketName, file, path, partSize, null);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path, long partSize, boolean overwrite)
			throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isNull(file, "Upload file cloud not be null.");
		Assert.isBlank(path, "Upload storage path cloud not be null or empty.");
		Assert.isZeroNegative(partSize, "PartSize cloud not equal or less than 0.");

		try{
			return doPartUpload(bucketName, file, path, partSize, overwrite);
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

	private File doPartUpload(String bucketName, java.io.File file, String path, long partSize, Boolean overwrite)
			throws Exception{
		// 创建InitiateMultipartUploadRequest对象。
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, path);

		com.buession.io.file.File bFile = new com.buession.io.file.File(file);
		ObjectMetadataBuilder objectMetadataBuilder = ObjectMetadataBuilder.create()
				.setContentType(bFile.getMimeType().toString());

		if(overwrite != null){
			objectMetadataBuilder.setHeader("x-oss-forbid-overwrite", overwrite == false);
		}

		request.setObjectMetadata(objectMetadataBuilder.build());

		// 初始化分片
		InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);

		String uploadId = upresult.getUploadId();

		// partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
		List<PartETag> partETags = new ArrayList<>();

		long fileSize = file.length();
		int partCount = (int) (fileSize / partSize);
		if(fileSize % partSize != 0){
			partCount++;
		}

		// 遍历分片上传。
		for(int i = 0; i < partCount; i++){
			long startPosition = i * partSize;
			long currentPartSize = (i + 1 == partCount) ? (fileSize - startPosition) : partSize;

			InputStream inputStream = new FileInputStream(file);
			// 跳过已经上传的分片。
			inputStream.skip(startPosition);
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName(bucketName);
			uploadPartRequest.setKey(path);
			uploadPartRequest.setUploadId(uploadId);
			uploadPartRequest.setInputStream(inputStream);
			// 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
			uploadPartRequest.setPartSize(currentPartSize);
			// 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出此范围，OSS将返回InvalidArgument错误码。
			uploadPartRequest.setPartNumber(i + 1);
			// 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
			UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
			// 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
			partETags.add(uploadPartResult.getPartETag());
		}

		// 创建CompleteMultipartUploadRequest对象。
		// 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
		CompleteMultipartUploadRequest completeMultipartUploadRequest =
				new CompleteMultipartUploadRequest(bucketName, path, uploadId, partETags);

		// 完成分片上传。
		CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(
				completeMultipartUploadRequest);
		return buildResult(completeMultipartUploadResult, path);
	}

	private File buildResult(final GenericResult genericResult, final String path){
		File file = new File();

		file.setPath(path);

		return file;
	}

}

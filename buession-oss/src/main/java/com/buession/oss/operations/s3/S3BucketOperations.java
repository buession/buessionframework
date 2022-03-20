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
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.SetBucketTaggingRequest;
import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.oss.core.Acl;
import com.buession.oss.core.DataRedundancyType;
import com.buession.oss.core.StorageClass;
import com.buession.oss.exception.BucketAlreadyExistException;
import com.buession.oss.exception.BucketNotExistException;
import com.buession.oss.exception.OSSException;
import com.buession.oss.model.Bucket;
import com.buession.oss.operations.BucketOperations;
import com.buession.oss.operations.s3.convert.AclConverter;
import com.buession.oss.operations.s3.convert.BucketConverter;
import com.buession.oss.operations.s3.convert.DataRedundancyTypeConverter;
import com.buession.oss.operations.s3.convert.StorageClassConverter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class AliCloudBucketOperations extends AbstractS3Operations implements BucketOperations {

	public AliCloudBucketOperations(final OSS ossClient){
		super(ossClient);
	}

	@Override
	public Status create(String bucketName) throws BucketAlreadyExistException, OSSException{
		return create(bucketName, null, null, null);
	}

	@Override
	public Status create(String bucketName, Acl acl) throws BucketAlreadyExistException, OSSException{
		return create(bucketName, acl, null, null);
	}

	@Override
	public Status create(String bucketName, StorageClass storageClass) throws BucketAlreadyExistException,
			OSSException{
		return create(bucketName, null, storageClass, null);
	}

	@Override
	public Status create(String bucketName, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException,
			OSSException{
		return create(bucketName, null, null, dataRedundancyType);
	}

	@Override
	public Status create(String bucketName, Acl acl, StorageClass storageClass)
			throws BucketAlreadyExistException, OSSException{
		return create(bucketName, acl, storageClass, null);
	}

	@Override
	public Status create(String bucketName, Acl acl, DataRedundancyType dataRedundancyType)
			throws BucketAlreadyExistException, OSSException{
		return create(bucketName, acl, null, dataRedundancyType);
	}

	@Override
	public Status create(String bucketName, StorageClass storageClass, DataRedundancyType dataRedundancyType)
			throws BucketAlreadyExistException, OSSException{
		return create(bucketName, null, storageClass, dataRedundancyType);
	}

	@Override
	public Status create(String bucketName, Acl acl, StorageClass storageClass,
						 DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

		if(acl != null){
			AclConverter.BuessionToAliCloudOSSConverter bucketAclConverter = new AclConverter.BuessionToAliCloudOSSConverter();
			CannedAccessControlList aliCloudOSSCannedAccessControlList = bucketAclConverter.convert(
					acl);

			createBucketRequest.setCannedACL(
					aliCloudOSSCannedAccessControlList ==
							null ? CannedAccessControlList.Default : aliCloudOSSCannedAccessControlList);
		}else{
			createBucketRequest.setCannedACL(CannedAccessControlList.Default);
		}

		if(storageClass != null){
			StorageClassConverter.BuessionToAliCloudOSSConverter storageClassConverter = new StorageClassConverter.BuessionToAliCloudOSSConverter();
			com.aliyun.oss.model.StorageClass aliCloudOSSStorageClass = storageClassConverter.convert(
					storageClass);

			if(aliCloudOSSStorageClass != null){
				createBucketRequest.setStorageClass(aliCloudOSSStorageClass);
			}
		}

		if(dataRedundancyType != null){
			DataRedundancyTypeConverter.BuessionToAliCloudOSSConverter dataRedundancyTypeConverter = new DataRedundancyTypeConverter.BuessionToAliCloudOSSConverter();
			com.aliyun.oss.model.DataRedundancyType aliCloudOSSDataRedundancyType = dataRedundancyTypeConverter.convert(
					dataRedundancyType);

			if(aliCloudOSSDataRedundancyType != null){
				createBucketRequest.setDataRedundancyType(aliCloudOSSDataRedundancyType);
			}
		}

		try{
			com.aliyun.oss.model.Bucket bucket = ossClient.createBucket(createBucketRequest);
			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public List<Bucket> lists() throws OSSException{
		List<com.aliyun.oss.model.Bucket> buckets = ossClient.listBuckets();
		BucketConverter.AliCloudOSSToBuessionConverter bucketConverter = new BucketConverter.AliCloudOSSToBuessionConverter();

		return buckets.stream().map(bucketConverter::convert).collect(Collectors.toList());
	}

	@Override
	public Bucket get(String bucketName) throws BucketNotExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		com.aliyun.oss.model.BucketInfo bucketInfo;
		BucketConverter.AliCloudOSSToBuessionConverter bucketConverter = new BucketConverter.AliCloudOSSToBuessionConverter();

		try{
			bucketInfo = ossClient.getBucketInfo(bucketName);

			final Bucket bucket = bucketConverter.convert(bucketInfo.getBucket());
			if(bucket == null){
				return null;
			}

			bucket.setDescription(bucketInfo.getComment());

			if(bucketInfo.getCannedACL() != null){
				AclConverter.AliCloudOSSToBuessionConverter bucketAclConverter = new AclConverter.AliCloudOSSToBuessionConverter();
				bucket.setAcl(bucketAclConverter.convert(bucketInfo.getCannedACL()));
			}

			if(bucketInfo.getDataRedundancyType() != null){
				DataRedundancyTypeConverter.AliCloudOSSToBuessionConverter dataRedundancyTypeConverter = new DataRedundancyTypeConverter.AliCloudOSSToBuessionConverter();
				bucket.setDataRedundancyType(dataRedundancyTypeConverter.convert(bucketInfo.getDataRedundancyType()));
			}

			return bucket;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public boolean exists(String bucketName) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		try{
			return ossClient.doesBucketExist(bucketName);
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Status setAcl(String bucketName, Acl acl) throws BucketNotExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		AclConverter.BuessionToAliCloudOSSConverter bucketAclConverter = new AclConverter.BuessionToAliCloudOSSConverter();

		try{
			ossClient.setBucketAcl(bucketName, bucketAclConverter.convert(acl));

			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Status setTags(String bucketName, Map<String, String> tags)
			throws BucketNotExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		SetBucketTaggingRequest request = new SetBucketTaggingRequest(bucketName);

		tags.forEach(request::setTag);

		try{
			ossClient.setBucketTagging(request);

			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

	@Override
	public Status delete(String bucketName) throws BucketNotExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		try{
			ossClient.deleteBucket(bucketName);

			return Status.SUCCESS;
		}catch(Exception e){
			throw convertException(bucketName, e);
		}
	}

}

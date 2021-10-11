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
package com.buession.oss.operations.alicloud;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.oss.model.Bucket;
import com.buession.oss.core.BucketAcl;
import com.buession.oss.core.DataRedundancyType;
import com.buession.oss.core.Owner;
import com.buession.oss.core.StorageClass;
import com.buession.oss.exception.BucketAlreadyExistException;
import com.buession.oss.exception.BucketNotExistException;
import com.buession.oss.exception.OSSException;
import com.buession.oss.operations.BucketOperations;

/**
 * @author Yong.Teng
 */
public class AliCloudBucketOperations extends AbstractAliCloudOperations implements BucketOperations {

	public AliCloudBucketOperations(final OSS ossClient){
		super(ossClient);
	}

	@Override
	public Status create(String bucketName) throws BucketAlreadyExistException, OSSException{
		return create(bucketName, null, null, null);
	}

	@Override
	public Status create(String bucketName, BucketAcl acl) throws BucketAlreadyExistException, OSSException{
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
	public Status create(String bucketName, BucketAcl acl, StorageClass storageClass) throws BucketAlreadyExistException, OSSException{
		return create(bucketName, acl, storageClass, null);
	}

	@Override
	public Status create(String bucketName, BucketAcl acl, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		return create(bucketName, acl, null, dataRedundancyType);
	}

	@Override
	public Status create(String bucketName, StorageClass storageClass, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		return create(bucketName, null, storageClass, dataRedundancyType);
	}

	@Override
	public Status create(String bucketName, BucketAcl acl, StorageClass storageClass,
						 DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

		if(acl != null){
			switch(acl){
				case PUBLIC_READ_WRITE:
					createBucketRequest.setCannedACL(CannedAccessControlList.PublicReadWrite);
					break;
				case PUBLIC_READ:
					createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
					break;
				case PRIVATE:
					createBucketRequest.setCannedACL(CannedAccessControlList.Private);
					break;
				default:
					createBucketRequest.setCannedACL(CannedAccessControlList.Default);
					break;
			}
		}

		if(storageClass != null){
			switch(storageClass){
				case STANDARD:
					createBucketRequest.setStorageClass(com.aliyun.oss.model.StorageClass.Standard);
					break;
				case IA:
					createBucketRequest.setStorageClass(com.aliyun.oss.model.StorageClass.IA);
					break;
				case ARCHIVE:
					createBucketRequest.setStorageClass(com.aliyun.oss.model.StorageClass.Archive);
					break;
				case COLD_ARCHIVE:
					createBucketRequest.setStorageClass(com.aliyun.oss.model.StorageClass.ColdArchive);
					break;
				default:
					break;
			}
		}

		if(dataRedundancyType != null){
			switch(dataRedundancyType){
				case LRS:
					createBucketRequest.setDataRedundancyType(com.aliyun.oss.model.DataRedundancyType.LRS);
					break;
				case ZRS:
					createBucketRequest.setDataRedundancyType(com.aliyun.oss.model.DataRedundancyType.ZRS);
					break;
				default:
					break;
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
	public Bucket get(String bucketName) throws BucketNotExistException, OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");

		com.aliyun.oss.model.BucketInfo bucketInfo;

		try{
			bucketInfo = ossClient.getBucketInfo(bucketName);

			final Owner owner = new Owner();
			final Bucket bucket = new Bucket();

			final com.aliyun.oss.model.Owner aliCloudOwner = bucketInfo.getBucket().getOwner();

			owner.setId(aliCloudOwner.getId());
			owner.setName(aliCloudOwner.getDisplayName());

			bucket.setName(bucketInfo.getBucket().getName());
			bucket.setOwner(owner);
			bucket.setCreatedAt(bucketInfo.getBucket().getCreationDate());
			bucket.setDescription(bucketInfo.getComment());

			if(bucketInfo.getCannedACL() != null){
				switch(bucketInfo.getCannedACL()){
					case PublicReadWrite:
						bucket.setAcl(BucketAcl.PUBLIC_READ_WRITE);
						break;
					case PublicRead:
						bucket.setAcl(BucketAcl.PUBLIC_READ);
						break;
					case Private:
						bucket.setAcl(BucketAcl.PRIVATE);
						break;
					case Default:
						bucket.setAcl(BucketAcl.DEFAULT);
						break;
					default:
						break;
				}
			}

			if(bucketInfo.getBucket().getStorageClass() != null){
				switch(bucketInfo.getBucket().getStorageClass()){
					case Standard:
						bucket.setStorageClass(StorageClass.STANDARD);
						break;
					case IA:
						bucket.setStorageClass(StorageClass.IA);
						break;
					case Archive:
						bucket.setStorageClass(StorageClass.ARCHIVE);
						break;
					case ColdArchive:
						bucket.setStorageClass(StorageClass.COLD_ARCHIVE);
						break;
					default:
						break;
				}
			}

			if(bucketInfo.getDataRedundancyType() != null){
				switch(bucketInfo.getDataRedundancyType()){
					case LRS:
						bucket.setDataRedundancyType(DataRedundancyType.LRS);
						break;
					case ZRS:
						bucket.setDataRedundancyType(DataRedundancyType.ZRS);
						break;
					default:
						break;
				}
			}

			return bucket;
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

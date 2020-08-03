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
package com.buession.oss.operations;

import com.buession.lang.Status;
import com.buession.oss.model.Bucket;
import com.buession.oss.core.BucketAcl;
import com.buession.oss.core.DataRedundancyType;
import com.buession.oss.core.StorageClass;
import com.buession.oss.exception.BucketAlreadyExistException;
import com.buession.oss.exception.BucketNotExistException;
import com.buession.oss.exception.OSSException;

/**
 * Bucket 操作
 *
 * @author Yong.Teng
 */
public interface BucketOperations extends Operations {

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName) throws BucketAlreadyExistException, OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param acl
	 * 		权限
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, BucketAcl acl) throws BucketAlreadyExistException, OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param storageClass
	 * 		存储类型
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, StorageClass storageClass) throws BucketAlreadyExistException, OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param dataRedundancyType
	 * 		数据容灾类型
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException,
			OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param acl
	 * 		权限
	 * @param storageClass
	 * 		存储类型
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, BucketAcl acl, StorageClass storageClass) throws BucketAlreadyExistException,
			OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param acl
	 * 		权限
	 * @param dataRedundancyType
	 * 		数据容灾类型
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, BucketAcl acl, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param storageClass
	 * 		存储类型
	 * @param dataRedundancyType
	 * 		数据容灾类型
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, StorageClass storageClass, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException;

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 * @param acl
	 * 		权限
	 * @param storageClass
	 * 		存储类型
	 * @param dataRedundancyType
	 * 		数据容灾类型
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws BucketAlreadyExistException
	 * 		Bucket 已存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status create(String bucketName, BucketAcl acl, StorageClass storageClass, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException;

	/**
	 * 获取 Bucket 信息
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 *
	 * @return Bucket 信息
	 *
	 * @throws BucketNotExistException
	 * 		Bucket 不存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Bucket get(String bucketName) throws BucketNotExistException, OSSException;

	/**
	 * 删除 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 *
	 * @return Bucket 信息
	 *
	 * @throws BucketNotExistException
	 * 		Bucket 不存在
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status delete(String bucketName) throws BucketNotExistException, OSSException;

}

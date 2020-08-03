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

import com.buession.lang.Status;
import com.buession.oss.core.ImageResizeMode;
import com.buession.oss.model.Bucket;
import com.buession.oss.core.BucketAcl;
import com.buession.oss.core.DataRedundancyType;
import com.buession.oss.model.File;
import com.buession.oss.core.StorageClass;
import com.buession.oss.exception.BucketAlreadyExistException;
import com.buession.oss.exception.BucketNotExistException;
import com.buession.oss.exception.OSSException;
import com.buession.oss.operations.BucketOperations;
import com.buession.oss.operations.ImageOperations;
import com.buession.oss.operations.ObjectOperations;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public abstract class AbstractOSSClient implements OSSClient {

	private String endpoint;

	private String accessKeyId;

	private String secretAccessKey;

	private BucketOperations bucketOperations;

	private ObjectOperations objectOperations;

	private ImageOperations imageOperations;

	public AbstractOSSClient(String endpoint, String accessKeyId, String secretAccessKey){
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.secretAccessKey = secretAccessKey;

		initialize();
	}

	public String getEndpoint(){
		return endpoint;
	}

	public String getAccessKeyId(){
		return accessKeyId;
	}

	public String getSecretAccessKey(){
		return secretAccessKey;
	}

	@Override
	public Status createBucket(String bucketName) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName);
	}

	@Override
	public Status createBucket(String bucketName, BucketAcl acl) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName, acl);
	}

	@Override
	public Status createBucket(String bucketName, StorageClass storageClass) throws BucketAlreadyExistException,
			OSSException{
		return bucketOperations.create(bucketName, storageClass);
	}

	@Override
	public Status createBucket(String bucketName, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName, dataRedundancyType);
	}

	@Override
	public Status createBucket(String bucketName, BucketAcl acl, StorageClass storageClass) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName, acl, storageClass);
	}

	@Override
	public Status createBucket(String bucketName, BucketAcl acl, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName, acl, dataRedundancyType);
	}

	@Override
	public Status createBucket(String bucketName, StorageClass storageClass, DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName, storageClass, dataRedundancyType);
	}

	@Override
	public Status createBucket(String bucketName, BucketAcl acl, StorageClass storageClass,
			DataRedundancyType dataRedundancyType) throws BucketAlreadyExistException, OSSException{
		return bucketOperations.create(bucketName, acl, storageClass, dataRedundancyType);
	}

	@Override
	public Bucket getBucket(String bucketName) throws BucketNotExistException, OSSException{
		return bucketOperations.get(bucketName);
	}

	@Override
	public Status deleteBucket(String bucketName) throws BucketNotExistException, OSSException{
		return bucketOperations.delete(bucketName);
	}

	@Override
	public boolean fileExists(String bucketName, String path) throws OSSException{
		return objectOperations.exists(bucketName, path);
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path) throws OSSException{
		return objectOperations.upload(bucketName, file, path);
	}

	@Override
	public File upload(String bucketName, Path file, String path) throws OSSException{
		return objectOperations.upload(bucketName, file, path);
	}

	@Override
	public File upload(String bucketName, InputStream stream, String path) throws OSSException{
		return objectOperations.upload(bucketName, stream, path);
	}

	@Override
	public File upload(String bucketName, java.io.File file, String path, boolean overwrite) throws OSSException{
		return objectOperations.upload(bucketName, file, path, overwrite);
	}

	@Override
	public File upload(String bucketName, Path file, String path, boolean overwrite) throws OSSException{
		return objectOperations.upload(bucketName, file, path, overwrite);
	}

	@Override
	public File upload(String bucketName, InputStream stream, String path, boolean overwrite) throws OSSException{
		return objectOperations.upload(bucketName, stream, path, overwrite);
	}

	@Override
	public File imageResize(String bucketName, String path, double percent) throws OSSException{
		return imageOperations.resize(bucketName, path, percent);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, boolean limit) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, limit);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, boolean limit, String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, limit, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, int l, int s) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, l, s);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, int l, int s, String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, l, s, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, int l, int s, boolean limit) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, l, s, limit);
	}

	@Override
	public File imageResize(String bucketName, String path, int width, int height, int l, int s, boolean limit,
			String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, width, height, l, s, limit, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height,
			String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height,
			boolean limit) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, limit);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, boolean limit
			, String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, limit, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, l, s);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
			String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, l, s, padColor);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
			boolean limit) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, l, s, limit);
	}

	@Override
	public File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
			boolean limit, String padColor) throws OSSException{
		return imageOperations.resize(bucketName, path, mode, width, height, l, s, limit, padColor);
	}

	@Override
	public File imageCrop(final String bucketName, final String path, final int width, final int height, final int x,
			final int y) throws OSSException{
		return imageOperations.crop(bucketName, path, width, height, x, y);
	}

	@Override
	public File imageAutoRotate(String bucketName, String path) throws OSSException{
		return imageOperations.autoRotate(bucketName, path);
	}

	@Override
	public File imageRotate(String bucketName, String path, double degrees) throws OSSException{
		return imageOperations.rotate(bucketName, path, degrees);
	}

	@Override
	public Status deleteFile(String bucketName, String path) throws OSSException{
		return objectOperations.delete(bucketName, path);
	}

	private void initialize(){
		bucketOperations = createBucketOperations();
		objectOperations = createObjectOperations();
		imageOperations = createImageOperations();
	}

	protected abstract BucketOperations createBucketOperations();

	protected abstract ObjectOperations createObjectOperations();

	protected abstract ImageOperations createImageOperations();

}

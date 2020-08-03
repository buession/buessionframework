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
import com.buession.oss.model.File;
import com.buession.oss.exception.OSSException;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * Object 操作
 *
 * @author Yong.Teng
 */
public interface ObjectOperations extends Operations {

	/**
	 * 判断文件是否存在
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		文件路径
	 *
	 * @return 存在，返回 true；否则。返回 false
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	boolean exists(String bucketName, String path) throws OSSException;

	/**
	 * 上传文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param file
	 * 		文件
	 * @param path
	 * 		上传路径
	 *
	 * @return 上传结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File upload(String bucketName, java.io.File file, String path) throws OSSException;

	/**
	 * 上传文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param file
	 * 		文件
	 * @param path
	 * 		上传路径
	 *
	 * @return 上传结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File upload(String bucketName, Path file, String path) throws OSSException;

	/**
	 * 上传文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param stream
	 * 		文件流
	 * @param path
	 * 		上传路径
	 *
	 * @return 上传结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File upload(final String bucketName, final InputStream stream, final String path) throws OSSException;

	/**
	 * 上传文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param file
	 * 		文件
	 * @param path
	 * 		上传路径
	 * @param overwrite
	 * 		是否允许覆盖已存在文件
	 *
	 * @return 上传结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File upload(String bucketName, java.io.File file, String path, boolean overwrite) throws OSSException;

	/**
	 * 上传文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param file
	 * 		文件
	 * @param path
	 * 		上传路径
	 * @param overwrite
	 * 		是否允许覆盖已存在文件
	 *
	 * @return 上传结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File upload(String bucketName, Path file, String path, boolean overwrite) throws OSSException;

	/**
	 * 上传文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param stream
	 * 		文件流
	 * @param path
	 * 		上传路径
	 * @param overwrite
	 * 		是否允许覆盖已存在文件
	 *
	 * @return 上传结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File upload(String bucketName, InputStream stream, String path, boolean overwrite) throws OSSException;

	/**
	 * 删除文件
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		文件路径
	 *
	 * @return 删除成功，则返回 Status.SUCCESS
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status delete(String bucketName, String path) throws OSSException;

}

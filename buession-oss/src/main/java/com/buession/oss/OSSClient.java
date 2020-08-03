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
import com.buession.oss.exception.OSSException;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public interface OSSClient {

	String PATH_SEPARATOR = "/";

	/**
	 * 创建 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 *
	 * @return 成功，返回 Status.SUCCESS
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, BucketAcl acl) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, StorageClass storageClass) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, DataRedundancyType dataRedundancyType) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, BucketAcl acl, StorageClass storageClass) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, BucketAcl acl, DataRedundancyType dataRedundancyType) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, StorageClass storageClass, DataRedundancyType dataRedundancyType) throws OSSException;

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
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status createBucket(String bucketName, BucketAcl acl, StorageClass storageClass,
			DataRedundancyType dataRedundancyType) throws OSSException;

	/**
	 * 获取 Bucket 信息
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 *
	 * @return Bucket 信息
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Bucket getBucket(String bucketName) throws OSSException;

	/**
	 * 删除 Bucket
	 *
	 * @param bucketName
	 * 		Bucket 名称
	 *
	 * @return Bucket 信息
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	Status deleteBucket(String bucketName) throws OSSException;

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
	boolean fileExists(String bucketName, String path) throws OSSException;

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
	File upload(String bucketName, InputStream stream, String path) throws OSSException;

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
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param percent
	 * 		缩放百分比
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, double percent) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * 		当目标缩略图大于原图时是否处理
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, final boolean limit) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, final boolean limit, String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, int l, int s) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, int l, int s, String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, int l, int s, final boolean limit) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, int width, int height, int l, int s, final boolean limit,
			String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * 		当目标缩略图大于原图时是否处理
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, final boolean limit) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, final boolean limit,
			String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
			String padColor) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
			final boolean limit) throws OSSException;

	/**
	 * 图片缩放
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param mode
	 * 		缩放模式
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param l
	 * 		最长边
	 * @param s
	 * 		最短边
	 * @param limit
	 * 		当目标缩略图大于原图时是否处理
	 * @param padColor
	 * 		当缩放模式选择为 ImageResizeMode.PAD（缩放填充）时，可以选择填充的颜色
	 *
	 * @return 缩放结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageResize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
			final boolean limit, String padColor) throws OSSException;

	/**
	 * 图片裁剪
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param x
	 * 		X 坐标起点
	 * @param y
	 * 		Y 坐标起点
	 *
	 * @return 裁剪结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageCrop(String bucketName, String path, int width, int height, int x, int y) throws OSSException;

	/**
	 * 图片旋转自适应方向
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 *
	 * @return 裁剪结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageAutoRotate(String bucketName, String path) throws OSSException;

	/**
	 * 图片旋转 degrees 指定的度数
	 *
	 * @param bucketName
	 * 		Bucket 名字
	 * @param path
	 * 		图片路径
	 * @param degrees
	 * 		旋转度数
	 *
	 * @return 裁剪结果
	 *
	 * @throws OSSException
	 * 		OSS 操作异常
	 */
	File imageRotate(String bucketName, String path, double degrees) throws OSSException;

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
	Status deleteFile(String bucketName, String path) throws OSSException;

	void close();

}

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
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.ProcessObjectRequest;
import com.buession.core.utils.Assert;
import com.buession.oss.core.ImageResizeMode;
import com.buession.oss.exception.OSSException;
import com.buession.oss.model.AliCloudResult;
import com.buession.oss.model.File;
import com.buession.oss.operations.ImageOperations;

import java.io.IOException;
import java.util.Formatter;

/**
 * @author Yong.Teng
 */
public class AliCloudImageOperations extends AbstractAliCloudOperations implements ImageOperations {

	protected final static String PERSISTENT_FORMAT = "%s|sys/saveas,o_%s,b_%s";

	public AliCloudImageOperations(final OSS ossClient){
		super(ossClient);
	}

	@Override
	public File resize(String bucketName, String path, double percent) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style = ObjectBuilder.ObjectResizeBuilder.getInstance().percent(percent).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style = ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, boolean limit) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).limit(limit).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, boolean limit, String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).limit(limit).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, int l, int s) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).l(l).s(s).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, int l, int s, String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).l(l).s(s).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, int l, int s, boolean limit) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).l(l).s(s).limit(limit).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, int width, int height, int l, int s, boolean limit,
					   String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().width(width).height(height).l(l).s(s).limit(limit).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, boolean limit) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).limit(limit).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, boolean limit,
					   String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).limit(limit).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).l(l).s(s).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
					   String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).l(l).s(s).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
					   boolean limit) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).l(l).s(s).limit(limit).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File resize(String bucketName, String path, ImageResizeMode mode, int width, int height, int l, int s,
					   boolean limit, String padColor) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Resize image path cloud not be null or empty.");
		Assert.isNull(mode, "Resize mode cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectResizeBuilder.getInstance().mode(mode).width(width).height(height).l(l).s(s).limit(limit).padColor(padColor).build();
		return doProcess(bucketName, path, style);
	}

	@Override
	public File crop(String bucketName, String path, int width, int height, int x, int y) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Crop image path cloud not be null or empty.");

		final String style =
				ObjectBuilder.ObjectCropBuilder.getInstance().width(width).height(height).x(x).y(y).build();

		return doProcess(bucketName, path, style);
	}

	@Override
	public File autoRotate(String bucketName, String path) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Crop image path cloud not be null or empty.");

		final String style = ObjectBuilder.ObjectRotateBuilder.getInstance().autoOrient(true).build();

		return doProcess(bucketName, path, style);
	}

	@Override
	public File rotate(String bucketName, String path, double degrees) throws OSSException{
		Assert.isBlank(bucketName, "Bucket name cloud not be null or empty.");
		Assert.isBlank(path, "Crop image path cloud not be null or empty.");

		final String style = ObjectBuilder.ObjectRotateBuilder.getInstance().degrees((int) degrees).build();

		return doProcess(bucketName, path, style);
	}

	protected final File doProcess(String bucketName, String path, final String style) throws OSSException{
		final String objectKey = path.startsWith("/") ? path.substring(1) : path;
		final Formatter formatter = buildFormatter(style, PERSISTENT_FORMAT, bucketName, objectKey);
		ProcessObjectRequest request = new ProcessObjectRequest(bucketName, objectKey, formatter.toString());

		try{
			AliCloudResult aliCloudResult = processObject(request, AliCloudResult.class);

			if(aliCloudResult.isSuccessful()){
				File file = buildResult(null, path);

				file.setSize(aliCloudResult.getFileSize());

				return file;
			}
		}catch(IOException e){
			logger.error("Crop images '{}' error.", path, e);
		}finally{
			formatter.close();
		}

		return null;
	}

	protected static Formatter buildFormatter(final String style, final String format, final String bucketName,
											  final String path){
		final Formatter formatter = new Formatter();

		formatter.format(format, style, BinaryUtil.toBase64String(path.getBytes()),
				BinaryUtil.toBase64String(bucketName.getBytes()));

		return formatter;
	}

	private File buildResult(final GenericResult genericResult, final String path){
		File file = new File();

		file.setPath(path);

		return file;
	}

}

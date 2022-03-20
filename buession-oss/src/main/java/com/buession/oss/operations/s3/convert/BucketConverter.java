/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.operations.alicloud.convert;

import com.buession.core.converter.Converter;
import com.buession.oss.core.Acl;
import com.buession.oss.model.Bucket;

/**
 * {@link Bucket} 阿里云 OSS {@link com.aliyun.oss.model.Bucket} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface BucketConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link Acl} 转换为阿里云 OSS {@link com.aliyun.oss.model.Bucket}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class BuessionToAliCloudOSSConverter
			implements BucketConverter<Bucket, com.aliyun.oss.model.Bucket> {

		@Override
		public com.aliyun.oss.model.Bucket convert(final Bucket source){
			return null;
		}

	}

	/**
	 * 阿里云 OSS {@link com.aliyun.oss.model.Bucket} 转换为 {@link Bucket}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class AliCloudOSSToBuessionConverter
			implements BucketConverter<com.aliyun.oss.model.Bucket, Bucket> {

		@Override
		public Bucket convert(final com.aliyun.oss.model.Bucket source){
			if(source != null){
				final Bucket bucket = new Bucket();

				OwnerConverter.AliCloudOSSToBuessionConverter ownerConverter = new OwnerConverter.AliCloudOSSToBuessionConverter();
				bucket.setName(source.getName());
				bucket.setOwner(ownerConverter.convert(source.getOwner()));

				StorageClassConverter.AliCloudOSSToBuessionConverter storageClassConverter = new StorageClassConverter.AliCloudOSSToBuessionConverter();
				bucket.setStorageClass(storageClassConverter.convert(source.getStorageClass()));

				bucket.setCreatedAt(source.getCreationDate());

				return bucket;
			}

			return null;
		}

	}

}

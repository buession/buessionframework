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
import com.buession.oss.core.StorageClass;

/**
 * {@link StorageClass} 转换为阿里云 OSS {@link com.aliyun.oss.model.StorageClass}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StorageClassAliCloudStorageClassConverter
		implements Converter<StorageClass, com.aliyun.oss.model.StorageClass> {

	@Override
	public com.aliyun.oss.model.StorageClass convert(final StorageClass source){
		if(source != null){
			switch(source){
				case STANDARD:
					return com.aliyun.oss.model.StorageClass.Standard;
				case IA:
					return com.aliyun.oss.model.StorageClass.IA;
				case ARCHIVE:
					return com.aliyun.oss.model.StorageClass.Archive;
				case COLD_ARCHIVE:
					return com.aliyun.oss.model.StorageClass.ColdArchive;
				default:
					break;
			}
		}

		return null;
	}

}

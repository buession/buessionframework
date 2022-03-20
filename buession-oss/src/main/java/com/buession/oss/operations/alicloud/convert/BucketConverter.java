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

import com.aliyun.oss.model.CannedAccessControlList;
import com.buession.core.converter.Converter;
import com.buession.oss.core.BucketAcl;

/**
 * {@link BucketAcl} 阿里云 OSS {@link CannedAccessControlList} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface BucketAclConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link BucketAcl} 转换为阿里云 OSS {@link CannedAccessControlList}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class BuessionToAliCloudOSSConverter
			implements BucketAclConverter<BucketAcl, CannedAccessControlList> {

		@Override
		public CannedAccessControlList convert(final BucketAcl source){
			if(source != null){
				switch(source){
					case PUBLIC_READ_WRITE:
						return CannedAccessControlList.PublicReadWrite;
					case PUBLIC_READ:
						return CannedAccessControlList.PublicRead;
					case PRIVATE:
						return CannedAccessControlList.Private;
					default:
						break;
				}
			}

			return null;
		}

	}

	/**
	 * 阿里云 OSS {@link CannedAccessControlList} 转换为 {@link BucketAcl}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class AliCloudOSSToBuessionConverter
			implements BucketAclConverter<CannedAccessControlList, BucketAcl> {

		@Override
		public BucketAcl convert(final CannedAccessControlList source){
			if(source != null){
				switch(source){
					case PublicReadWrite:
						return BucketAcl.PUBLIC_READ_WRITE;
					case PublicRead:
						return BucketAcl.PUBLIC_READ;
					case Private:
						return BucketAcl.PRIVATE;
					case Default:
						return BucketAcl.DEFAULT;
					default:
						break;
				}
			}

			return null;
		}

	}

}

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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.utils.useragentutils;

import com.buession.core.utils.VersionUtils;

/**
 * 版本
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public final class Version implements Comparable<Version> {

	/**
	 * 版本
	 */
	private final String version;

	/**
	 * 主版本号
	 */
	private final String majorVersion;

	/**
	 * 次版本号
	 */
	private final String minorVersion;

	/**
	 * 构造函数
	 *
	 * @param version
	 * 		版本
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 */
	public Version(final String version, final String majorVersion, final String minorVersion){
		this.version = version;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}

	/**
	 * 返回版本
	 *
	 * @return 版本
	 */
	public String getVersion(){
		return version;
	}

	/**
	 * 返回主版本号
	 *
	 * @return 主版本号
	 */
	public String getMajorVersion(){
		return majorVersion;
	}

	/**
	 * 返回次版本号
	 *
	 * @return 次版本号
	 */
	public String getMinorVersion(){
		return minorVersion;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((majorVersion == null) ? 0 : majorVersion.hashCode());
		result = prime * result
				+ ((minorVersion == null) ? 0 : minorVersion.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Version){
			Version that = (Version) obj;

			if(majorVersion == null){
				if(that.majorVersion != null){
					return false;
				}
			}else if(majorVersion.equals(that.majorVersion) == false){
				return false;
			}

			if(minorVersion == null){
				if(that.minorVersion != null){
					return false;
				}
			}else if(minorVersion.equals(that.minorVersion) == false){
				return false;
			}

			if(version == null){
				if(that.version != null){
					return false;
				}
			}else if(version.equals(that.version) == false){
				return false;
			}

			return true;
		}

		return false;
	}

	@Override
	public int compareTo(Version other){
		if(other == null){
			return 1;
		}

		return VersionUtils.compare(version, other.version);
	}

	@Override
	public String toString(){
		return version;
	}

}

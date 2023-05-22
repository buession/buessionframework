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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.lang.Version;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * 版本工具类
 *
 * @author Yong.Teng
 */
public class VersionUtils {

	/**
	 * 版本号比较
	 *
	 * @param version1
	 * 		第一个版本号
	 * @param version2
	 * 		第二个版本号
	 *
	 * @return 当 version1 &lt; version2 时，返回 -1；当 version1 = version2 时，返回 0；当 version1 &gt; version2 时返回 1
	 */
	public static int compare(final String version1, final String version2){
		final Version ver1 = new Version(version1);
		final Version ver2 = new Version(version2);

		return ver1.compareTo(ver2);
	}

	public static String determineClassVersion(final Class<?> clazz){
		String implementationVersion = clazz.getPackage().getImplementationVersion();
		if(implementationVersion != null){
			return implementationVersion;
		}

		CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
		if(codeSource == null){
			return null;
		}

		URL codeSourceLocation = codeSource.getLocation();
		try{
			URLConnection connection = codeSourceLocation.openConnection();

			if(connection instanceof JarURLConnection){
				return getJarFileImplementationVersion(((JarURLConnection) connection).getJarFile());
			}

			JarFile jarFile = new JarFile(new File(codeSourceLocation.toURI()));
			return getJarFileImplementationVersion(jarFile);
		}catch(Exception ex){
			return null;
		}
	}

	public static String getJarFileImplementationVersion(final JarFile jarFile) throws IOException{
		return jarFile.getManifest().getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
	}

}
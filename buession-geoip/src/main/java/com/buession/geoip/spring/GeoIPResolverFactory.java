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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.spring;

import com.buession.core.utils.Assert;
import com.buession.geoip.Resolver;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * GeoIP {@link Resolver} 工厂
 *
 * @author Yong.Teng
 */
public class GeoIPResolverFactory {

	/**
	 * IP 库文件路径
	 */
	private File dbPath;

	/**
	 * IP 库文件流
	 */
	private InputStream stream;

	/**
	 * IP 库加载模式
	 */
	private LoadMode loadMode = LoadMode.STREAM;

	/**
	 * 是否开启缓存
	 */
	private boolean enableCache = true;

	/**
	 * 返回 IP 库文件路径
	 *
	 * @return IP 库文件路径
	 */
	public File getDbPath(){
		return dbPath;
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件 {@link Resource}
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public void setDbPath(Resource dbPath) throws IOException{
		Assert.isNull(dbPath, "Ip database path cloud not be null.");

		this.dbPath = dbPath.getFile();
		this.loadMode = LoadMode.FILE;
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public void setDbPath(File dbPath) throws IOException{
		Assert.isNull(dbPath, "Ip database path cloud not be null.");

		this.dbPath = dbPath;
		this.loadMode = LoadMode.FILE;
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public void setDbPath(Path dbPath) throws IOException{
		Assert.isNull(dbPath, "Ip database path cloud not be null.");

		this.dbPath = dbPath.toFile();
		this.loadMode = LoadMode.FILE;
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public void setDbPath(String dbPath) throws IOException{
		Assert.isBlank(dbPath, "Ip database path cloud not be null or empty.");

		setDbPath(new File(dbPath));
	}

	/**
	 * 返回 IP 库文件流
	 *
	 * @return IP 库文件流
	 */
	public InputStream getStream(){
		if(stream == null){
			stream = GeoIPResolverFactory.class.getResourceAsStream("/maxmind/City.mmdb");
		}

		return stream;
	}

	/**
	 * 设置 IP 库文件流
	 *
	 * @param stream
	 * 		IP 库文件流
	 */
	public void setStream(InputStream stream){
		Assert.isNull(stream, "Ip database stream cloud not be null.");

		this.stream = stream;
		this.loadMode = LoadMode.STREAM;
	}

	/**
	 * 返回 IP 库加载模式
	 *
	 * @return IP 库加载模式
	 */
	public LoadMode getLoadMode(){
		return loadMode;
	}

	/**
	 * 返回是否开启缓存
	 *
	 * @return 是否开启缓存
	 */
	public boolean isEnableCache(){
		return enableCache;
	}

	/**
	 * 设置是否开启缓存
	 *
	 * @param enableCache
	 * 		是否开启缓存
	 */
	public void setEnableCache(boolean enableCache){
		this.enableCache = enableCache;
	}

	/**
	 * 加载模式
	 */
	protected enum LoadMode {

		/**
		 * 流模式
		 */
		STREAM,

		/**
		 * 文件模式
		 */
		FILE

	}

}

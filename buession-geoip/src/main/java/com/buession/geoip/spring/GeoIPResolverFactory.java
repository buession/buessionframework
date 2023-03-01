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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 											   |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.spring;

import com.buession.core.utils.Assert;
import com.buession.geoip.DatabaseResolver;
import com.buession.geoip.Resolver;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
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
	 * ASN 库文件路径
	 *
	 * @since 2.2.0
	 */
	private File asnDbPath;

	/**
	 * ASN 库文件流
	 *
	 * @since 2.2.0
	 */
	private InputStream asnStream;

	/**
	 * IP 库加载模式
	 */
	@Deprecated
	private LoadMode loadMode = LoadMode.STREAM;

	/**
	 * 是否开启缓存
	 */
	private boolean enableCache = true;

	/**
	 * 返回 IP 库文件路径
	 *
	 * @return IP 库文件路径
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 */
	public File getDbPath() throws IOException{
		if(dbPath == null){
			dbPath = new File(DatabaseResolver.class.getResource(DatabaseResolver.DEFAULT_CITY_DB).getFile());
		}

		return dbPath;
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件 {@link Resource}
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 */
	public void setDbPath(Resource dbPath) throws IOException{
		Assert.isNull(dbPath, "Ip database path cloud not be null.");
		this.dbPath = dbPath.getFile();
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 */
	public void setDbPath(File dbPath) throws IOException{
		Assert.isNull(dbPath, "Ip database path cloud not be null.");
		this.dbPath = dbPath;
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 */
	public void setDbPath(Path dbPath) throws IOException{
		Assert.isNull(dbPath, "Ip database path cloud not be null.");
		this.dbPath = dbPath.toFile();
	}

	/**
	 * 设置 IP 库文件路径
	 *
	 * @param dbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 */
	public void setDbPath(String dbPath) throws IOException{
		Assert.isBlank(dbPath, "Ip database path cloud not be null or empty.");
		setDbPath(new File(dbPath));
	}

	/**
	 * 返回 ASN 库文件路径
	 *
	 * @return ASN 库文件路径
	 *
	 * @throws IOException
	 * 		ASN 库文件不存在时
	 * @since 2.2.0
	 */
	public File getAsnDbPath() throws IOException{
		if(asnDbPath == null){
			asnDbPath = new File(DatabaseResolver.class.getResource(DatabaseResolver.DEFAULT_ASN_DB).getFile());
		}

		return asnDbPath;
	}

	/**
	 * 设置 ASN 库文件路径
	 *
	 * @param asnDbPath
	 * 		ASN 库文件 {@link Resource}
	 *
	 * @throws IOException
	 * 		ASN 库文件不存在时
	 * @since 2.2.0
	 */
	public void setAsnDbPath(Resource asnDbPath) throws IOException{
		Assert.isNull(asnDbPath, "Ip asn database path cloud not be null.");
		this.asnDbPath = asnDbPath.getFile();
	}

	/**
	 * 设置 ASN 库文件路径
	 *
	 * @param asnDbPath
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		ASN 库文件不存在时
	 * @since 2.2.0
	 */
	public void setAsnDbPath(File asnDbPath) throws IOException{
		Assert.isNull(asnDbPath, "Ip asn database path cloud not be null.");
		this.asnDbPath = asnDbPath;
	}

	/**
	 * 设置 ASN 库文件路径
	 *
	 * @param asnDbPath
	 * 		ASN 库文件路径
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 * @since 2.2.0
	 */
	public void setAsnDbPath(Path asnDbPath) throws IOException{
		Assert.isNull(asnDbPath, "Ip asn database path cloud not be null.");
		this.asnDbPath = asnDbPath.toFile();
	}

	/**
	 * 设置 ASN 库文件路径
	 *
	 * @param asnDbPath
	 * 		ASN 库文件路径
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 * @since 2.2.0
	 */
	public void setAsnDbPath(String asnDbPath) throws IOException{
		Assert.isBlank(asnDbPath, "Ip asn database path cloud not be null or empty.");
		setAsnDbPath(new File(asnDbPath));
	}

	/**
	 * 返回 IP 库文件流
	 *
	 * @return IP 库文件流
	 *
	 * @throws IOException
	 * 		数据库文件不存在时
	 */
	public InputStream getStream() throws IOException{
		if(stream == null){
			stream = DatabaseResolver.class.getResourceAsStream(DatabaseResolver.DEFAULT_CITY_DB);
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
	}

	/**
	 * 返回 ASN 库文件流
	 *
	 * @return ASN 库文件流
	 *
	 * @throws IOException
	 * 		ASN 库文件不存在时
	 * @since 2.2.0
	 */
	public InputStream getAsnStream() throws IOException{
		if(asnStream == null){
			asnStream = DatabaseResolver.class.getResourceAsStream(DatabaseResolver.DEFAULT_ASN_DB);
		}

		return asnStream;
	}

	/**
	 * 设置 asnStream 库文件流
	 *
	 * @param asnStream
	 * 		asnStream 库文件流
	 *
	 * @since 2.2.0
	 */
	public void setAsnStream(InputStream asnStream){
		Assert.isNull(asnStream, "Ip asn database stream cloud not be null.");
		this.asnStream = asnStream;
	}

	/**
	 * 返回 IP 库加载模式
	 *
	 * @return IP 库加载模式
	 */
	@Deprecated
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

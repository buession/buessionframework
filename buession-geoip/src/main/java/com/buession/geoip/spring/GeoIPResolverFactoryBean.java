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
package com.buession.geoip.spring;

import com.buession.geoip.CacheDatabaseResolver;
import com.buession.geoip.DatabaseResolver;
import com.buession.geoip.Resolver;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;

/**
 * GeoIP {@link Resolver} 工厂 Bean
 *
 * @author Yong.Teng
 */
public class GeoIPResolverFactoryBean extends GeoIPResolverFactory implements FactoryBean<DatabaseResolver>,
		InitializingBean, Closeable {

	private DatabaseResolver resolver;

	@Override
	public DatabaseResolver getObject() throws Exception {
		return resolver;
	}

	@Override
	public Class<? extends DatabaseResolver> getObjectType() {
		return DatabaseResolver.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(resolver == null){
			resolver = isEnableCache() ? new CacheDatabaseResolver(getStream(),
					getAsnStream()) : new DatabaseResolver(getStream(), getAsnStream());
		}
	}

	@Override
	public void close() throws IOException {
		resolver.close();
	}

}

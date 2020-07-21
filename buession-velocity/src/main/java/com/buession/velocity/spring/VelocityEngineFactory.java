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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.spring;

import com.buession.core.utils.StringUtils;
import com.buession.velocity.SpringResourceLoader;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Yong.Teng
 */
public class VelocityEngineFactory {

	private Resource configLocation;

	private final Map<String, Object> velocityProperties = new HashMap<>();

	private String resourceLoaderPath;

	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	private boolean preferFileSystemAccess = true;

	private final static Logger logger = LoggerFactory.getLogger(VelocityEngineFactory.class);

	public Resource getConfigLocation(){
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation){
		this.configLocation = configLocation;
	}

	public Map<String, Object> getVelocityProperties(){
		return velocityProperties;
	}

	public void setVelocityProperties(Properties velocityProperties){
		CollectionUtils.mergePropertiesIntoMap(velocityProperties, this.velocityProperties);
	}

	public void setVelocityPropertiesMap(Map<String, Object> velocityPropertiesMap){
		if(velocityPropertiesMap != null){
			this.velocityProperties.putAll(velocityPropertiesMap);
		}
	}

	public String getResourceLoaderPath(){
		return resourceLoaderPath;
	}

	public void setResourceLoaderPath(String resourceLoaderPath){
		this.resourceLoaderPath = resourceLoaderPath;
	}

	public ResourceLoader getResourceLoader(){
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader){
		this.resourceLoader = resourceLoader;
	}

	public boolean isPreferFileSystemAccess(){
		return getPreferFileSystemAccess();
	}

	public boolean getPreferFileSystemAccess(){
		return preferFileSystemAccess;
	}

	public void setPreferFileSystemAccess(boolean preferFileSystemAccess){
		this.preferFileSystemAccess = preferFileSystemAccess;
	}

	public VelocityEngine createVelocityEngine() throws IOException, VelocityException{
		VelocityEngine velocityEngine = new VelocityEngine();
		Map<String, Object> properties = new HashMap<>(velocityProperties.size());

		if(configLocation != null){
			logger.info("Loading Velocity config from [{}]", configLocation);
			CollectionUtils.mergePropertiesIntoMap(PropertiesLoaderUtils.loadProperties(configLocation), properties);
		}

		if(velocityProperties.isEmpty() == false){
			properties.putAll(velocityProperties);
		}

		if(resourceLoaderPath != null){
			initVelocityResourceLoader(velocityEngine, resourceLoaderPath);
		}

		properties.forEach((name, value)->{
			velocityEngine.setProperty(name, value);
		});

		postProcessVelocityEngine(velocityEngine);

		// Perform actual initialization.
		velocityEngine.init();

		return velocityEngine;
	}

	protected void initVelocityResourceLoader(VelocityEngine velocityEngine, String resourceLoaderPath){
		if(isPreferFileSystemAccess() == false){
			logger.debug("File system access not preferred: using SpringResourceLoader");
			initSpringResourceLoader(velocityEngine, resourceLoaderPath);
			return;
		}

		try{
			StringBuilder resolvedPath = new StringBuilder();
			String[] paths = StringUtils.split(resourceLoaderPath, ',');

			for(int i = 0; i < paths.length; i++){
				String path = paths[i];
				Resource resource = getResourceLoader().getResource(path);
				File file = resource.getFile();

				logger.debug("Resource loader path [{}] resolved to file [{}]", path, file.getAbsolutePath());

				resolvedPath.append(file.getAbsolutePath());
				if(i < paths.length - 1){
					resolvedPath.append(',');
				}
			}

			velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
			velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
			velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, resolvedPath.toString());
		}catch(IOException ex){
			logger.debug("Cannot resolve resource loader path [{}] to [java.io.File]: using " + "SpringResourceLoader"
					, resourceLoaderPath, ex);
			initSpringResourceLoader(velocityEngine, resourceLoaderPath);
		}
	}

	protected void initSpringResourceLoader(VelocityEngine velocityEngine, String resourceLoaderPath){
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, SpringResourceLoader.NAME);
		velocityEngine.setProperty(SpringResourceLoader.SPRING_RESOURCE_LOADER_CLASS,
				SpringResourceLoader.class.getName());
		velocityEngine.setProperty(SpringResourceLoader.SPRING_RESOURCE_LOADER_CACHE, "true");
		velocityEngine.setApplicationAttribute(SpringResourceLoader.SPRING_RESOURCE_LOADER, getResourceLoader());
		velocityEngine.setApplicationAttribute(SpringResourceLoader.SPRING_RESOURCE_LOADER_PATH, resourceLoaderPath);
	}

	protected void postProcessVelocityEngine(VelocityEngine velocityEngine) throws IOException, VelocityException{
	}

}

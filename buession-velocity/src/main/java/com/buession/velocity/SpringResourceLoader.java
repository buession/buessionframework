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
package com.buession.velocity;

import com.buession.core.exception.PresentException;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.ExtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

/**
 * @author Yong.Teng
 */
public class SpringResourceLoader extends ResourceLoader {

	public final static String NAME = "spring";

	public final static String LOADER_NAME = "SpringResourceLoader";

	public final static String SPRING_RESOURCE_LOADER_CLASS = "spring.resource.loader.class";

	public final static String SPRING_RESOURCE_LOADER_CACHE = "spring.resource.loader.cache";

	public final static String SPRING_RESOURCE_LOADER = "spring.resource.loader";

	public final static String SPRING_RESOURCE_LOADER_PATH = "spring.resource.loader.path";

	private org.springframework.core.io.ResourceLoader resourceLoader;

	private String[] resourceLoaderPaths;

	private final static Logger logger = LoggerFactory.getLogger(SpringResourceLoader.class);

	@Override
	public void init(final ExtProperties configuration){
		resourceLoader =
				(org.springframework.core.io.ResourceLoader) rsvc.getApplicationAttribute(SPRING_RESOURCE_LOADER);
		String resourceLoaderPath = (String) rsvc.getApplicationAttribute(SPRING_RESOURCE_LOADER_PATH);

		if(resourceLoader == null){
			throw new PresentException("'resourceLoader' application attribute", LOADER_NAME);
		}

		if(Validate.hasText(resourceLoaderPath) == false){
			throw new PresentException("'resourceLoaderPath' application attribute", LOADER_NAME);
		}

		resourceLoaderPaths = StringUtils.split(resourceLoaderPath);

		for(int i = 0; i < resourceLoaderPaths.length; i++){
			String path = resourceLoaderPaths[i];
			if(StringUtils.endsWith(path, '/') == false){
				resourceLoaderPaths[i] = path + "/";
			}
		}

		if(logger.isInfoEnabled()){
			logger.info(LOADER_NAME + " for Velocity: using resource loader [{}] and resource " + "loader paths {}",
					resourceLoader, Arrays.asList(resourceLoaderPaths));
		}
	}

	@Override
	public Reader getResourceReader(String source, String encoding) throws ResourceNotFoundException{
		logger.debug("Looking for Velocity resource with name [{}]", source);

		org.springframework.core.io.Resource resource;
		for(String resourceLoaderPath : resourceLoaderPaths){
			resource = resourceLoader.getResource(resourceLoaderPath + source);

			try{
				return encoding == null ? new InputStreamReader(resource.getInputStream()) :
						new InputStreamReader(resource.getInputStream(), encoding);
			}catch(IOException ex){
				logger.debug("Could not find Velocity resource: {}", resource);
			}
		}

		throw new ResourceNotFoundException("Could not find resource [" + source + "] in Spring resource loader path");
	}

	@Override
	public boolean isSourceModified(Resource resource){
		return false;
	}

	@Override
	public long getLastModified(Resource resource){
		return 0;
	}

}

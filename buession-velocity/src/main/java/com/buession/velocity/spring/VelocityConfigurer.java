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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.spring;

import com.buession.velocity.VelocityConfig;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class VelocityConfigurer extends VelocityEngineFactory implements VelocityConfig, InitializingBean,
        ResourceLoaderAware, ServletContextAware {

    /**
     * the name of the resource loader for Spring's bind macros
     */
    private final static String SPRING_MACRO_RESOURCE_LOADER_NAME = "springMacro";

    /**
     * the key for the class of Spring's bind macro resource loader
     */
    private final static String SPRING_MACRO_RESOURCE_LOADER_CLASS = "springMacro.resource.loader.class";

    /**
     * the name of Spring's default bind macro library
     */
    private final static String SPRING_MACRO_LIBRARY = "com/buession/velocity/spring/view/spring.vm";

    private VelocityEngine velocityEngine;

    private ServletContext servletContext;

    private final static Logger logger = LoggerFactory.getLogger(VelocityConfigurer.class);

    @Override
    public VelocityEngine getVelocityEngine(){
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine){
        this.velocityEngine = velocityEngine;
    }

    public ServletContext getServletContext(){
        return servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
    }

    @Override
    public void afterPropertiesSet() throws IOException, VelocityException{
        if(velocityEngine == null){
            velocityEngine = createVelocityEngine();
        }
    }

    @Override
    protected void postProcessVelocityEngine(VelocityEngine velocityEngine){
        velocityEngine.setApplicationAttribute(ServletContext.class.getName(), this.servletContext);
        velocityEngine.setProperty(SPRING_MACRO_RESOURCE_LOADER_CLASS, ClasspathResourceLoader.class.getName());
        velocityEngine.addProperty(VelocityEngine.RESOURCE_LOADER, SPRING_MACRO_RESOURCE_LOADER_NAME);
        velocityEngine.addProperty(VelocityEngine.VM_LIBRARY, SPRING_MACRO_LIBRARY);

        logger.info("ClasspathResourceLoader with name '{}' added to configured VelocityEngine",
                SPRING_MACRO_RESOURCE_LOADER_NAME);
    }

}

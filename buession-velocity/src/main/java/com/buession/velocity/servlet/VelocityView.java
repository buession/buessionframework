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
package com.buession.velocity.servlet;

import com.buession.velocity.VelocityConfig;
import com.buession.velocity.tools.LocaleAwareDateTool;
import com.buession.velocity.tools.LocaleAwareNumberTool;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.NestedIOException;
import org.springframework.web.servlet.view.AbstractTemplateView;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class VelocityView extends AbstractTemplateView {

    private Map<String, Class<?>> toolAttributes;

    private String dateToolAttribute;

    private String numberToolAttribute;

    private String encoding;

    private boolean cacheTemplate = false;

    private VelocityEngine velocityEngine;

    private Template template;

    private final static Logger logger = LoggerFactory.getLogger(VelocityView.class);

    public Map<String, Class<?>> getToolAttributes(){
        return toolAttributes;
    }

    public void setToolAttributes(Map<String, Class<?>> toolAttributes){
        this.toolAttributes = toolAttributes;
    }

    public String getDateToolAttribute(){
        return dateToolAttribute;
    }

    public void setDateToolAttribute(String dateToolAttribute){
        this.dateToolAttribute = dateToolAttribute;
    }

    public String getNumberToolAttribute(){
        return numberToolAttribute;
    }

    public void setNumberToolAttribute(String numberToolAttribute){
        this.numberToolAttribute = numberToolAttribute;
    }

    public String getEncoding(){
        return encoding;
    }

    public void setEncoding(String encoding){
        this.encoding = encoding;
    }

    public void setCharset(Charset charset){
        this.encoding = charset.name();
    }

    public boolean isCacheTemplate(){
        return getCacheTemplate();
    }

    public boolean getCacheTemplate(){
        return cacheTemplate;
    }

    public void setCacheTemplate(boolean cacheTemplate){
        this.cacheTemplate = cacheTemplate;
    }

    public VelocityEngine getVelocityEngine(){
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine){
        this.velocityEngine = velocityEngine;
    }

    @Override
    protected void initApplicationContext() throws BeansException{
        super.initApplicationContext();

        if(getVelocityEngine() == null){
            setVelocityEngine(autodetectVelocityEngine());
        }
    }

    protected VelocityEngine autodetectVelocityEngine() throws BeansException{
        try{
            VelocityConfig velocityConfig = BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(),
                    VelocityConfig.class, true, false);
            return velocityConfig.getVelocityEngine();
        }catch(NoSuchBeanDefinitionException ex){
            throw new ApplicationContextException("Must define a single VelocityConfig bean in this web application "
                    + "context (may be inherited): VelocityConfigurer is the usual implementation. " + "This bean " +
                    "may" + " be given any name.", ex);
        }
    }

    @Override
    public boolean checkResource(Locale locale) throws Exception{
        try{
            template = getTemplate(getUrl());
            return true;
        }catch(ResourceNotFoundException ex){
            logger.debug("No Velocity view found for URL: {}", getUrl());
            return false;
        }catch(Exception ex){
            throw new NestedIOException("Could not load Velocity template for URL [" + getUrl() + "]", ex);
        }
    }

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception{
        exposeHelpers(model, request);

        Context velocityContext = createVelocityContext(model, request, response);
        exposeHelpers(velocityContext, request, response);
        exposeToolAttributes(velocityContext, request);

        doRender(velocityContext, response);
    }

    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception{
    }

    protected void exposeHelpers(Context velocityContext, HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        exposeHelpers(velocityContext, request);
    }

    protected void exposeHelpers(Context velocityContext, HttpServletRequest request) throws Exception{
    }

    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception{
        return createVelocityContext(model);
    }

    protected Context createVelocityContext(Map<String, Object> model) throws Exception{
        return new VelocityContext(model);
    }

    protected void exposeToolAttributes(Context velocityContext, HttpServletRequest request) throws Exception{
        // Expose generic attributes.
        if(toolAttributes != null){
            for(Map.Entry<String, Class<?>> entry : toolAttributes.entrySet()){
                String attributeName = entry.getKey();
                Class<?> toolClass = entry.getValue();

                try{
                    Object tool = toolClass.newInstance();
                    initTool(tool, velocityContext);
                    velocityContext.put(attributeName, tool);
                }catch(Exception ex){
                    throw new NestedServletException("Could not instantiate Velocity tool '" + attributeName + "'", ex);
                }
            }
        }

        // Expose locale-aware DateTool/NumberTool attributes.
        if(dateToolAttribute != null){
            velocityContext.put(dateToolAttribute, new LocaleAwareDateTool(request));
        }
        if(this.numberToolAttribute != null){
            velocityContext.put(numberToolAttribute, new LocaleAwareNumberTool(request));
        }
    }

    protected void initTool(Object tool, Context velocityContext) throws Exception{
    }

    protected void doRender(Context context, HttpServletResponse response) throws Exception{
        logger.debug("Rendering Velocity template [{}] in VelocityView '{}'", getUrl(), getBeanName());
        mergeTemplate(getTemplate(), context, response);
    }

    protected Template getTemplate() throws Exception{
        return isCacheTemplate() && template != null ? template : getTemplate(getUrl());
    }

    protected Template getTemplate(String name) throws Exception{
        return (getEncoding() != null ? getVelocityEngine().getTemplate(name, getEncoding()) : getVelocityEngine()
                .getTemplate(name));
    }

    protected void mergeTemplate(Template template, Context context, HttpServletResponse response) throws Exception{
        try{
            template.merge(context, response.getWriter());
        }catch(MethodInvocationException ex){
            Throwable cause = ex.getCause();
            String message = String.format("Method invocation failed during rendering of Velocity view with name " +
                    "'{}': {}; reference [{}], method: '{}'", getBeanName(), ex.getMessage(), ex.getReferenceName(),
                    ex.getMethodName());

            throw new NestedServletException(message, cause == null ? ex : cause);
        }
    }

}

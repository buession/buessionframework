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

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.view.ViewToolContext;
import org.apache.velocity.tools.view.ViewToolManager;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class VelocityToolboxView extends VelocityView {

    private String toolboxConfigLocation;

    protected String getToolboxConfigLocation(){
        return this.toolboxConfigLocation;
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation){
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception{
        ViewToolContext velocityContext = new ViewToolContext(getVelocityEngine(), request, response,
                getServletContext());

        velocityContext.putAll(model);

        if(getToolboxConfigLocation() != null){
            ToolManager toolManager = new ViewToolManager(getServletContext(), false, true);

            toolManager.setVelocityEngine(getVelocityEngine());
            toolManager.configure(getToolboxConfigLocation());

            ToolboxFactory toolboxFactory = toolManager.getToolboxFactory();

            if(toolboxFactory.hasTools(Scope.APPLICATION)){
                velocityContext.addToolbox(toolboxFactory.createToolbox(Scope.APPLICATION));
            }
            if(toolboxFactory.hasTools(Scope.SESSION)){
                velocityContext.addToolbox(toolboxFactory.createToolbox(Scope.SESSION));
            }
            if(toolboxFactory.hasTools(Scope.REQUEST)){
                velocityContext.addToolbox(toolboxFactory.createToolbox(Scope.REQUEST));
            }
        }

        return velocityContext;
    }

    @Override
    protected void initTool(Object tool, Context velocityContext) throws Exception{
        Method initMethod = ClassUtils.getMethodIfAvailable(tool.getClass(), "init", Object.class);
        if(initMethod != null){
            ReflectionUtils.invokeMethod(initMethod, tool, velocityContext);
        }
    }

}
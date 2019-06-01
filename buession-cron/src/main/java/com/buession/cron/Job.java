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
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.cron;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 计划任务作业实体
 *
 * @author Yong.Teng
 */
public final class Job implements Serializable {

    private static final long serialVersionUID = -3006447320967793168L;

    /**
     * 计划任务作业名称
     */
    @NotNull(message = "job name could not be null")
    private String name;

    /**
     * 计划任务作业分组
     */
    @NotNull(message = "job group could not be null")
    private String group;

    /**
     * 计划任务作业类
     */
    @NotNull(message = "job class could not be null")
    private Class<? extends org.quartz.Job> clazz;

    /**
     * 计划任务作业执行表达式
     */
    @NotNull(message = "job excute expression could not be null")
    private String expression;

    /**
     * 返回计划任务作业名称
     *
     * @return 计划任务作业名称
     */
    public String getName(){
        return name;
    }

    /**
     * 设置计划任务作业名称
     *
     * @param name
     *         计划任务作业名称
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 返回计划任务作业分组
     *
     * @return 计划任务作业分组
     */
    public String getGroup(){
        return group;
    }

    /**
     * 设置计划任务作业分组
     *
     * @param group
     *         计划任务作业分组
     */
    public void setGroup(String group){
        this.group = group;
    }

    /**
     * 返回计划任务作业类
     *
     * @return 计划任务作业类
     */
    public Class<? extends org.quartz.Job> getClazz(){
        return clazz;
    }

    /**
     * 设置计划任务作业类
     *
     * @param clazz
     *         计划任务作业类
     */
    public void setClazz(Class<? extends org.quartz.Job> clazz){
        this.clazz = clazz;
    }

    /**
     * 返回计划任务作业执行表达式
     *
     * @return 计划任务作业执行表达式
     */
    public String getExpression(){
        return expression;
    }

    /**
     * 设置计划任务作业执行表达式
     *
     * @param expression
     *         计划任务作业执行表达式
     */
    public void setExpression(String expression){
        this.expression = expression;
    }

}
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
package com.buession.aop.aspectj;

import com.buession.aop.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.AdviceSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public abstract class AbstractAdviceMethodInvocationAdapter implements MethodInvocation {

    private Object object;

    private Method method;

    private Object[] arguments;

    public AbstractAdviceMethodInvocationAdapter(Object object, Method method, Object[] arguments){
        this.object = object;
        this.method = method;
        this.arguments = arguments;
    }

    public static BeforeAdviceMethodInvocationAdapter createFromJoinPoint(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();

        if(signature instanceof MethodSignature){
            return new BeforeAdviceMethodInvocationAdapter(joinPoint.getThis(), ((MethodSignature) signature)
                    .getMethod(), joinPoint.getArgs());
        }else if(signature instanceof AdviceSignature){
            return new BeforeAdviceMethodInvocationAdapter(joinPoint.getThis(), ((AdviceSignature) signature)
                    .getAdvice(), joinPoint.getArgs());
        }else{
            throw new IllegalArgumentException("The join point signature is invalid: expected a MethodSignature or "
                    + "an AdviceSignature but was " + signature);
        }
    }

    @Override
    public Object proceed() throws Throwable{
        return null;
    }

    @Override
    public Object getThis(){
        return object;
    }

    @Override
    public Method getMethod(){
        return method;
    }

    @Override
    public Object[] getArguments(){
        return arguments;
    }

}

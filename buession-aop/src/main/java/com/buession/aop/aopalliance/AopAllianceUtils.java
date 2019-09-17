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
package com.buession.aop.aopalliance;

import com.buession.aop.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public class AopAllianceUtils {

    private AopAllianceUtils(){

    }

    public final static MethodInvocation createMethodInvocation(org.aopalliance.intercept.MethodInvocation
                                                                        implSpecificMethodInvocation){
        return new MethodInvocation() {

            @Override
            public Object proceed() throws Throwable{
                return implSpecificMethodInvocation.proceed();
            }

            @Override
            public Object getThis(){
                return implSpecificMethodInvocation.getThis();
            }

            @Override
            public Method getMethod(){
                return implSpecificMethodInvocation.getMethod();
            }

            @Override
            public Object[] getArguments(){
                return implSpecificMethodInvocation.getArguments();
            }

            @Override
            public String toString(){
                StringBuffer sb = new StringBuffer();

                sb.append("Method invocation [");
                sb.append(implSpecificMethodInvocation.getThis().getClass().getName());
                sb.append("::");
                sb.append(implSpecificMethodInvocation.getMethod());
                sb.append("()]");

                return sb.toString();
            }

        };

    }

}

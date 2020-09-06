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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.exception;

import java.lang.reflect.Method;

/**
 * 类方法废弃后，需要使用其它类库方法来替代
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class InsteadException extends RuntimeException {

	private final static long serialVersionUID = -537962037756547721L;

	private Method method;

	private String groupId;

	private String artifactId;

	private String version;

	private String className;

	private String methodName;

	public InsteadException(Method method, String groupId, String artifactId, String version, String className,
			String methodName){
		super(method + " is deprecated, instead of " + className + "." + methodName + "() with dependency: " + groupId + ":" + artifactId + ":" + version + ".");
		this.method = method;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.className = className;
		this.methodName = methodName;
	}

	public Method getMethod(){
		return method;
	}

	public String getGroupId(){
		return groupId;
	}

	public String getArtifactId(){
		return artifactId;
	}

	public String getVersion(){
		return version;
	}

	public String getClassName(){
		return className;
	}

	public String getMethodName(){
		return methodName;
	}

}

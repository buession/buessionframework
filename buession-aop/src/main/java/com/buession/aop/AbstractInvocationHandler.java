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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.aop;

import com.buession.aop.utils.ProxyUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * {@link InvocationHandler} 抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractInvocationHandler implements InvocationHandler {

	private final static int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
			| MethodHandles.Lookup.PUBLIC | MethodHandles.Lookup.PACKAGE;

	protected final static Constructor<MethodHandles.Lookup> lookupConstructor;

	protected final static Method privateLookupInMethod;

	static {
		Method privateLookupIn;
		try{
			privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
		}catch(NoSuchMethodException e){
			privateLookupIn = null;
		}
		privateLookupInMethod = privateLookupIn;

		Constructor<MethodHandles.Lookup> lookup = null;
		if(privateLookupInMethod == null){
			// JDK 1.8
			try{
				lookup = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
				lookup.setAccessible(true);
			}catch(NoSuchMethodException e){
				throw new IllegalStateException(
						"There is neither 'privateLookupIn(Class, Lookup)' nor 'Lookup(Class, int)' method in java.lang.invoke.MethodHandles.",
						e);
			}catch(Exception e){
				lookup = null;
			}
		}
		lookupConstructor = lookup;
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(args == null){
			args = ProxyMethodInvoker.NO_ARGS;
		}

		if(args.length == 0){
			if("hashCode".equals(method.getName())){
				return doHashCode();
			}else if("toString".equals(method.getName())){
				return doToString();
			}
		}

		if(args.length == 1 && "equals".equals(method.getName()) && method.getParameterTypes()[0] == Object.class){
			Object arg = args[0];
			return doEquals(proxy, arg);
		}

		return doInvoke(proxy, method, args);
	}

	protected Object doHashCode() {
		return hashCode();
	}

	protected Object doEquals(final Object proxy, final Object arg) {
		if(arg == null){
			return false;
		}

		if(proxy == arg){
			return true;
		}

		return ProxyUtils.isProxyOfSameInterfaces(arg, proxy.getClass()) && equals(Proxy.getInvocationHandler(arg));
	}

	protected Object doToString() {
		return toString();
	}

	protected Object doInvoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		if(Object.class.equals(method.getDeclaringClass())){
			return method.invoke(this, args);
		}

		return handleInvocation(proxy, method, args);
	}

	protected abstract Object handleInvocation(final Object proxy, final Method method, final Object[] args)
			throws Throwable;

	protected int getAllowedModes() {
		return ALLOWED_MODES;
	}

	protected <T> ProxyMethodInvoker<T> createMethodInvoker(final Method method)
			throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
		if(method.isDefault()){
			final MethodHandle methodHandle = privateLookupInMethod == null ? getMethodHandleJava8(method) :
					getMethodHandleJava9(method);
			return new DefaultMethodInvoker<>(methodHandle);
		}else{
			return new PlainMethodInvoker<>();
		}
	}

	protected MethodHandle getMethodHandleJava8(final Method method)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
		final Class<?> declaringClass = method.getDeclaringClass();
		return lookupConstructor.newInstance(declaringClass, getAllowedModes())
				.unreflectSpecial(method, declaringClass);
	}

	protected MethodHandle getMethodHandleJava9(final Method method)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		final Class<?> declaringClass = method.getDeclaringClass();
		final MethodType methodType = MethodType.methodType(method.getReturnType(), method.getParameterTypes());

		return ((MethodHandles.Lookup) privateLookupInMethod.invoke(null, declaringClass, MethodHandles.lookup()))
				.findSpecial(declaringClass, method.getName(), methodType, declaringClass);
	}

}

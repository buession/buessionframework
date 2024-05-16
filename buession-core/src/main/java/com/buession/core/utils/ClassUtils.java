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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.core.exception.ClassInstantiationException;
import com.buession.lang.Primitive;
import org.springframework.core.KotlinDetector;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;

/**
 * 类工具类
 *
 * @author Yong.Teng
 * @see org.apache.commons.lang3.ClassUtils
 * @since 1.2.0
 */
public class ClassUtils extends org.apache.commons.lang3.ClassUtils {

	/**
	 * 获取默认类加载器
	 *
	 * @return 默认类加载器
	 *
	 * @since 3.0.0
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader classLoader = null;
		try{
			classLoader = Thread.currentThread().getContextClassLoader();
		}catch(Throwable ex){
			// Cannot access thread context ClassLoader - falling back...
		}

		if(classLoader == null){
			// No thread context class loader -> use class loader of this class.
			classLoader = ClassUtils.class.getClassLoader();
			if(classLoader == null){
				// getClassLoader() returning null indicates the bootstrap ClassLoader
				try{
					classLoader = ClassLoader.getSystemClassLoader();
				}catch(Throwable ex){
					// Cannot access system ClassLoader - oh well, maybe the caller can live with null...
				}
			}
		}

		return classLoader;
	}

	/**
	 * 判断类是否存在
	 *
	 * @param className
	 * 		类名
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public static boolean isPresent(String className) {
		try{
			getClass(getDefaultClassLoader(), className, false);
			return true;
		}catch(IllegalAccessError error){
			throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" +
					className + "]: " + error.getMessage(), error);
		}catch(Throwable e){
			return false;
		}
	}

	/**
	 * 判断类是否存在
	 *
	 * @param className
	 * 		类名
	 * @param classLoader
	 * 		类加载器
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public static boolean isPresent(String className, @Nullable ClassLoader classLoader) {
		try{
			getClass(classLoader, className, false);
			return true;
		}catch(IllegalAccessError error){
			throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" +
					className + "]: " + error.getMessage(), error);
		}catch(Throwable e){
			return false;
		}
	}

	public static <T> T instantiate(Class<T> clazz, Object... args) throws ClassInstantiationException {
		Assert.isNull(clazz, "Class cloud not be null");
		Assert.isTrue(clazz.isInterface(),
				()->new ClassInstantiationException(clazz, "Specified class is an interface"));

		try{
			if(args == null){
				return instantiate(clazz.getDeclaredConstructor());
			}else{
				Class<?>[] ctorTypes = new Class[args.length];

				for(int i = 0; i < args.length; i++){
					if(args[i] == null){
						//
					}else{
						ctorTypes[i] = args[i].getClass();
					}
				}

				return instantiate(clazz.getDeclaredConstructor(ctorTypes), args);
			}
		}catch(NoSuchMethodException e){
			Constructor<T> ctor = findPrimaryConstructor(clazz);
			if(ctor != null){
				return instantiate(ctor);
			}
			throw new ClassInstantiationException(clazz, "No default constructor found", e);
		}catch(LinkageError err){
			throw new ClassInstantiationException(clazz, "Unresolvable class definition", err);
		}
	}

	/**
	 * Convenience method to instantiate a class using the given constructor.
	 * <p>Note that this method tries to set the constructor accessible if given a
	 * non-accessible (that is, non-public) constructor, and supports Kotlin classes
	 * with optional parameters and default values.
	 *
	 * @param ctor
	 * 		the constructor to instantiate
	 * @param args
	 * 		the constructor arguments to apply (use {@code null} for an unspecified parameter,
	 * 		Kotlin optional parameters and Java primitive types are supported)
	 * @param <T>
	 * 		实例类型
	 *
	 * @return The new instance
	 *
	 * @throws ClassInstantiationException
	 * 		if the bean cannot be instantiated
	 * @see Constructor#newInstance
	 * @since 2.3.2
	 */
	public static <T> T instantiate(Constructor<T> ctor, Object... args) throws ClassInstantiationException {
		Assert.isNull(ctor, "Constructor cloud not be null");

		try{
			ReflectionUtils.makeAccessible(ctor);

			if(KotlinDetector.isKotlinReflectPresent() && KotlinDetector.isKotlinType(ctor.getDeclaringClass())){
				return KotlinDelegate.instantiateClass(ctor, args);
			}else{
				Class<?>[] parameterTypes = ctor.getParameterTypes();
				Assert.isFalse(args.length <= parameterTypes.length,
						"Can't specify more arguments than constructor parameters");
				Object[] argsWithDefaultValues = new Object[args.length];

				for(int i = 0; i < args.length; i++){
					if(args[i] == null){
						Class<?> parameterType = parameterTypes[i];
						argsWithDefaultValues[i] = parameterType.isPrimitive() ? Primitive.DEFAULT_TYPE_VALUES.get(
								parameterType) : null;
					}else{
						argsWithDefaultValues[i] = args[i];
					}
				}

				return ctor.newInstance(argsWithDefaultValues);
			}
		}catch(InstantiationException e){
			throw new ClassInstantiationException(ctor, "Is it an abstract class?", e);
		}catch(IllegalAccessException e){
			throw new ClassInstantiationException(ctor, "Is the constructor accessible?", e);
		}catch(IllegalArgumentException e){
			throw new ClassInstantiationException(ctor, "Illegal arguments for constructor", e);
		}catch(InvocationTargetException e){
			throw new ClassInstantiationException(ctor, "Constructor threw exception", e.getTargetException());
		}
	}

	/**
	 * Return the primary constructor of the provided class. For Kotlin classes, this
	 * returns the Java constructor corresponding to the Kotlin primary constructor
	 * (as defined in the Kotlin specification). Otherwise, in particular for non-Kotlin
	 * classes, this simply returns {@code null}.
	 *
	 * @param clazz
	 * 		the class to check
	 * @param <T>
	 * 		实例类型
	 *
	 * @return The primary constructor of the provided class
	 *
	 * @see <a href="https://kotlinlang.org/docs/reference/classes.html#constructors">Kotlin docs</a>
	 * @since 2.3.2
	 */
	@Nullable
	public static <T> Constructor<T> findPrimaryConstructor(Class<T> clazz) {
		Assert.isNull(clazz, "Class must not be null");

		if(KotlinDetector.isKotlinReflectPresent() && KotlinDetector.isKotlinType(clazz)){
			return KotlinDelegate.findPrimaryConstructor(clazz);
		}

		return null;
	}

	/**
	 * 获取类的所有声明的字段，即包括 public、protected、private 和 default，但是不包括父类申明的字段
	 *
	 * @param clazz
	 * 		类
	 *
	 * @return 类的所有声明的字段
	 */
	public static Field[] getFields(final Class<?> clazz) {
		Assert.isNull(clazz, "Class cloud not be null.");
		return clazz.getDeclaredFields();
	}

	/**
	 * 获取类的所有声明的字段，即包括 public、protected、private 和 default，包括父类申明的字段
	 *
	 * @param clazz
	 * 		类
	 *
	 * @return 类的所有声明的字段
	 */
	public static Field[] getAllFields(final Class<?> clazz) {
		Assert.isNull(clazz, "Class cloud not be null.");
		final List<Field> allFields = new ArrayList<>(16);
		Class<?> currentClass = clazz;

		while(currentClass != null){
			Collections.addAll(allFields, currentClass.getDeclaredFields());
			currentClass = currentClass.getSuperclass();
		}

		return allFields.toArray(new Field[]{});
	}

	/**
	 * 检测一个类是否含有 annotations 中的任意一个注解
	 *
	 * @param clazz
	 * 		待验证的类
	 * @param annotations
	 * 		检测是否含有的注解
	 *
	 * @return 类包含有 annotations 中的任意一个注解返回 true；否则返回 false
	 *
	 * @since 2.3.2
	 */
	public static boolean hasAnnotation(final Class<?> clazz, final Class<? extends Annotation>[] annotations) {
		return AnnotationUtils.hasClassAnnotationPresent(clazz, annotations);
	}

	/**
	 * 调用类方法
	 *
	 * @param object
	 * 		类实例
	 * @param method
	 * 		类方法
	 *
	 * @return 返回方法执行结果
	 *
	 * @throws InvocationTargetException
	 * 		反射异常，当被调用的方法的内部抛出了异常而没有被捕获时，将由此异常接收
	 * @throws IllegalAccessException
	 *        {@link IllegalAccessException}
	 */
	public static Object invoke(final Object object, final Method method) throws InvocationTargetException,
			IllegalAccessException {
		Assert.isNull(object, "Object cloud not be null.");
		Assert.isNull(method, "Object method cloud not be null.");

		MethodUtils.setAccessible(method);

		return method.invoke(object);
	}

	/**
	 * 调用类方法
	 *
	 * @param object
	 * 		类实例
	 * @param method
	 * 		类方法
	 * @param arguments
	 * 		方法参数
	 *
	 * @return 返回方法执行结果
	 *
	 * @throws InvocationTargetException
	 * 		反射异常，当被调用的方法的内部抛出了异常而没有被捕获时，将由此异常接收
	 * @throws IllegalAccessException
	 *        {@link IllegalAccessException}
	 */
	public static Object invoke(final Object object, final Method method, final Object... arguments)
			throws InvocationTargetException, IllegalAccessException {
		Assert.isNull(object, "Object cloud not be null.");
		Assert.isNull(method, "Object method cloud not be null.");

		MethodUtils.setAccessible(method);

		return method.invoke(object, arguments);
	}

	/**
	 * Inner class to avoid a hard dependency on Kotlin at runtime.
	 *
	 * @since 2.3.2
	 */
	private final static class KotlinDelegate {

		/**
		 * Retrieve the Java constructor corresponding to the Kotlin primary constructor, if any.
		 *
		 * @param clazz
		 * 		the {@link Class} of the Kotlin class
		 *
		 * @see <a href="https://kotlinlang.org/docs/reference/classes.html#constructors">
		 * https://kotlinlang.org/docs/reference/classes.html#constructors</a>
		 */
		@Nullable
		public static <T> Constructor<T> findPrimaryConstructor(Class<T> clazz) {
			try{
				KFunction<T> primaryCtor = KClasses.getPrimaryConstructor(JvmClassMappingKt.getKotlinClass(clazz));
				if(primaryCtor == null){
					return null;
				}
				Constructor<T> constructor = ReflectJvmMapping.getJavaConstructor(primaryCtor);
				Assert.isNull(constructor, ()->new IllegalStateException(
						"Failed to find Java constructor for Kotlin primary constructor: " + clazz.getName()));
				return constructor;
			}catch(UnsupportedOperationException e){
				return null;
			}
		}

		/**
		 * Instantiate a Kotlin class using the provided constructor.
		 *
		 * @param ctor
		 * 		the constructor of the Kotlin class to instantiate
		 * @param args
		 * 		the constructor arguments to apply
		 * 		(use {@code null} for unspecified parameter if needed)
		 */
		public static <T> T instantiateClass(Constructor<T> ctor, Object... args)
				throws IllegalAccessException, InvocationTargetException, InstantiationException {
			KFunction<T> kotlinConstructor = ReflectJvmMapping.getKotlinFunction(ctor);

			if(kotlinConstructor == null){
				return ctor.newInstance(args);
			}

			if(Modifier.isPublic(ctor.getModifiers()) == false ||
					Modifier.isPublic(ctor.getDeclaringClass().getModifiers()) == false){
				KCallablesJvm.setAccessible(kotlinConstructor, true);
			}

			List<KParameter> parameters = kotlinConstructor.getParameters();

			Assert.isFalse(args.length <= parameters.size(),
					"Number of provided arguments should be less of equals than number of constructor parameters");

			Map<KParameter, Object> argParameters = new HashMap<>(parameters.size());

			for(int i = 0; i < args.length; i++){
				if(!(parameters.get(i).isOptional() && args[i] == null)){
					argParameters.put(parameters.get(i), args[i]);
				}
			}

			return kotlinConstructor.callBy(argParameters);
		}

	}

}

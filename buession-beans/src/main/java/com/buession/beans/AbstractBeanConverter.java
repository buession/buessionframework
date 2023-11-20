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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.beans;

import com.buession.beans.converters.*;
import com.buession.core.utils.Assert;
import com.buession.core.utils.FieldUtils;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Primitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 转换器抽象类
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public abstract class AbstractBeanConverter implements BeanConverter {

	private final static Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>(32);

	private final static Map<Class<?>, BeanPropertyConverter<?>> converters = new ConcurrentHashMap<>(24);

	private final static Logger logger = LoggerFactory.getLogger(AbstractBeanConverter.class);

	/**
	 * 构造函数
	 */
	public AbstractBeanConverter() {
		initDefaultBeanPropertyConverters();
	}

	/**
	 * 注册 bean 属性转换器
	 *
	 * @param type
	 * 		类型
	 * @param propertyConverter
	 * 		bean 属性转换器
	 */
	public void registerConverter(Class<?> type, BeanPropertyConverter<?> propertyConverter) {
		converters.put(type, propertyConverter);
	}

	@Override
	public <S, T> T convert(final S source, final T target) {
		Assert.isNull(target, "No destination bean specified.");

		if(source == null){
			return null;
		}

		final boolean isSourceMap = source instanceof Map;
		final boolean isTargetMap = target instanceof Map;

		if(isSourceMap && isTargetMap){
			return mapToMap(source, target);
		}

		if(isSourceMap && isTargetMap == false){
			return mapToBean(source, target);
		}

		if(isSourceMap == false && isTargetMap){
			return beanToMap(source, target);
		}

		return beanToBean(source, target);
	}

	@SuppressWarnings({"unchecked"})
	protected <S, T> T mapToMap(final S source, final T target) {
		Map<Object, Object> sourceMap = (Map<Object, Object>) source;
		Map<Object, Object> targetMap = (Map<Object, Object>) target;

		targetMap.putAll(sourceMap);

		return target;
	}

	@SuppressWarnings({"unchecked"})
	protected <S, T> T mapToBean(final S source, final T target) {
		final Map<Object, Object> sourceMap = (Map<Object, Object>) source;
		final BeanMap beanMap = BeanMap.create(target);

		sourceMap.forEach((key, value)->{
			if(key instanceof CharSequence){
				String strKey = (String) key;
				String propertyName;

				if(StringUtils.contains(strKey, '_')){
					StringBuilder sb = new StringBuilder(strKey.length());

					for(int i = 0; i < strKey.length(); i++){
						char c = strKey.charAt(i);

						if(c == '_'){
							c = strKey.charAt(++i);
							sb.append((char) (c - 32));
						}else{
							sb.append(c);
						}
					}

					propertyName = sb.toString();
				}else{
					propertyName = strKey;
				}

				Field field = FieldUtils.getField(target.getClass(), propertyName, true);

				if(field != null){
					Class<?> fieldType = Primitive.primitiveToWrapper(field.getType());

					if(value == null || fieldType == value.getClass()){
						beanMap.put(propertyName, value);
					}else{
						final BeanPropertyConverter<?> propertyConverter = converters.get(fieldType);
						try{
							if(propertyConverter == null){
								beanMap.put(propertyName, value);
							}else{
								beanMap.put(propertyName, propertyConverter.convert(value));
							}
						}catch(Exception e){
							if(logger.isWarnEnabled()){
								logger.warn(e.getMessage());
							}
						}
					}
				}
			}
		});

		return target;
	}

	@SuppressWarnings({"unchecked"})
	protected <S, T> T beanToMap(final S source, final T target) {
		final BeanMap beanMap = BeanMap.create(source);
		final Map<Object, Object> targetMap = (Map<Object, Object>) target;

		targetMap.putAll(beanMap);

		return target;
	}

	@SuppressWarnings({"unchecked", "rawtype"})
	protected <S, T> T beanToBean(final S source, final T target) {
		final String cacheKey = buildCacheKey(source, target);
		final BeanCopier beanCopier = BEAN_COPIERS.computeIfAbsent(cacheKey,
				(key)->BeanCopier.create(source.getClass(), target.getClass(), true));

		beanCopier.copy(source, target, (value, targetType, setter)->{
			if(value == null){
				return null;
			}else if(targetType.equals(value.getClass())){
				return value;
			}else{
				final BeanPropertyConverter<?> propertyConverter = converters.get(
						Primitive.primitiveToWrapper(targetType));

				if(propertyConverter == null){
					return value;
				}else{
					return propertyConverter.convert(value);
				}
			}
		});

		return target;
	}

	private static <S, T> String buildCacheKey(final S source, final T target) {
		return source.getClass().getName() + '_' + target.getClass().getName();
	}

	private void initDefaultBeanPropertyConverters() {
		converters.put(Byte.class, new BytePropertyConverter());
		converters.put(Short.class, new ShortPropertyConverter());
		converters.put(Integer.class, new IntegerPropertyConverter());
		converters.put(Long.class, new LongPropertyConverter());
		converters.put(Float.class, new FloatPropertyConverter());
		converters.put(Double.class, new DoublePropertyConverter());
		converters.put(BigDecimal.class, new BigDecimalPropertyConverter());
		converters.put(BigInteger.class, new BigIntegerPropertyConverter());

		converters.put(Boolean.class, new BooleanPropertyConverter());

		converters.put(Character.class, new CharacterPropertyConverter());
		converters.put(String.class, new StringPropertyConverter());

		converters.put(Calendar.class, new CalendarPropertyConverter());
		converters.put(java.util.Date.class, new DatePropertyConverter());
		converters.put(LocalDate.class, new LocalDatePropertyConverter());
		converters.put(LocalDateTime.class, new LocalDateTimePropertyConverter());
		converters.put(java.sql.Date.class, new SqlDatePropertyConverter());
		converters.put(java.sql.Time.class, new SqlTimePropertyConverter());
		converters.put(java.sql.Timestamp.class, new SqlTimestampPropertyConverter());
		converters.put(Instant.class, new InstantPropertyConverter());

		converters.put(Class.class, new ClassPropertyConverter());

		converters.put(File.class, new FilePropertyConverter());
		converters.put(Path.class, new PathPropertyConverter());

		converters.put(URI.class, new URIPropertyConverter());
		converters.put(URL.class, new URLPropertyConverter());
	}

}

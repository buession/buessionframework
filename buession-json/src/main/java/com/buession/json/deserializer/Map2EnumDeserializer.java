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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.json.deserializer;

import com.buession.core.utils.EnumUtil;
import com.buession.core.utils.ReflectUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yong.Teng
 */
public class Map2EnumDeserializer extends JsonDeserializer<Enum<?>> {

	private final static Map<String, Enum<?>> cache = new ConcurrentHashMap<>(32);

	private final Logger logger = LoggerFactory.getLogger(Map2EnumDeserializer.class);

	@SuppressWarnings({"unchecked"})
	@Override
	public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
			IOException{
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		/** 当前节点名 */
		String fieldName = jsonParser.getCurrentName();
		/** 当前值 */
		Object currentValue = jsonParser.getCurrentValue();
		Class<?> clazz = currentValue.getClass();

		try{
			Field nodeCurrentField = clazz.getDeclaredField(fieldName);
			Class<?> nodeCurrentFieldType = nodeCurrentField.getType();

			if(nodeCurrentFieldType.isEnum() == false){
				throw new JsonParseException(jsonParser, nodeCurrentFieldType.getName() + " is not Enum.");
			}

			/** 可能只是字符串 **/
			if(node.size() == 0){
				return Enum.valueOf((Class<Enum>) nodeCurrentFieldType, node.asText());
			}

			final String cacheKey = parseCacheKey(clazz, nodeCurrentField, node);
			Enum<?> ret = cache.get(cacheKey);

			if(ret != null){
				return ret;
			}

			Map<String, JsonNode> nodeMapValues = getNodeMapVlues(node);
			Field[] nodeCurrentFieldTypeFields = nodeCurrentFieldType.getDeclaredFields();

			for(Field field : nodeCurrentFieldTypeFields){
				if(field.isEnumConstant() == false){
					continue;
				}

				ReflectUtils.setFieldAccessible(field);

				Enum<?> enumValue = (Enum<?>) field.get(currentValue);
				if(enumValue == null){
					continue;
				}

				Field[] enumValueFields = enumValue.getClass().getDeclaredFields();
				Map<String, Object> temp = new HashMap<>(enumValueFields.length);

				for(Field enumValueField : enumValueFields){
					if(EnumUtil.notEnumValuesField(enumValueField)){
						ReflectUtils.setFieldAccessible(enumValueField);

						temp.put(enumValueField.getName(), enumValueField.get(enumValue));
					}
				}

				if(temp.size() == nodeMapValues.size()){
					if(comparatorMap(temp, nodeMapValues)){
						cache.put(cacheKey, enumValue);

						return enumValue;
					}
				}
			}
		}catch(NoSuchFieldException e){
			logger.error("{}", e);
		}catch(IllegalAccessException e){
			logger.error("{}", e);
		}

		return null;
	}

	private final static String parseCacheKey(final Class<?> clazz, final Field field, final JsonNode node){
		final String className = clazz.getName();
		final String fieldName = field.getName();
		final String nodeName = node.toString();
		final StringBuilder sb = new StringBuilder(className.length() + fieldName.length() + nodeName.length() + 2);

		sb.append(className);
		sb.append('_');
		sb.append(fieldName);
		sb.append('_');
		sb.append(nodeName);

		return sb.toString();
	}

	private final static Map<String, JsonNode> getNodeMapVlues(final JsonNode node){
		if(node == null || node.isObject() == false){
			return null;
		}

		final Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
		final Map<String, JsonNode> result = new HashMap<>(node.size());

		while(iterator.hasNext()){
			Map.Entry<String, JsonNode> map = iterator.next();
			result.put(map.getKey(), map.getValue());
		}

		return result;
	}

	private final static boolean comparatorMap(final Map<String, Object> map1, final Map<String, JsonNode> map2){
		Iterator<Map.Entry<String, Object>> iterator1 = map1.entrySet().iterator();

		while(iterator1.hasNext()){
			Map.Entry<String, Object> entry1 = iterator1.next();

			if(checkEquals(entry1.getValue(), map2.get(entry1.getKey())) == false){
				return false;
			}
		}

		return true;
	}

	private final static boolean checkEquals(Object value, JsonNode node){
		if(node.isBigDecimal()){
			return value.equals(node.decimalValue());
		}else if(node.isBigInteger()){
			return value.equals(node.shortValue()) || value.equals(node.intValue()) || value.equals(node.longValue())
					|| value.equals(node.bigIntegerValue());
		}else if(node.isLong()){
			return value.equals(node.shortValue()) || value.equals(node.intValue()) || value.equals(node.longValue())
					|| value.equals(node.bigIntegerValue());
		}else if(node.isInt()){
			return value.equals(node.shortValue()) || value.equals(node.intValue()) || value.equals(node.longValue())
					|| value.equals(node.bigIntegerValue());
		}else if(node.isShort()){
			return value.equals(node.shortValue()) || value.equals(node.intValue()) || value.equals(node.longValue())
					|| value.equals(node.bigIntegerValue());
		}else if(node.isDouble()){
			return value.equals(node.floatValue()) || value.equals(node.doubleValue());
		}else if(node.isFloat()){
			return value.equals(node.floatValue()) || value.equals(node.doubleValue());
		}else if(node.isNumber()){
			return value.equals(node.numberValue());
		}else if(node.isBoolean() && value instanceof Boolean){
			return value.equals(node.booleanValue());
		}else if(node.isTextual() && value instanceof CharSequence){
			return value.equals(node.textValue());
		}else{
			return false;
		}
	}

}

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
package com.buession.core.utils;

import com.buession.core.validator.Validate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class Assert {

	private Assert(){

	}

	public final static void isTrue(final boolean expression, final String message){
		if(expression){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isFalse(final boolean expression, final String message){
		if(expression == false){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isNull(final Object object, final String message){
		if(object == null){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notNull(final Object object, final String message){
		if(object != null){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isEmpty(final Object[] objects, final String message){
		if(Validate.isEmpty(objects)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isEmpty(final Collection<?> collection, final String message){
		if(Validate.isEmpty(collection)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isEmpty(final Map<?, ?> map, final String message){
		if(Validate.isEmpty(map)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isEmpty(final Iterator iterator, final String message){
		if(Validate.isEmpty(iterator)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isEmpty(final Enumeration enumeration, final String message){
		if(Validate.isEmpty(enumeration)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notEmpty(final Object[] objects, final String message){
		if(Validate.isNotEmpty(objects)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notEmpty(final Collection<?> collection, final String message){
		if(Validate.isNotEmpty(collection)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notEmpty(final Map<?, ?> map, final String message){
		if(Validate.isNotEmpty(map)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notEmpty(final Iterator iterator, final String message){
		if(Validate.isNotEmpty(iterator)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notEmpty(final Enumeration enumeration, final String message){
		if(Validate.isNotEmpty(enumeration)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isBlank(final String str, final String message){
		if(Validate.hasText(str) == false){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void notBlank(final String str, final String message){
		if(Validate.hasText(str)){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isNegative(final Long value, final String message){
		if(value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isNegative(final Integer value, final String message){
		if(value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isNegative(final Short value, final String message){
		if(value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isNegative(final Double value, final String message){
		if(value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isNegative(final Float value, final String message){
		if(value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isZeroNegative(final Long value, final String message){
		if(value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isZeroNegative(final Integer value, final String message){
		if(value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isZeroNegative(final Short value, final String message){
		if(value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isZeroNegative(final Double value, final String message){
		if(value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	public final static void isZeroNegative(final Float value, final String message){
		if(value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

}

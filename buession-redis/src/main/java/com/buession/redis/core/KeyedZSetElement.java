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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.core.utils.comparator.ByteArrayComparable;
import com.buession.redis.utils.SafeEncoder;

import java.util.Arrays;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class KeyedZSetElement extends Tuple {

	private final static long serialVersionUID = -8468320641916277445L;

	private final byte[] key;

	public KeyedZSetElement(final String key, final String element, final Double score){
		super(element, score);
		this.key = SafeEncoder.encode(key);
	}

	public KeyedZSetElement(final byte[] key, final byte[] element, final Double score){
		super(element, score);
		this.key = key;
	}

	public String getKey(){
		return key == null ? null : SafeEncoder.encode(key);
	}

	public byte[] getBinaryKey(){
		return key;
	}

	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + Arrays.hashCode(key);
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof KeyedZSetElement){
			KeyedZSetElement that = (KeyedZSetElement) obj;
			return Arrays.equals(key, that.key) && super.equals(obj);
		}

		return false;
	}

	public int compareTo(KeyedZSetElement that){
		int compScore = Double.compare(this.getScore(), that.getScore());

		if(compScore != 0){
			return compScore;
		}else{
			ByteArrayComparable comparable = new ByteArrayComparable(this.getBinaryElement());

			compScore = comparable.compareTo(that.getBinaryElement());
			if(compScore != 0){
				return compScore;
			}else{
				comparable = new ByteArrayComparable(this.key);
				return comparable.compareTo(that.key);
			}
		}
	}

	@Override
	public String toString(){
		return "key=" + SafeEncoder.encode(key) + ", " + super.toString();
	}

}

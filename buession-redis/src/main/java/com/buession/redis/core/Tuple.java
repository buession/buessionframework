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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 */
public class Tuple implements Comparable<Tuple>, Serializable {

	private final static long serialVersionUID = -6469375940111456577L;

	private final byte[] element;

	private final Double score;

	public Tuple(final String element, final Double score){
		this(SafeEncoder.encode(element), score);
	}

	public Tuple(final byte[] element, final Double score){
		super();
		this.element = element;
		this.score = score;
	}

	public String getElement(){
		return element == null ? null : SafeEncoder.encode(element);
	}

	public byte[] getBinaryElement(){
		return element;
	}

	public double getScore(){
		return score;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;

		result = prime * result;

		if(null != element){
			for(byte b : element){
				result = prime * result + b;
			}
		}

		long temp = Double.doubleToLongBits(score);
		return prime * result + (int) (temp ^ (temp >>> 32));
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Tuple){
			Tuple that = (Tuple) obj;
			return Arrays.equals(element, that.element) && Objects.equals(score, that.score);
		}

		return false;
	}

	@Override
	public int compareTo(Tuple that){
		int compScore = Double.compare(this.score, that.score);

		if(compScore != 0){
			return compScore;
		}else{
			ByteArrayComparable comparable = new ByteArrayComparable(this.element);
			return comparable.compareTo(that.element);
		}
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "{", "}")
				.add("element=" + SafeEncoder.encode(element))
				.add("score=" + score)
				.toString();
	}

}

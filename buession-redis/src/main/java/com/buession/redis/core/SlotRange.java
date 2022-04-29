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

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 哈希槽范围
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SlotRange implements Serializable {

	private final static long serialVersionUID = -185528503728995147L;

	private final int lowerBound;

	private final int upperBound;

	private final Set<Integer> range;

	public SlotRange(int lowerBound, int upperBound){
		this.range = new LinkedHashSet<>();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;

		for(int i = lowerBound; i <= upperBound; i++){
			this.range.add(i);
		}
	}

	public boolean contains(int slot){
		return range.contains(slot);
	}

	public Set<Integer> getSlots(){
		return Collections.unmodifiableSet(range);
	}

	public int[] getSlotsArray(){
		int[] slots = new int[range.size()];
		int pos = 0;

		for(Integer value : range){
			slots[pos++] = value;
		}

		return slots;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder();

		sb.append(lowerBound).append('-').append(upperBound);

		return sb.toString();
	}

}

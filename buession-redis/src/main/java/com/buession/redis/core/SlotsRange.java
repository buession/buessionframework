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

/**
 * Redis Cluster Hash Slots Range
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SlotsRange implements Serializable {

	private final static long serialVersionUID = -1872294358162514087L;

	private long start;

	private long end;

	public SlotsRange(){
	}

	public SlotsRange(final long start, final long end){
		this.start = start;
		this.end = end;
	}

	public long getStart(){
		return start;
	}

	public void setStart(long start){
		this.start = start;
	}

	public long getEnd(){
		return end;
	}

	public void setEnd(long end){
		this.end = end;
	}

	@Override
	public String toString(){
		return "start=" + start + ", end=" + end;
	}
	
}

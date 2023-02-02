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
package com.buession.git;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author Yong.Teng
 * @since 2.2.0
 */
class GitInfoBuilder {

	private final static String PREFIX = "git";

	private final StringBuilder sb = new StringBuilder();

	private final String group;

	private int i = 0;

	private GitInfoBuilder(final String group){
		this.group = group;
	}

	public static GitInfoBuilder getInstance(){
		return getInstance(null);
	}

	public static GitInfoBuilder getInstance(final String group){
		return new GitInfoBuilder(group);
	}

	public GitInfoBuilder append(final String name, final String value){
		ensure();
		ensurePrefix();

		if(name != null){
			sb.append(name).append('=');
		}else{
			endDotReplaceToEqualsSign();
		}

		if(value != null){
			sb.append(value);
		}

		return this;
	}

	public GitInfoBuilder append(final String name, final int value){
		ensure();
		ensurePrefix();

		if(name != null){
			sb.append(name).append('=');
		}else{
			endDotReplaceToEqualsSign();
		}

		sb.append(value);

		return this;
	}

	public GitInfoBuilder append(final String name, final boolean value){
		ensure();
		ensurePrefix();

		if(name != null){
			sb.append(name).append('=');
		}else{
			endDotReplaceToEqualsSign();
		}

		sb.append(value);

		return this;
	}

	public GitInfoBuilder append(final String name, final Set<String> value){
		if(value != null){
			ensure();
			ensurePrefix();

			if(name != null){
				sb.append(name).append('=');
			}else{
				endDotReplaceToEqualsSign();
			}

			sb.append(String.join(",", value));
		}

		return this;
	}

	public GitInfoBuilder append(final String name, final ZonedDateTime value){
		ensure();
		ensurePrefix();

		if(name != null){
			sb.append(name).append('=');
		}else{
			endDotReplaceToEqualsSign();
		}

		sb.append(value == null ? "" : value);

		return this;
	}

	public GitInfoBuilder append(final String name, final Info value){
		return append(name, value, false);
	}

	public GitInfoBuilder append(final String name, final Info value, final boolean containName){
		if(value != null){
			ensure();

			if(containName && name != null){
				sb.append(name).append('=');
			}

			sb.append(value);
		}

		return this;
	}

	public String build(){
		return sb.toString();
	}

	private void ensurePrefix(){
		sb.append(PREFIX).append('.');
		if(group != null){
			sb.append(group).append('.');
		}
	}

	private void ensure(){
		if(i++ > 0){
			sb.append(System.lineSeparator());
		}
	}

	private void endDotReplaceToEqualsSign(){
		sb.setCharAt(sb.length() - 1, '=');
	}

}

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
package com.buession.redis.utils;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class ObjectStringBuilder {

	private final StringBuilder sb = new StringBuilder("{");

	private boolean initialized = false;

	private ObjectStringBuilder(){
	}

	public static ObjectStringBuilder create(){
		return new ObjectStringBuilder();
	}

	public ObjectStringBuilder add(final String name, final boolean value){
		if(initialized){
			sb.append(", ");
		}

		sb.append(name).append('=').append(value);
		initialized = true;

		return this;
	}

	public ObjectStringBuilder add(final String name, final short value){
		if(initialized){
			sb.append(", ");
		}

		sb.append(name).append('=').append(value);
		initialized = true;

		return this;
	}

	public ObjectStringBuilder add(final String name, final int value){
		if(initialized){
			sb.append(", ");
		}

		sb.append(name).append('=').append(value);
		initialized = true;

		return this;
	}

	public ObjectStringBuilder add(final String name, final long value){
		if(initialized){
			sb.append(", ");
		}

		sb.append(name).append('=').append(value);
		initialized = true;

		return this;
	}

	public ObjectStringBuilder add(final String name, final float value){
		if(initialized){
			sb.append(", ");
		}

		sb.append(name).append('=').append(value);
		initialized = true;

		return this;
	}

	public ObjectStringBuilder add(final String name, final double value){
		if(initialized){
			sb.append(", ");
		}

		sb.append(name).append('=').append(value);
		initialized = true;

		return this;
	}

	public ObjectStringBuilder add(final String name, final byte[] value){
		if(value != null){
			if(initialized){
				sb.append(", ");
			}

			sb.append(name).append('=').append(SafeEncoder.encode(value));
			initialized = true;
		}

		return this;
	}

	public ObjectStringBuilder add(final String name, final String value){
		if(value != null){
			if(initialized){
				sb.append(", ");
			}

			sb.append(name).append('=').append(value);
			initialized = true;
		}

		return this;
	}

	public ObjectStringBuilder add(final String name, final Object value){
		if(value != null){
			if(initialized){
				sb.append(", ");
			}

			sb.append(name).append('=').append(value);
			initialized = true;
		}

		return this;
	}

	public ObjectStringBuilder append(final CharSequence seq){
		if(seq != null){
			if(initialized){
				sb.append(", ");
			}

			sb.append(seq);
			initialized = true;
		}

		return this;
	}

	public String build(){
		return sb.append('}').toString();
	}

}

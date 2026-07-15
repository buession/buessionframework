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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.pipeline.lettuce;

import com.buession.core.utils.Assert;
import com.buession.redis.pipeline.Pipeline;

import java.util.List;

/**
 * Lettuce 管道
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettucePipeline<K, V> implements Pipeline {

	private final io.lettuce.core.Pipeline<K, V> delegate;

	public LettucePipeline(final io.lettuce.core.Pipeline<K, V> pipeline) {
		Assert.isNull(pipeline, "Redis Pipeline cloud not be null.");
		this.delegate = pipeline;
	}

	@Override
	public void sync() {
		delegate.sync();
	}

	@Override
	public List<Object> syncAndReturnAll() {
		return delegate.syncAndReturnAll();
	}

	@Override
	public void close() {
		if(delegate != null){
			delegate.close();
		}
	}

}

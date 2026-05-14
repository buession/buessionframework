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
package com.buession.core;

import com.buession.core.utils.Assert;

import java.time.Duration;

/**
 * 线程休眠
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
@FunctionalInterface
public interface Sleeper {

	/**
	 * 线程休眠
	 *
	 * @param millis
	 * 		休眠时间
	 *
	 * @throws InterruptedException
	 * 		异常
	 */
	void sleep(long millis) throws InterruptedException;

	/**
	 * 线程休眠
	 *
	 * @param duration
	 * 		休眠时间
	 *
	 * @throws InterruptedException
	 * 		异常
	 */
	default void sleep(Duration duration) throws InterruptedException {
		Assert.isNull(duration, "Sleep time cloud not be null");
		sleep(duration.toMillis());
	}

}

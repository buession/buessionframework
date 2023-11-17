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
package com.buession.core.concurrent;

import java.util.concurrent.RejectedExecutionException;

/**
 * 线程池饱和策略
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public enum ThreadPolicy {

	/**
	 * 直接抛出 {@link RejectedExecutionException} 异常来拒绝处理新任务
	 */
	ABORT,

	/**
	 * 只用调用者所在的线程来运行任务，会降低新任务的提交速度，影响程序的整体性能
	 */
	CALLER_RUNS,

	/**
	 * 不处理新任务，直接丢弃掉
	 */
	DISCARD,

	/**
	 * 丢弃队列中最近的一个任务，执行当前任务
	 */
	DISCARD_OLDEST

}

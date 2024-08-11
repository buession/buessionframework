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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.jdbc.datasource.pool;

import com.buession.jdbc.core.Jmx;

import java.time.Duration;

/**
 * 连接池配置
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public interface PoolConfiguration {

	/**
	 * 返回用户定义连接池的名称
	 *
	 * @return 用户定义连接池的名称
	 */
	String getPoolName();

	/**
	 * 设置连接池的名称
	 *
	 * @param poolName
	 * 		连接池的名称
	 */
	void setPoolName(String poolName);

	/**
	 * 返回初始连接数
	 *
	 * @return 初始连接数
	 */
	Integer getInitialSize();

	/**
	 * 设置初始连接数
	 *
	 * @param initialSize
	 * 		初始连接数
	 */
	void setInitialSize(Integer initialSize);

	/**
	 * 返回最小空闲连接数
	 *
	 * @return 最小空闲连接数
	 */
	Integer getMinIdle();

	/**
	 * 设置最小空闲连接数
	 *
	 * @param minIdle
	 * 		最小空闲连接数
	 */
	void setMinIdle(Integer minIdle);

	/**
	 * 返回最大空闲连接数
	 *
	 * @return 最大空闲连接数
	 */
	Integer getMaxIdle();

	/**
	 * 设置最大空闲连接数，如设置为负数，则不限制
	 *
	 * @param maxIdle
	 * 		最大空闲连接数
	 */
	void setMaxIdle(Integer maxIdle);

	/**
	 * 返回最大连接数
	 *
	 * @return 最大连接数
	 */
	Integer getMaxTotal();

	/**
	 * 设置最大连接数，如设置为负数，则不限制
	 *
	 * @param maxTotal
	 * 		最大连接数
	 */
	void setMaxTotal(Integer maxTotal);

	/**
	 * 返回从连接池获取一个连接时，最大的等待时间
	 *
	 * @return 从连接池获取一个连接时，最大的等待时间
	 */
	Duration getMaxWait();

	/**
	 * 设置从连接池获取一个连接时，最大的等待时间
	 *
	 * @param maxWait
	 * 		从连接池获取一个连接时，最大的等待时间
	 */
	void setMaxWait(Duration maxWait);

	/**
	 * 返回连接创建后，是否马上验证有效性
	 *
	 * @return 连接创建后，是否马上验证有效性
	 */
	default Boolean isTestOnCreate() {
		return getTestOnCreate();
	}

	/**
	 * 返回连接创建后，是否马上验证有效性
	 *
	 * @return 连接创建后，是否马上验证有效性
	 */
	Boolean getTestOnCreate();

	/**
	 * 设置连接创建后，是否马上验证有效性
	 *
	 * @param testOnCreate
	 * 		连接创建后，是否马上验证有效性
	 */
	void setTestOnCreate(Boolean testOnCreate);

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	default Boolean isTestOnBorrow() {
		return getTestOnBorrow();
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	Boolean getTestOnBorrow();

	/**
	 * 设置从连接池获取一个连接时，是否验证有效性
	 *
	 * @param testOnBorrow
	 * 		从连接池获取一个连接时，是否验证有效性
	 */
	void setTestOnBorrow(Boolean testOnBorrow);

	/**
	 * 返回连接被归还到连接池时，是否验证有效性
	 *
	 * @return 连接被归还到连接池时，是否验证有效性
	 */
	default Boolean isTestOnReturn() {
		return getTestOnReturn();
	}

	/**
	 * 返回连接被归还到连接池时，是否验证有效性
	 *
	 * @return 连接被归还到连接池时，是否验证有效性
	 */
	Boolean getTestOnReturn();

	/**
	 * 设置连接被归还到连接池时，是否验证有效性
	 *
	 * @param testOnReturn
	 * 		连接被归还到连接池时，是否验证有效性
	 */
	void setTestOnReturn(Boolean testOnReturn);

	/**
	 * 返回连接空闲时，是否验证有效性
	 *
	 * @return 连接空闲时，是否验证有效性
	 */
	default Boolean isTestWhileIdle() {
		return getTestWhileIdle();
	}

	/**
	 * 返回连接空闲时，是否验证有效性
	 *
	 * @return 连接空闲时，是否验证有效性
	 */
	Boolean getTestWhileIdle();

	/**
	 * 设置连接空闲时，是否验证有效性
	 *
	 * @param testWhileIdle
	 * 		连接空闲时，是否验证有效性
	 */
	void setTestWhileIdle(Boolean testWhileIdle);

	/**
	 * 返回空闲的连接被释放最低要待时间
	 *
	 * @return 空闲的连接被释放最低要待时间
	 */
	Duration getMinEvictableIdle();

	/**
	 * 设置空闲的连接被释放最低要待时间
	 *
	 * @param minEvictableIdle
	 * 		空闲的连接被释放最低要待时间
	 */
	void setMinEvictableIdle(Duration minEvictableIdle);

	/**
	 * 返回空闲的连接被释放最高要待时间
	 *
	 * @return 空闲的连接被释放最高要待时间
	 */
	Duration getMaxEvictableIdle();

	/**
	 * 设置空闲的连接被释放最高要待时间
	 *
	 * @param maxEvictableIdle
	 * 		空闲的连接被释放最高要待时间
	 */
	void setMaxEvictableIdle(Duration maxEvictableIdle);

	/**
	 * 返回在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 *
	 * @return 在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	Integer getNumTestsPerEvictionRun();

	/**
	 * 设置在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 *
	 * @param numTestsPerEvictionRun
	 * 		在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun);

	/**
	 * 返回空闲对象驱逐线程运行时的休眠时间
	 *
	 * @return 空闲对象驱逐线程运行时的休眠时间
	 */
	Duration getTimeBetweenEvictionRuns();

	/**
	 * 设置空闲对象驱逐线程运行时的休眠时间
	 *
	 * @param timeBetweenEvictionRuns
	 * 		空闲对象驱逐线程运行时的休眠时间
	 */
	void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns);

	/**
	 * 返回指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @return 指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @since 3.0.0
	 */
	Duration getRemoveAbandonedTimeout();

	/**
	 * 设置指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @param removeAbandonedTimeout
	 * 		指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @since 3.0.0
	 */
	void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout);

	/**
	 * 返回 JMX 管理对象配置
	 *
	 * @return JMX 管理对象配置
	 */
	Jmx getJmx();

	/**
	 * 设置 JMX 管理对象配置
	 *
	 * @param jmx
	 * 		JMX 管理对象配置
	 */
	void setJmx(Jmx jmx);

}

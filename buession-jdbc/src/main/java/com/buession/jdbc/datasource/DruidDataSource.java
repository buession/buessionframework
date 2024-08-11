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
package com.buession.jdbc.datasource;

import com.alibaba.druid.Constants;
import com.alibaba.druid.util.Utils;
import com.buession.core.builder.ListBuilder;
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.jdbc.core.Jmx;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.pool.DruidPoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Druid DataSource
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DruidDataSource
		extends AbstractDataSource<com.alibaba.druid.pool.DruidDataSource, DruidPoolConfiguration> {

	/**
	 * 用户名回调 {@link NameCallback} 类名
	 *
	 * @since 3.0.0
	 */
	private String userCallbackClassName;

	/**
	 * 密码回调 {@link PasswordCallback} 类名
	 *
	 * @since 3.0.0
	 */
	private String passwordCallbackClassName;

	/**
	 * 连接超时
	 *
	 * @since 3.0.0
	 */
	private Duration connectTimeout;

	/**
	 * 网络套接字的读超时
	 *
	 * @since 3.0.0
	 */
	private Duration socketTimeout;

	/**
	 * 在发生连接错误后，连接池尝试重新获取连接之间的间隔时间
	 *
	 * @since 3.0.0
	 */
	private Duration timeBetweenConnectError;

	/**
	 * 数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @since 3.0.0
	 */
	private Boolean killWhenSocketReadTimeout;

	/**
	 * 物理连接的超时时间
	 *
	 * @since 3.0.0
	 */
	private Duration phyTimeout;

	/**
	 * 物理数据库连接的最大使用次数，设定每个物理连接在被关闭之前最多可以被重复使用的次数
	 *
	 * @since 3.0.0
	 */
	private Long phyMaxUseCount;

	/**
	 * 数据源初始化时是否以异步方式进行
	 *
	 * @since 3.0.0
	 */
	private Boolean asyncInit;

	/**
	 * 是否启用特定的变体或选项
	 *
	 * @since 3.0.0
	 */
	private Boolean initVariants;

	/**
	 * 在连接池初始化时应用的全局变体或选项
	 *
	 * @since 3.0.0
	 */
	private Boolean initGlobalVariants;

	/**
	 * 在第一次创建时用来初始化物理连接的SQL语句集合，只在配置的连接工厂创建连接时被执行一次
	 *
	 * @since 3.0.0
	 */
	private Collection<String> connectionInitSqls;

	/**
	 * 自定义检查数据库连接的有效性类
	 *
	 * @since 3.0.0
	 */
	private String validConnectionCheckerClassName;

	/**
	 * 连接出错重试次数
	 *
	 * @since 3.0.0
	 */
	private Integer connectionErrorRetryAttempts;

	/**
	 * 数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @since 3.0.0
	 */
	private Boolean initExceptionThrow;

	/**
	 * 异常分类器 {@link com.alibaba.druid.pool.ExceptionSorter} 名称；
	 * 用于检测和分类数据库连接中的异常，当检测到某些特定类型的异常时，可以根据分类器的判断来决定是否将当前连接标记为不可用，并从连接池中移除。
	 */
	private String exceptionSorterClassName;

	/**
	 * 是否启用 Oracle JDBC 驱动的隐式连接缓存
	 */
	private Boolean useOracleImplicitCache;

	/**
	 * 是否异步关闭连接
	 *
	 * @since 3.0.0
	 */
	private Boolean asyncCloseConnectionEnable;

	/**
	 * 在事务中执行的 SQL 查询的超时时间
	 *
	 * @since 3.0.0
	 */
	private Duration transactionQueryTimeout;

	/**
	 * 事务的阈值时间
	 *
	 * @since 3.0.0
	 */
	private Duration transactionThreshold;

	/**
	 * 控制连接池内部锁机制的公平性。
	 * 当为 {@code true} 时，使用公平锁，公平锁保证线程获取锁的顺序是按照线程请求锁的顺序，即先请求锁的线程会先获得锁；适用于对锁获取顺序有严格要求的场景，但可能会导致较高的上下文切换成本，从而影响性能。
	 * 当为 {@code false} 时，使用非公平锁，非公平锁在获取锁时不考虑线程的请求顺序，可能会导致“线程饥饿”现象，但通常能够提供更好的吞吐量和性能。
	 *
	 * @since 3.0.0
	 */
	private Boolean fairLock;

	/**
	 * @since 3.0.0
	 */
	private ScheduledExecutorService createScheduler;

	/**
	 * @since 3.0.0
	 */
	private ScheduledExecutorService destroyScheduler;

	/**
	 * 数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @since 3.0.0
	 */
	private Boolean failFast;

	/**
	 * 是否监控执行时间
	 *
	 * @since 3.0.0
	 */
	private Boolean checkExecuteTime;

	/**
	 * 是否启用全局数据源统计功能
	 *
	 * @since 3.0.0
	 */
	private Boolean useGlobalDataSourceStat;

	/**
	 * SQL 监控日志的记录器名称
	 *
	 * @since 3.0.0
	 */
	private String statLoggerClassName;

	/**
	 * SQL 监控统计中 SQL 语句的最大数量；当超过这个数量时，Druid 会自动清理掉最早的一些 SQL 监控信息，以保持在设定的最大值以内；
	 * 这个设置有助于防止在高并发或长时间运行的情况下，SQL 监控信息占用过多的内存。
	 *
	 * @since 3.0.0
	 */
	private Integer maxSqlSize;

	/**
	 * 是否启用重置统计
	 *
	 * @since 3.0.0
	 */
	private Boolean resetStatEnable;

	/**
	 * 过滤器
	 *
	 * @since 3.0.0
	 */
	private Set<String> filters;

	/**
	 * 是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @since 3.0.0
	 */
	private Boolean loadSpifilterSkip;

	/**
	 * 是否允许清除过滤器
	 *
	 * @since 3.0.0
	 */
	private Boolean clearFiltersEnable;

	/**
	 * @since 3.0.0
	 */
	private Boolean enable;

	private final static Logger logger = LoggerFactory.getLogger(DruidDataSource.class);

	/**
	 * 构造函数
	 */
	public DruidDataSource() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 *
	 * @since 2.3.0
	 */
	public DruidDataSource(String driverClassName, String url) {
		super(driverClassName, url);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 *
	 * @since 2.3.0
	 */
	public DruidDataSource(String driverClassName, String url, String username) {
		super(driverClassName, url, username);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 * @param password
	 * 		数据库密码
	 *
	 * @since 2.3.0
	 */
	public DruidDataSource(String driverClassName, String url, String username, String password) {
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public DruidDataSource(DruidPoolConfiguration poolConfiguration) {
		super(poolConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param poolConfiguration
	 * 		连接池配置
	 *
	 * @since 2.3.0
	 */
	public DruidDataSource(String driverClassName, String url, DruidPoolConfiguration poolConfiguration) {
		super(driverClassName, url, poolConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 * @param poolConfiguration
	 * 		连接池配置
	 *
	 * @since 2.3.0
	 */
	public DruidDataSource(String driverClassName, String url, String username,
						   DruidPoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, poolConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 * @param password
	 * 		数据库密码
	 * @param poolConfiguration
	 * 		连接池配置
	 *
	 * @since 2.3.0
	 */
	public DruidDataSource(String driverClassName, String url, String username, String password,
						   DruidPoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, password, poolConfiguration);
	}

	/**
	 * 返回用户名回调 {@link NameCallback} 类名
	 *
	 * @return 用户名回调 {@link NameCallback} 类名
	 *
	 * @since 3.0.0
	 */
	public String getUserCallbackClassName() {
		return userCallbackClassName;
	}

	/**
	 * 设置用户名回调 {@link NameCallback} 类名
	 *
	 * @param userCallbackClassName
	 * 		用户名回调 {@link NameCallback} 类名
	 *
	 * @since 3.0.0
	 */
	public void setUserCallbackClassName(String userCallbackClassName) {
		this.userCallbackClassName = userCallbackClassName;
	}

	/**
	 * 返回密码回调 {@link PasswordCallback} 类名
	 *
	 * @return 密码回调 {@link PasswordCallback} 类名
	 *
	 * @since 3.0.0
	 */
	public String getPasswordCallbackClassName() {
		return passwordCallbackClassName;
	}

	/**
	 * 设置密码回调 {@link PasswordCallback} 类名
	 *
	 * @param passwordCallbackClassName
	 * 		密码回调 {@link PasswordCallback} 类名
	 *
	 * @since 3.0.0
	 */
	public void setPasswordCallbackClassName(String passwordCallbackClassName) {
		this.passwordCallbackClassName = passwordCallbackClassName;
	}

	/**
	 * 返回连接超时
	 *
	 * @return 连接超时
	 *
	 * @since 3.0.0
	 */
	public Duration getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * 设置连接超时
	 *
	 * @param connectTimeout
	 * 		连接超时
	 *
	 * @since 3.0.0
	 */
	public void setConnectTimeout(Duration connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * 返回网络套接字的读超时
	 *
	 * @return 网络套接字的读超时
	 *
	 * @since 3.0.0
	 */
	public Duration getSocketTimeout() {
		return socketTimeout;
	}

	/**
	 * 设置网络套接字的读超时
	 *
	 * @param socketTimeout
	 * 		网络套接字的读超时
	 *
	 * @since 3.0.0
	 */
	public void setSocketTimeout(Duration socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/**
	 * 返回在发生连接错误后，连接池尝试重新获取连接之间的间隔时间
	 *
	 * @return 在发生连接错误后，连接池尝试重新获取连接之间的间隔时间
	 *
	 * @since 3.0.0
	 */
	public Duration getTimeBetweenConnectError() {
		return timeBetweenConnectError;
	}

	/**
	 * 设置在发生连接错误后，连接池尝试重新获取连接之间的间隔时间
	 *
	 * @param timeBetweenConnectError
	 * 		在发生连接错误后，连接池尝试重新获取连接之间的间隔时间
	 *
	 * @since 3.0.0
	 */
	public void setTimeBetweenConnectError(Duration timeBetweenConnectError) {
		this.timeBetweenConnectError = timeBetweenConnectError;
	}

	/**
	 * 返回数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @return 数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @since 3.0.0
	 */
	public Boolean isKillWhenSocketReadTimeout() {
		return getKillWhenSocketReadTimeout();
	}

	/**
	 * 返回数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @return 数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @since 3.0.0
	 */
	public Boolean getKillWhenSocketReadTimeout() {
		return killWhenSocketReadTimeout;
	}

	/**
	 * 设置数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @param killWhenSocketReadTimeout
	 * 		数据库连接发生 Socket 读取超时时，决定是否立即销毁该连接
	 *
	 * @since 3.0.0
	 */
	public void setKillWhenSocketReadTimeout(Boolean killWhenSocketReadTimeout) {
		this.killWhenSocketReadTimeout = killWhenSocketReadTimeout;
	}

	/**
	 * 返回物理连接的超时时间
	 *
	 * @return 物理连接的超时时间
	 *
	 * @since 3.0.0
	 */
	public Duration getPhyTimeout() {
		return phyTimeout;
	}

	/**
	 * 设置物理连接的超时时间
	 *
	 * @param phyTimeout
	 * 		物理连接的超时时间
	 *
	 * @since 3.0.0
	 */
	public void setPhyTimeout(Duration phyTimeout) {
		this.phyTimeout = phyTimeout;
	}

	/**
	 * 返回物理数据库连接的最大使用次数，设定每个物理连接在被关闭之前最多可以被重复使用的次数
	 *
	 * @return 物理数据库连接的最大使用次数
	 *
	 * @since 3.0.0
	 */
	public Long getPhyMaxUseCount() {
		return phyMaxUseCount;
	}

	/**
	 * 设置物理数据库连接的最大使用次数，设定每个物理连接在被关闭之前最多可以被重复使用的次数
	 *
	 * @param phyMaxUseCount
	 * 		物理数据库连接的最大使用次数，设定每个物理连接在被关闭之前最多可以被重复使用的次数
	 *
	 * @since 3.0.0
	 */
	public void setPhyMaxUseCount(Long phyMaxUseCount) {
		this.phyMaxUseCount = phyMaxUseCount;
	}

	/**
	 * 返回数据源初始化时是否以异步方式进行
	 *
	 * @return 数据源初始化时是否以异步方式进行
	 *
	 * @since 3.0.0
	 */
	public Boolean isAsyncInit() {
		return getAsyncInit();
	}

	/**
	 * 返回数据源初始化时是否以异步方式进行
	 *
	 * @return 数据源初始化时是否以异步方式进行
	 *
	 * @since 3.0.0
	 */
	public Boolean getAsyncInit() {
		return asyncInit;
	}

	/**
	 * 设置数据源初始化时是否以异步方式进行
	 *
	 * @param asyncInit
	 * 		数据源初始化时是否以异步方式进行
	 *
	 * @since 3.0.0
	 */
	public void setAsyncInit(Boolean asyncInit) {
		this.asyncInit = asyncInit;
	}

	/**
	 * 返回是否启用特定的变体或选项
	 *
	 * @return 是否启用特定的变体或选项
	 *
	 * @since 3.0.0
	 */
	public Boolean isInitVariants() {
		return getInitVariants();
	}

	/**
	 * 返回是否启用特定的变体或选项
	 *
	 * @return 是否启用特定的变体或选项
	 *
	 * @since 3.0.0
	 */
	public Boolean getInitVariants() {
		return initVariants;
	}

	/**
	 * 设置是否启用特定的变体或选项
	 *
	 * @param initVariants
	 * 		是否启用特定的变体或选项
	 *
	 * @since 3.0.0
	 */
	public void setInitVariants(Boolean initVariants) {
		this.initVariants = initVariants;
	}

	/**
	 * 返回在连接池初始化时应用的全局变体或选项
	 *
	 * @return 在连接池初始化时应用的全局变体或选项
	 *
	 * @since 3.0.0
	 */
	public Boolean isInitGlobalVariants() {
		return getInitGlobalVariants();
	}

	/**
	 * 返回在连接池初始化时应用的全局变体或选项
	 *
	 * @return 在连接池初始化时应用的全局变体或选项
	 *
	 * @since 3.0.0
	 */
	public Boolean getInitGlobalVariants() {
		return initGlobalVariants;
	}

	/**
	 * 设置在连接池初始化时应用的全局变体或选项
	 *
	 * @param initGlobalVariants
	 * 		在连接池初始化时应用的全局变体或选项
	 *
	 * @since 3.0.0
	 */
	public void setInitGlobalVariants(Boolean initGlobalVariants) {
		this.initGlobalVariants = initGlobalVariants;
	}

	@Override
	public void setInitSQL(String initSQL) {
		super.setInitSQL(initSQL);
		setConnectionInitSqls(ListBuilder.of(initSQL));
	}

	/**
	 * 返回在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @return 在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @since 3.0.0
	 */
	public Collection<String> getConnectionInitSqls() {
		return connectionInitSqls;
	}

	/**
	 * 设置在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @param connectionInitSqls
	 * 		在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @since 3.0.0
	 */
	public void setConnectionInitSqls(Collection<String> connectionInitSqls) {
		this.connectionInitSqls = connectionInitSqls;
	}

	/**
	 * 返回自定义检查数据库连接的有效性类
	 *
	 * @return 自定义检查数据库连接的有效性类
	 *
	 * @since 3.0.0
	 */
	public String getValidConnectionCheckerClassName() {
		return validConnectionCheckerClassName;
	}

	/**
	 * 设置自定义检查数据库连接的有效性类
	 *
	 * @param validConnectionCheckerClassName
	 * 		自定义检查数据库连接的有效性类
	 *
	 * @since 3.0.0
	 */
	public void setValidConnectionCheckerClassName(String validConnectionCheckerClassName) {
		this.validConnectionCheckerClassName = validConnectionCheckerClassName;
	}

	/**
	 * 返回连接出错重试次数
	 *
	 * @return 连接出错重试次数
	 *
	 * @since 3.0.0
	 */
	public Integer getConnectionErrorRetryAttempts() {
		return connectionErrorRetryAttempts;
	}

	/**
	 * 设置连接出错重试次数
	 *
	 * @param connectionErrorRetryAttempts
	 * 		连接出错重试次数
	 *
	 * @since 3.0.0
	 */
	public void setConnectionErrorRetryAttempts(Integer connectionErrorRetryAttempts) {
		this.connectionErrorRetryAttempts = connectionErrorRetryAttempts;
	}

	/**
	 * 返回数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @return 数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @since 3.0.0
	 */
	public Boolean isInitExceptionThrow() {
		return getInitExceptionThrow();
	}

	/**
	 * 返回数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @return 数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @since 3.0.0
	 */
	public Boolean getInitExceptionThrow() {
		return initExceptionThrow;
	}

	/**
	 * 设置数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @param initExceptionThrow
	 * 		数据源初始化过程中出现异常时，是否抛出异常
	 *
	 * @since 3.0.0
	 */
	public void setInitExceptionThrow(Boolean initExceptionThrow) {
		this.initExceptionThrow = initExceptionThrow;
	}

	/**
	 * 异常分类器 {@link com.alibaba.druid.pool.ExceptionSorter} 名称
	 *
	 * @return 异常分类器 {@link com.alibaba.druid.pool.ExceptionSorter} 名称
	 */
	public String getExceptionSorterClassName() {
		return exceptionSorterClassName;
	}

	/**
	 * 设置异常分类器 {@link com.alibaba.druid.pool.ExceptionSorter} 名称
	 *
	 * @param exceptionSorterClassName
	 * 		异常分类器 {@link com.alibaba.druid.pool.ExceptionSorter} 名称
	 */
	public void setExceptionSorterClassName(String exceptionSorterClassName) {
		this.exceptionSorterClassName = exceptionSorterClassName;
	}

	/**
	 * 返回是否启用 Oracle JDBC 驱动的隐式连接缓存
	 *
	 * @return 是否启用 Oracle JDBC 驱动的隐式连接缓存
	 *
	 * @since 3.0.0
	 */
	public Boolean isUseOracleImplicitCache() {
		return getUseOracleImplicitCache();
	}

	/**
	 * 返回是否启用 Oracle JDBC 驱动的隐式连接缓存
	 *
	 * @return 是否启用 Oracle JDBC 驱动的隐式连接缓存
	 *
	 * @since 3.0.0
	 */
	public Boolean getUseOracleImplicitCache() {
		return useOracleImplicitCache;
	}

	/**
	 * 设置是否启用 Oracle JDBC 驱动的隐式连接缓存
	 *
	 * @param useOracleImplicitCache
	 * 		是否启用 Oracle JDBC 驱动的隐式连接缓存
	 *
	 * @since 3.0.0
	 */
	public void setUseOracleImplicitCache(Boolean useOracleImplicitCache) {
		this.useOracleImplicitCache = useOracleImplicitCache;
	}

	/**
	 * 返回是否异步关闭连接
	 *
	 * @return 是否异步关闭连接
	 *
	 * @since 3.0.0
	 */
	public Boolean isAsyncCloseConnectionEnable() {
		return getAsyncCloseConnectionEnable();
	}

	/**
	 * 返回是否异步关闭连接
	 *
	 * @return 是否异步关闭连接
	 *
	 * @since 3.0.0
	 */
	public Boolean getAsyncCloseConnectionEnable() {
		return asyncCloseConnectionEnable;
	}

	/**
	 * 设置是否异步关闭连接
	 *
	 * @param asyncCloseConnectionEnable
	 * 		是否异步关闭连接
	 *
	 * @since 3.0.0
	 */
	public void setAsyncCloseConnectionEnable(Boolean asyncCloseConnectionEnable) {
		this.asyncCloseConnectionEnable = asyncCloseConnectionEnable;
	}

	/**
	 * 返回在事务中执行的 SQL 查询的超时时间
	 *
	 * @return 在事务中执行的 SQL 查询的超时时间
	 *
	 * @since 3.0.0
	 */
	public Duration getTransactionQueryTimeout() {
		return transactionQueryTimeout;
	}

	/**
	 * 设置在事务中执行的 SQL 查询的超时时间
	 *
	 * @param transactionQueryTimeout
	 * 		在事务中执行的 SQL 查询的超时时间
	 *
	 * @since 3.0.0
	 */
	public void setTransactionQueryTimeout(Duration transactionQueryTimeout) {
		this.transactionQueryTimeout = transactionQueryTimeout;
	}

	/**
	 * 返回事务的阈值时间
	 *
	 * @return 事务的阈值时间
	 *
	 * @since 3.0.0
	 */
	public Duration getTransactionThreshold() {
		return transactionThreshold;
	}

	/**
	 * 设置事务的阈值时间
	 *
	 * @param transactionThreshold
	 * 		事务的阈值时间
	 *
	 * @since 3.0.0
	 */
	public void setTransactionThreshold(Duration transactionThreshold) {
		this.transactionThreshold = transactionThreshold;
	}

	/**
	 * 返回是否使用公平锁
	 *
	 * @return 是否使用公平锁
	 *
	 * @since 3.0.0
	 */
	public Boolean isFairLock() {
		return getFairLock();
	}

	/**
	 * 返回是否使用公平锁
	 *
	 * @return 是否使用公平锁
	 *
	 * @since 3.0.0
	 */
	public Boolean getFairLock() {
		return fairLock;
	}

	/**
	 * 设置是否使用公平锁
	 *
	 * @param fairLock
	 * 		是否使用公平锁
	 *
	 * @since 3.0.0
	 */
	public void setFairLock(Boolean fairLock) {
		this.fairLock = fairLock;
	}

	public ScheduledExecutorService getCreateScheduler() {
		return createScheduler;
	}

	public void setCreateScheduler(ScheduledExecutorService createScheduler) {
		this.createScheduler = createScheduler;
	}

	public ScheduledExecutorService getDestroyScheduler() {
		return destroyScheduler;
	}

	public void setDestroyScheduler(ScheduledExecutorService destroyScheduler) {
		this.destroyScheduler = destroyScheduler;
	}

	/**
	 * 返回数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @return 数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @since 3.0.0
	 */
	public Boolean isFailFast() {
		return getFailFast();
	}

	/**
	 * 返回数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @return 数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @since 3.0.0
	 */
	public Boolean getFailFast() {
		return failFast;
	}

	/**
	 * 设置数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @param failFast
	 * 		数据源初始化过程中，如果发生错误，是否立即停止初始化并抛出异常
	 *
	 * @since 3.0.0
	 */
	public void setFailFast(Boolean failFast) {
		this.failFast = failFast;
	}

	/**
	 * 返回是否监控执行时间
	 *
	 * @return 是否监控执行时间
	 *
	 * @since 3.0.0
	 */
	public Boolean isCheckExecuteTime() {
		return getCheckExecuteTime();
	}

	/**
	 * 返回是否监控执行时间
	 *
	 * @return 是否监控执行时间
	 *
	 * @since 3.0.0
	 */
	public Boolean getCheckExecuteTime() {
		return checkExecuteTime;
	}

	/**
	 * 设置是否监控执行时间
	 *
	 * @param checkExecuteTime
	 * 		是否监控执行时间
	 *
	 * @since 3.0.0
	 */
	public void setCheckExecuteTime(Boolean checkExecuteTime) {
		this.checkExecuteTime = checkExecuteTime;
	}

	/**
	 * 返回是否启用全局数据源统计功能
	 *
	 * @return 是否启用全局数据源统计功能
	 *
	 * @since 3.0.0
	 */
	public Boolean isUseGlobalDataSourceStat() {
		return getUseGlobalDataSourceStat();
	}

	/**
	 * 返回是否启用全局数据源统计功能
	 *
	 * @return 是否启用全局数据源统计功能
	 *
	 * @since 3.0.0
	 */
	public Boolean getUseGlobalDataSourceStat() {
		return useGlobalDataSourceStat;
	}

	/**
	 * 设置是否启用全局数据源统计功能
	 *
	 * @param useGlobalDataSourceStat
	 * 		是否启用全局数据源统计功能
	 *
	 * @since 3.0.0
	 */
	public void setUseGlobalDataSourceStat(Boolean useGlobalDataSourceStat) {
		this.useGlobalDataSourceStat = useGlobalDataSourceStat;
	}

	/**
	 * 返回 SQL 监控日志的记录器名称
	 *
	 * @return SQL 监控日志的记录器名称
	 *
	 * @since 3.0.0
	 */
	public String getStatLoggerClassName() {
		return statLoggerClassName;
	}

	/**
	 * 设置 SQL 监控日志的记录器名称
	 *
	 * @param statLoggerClassName
	 * 		SQL 监控日志的记录器名称
	 *
	 * @since 3.0.0
	 */
	public void setStatLoggerClassName(String statLoggerClassName) {
		this.statLoggerClassName = statLoggerClassName;
	}

	/**
	 * 返回 SQL 监控统计中 SQL 语句的最大数量
	 *
	 * @return SQL 监控统计中 SQL 语句的最大数量
	 *
	 * @since 3.0.0
	 */
	public Integer getMaxSqlSize() {
		return maxSqlSize;
	}

	/**
	 * 设置 SQL 监控统计中 SQL 语句的最大数量
	 *
	 * @param maxSqlSize
	 * 		SQL 监控统计中 SQL 语句的最大数量
	 *
	 * @since 3.0.0
	 */
	public void setMaxSqlSize(Integer maxSqlSize) {
		this.maxSqlSize = maxSqlSize;
	}

	/**
	 * 返回是否启用重置统计
	 *
	 * @return 是否启用重置统计
	 *
	 * @since 3.0.0
	 */
	public Boolean isResetStatEnable() {
		return getResetStatEnable();
	}

	/**
	 * 返回是否启用重置统计
	 *
	 * @return 是否启用重置统计
	 *
	 * @since 3.0.0
	 */
	public Boolean getResetStatEnable() {
		return resetStatEnable;
	}

	/**
	 * 设置是否启用重置统计
	 *
	 * @param resetStatEnable
	 * 		是否启用重置统计
	 *
	 * @since 3.0.0
	 */
	public void setResetStatEnable(Boolean resetStatEnable) {
		this.resetStatEnable = resetStatEnable;
	}

	/**
	 * 返回过滤器
	 *
	 * @return 过滤器
	 *
	 * @since 3.0.0
	 */
	public Set<String> getFilters() {
		return filters;
	}

	/**
	 * 设置过滤器
	 *
	 * @param filters
	 * 		过滤器
	 *
	 * @since 3.0.0
	 */
	public void setFilters(Set<String> filters) {
		this.filters = filters;
	}

	/**
	 * 返回是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @return 是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @since 3.0.0
	 */
	public Boolean isLoadSpifilterSkip() {
		return getLoadSpifilterSkip();
	}

	/**
	 * 返回是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @return 是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @since 3.0.0
	 */
	public Boolean getLoadSpifilterSkip() {
		return loadSpifilterSkip;
	}

	/**
	 * 设置是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @param loadSpifilterSkip
	 * 		是否跳过加载 SPI 机制下的 {@link com.alibaba.druid.filter.Filter}
	 *
	 * @since 3.0.0
	 */
	public void setLoadSpifilterSkip(Boolean loadSpifilterSkip) {
		this.loadSpifilterSkip = loadSpifilterSkip;
	}

	/**
	 * 返回是否允许清除过滤器
	 *
	 * @return 是否允许清除过滤器
	 *
	 * @since 3.0.0
	 */
	public Boolean isClearFiltersEnable() {
		return getClearFiltersEnable();
	}

	/**
	 * 返回是否允许清除过滤器
	 *
	 * @return 是否允许清除过滤器
	 *
	 * @since 3.0.0
	 */
	public Boolean getClearFiltersEnable() {
		return clearFiltersEnable;
	}

	/**
	 * 设置是否允许清除过滤器
	 *
	 * @param clearFiltersEnable
	 * 		是否允许清除过滤器
	 *
	 * @since 3.0.0
	 */
	public void setClearFiltersEnable(Boolean clearFiltersEnable) {
		this.clearFiltersEnable = clearFiltersEnable;
	}

	public Boolean isEnable() {
		return getEnable();
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	protected com.alibaba.druid.pool.DruidDataSource createDataSource(final PropertyMapper propertyMapper) {
		final com.alibaba.druid.pool.DruidDataSource dataSource = getFairLock() == null ?
				new com.alibaba.druid.pool.DruidDataSource() : new com.alibaba.druid.pool.DruidDataSource(
				getFairLock());

		propertyMapper.from(this::getDriverClassLoader).to(dataSource::setDriverClassLoader);
		propertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);

		propertyMapper.from(this::getUrl).to(dataSource::setUrl);
		propertyMapper.from(this::getUsername).to(dataSource::setUsername);
		propertyMapper.from(this::getPassword).to(dataSource::setPassword);

		propertyMapper.from(this::getLoginTimeout).as((v)->(int) v.getSeconds()).to(dataSource::setLoginTimeout);

		try{
			if(Validate.hasText(this.getUserCallbackClassName())){
				Class<?> userCallbackClazz = Utils.loadClass(this.getUserCallbackClassName());

				if(userCallbackClazz != null){
					dataSource.setUserCallback((NameCallback) userCallbackClazz.newInstance());
				}else{
					logger.error("load userCallback error : {}", this.getUserCallbackClassName());
				}
			}
		}catch(Exception e){
			logger.error("load userCallback[{}] error : {}", this.getUserCallbackClassName(), e.getMessage());
		}

		try{
			if(Validate.hasText(this.getPasswordCallbackClassName())){
				dataSource.setPasswordCallbackClassName(this.getPasswordCallbackClassName());
			}
		}catch(Exception e){
			logger.error("load passwordCallback[{}] error : {}", this.getPasswordCallbackClassName(), e.getMessage());
		}

		propertyMapper.from(this::getConnectTimeout).as((v)->(int) v.toMillis()).to(dataSource::setConnectTimeout);
		propertyMapper.from(this::getSocketTimeout).as((v)->(int) v.toMillis()).to(dataSource::setSocketTimeout);
		propertyMapper.from(this::getLoginTimeout).as((v)->(int) v.getSeconds()).to(dataSource::setLoginTimeout);

		propertyMapper.from(this::getDefaultCatalog).to(dataSource::setDefaultCatalog);

		propertyMapper.from(this::getConnectionInitSqls).to(dataSource::setConnectionInitSqls);

		propertyMapper.from(this::getValidationQuery).to(dataSource::setValidationQuery);
		propertyMapper.from(this::getValidationQueryTimeout).as((v)->(int) v.getSeconds())
				.to(dataSource::setValidationQueryTimeout);

		propertyMapper.from(this::getQueryTimeout).as((v)->(int) v.getSeconds()).to(dataSource::setQueryTimeout);

		propertyMapper.from(this::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		propertyMapper.from(this::getTransactionQueryTimeout).as((v)->(int) v.getSeconds())
				.to(dataSource::setTransactionQueryTimeout);
		propertyMapper.from(this::getTransactionThreshold).as(Duration::toMillis)
				.to(dataSource::setTransactionThresholdMillis);

		propertyMapper.from(this::isDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(this::isDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);


		propertyMapper.from(this::getTimeBetweenConnectError).as(Duration::toMillis)
				.to(dataSource::setTimeBetweenConnectErrorMillis);
		propertyMapper.from(this::isKillWhenSocketReadTimeout).to(dataSource::setKillWhenSocketReadTimeout);
		propertyMapper.from(this::isAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		propertyMapper.from(this::getPhyTimeout).as(Duration::toMillis).to(dataSource::setPhyTimeoutMillis);
		propertyMapper.from(this::getPhyMaxUseCount).to(dataSource::setPhyMaxUseCount);

		if(Validate.hasText(this.getValidConnectionCheckerClassName())){
			try{
				dataSource.setValidConnectionCheckerClassName(this.getValidConnectionCheckerClassName());
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Set validConnectionChecker error: {}", e.getMessage());
				}
			}
		}

		propertyMapper.from(this::getConnectionErrorRetryAttempts).to(dataSource::setConnectionErrorRetryAttempts);
		propertyMapper.from(this::isInitExceptionThrow).to(dataSource::setInitExceptionThrow);

		if(Validate.hasText(this.getExceptionSorterClassName())){
			try{
				dataSource.setExceptionSorterClassName(this.getExceptionSorterClassName());
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Set exceptionSorter error: {}", e.getMessage());
				}
			}
		}

		propertyMapper.from(this::isAsyncCloseConnectionEnable).to(dataSource::setAsyncCloseConnectionEnable);
		propertyMapper.from(this::isUseOracleImplicitCache).to(dataSource::setUseOracleImplicitCache);
		propertyMapper.from(this::isAsyncInit).to(dataSource::setAsyncInit);

		propertyMapper.from(this::isInitVariants).to(dataSource::setInitVariants);
		propertyMapper.from(this::isInitGlobalVariants).to(dataSource::setInitGlobalVariants);

		propertyMapper.from(this::getCreateScheduler).to(dataSource::setCreateScheduler);
		propertyMapper.from(this::getDestroyScheduler).to(dataSource::setDestroyScheduler);

		propertyMapper.from(this::isFailFast).to(dataSource::setFailFast);
		propertyMapper.from(this::isCheckExecuteTime).to(dataSource::setCheckExecuteTime);
		propertyMapper.from(this::isUseGlobalDataSourceStat).to(dataSource::setUseGlobalDataSourceStat);
		propertyMapper.from(this::getStatLoggerClassName).to(dataSource::setStatLoggerClassName);
		propertyMapper.from(this::isResetStatEnable).to(dataSource::setResetStatEnable);

		propertyMapper.from(this::getConnectionProperties).to(dataSource::setConnectProperties);

		if(Validate.isNotEmpty(this.getFilters())){
			try{
				dataSource.setFilters(StringUtils.join(this.getFilters(), ','));
			}catch(SQLException e){
				if(logger.isErrorEnabled()){
					logger.error("Set filters error: {}(errorCode: {}, sqlState: {})", e.getMessage(), e.getErrorCode(),
							e.getSQLState());
				}
			}
		}

		if(this.getMaxSqlSize() != null){
			dataSource.addConnectionProperty(Constants.DRUID_STAT_SQL_MAX_SIZE, Integer.toString(this.getMaxSqlSize()));
		}

		if(this.isLoadSpifilterSkip() != null){
			System.setProperty("druid.load.spifilter.skip", Boolean.toString(this.getLoadSpifilterSkip()));
		}

		propertyMapper.from(this::isClearFiltersEnable).to(dataSource::setClearFiltersEnable);
		propertyMapper.from(this::isEnable).to(dataSource::setEnable);

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final com.alibaba.druid.pool.DruidDataSource dataSource,
										  final DruidPoolConfiguration poolConfiguration,
										  final PropertyMapper propertyMapper) {
		propertyMapper.from(poolConfiguration::getPoolName).to(dataSource::setName);

		propertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		propertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		propertyMapper.from(poolConfiguration::getMaxActive).to(dataSource::setMaxActive);
		propertyMapper.from(poolConfiguration::getOnFatalErrorMaxActive).to(dataSource::setOnFatalErrorMaxActive);

		propertyMapper.from(poolConfiguration::getMaxWait).as(Duration::toMillis).to(dataSource::setMaxWait);

		propertyMapper.from(poolConfiguration::getMaxCreateTaskCount).to(dataSource::setMaxCreateTaskCount);
		propertyMapper.from(poolConfiguration::getMaxWaitThreadCount).to(dataSource::setMaxWaitThreadCount);
		propertyMapper.from(poolConfiguration::getNotFullTimeoutRetryCount).to(dataSource::setNotFullTimeoutRetryCount);

		propertyMapper.from(poolConfiguration::isTestOnBorrow).to(dataSource::setTestOnBorrow);
		propertyMapper.from(poolConfiguration::isTestOnReturn).to(dataSource::setTestOnReturn);
		propertyMapper.from(poolConfiguration::isTestWhileIdle).to(dataSource::setTestWhileIdle);
		propertyMapper.from(poolConfiguration::getKeepAlive).to(dataSource::setKeepAlive);

		propertyMapper.from(poolConfiguration::getKeepAliveBetweenTime).as(Duration::toMillis)
				.to(dataSource::setKeepAliveBetweenTimeMillis);

		propertyMapper.from(poolConfiguration::isUsePingMethod).to(dataSource::setUsePingMethod);

		propertyMapper.from(poolConfiguration::getMinEvictableIdle).as(Duration::toMillis)
				.to(dataSource::setMinEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::getMaxEvictableIdle).as(Duration::toMillis)
				.to(dataSource::setMaxEvictableIdleTimeMillis);

		propertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		propertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns).as(Duration::toMillis)
				.to(dataSource::setTimeBetweenEvictionRunsMillis);

		propertyMapper.from(poolConfiguration::isKeepConnectionUnderlyingTransactionIsolation)
				.to(dataSource::setKeepConnectionUnderlyingTransactionIsolation);

		propertyMapper.from(poolConfiguration::isBreakAfterAcquireFailure).to(dataSource::setBreakAfterAcquireFailure);

		propertyMapper.from(poolConfiguration::isPoolPreparedStatements).to(dataSource::setPoolPreparedStatements);
		propertyMapper.from(poolConfiguration::isSharePreparedStatements).to(dataSource::setSharePreparedStatements);
		propertyMapper.from(poolConfiguration::getMaxOpenPreparedStatements)
				.to(dataSource::setMaxOpenPreparedStatements);
		propertyMapper.from(poolConfiguration::getMaxPoolPreparedStatementPerConnectionSize)
				.to(dataSource::setMaxPoolPreparedStatementPerConnectionSize);

		propertyMapper.from(poolConfiguration::isRemoveAbandoned).to(dataSource::setRemoveAbandoned);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedTimeout).as(Duration::toMillis)
				.to(dataSource::setRemoveAbandonedTimeoutMillis);
		propertyMapper.from(poolConfiguration::isLogAbandoned).to(dataSource::setLogAbandoned);

		propertyMapper.from(poolConfiguration::isUseLocalSessionState).to(dataSource::setUseLocalSessionState);

		propertyMapper.from(poolConfiguration::getTimeBetweenLogStats).as(Duration::toMillis)
				.to(dataSource::setTimeBetweenLogStatsMillis);
		propertyMapper.from(poolConfiguration::isDupCloseLogEnable).to(dataSource::setDupCloseLogEnable);
		propertyMapper.from(poolConfiguration::isLogDifferentThread).to(dataSource::setLogDifferentThread);

		if(poolConfiguration.getJmx() != null){
			final Jmx jmx = poolConfiguration.getJmx();

			if(Validate.hasText(jmx.getName())){
				try{
					dataSource.setObjectName(new ObjectName(jmx.getName()));
				}catch(MalformedObjectNameException e){
					if(logger.isErrorEnabled()){
						logger.error("create {} instance error: {}", ObjectName.class.getName(), e.getMessage());
					}
				}
			}
		}
	}

}

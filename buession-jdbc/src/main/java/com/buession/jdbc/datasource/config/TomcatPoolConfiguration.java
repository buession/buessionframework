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
package com.buession.jdbc.datasource.config;

import com.buession.jdbc.core.TransactionIsolation;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.time.Duration;

/**
 * Tomcat 数据源连接池配置 {@link PoolProperties}
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class TomcatPoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 连接池名称
	 */
	private String name;

	/**
	 * 为支持 catalog 概念的数据库设置默认 catalog
	 */
	private String defaultCatalog;

	/**
	 * 初始化时建立连接的个数
	 */
	private Integer initialSize;

	/**
	 * 最大连接池数量
	 */
	private Integer maxActive;

	/**
	 * 最小空闲连接数
	 */
	private Integer minIdle;

	/**
	 * 最大空闲连接数
	 */
	private Integer maxIdle;

	/**
	 * 获取连接时最大等待时间，-1 表示不限制
	 */
	private Duration maxWait;

	/**
	 * 以毫秒为单位的时间，以重新建立连接。
	 * 当从池中借用连接时，池将检查 now - time-when-connected > maxAge是否已达到，如果是，则在借用之前重新连接；
	 * 当连接返回到池中时，池将检查 now - time-when-connected > maxAge是否已达到，如果是，则尝试重新连接；
	 * 当连接处于空闲状态并且 timeBetweenEvictionRunsMillis 大于零时，池将定期检查以查看是否 now - time-when-connected >
	 * maxAge 已达到，如果是，则尝试重新连接；
	 * 设置 maxAge 为小于该值 timeBetweenEvictionRunsMillis 将覆盖它（因此，空闲连接验证/清除将更频繁地运行）。
	 * 默认值为0，这意味着连接将保持打开状态，并且从池中借用，将连接返回到池中或检查空闲连接时都不会进行年龄检查
	 */
	private Long maxAge;

	/**
	 * 设置一个SQL语句，在将每个新连接创建后，将其添加到池中之前执行该语句
	 */
	private String initSQL;

	/**
	 * 验证连接使用的 SQL
	 */
	private String validationQuery;

	/**
	 * 验证连接时间间隔
	 */
	private Duration validationInterval;

	/**
	 * 验证SQL的执行超时时间，为负数表示关闭连接验证超时
	 */
	private Duration validationQueryTimeout;

	/**
	 * 实现 {@link org.apache.tomcat.jdbc.pool.Validator} 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证
	 */
	private String validatorClassName;

	/**
	 * 将此属性设置为true可以在验证阶段将错误记录到日志文件中
	 */
	private Boolean logValidationErrors;

	/**
	 * 创建连接时测试连接的有效性
	 */
	private Boolean testOnConnect;

	/**
	 * 从连接池获取一个连接时，验证有效性；
	 * 指明在从池中租借对象时是否要进行验证有效，如果对象验证失败，则对象将从池子释放，然后我们将尝试租借另一个
	 */
	private Boolean testOnBorrow;

	/**
	 * 连接被归还到连接池时，验证有效性
	 */
	private Boolean testOnReturn;

	/**
	 * 连接空闲时，验证有效性；
	 * 指明对象是否需要通过对象驱逐者进行校验（如果有的话），假如一个对象验证失败，则对象将被从池中释放
	 */
	private Boolean testWhileIdle;

	/**
	 * 空闲连接验证/清除线程的运行之间休眠时间
	 */
	private Duration timeBetweenEvictionRuns;

	private Integer numTestsPerEvictionRun;

	/**
	 * 个对象在有资格被驱逐之前可以在池中空闲的最短时间
	 */
	private Duration minEvictableIdleTime;

	/**
	 * 默认事务隔离级别
	 */
	private TransactionIsolation defaultTransactionIsolation = TransactionIsolation.DEFAULT;

	/**
	 * 默认是否自动提交事务
	 */
	private Boolean defaultAutoCommit;

	/**
	 * 连接归还到池时，设置为自动提交
	 */
	private Boolean commitOnReturn;

	/**
	 * 连接归还到池时，是否回滚所有操作
	 */
	private Boolean rollbackOnReturn;

	/**
	 * 默认连接是否是只读模式
	 */
	private Boolean defaultReadOnly;

	/**
	 * 是否移除抛弃的（abandoned）连接，一个连接使用超过了 removeAbandonedTimeout 上限就被视为抛弃的，
	 * 开启该开关可以恢复那些应用没有关闭的连接
	 */
	private Boolean removeAbandoned;

	/**
	 * 一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 */
	private Duration removeAbandonedTimeout;

	/**
	 * 记录抛弃连接的应用的堆栈信息；
	 * 会增加系统开销，因为为了能够在可能发生的连接被抛弃时记录堆栈 ，应用每次获取连接时都需要生成堆栈信息
	 */
	private Boolean logAbandoned;

	/**
	 * 除非正在使用的连接数超过定义的百分比，
	 * 否则已放弃（超时）的连接不会关闭并报告 abandonWhenPercentageFull；
	 * 该值应介于0到100之间。默认值为0，这表示一旦 removeAbandonedTimeout 达到连接就可以关闭连接
	 */
	private Integer abandonWhenPercentageFull;

	/**
	 * 与 removeAbandonedTimeout 值类似，但是不是将连接视为已放弃并可能关，而是将警告（如果 logAbandoned 设置为 true）记录下来。
	 * 如果该值等于或小于0，将不执行任何可疑检查；仅当超时值大于0并且未放弃连接或禁用放弃检查时，才进行可疑检查；
	 * 如果怀疑连接，则记录 WARN 消息，并发送一次 JMX 通知
	 */
	private Duration suspectTimeout = Duration.ofMillis(0L);

	private Boolean alternateUsernameAllowed;

	/**
	 * JDBC 拦截器，{@link org.apache.tomcat.jdbc.pool.JdbcInterceptor} 的实现
	 */
	private String jdbcInterceptors;

	/**
	 * 标记初始化池时是否忽略连接创建错误；如果要在初始化池时忽略连接创建错误，请设置为true
	 */
	private Boolean ignoreExceptionOnPreLoad;

	private Boolean useEquals;

	private Boolean useLock;

	/**
	 * 如果希望以真正的 FIFO 方式公平对待对 getConnection 的调用，则设置为true。
	 * 这将 {@link org.apache.tomcat.jdbc.pool.FairBlockingQueue} 实现用于空闲连接的列表。
	 * 默认值为true。当您要使用异步连接检索时，此标志是必需的；设置此标志可确保线程按到达顺序接收连接。
	 * 在性能测试期间，实现锁和等待锁的方式有很大的不同。
	 * 当 fairQueue=true 存在基于系统在运行什么操作系统的决策过程时。
	 * 如果系统在 Linux 上运行（属性 os.name=Linux）要禁用此Linux特定行为并仍使用公平队列，只需 org.apache.tomcat
	 * .jdbc.pool.FairBlockingQueue.ignoreOS=true 在加载连接池类之前将该属性添加到系统属性中即可
	 */
	private Boolean fairQueue;

	/**
	 * 如果希望在连接上放置外观，则将其设置为true；
	 * 以使其在关闭后无法重复使用；这样可以防止线程保留已调用的已关闭连接的引用，以对其执行查询
	 */
	private Boolean useDisposableConnectionFacade;

	/**
	 * 将此值设置为true可以传播已被中断的线程的中断状态（不清除中断状态）
	 */
	private Boolean propagateInterruptState;

	/**
	 * 如果希望包装语句以便启用，equals()并且hashCode()在设置了任何语句代理的情况下在关闭的语句上调用方法，请将其设置为true
	 */
	private Boolean useStatementFacade;

	private Boolean accessToUnderlyingConnectionAllowed;

	/**
	 * 是否启用 JMX
	 */
	private Boolean jmxEnabled;

	/**
	 * 返回连接池名称
	 *
	 * @return 连接池名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置连接池名称
	 *
	 * @param name
	 * 		连接池名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 返回为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @return 为支持 catalog 概念的数据库设置默认 catalog
	 */
	public String getDefaultCatalog(){
		return defaultCatalog;
	}

	/**
	 * 设置为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @param defaultCatalog
	 * 		为支持 catalog 概念的数据库设置默认 catalog
	 */
	public void setDefaultCatalog(String defaultCatalog){
		this.defaultCatalog = defaultCatalog;
	}

	/**
	 * 返回初始连接数
	 *
	 * @return 初始连接数
	 */
	public Integer getInitialSize(){
		return initialSize;
	}

	/**
	 * 设置初始连接数
	 *
	 * @param initialSize
	 * 		初始连接数
	 */
	public void setInitialSize(Integer initialSize){
		this.initialSize = initialSize;
	}

	/**
	 * 返回最大连接池数量
	 *
	 * @return 最大连接池数量
	 */
	public Integer getMaxActive(){
		return maxActive;
	}

	/**
	 * 设置最大连接池数量
	 *
	 * @param maxActive
	 * 		最大连接池数量
	 */
	public void setMaxActive(Integer maxActive){
		this.maxActive = maxActive;
	}

	/**
	 * 返回最小空闲连接数
	 *
	 * @return 最小空闲连接数
	 */
	public Integer getMinIdle(){
		return minIdle;
	}

	/**
	 * 设置最小空闲连接数
	 *
	 * @param minIdle
	 * 		最小空闲连接数
	 */
	public void setMinIdle(Integer minIdle){
		this.minIdle = minIdle;
	}

	/**
	 * 返回最大空闲连接数
	 *
	 * @return 最大空闲连接数
	 */
	public Integer getMaxIdle(){
		return maxIdle;
	}

	/**
	 * 设置空闲连接数
	 *
	 * @param maxIdle
	 * 		最大空闲连接数
	 */
	public void setMaxIdle(Integer maxIdle){
		this.maxIdle = maxIdle;
	}

	/**
	 * 返回获取连接时最大等待时间
	 *
	 * @return 获取连接时最大等待时间
	 */
	public Duration getMaxWait(){
		return maxWait;
	}

	/**
	 * 设置获取连接时最大等待时间
	 *
	 * @param maxWait
	 * 		获取连接时最大等待时间
	 */
	public void setMaxWait(Duration maxWait){
		this.maxWait = maxWait;
	}

	public Long getMaxAge(){
		return maxAge;
	}

	public void setMaxAge(Long maxAge){
		this.maxAge = maxAge;
	}

	/**
	 * 返回在将每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @return 每个新连接创建后，将其添加到池中之前执行的SQL语句
	 */
	public String getInitSQL(){
		return initSQL;
	}

	/**
	 * 设置每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @param initSQL
	 * 		每个新连接创建后，将其添加到池中之前执行的SQL语句
	 */
	public void setInitSQL(String initSQL){
		this.initSQL = initSQL;
	}

	/**
	 * 返回验证连接使用的 SQL
	 *
	 * @return 验证连接使用的 SQL
	 */
	public String getValidationQuery(){
		return validationQuery;
	}

	/**
	 * 设置验证连接使用的 SQL
	 *
	 * @param validationQuery
	 * 		验证连接使用的 SQL
	 */
	public void setValidationQuery(String validationQuery){
		this.validationQuery = validationQuery;
	}

	/**
	 * 返回验证连接时间间隔
	 *
	 * @return 验证连接时间间隔
	 */
	public Duration getValidationInterval(){
		return validationInterval;
	}

	/**
	 * 设置验证连接时间间隔
	 *
	 * @param validationInterval
	 * 		验证连接时间间隔
	 */
	public void setValidationInterval(Duration validationInterval){
		this.validationInterval = validationInterval;
	}

	/**
	 * 返回验证SQL的执行超时时间，为负数表示关闭连接验证超时
	 *
	 * @return 验证SQL的执行超时时间
	 */
	public Duration getValidationQueryTimeout(){
		return validationQueryTimeout;
	}

	/**
	 * 设置验证SQL的执行超时时间，为负数表示关闭连接验证超时
	 *
	 * @param validationQueryTimeout
	 * 		验证SQL的执行超时时间
	 */
	public void setValidationQueryTimeout(Duration validationQueryTimeout){
		this.validationQueryTimeout = validationQueryTimeout;
	}

	/**
	 * 返回实现 org.apache.tomcat.jdbc.pool.Validator 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 *
	 * @return 实现 org.apache.tomcat.jdbc.pool.Validator 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 */
	public String getValidatorClassName(){
		return validatorClassName;
	}

	/**
	 * 设置实现 org.apache.tomcat.jdbc.pool.Validator 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 *
	 * @param validatorClassName
	 * 		实现 org.apache.tomcat.jdbc.pool.Validator 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 */
	public void setValidatorClassName(String validatorClassName){
		this.validatorClassName = validatorClassName;
	}

	/**
	 * 返回验证阶段将错误记录到日志文件中
	 *
	 * @return 验证阶段将错误记录到日志文件中
	 */
	@Deprecated
	public Boolean isLogValidationErrors(){
		return getLogValidationErrors();
	}

	/**
	 * 返回验证阶段将错误记录到日志文件中
	 *
	 * @return 验证阶段将错误记录到日志文件中
	 */
	public Boolean getLogValidationErrors(){
		return logValidationErrors;
	}

	/**
	 * 设置验证阶段将错误记录到日志文件中
	 *
	 * @param logValidationErrors
	 * 		验证阶段将错误记录到日志文件中
	 */
	public void setLogValidationErrors(Boolean logValidationErrors){
		this.logValidationErrors = logValidationErrors;
	}

	/**
	 * 返回创建连接时是否测试连接的有效性
	 *
	 * @return 创建连接时是否测试连接的有效性
	 */
	@Deprecated
	public Boolean isTestOnConnect(){
		return getTestOnConnect();
	}

	/**
	 * 返回创建连接时是否测试连接的有效性
	 *
	 * @return 创建连接时是否测试连接的有效性
	 */
	public Boolean getTestOnConnect(){
		return testOnConnect;
	}

	/**
	 * 设置创建连接时是否测试连接的有效性
	 *
	 * @param testOnConnect
	 * 		创建连接时是否测试连接的有效性
	 */
	public void setTestOnConnect(Boolean testOnConnect){
		this.testOnConnect = testOnConnect;
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	@Deprecated
	public Boolean isTestOnBorrow(){
		return getTestOnBorrow();
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	public Boolean getTestOnBorrow(){
		return testOnBorrow;
	}

	/**
	 * 设置从连接池获取一个连接时，是否验证有效性
	 *
	 * @param testOnBorrow
	 * 		从连接池获取一个连接时，是否验证有效性
	 */
	public void setTestOnBorrow(Boolean testOnBorrow){
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * 返回连接被归还到连接池时，是否验证有效性
	 *
	 * @return 连接被归还到连接池时，是否验证有效性
	 */
	@Deprecated
	public Boolean isTestOnReturn(){
		return getTestOnReturn();
	}

	/**
	 * 返回连接被归还到连接池时，是否验证有效性
	 *
	 * @return 连接被归还到连接池时，是否验证有效性
	 */
	public Boolean getTestOnReturn(){
		return testOnReturn;
	}

	/**
	 * 设置连接被归还到连接池时，是否验证有效性
	 *
	 * @param testOnReturn
	 * 		连接被归还到连接池时，是否验证有效性
	 */
	@Deprecated
	public void setTestOnReturn(Boolean testOnReturn){
		this.testOnReturn = testOnReturn;
	}

	/**
	 * 返回连接空闲时，是否验证有效性
	 *
	 * @return 连接空闲时，是否验证有效性
	 */
	@Deprecated
	public Boolean isTestWhileIdle(){
		return getTestWhileIdle();
	}

	/**
	 * 返回连接空闲时，是否验证有效性
	 *
	 * @return 连接空闲时，是否验证有效性
	 */
	public Boolean getTestWhileIdle(){
		return testWhileIdle;
	}

	/**
	 * 设置连接空闲时，是否验证有效性
	 *
	 * @param testWhileIdle
	 * 		连接空闲时，是否验证有效性
	 */
	public void setTestWhileIdle(Boolean testWhileIdle){
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * 返回空闲连接验证/清除线程的运行之间休眠时间
	 *
	 * @return 空闲连接验证/清除线程的运行之间休眠时间
	 */
	public Duration getTimeBetweenEvictionRuns(){
		return timeBetweenEvictionRuns;
	}

	/**
	 * 设置空闲连接验证/清除线程的运行之间休眠时间
	 *
	 * @param timeBetweenEvictionRuns
	 * 		空闲连接验证/清除线程的运行之间休眠时间
	 */
	public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns){
		this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
	}

	public Integer getNumTestsPerEvictionRun(){
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun){
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	/**
	 * 返回一个对象在有资格被驱逐之前可以在池中空闲的最短时间
	 *
	 * @return 一个对象在有资格被驱逐之前可以在池中空闲的最短时间
	 */
	public Duration getMinEvictableIdleTime(){
		return minEvictableIdleTime;
	}

	/**
	 * 设置一个对象在有资格被驱逐之前可以在池中空闲的最短时间
	 *
	 * @param minEvictableIdleTime
	 * 		一个对象在有资格被驱逐之前可以在池中空闲的最短时间
	 */
	public void setMinEvictableIdleTime(Duration minEvictableIdleTime){
		this.minEvictableIdleTime = minEvictableIdleTime;
	}

	/**
	 * 返回默认事务隔离级别
	 *
	 * @return 默认事务隔离级别
	 */
	public TransactionIsolation getDefaultTransactionIsolation(){
		return defaultTransactionIsolation;
	}

	/**
	 * 设置默认事务隔离级别
	 *
	 * @param defaultTransactionIsolation
	 * 		默认事务隔离级别
	 */
	public void setDefaultTransactionIsolation(TransactionIsolation defaultTransactionIsolation){
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 */
	@Deprecated
	public Boolean isCommitOnReturn(){
		return getCommitOnReturn();
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 */
	public Boolean getCommitOnReturn(){
		return commitOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否自动提交
	 *
	 * @param commitOnReturn
	 * 		连接归还到池时，是否自动提交
	 */
	public void setCommitOnReturn(Boolean commitOnReturn){
		this.commitOnReturn = commitOnReturn;
	}

	/**
	 * 返回连接归还到池时，是否回滚所有操作
	 *
	 * @return 连接归还到池时，是否回滚所有操作
	 */
	@Deprecated
	public Boolean isRollbackOnReturn(){
		return getRollbackOnReturn();
	}

	/**
	 * 返回连接归还到池时，是否回滚所有操作
	 *
	 * @return 连接归还到池时，是否回滚所有操作
	 */
	public Boolean getRollbackOnReturn(){
		return rollbackOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否回滚所有操作
	 *
	 * @param rollbackOnReturn
	 * 		连接归还到池时，是否回滚所有操作
	 */
	public void setRollbackOnReturn(Boolean rollbackOnReturn){
		this.rollbackOnReturn = rollbackOnReturn;
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	@Deprecated
	public Boolean isDefaultAutoCommit(){
		return getDefaultAutoCommit();
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	public Boolean getDefaultAutoCommit(){
		return defaultAutoCommit;
	}

	/**
	 * 设置是否自动提交事务
	 *
	 * @param defaultAutoCommit
	 * 		是否自动提交事务
	 */
	public void setDefaultAutoCommit(Boolean defaultAutoCommit){
		this.defaultAutoCommit = defaultAutoCommit;
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	@Deprecated
	public Boolean isDefaultReadOnly(){
		return getDefaultReadOnly();
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	public Boolean getDefaultReadOnly(){
		return defaultReadOnly;
	}

	/**
	 * 设置连接是否是只读模式
	 *
	 * @param defaultReadOnly
	 * 		连接是否是只读模式
	 */
	public void setDefaultReadOnly(Boolean defaultReadOnly){
		this.defaultReadOnly = defaultReadOnly;
	}

	/**
	 * 返回是否移除抛弃的（abandoned）连接
	 *
	 * @return 是否移除抛弃的（abandoned）连接
	 */
	@Deprecated
	public Boolean isRemoveAbandoned(){
		return getRemoveAbandoned();
	}

	/**
	 * 返回是否移除抛弃的（abandoned）连接
	 *
	 * @return 是否移除抛弃的（abandoned）连接
	 */
	public Boolean getRemoveAbandoned(){
		return removeAbandoned;
	}

	/**
	 * 设置是否移除抛弃的（abandoned）连接
	 *
	 * @param removeAbandoned
	 * 		是否移除抛弃的（abandoned）连接
	 */
	public void setRemoveAbandoned(Boolean removeAbandoned){
		this.removeAbandoned = removeAbandoned;
	}

	/**
	 * 返回一个连接使用超过多久就视为抛弃的
	 *
	 * @return 一个连接使用超过多久就视为抛弃的
	 */
	public Duration getRemoveAbandonedTimeout(){
		return removeAbandonedTimeout;
	}

	/**
	 * 设置一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 *
	 * @param removeAbandonedTimeout
	 * 		一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 */
	public void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout){
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	/**
	 * 返回是否记录抛弃连接的应用的堆栈信息
	 *
	 * @return 是否记录抛弃连接的应用的堆栈信息
	 */
	@Deprecated
	public Boolean isLogAbandoned(){
		return getLogAbandoned();
	}

	/**
	 * 返回是否记录抛弃连接的应用的堆栈信息
	 *
	 * @return 是否记录抛弃连接的应用的堆栈信息
	 */
	public Boolean getLogAbandoned(){
		return logAbandoned;
	}

	/**
	 * 设置是否记录抛弃连接的应用的堆栈信息
	 *
	 * @param logAbandoned
	 * 		是否记录抛弃连接的应用的堆栈信息
	 */
	public void setLogAbandoned(Boolean logAbandoned){
		this.logAbandoned = logAbandoned;
	}

	/**
	 * 返回正在使用的连接数超过定义的百分比
	 *
	 * @return 正在使用的连接数超过定义的百分比
	 */
	public Integer getAbandonWhenPercentageFull(){
		return abandonWhenPercentageFull;
	}

	/**
	 * 设置正在使用的连接数超过定义的百分比
	 *
	 * @param abandonWhenPercentageFull
	 * 		正在使用的连接数超过定义的百分比
	 */
	public void setAbandonWhenPercentageFull(Integer abandonWhenPercentageFull){
		this.abandonWhenPercentageFull = abandonWhenPercentageFull;
	}

	/**
	 * 超时
	 *
	 * @return 超时
	 */
	public Duration getSuspectTimeout(){
		return suspectTimeout;
	}

	/**
	 * 超时
	 *
	 * @param suspectTimeout
	 * 		超时
	 */
	public void setSuspectTimeout(Duration suspectTimeout){
		this.suspectTimeout = suspectTimeout;
	}

	@Deprecated
	public Boolean isAlternateUsernameAllowed(){
		return getAlternateUsernameAllowed();
	}

	public Boolean getAlternateUsernameAllowed(){
		return alternateUsernameAllowed;
	}

	public void setAlternateUsernameAllowed(Boolean alternateUsernameAllowed){
		this.alternateUsernameAllowed = alternateUsernameAllowed;
	}

	/**
	 * 返回 JDBC 拦截器，{@link org.apache.tomcat.jdbc.pool.JdbcInterceptor} 的实现
	 *
	 * @return JDBC 拦截器
	 */
	public String getJdbcInterceptors(){
		return jdbcInterceptors;
	}

	/**
	 * 设置 JDBC 拦截器，{@link org.apache.tomcat.jdbc.pool.JdbcInterceptor} 的实现
	 *
	 * @param jdbcInterceptors
	 * 		JDBC 拦截器
	 */
	public void setJdbcInterceptors(String jdbcInterceptors){
		this.jdbcInterceptors = jdbcInterceptors;
	}

	/**
	 * 返回标记初始化池时是否忽略连接创建错误
	 *
	 * @return 初始化池时是否忽略连接创建错误
	 */
	@Deprecated
	public Boolean isIgnoreExceptionOnPreLoad(){
		return getIgnoreExceptionOnPreLoad();
	}

	/**
	 * 返回标记初始化池时是否忽略连接创建错误
	 *
	 * @return 初始化池时是否忽略连接创建错误
	 */
	public Boolean getIgnoreExceptionOnPreLoad(){
		return ignoreExceptionOnPreLoad;
	}

	/**
	 * 设置标记初始化池时是否忽略连接创建错误；如果要在初始化池时忽略连接创建错误，请设置为true
	 *
	 * @param ignoreExceptionOnPreLoad
	 * 		初始化池时是否忽略连接创建错误
	 */
	public void setIgnoreExceptionOnPreLoad(Boolean ignoreExceptionOnPreLoad){
		this.ignoreExceptionOnPreLoad = ignoreExceptionOnPreLoad;
	}

	@Deprecated
	public Boolean isUseEquals(){
		return getUseEquals();
	}

	public Boolean getUseEquals(){
		return useEquals;
	}

	public void setUseEquals(Boolean useEquals){
		this.useEquals = useEquals;
	}

	@Deprecated
	public Boolean isUseLock(){
		return getUseLock();
	}

	public Boolean getUseLock(){
		return useLock;
	}

	public void setUseLock(Boolean useLock){
		this.useLock = useLock;
	}

	/**
	 * 返回是否以真正的 FIFO 方式公平对待对 getConnection 的调用
	 *
	 * @return 是否以真正的 FIFO 方式公平对待对 getConnection 的调用
	 */
	@Deprecated
	public Boolean isFairQueue(){
		return getFairQueue();
	}

	/**
	 * 返回是否以真正的 FIFO 方式公平对待对 getConnection 的调用
	 *
	 * @return 是否以真正的 FIFO 方式公平对待对 getConnection 的调用
	 */
	public Boolean getFairQueue(){
		return fairQueue;
	}

	/**
	 * 设置是否以真正的 FIFO 方式公平对待对 getConnection 的调用
	 *
	 * @param fairQueue
	 * 		是否以真正的 FIFO 方式公平对待对 getConnection 的调用
	 */
	public void setFairQueue(Boolean fairQueue){
		this.fairQueue = fairQueue;
	}

	/**
	 * 返回希望在连接上放置外观
	 *
	 * @return 希望在连接上放置外观
	 */
	@Deprecated
	public Boolean isUseDisposableConnectionFacade(){
		return getUseDisposableConnectionFacade();
	}

	/**
	 * 返回希望在连接上放置外观
	 *
	 * @return 希望在连接上放置外观
	 */
	public boolean getUseDisposableConnectionFacade(){
		return useDisposableConnectionFacade;
	}

	/**
	 * 设置希望在连接上放置外观，则将其设置为true，以使其在关闭后无法重复使用；
	 * 这样可以防止线程保留已调用的已关闭连接的引用
	 *
	 * @param useDisposableConnectionFacade
	 * 		希望在连接上放置外观
	 */
	public void setUseDisposableConnectionFacade(Boolean useDisposableConnectionFacade){
		this.useDisposableConnectionFacade = useDisposableConnectionFacade;
	}

	/**
	 * 返回是否传播已被中断的线程的中断状态
	 *
	 * @return 是否传播已被中断的线程的中断状态
	 */
	@Deprecated
	public Boolean isPropagateInterruptState(){
		return getPropagateInterruptState();
	}

	/**
	 * 返回是否传播已被中断的线程的中断状态
	 *
	 * @return 是否传播已被中断的线程的中断状态
	 */
	public Boolean getPropagateInterruptState(){
		return propagateInterruptState;
	}

	/**
	 * 设置是否传播已被中断的线程的中断状态
	 *
	 * @param propagateInterruptState
	 * 		是否传播已被中断的线程的中断状态
	 */
	public void setPropagateInterruptState(Boolean propagateInterruptState){
		this.propagateInterruptState = propagateInterruptState;
	}

	/**
	 * 返回是否希望包装语句以便启用，equals()
	 *
	 * @return 是否希望包装语句以便启用，equals()
	 */
	@Deprecated
	public Boolean isUseStatementFacade(){
		return getUseStatementFacade();
	}

	/**
	 * 返回是否希望包装语句以便启用，equals()
	 *
	 * @return 是否希望包装语句以便启用，equals()
	 */
	public boolean getUseStatementFacade(){
		return useStatementFacade;
	}

	/**
	 * 设置是否希望包装语句以便启用，equals()
	 *
	 * @param useStatementFacade
	 * 		是否希望包装语句以便启用，equals()
	 */
	public void setUseStatementFacade(Boolean useStatementFacade){
		this.useStatementFacade = useStatementFacade;
	}

	@Deprecated
	public Boolean isAccessToUnderlyingConnectionAllowed(){
		return getAccessToUnderlyingConnectionAllowed();
	}

	public Boolean getAccessToUnderlyingConnectionAllowed(){
		return accessToUnderlyingConnectionAllowed;
	}

	public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed){
		this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
	}

	/**
	 * 返回是否启用 JMX
	 *
	 * @return 是否启用 JMX
	 */
	@Deprecated
	public Boolean isJmxEnabled(){
		return getJmxEnabled();
	}

	/**
	 * 返回是否启用 JMX
	 *
	 * @return 是否启用 JMX
	 */
	public Boolean getJmxEnabled(){
		return jmxEnabled;
	}

	/**
	 * 设置是否启用 JMX
	 *
	 * @param jmxEnabled
	 * 		是否启用 JMX
	 */
	public void setJmxEnabled(Boolean jmxEnabled){
		this.jmxEnabled = jmxEnabled;
	}

}

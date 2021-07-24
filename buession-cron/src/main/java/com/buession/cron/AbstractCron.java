/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.cron;

import java.util.Date;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public abstract class AbstractCron implements Cron {

	protected final static long DEFAULT_SLEEP_MILLISECONDS = 60000L;

	private long sleepMilliseconds = DEFAULT_SLEEP_MILLISECONDS;

	private final static Logger logger = LoggerFactory.getLogger(AbstractCron.class);

	public long getSleepMilliseconds(){
		return sleepMilliseconds;
	}

	public void setSleepMilliseconds(long sleepMilliseconds){
		this.sleepMilliseconds = sleepMilliseconds;
	}

	protected void run(final Set<Job> jobs) throws SchedulerException{
		if(jobs == null){
			logger.debug("Jobs is null.");
			return;
		}

		logger.debug("job size: {}", jobs.size());

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		for(Job job : jobs){
			execute(scheduler, job);
			logger.debug("Register job {} [{}]", job.getName(), job.getClass().getName());
		}

		scheduler.start();

		try{
			long sleepMilliseconds = getSleepMilliseconds();
			Thread.sleep(sleepMilliseconds <= 0 ? DEFAULT_SLEEP_MILLISECONDS : sleepMilliseconds);
		}catch(InterruptedException e){
			logger.error("Thread sleep execute failure: {}", e.getMessage());
		}

		// scheduler.shutdown(true);
	}

	private static void execute(final Scheduler scheduler, final Job job) throws SchedulerException{
		JobDetail jobDetail = JobBuilder.newJob(job.getClazz()).withIdentity(job.getName(), job.getGroup()).build();
		// 设置作业
		CronTrigger trigger =
				TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup()).withSchedule(CronScheduleBuilder.cronSchedule(job.getExpression())).build(); // 设置触发器
		Date ft = scheduler.scheduleJob(jobDetail, trigger); // 设置调度作业

		if(logger.isDebugEnabled()){
			logger.debug("{} has been scheduled to run at: {} and repeat based on expression: {}", jobDetail.getKey(),
					ft, trigger.getCronExpression());
		}
	}

}
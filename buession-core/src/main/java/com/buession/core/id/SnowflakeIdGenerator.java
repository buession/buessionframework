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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.id;

import com.buession.core.utils.Assert;
import com.buession.core.utils.RandomUtils;
import com.buession.core.utils.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * 雪花算法 ID 生成器
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public class SnowflakeIdGenerator implements IdGenerator<Long> {

	/**
	 * 开始时间截，2000-01-01 00:00:00
	 */
	private final static long START_TIMESTAMP = TimeUnit.MILLISECONDS.toSeconds(1577808000000L);

	/**
	 * 默认时间所占的位数
	 */
	private final static int TIME_BITS = 32;

	/**
	 * 默认数据中心id所占的位数
	 */
	private final static int DATACENTER_BITS = 9;

	/**
	 * 默认机器id所占的位数
	 */
	private final static int WORKER_BITS = 9;

	/**
	 * 默认序列id中占的位数
	 */
	private final static int SEQUENCE_BITS = 13;

	/**
	 * 时间所占的位数
	 */
	protected int timeBits = TIME_BITS;

	/**
	 * 数据中心id所占的位数
	 */
	protected int datacenterBits = DATACENTER_BITS;

	/**
	 * 机器id所占的位数
	 */
	protected int workerBits = WORKER_BITS;

	/**
	 * 序列id中占的位数
	 */
	protected int sequenceBits = SEQUENCE_BITS;

	private BitsAllocator bitsAllocator;

	/**
	 * 数据中心ID(0~31)
	 */
	private long datacenterId;

	/**
	 * 工作机器ID(0~31)
	 */
	private long workerId;

	/**
	 * 毫秒内序列(0~4095)
	 */
	private long sequence = 0L;

	/**
	 * 上次生成ID的时间截
	 */
	private long lastTimestamp = -1L;

	/**
	 * 构造函数，使用 IPv4 A、B 段作为 datacenterId，C、D 段作为 workerId
	 */
	public SnowflakeIdGenerator() {
		this(TIME_BITS, DATACENTER_BITS, WORKER_BITS, SEQUENCE_BITS);
	}

	/**
	 * 构造函数，使用 IPv4 A、B 段作为 datacenterId，C、D 段作为 workerId
	 *
	 * @param timeBits
	 * 		时间所占的位数
	 * @param datacenterBits
	 * 		数据中心id所占的位数
	 * @param workerBits
	 * 		机器id所占的位数
	 * @param sequenceBits
	 * 		序列id中占的位数
	 */
	public SnowflakeIdGenerator(final int timeBits, final int datacenterBits, final int workerBits,
								final int sequenceBits) {
		initialize(timeBits, datacenterBits, workerBits, sequenceBits);

		try{
			String address = InetAddress.getLocalHost().getHostAddress();
			String[] addrs = StringUtils.split(address, ".");

			this.datacenterId = Long.parseLong(addrs[0] + addrs[1]);
			this.workerId = Long.parseLong(addrs[2] + addrs[3]);
		}catch(UnknownHostException e){
			//
		}

		if(this.datacenterId == 0L){
			this.datacenterId = RandomUtils.nextInt(4);
		}

		if(this.workerId == 0L){
			this.workerId = RandomUtils.nextInt(8);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param datacenterId
	 * 		数据中心ID
	 * @param workerId
	 * 		工作机器ID
	 */
	public SnowflakeIdGenerator(final long datacenterId, final long workerId) {
		this(datacenterId, workerId, TIME_BITS, DATACENTER_BITS, WORKER_BITS, SEQUENCE_BITS);
	}

	/**
	 * 构造函数
	 *
	 * @param datacenterId
	 * 		数据中心ID
	 * @param workerId
	 * 		工作机器ID
	 * @param timeBits
	 * 		时间所占的位数
	 * @param datacenterBits
	 * 		数据中心id所占的位数
	 * @param workerBits
	 * 		机器id所占的位数
	 * @param sequenceBits
	 * 		序列id中占的位数
	 */
	public SnowflakeIdGenerator(final long datacenterId, final long workerId, final int timeBits,
								final int datacenterBits, final int workerBits, final int sequenceBits) {
		initialize(timeBits, datacenterBits, workerBits, sequenceBits);

		this.datacenterId = datacenterId;
		this.workerId = workerId;
	}

	@Override
	public synchronized Long nextId() {
		long currentTimestamp = getCurrentTimestamp();

		//如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if(currentTimestamp < lastTimestamp){
			throw new IdGenerateException(
					String.format("Clock moved backwards. Refusing for %d seconds", lastTimestamp - currentTimestamp));
		}

		//如果是同一时间生成的，则进行毫秒内序列
		if(lastTimestamp == currentTimestamp){
			sequence = (sequence + 1) & bitsAllocator.getMaxSequence();
			//毫秒内序列溢出
			if(sequence == 0){
				//阻塞到下一个毫秒,获得新的时间戳
				currentTimestamp = getNextTimestamp(lastTimestamp);
			}
		}
		//时间戳改变，毫秒内序列重置
		else{
			sequence = 0L;
		}

		//上次生成ID的时间截
		lastTimestamp = currentTimestamp;

		//移位并通过或运算拼到一起组成64位的ID
		return bitsAllocator.allocate(currentTimestamp - START_TIMESTAMP, datacenterId, workerId, sequence);
	}

	private void initialize(final int timeBits, final int datacenterBits, final int workerBits,
							final int sequenceBits) {
		if(timeBits > 0){
			this.timeBits = timeBits;
		}

		if(datacenterBits > 0){
			this.datacenterBits = datacenterBits;
		}

		if(workerBits > 0){
			this.workerBits = workerBits;
		}

		if(sequenceBits > 0){
			this.sequenceBits = sequenceBits;
		}

		this.bitsAllocator = new BitsAllocator(timeBits, datacenterBits, workerBits, sequenceBits);
	}

	private long getCurrentTimestamp() {
		long currentTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		if(currentTimestamp - START_TIMESTAMP > bitsAllocator.getMaxDeltaSeconds()){
			throw new IdGenerateException(
					"Timestamp bits is exhausted. Refusing ID generate. Now: " + currentTimestamp);
		}

		return currentTimestamp;
	}

	private long getNextTimestamp(long lastTimestamp) {
		long timestamp = getCurrentTimestamp();
		while(timestamp <= lastTimestamp){
			timestamp = getCurrentTimestamp();
		}

		return timestamp;
	}

	private final static class BitsAllocator {

		/**
		 * Total 64 bits
		 */
		public final static int TOTAL_BITS = 1 << 6;

		/**
		 * Bits for [sign-> second-> datacenterId -> workId -> sequence]
		 */
		private final int signBits = 1;

		private final int timestampBits;

		private final int datacenterIdBits;

		private final int workerIdBits;

		private final int sequenceBits;

		/**
		 * Max value for workId & sequence
		 */
		private final long maxDeltaSeconds;

		private final long maxDatacenterId;

		private final long maxWorkerId;

		private final long maxSequence;

		/**
		 * Shift for timestamp & workerId
		 */
		private final int timestampShift;

		private final int datacenterIdShift;

		private final int workerIdShift;

		BitsAllocator(final int timestampBits, final int datacenterIdBits, final int workerIdBits,
					  final int sequenceBits) {
			// make sure allocated 64 bits
			int allocateTotalBits = signBits + timestampBits + datacenterIdBits + workerIdBits + sequenceBits;
			Assert.isFalse(allocateTotalBits == TOTAL_BITS,
					"allocate not enough " + TOTAL_BITS + " bits, now: " + allocateTotalBits + " bits.");

			// initialize bits
			this.timestampBits = timestampBits;
			this.datacenterIdBits = datacenterIdBits;
			this.workerIdBits = workerIdBits;
			this.sequenceBits = sequenceBits;

			// initialize max value
			this.maxDeltaSeconds = ~(-1L << timestampBits);
			this.maxDatacenterId = ~(-1L << datacenterIdBits);
			this.maxWorkerId = ~(-1L << workerIdBits);
			this.maxSequence = ~(-1L << sequenceBits);

			// initialize shift
			this.timestampShift = datacenterIdBits + workerIdBits + sequenceBits;
			this.datacenterIdShift = workerIdBits + sequenceBits;
			this.workerIdShift = sequenceBits;
		}

		public long allocate(final long deltaSeconds, final long datacenterId, final long workerId,
							 final long sequence) {
			return (deltaSeconds << timestampShift) | (datacenterId << datacenterIdShift) |
					(workerId << workerIdShift) | sequence;
		}

		public int getSignBits() {
			return signBits;
		}

		public int getTimestampBits() {
			return timestampBits;
		}

		public int getDatacenterIdBits() {
			return datacenterIdBits;
		}

		public int getWorkerIdBits() {
			return workerIdBits;
		}

		public int getSequenceBits() {
			return sequenceBits;
		}

		public long getMaxDeltaSeconds() {
			return maxDeltaSeconds;
		}

		public long getMaxDatacenterId() {
			return maxDatacenterId;
		}

		public long getMaxWorkerId() {
			return maxWorkerId;
		}

		public long getMaxSequence() {
			return maxSequence;
		}

		public int getTimestampShift() {
			return timestampShift;
		}

		public int getDatacenterIdShift() {
			return datacenterIdShift;
		}

		public int getWorkerIdShift() {
			return workerIdShift;
		}

	}

}

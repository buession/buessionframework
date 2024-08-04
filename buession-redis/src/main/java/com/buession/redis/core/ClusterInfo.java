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
package com.buession.redis.core;

import com.buession.lang.Status;
import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * 集群信息，更多信息 <a href="http://www.redis.cn/commands/cluster-info.html" target="_blank">http://www.redis.cn/commands/cluster-info.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ClusterInfo implements Serializable {

	private final static long serialVersionUID = 5975395725518673096L;

	/**
	 * 集群状态，Status.SUCCESS 表示集群可以正常接受查询请求；Status.FAILURE 表示，至少有一个哈希槽没有被绑定（说明有哈希槽没有被绑定到任意一个节点），或者在错误的状态（节点可以提供服务但是带有FAIL 标记），或者该节点无法联系到多数master节点
	 */
	private final State state;

	/**
	 * 已分配到集群节点的哈希槽数量
	 */
	private final int slotsAssigned;

	/**
	 * 哈希槽状态不是 FAIL 和 PFAIL 的数量
	 */
	private final int slotsOk;

	/**
	 * 哈希槽状态是 PFAIL 的数量；
	 * 只要哈希槽状态没有被升级到 FAIL 状态，这些哈希槽仍然可以被正常处理；
	 * PFAIL 状态表示我们当前不能和节点进行交互，但这种状态只是临时的错误状态
	 */
	private final int slotsPfail;

	/**
	 * 哈希槽状态是 FAIL 的数量，如果值不是 0，那么集群节点将无法提供查询服务，除非 cluster-require-full-coverage 被设置为 no
	 */
	private final int slotsFail;

	/**
	 * 集群中节点数量，包括处于握手状态还没有成为集群正式成员的节点
	 */
	private final int knownNodes;

	/**
	 * 至少包含一个哈希槽且能够提供服务的 master 节点数量
	 */
	private final int size;

	/**
	 * 集群本地 Current Epoch 变量的值；这个值在节点故障转移过程时有用，它总是递增和唯一的
	 */
	private final int currentEpoch;

	/**
	 * 当前正在使用的节点的 Config Epoch 值，这个是关联在本节点的版本值
	 */
	private final int myEpoch;

	/**
	 * -
	 */
	private final long messagesPingSent;

	/**
	 * -
	 */
	private final long messagesPongSent;

	/**
	 * 通过 node-to-node 二进制总线发送的消息数量
	 */
	private final long messagesSent;

	/**
	 * -
	 */
	private final long messagesPingReceived;

	/**
	 * -
	 */
	private final long messagesPongReceived;

	/**
	 * -
	 */
	private final long messagesMeetReceived;

	/**
	 * 通过 node-to-node 二进制总线接收的消息数量
	 */
	private final long messagesReceived;

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		集群状态
	 * @param slotsAssigned
	 * 		已分配到集群节点的哈希槽数量
	 * @param slotsOk
	 * 		哈希槽状态不是 FAIL 和 PFAIL 的数量
	 * @param slotsPfail
	 * 		哈希槽状态是 PFAIL 的数量
	 * @param slotsFail
	 * 		哈希槽状态是 FAIL 的数量
	 * @param knownNodes
	 * 		集群中节点数量
	 * @param size
	 * 		至少包含一个哈希槽且能够提供服务的 master 节点数量
	 * @param currentEpoch
	 * 		集群本地 Current Epoch 变量的值
	 * @param myEpoch
	 * 		当前正在使用的节点的 Config Epoch 值
	 * @param messagesPingSent
	 * 		-
	 * @param messagesPongSent
	 * 		-
	 * @param messagesSent
	 * 		通过 node-to-node 二进制总线发送的消息数量
	 * @param messagesPingReceived
	 * 		-
	 * @param messagesPongReceived
	 * 		-
	 * @param messagesMeetReceived
	 * 		-
	 * @param messagesReceived
	 * 		通过 node-to-node 二进制总线接收的消息数量
	 */
	public ClusterInfo(final State state, final int slotsAssigned, final int slotsOk, final int slotsPfail,
					   final int slotsFail, final int knownNodes, final int size, final int currentEpoch,
					   final int myEpoch, final long messagesPingSent, final long messagesPongSent,
					   final long messagesSent, final long messagesPingReceived, final long messagesPongReceived,
					   final long messagesMeetReceived, final long messagesReceived){
		this.state = state;
		this.slotsAssigned = slotsAssigned;
		this.slotsOk = slotsOk;
		this.slotsPfail = slotsPfail;
		this.slotsFail = slotsFail;
		this.knownNodes = knownNodes;
		this.size = size;
		this.currentEpoch = currentEpoch;
		this.myEpoch = myEpoch;
		this.messagesPingSent = messagesPingSent;
		this.messagesPongSent = messagesPongSent;
		this.messagesSent = messagesSent;
		this.messagesPingReceived = messagesPingReceived;
		this.messagesPongReceived = messagesPongReceived;
		this.messagesMeetReceived = messagesMeetReceived;
		this.messagesReceived = messagesReceived;
	}

	/**
	 * 返回集群状态
	 *
	 * @return 集群状态
	 */
	public State getState(){
		return state;
	}

	/**
	 * 返回已分配到集群节点的哈希槽数量
	 *
	 * @return 已分配到集群节点的哈希槽数量
	 */
	public int getSlotsAssigned(){
		return slotsAssigned;
	}

	/**
	 * 返回哈希槽状态不是 FAIL 和 PFAIL 的数量
	 *
	 * @return 哈希槽状态不是 FAIL 和 PFAIL 的数量
	 */
	public int getSlotsOk(){
		return slotsOk;
	}

	/**
	 * 返回哈希槽状态是 PFAIL 的数量
	 *
	 * @return 哈希槽状态是 PFAIL 的数量
	 */
	public int getSlotsPfail(){
		return slotsPfail;
	}

	/**
	 * 返回哈希槽状态是 FAIL 的数量
	 *
	 * @return 哈希槽状态是 FAIL 的数量
	 */
	public int getSlotsFail(){
		return slotsFail;
	}

	/**
	 * 返回集群中节点数量，包括处于握手状态还没有成为集群正式成员的节点
	 *
	 * @return 集群中节点数量，包括处于握手状态还没有成为集群正式成员的节点
	 */
	public int getKnownNodes(){
		return knownNodes;
	}

	/**
	 * 返回至少包含一个哈希槽且能够提供服务的 master 节点数量
	 *
	 * @return 至少包含一个哈希槽且能够提供服务的 master 节点数量
	 */
	public int getSize(){
		return size;
	}

	/**
	 * 返回集群本地 Current Epoch 变量的值；这个值在节点故障转移过程时有用，它总是递增和唯一的
	 *
	 * @return 集群本地 Current Epoch 变量的值
	 */
	public int getCurrentEpoch(){
		return currentEpoch;
	}

	/**
	 * 返回当前正在使用的节点的 Config Epoch 值，这个是关联在本节点的版本值
	 *
	 * @return 当前正在使用的节点的 Config Epoch 值
	 */
	public int getMyEpoch(){
		return myEpoch;
	}

	/**
	 * -
	 *
	 * @return -
	 */
	public long getMessagesPingSent(){
		return messagesPingSent;
	}

	/**
	 * -
	 *
	 * @return -
	 */
	public long getMessagesPongSent(){
		return messagesPongSent;
	}

	/**
	 * 返回通过 node-to-node 二进制总线发送的消息数量
	 *
	 * @return 通过 node-to-node 二进制总线发送的消息数量
	 */
	public long getMessagesSent(){
		return messagesSent;
	}

	/**
	 * -
	 *
	 * @return -
	 */
	public long getMessagesPingReceived(){
		return messagesPingReceived;
	}

	/**
	 * -
	 *
	 * @return -
	 */
	public long getMessagesPongReceived(){
		return messagesPongReceived;
	}

	/**
	 * -
	 *
	 * @return -
	 */
	public long getMessagesMeetReceived(){
		return messagesMeetReceived;
	}

	/**
	 * 返回通过 node-to-node 二进制总线接收的消息数量
	 *
	 * @return 通过 node-to-node 二进制总线接收的消息数量
	 */
	public long getMessagesReceived(){
		return messagesReceived;
	}

	@SuppressWarnings("unchecked")
	protected static <E> E getObject(final Properties properties, final String key){
		return (E) properties.get(key);
	}

	@Override
	public String toString(){
		return ObjectStringBuilder.create()
				.add(Key.STATE.value, state)
				.add(Key.SLOTS_ASSIGNED.value, slotsAssigned)
				.add(Key.SLOTS_OK.value, slotsOk)
				.add(Key.SLOTS_PFAIL.value, slotsPfail)
				.add(Key.SLOTS_FAIL.value, slotsFail)
				.add(Key.KNOWN_NODES.value, knownNodes)
				.add(Key.SIZE.value, size)
				.add(Key.CURRENT_EPOCH.value, currentEpoch)
				.add(Key.MY_EPOCH.value, myEpoch)
				.add(Key.MESSAGES_PING_SENT.value, messagesPingSent)
				.add(Key.MESSAGES_PONG_SENT.value, messagesPongSent)
				.add(Key.MESSAGES_SENT.value, messagesSent)
				.add(Key.MESSAGES_PING_RECEIVED.value, messagesPingReceived)
				.add(Key.MESSAGES_PONG_RECEIVED.value, messagesPongReceived)
				.add(Key.MESSAGES_MEET_RECEIVED.value, messagesMeetReceived)
				.add(Key.MESSAGES_RECEIVED.value, messagesReceived)
				.build();
	}

	public enum State {

		OK("ok"),

		FAIL("fail");

		private String value;

		State(final String value){
			this.value = value;
		}

		public String getValue(){
			return value;
		}

		@Override
		public String toString(){
			return getValue();
		}

	}

	public enum Key {

		STATE("cluster_state"),

		SLOTS_ASSIGNED("cluster_slots_assigned"),

		SLOTS_OK("cluster_slots_ok"),

		SLOTS_PFAIL("cluster_slots_pfail"),

		SLOTS_FAIL("cluster_slots_fail"),

		KNOWN_NODES("cluster_known_nodes"),

		SIZE("cluster_size"),

		CURRENT_EPOCH("cluster_current_epoch"),

		MY_EPOCH("cluster_my_epoch"),

		MESSAGES_PING_SENT("cluster_stats_messages_ping_sent"),

		MESSAGES_PONG_SENT("cluster_stats_messages_pong_sent"),

		MESSAGES_SENT("cluster_stats_messages_sent"),

		MESSAGES_PING_RECEIVED("cluster_stats_messages_ping_received"),

		MESSAGES_PONG_RECEIVED("cluster_stats_messages_pong_received"),

		MESSAGES_MEET_RECEIVED("cluster_stats_messages_meet_received"),

		MESSAGES_RECEIVED("cluster_stats_messages_received");

		private final String value;

		Key(final String value){
			this.value = value;
		}

		public String getValue(){
			return value;
		}

	}

}

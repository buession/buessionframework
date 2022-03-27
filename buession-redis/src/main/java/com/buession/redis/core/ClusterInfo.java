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
	 * 集群状态
	 */
	private Status state;

	/**
	 * 已分配到集群节点的哈希槽数量
	 */
	private int slotsAssigned;

	/**
	 * 哈希槽状态
	 */
	private Status slotsOk;

	/**
	 * 哈希槽状态是 PFAIL 的数量；
	 * 只要哈希槽状态没有被升级到 FAIL 状态，这些哈希槽仍然可以被正常处理；
	 * PFAIL 状态表示我们当前不能和节点进行交互，但这种状态只是临时的错误状态
	 */
	private int slotsPfail;

	/**
	 * 哈希槽状态是 FAIL 的数量，如果值不是 0，那么集群节点将无法提供查询服务，除非 cluster-require-full-coverage 被设置为 no
	 */
	private int slotsFail;

	/**
	 * 集群中节点数量，包括处于握手状态还没有成为集群正式成员的节点
	 */
	private int knownNodes;

	/**
	 * 至少包含一个哈希槽且能够提供服务的 master 节点数量
	 */
	private int size;

	/**
	 * 集群本地 Current Epoch 变量的值；这个值在节点故障转移过程时有用，它总是递增和唯一的
	 */
	private int currentEpoch;

	/**
	 * 当前正在使用的节点的 Config Epoch 值，这个是关联在本节点的版本值
	 */
	private int myEpoch;

	/**
	 * 通过 node-to-node 二进制总线发送的消息数量
	 */
	private long messagesSent;

	/**
	 * 通过 node-to-node 二进制总线接收的消息数量
	 */
	private long messagesReceived;

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		集群状态
	 * @param slotsAssigned
	 * 		已分配到集群节点的哈希槽数量
	 * @param slotsOk
	 * 		哈希槽状态
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
	 * @param messagesSent
	 * 		通过 node-to-node 二进制总线发送的消息数量
	 * @param messagesReceived
	 * 		通过 node-to-node 二进制总线接收的消息数量
	 */
	public ClusterInfo(final Status state, final int slotsAssigned, final Status slotsOk, final int slotsPfail,
					   final int slotsFail, final int knownNodes, final int size, final int currentEpoch,
					   final int myEpoch, final long messagesSent, final long messagesReceived){
		this.state = state;
		this.slotsAssigned = slotsAssigned;
		this.slotsOk = slotsOk;
		this.slotsPfail = slotsPfail;
		this.slotsFail = slotsFail;
		this.knownNodes = knownNodes;
		this.size = size;
		this.currentEpoch = currentEpoch;
		this.myEpoch = myEpoch;
		this.messagesSent = messagesSent;
		this.messagesReceived = messagesReceived;
	}

	/**
	 * 返回集群状态
	 *
	 * @return 集群状态
	 */
	public Status getState(){
		return state;
	}

	/**
	 * 设置集群状态
	 *
	 * @param state
	 * 		集群状态
	 */
	public void setState(Status state){
		this.state = state;
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
	 * 设置已分配到集群节点的哈希槽数量
	 *
	 * @param slotsAssigned
	 * 		已分配到集群节点的哈希槽数量
	 */
	public void setSlotsAssigned(int slotsAssigned){
		this.slotsAssigned = slotsAssigned;
	}

	/**
	 * 返回哈希槽状态
	 *
	 * @return 哈希槽状态
	 */
	public Status getSlotsOk(){
		return slotsOk;
	}

	/**
	 * 设置哈希槽状态
	 *
	 * @param slotsOk
	 * 		哈希槽状态
	 */
	public void setSlotsOk(Status slotsOk){
		this.slotsOk = slotsOk;
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
	 * 设置哈希槽状态是 PFAIL 的数量
	 *
	 * @param slotsPfail
	 * 		哈希槽状态是 PFAIL 的数量
	 */
	public void setSlotsPfail(int slotsPfail){
		this.slotsPfail = slotsPfail;
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
	 * 设置哈希槽状态是 FAIL 的数量
	 *
	 * @param slotsFail
	 * 		哈希槽状态是 FAIL 的数量
	 */
	public void setSlotsFail(int slotsFail){
		this.slotsFail = slotsFail;
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
	 * 设置集群中节点数量
	 *
	 * @param knownNodes
	 * 		集群中节点数量
	 */
	public void setKnownNodes(int knownNodes){
		this.knownNodes = knownNodes;
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
	 * 设置至少包含一个哈希槽且能够提供服务的 master 节点数量
	 *
	 * @param size
	 * 		至少包含一个哈希槽且能够提供服务的 master 节点数量
	 */
	public void setSize(int size){
		this.size = size;
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
	 * 设置集群本地 Current Epoch 变量的值
	 *
	 * @param currentEpoch
	 * 		集群本地 Current Epoch 变量的值
	 */
	public void setCurrentEpoch(int currentEpoch){
		this.currentEpoch = currentEpoch;
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
	 * 设置当前正在使用的节点的 Config Epoch 值
	 *
	 * @param myEpoch
	 * 		当前正在使用的节点的 Config Epoch 值
	 */
	public void setMyEpoch(int myEpoch){
		this.myEpoch = myEpoch;
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
	 * 设置通过 node-to-node 二进制总线发送的消息数量
	 *
	 * @param messagesSent
	 * 		通过 node-to-node 二进制总线发送的消息数量
	 */
	public void setMessagesSent(long messagesSent){
		this.messagesSent = messagesSent;
	}

	/**
	 * 返回通过 node-to-node 二进制总线接收的消息数量
	 *
	 * @return 通过 node-to-node 二进制总线接收的消息数量
	 */
	public long getMessagesReceived(){
		return messagesReceived;
	}

	/**
	 * 设置通过 node-to-node 二进制总线接收的消息数量
	 *
	 * @param messagesReceived
	 * 		通过 node-to-node 二进制总线接收的消息数量
	 */
	public void setMessagesReceived(long messagesReceived){
		this.messagesReceived = messagesReceived;
	}

	@SuppressWarnings("unchecked")
	protected static <E> E getObject(final Properties properties, final String key){
		return (E) properties.get(key);
	}

	@Override
	public String toString(){
		final StringJoiner stringJoiner = new StringJoiner(",", "{", "}");

		stringJoiner.add("state=" + state).add("slotsAssigned=" + slotsAssigned).add("slotsOk=" + slotsOk);
		stringJoiner.add("slotsPfail=" + slotsPfail).add("slotsFail=" + slotsFail);
		stringJoiner.add("knownNodes=" + knownNodes).add("size=" + size);
		stringJoiner.add("currentEpoch=" + currentEpoch).add("myEpoch=" + myEpoch);
		stringJoiner.add("messagesSent=" + messagesSent).add("messagesReceived=" + messagesReceived);

		return stringJoiner.toString();
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

		MESSAGES_SENT("cluster_stats_messages_sent"),

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

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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.util.Set;

/**
 * 集群节点
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ClusterRedisNode extends RedisNode {

	private final static long serialVersionUID = -6841072654023820951L;

	/**
	 * Redis 服务器主机 IP 地址
	 */
	private final String ip;

	/**
	 * 标记位
	 */
	private final Set<Flag> flags;

	/**
	 * 是否是自身
	 */
	private final boolean isMySelf;

	/**
	 * 主机角色
	 */
	private Role role;

	/**
	 * Master 主机 ID
	 */
	private String masterId;

	/**
	 * 最近一次发送 ping 的时间（unix 毫秒时间戳），0 代表没有发送过
	 */
	private final long pingSent;

	/**
	 * 最近一次收到 pong 的时间（unix 毫秒时间戳），0 代表没有接收过
	 */
	private final long pongSent;

	/**
	 * 节点的 epoch 值
	 */
	private final long configEpoch;

	/**
	 * node-to-node 集群总线使用的链接的状态
	 */
	private final LinkState linkState;

	/**
	 * 哈希槽值或者一个哈希槽范围
	 */
	private final SlotRange slot;

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		节点ID
	 * @param ip
	 * 		客户端与节点通信使用的地址
	 * @param port
	 * 		客户端与节点通信使用的端口
	 * @param flags
	 * 		标记位
	 * @param role
	 * 		主机角色
	 * @param masterId
	 * 		Master 节点 Id
	 * @param pingSent
	 * 		最近一次发送 ping 的时间（unix 毫秒时间戳）
	 * @param pongSent
	 * 		最近一次收到 pong 的时间（unix 毫秒时间戳）
	 * @param configEpoch
	 * 		节点的 epoch 值
	 * @param linkState
	 * 		node-to-node 集群总线使用的链接的状态
	 * @param slot
	 * 		哈希槽值或者一个哈希槽范围
	 *
	 * @
	 */
	public ClusterRedisNode(final String id, final String ip, final int port, final Set<Flag> flags,
							final Role role, final String masterId, final long pingSent, final long pongSent,
							final long configEpoch,
							final LinkState linkState, final SlotRange slot) {
		super(ip, port);
		setId(id);
		this.ip = ip;
		this.flags = flags;
		if(flags == null){
			this.isMySelf = false;
		}else{
			this.isMySelf = flags.contains(Flag.MYSELF);
		}
		this.role = role;
		this.masterId = masterId;
		this.pingSent = pingSent;
		this.pongSent = pongSent;
		this.configEpoch = configEpoch;
		this.linkState = linkState;
		this.slot = slot;
	}

	/**
	 * 返回客户端与节点通信使用的地址
	 *
	 * @return 客户端与节点通信使用的地址
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 返回标记位
	 *
	 * @return 标记位
	 */
	public Set<Flag> getFlags() {
		return flags;
	}

	/**
	 * 是否是自身
	 *
	 * @return true / false
	 */
	public boolean isMySelf() {
		return isMySelf;
	}

	/**
	 * 返回主机角色
	 *
	 * @return 主机角色
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * 设置主机角色
	 *
	 * @param role
	 * 		主机角色
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 返回 Master 主机 ID
	 *
	 * @return Master 主机 ID
	 */
	public String getMasterId() {
		return masterId;
	}

	/**
	 * 设置 Master 主机 ID
	 *
	 * @param masterId
	 * 		Master 主机 ID
	 */
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	/**
	 * 返回最近一次发送 ping 的时间（unix 毫秒时间戳），0 代表没有发送过
	 *
	 * @return 最近一次发送 ping 的时间（unix 毫秒时间戳），0 代表没有发送过
	 */
	public long getPingSent() {
		return pingSent;
	}

	/**
	 * 返回最近一次收到 pong 的时间（unix 毫秒时间戳），0 代表没有接收过
	 *
	 * @return 最近一次收到 pong 的时间（unix 毫秒时间戳），0 代表没有接收过
	 */
	public long getPongSent() {
		return pongSent;
	}

	/**
	 * 返回节点的 epoch 值
	 *
	 * @return 节点的 epoch 值
	 */
	public long getConfigEpoch() {
		return configEpoch;
	}

	/**
	 * 返回 node-to-node 集群总线使用的链接的状态
	 *
	 * @return node-to-node 集群总线使用的链接的状态
	 */
	public LinkState getLinkState() {
		return linkState;
	}

	/**
	 * 返回哈希槽值或者一个哈希槽范围
	 *
	 * @return 哈希槽值或者一个哈希槽范围
	 */
	public SlotRange getSlot() {
		return slot;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("id", getId())
				.add("host", getHost() + ":" + getPort())
				.add("masterId", getMasterId())
				.add("ping-sent", pingSent)
				.add("pong-recv", pongSent)
				.add("config-epoch", configEpoch)
				.add("link-state", linkState)
				.add("slot", slot)
				.build();
	}

	/**
	 * 标记位
	 */
	public enum Flag {

		/**
		 * 当前连接的节点
		 */
		MYSELF("myself"),

		/**
		 * master 节点
		 */
		MASTER("master"),

		/**
		 * slave 节点
		 */
		SLAVE("slave"),

		/**
		 * 节点处于 PFAIL 状态
		 */
		PFAIL("fail?"),

		/**
		 * 节点处于FAIL 状态
		 */
		FAIL(""),

		/**
		 * 还未取得信任的节点，当前正在与其进行握手
		 */
		HANDSHAKE("handshake"),

		/**
		 * 没有地址的节点
		 */
		NOADDR("noaddr"),

		/**
		 * 没有标记
		 */
		NOFLAGS("noflags");

		private final String value;

		Flag(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

	/**
	 * node-to-node 集群总线使用的链接的状态
	 */
	public enum LinkState {

		CONNECTED("connected"),

		DISCONNECTED("disconnected");

		private final String value;

		LinkState(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}

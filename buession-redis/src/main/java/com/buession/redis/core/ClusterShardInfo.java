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
package com.buession.redis.core;

import com.buession.core.IntegerRange;

import java.io.Serializable;
import java.util.List;

/**
 * Redis Cluster 的节点和槽分布信息
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ClusterShardInfo implements Serializable {

	private final static long serialVersionUID = -6906496894720683515L;

	private IntegerRange slots;

	private List<ClusterShardNode> nodes;

	public IntegerRange getSlots() {
		return slots;
	}

	public void setSlots(IntegerRange slots) {
		this.slots = slots;
	}

	public List<ClusterShardNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ClusterShardNode> nodes) {
		this.nodes = nodes;
	}

	public final static class ClusterShardNode implements Serializable {

		private final static long serialVersionUID = -3226058616174727906L;

		private String id;

		private int port;

		private String ip;

		private String endpoint;

		private ClusterRole role;

		private int replicationOffset;

		private ClusterHealth health;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

		public ClusterRole getRole() {
			return role;
		}

		public void setRole(ClusterRole role) {
			this.role = role;
		}

		public int getReplicationOffset() {
			return replicationOffset;
		}

		public void setReplicationOffset(int replicationOffset) {
			this.replicationOffset = replicationOffset;
		}

		public ClusterHealth getHealth() {
			return health;
		}

		public void setHealth(ClusterHealth health) {
			this.health = health;
		}
	}

}

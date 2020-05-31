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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import java.io.Serializable;
import java.util.List;

/**
 * Redis 服务器的各种信息和统计数值
 *
 * @author Yong.Teng
 */
public class Info implements Serializable {

	private final static long serialVersionUID = -2772690110674245981L;

	/**
	 * Redis 服务器的信息
	 */
	private Server server;

	/**
	 * 已连接客户端的信息
	 */
	private Clients clients;

	/**
	 * 集群有关的信息
	 */
	private Cluster cluster;

	/**
	 * 主从复制信息
	 */
	private Replication replication;

	/**
	 * Redis 服务器的内存信息
	 */
	private Memory memory;

	/**
	 * CPU 的计算量统计信息
	 */
	private Cpu cpu;

	/**
	 * RDB 持久化和 AOF 持久化有关的信息
	 */
	private Persistence persistence;

	/**
	 * 数据库相关的统计信息
	 */
	private List<Keyspace> keyspace;

	/**
	 * 一般统计信息
	 */
	private Stats stats;

	/**
	 * 获取 Redis 服务器的信息
	 *
	 * @return Redis 服务器的信息
	 */
	public Server getServer(){
		return server;
	}

	/**
	 * 设置 Redis 服务器的信息
	 *
	 * @param server
	 * 		Redis 服务器的信息
	 */
	public void setServer(Server server){
		this.server = server;
	}

	/**
	 * 获取已连接客户端的信息
	 *
	 * @return 已连接客户端的信息
	 */
	public Clients getClients(){
		return clients;
	}

	/**
	 * 设置已连接客户端的信息
	 *
	 * @param clients
	 * 		已连接客户端的信息
	 */
	public void setClients(Clients clients){
		this.clients = clients;
	}

	/**
	 * 获取集群有关的信息
	 *
	 * @return 集群有关的信息
	 */
	public Cluster getCluster(){
		return cluster;
	}

	/**
	 * 设置集群有关的信息
	 *
	 * @param cluster
	 * 		集群有关的信息
	 */
	public void setCluster(Cluster cluster){
		this.cluster = cluster;
	}

	/**
	 * 获取主从复制信息
	 *
	 * @return 主从复制信息
	 */
	public Replication getReplication(){
		return replication;
	}

	/**
	 * 设置主从复制信息
	 *
	 * @param replication
	 * 		主从复制信息
	 */
	public void setReplication(Replication replication){
		this.replication = replication;
	}

	/**
	 * 获取 Redis 服务器的内存信息
	 *
	 * @return Redis 服务器的内存信息
	 */
	public Memory getMemory(){
		return memory;
	}

	/**
	 * 设置 Redis 服务器的内存信息
	 *
	 * @param memory
	 * 		Redis 服务器的内存信息
	 */
	public void setMemory(Memory memory){
		this.memory = memory;
	}

	/**
	 * 获取 CPU 的计算量统计信息
	 *
	 * @return CPU 的计算量统计信息
	 */
	public Cpu getCpu(){
		return cpu;
	}

	/**
	 * 设置 CPU 的计算量统计信息
	 *
	 * @param cpu
	 * 		CPU 的计算量统计信息
	 */
	public void setCpu(Cpu cpu){
		this.cpu = cpu;
	}

	/**
	 * 获取 RDB 持久化和 AOF 持久化有关的信息
	 *
	 * @return RDB 持久化和 AOF 持久化有关的信息
	 */
	public Persistence getPersistence(){
		return persistence;
	}

	/**
	 * 设置 RDB 持久化和 AOF 持久化有关的信息
	 *
	 * @param persistence
	 * 		RDB 持久化和 AOF 持久化有关的信息
	 */
	public void setPersistence(Persistence persistence){
		this.persistence = persistence;
	}

	/**
	 * 获取数据库相关的统计信息
	 *
	 * @return 数据库相关的统计信息
	 */
	public List<Keyspace> getKeyspace(){
		return keyspace;
	}

	/**
	 * 设置数据库相关的统计信息
	 *
	 * @param keyspace
	 * 		数据库相关的统计信息
	 */
	public void setKeyspace(List<Keyspace> keyspace){
		this.keyspace = keyspace;
	}

	/**
	 * 获取一般统计信息
	 *
	 * @return 一般统计信息
	 */
	public Stats getStats(){
		return stats;
	}

	/**
	 * 设置一般统计信息
	 *
	 * @param stats
	 * 		一般统计信息
	 */
	public void setStats(Stats stats){
		this.stats = stats;
	}

	@Override
	public String toString(){
		return "server=" + server + ", clients=" + clients + ", cluster=" + cluster + ", replication=" + replication + ", memory=" + memory + ", cpu=" + cpu + ", persistence=" + persistence + ", keyspace=" + keyspace + ", stats=" + stats;
	}

}

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
package com.buession.redis.core.command;

import com.buession.core.IntegerRange;
import com.buession.core.LongRange;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterShardInfo;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.ClusterSlotStat;

import java.util.List;

/**
 * 集群命令
 *
 * <p>详情说明 <a href="http://doc.redisfans.com/topic/cluster-tutorial/" target="_blank">http://doc.redisfans.com/topic/cluster-tutorial/</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ClusterCommands extends RedisCommands {

	/**
	 * When a cluster client receives an -ASK redirect,
	 * the ASKING command is sent to the target node followed by the command which was redirected.
	 * This is normally done automatically by cluster clients.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/asking/" target="_blank">https://redis.io/commands/asking/</a></p>
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status asking();

	/**
	 * 把一组 hash slots 分配给接收命令的节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-addslots/" target="_blank">https://redis.io/docs/latest/commands/cluster-addslots/</a></p>
	 *
	 * @param slots
	 * 		hash slots
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterAddSlots(final int... slots);

	/**
	 * 把一段连续的 hash slots 分配给接收命令的节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-addslotsrange/" target="_blank">https://redis.io/docs/latest/commands/cluster-addslotsrange/</a></p>
	 *
	 * @param slots
	 * 		hash slots
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterAddSlotsRange(final IntegerRange slots);

	/**
	 * The CLUSTER BUMPEPOCH command triggers an increment to the cluster’s config epoch from the connected node.
	 * The epoch will be incremented if the node’s config epoch is zero, or if it is less than the cluster’s greatest epoch.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/cluster-bumpepoch/" target="_blank">https://redis.io/commands/cluster-bumpepoch/</a></p>
	 *
	 * @return {@link BumpEpoch}
	 */
	KeyValue<BumpEpoch, Integer> clusterBumpEpoch();

	/**
	 * The command returns the number of failure reports for the specified node
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/cluster-count-failure-reports/" target="_blank">https://redis.io/commands/cluster-count-failure-reports/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return The number of active failure reports for the node
	 */
	Integer clusterCountFailureReports(final String nodeId);

	/**
	 * The command returns the number of failure reports for the specified node
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/cluster-count-failure-reports/" target="_blank">https://redis.io/commands/cluster-count-failure-reports/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return The number of active failure reports for the node
	 */
	Integer clusterCountFailureReports(final byte[] nodeId);

	/**
	 * 返回连接节点负责的指定 hash slot 的 key 的数量；
	 * 只查询连接节点的数据集，所以如果连接节点指派到该 hash slot 会返回 0
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-countkeysinslot/" target="_blank">https://redis.io/docs/latest/commands/cluster-countkeysinslot/</a></p>
	 *
	 * @param slot
	 * 		hash slot
	 *
	 * @return 连接节点负责的指定 hash slot 的 key 的数量
	 */
	Long clusterCountKeysInSlot(final int slot);

	/**
	 * 从当前节点删除指定的一个或多个哈希槽
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-delslots/" target="_blank">https://redis.io/docs/latest/commands/cluster-delslots/</a></p>
	 *
	 * @param slots
	 * 		hash slots
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterDelSlots(final int... slots);

	/**
	 * 从当前节点删除一段或多段连续的哈希槽
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-delslotsrange/" target="_blank">https://redis.io/docs/latest/commands/cluster-delslotsrange/</a></p>
	 *
	 * @param slots
	 * 		hash slots
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterDelSlotsRange(final IntegerRange slots);

	/**
	 * 让 slave 节点进行一次人工故障切换
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-failover/" target="_blank">https://redis.io/docs/latest/commands/cluster-failover/</a></p>
	 *
	 * @param clusterFailoverOption
	 * 		切换选项
	 *
	 * @return 该命令已被接受并进行人工故障转移，返回 Status.SUCCESS；切换操作无法执行，返回 Status.FAILURE
	 */
	Status clusterFailover(final ClusterFailoverOption clusterFailoverOption);

	/**
	 * Deletes all slots from a node
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/cluster-flushslots/" target="_blank">https://redis.io/commands/cluster-flushslots/</a></p>
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterFlushSlots();

	/**
	 * 从收到命令的 Redis 群集节点的节点信息列表中移除指定ID的节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-forget/" target="_blank">https://redis.io/docs/latest/commands/cluster-forget/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterForget(final String nodeId);

	/**
	 * 从收到命令的 Redis 群集节点的节点信息列表中移除指定ID的节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-forget/" target="_blank">https://redis.io/docs/latest/commands/cluster-forget/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterForget(final byte[] nodeId);

	/**
	 * 返回存储在连接节点的指定 hash slot 里面的 key 的列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-getkeysinslot/" target="_blank">https://redis.io/docs/latest/commands/cluster-getkeysinslot/</a></p>
	 *
	 * @param slot
	 * 		hash slot
	 * @param count
	 * 		返回数量
	 *
	 * @return count 个储在连接节点的指定 hash slot 里面的 key 的列表
	 */
	List<String> clusterGetKeysInSlot(final int slot, final long count);

	/**
	 * 返回 Redis 集群信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-info/" target="_blank">https://redis.io/docs/latest/commands/cluster-info/</a></p>
	 *
	 * @return Redis 集群信息
	 */
	ClusterInfo clusterInfo();

	/**
	 * 返回一个整数，用于标识指定键所散列到的哈希槽
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-keyslot/" target="_blank">https://redis.io/docs/latest/commands/cluster-keyslot/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希槽的值
	 */
	Long clusterKeySlot(final String key);

	/**
	 * 返回一个整数，用于标识指定键所散列到的哈希槽
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-keyslot/" target="_blank">https://redis.io/docs/latest/commands/cluster-keyslot/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希槽的值
	 */
	Long clusterKeySlot(final byte[] key);

	/**
	 * 返回当前节点与其他集群节点之间建立的所有 TCP 连接（即 cluster bus 链路）的详细信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-links/" target="_blank">https://redis.io/docs/latest/commands/cluster-links/</a></p>
	 *
	 * @return 当前节点与其他集群节点之间建立的所有 TCP 连接（即 cluster bus 链路）的详细信息
	 */
	List<ClusterLink> clusterLinks();

	/**
	 * 用来连接不同的开启集群支持的 Redis 节点，以进入工作集群
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-meet/" target="_blank">https://redis.io/docs/latest/commands/cluster-meet/</a></p>
	 *
	 * @param ip
	 * 		Redis 集群节点 IP
	 * @param port
	 * 		Redis 集群节点端口
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterMeet(final String ip, final int port);

	/**
	 * 执行原子化的哈希槽迁移操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-migration/" target="_blank">https://redis.io/docs/latest/commands/cluster-migration/</a></p>
	 *
	 * @param slots
	 * 		hash slots
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterMigration(final IntegerRange slots);

	/**
	 * 执行原子化的哈希槽迁移操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-migration/" target="_blank">https://redis.io/docs/latest/commands/cluster-migration/</a></p>
	 *
	 * @param migrationOp
	 * 		迁移操作类型
	 *
	 * @return -
	 */
	Object clusterMigration(final ClusterMigrationOp migrationOp);

	/**
	 * 执行原子化的哈希槽迁移操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-migration/" target="_blank">https://redis.io/docs/latest/commands/cluster-migration/</a></p>
	 *
	 * @param migrationOp
	 * 		迁移操作类型
	 * @param id
	 * 		ID
	 *
	 * @return -
	 */
	Object clusterMigration(final ClusterMigrationOp migrationOp, final String id);

	/**
	 * 返回当前节点 Id
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/cluster-myid/" target="_blank">https://redis.io/commands/cluster-myid/</a></p>
	 *
	 * @return 返回当前节点 Id
	 */
	String clusterMyId();

	/**
	 * 返回当前节点所属分片的唯一标识符
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-myshardid/" target="_blank">https://redis.io/docs/latest/commands/cluster-myshardid/</a></p>
	 *
	 * @return 返回当前节点所属分片的唯一标识符
	 */
	String clusterMyShardId();

	/**
	 * 当前连接节点所属集群的配置信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-nodes/" target="_blank">https://redis.io/docs/latest/commands/cluster-nodes/</a></p>
	 *
	 * @return 集群配置信息
	 */
	List<ClusterRedisNode> clusterNodes();

	/**
	 * 列出指定主节点的辅助副本节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-replicas/" target="_blank">https://redis.io/docs/latest/commands/cluster-replicas/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 主节点的辅助副本节点
	 */
	List<ClusterRedisNode> clusterReplicas(final String nodeId);

	/**
	 * 列出指定主节点的辅助副本节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-replicas/" target="_blank">https://redis.io/docs/latest/commands/cluster-replicas/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 主节点的辅助副本节点
	 */
	List<ClusterRedisNode> clusterReplicas(final byte[] nodeId);

	/**
	 * 重新配置一个节点成为指定master的salve节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-replicate/" target="_blank">https://redis.io/docs/latest/commands/cluster-replicate/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterReplicate(final String nodeId);

	/**
	 * 重新配置一个节点成为指定master的salve节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-replicate/" target="_blank">https://redis.io/docs/latest/commands/cluster-replicate/</a></p>
	 *
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterReplicate(final byte[] nodeId);

	/**
	 * Reset 集群
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-reset/" target="_blank">https://redis.io/docs/latest/commands/cluster-reset/</a></p>
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterReset();

	/**
	 * Reset 集群
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-reset/" target="_blank">https://redis.io/docs/latest/commands/cluster-reset/</a></p>
	 *
	 * @param clusterResetOption
	 * 		Reset 类型
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterReset(final ClusterResetOption clusterResetOption);

	/**
	 * 强制保存配置 nodes.conf 至磁盘
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-saveconfig/" target="_blank">https://redis.io/docs/latest/commands/cluster-saveconfig/</a></p>
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterSaveConfig();

	/**
	 * 为一个全新的节点设置指定的 config epoch，仅在如下情况下有效：
	 * 1）节点的节点信息表为空
	 * 2）节点的当前 config epoch 为 0
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-set-config-epoch/" target="_blank">https://redis.io/docs/latest/commands/cluster-set-config-epoch/</a></p>
	 *
	 * @param configEpoch
	 * 		Config Epoch
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterSetConfigEpoch(final long configEpoch);

	/**
	 * 根据如下子命令选项，修改接受节点中哈希槽的状态
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-setslot/" target="_blank">https://redis.io/docs/latest/commands/cluster-setslot/</a></p>
	 *
	 * @param slot
	 * 		hash slot
	 * @param setSlotOption
	 * 		命令选项 {@link ClusterSetSlotOption}
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId);

	/**
	 * 根据如下子命令选项，修改接受节点中哈希槽的状态
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-setslot/" target="_blank">https://redis.io/docs/latest/commands/cluster-setslot/</a></p>
	 *
	 * @param slot
	 * 		hash slot
	 * @param setSlotOption
	 * 		命令选项 {@link ClusterSetSlotOption}
	 * @param nodeId
	 * 		节点 Id
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId);

	/**
	 * 返回整个 Redis Cluster 的节点和槽分布信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-shards/" target="_blank">https://redis.io/docs/latest/commands/cluster-shards/</a></p>
	 *
	 * @return 指定 master 节点所有 slave 节点
	 */
	List<ClusterShardInfo> clusterShards();

	/**
	 * 列出指定 master 节点所有 slave 节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-slaves/" target="_blank">https://redis.io/docs/latest/commands/cluster-slaves/</a></p>
	 *
	 * @param nodeId
	 * 		Master 节点 Id
	 *
	 * @return 指定 master 节点所有 slave 节点
	 */
	List<ClusterRedisNode> clusterSlaves(final String nodeId);

	/**
	 * 列出指定 master 节点所有 slave 节点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-slaves/" target="_blank">https://redis.io/docs/latest/commands/cluster-slaves/</a></p>
	 *
	 * @param nodeId
	 * 		Master 节点 Id
	 *
	 * @return 指定 master 节点所有 slave 节点
	 */
	List<ClusterRedisNode> clusterSlaves(final byte[] nodeId);

	/**
	 * 哈希槽的详细统计信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-slot-stats/" target="_blank">https://redis.io/docs/latest/commands/cluster-slot-stats/</a></p>
	 *
	 * @return 指定 master 节点所有 slave 节点
	 */
	ClusterSlotStat clusterSlotStats();

	/**
	 * 返回哈希槽和 Redis 实例映射关系
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-slots/" target="_blank">https://redis.io/docs/latest/commands/cluster-slots/</a></p>
	 *
	 * @return 哈希槽和 Redis 实例映射关系
	 */
	List<ClusterSlot> clusterSlots();

	/**
	 * 开启与 Redis Cluster 从节点连接的读请求，通过该命令将从节点设置为只读模式
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/readonly/" target="_blank">https://redis.io/docs/latest/commands/readonly/</a></p>
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status readOnly();

	/**
	 * 将连接的只读模式重置为读写模式
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/readwrite/" target="_blank">https://redis.io/docs/latest/commands/readwrite/</a></p>
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status readWrite();

	enum ClusterMigrationOp {

		CANCEL,

		STATUS

	}

}

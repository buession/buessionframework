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
package com.buession.redis.core.operations;

import com.buession.lang.Status;
import com.buession.redis.core.RawVector;
import com.buession.redis.core.VSimScoreAttribs;
import com.buession.redis.core.VectorInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.VectorSetCommands;
import com.buession.redis.core.command.args.vectorset.VAddArgument;
import com.buession.redis.core.command.args.vectorset.VSimArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.List;
import java.util.Map;

/**
 * Vector Set 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=vector_set" target="_blank">https://redis.io/docs/latest/commands/?group=vector_set</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface VectorSetOperations extends VectorSetCommands, RedisOperations {

	@Override
	default Status vAdd(final String key, final double[] vectors, final String element) {
		return doExecute((cmd)->cmd.vAdd(KeyUtils.rawKey(this, key), vectors, element));
	}

	@Override
	default Status vAdd(final byte[] key, final double[] vectors, final byte[] element) {
		return doExecute((cmd)->cmd.vAdd(KeyUtils.rawKey(this, key), vectors, element));
	}

	@Override
	default Status vAdd(final String key, final double[] vectors, final String element, final VAddArgument argument) {
		return doExecute((cmd)->cmd.vAdd(KeyUtils.rawKey(this, key), vectors, element, argument));
	}

	@Override
	default Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument) {
		return doExecute((cmd)->cmd.vAdd(KeyUtils.rawKey(this, key), vectors, element, argument));
	}

	@Override
	default Long vCard(final String key) {
		return doExecute((cmd)->cmd.vCard(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long vCard(final byte[] key) {
		return doExecute((cmd)->cmd.vCard(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long vDim(final String key) {
		return doExecute((cmd)->cmd.vDim(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long vDim(final byte[] key) {
		return doExecute((cmd)->cmd.vDim(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Double> vEmb(final String key, final String element) {
		return doExecute((cmd)->cmd.vEmb(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default List<Double> vEmb(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vEmb(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default RawVector vembRaw(final String key, final String element) {
		return doExecute((cmd)->cmd.vembRaw(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default RawVector vembRaw(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vembRaw(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default String vGetAttr(final String key, final String element) {
		return doExecute((cmd)->cmd.vGetAttr(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default byte[] vGetAttr(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vGetAttr(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Boolean vIsMember(final String key, final String element) {
		return doExecute((cmd)->cmd.vIsMember(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Boolean vIsMember(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vIsMember(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default VectorInfo vInfo(final String key) {
		return doExecute((cmd)->cmd.vInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default VectorInfo vInfo(final byte[] key) {
		return doExecute((cmd)->cmd.vInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<String> vLinks(final String key, final String element) {
		return doExecute((cmd)->cmd.vLinks(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default List<byte[]> vLinks(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vLinks(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Map<String, Double> vLinksWithScores(final String key, final String element) {
		return doExecute((cmd)->cmd.vLinksWithScores(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Map<byte[], Double> vLinksWithScores(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vLinksWithScores(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default String vRandMember(final String key) {
		return doExecute((cmd)->cmd.vRandMember(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] vRandMember(final byte[] key) {
		return doExecute((cmd)->cmd.vRandMember(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<String> vRandMember(final String key, final int count) {
		return doExecute((cmd)->cmd.vRandMember(KeyUtils.rawKey(this, key), count));
	}

	@Override
	default List<byte[]> vRandMember(final byte[] key, final int count) {
		return doExecute((cmd)->cmd.vRandMember(KeyUtils.rawKey(this, key), count));
	}

	@Override
	default List<String> vRange(final String key, final String start, final String end) {
		return doExecute((cmd)->cmd.vRange(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default List<byte[]> vRange(final byte[] key, final byte[] start, final byte[] end) {
		return doExecute((cmd)->cmd.vRange(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default List<String> vRange(final String key, final String start, final String end, final int count) {
		return doExecute((cmd)->cmd.vRange(KeyUtils.rawKey(this, key), start, end, count));
	}

	@Override
	default List<byte[]> vRange(final byte[] key, final byte[] start, final byte[] end, final int count) {
		return doExecute((cmd)->cmd.vRange(KeyUtils.rawKey(this, key), start, end, count));
	}

	@Override
	default Status vRem(final String key, final String element) {
		return doExecute((cmd)->cmd.vRem(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Status vRem(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vRem(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Status vSetAttr(final String key, final String element, final String value) {
		return doExecute((cmd)->cmd.vSetAttr(KeyUtils.rawKey(this, key), element, value));
	}

	@Override
	default Status vSetAttr(final byte[] key, final byte[] element, final byte[] value) {
		return doExecute((cmd)->cmd.vSetAttr(KeyUtils.rawKey(this, key), element, value));
	}

	@Override
	default List<String> vSim(final String key, final double... vectors) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final double... vectors) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors));
	}

	@Override
	default List<String> vSim(final String key, final String element) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default List<String> vSim(final String key, final double[] vectors, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors, argument));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors, argument));
	}

	@Override
	default List<String> vSim(final String key, final double[] vectors, final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors, argument, count));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors, argument, count));
	}

	@Override
	default List<String> vSim(final String key, final double[] vectors, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors, count));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final double[] vectors, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), vectors, count));
	}

	@Override
	default List<String> vSim(final String key, final String element, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element, argument));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element, argument));
	}

	@Override
	default List<String> vSim(final String key, final String element, final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element, argument, count));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element, argument, count));
	}

	@Override
	default List<String> vSim(final String key, final String element, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element, count));
	}

	@Override
	default List<byte[]> vSim(final byte[] key, final byte[] element, final int count) {
		return doExecute((cmd)->cmd.vSim(KeyUtils.rawKey(this, key), element, count));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final double... vectors) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final double... vectors) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final String element) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors, argument));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors, argument));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument,
	                                           final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors, argument, count));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument,
	                                           final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors, argument, count));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final double[] vectors, final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors, count));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), vectors, count));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element, argument));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element, argument));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument,
	                                           final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element, argument, count));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument,
	                                           final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element, argument, count));
	}

	@Override
	default Map<String, Double> vSimWithScores(final String key, final String element, final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element, count));
	}

	@Override
	default Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final int count) {
		return doExecute((cmd)->cmd.vSimWithScores(KeyUtils.rawKey(this, key), element, count));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double... vectors) {
		return doExecute((cmd)->cmd.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double... vectors) {
		return doExecute((cmd)->cmd.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element) {
		return doExecute((cmd)->cmd.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element) {
		return doExecute((cmd)->cmd.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                                final VSimArgument argument) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors, argument));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                                final VSimArgument argument) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors, argument));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                                final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors, argument, count));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                                final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors, argument, count));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                                final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors, count));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                                final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), vectors, count));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                                final VSimArgument argument) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element, argument));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                                final VSimArgument argument) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element, argument));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                                final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element, argument, count));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                                final VSimArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element, argument, count));
	}

	@Override
	default Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                                final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element, count));
	}

	@Override
	default Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                                final int count) {
		return doExecute((cmd)->cmd
				.vSimWithScoresWithAttribs(KeyUtils.rawKey(this, key), element, count));
	}

	private <R> R doExecute(final Command.Executor<VectorSetCommands, R> executor) {
		return execute((client)->executor.execute(client.vectorSetCommands()));
	}

}

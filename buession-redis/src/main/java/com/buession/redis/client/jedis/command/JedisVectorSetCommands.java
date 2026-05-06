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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.RawVector;
import com.buession.redis.core.VSimScoreAttribs;
import com.buession.redis.core.VectorInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.VectorSetCommands;
import com.buession.redis.core.command.args.vectorset.VAddArgument;
import com.buession.redis.core.command.args.vectorset.VSimArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.response.RawVectorConveter;
import com.buession.redis.core.internal.convert.jedis.response.VSimScoreAttribsConverter;
import com.buession.redis.core.internal.convert.jedis.response.VectorInfoConverter;
import com.buession.redis.core.internal.jedis.args.JedisVAddParams;
import com.buession.redis.core.internal.jedis.args.JedisVSimParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.params.VSimParams;

import java.util.List;
import java.util.Map;

/**
 * Jedis Vector Set 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisVectorSetCommands extends AbstractJedisRedisCommands implements VectorSetCommands {

	public JedisVectorSetCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status vAdd(final String key, final double[] vectors, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element);
		return executeCommand(RedisCommand.VADD, args, (cmd)->cmd.vadd(key, vectors(vectors), element),
				(cmd)->cmd.vadd(key, vectors(vectors), element),
				(cmd)->cmd.vadd(key, vectors(vectors), element),
				new BooleanStatusConverter());
	}

	@Override
	public Status vAdd(final byte[] key, final double[] vectors, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element);
		return executeCommand(RedisCommand.VADD, args, (cmd)->cmd.vadd(key, vectors(vectors), element),
				(cmd)->cmd.vadd(key, vectors(vectors), element),
				(cmd)->cmd.vadd(key, vectors(vectors), element),
				new BooleanStatusConverter());
	}

	@Override
	public Status vAdd(final String key, final double[] vectors, final String element, final VAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element).add(argument);

		if(argument != null && argument.getReduce() != null){
			return executeCommand(RedisCommand.VADD, args,
					(cmd)->cmd.vadd(key, vectors(vectors), element, argument.getReduce(),
							new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, argument.getReduce(),
							new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, argument.getReduce(),
							new JedisVAddParams(argument)), new BooleanStatusConverter());
		}else{
			return executeCommand(RedisCommand.VADD, args,
					(cmd)->cmd.vadd(key, vectors(vectors), element, new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, new JedisVAddParams(argument)),
					new BooleanStatusConverter());
		}
	}

	@Override
	public Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element).add(argument);

		if(argument != null && argument.getReduce() != null){
			return executeCommand(RedisCommand.VADD, args,
					(cmd)->cmd.vadd(key, vectors(vectors), element, argument.getReduce(),
							new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, argument.getReduce(),
							new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, argument.getReduce(),
							new JedisVAddParams(argument)), new BooleanStatusConverter());
		}else{
			return executeCommand(RedisCommand.VADD, args,
					(cmd)->cmd.vadd(key, vectors(vectors), element, new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, new JedisVAddParams(argument)),
					(cmd)->cmd.vadd(key, vectors(vectors), element, new JedisVAddParams(argument)),
					new BooleanStatusConverter());
		}
	}

	@Override
	public Long vCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VCARD, args, (cmd)->cmd.vcard(key), (cmd)->cmd.vcard(key),
				(cmd)->cmd.vcard(key));
	}

	@Override
	public Long vCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VCARD, args, (cmd)->cmd.vcard(key), (cmd)->cmd.vcard(key),
				(cmd)->cmd.vcard(key));
	}

	@Override
	public Long vDim(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vdim(key), (cmd)->cmd.vdim(key),
				(cmd)->cmd.vdim(key));
	}

	@Override
	public Long vDim(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vdim(key), (cmd)->cmd.vdim(key),
				(cmd)->cmd.vdim(key));
	}

	@Override
	public List<Double> vEmb(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VEMB, args, (cmd)->cmd.vemb(key, element),
				(cmd)->cmd.vemb(key, element), (cmd)->cmd.vemb(key, element));
	}

	@Override
	public List<Double> vEmb(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vemb(key, element),
				(cmd)->cmd.vemb(key, element), (cmd)->cmd.vemb(key, element));
	}

	@Override
	public RawVector vembRaw(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("RAW");
		return executeCommand(RedisCommand.VEMB, args,
				(cmd)->cmd.vembRaw(key, element), (cmd)->cmd.vembRaw(key, element),
				(cmd)->cmd.vembRaw(key, element), new RawVectorConveter());
	}

	@Override
	public RawVector vembRaw(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("RAW");
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vembRaw(key, element),
				(cmd)->cmd.vembRaw(key, element), (cmd)->cmd.vembRaw(key, element),
				new RawVectorConveter());
	}

	@Override
	public String vGetAttr(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VGETATTR, args, (cmd)->cmd.vgetattr(key, element),
				(cmd)->cmd.vgetattr(key, element), (cmd)->cmd.vgetattr(key, element));
	}

	@Override
	public byte[] vGetAttr(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VGETATTR, args, (cmd)->cmd.vgetattr(key, element),
				(cmd)->cmd.vgetattr(key, element), (cmd)->cmd.vgetattr(key, element));
	}

	@Override
	public VectorInfo vInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VINFO, args, (cmd)->cmd.vinfo(key), (cmd)->cmd.vinfo(key),
				(cmd)->cmd.vinfo(key), new VectorInfoConverter());
	}

	@Override
	public VectorInfo vInfo(final byte[] key) {
		return vInfo(SafeEncoder.encode(key));
	}

	@Override
	public Boolean vIsMember(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VISMEMBER, args);
	}

	@Override
	public Boolean vIsMember(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VISMEMBER, args);
	}

	@Override
	public List<String> vLinks(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinks(key, element),
				(cmd)->cmd.vlinks(key, element), (cmd)->cmd.vlinks(key, element),
				Converters.list0Converter());
	}

	@Override
	public List<byte[]> vLinks(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinks(key, element),
				(cmd)->cmd.vlinks(key, element), (cmd)->cmd.vlinks(key, element),
				Converters.list0Converter());
	}

	@Override
	public Map<String, Double> vLinksWithScores(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinksWithScores(key, element),
				(cmd)->cmd.vlinksWithScores(key, element), (cmd)->cmd.vlinksWithScores(key, element),
				Converters.list0Converter());
	}

	@Override
	public Map<byte[], Double> vLinksWithScores(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinksWithScores(key, element),
				(cmd)->cmd.vlinksWithScores(key, element), (cmd)->cmd.vlinksWithScores(key, element),
				Converters.list0Converter());
	}

	@Override
	public String vRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(key),
				(cmd)->cmd.vrandmember(key), (cmd)->cmd.vrandmember(key));
	}

	@Override
	public byte[] vRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(key),
				(cmd)->cmd.vrandmember(key), (cmd)->cmd.vrandmember(key));
	}

	@Override
	public List<String> vRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key, count);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(key, count),
				(cmd)->cmd.vrandmember(key, count), (cmd)->cmd.vrandmember(key, count));
	}

	@Override
	public List<byte[]> vRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key, count);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(key, count),
				(cmd)->cmd.vrandmember(key, count), (cmd)->cmd.vrandmember(key, count));
	}

	@Override
	public List<String> vRange(final String key, final String start, final String end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.VRANGE, args);
	}

	@Override
	public List<byte[]> vRange(final byte[] key, final byte[] start, final byte[] end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.VRANGE, args);
	}

	@Override
	public List<String> vRange(final String key, final String start, final String end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(count);
		return executeCommand(RedisCommand.VRANGE, args);
	}

	@Override
	public List<byte[]> vRange(final byte[] key, final byte[] start, final byte[] end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(count);
		return executeCommand(RedisCommand.VRANGE, args);
	}

	@Override
	public Status vRem(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vrem(key, element),
				(cmd)->cmd.vrem(key, element), (cmd)->cmd.vrem(key, element),
				new BooleanStatusConverter());
	}

	@Override
	public Status vRem(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vrem(key, element),
				(cmd)->cmd.vrem(key, element), (cmd)->cmd.vrem(key, element),
				new BooleanStatusConverter());
	}

	@Override
	public Status vSetAttr(final String key, final String element, final String value) {
		final CommandArguments args = CommandArguments.create(key, element).add(value);
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vsetattr(key, element, value),
				(cmd)->cmd.vsetattr(key, element, value), (cmd)->cmd.vsetattr(key, element, value),
				new BooleanStatusConverter());
	}

	@Override
	public Status vSetAttr(final byte[] key, final byte[] element, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, element).add(value);
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vsetattr(key, element, value),
				(cmd)->cmd.vsetattr(key, element, value), (cmd)->cmd.vsetattr(key, element, value),
				new BooleanStatusConverter());
	}

	@Override
	public List<String> vSim(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vectors(vectors)),
				(cmd)->cmd.vsim(key, vectors(vectors)), (cmd)->cmd.vsim(key, vectors(vectors)));
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vectors(vectors)),
				(cmd)->cmd.vsim(key, vectors(vectors)), (cmd)->cmd.vsim(key, vectors(vectors)));
	}

	@Override
	public List<String> vSim(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimByElement(key, element),
				(cmd)->cmd.vsimByElement(key, element), (cmd)->cmd.vsimByElement(key, element));
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimByElement(key, element),
				(cmd)->cmd.vsimByElement(key, element), (cmd)->cmd.vsimByElement(key, element));
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument);
		return vSim(key, vectors, new JedisVSimParams(argument), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument);
		return vSim(key, vectors, new JedisVSimParams(argument), args);
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vSim(key, vectors, new JedisVSimParams(argument, count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vSim(key, vectors, new JedisVSimParams(argument, count), args);
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(Keyword.Common.COUNT, count);
		return vSim(key, vectors, new JedisVSimParams(count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(Keyword.Common.COUNT, count);
		return vSim(key, vectors, new JedisVSimParams(count), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument);
		return vSim(key, element, new JedisVSimParams(argument), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument);
		return vSim(key, element, new JedisVSimParams(argument), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vSim(key, element, new JedisVSimParams(argument, count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vSim(key, element, new JedisVSimParams(argument, count), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(Keyword.Common.COUNT, count);
		return vSim(key, element, new JedisVSimParams(count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(Keyword.Common.COUNT, count);
		return vSim(key, element, new JedisVSimParams(count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES");
		return vSimWithScores(key, vectors, new JedisVSimParams(), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES");
		return vSimWithScores(key, vectors, new JedisVSimParams(), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return vSimWithScores(key, element, new JedisVSimParams(), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return vSimWithScores(key, element, new JedisVSimParams(), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES");
		return vSimWithScores(key, vectors, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES");
		return vSimWithScores(key, vectors, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, vectors, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, vectors, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, vectors, new JedisVSimParams(count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, vectors, new JedisVSimParams(count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES");
		return vSimWithScores(key, element, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES");
		return vSimWithScores(key, element, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, element, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, element, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, element, new JedisVSimParams(count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScores(key, element, new JedisVSimParams(count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS");
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS");
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS");
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS");
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(argument);
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(argument);
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, vectors, new JedisVSimParams(count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS");
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS");
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(argument), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(argument, count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vSimWithScoresWithAttribs(key, element, new JedisVSimParams(count), args);
	}

	private List<String> vSim(final String key, final double[] vectors, final VSimParams vSimParams,
	                          final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vectors(vectors), vSimParams),
				(cmd)->cmd.vsim(key, vectors(vectors), vSimParams), (cmd)->cmd.vsim(key, vectors(vectors), vSimParams));
	}

	private List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimParams vSimParams,
	                          final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vectors(vectors), vSimParams),
				(cmd)->cmd.vsim(key, vectors(vectors), vSimParams), (cmd)->cmd.vsim(key, vectors(vectors), vSimParams));
	}

	private List<String> vSim(final String key, final String element, final VSimParams vSimParams,
	                          final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimByElement(key, element, vSimParams),
				(cmd)->cmd.vsimByElement(key, element, vSimParams), (cmd)->cmd.vsimByElement(key, element, vSimParams));
	}

	private List<byte[]> vSim(final byte[] key, final byte[] element, final VSimParams vSimParams,
	                          final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimByElement(key, element, vSimParams),
				(cmd)->cmd.vsimByElement(key, element, vSimParams), (cmd)->cmd.vsimByElement(key, element, vSimParams));
	}

	private Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimParams vSimParams,
	                                           final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScores(key, vectors(vectors), vSimParams),
				(cmd)->cmd.vsimWithScores(key, vectors(vectors), vSimParams),
				(cmd)->cmd.vsimWithScores(key, vectors(vectors), vSimParams));
	}

	private Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimParams vSimParams,
	                                           final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScores(key, vectors(vectors), vSimParams),
				(cmd)->cmd.vsimWithScores(key, vectors(vectors), vSimParams),
				(cmd)->cmd.vsimWithScores(key, vectors(vectors), vSimParams));
	}

	private Map<String, Double> vSimWithScores(final String key, final String element, final VSimParams vSimParams,
	                                           final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimByElementWithScores(key, element, vSimParams),
				(cmd)->cmd.vsimByElementWithScores(key, element, vSimParams),
				(cmd)->cmd.vsimByElementWithScores(key, element, vSimParams));
	}

	private Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimParams vSimParams,
	                                           final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimByElementWithScores(key, element, vSimParams),
				(cmd)->cmd.vsimByElementWithScores(key, element, vSimParams),
				(cmd)->cmd.vsimByElementWithScores(key, element, vSimParams));
	}

	private Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                                final VSimParams vSimParams,
	                                                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, null, null,
				(cmd)->cmd.vsimWithScoresAndAttribs(key, vectors(vectors), vSimParams),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	private Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                                final VSimParams vSimParams,
	                                                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, null, null,
				(cmd)->cmd.vsimWithScoresAndAttribs(key, vectors(vectors), vSimParams),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	private Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                                final VSimParams vSimParams,
	                                                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, null, null,
				(cmd)->cmd.vsimByElementWithScoresAndAttribs(key, element, vSimParams),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	private Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                                final VSimParams vSimParams,
	                                                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, null, null,
				(cmd)->cmd.vsimByElementWithScoresAndAttribs(key, element, vSimParams),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	private static float[] vectors(final double... vectors) {
		final float[] result = new float[vectors.length];

		for(int i = 0; i < vectors.length; i++){
			result[i] = (float) vectors[i];
		}

		return result;
	}

}

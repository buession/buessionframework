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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
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
import com.buession.redis.core.internal.convert.lettuce.response.RawVectorConveter;
import com.buession.redis.core.internal.convert.lettuce.response.VSimScoreAttribsConverter;
import com.buession.redis.core.internal.convert.lettuce.response.VectorMetadataConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceVAddArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceVSimArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.VSimArgs;

import java.util.List;
import java.util.Map;

/**
 * Lettuce Vector Set 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceVectorSetCommands extends AbstractLettuceRedisCommands implements VectorSetCommands {

	public LettuceVectorSetCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status vAdd(final String key, final double[] vectors, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element);
		return vAdd(SafeEncoder.encode(key), vectors, SafeEncoder.encode(element), args);
	}

	@Override
	public Status vAdd(final byte[] key, final double[] vectors, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element);
		return vAdd(key, vectors, element, args);
	}

	@Override
	public Status vAdd(final String key, final double[] vectors, final String element, final VAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element).add(argument);
		return vAdd(SafeEncoder.encode(key), vectors, SafeEncoder.encode(element), argument, args);
	}

	@Override
	public Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element).add(argument);
		return vAdd(key, vectors, element, argument, args);
	}

	@Override
	public Long vCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VCARD, args, (cmd)->cmd.vcard(SafeEncoder.encode(key)),
				(cmd)->cmd.vcard(SafeEncoder.encode(key)));
	}

	@Override
	public Long vCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VCARD, args, (cmd)->cmd.vcard(key), (cmd)->cmd.vcard(key));
	}

	@Override
	public Long vDim(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vdim(SafeEncoder.encode(key)),
				(cmd)->cmd.vdim(SafeEncoder.encode(key)));
	}

	@Override
	public Long vDim(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vdim(key), (cmd)->cmd.vdim(key));
	}

	@Override
	public List<Double> vEmb(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VEMB, args,
				(cmd)->cmd.vemb(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vemb(SafeEncoder.encode(key), SafeEncoder.encode(element)));
	}

	@Override
	public List<Double> vEmb(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vemb(key, element),
				(cmd)->cmd.vemb(key, element));
	}

	@Override
	public RawVector vembRaw(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("RAW");
		return executeCommand(RedisCommand.VEMB, args,
				(cmd)->cmd.vembRaw(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vembRaw(SafeEncoder.encode(key), SafeEncoder.encode(element)), new RawVectorConveter());
	}

	@Override
	public RawVector vembRaw(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("RAW");
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vembRaw(key, element),
				(cmd)->cmd.vembRaw(key, element), new RawVectorConveter());
	}

	@Override
	public String vGetAttr(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VGETATTR, args,
				(cmd)->cmd.vgetattr(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vgetattr(SafeEncoder.encode(key), SafeEncoder.encode(element)));
	}

	@Override
	public byte[] vGetAttr(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VGETATTR, args, (cmd)->cmd.vgetattr(key, element),
				(cmd)->cmd.vgetattr(key, element), SafeEncoder::encode);
	}

	@Override
	public VectorInfo vInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VINFO, args, (cmd)->cmd.vinfo(SafeEncoder.encode(key)),
				(cmd)->cmd.vinfo(SafeEncoder.encode(key)), new VectorMetadataConverter());
	}

	@Override
	public VectorInfo vInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VINFO, args, (cmd)->cmd.vinfo(key), (cmd)->cmd.vinfo(key),
				new VectorMetadataConverter());
	}

	@Override
	public Boolean vIsMember(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VISMEMBER, args);
	}

	@Override
	public Boolean vIsMember(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VISMEMBER, args);
	}

	@Override
	public List<String> vLinks(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VLINKS, args,
				(cmd)->cmd.vlinks(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vlinks(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> vLinks(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinks(key, element),
				(cmd)->cmd.vlinks(key, element));
	}

	@Override
	public Map<String, Double> vLinksWithScores(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES");
		return executeCommand(RedisCommand.VLINKS, args,
				(cmd)->cmd.vlinksWithScores(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vlinksWithScores(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Double> vLinksWithScores(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES");
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinksWithScores(key, element),
				(cmd)->cmd.vlinksWithScores(key, element));
	}

	@Override
	public String vRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(SafeEncoder.encode(key)),
				(cmd)->cmd.vrandmember(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] vRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(key),
				(cmd)->cmd.vrandmember(key));
	}

	@Override
	public List<String> vRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(SafeEncoder.encode(key), count),
				(cmd)->cmd.vrandmember(SafeEncoder.encode(key), count), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> vRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(key, count),
				(cmd)->cmd.vrandmember(key, count));
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
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VREM, args,
				(cmd)->cmd.vrem(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vrem(SafeEncoder.encode(key), SafeEncoder.encode(element)), new BooleanStatusConverter());
	}

	@Override
	public Status vRem(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vrem(key, element),
				(cmd)->cmd.vrem(key, element), new BooleanStatusConverter());
	}

	@Override
	public Status vSetAttr(final String key, final String element, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(value);
		return executeCommand(RedisCommand.VREM, args,
				(cmd)->cmd.vsetattr(SafeEncoder.encode(key), SafeEncoder.encode(element), value),
				(cmd)->cmd.vsetattr(SafeEncoder.encode(key), SafeEncoder.encode(element), value),
				new BooleanStatusConverter());
	}

	@Override
	public Status vSetAttr(final byte[] key, final byte[] element, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(value);
		return executeCommand(RedisCommand.VREM, args,
				(cmd)->cmd.vsetattr(key, element, SafeEncoder.encode(value)),
				(cmd)->cmd.vsetattr(key, element, SafeEncoder.encode(value)),
				new BooleanStatusConverter());
	}

	@Override
	public List<String> vSim(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(SafeEncoder.encode(key), vectors(vectors)),
				(cmd)->cmd.vsim(SafeEncoder.encode(key), vectors(vectors)), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vectors(vectors)),
				(cmd)->cmd.vsim(key, vectors(vectors)));
	}

	@Override
	public List<String> vSim(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsim(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vsim(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, element),
				(cmd)->cmd.vsim(key, element));
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument);
		return vStringSim(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument);
		return vBinarySim(key, vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add(Keyword.Common.COUNT).add(count);
		return vStringSim(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add(Keyword.Common.COUNT).add(count);
		return vBinarySim(key, vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(Keyword.Common.COUNT).add(count);
		return vStringSim(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(Keyword.Common.COUNT).add(count);
		return vBinarySim(key, vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument);
		return vSim(SafeEncoder.encode(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument);
		return vSim(key, element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add(Keyword.Common.COUNT).add(count);
		return vSim(SafeEncoder.encode(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add(Keyword.Common.COUNT).add(count);
		return vSim(key, element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(Keyword.Common.COUNT).add(count);
		return vSim(SafeEncoder.encode(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(Keyword.Common.COUNT).add(count);
		return vSim(key, element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScore(SafeEncoder.encode(key), vectors(vectors)),
				(cmd)->cmd.vsimWithScore(SafeEncoder.encode(key), vectors(vectors)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScore(key, vectors(vectors)),
				(cmd)->cmd.vsimWithScore(key, vectors(vectors)));
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vlinksWithScores(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vlinksWithScores(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vlinksWithScores(key, element),
				(cmd)->cmd.vlinksWithScores(key, element));
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES").add(argument);
		return vStringSimWithScores(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES").add(argument);
		return vBinarySimWithScores(key, vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vStringSimWithScores(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vBinarySimWithScores(key, vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vStringSimWithScores(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vBinarySimWithScores(key, vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument).add("WITHSCORES");
		return vsimWithScores(SafeEncoder.encode(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument).add("WITHSCORES");
		return vsimWithScores(key, element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vsimWithScores(SafeEncoder.encode(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vsimWithScores(key, element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vsimWithScores(SafeEncoder.encode(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES")
				.add(Keyword.Common.COUNT).add(count);
		return vsimWithScores(key, element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(SafeEncoder.encode(key), vectors(vectors)),
				(cmd)->cmd.vsimWithScoreWithAttribs(SafeEncoder.encode(key), vectors(vectors)),
				new MapConverter<>(SafeEncoder::encode, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vectors(vectors)),
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vectors(vectors)),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vsimWithScoreWithAttribs(SafeEncoder.encode(key), SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScoreWithAttribs(key, element),
				(cmd)->cmd.vsimWithScoreWithAttribs(key, element),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(argument);
		return vStringSimWithScoresWithAttribs(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(argument);
		return vBinarySimWithScoresWithAttribs(key, vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final VSimArgument argument,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT).add(count);
		return vStringSimWithScoresWithAttribs(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(argument, count),
				args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final VSimArgument argument,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT).add(count);
		return vBinarySimWithScoresWithAttribs(key, vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT).add(count);
		return vStringSimWithScoresWithAttribs(SafeEncoder.encode(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT).add(count);
		return vBinarySimWithScoresWithAttribs(key, vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS");
		return vsimWithScoresWithAttribs(SafeEncoder.encode(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS");
		return vsimWithScoresWithAttribs(key, element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT).add(count);
		return vsimWithScoresWithAttribs(SafeEncoder.encode(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT).add(count);
		return vsimWithScoresWithAttribs(key, element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT).add(count);
		return vsimWithScoresWithAttribs(SafeEncoder.encode(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT).add(count);
		return vsimWithScoresWithAttribs(key, element, new LettuceVSimArgs(count), args);
	}

	private Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final CommandArguments args) {
		return executeCommand(RedisCommand.VADD, args, (cmd)->cmd.vadd(key, element, vectors(vectors)),
				(cmd)->cmd.vadd(key, element, vectors(vectors)), new BooleanStatusConverter());
	}

	private Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument,
	                    final CommandArguments args) {
		return executeCommand(RedisCommand.VADD, args,
				(cmd)->cmd.vadd(key, element, new LettuceVAddArgs(argument), vectors(vectors)),
				(cmd)->cmd.vadd(key, element, new LettuceVAddArgs(argument), vectors(vectors)),
				new BooleanStatusConverter());
	}

	private List<String> vStringSim(final byte[] key, final double[] vectors, final VSimArgs vSimArgs,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsim(key, vSimArgs, vectors(vectors)), Converters.binaryListStringListConverter());
	}

	private List<byte[]> vBinarySim(final byte[] key, final double[] vectors, final VSimArgs vSimArgs,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsim(key, vSimArgs, vectors(vectors)));
	}

	private List<String> vSim(final byte[] key, final String element, final VSimArgs vSimArgs,
	                          final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vSimArgs, SafeEncoder.encode(element)),
				(cmd)->cmd.vsim(key, vSimArgs, SafeEncoder.encode(element)),
				Converters.binaryListStringListConverter());
	}

	private List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgs vSimArgs,
	                          final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vSimArgs, element),
				(cmd)->cmd.vsim(key, vSimArgs, element));
	}

	private Map<String, Double> vStringSimWithScores(final byte[] key, final double[] vectors, final VSimArgs vSimArgs,
	                                                 final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScore(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsimWithScore(key, vSimArgs, vectors(vectors)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	private Map<byte[], Double> vBinarySimWithScores(final byte[] key, final double[] vectors, final VSimArgs vSimArgs,
	                                                 final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScore(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsimWithScore(key, vSimArgs, vectors(vectors)));
	}

	private Map<String, Double> vsimWithScores(final byte[] key, final String element, final VSimArgs vSimArgs,
	                                           final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScore(key, vSimArgs, SafeEncoder.encode(element)),
				(cmd)->cmd.vsimWithScore(key, vSimArgs, SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	private Map<byte[], Double> vsimWithScores(final byte[] key, final byte[] element, final VSimArgs vSimArgs,
	                                           final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScore(key, vSimArgs, element),
				(cmd)->cmd.vsimWithScore(key, vSimArgs, element));
	}

	private Map<String, VSimScoreAttribs> vStringSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                                      final VSimArgs vSimArgs,
	                                                                      final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, vectors(vectors)),
				new MapConverter<>(SafeEncoder::encode, new VSimScoreAttribsConverter()));
	}

	private Map<byte[], VSimScoreAttribs> vBinarySimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                                      final VSimArgs vSimArgs,
	                                                                      final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, vectors(vectors)),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	private Map<String, VSimScoreAttribs> vsimWithScoresWithAttribs(final byte[] key, final String element,
	                                                                final VSimArgs vSimArgs,
	                                                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, SafeEncoder.encode(element)),
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, new VSimScoreAttribsConverter()));
	}

	private Map<byte[], VSimScoreAttribs> vsimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                                final VSimArgs vSimArgs,
	                                                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, element),
				(cmd)->cmd.vsimWithScoreWithAttribs(key, vSimArgs, element),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	private static Double[] vectors(final double... vectors) {
		final Double[] result = new Double[vectors.length];

		for(int i = 0; i < vectors.length; i++){
			result[i] = vectors[i];
		}

		return result;
	}

}

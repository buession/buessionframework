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
import com.buession.redis.core.internal.convert.BinaryListStringListConverter;
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
		return vAdd(rawBinaryKey(key), vectors, SafeEncoder.encode(element), args);
	}

	@Override
	public Status vAdd(final byte[] key, final double[] vectors, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element);
		return vAdd(rawKey(key), vectors, element, args);
	}

	@Override
	public Status vAdd(final String key, final double[] vectors, final String element, final VAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element).add(argument);
		return vAdd(rawBinaryKey(key), vectors, SafeEncoder.encode(element), argument, args);
	}

	@Override
	public Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(element).add(argument);
		return vAdd(rawKey(key), vectors, element, argument, args);
	}

	@Override
	public Long vCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VCARD, args, (cmd)->cmd.vcard(rawBinaryKey(key)),
				(cmd)->cmd.vcard(rawBinaryKey(key)));
	}

	@Override
	public Long vCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VCARD, args, (cmd)->cmd.vcard(rawKey(key)), (cmd)->cmd.vcard(rawKey(key)));
	}

	@Override
	public Long vDim(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vdim(rawBinaryKey(key)),
				(cmd)->cmd.vdim(rawBinaryKey(key)));
	}

	@Override
	public Long vDim(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vdim(rawKey(key)), (cmd)->cmd.vdim(rawKey(key)));
	}

	@Override
	public List<Double> vEmb(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VEMB, args, (cmd)->cmd.vemb(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vemb(rawBinaryKey(key), SafeEncoder.encode(element)));
	}

	@Override
	public List<Double> vEmb(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vemb(rawKey(key), element),
				(cmd)->cmd.vemb(rawKey(key), element));
	}

	@Override
	public RawVector vembRaw(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("RAW");
		return executeCommand(RedisCommand.VEMB, args,
				(cmd)->cmd.vembRaw(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vembRaw(rawBinaryKey(key), SafeEncoder.encode(element)), new RawVectorConveter());
	}

	@Override
	public RawVector vembRaw(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("RAW");
		return executeCommand(RedisCommand.VDIM, args, (cmd)->cmd.vembRaw(rawKey(key), element),
				(cmd)->cmd.vembRaw(rawKey(key), element), new RawVectorConveter());
	}

	@Override
	public String vGetAttr(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VGETATTR, args,
				(cmd)->cmd.vgetattr(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vgetattr(rawBinaryKey(key), SafeEncoder.encode(element)));
	}

	@Override
	public byte[] vGetAttr(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VGETATTR, args, (cmd)->cmd.vgetattr(rawKey(key), element),
				(cmd)->cmd.vgetattr(rawKey(key), element), SafeEncoder::encode);
	}

	@Override
	public VectorInfo vInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VINFO, args, (cmd)->cmd.vinfo(rawBinaryKey(key)),
				(cmd)->cmd.vinfo(rawBinaryKey(key)), new VectorMetadataConverter());
	}

	@Override
	public VectorInfo vInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VINFO, args, (cmd)->cmd.vinfo(rawKey(key)), (cmd)->cmd.vinfo(rawKey(key)),
				new VectorMetadataConverter());
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
		return executeCommand(RedisCommand.VLINKS, args,
				(cmd)->cmd.vlinks(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vlinks(rawBinaryKey(key), SafeEncoder.encode(element)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> vLinks(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinks(rawKey(key), element),
				(cmd)->cmd.vlinks(rawKey(key), element));
	}

	@Override
	public Map<String, Double> vLinksWithScores(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return executeCommand(RedisCommand.VLINKS, args,
				(cmd)->cmd.vlinksWithScores(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vlinksWithScores(rawBinaryKey(key), SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Double> vLinksWithScores(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return executeCommand(RedisCommand.VLINKS, args, (cmd)->cmd.vlinksWithScores(rawKey(key), element),
				(cmd)->cmd.vlinksWithScores(rawKey(key), element));
	}

	@Override
	public String vRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(rawBinaryKey(key)),
				(cmd)->cmd.vrandmember(rawBinaryKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] vRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(rawKey(key)),
				(cmd)->cmd.vrandmember(rawKey(key)));
	}

	@Override
	public List<String> vRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key, count);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(rawBinaryKey(key), count),
				(cmd)->cmd.vrandmember(rawBinaryKey(key), count), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> vRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key, count);
		return executeCommand(RedisCommand.VRANDMEMBER, args, (cmd)->cmd.vrandmember(rawKey(key), count),
				(cmd)->cmd.vrandmember(rawKey(key), count));
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
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vrem(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vrem(rawBinaryKey(key), SafeEncoder.encode(element)), new BooleanStatusConverter());
	}

	@Override
	public Status vRem(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VREM, args, (cmd)->cmd.vrem(rawKey(key), element),
				(cmd)->cmd.vrem(rawKey(key), element), new BooleanStatusConverter());
	}

	@Override
	public Status vSetAttr(final String key, final String element, final String value) {
		final CommandArguments args = CommandArguments.create(key, element).add(value);
		return executeCommand(RedisCommand.VREM, args,
				(cmd)->cmd.vsetattr(rawBinaryKey(key), SafeEncoder.encode(element), value),
				(cmd)->cmd.vsetattr(rawBinaryKey(key), SafeEncoder.encode(element), value),
				new BooleanStatusConverter());
	}

	@Override
	public Status vSetAttr(final byte[] key, final byte[] element, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, element).add(value);
		return executeCommand(RedisCommand.VREM, args,
				(cmd)->cmd.vsetattr(rawKey(key), element, SafeEncoder.encode(value)),
				(cmd)->cmd.vsetattr(rawKey(key), element, SafeEncoder.encode(value)),
				new BooleanStatusConverter());
	}

	@Override
	public List<String> vSim(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(rawBinaryKey(key), vectors(vectors)),
				(cmd)->cmd.vsim(rawBinaryKey(key), vectors(vectors)), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(rawKey(key), vectors(vectors)),
				(cmd)->cmd.vsim(rawKey(key), vectors(vectors)));
	}

	@Override
	public List<String> vSim(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vsim(rawBinaryKey(key), SafeEncoder.encode(element)), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(rawKey(key), element),
				(cmd)->cmd.vsim(rawKey(key), element));
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument);
		return vStringSim(rawBinaryKey(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument);
		return vBinarySim(rawKey(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vStringSim(rawBinaryKey(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vBinarySim(rawKey(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<String> vSim(final String key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(Keyword.Common.COUNT, count);
		return vStringSim(rawBinaryKey(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(Keyword.Common.COUNT, count);
		return vBinarySim(rawKey(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument);
		return vSim(rawBinaryKey(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument);
		return vSim(rawKey(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vSim(rawBinaryKey(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return vSim(rawKey(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public List<String> vSim(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(Keyword.Common.COUNT, count);
		return vSim(rawBinaryKey(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public List<byte[]> vSim(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(Keyword.Common.COUNT, count);
		return vSim(rawKey(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScore(rawBinaryKey(key), vectors(vectors)),
				(cmd)->cmd.vsimWithScore(rawBinaryKey(key), vectors(vectors)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScore(rawKey(key), vectors(vectors)),
				(cmd)->cmd.vsimWithScore(rawKey(key), vectors(vectors)));
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vlinksWithScores(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vlinksWithScores(rawBinaryKey(key), SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vlinksWithScores(rawKey(key), element),
				(cmd)->cmd.vlinksWithScores(rawKey(key), element));
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES").add(argument);
		return vStringSimWithScores(rawBinaryKey(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES").add(argument);
		return vBinarySimWithScores(rawKey(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vStringSimWithScores(rawBinaryKey(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vBinarySimWithScores(rawKey(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vStringSimWithScores(rawBinaryKey(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vBinarySimWithScores(rawKey(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES");
		return vsimWithScores(rawBinaryKey(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES");
		return vsimWithScores(rawKey(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vsimWithScores(rawBinaryKey(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vsimWithScores(rawKey(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, Double> vSimWithScores(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vsimWithScores(rawBinaryKey(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES")
				.add(Keyword.Common.COUNT, count);
		return vsimWithScores(rawKey(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(rawBinaryKey(key), vectors(vectors)),
				(cmd)->cmd.vsimWithScoreWithAttribs(rawBinaryKey(key), vectors(vectors)),
				new MapConverter<>(SafeEncoder::encode, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double... vectors) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(rawKey(key), vectors(vectors)),
				(cmd)->cmd.vsimWithScoreWithAttribs(rawKey(key), vectors(vectors)),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args,
				(cmd)->cmd.vsimWithScoreWithAttribs(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.vsimWithScoreWithAttribs(rawBinaryKey(key), SafeEncoder.encode(element)),
				new MapConverter<>(SafeEncoder::encode, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS");
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsimWithScoreWithAttribs(rawKey(key), element),
				(cmd)->cmd.vsimWithScoreWithAttribs(rawKey(key), element),
				new MapConverter<>((k)->k, new VSimScoreAttribsConverter()));
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(argument);
		return vStringSimWithScoresWithAttribs(rawBinaryKey(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(argument);
		return vBinarySimWithScoresWithAttribs(rawKey(key), vectors, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final VSimArgument argument,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vStringSimWithScoresWithAttribs(rawBinaryKey(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final VSimArgument argument,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vBinarySimWithScoresWithAttribs(rawKey(key), vectors, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vStringSimWithScoresWithAttribs(rawBinaryKey(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key).add(vectors).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vBinarySimWithScoresWithAttribs(rawKey(key), vectors, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS");
		return vsimWithScoresWithAttribs(rawBinaryKey(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final VSimArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS");
		return vsimWithScoresWithAttribs(rawKey(key), element, new LettuceVSimArgs(argument), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vsimWithScoresWithAttribs(rawBinaryKey(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final VSimArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add("WITHSCORES", "WITHATTRIBS").add(Keyword.Common.COUNT, count);
		return vsimWithScoresWithAttribs(rawKey(key), element, new LettuceVSimArgs(argument, count), args);
	}

	@Override
	public Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vsimWithScoresWithAttribs(rawBinaryKey(key), element, new LettuceVSimArgs(count), args);
	}

	@Override
	public Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
	                                                               final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add("WITHSCORES", "WITHATTRIBS")
				.add(Keyword.Common.COUNT, count);
		return vsimWithScoresWithAttribs(rawKey(key), element, new LettuceVSimArgs(count), args);
	}

	private Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final CommandArguments args) {
		return executeCommand(RedisCommand.VADD, args, (cmd)->cmd.vadd(rawKey(key), element, vectors(vectors)),
				(cmd)->cmd.vadd(rawKey(key), element, vectors(vectors)), new BooleanStatusConverter());
	}

	private Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument,
	                    final CommandArguments args) {
		return executeCommand(RedisCommand.VADD, args,
				(cmd)->cmd.vadd(rawKey(key), element, new LettuceVAddArgs(argument), vectors(vectors)),
				(cmd)->cmd.vadd(rawKey(key), element, new LettuceVAddArgs(argument), vectors(vectors)),
				new BooleanStatusConverter());
	}

	private List<String> vStringSim(final byte[] key, final double[] vectors, final VSimArgs vSimArgs,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.VSIM, args, (cmd)->cmd.vsim(key, vSimArgs, vectors(vectors)),
				(cmd)->cmd.vsim(key, vSimArgs, vectors(vectors)), new BinaryListStringListConverter());
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
				new BinaryListStringListConverter());
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

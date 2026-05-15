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
package com.buession.redis.core.command.args.string;

import com.buession.redis.core.command.args.Argument;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class SetType implements Argument {

	private NxXx nxXx;

	private CompareCondition compareCondition;

	private String compareValue;

	private SetType() {

	}

	public NxXx getNxXx() {
		return nxXx;
	}

	public CompareCondition getCompareCondition() {
		return compareCondition;
	}

	public String getCompareValue() {
		return compareValue;
	}

	public static SetType nx() {
		return nxXx(NxXx.NX);
	}

	public static SetType xx() {
		return nxXx(NxXx.XX);
	}

	public static SetType nxXx(final NxXx nxXx) {
		SetType setType = new SetType();
		setType.nxXx = nxXx;
		return setType;
	}

	public static SetType eq(final String value) {
		return compareCondition(CompareCondition.IFEQ, value);
	}

	public static SetType eq(final byte[] value) {
		return compareCondition(CompareCondition.IFEQ, value);
	}

	public static SetType ne(final String value) {
		return compareCondition(CompareCondition.IFNE, value);
	}

	public static SetType ne(final byte[] value) {
		return compareCondition(CompareCondition.IFNE, value);
	}

	public static SetType deq(final String value) {
		return compareCondition(CompareCondition.IFDEQ, value);
	}

	public static SetType deq(final byte[] value) {
		return compareCondition(CompareCondition.IFDEQ, value);
	}

	public static SetType dne(final String value) {
		return compareCondition(CompareCondition.IFDNE, value);
	}

	public static SetType dne(final byte[] value) {
		return compareCondition(CompareCondition.IFDNE, value);
	}

	public static SetType compareCondition(final CompareCondition condition, final String value) {
		SetType setType = new SetType();
		setType.compareCondition = condition;
		setType.compareValue = value;
		return setType;
	}

	public static SetType compareCondition(final CompareCondition condition, final byte[] value) {
		return compareCondition(condition, SafeEncoder.encode(value));
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create();

		if(getNxXx() != null){
			builder.append(getNxXx());
		}else if(getCompareCondition() != null && getCompareValue() != null){
			builder.add(getCompareCondition().name(), getCompareValue());
		}

		return builder.build();
	}

}

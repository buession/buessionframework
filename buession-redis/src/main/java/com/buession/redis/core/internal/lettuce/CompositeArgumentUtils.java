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
package com.buession.redis.core.internal.lettuce;

import com.buession.core.collect.Arrays;
import com.buession.redis.core.Direction;
import com.buession.redis.core.StreamEntryId;
import io.lettuce.core.LMPopArgs;
import io.lettuce.core.LMoveArgs;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class CompositeArgumentUtils {

	private CompositeArgumentUtils() {
	}

	public static LMoveArgs lMoveArgs(final Direction source, final Direction destination) {
		if(source == null || destination == null){
			return null;
		}

		if(Direction.LEFT.equals(source)){
			return Direction.LEFT.equals(destination) ? LMoveArgs.Builder.leftLeft() : LMoveArgs.Builder.leftRight();
		}else{
			return Direction.LEFT.equals(destination) ? LMoveArgs.Builder.rightLeft() : LMoveArgs.Builder.rightRight();
		}
	}

	public static LMPopArgs lMPopArgs(final Direction direction) {
		return lMPopArgs(direction, null);
	}

	public static LMPopArgs lMPopArgs(final Direction direction, final Integer count) {
		if(direction == null){
			return null;
		}

		final LMPopArgs lmPopArgs = Direction.LEFT.equals(
				direction) ? LMPopArgs.Builder.left() : LMPopArgs.Builder.right();

		if(count != null){
			lmPopArgs.count(count);
		}

		return lmPopArgs;
	}

	public static String[] messageIds(final StreamEntryId... ids) {
		return Arrays.map(ids, String.class, StreamEntryId::toString);
	}

}

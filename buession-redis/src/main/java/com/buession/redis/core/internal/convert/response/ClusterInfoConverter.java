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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert.response;

import com.buession.core.converter.Converter;
import com.buession.core.utils.KeyValueParser;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.ClusterInfo;

/**
 * Cluster Info 命令结果转换为 {@link ClusterInfo}
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class ClusterInfoConverter implements Converter<String, ClusterInfo> {

	public final static ClusterInfoConverter INSTANCE = new ClusterInfoConverter();

	@Override
	public ClusterInfo convert(final String source) {
		String[] rows = StringUtils.split(source, "\r\n");
		KeyValueParser keyValueParser;
		ClusterInfo.State state = null;
		int slotsAssigned = 0;
		int slotsOk = 0;
		int slotsPfail = 0;
		int slotsFail = 0;
		int knownNodes = 0;
		int size = 0;
		int currentEpoch = 0;
		int myEpoch = 0;
		long messagesPingSent = 0;
		long messagesPongSent = 0;
		long messagesSent = 0;
		long messagesPingReceived = 0;
		long messagesPongReceived = 0;
		long messagesMeetReceived = 0;
		long messagesReceived = 0;

		for(String row : rows){
			keyValueParser = new KeyValueParser(row, ':');

			if(ClusterInfo.Key.STATE.getValue().equals(keyValueParser.getKey())){
				state = "ok".equalsIgnoreCase(
						keyValueParser.getValue()) ? ClusterInfo.State.OK : ClusterInfo.State.FAIL;
			}else if(ClusterInfo.Key.SLOTS_ASSIGNED.getValue().equals(keyValueParser.getKey())){
				slotsAssigned = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.SLOTS_OK.getValue().equals(keyValueParser.getKey())){
				slotsOk = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.SLOTS_PFAIL.getValue().equals(keyValueParser.getKey())){
				slotsPfail = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.SLOTS_FAIL.getValue().equals(keyValueParser.getKey())){
				slotsFail = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.KNOWN_NODES.getValue().equals(keyValueParser.getKey())){
				knownNodes = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.SIZE.getValue().equals(keyValueParser.getKey())){
				size = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.CURRENT_EPOCH.getValue().equals(keyValueParser.getKey())){
				currentEpoch = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MY_EPOCH.getValue().equals(keyValueParser.getKey())){
				myEpoch = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_PING_SENT.getValue().equals(keyValueParser.getKey())){
				messagesPingSent = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_PONG_SENT.getValue().equals(keyValueParser.getKey())){
				messagesPongSent = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_SENT.getValue().equals(keyValueParser.getKey())){
				messagesSent = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_PING_RECEIVED.getValue().equals(keyValueParser.getKey())){
				messagesPingReceived = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_PONG_RECEIVED.getValue().equals(keyValueParser.getKey())){
				messagesPongReceived = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_MEET_RECEIVED.getValue().equals(keyValueParser.getKey())){
				messagesMeetReceived = keyValueParser.getIntValue();
			}else if(ClusterInfo.Key.MESSAGES_RECEIVED.getValue().equals(keyValueParser.getKey())){
				messagesReceived = keyValueParser.getIntValue();
			}
		}

		return new ClusterInfo(state, slotsAssigned, slotsOk, slotsPfail, slotsFail, knownNodes, size, currentEpoch,
				myEpoch, messagesPingSent, messagesPongSent, messagesSent, messagesPingReceived, messagesPongReceived,
				messagesMeetReceived, messagesReceived);
	}

}

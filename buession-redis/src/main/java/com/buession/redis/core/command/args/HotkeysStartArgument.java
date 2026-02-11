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
package com.buession.redis.core.command.args;

import com.buession.core.collect.Arrays;
import com.buession.core.validator.Validate;
import com.buession.redis.utils.ArgStringBuilder;

import java.util.List;

/**
 * HOTKEYS START 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class HotkeysStartArgument {

	private Boolean cpu;

	private Boolean net;

	private Integer count;

	private Integer duration;

	private Integer sample;

	private List<String> slots;

	public HotkeysStartArgument() {

	}

	public HotkeysStartArgument(Boolean cpu, Boolean net, Integer count, Integer duration, Integer sample,
								List<String> slots) {
		this.cpu = cpu;
		this.net = net;
		this.count = count;
		this.duration = duration;
		this.sample = sample;
		this.slots = slots;
	}

	public Boolean getCpu() {
		return cpu;
	}

	public HotkeysStartArgument setCpu(Boolean cpu) {
		this.cpu = cpu;
		return this;
	}

	public Boolean getNet() {
		return net;
	}

	public HotkeysStartArgument setNet(Boolean net) {
		this.net = net;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public HotkeysStartArgument setCount(Integer count) {
		this.count = count;
		return this;
	}

	public Integer getDuration() {
		return duration;
	}

	public HotkeysStartArgument setDuration(Integer duration) {
		this.duration = duration;
		return this;
	}

	public Integer getSample() {
		return sample;
	}

	public HotkeysStartArgument setSample(Integer sample) {
		this.sample = sample;
		return this;
	}

	public List<String> getSlots() {
		return slots;
	}

	public HotkeysStartArgument setSlots(List<String> slots) {
		this.slots = slots;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(Boolean.TRUE.equals(cpu) ? "CPU" : null)
				.append(Boolean.TRUE.equals(net) ? "NET" : null).add("COUNT", count).add("DURATION", duration)
				.add("SAMPLE", sample)
				.add("SLOTS", Validate.isEmpty(slots) ? null : slots.size() + " " + Arrays.toString(slots)).build();
	}

}

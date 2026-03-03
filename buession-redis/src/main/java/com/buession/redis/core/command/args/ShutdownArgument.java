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

import com.buession.redis.utils.ArgStringBuilder;

/**
 * SHUTDOWN 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ShutdownArgument {

	private Boolean save;

	private Boolean now;

	private Boolean force;

	private Boolean abort;

	public ShutdownArgument() {
	}

	public ShutdownArgument(final Boolean save, final Boolean now, final Boolean force, final Boolean abort) {
		this.save = save;
		this.now = now;
		this.force = force;
		this.abort = abort;
	}

	public Boolean isSave() {
		return getSave();
	}

	public Boolean getSave() {
		return save;
	}

	public ShutdownArgument save() {
		return setSave(true);
	}

	public ShutdownArgument setSave(Boolean save) {
		this.save = save;
		return this;
	}

	public Boolean isNow() {
		return getNow();
	}

	public Boolean getNow() {
		return now;
	}

	public ShutdownArgument now() {
		return setNow(true);
	}

	public ShutdownArgument setNow(Boolean now) {
		this.now = now;
		return this;
	}

	public Boolean isForce() {
		return getForce();
	}

	public Boolean getForce() {
		return force;
	}

	public ShutdownArgument force() {
		return setForce(true);
	}

	public ShutdownArgument setForce(Boolean force) {
		this.force = force;
		return this;
	}

	public Boolean isAbort() {
		return getAbort();
	}

	public Boolean getAbort() {
		return abort;
	}

	public ShutdownArgument abort() {
		return setAbort(true);
	}

	public ShutdownArgument setAbort(Boolean abort) {
		this.abort = abort;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getSave() == null ? null : (Boolean.TRUE.equals(getSave()) ? "SAVE" : "NOSAVE"))
				.append(Boolean.TRUE.equals(getNow()) ? "NOW" : null)
				.append(Boolean.TRUE.equals(getForce()) ? "FORCE" : null)
				.append(Boolean.TRUE.equals(getAbort()) ? "ABORT" : null)
				.build();
	}

}

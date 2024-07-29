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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args;

import com.buession.core.utils.StringJoiner;
import com.buession.core.validator.Validate;
import com.buession.redis.core.Keyword;

/**
 * {@code CLIENT TRACKING} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ClientTracking {

	private Boolean enabled;

	private Long redirect;

	private String[] prefixes;

	private Boolean bcast;

	private Boolean optin;

	private Boolean optout;

	private Boolean noloop;

	public ClientTracking() {
	}

	public ClientTracking(final Boolean enabled) {
		this.enabled = enabled;
	}

	public ClientTracking(final Boolean enabled, final Long redirect) {
		this(enabled);
		this.redirect = redirect;
	}

	public ClientTracking(final Boolean enabled, final String[] prefixes) {
		this(enabled);
		this.prefixes = prefixes;
	}

	public ClientTracking(final Boolean enabled, final Long redirect, final String[] prefixes) {
		this(enabled, redirect);
		this.prefixes = prefixes;
	}

	public ClientTracking(final Boolean enabled, final Long redirect, final String[] prefixes, final Boolean bcast,
						  final Boolean optin, final Boolean optout, final Boolean noloop) {
		this(enabled, redirect, prefixes);
		this.bcast = bcast;
		this.optin = optin;
		this.optout = optout;
		this.noloop = noloop;
	}

	public Boolean isEnabled() {
		return getEnabled();
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void enabled() {
		this.enabled = true;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getRedirect() {
		return redirect;
	}

	public void setRedirect(Long redirect) {
		this.redirect = redirect;
	}

	public String[] getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(String[] prefixes) {
		this.prefixes = prefixes;
	}

	public Boolean isBcast() {
		return getBcast();
	}

	public Boolean getBcast() {
		return bcast;
	}

	public void bcast() {
		this.bcast = true;
	}

	public void setBcast(Boolean bcast) {
		this.bcast = bcast;
	}

	public Boolean isOptin() {
		return getOptin();
	}

	public Boolean getOptin() {
		return optin;
	}

	public void optin() {
		this.optin = true;
	}

	public void setOptin(Boolean optin) {
		this.optin = optin;
	}

	public Boolean isOptout() {
		return getOptout();
	}

	public Boolean getOptout() {
		return optout;
	}

	public void optout() {
		this.optout = true;
	}

	public void setOptout(Boolean optout) {
		this.optout = optout;
	}

	public Boolean isNoloop() {
		return getNoloop();
	}

	public Boolean getNoloop() {
		return noloop;
	}

	public void noloop() {
		this.noloop = true;
	}

	public void setNoloop(Boolean noloop) {
		this.noloop = noloop;
	}

	@Override
	public String toString() {
		final StringJoiner joiner = new StringJoiner(" ");

		if(enabled != null){
			joiner.add(enabled ? Keyword.Common.ON : Keyword.Common.OFF);
		}

		if(redirect != null){
			joiner.add("REDIRECT").add(redirect);
		}

		if(Validate.isNotEmpty(prefixes)){
			for(String prefix : prefixes){
				joiner.add("PREFIX").add(prefix);
			}
		}

		if(bcast != null){
			joiner.add("BCAST");
		}

		if(optin != null){
			joiner.add("OPTIN");
		}

		if(optout != null){
			joiner.add("OPTOUT");
		}

		if(noloop != null){
			joiner.add("NOLOOP");
		}

		return joiner.toString();
	}

}

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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.common.net;

import java.net.URI;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class HttpURI extends AbstractUserInfoURI {

	private static final long serialVersionUID = 1180132809550587384L;

	public final static String HTTP = "http";

	public final static String HTTPS = "https";

	protected String path;

	protected String queryString;

	protected Map<String, String> parameters;

	protected String fragment;

	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getQueryString(){
		return queryString;
	}

	public void setQueryString(String queryString){
		this.queryString = queryString;
	}

	public Map<String, String> getParameters(){
		return parameters;
	}

	public void setParameters(Map<String, String> parameters){
		this.parameters = parameters;
	}

	public String getFragment(){
		return fragment;
	}

	public void setFragment(String fragment){
		this.fragment = fragment;
	}

	@Override
	public URI toURI(){
		return URI.create(toString());
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder();

		sb.append(scheme).append("://");

		if(username != null){
			sb.append(username).append('@');
			if(password != null){
				sb.append(password);
			}
		}else if(password != null){
			sb.append('@').append(password);
		}

		sb.append(host);

		if(HTTP.equalsIgnoreCase(scheme)){
			if(port != Protocol.HTTP.getPort()){
				sb.append(':').append(port);
			}
		}else if(HTTPS.equalsIgnoreCase(scheme)){
			if(port != Protocol.HTTPS.getPort()){
				sb.append(':').append(port);
			}
		}

		if(path != null){
			if(path.startsWith("/") == false){
				sb.append('/');
			}
			sb.append(path);
		}else{
			sb.append('/');
		}

		if(queryString != null){
			sb.append('?').append(queryString);
		}

		if(fragment != null){
			sb.append('#').append(fragment);
		}

		return sb.toString();
	}

	public final static class Builder extends AbstractUserInfoURIBuilder<HttpURI, Builder> {

		protected String path;

		protected String queryString;

		protected String fragment;

		private Builder(){
			super();
		}

		public final static Builder getInstance(){
			return new Builder();
		}

		public Builder path(final String path){
			this.path = path;
			return this;
		}

		public Builder queryString(final String queryString){
			this.queryString = queryString;
			return this;
		}

		public Builder fragment(final String fragment){
			this.fragment = fragment;
			return this;
		}

		@Override
		public HttpURI build(){
			HttpURI httpURI = new HttpURI();

			httpURI.setScheme(scheme);
			httpURI.setHost(host);
			httpURI.setPort(port);
			httpURI.setUsername(username);
			httpURI.setPassword(password);
			httpURI.setPath(path);
			httpURI.setQueryString(queryString);
			httpURI.setParameters(parseParameters(queryString));
			httpURI.setFragment(fragment);
			httpURI.setSsl(HTTPS.equalsIgnoreCase(scheme));

			return httpURI;
		}

	}

}

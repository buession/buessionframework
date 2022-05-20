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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http;

import java.util.Set;

/**
 * <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS" target="_blank">跨源资源共享（CORS）</a> 配置
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class CorsConfig {

	/**
	 * 允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
	 */
	private Set<String> origins;

	/**
	 * 允许请求的方法
	 */
	private Set<HttpMethod> allowedMethods;

	/**
	 * 实际请求中允许携带的首部字段
	 */
	private Set<String> allowedHeaders;

	/**
	 * 允许浏览器访问的头
	 */
	private Set<String> exposedHeaders;

	/**
	 * 当浏览器的 credentials 设置为 true 时是否允许浏览器读取 response 的内容
	 */
	private Boolean allowCredentials;

	/**
	 * 指定了 preflight 请求的结果能够被缓存时间（单位：秒）
	 */
	private Long maxAge;

	/**
	 * 返回允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
	 *
	 * @return 允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
	 */
	public Set<String> getOrigins(){
		return origins;
	}

	/**
	 * 设置允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
	 *
	 * @param origins
	 * 		允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
	 */
	public void setOrigins(Set<String> origins){
		this.origins = origins;
	}

	/**
	 * 返回允许请求的方法
	 *
	 * @return 允许请求的方法
	 */
	public Set<HttpMethod> getAllowedMethods(){
		return allowedMethods;
	}

	/**
	 * 设置允许请求的方法
	 *
	 * @param allowedMethods
	 * 		允许请求的方法
	 */
	public void setAllowedMethods(Set<HttpMethod> allowedMethods){
		this.allowedMethods = allowedMethods;
	}

	/**
	 * 返回实际请求中允许携带的首部字段
	 *
	 * @return 实际请求中允许携带的首部字段
	 */
	public Set<String> getAllowedHeaders(){
		return allowedHeaders;
	}

	/**
	 * 设置实际请求中允许携带的首部字段
	 *
	 * @param allowedHeaders
	 * 		实际请求中允许携带的首部字段
	 */
	public void setAllowedHeaders(Set<String> allowedHeaders){
		this.allowedHeaders = allowedHeaders;
	}

	/**
	 * 设置允许浏览器访问的头
	 *
	 * @return 允许浏览器访问的头
	 */
	public Set<String> getExposedHeaders(){
		return exposedHeaders;
	}

	/**
	 * 返回允许浏览器访问的头
	 *
	 * @param exposedHeaders
	 * 		允许浏览器访问的头
	 */
	public void setExposedHeaders(Set<String> exposedHeaders){
		this.exposedHeaders = exposedHeaders;
	}

	/**
	 * 返回是否允许浏览器读取 response 的内容
	 *
	 * @return 当浏览器的 credentials 设置为 true 时是否允许浏览器读取 response 的内容
	 */
	public Boolean getAllowCredentials(){
		return allowCredentials;
	}

	/**
	 * 设置是否允许浏览器读取 response 的内容
	 *
	 * @param allowCredentials
	 * 		是否允许浏览器读取 response 的内容
	 */
	public void setAllowCredentials(Boolean allowCredentials){
		this.allowCredentials = allowCredentials;
	}

	/**
	 * 返回 preflight 请求的结果能够被缓存时间（单位：秒）
	 *
	 * @return preflight 请求的结果能够被缓存时间
	 */
	public Long getMaxAge(){
		return maxAge;
	}

	/**
	 * 设置 preflight 请求的结果能够被缓存时间（单位：秒）
	 *
	 * @param maxAge
	 * 		preflight 请求的结果能够被缓存时间（单位：秒）
	 */
	public void setMaxAge(Long maxAge){
		this.maxAge = maxAge;
	}

	/**
	 * {@link CorsConfig} 构建类
	 */
	public final static class Builder {

		private final static CorsConfig corsConfig = new CorsConfig();

		private Builder(){

		}

		/**
		 * 创建 {@link CorsConfig} 构建类 {@link Builder} 实例
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public static Builder create(){
			return new Builder();
		}

		/**
		 * 设置允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
		 *
		 * @param origins
		 * 		允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public Builder origins(Set<String> origins){
			corsConfig.setOrigins(origins);
			return this;
		}

		/**
		 * 设置允许请求的方法
		 *
		 * @param allowedMethods
		 * 		允许请求的方法
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public Builder allowedMethods(Set<HttpMethod> allowedMethods){
			corsConfig.setAllowedMethods(allowedMethods);
			return this;
		}

		/**
		 * 设置实际请求中允许携带的首部字段
		 *
		 * @param allowedHeaders
		 * 		实际请求中允许携带的首部字段
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public Builder allowedHeaders(Set<String> allowedHeaders){
			corsConfig.setAllowedHeaders(allowedHeaders);
			return this;
		}

		/**
		 * 返回允许浏览器访问的头
		 *
		 * @param exposedHeaders
		 * 		允许浏览器访问的头
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public Builder exposedHeaders(Set<String> exposedHeaders){
			corsConfig.setExposedHeaders(exposedHeaders);
			return this;
		}

		/**
		 * 设置是否允许浏览器读取 response 的内容
		 *
		 * @param allowCredentials
		 * 		是否允许浏览器读取 response 的内容
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public Builder allowCredentials(Boolean allowCredentials){
			corsConfig.setAllowCredentials(allowCredentials);
			return this;
		}

		/**
		 * 设置 preflight 请求的结果能够被缓存时间（单位：秒）
		 *
		 * @param maxAge
		 * 		preflight 请求的结果能够被缓存时间（单位：秒）
		 *
		 * @return {@link CorsConfig} 构建类 {@link Builder} 实例
		 */
		public Builder maxAge(Long maxAge){
			corsConfig.setMaxAge(maxAge);
			return this;
		}

		/**
		 * 构建 {@link CorsConfig}
		 *
		 * @return {@link CorsConfig} 实例
		 */
		public CorsConfig build(){
			return corsConfig;
		}

	}

}

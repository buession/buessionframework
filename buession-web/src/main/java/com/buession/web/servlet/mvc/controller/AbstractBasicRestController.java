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
package com.buession.web.servlet.mvc.controller;

import com.buession.web.mvc.Response;
import com.buession.web.mvc.controller.AbstractRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet RESTful 控制器基类
 *
 * @param <P>
 * 		主键类型
 * @param <DTO>
 * 		数据传输对象模型
 * @param <VO>
 * 		数据输出对象模型
 *
 * @author Yong.Teng
 */
public abstract class AbstractBasicRestController<P, DTO, VO> extends AbstractRestController<P, DTO, VO> {

	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response<VO> add(HttpServletRequest request, HttpServletResponse response, @RequestBody DTO e) {
		return pageNotFound(request);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Response<VO> edit(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "id") P id,
							 @RequestBody DTO e) {
		return pageNotFound(request);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Response<VO> detail(HttpServletRequest request, HttpServletResponse response,
							   @PathVariable(name = "id") P id) {
		return pageNotFound(request);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Response<VO> delete(HttpServletRequest request, HttpServletResponse response,
							   @PathVariable(name = "id") P id) {
		return pageNotFound(request);
	}

	protected Response<VO> pageNotFound(final HttpServletRequest request) {
		return pageNotFound(request.getRequestURI());
	}

}

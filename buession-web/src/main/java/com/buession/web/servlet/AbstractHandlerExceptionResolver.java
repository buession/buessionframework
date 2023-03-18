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
package com.buession.web.servlet;

import com.buession.core.collect.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractHandlerExceptionResolver
		extends org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
		implements ServletExceptionHandlerResolver {

	/**
	 * 异常属性名称
	 */
	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

	/**
	 * 默认错误视图
	 */
	private String defaultErrorView = DEFAULT_ERROR_VIEW;

	private String cacheControl = CACHE_CONTROL;

	private Map<HttpStatus, String> errorViews;

	private Map<Exception, String> exceptionViews;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final static Logger pageNotFoundLogger = LoggerFactory.getLogger(PAGE_NOT_FOUND_LOG_CATEGORY);

	/**
	 * 返回异常属性名称
	 *
	 * @return 异常属性名称
	 *
	 * @since 2.2.1
	 */
	public String getExceptionAttribute(){
		return exceptionAttribute;
	}

	/**
	 * 设置异常属性名称
	 *
	 * @param exceptionAttribute
	 * 		异常属性名称
	 *
	 * @since 2.2.1
	 */
	public void setExceptionAttribute(String exceptionAttribute){
		this.exceptionAttribute = exceptionAttribute;
	}

	/**
	 * 返回默认错误视图
	 *
	 * @return 默认错误视图
	 *
	 * @since 2.2.1
	 */
	public String getDefaultErrorView(){
		return defaultErrorView;
	}

	/**
	 * 设置默认错误视图
	 *
	 * @param defaultErrorView
	 * 		默认错误视图
	 *
	 * @since 2.2.1
	 */
	public void setDefaultErrorView(String defaultErrorView){
		this.defaultErrorView = defaultErrorView;
	}

	public String getCacheControl(){
		return cacheControl;
	}

	public void setCacheControl(String cacheControl){
		this.cacheControl = cacheControl;
	}

	public Map<HttpStatus, String> getErrorViews(){
		return errorViews;
	}

	public void setErrorViews(Map<HttpStatus, String> errorViews){
		this.errorViews = errorViews;
	}

	public Map<Exception, String> getExceptionViews(){
		return exceptionViews;
	}

	public void setExceptionViews(Map<Exception, String> exceptionViews){
		this.exceptionViews = exceptionViews;
	}

	@ExceptionHandler(value = {Throwable.class, Exception.class})
	@Nullable
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
											  @Nullable Object handler, Exception ex){
		if(logger.isErrorEnabled()){
			logger.error("Application error: {}", ex.getMessage(), ex);
		}
		ModelAndView mv = doSpecialResolveException(request, response, handler, ex);

		if(mv != null){
			return mv;
		}

		try{
			if(ex instanceof MethodArgumentNotValidException){
				return handleMethodArgumentNotValidException(request, response, handler,
						(MethodArgumentNotValidException) ex);
			}else if(ex instanceof MissingServletRequestPartException){
				return handleMissingServletRequestPartException(request, response, handler,
						(MissingServletRequestPartException) ex);
			}else if(ex instanceof BindException){
				return handleBindException(request, response, handler, (BindException) ex);
			}else if(ex instanceof TypeMismatchException){
				return handleTypeMismatchException(request, response, handler, (TypeMismatchException) ex);
			}else if(ex instanceof HttpMessageNotReadableException){
				return handleHttpMessageNotReadableException(request, response, handler,
						(HttpMessageNotReadableException) ex);
			}else if(ex instanceof MissingServletRequestParameterException){
				return handleMissingServletRequestParameterException(request, response, handler,
						(MissingServletRequestParameterException) ex);
			}else if(ex instanceof ServletRequestBindingException){
				return handleServletRequestBindingException(request, response, handler,
						(ServletRequestBindingException) ex);
			}else if(ex instanceof NoHandlerFoundException){
				return handleNoHandlerFoundException(request, response, handler, (NoHandlerFoundException) ex);
			}else if(ex instanceof HttpRequestMethodNotSupportedException){
				return handleHttpRequestMethodNotSupportedException(request, response, handler,
						(HttpRequestMethodNotSupportedException) ex);
			}else if(ex instanceof HttpMediaTypeNotAcceptableException){
				return handleHttpMediaTypeNotAcceptableException(request, response, handler,
						(HttpMediaTypeNotAcceptableException) ex);
			}else if(ex instanceof HttpMediaTypeNotSupportedException){
				return handleHttpMediaTypeNotSupportedException(request, response, handler,
						(HttpMediaTypeNotSupportedException) ex);
			}else if(ex instanceof MissingPathVariableException){
				return handleMissingPathVariableException(request, response, handler,
						(MissingPathVariableException) ex);
			}else if(ex instanceof ConversionNotSupportedException){
				return handleConversionNotSupportedException(request, response, handler,
						(ConversionNotSupportedException) ex);
			}else if(ex instanceof HttpMessageNotWritableException){
				return handleHttpMessageNotWritableException(request, response, handler,
						(HttpMessageNotWritableException) ex);
			}else if(ex instanceof AsyncRequestTimeoutException){
				return handleAsyncRequestTimeoutException(request, response, handler,
						(AsyncRequestTimeoutException) ex);
			}
		}catch(Exception handlerEx){
			if(logger.isWarnEnabled()){
				logger.warn("Failure while trying to resolve exception [{}]", ex.getClass().getName(), handlerEx);
			}
		}

		return doDefaultResolveException(request, response, handler, ex);
	}

	protected ModelAndView doSpecialResolveException(final HttpServletRequest request,
													 final HttpServletResponse response, final Object handler,
													 final Exception ex){
		return null;
	}

	protected ModelAndView doDefaultResolveException(final HttpServletRequest request,
													 final HttpServletResponse response, final Object handler,
													 final Exception ex){
		return null;
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link MethodArgumentNotValidException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleMethodArgumentNotValidException(final HttpServletRequest request,
																 final HttpServletResponse response,
																 @Nullable final Object handler,
																 final MethodArgumentNotValidException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link MissingServletRequestPartException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleMissingServletRequestPartException(final HttpServletRequest request,
																	final HttpServletResponse response,
																	@Nullable final Object handler,
																	final MissingServletRequestPartException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link BindException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleBindException(final HttpServletRequest request, final HttpServletResponse response,
											   @Nullable final Object handler, final BindException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link MissingServletRequestParameterException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleMissingServletRequestParameterException(final HttpServletRequest request,
																		 final HttpServletResponse response,
																		 @Nullable final Object handler,
																		 final MissingServletRequestParameterException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link ServletRequestBindingException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleServletRequestBindingException(final HttpServletRequest request,
																final HttpServletResponse response,
																@Nullable final Object handler,
																final ServletRequestBindingException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link TypeMismatchException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleTypeMismatchException(final HttpServletRequest request,
													   final HttpServletResponse response,
													   @Nullable final Object handler, final TypeMismatchException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 400
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link HttpMessageNotReadableException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleHttpMessageNotReadableException(final HttpServletRequest request,
																 final HttpServletResponse response,
																 @Nullable final Object handler,
																 final HttpMessageNotReadableException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 404
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link NoHandlerFoundException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleNoHandlerFoundException(final HttpServletRequest request,
														 final HttpServletResponse response,
														 @Nullable final Object handler,
														 final NoHandlerFoundException ex) throws IOException{
		pageNotFoundLogger.warn(ex.getMessage());
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 405
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link HttpRequestMethodNotSupportedException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleHttpRequestMethodNotSupportedException(final HttpServletRequest request,
																		final HttpServletResponse response,
																		@Nullable final Object handler,
																		final HttpRequestMethodNotSupportedException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex.getMessage());

		String[] supportedMethods = ex.getSupportedMethods();
		if(supportedMethods != null){
			response.setHeader("Allow", Arrays.toString(supportedMethods, ", "));
		}

		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 406
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link HttpMediaTypeNotAcceptableException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleHttpMediaTypeNotAcceptableException(final HttpServletRequest request,
																	 final HttpServletResponse response,
																	 @Nullable final Object handler,
																	 final HttpMediaTypeNotAcceptableException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 415
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link HttpMediaTypeNotSupportedException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleHttpMediaTypeNotSupportedException(final HttpServletRequest request,
																	final HttpServletResponse response,
																	@Nullable final Object handler,
																	final HttpMediaTypeNotSupportedException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);

		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if(!CollectionUtils.isEmpty(mediaTypes)){
			response.setHeader("Accept", MediaType.toString(mediaTypes));
		}

		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 500
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link MissingPathVariableException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleMissingPathVariableException(final HttpServletRequest request,
															  final HttpServletResponse response,
															  @Nullable final Object handler,
															  final MissingPathVariableException ex) throws IOException{
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 500
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link ConversionNotSupportedException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleConversionNotSupportedException(final HttpServletRequest request,
																 final HttpServletResponse response,
																 @Nullable final Object handler,
																 final ConversionNotSupportedException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 500
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link HttpMessageNotWritableException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleHttpMessageNotWritableException(final HttpServletRequest request,
																 final HttpServletResponse response,
																 @Nullable final Object handler,
																 final HttpMessageNotWritableException ex)
			throws IOException{
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return doResolve(request, response, ex);
	}

	/**
	 * Status code: 503
	 *
	 * @param request
	 *        {@link HttpServletRequest}
	 * @param response
	 *        {@link HttpServletResponse}
	 * @param handler
	 * 		the executed handler, or {@code null} if none chosen at the time of the exception (for example, if
	 * 		multipart resolution failed)
	 * @param ex
	 *        {@link AsyncRequestTimeoutException}
	 *
	 * @return 返回数据
	 *
	 * @throws IOException
	 * 		设置 Response 状态错误时抛出
	 */
	protected ModelAndView handleAsyncRequestTimeoutException(final HttpServletRequest request,
															  final HttpServletResponse response,
															  @Nullable final Object handler,
															  final AsyncRequestTimeoutException ex) throws IOException{
		if(response.isCommitted() == false){
			response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}else{
			logger.warn("Async request timed out");
		}

		return doResolve(request, response, ex);
	}

	protected boolean acceptTextHtml(final HttpServletRequest request){
		return true;
	}

	protected boolean acceptJson(final HttpServletRequest request){
		final String contentType = request.getHeader("Content-Type");
		return contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE);
	}

	protected ModelAndView createModelAndView(final HttpServletRequest request, final HttpServletResponse response,
											  final HttpStatus httpStatus, final Exception ex){
		return acceptJson(request) ? new ModelAndView(new MappingJackson2JsonView()) :
				new ModelAndView(determineViewName(request, response, ex, httpStatus));
	}

	protected ModelAndView doResolve(final HttpServletRequest request, final HttpServletResponse response,
									 final Exception ex){
		request.setAttribute("javax.servlet.error.exception", ex);

		HttpStatus httpStatus = HttpStatus.resolve(response.getStatus());

		ModelAndView mv = createModelAndView(request, response, httpStatus, ex);

		mv.addObject("state", false);
		mv.addObject("code", response.getStatus());
		mv.addObject("message", httpStatus.getReasonPhrase());
		mv.addObject("status", httpStatus);
		mv.addObject("timestamp", new Date());
		mv.addObject(getExceptionAttribute(), ex);

		applyStatusCodeIfPossible(request, response, httpStatus);

		return mv;
	}

	protected String determineViewName(final HttpServletRequest request, final HttpServletResponse response,
									   final Exception ex, final HttpStatus httpStatus){
		String viewName = null;

		if(getExceptionViews() != null){
			viewName = getExceptionViews().get(ex);
		}

		if(viewName == null && getErrorViews() != null){
			viewName = getErrorViews().get(httpStatus);
		}

		if(viewName == null){
			viewName = SERIES_VIEWS.get(httpStatus.series());
		}

		if(viewName == null && getDefaultErrorView() != null){
			if(logger.isDebugEnabled()){
				logger.debug("Resolving to default view '{}' for exception of type [{}]", getDefaultErrorView(),
						ex.getClass().getName());
			}

			viewName = getDefaultErrorView();
		}

		return viewName;
	}

	protected void applyStatusCodeIfPossible(final HttpServletRequest request, final HttpServletResponse response,
											 final HttpStatus statusCode){
		if(WebUtils.isIncludeRequest(request) == false){
			logger.debug("Applying HTTP status code {}", statusCode);

			if(getCacheControl() != null){
				response.setHeader("Cache-Control", getCacheControl());
			}
			request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, statusCode);
		}
	}

}

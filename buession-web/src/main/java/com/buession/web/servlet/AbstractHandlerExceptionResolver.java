package com.buession.web.servlet;

import com.buession.web.http.ExceptionHandlerResolver;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
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
public abstract class AbstractHandlerExceptionResolver extends org.springframework.web.servlet.handler
        .AbstractHandlerExceptionResolver implements ExceptionHandlerResolver {

    private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

    private String defaultErrorView = DEFAULT_ERROR_VIEW;

    private String cacheControl = CACHE_CONTROL;

    private Map<HttpStatus, String> errorViews;

    private Map<Exception, String> exceptionViews;

    private final static Logger logger = LoggerFactory.getLogger(AbstractHandlerExceptionResolver.class);

    private final static Logger pageNotFoundLogger = LoggerFactory.getLogger(PAGE_NOT_FOUND_LOG_CATEGORY);

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

    @Nullable
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, @Nullable
            Object handler, Exception ex){
        ModelAndView mv = doSpecialResolveException(request, response, handler, ex);

        if(mv != null){
            return mv;
        }

        try{
            if(ex instanceof HttpRequestMethodNotSupportedException){
                return handleHttpRequestMethodNotSupported(request, response, handler,
                        (HttpRequestMethodNotSupportedException) ex);
            }else if(ex instanceof HttpMediaTypeNotSupportedException){
                return handleHttpMediaTypeNotSupported(request, response, handler,
                        (HttpMediaTypeNotSupportedException) ex);
            }else if(ex instanceof HttpMediaTypeNotAcceptableException){
                return handleHttpMediaTypeNotAcceptable(request, response, handler,
                        (HttpMediaTypeNotAcceptableException) ex);
            }else if(ex instanceof MissingPathVariableException){
                return handleMissingPathVariable(request, response, handler, (MissingPathVariableException) ex);
            }else if(ex instanceof MissingServletRequestParameterException){
                return handleMissingServletRequestParameter(request, response, handler,
                        (MissingServletRequestParameterException) ex);
            }else if(ex instanceof ServletRequestBindingException){
                return handleServletRequestBindingException(request, response, handler,
                        (ServletRequestBindingException) ex);
            }else if(ex instanceof ConversionNotSupportedException){
                return handleConversionNotSupported(request, response, handler, (ConversionNotSupportedException) ex);
            }else if(ex instanceof TypeMismatchException){
                return handleTypeMismatch(request, response, handler, (TypeMismatchException) ex);
            }else if(ex instanceof HttpMessageNotReadableException){
                return handleHttpMessageNotReadable(request, response, handler, (HttpMessageNotReadableException) ex);
            }else if(ex instanceof HttpMessageNotWritableException){
                return handleHttpMessageNotWritable(request, response, handler, (HttpMessageNotWritableException) ex);
            }else if(ex instanceof MethodArgumentNotValidException){
                return handleMethodArgumentNotValidException(request, response, handler,
                        (MethodArgumentNotValidException) ex);
            }else if(ex instanceof MissingServletRequestPartException){
                return handleMissingServletRequestPartException(request, response, handler,
                        (MissingServletRequestPartException) ex);
            }else if(ex instanceof BindException){
                return handleBindException(request, response, handler, (BindException) ex);
            }else if(ex instanceof NoHandlerFoundException){
                return handleNoHandlerFoundException(request, response, handler, (NoHandlerFoundException) ex);
            }else if(ex instanceof AsyncRequestTimeoutException){
                return handleAsyncRequestTimeoutException(request, response, handler, (AsyncRequestTimeoutException)
                        ex);
            }
        }catch(Exception handlerEx){
            if(logger.isWarnEnabled()){
                logger.warn("Failure while trying to resolve exception [{}]", ex.getClass().getName(), handlerEx);
            }
        }

        return null;
    }

    protected ModelAndView doSpecialResolveException(final HttpServletRequest request, final HttpServletResponse
            response, final Object handler, final Exception ex){
        return null;
    }

    protected ModelAndView handleHttpRequestMethodNotSupported(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final HttpRequestMethodNotSupportedException ex)
            throws IOException{
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex.getMessage());

        String[] supportedMethods = ex.getSupportedMethods();
        if(supportedMethods != null){
            response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }

        return doResolve(request, response, ex);
    }

    protected ModelAndView handleHttpMediaTypeNotSupported(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final HttpMediaTypeNotSupportedException ex) throws
            IOException{
        response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);

        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if(!CollectionUtils.isEmpty(mediaTypes)){
            response.setHeader("Accept", MediaType.toString(mediaTypes));
        }

        return doResolve(request, response, ex);
    }

    protected ModelAndView handleHttpMediaTypeNotAcceptable(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final HttpMediaTypeNotAcceptableException ex)
            throws IOException{
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleMissingPathVariable(final HttpServletRequest request, final HttpServletResponse
            response, @Nullable final Object handler, final MissingPathVariableException ex) throws IOException{
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleMissingServletRequestParameter(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final MissingServletRequestParameterException ex)
            throws IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleServletRequestBindingException(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final ServletRequestBindingException ex) throws
            IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleConversionNotSupported(final HttpServletRequest request, final HttpServletResponse
            response, @Nullable final Object handler, final ConversionNotSupportedException ex) throws IOException{
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleTypeMismatch(final HttpServletRequest request, final HttpServletResponse response,
                                              @Nullable final Object handler, final TypeMismatchException ex) throws
            IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleHttpMessageNotReadable(final HttpServletRequest request, final HttpServletResponse
            response, @Nullable final Object handler, final HttpMessageNotReadableException ex) throws IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleHttpMessageNotWritable(final HttpServletRequest request, final HttpServletResponse
            response, @Nullable final Object handler, final HttpMessageNotWritableException ex) throws IOException{
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleMethodArgumentNotValidException(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final MethodArgumentNotValidException ex) throws
            IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleMissingServletRequestPartException(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final MissingServletRequestPartException ex) throws
            IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleBindException(final HttpServletRequest request, final HttpServletResponse response,
                                               @Nullable final Object handler, final BindException ex) throws
            IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleNoHandlerFoundException(final HttpServletRequest request, final HttpServletResponse
            response, @Nullable final Object handler, final NoHandlerFoundException ex) throws IOException{
        pageNotFoundLogger.warn(ex.getMessage());
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return doResolve(request, response, ex);
    }

    protected ModelAndView handleAsyncRequestTimeoutException(final HttpServletRequest request, final
    HttpServletResponse response, @Nullable final Object handler, final AsyncRequestTimeoutException ex) throws
            IOException{
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
        return request.getHeader("Content-Type").contains(MediaType.APPLICATION_JSON_VALUE);
    }

    protected ModelAndView createModelAndView(final HttpServletRequest request, final HttpServletResponse response,
                                              final HttpStatus httpStatus, final Exception ex){
        return acceptJson(request) ? new ModelAndView(new MappingJackson2JsonView()) : new ModelAndView
                (determineViewName(request, response, ex, httpStatus));
    }

    protected ModelAndView doResolve(final HttpServletRequest request, final HttpServletResponse response, final
    Exception ex){
        request.setAttribute("javax.servlet.error.exception", ex);

        HttpStatus httpStatus = HttpStatus.resolve(response.getStatus());

        ModelAndView mv = createModelAndView(request, response, httpStatus, ex);

        mv.addObject("state", false);
        mv.addObject("code", response.getStatus());
        mv.addObject("message", httpStatus.getReasonPhrase());
        mv.addObject("status", httpStatus);
        mv.addObject("timestamp", new Date());
        mv.addObject(exceptionAttribute, ex);

        applyStatusCodeIfPossible(request, response, httpStatus);

        return mv;
    }

    protected String determineViewName(final HttpServletRequest request, final HttpServletResponse response, final
    Exception ex, final HttpStatus httpStatus){
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

        if(viewName == null && defaultErrorView != null){
            if(logger.isDebugEnabled()){
                logger.debug("Resolving to default view '{}' for exception of type [{}]", defaultErrorView, ex
                        .getClass().getName());
            }

            viewName = defaultErrorView;
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

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
 * (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

/**
 * @author Yong.Teng
 */
public enum HttpStatus {

    // --- 1xx Informational start ---

    /**
     * {@code 100 Continue} (HTTP/1.1 - RFC 2616)
     */
    CONTINUE(100, "Continue"),

    /**
     * {@code 101 Switching Protocols} (HTTP/1.1 - RFC 2616)
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),

    /**
     * {@code 102 Processing} (WebDAV - RFC 2518)
     */
    PROCESSING(102, "Processing"),

    // --- 1xx Informational end ---

    // --- 2xx Success start ---

    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    OK(200, "OK"),

    /**
     * {@code 201 Created} (HTTP/1.0 - RFC 1945)
     */
    CREATED(201, "Created"),

    /**
     * {@code 202 Accepted} (HTTP/1.0 - RFC 1945)
     */
    ACCEPTED(202, "Accepted"),

    /**
     * {@code 203 Non Authoritative Information} (HTTP/1.1 - RFC 2616)
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non Authoritative Information"),

    /**
     * {@code 204 No Content} (HTTP/1.0 - RFC 1945)
     */
    NO_CONTENT(204, "No Content"),

    /**
     * {@code 205 Reset Content} (HTTP/1.1 - RFC 2616)
     */
    RESET_CONTENT(205, "Reset Content"),

    /**
     * {@code 206 Partial Content} (HTTP/1.1 - RFC 2616)
     */
    PARTIAL_CONTENT(206, "Partial Content"),

    /**
     * {@code 207 Multi-Status} (WebDAV - RFC 2518)
     * or
     * {@code 207 Partial Update OK} (HTTP/1.1 - draft-ietf-http-v11-spec-rev-01?)
     */
    MULTI_STATUS(207, "Partial Update OK"),

    // --- 2xx Success end ---

    // --- 3xx Redirection start ---

    /**
     * {@code 300 Mutliple Choices} (HTTP/1.1 - RFC 2616)
     */
    MULTIPLE_CHOICES(300, "Mutliple Choices"),

    /**
     * {@code 301 Moved Permanently} (HTTP/1.0 - RFC 1945)
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    /**
     * {@code 302 Moved Temporarily} (Sometimes {@code Found}) (HTTP/1.0 - RFC 1945)
     */
    MOVED_TEMPORARILY(302, "Moved Temporarily"),

    /**
     * {@code 303 See Other} (HTTP/1.1 - RFC 2616)
     */
    SEE_OTHER(303, "See Other"),

    /**
     * {@code 304 Not Modified} (HTTP/1.0 - RFC 1945)
     */
    NOT_MODIFIED(304, "Not Modified"),

    /**
     * {@code 305 Use Proxy} (HTTP/1.1 - RFC 2616)
     */
    USE_PROXY(305, "Use Proxy"),

    /**
     * {@code 307 Temporary Redirect} (HTTP/1.1 - RFC 2616)
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    // --- 3xx Redirection end ---

    // --- 4xx Client Error start ---

    /**
     * {@code 400 Bad Request} (HTTP/1.1 - RFC 2616)
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * {@code 401 Unauthorized} (HTTP/1.0 - RFC 1945)
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * {@code 402 Payment Required} (HTTP/1.1 - RFC 2616)
     */
    PAYMENT_REQUIRED(402, "Payment Required"),

    /**
     * {@code 403 Forbidden} (HTTP/1.0 - RFC 1945)
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * {@code 404 Not Found} (HTTP/1.0 - RFC 1945)
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * {@code 405 Method Not Allowed} (HTTP/1.1 - RFC 2616)
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    /**
     * {@code 406 Not Acceptable} (HTTP/1.1 - RFC 2616)
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),

    /**
     * {@code 407 Proxy Authentication Required} (HTTP/1.1 - RFC 2616)
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),

    /**
     * {@code 408 Request Timeout} (HTTP/1.1 - RFC 2616)
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),

    /**
     * {@code 409 Conflict} (HTTP/1.1 - RFC 2616)
     */
    CONFLICT(409, "Conflict"),

    /**
     * {@code 410 Gone} (HTTP/1.1 - RFC 2616)
     */
    GONE(410, "Gone"),

    /**
     * {@code 411 Length Required} (HTTP/1.1 - RFC 2616)
     */
    LENGTH_REQUIRED(411, "Length Required"),

    /**
     * {@code 412 Precondition Failed} (HTTP/1.1 - RFC 2616)
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),

    /**
     * {@code 413 Request Entity Too Large} (HTTP/1.1 - RFC 2616)
     */
    REQUEST_TOO_LONG(413, "Request Entity Too Large"),

    /**
     * {@code 414 Request-URI Too Long} (HTTP/1.1 - RFC 2616)
     */
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),

    /**
     * {@code 415 Unsupported Media Type} (HTTP/1.1 - RFC 2616)
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

    /**
     * {@code 416 Requested Range Not Satisfiable} (HTTP/1.1 - RFC 2616)
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),

    /**
     * {@code 417 Expectation Failed} (HTTP/1.1 - RFC 2616)
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),

    /**
     * Static constant for a 418 error.
     * {@code 418 Unprocessable Entity} (WebDAV drafts?)
     * or {@code 418 Reauthentication Required} (HTTP/1.1 drafts?)
     */
    //UNPROCESSABLE_ENTITY(418, "Reauthentication Required"),

    /**
     * Static constant for a 419 error.
     * {@code 419 Insufficient Space on Resource}
     * (WebDAV - draft-ietf-webdav-protocol-05?)
     * or {@code 419 Proxy Reauthentication Required}
     * (HTTP/1.1 drafts?)
     */
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Proxy Reauthentication Required"),

    /**
     * Static constant for a 420 error.
     * {@code 420 Method Failure}
     * (WebDAV - draft-ietf-webdav-protocol-05?)
     */
    METHOD_FAILURE(420, "Method Failure"),

    /**
     * {@code 422 Unprocessable Entity} (WebDAV - RFC 2518)
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

    /**
     * {@code 423 Locked} (WebDAV - RFC 2518)
     */
    LOCKED(423, "Locked"),

    /**
     * {@code 424 Failed Dependency} (WebDAV - RFC 2518)
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),

    // --- 4xx Client Error end ---

    // --- 5xx Server Error start ---

    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    INTERNAL_SERVER_ERROR(500, "Server Error"),

    /**
     * {@code 501 Not Implemented} (HTTP/1.0 - RFC 1945)
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),

    /**
     * {@code 502 Bad Gateway} (HTTP/1.0 - RFC 1945)
     */
    BAD_GATEWAY(502, "Bad Gateway"),

    /**
     * {@code 503 Service Unavailable} (HTTP/1.0 - RFC 1945)
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    /**
     * {@code 504 Gateway Timeout} (HTTP/1.1 - RFC 2616)
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),

    /**
     * {@code 505 HTTP Version Not Supported} (HTTP/1.1 - RFC 2616)
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),

    /**
     * {@code 507 Insufficient Storage} (WebDAV - RFC 2518)
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage");

    private int code;

    private String text;

    HttpStatus(int code, String text){
        this.code = code;
        this.text = text;
    }

    public int getCode(){
        return code;
    }

    public String getText(){
        return text;
    }
}

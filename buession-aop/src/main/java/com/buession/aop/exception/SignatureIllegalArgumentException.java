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
package com.buession.aop.exception;

import org.aspectj.lang.Signature;

/**
 * 签名错误异常
 *
 * @author Yong.Teng
 */
public class SignatureIllegalArgumentException extends IllegalArgumentException {

	private final static long serialVersionUID = 6148632731869242050L;

	/**
	 * 签名
	 */
	private final Signature signature;

	/**
	 * 构造函数，定义了默认异常信息
	 *
	 * @param signature
	 * 		签名
	 */
	public SignatureIllegalArgumentException(Signature signature){
		this(signature,
				"The join point signature is invalid: expected a MethodSignature or an AdviceSignature but was");
	}

	/**
	 * 构造函数
	 *
	 * @param signature
	 * 		签名
	 * @param message
	 * 		异常信息
	 */
	public SignatureIllegalArgumentException(Signature signature, String message){
		super(message + signature);
		this.signature = signature;
	}

	/**
	 * 构造函数
	 *
	 * @param signature
	 * 		签名
	 * @param message
	 * 		异常信息
	 * @param cause
	 *        {@link Throwable}
	 */
	public SignatureIllegalArgumentException(Signature signature, String message, Throwable cause){
		super(message + signature, cause);
		this.signature = signature;
	}

	/**
	 * 返回签名
	 *
	 * @return 签名
	 */
	public Signature getSignature(){
		return signature;
	}

}

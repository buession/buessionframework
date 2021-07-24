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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import org.junit.Test;

/**
 * @author Yong.Teng
 * @since 1.13.0
 */
public class ApacheHttpClientTest {

	@Test
	public void largeNumberRequest(){
		ApacheHttpClient httpClient = new ApacheHttpClient();

		for(int i = 0; i < 5; i++){
			try{
				Response response = httpClient.get("https://www.baidu.com");

				System.out.println(response.getBody());
				System.out.println(response.getInputStream());
			}catch(ConnectTimeoutException e){
				e.printStackTrace();
			}catch(ConnectionPoolTimeoutException e){
				e.printStackTrace();
			}catch(ReadTimeoutException e){
				e.printStackTrace();
			}catch(RequestAbortedException e){
				e.printStackTrace();
			}catch(RequestException e){
				e.printStackTrace();
			}
		}
	}

}

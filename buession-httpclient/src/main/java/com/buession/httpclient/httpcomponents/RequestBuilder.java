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
package com.buession.httpclient.httpcomponents;

import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.ObjectFormRequestBody;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.httpcomponents.convert.ChunkedInputStreamRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.EncodedFormRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.ObjectRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.RepeatableInputStreamRequestBodyConvert;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpConnect;
import org.apache.http.client.methods.HttpCopy;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpLink;
import org.apache.http.client.methods.HttpLock;
import org.apache.http.client.methods.HttpMove;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPropPatch;
import org.apache.http.client.methods.HttpPropfind;
import org.apache.http.client.methods.HttpPurge;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpReport;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUnlink;
import org.apache.http.client.methods.HttpUnlock;
import org.apache.http.client.methods.HttpView;
import org.apache.http.client.methods.HttpWrapped;

import java.util.ArrayList;

/**
 * @author Yong.Teng
 */
public class RequestBuilder {

	private String url;

	public RequestBuilder(){
	}

	public RequestBuilder(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public HttpGet get(){
		return new HttpGet(url);
	}

	public HttpPost post(){
		return new HttpPost(url);
	}

	public HttpPost post(RequestBody body){
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(parseEntity(body));
		return httpPost;
	}

	public HttpPatch patch(){
		return new HttpPatch(url);
	}

	public HttpPatch patch(RequestBody body){
		HttpPatch httpPatch = new HttpPatch(url);
		httpPatch.setEntity(parseEntity(body));
		return httpPatch;
	}

	public HttpPut put(){
		return new HttpPut(url);
	}

	public HttpPut put(RequestBody body){
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(parseEntity(body));
		return httpPut;
	}

	public HttpDelete delete(){
		return new HttpDelete(url);
	}

	public HttpConnect connect(){
		return new HttpConnect(url);
	}

	public HttpTrace trace(){
		return new HttpTrace(url);
	}

	public HttpCopy copy(){
		return new HttpCopy(url);
	}

	public HttpMove move(){
		return new HttpMove(url);
	}

	public HttpHead head(){
		return new HttpHead(url);
	}

	public HttpOptions options(){
		return new HttpOptions(url);
	}

	public HttpLink link(){
		return new HttpLink(url);
	}

	public HttpUnlink unlink(){
		return new HttpUnlink(url);
	}

	public HttpPurge purge(){
		return new HttpPurge(url);
	}

	public HttpLock lock(){
		return new HttpLock(url);
	}

	public HttpUnlock unlock(){
		return new HttpUnlock(url);
	}

	public HttpPropfind propfind(){
		return new HttpPropfind(url);
	}

	public HttpPropPatch proppatch(){
		return new HttpPropPatch(url);
	}

	public HttpPropPatch proppatch(RequestBody body){
		HttpPropPatch httpPropPatch = new HttpPropPatch(url);
		httpPropPatch.setEntity(parseEntity(body));
		return httpPropPatch;
	}

	public HttpReport report(){
		return new HttpReport(url);
	}

	public HttpReport report(RequestBody body){
		HttpReport httpReport = new HttpReport(url);
		httpReport.setEntity(parseEntity(body));
		return httpReport;
	}

	public HttpView view(){
		return new HttpView(url);
	}

	public HttpWrapped wrapped(){
		return new HttpWrapped(url);
	}

	private HttpEntity parseEntity(RequestBody data){
		if(data == null){
			return null;
		}

		if(data instanceof EncodedFormRequestBody){
			EncodedFormRequestBodyConvert convert = new EncodedFormRequestBodyConvert();
			return convert.convert((EncodedFormRequestBody) data);
		}else if(data instanceof ChunkedInputStreamRequestBody){
			ChunkedInputStreamRequestBodyConvert convert = new ChunkedInputStreamRequestBodyConvert();
			return convert.convert((ChunkedInputStreamRequestBody) data);
		}else if(data instanceof RepeatableInputStreamRequestBody){
			RepeatableInputStreamRequestBodyConvert convert = new RepeatableInputStreamRequestBodyConvert();
			return convert.convert((RepeatableInputStreamRequestBody) data);
		}else if(data instanceof ObjectFormRequestBody){
			ObjectRequestBodyConvert convert = new ObjectRequestBodyConvert();
			return convert.convert((ObjectFormRequestBody) data);
		}else{
			return new UrlEncodedFormEntity(new ArrayList<NameValuePair>(), EncodedFormRequestBody.CONTENT_TYPE
					.getCharset());
		}
	}

}

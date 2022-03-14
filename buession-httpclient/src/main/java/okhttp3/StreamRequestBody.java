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
package okhttp3;

import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * 流请求体
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamRequestBody extends okhttp3.RequestBody {

	/**
	 * {@link MediaType}
	 */
	private final MediaType contentType;

	/**
	 * 输入流
	 */
	private final InputStream inputStream;

	/**
	 * 构造函数
	 *
	 * @param contentType
	 *        {@link MediaType}
	 * @param inputStream
	 * 		输入流
	 */
	protected StreamRequestBody(final @Nullable MediaType contentType, final @Nullable InputStream inputStream){
		this.inputStream = inputStream;
		this.contentType = contentType;
	}

	/**
	 * 创建流请求体
	 *
	 * @param inputStream
	 * 		输入流
	 * @param contentType
	 *        {@link MediaType}
	 *
	 * @return 流请求体
	 */
	public static StreamRequestBody create(final @Nullable InputStream inputStream,
										   final @Nullable MediaType contentType){
		return new StreamRequestBody(contentType, inputStream);
	}

	@Nullable
	@Override
	public MediaType contentType(){
		return contentType;
	}

	@Override
	public long contentLength() throws IOException{
		try{
			return inputStream.available();
		}catch(IOException e){
			return 0;
		}
	}

	@Override
	public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException{
		Source source = null;
		try{
			source = Okio.source(inputStream);
			bufferedSink.writeAll(source);
		}finally{
			assert source != null;
			Util.closeQuietly(source);
		}
	}

}

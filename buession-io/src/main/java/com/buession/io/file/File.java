/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.io.file;

import com.buession.io.MimeType;
import org.apache.tika.Tika;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

/**
 * @author Yong.Teng
 */
public class File extends java.io.File {

	private final static long serialVersionUID = 1512573860637989192L;

	/**
	 * @param parent
	 * 		父目录
	 * @param child
	 * 		子文件
	 */
	public File(java.io.File parent, String child){
		super(parent, child);
	}

	/**
	 * @param path
	 * 		文件路径
	 */
	public File(String path){
		super(path);
	}

	/**
	 * @param file
	 * 		java.io.File
	 */
	public File(java.io.File file){
		super(file.getPath());
	}

	/**
	 * @param parent
	 * 		父目录
	 * @param child
	 * 		子文件
	 */
	public File(String parent, String child){
		super(parent, child);
	}

	/**
	 * @param uri
	 * 		URI
	 */
	public File(URI uri){
		super(uri);
	}

	public MimeType getMimeType(){
		Tika tika = new Tika();

		try{
			return MimeType.parse(tika.detect(this));
		}catch(IOException e){
		}

		return null;
	}

	/**
	 * 读取文件内容
	 *
	 * @return 文件内容
	 *
	 * @throws FileNotFoundException
	 * 		当文件不存在
	 * @throws IOException
	 * 		IO 异常
	 */
	public String read() throws FileNotFoundException, IOException{
		FileReader reader = new FileReader(this);

		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer((int) length());
		String line;

		while((line = bufferedReader.readLine()) != null){
			sb.append(line);
		}

		bufferedReader.close();
		reader.close();

		return sb.toString();
	}

	/**
	 * 向文件写内容
	 *
	 * @param str
	 * 		待写入内容
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public void write(final String str) throws IOException{
		FileWriter writer = new FileWriter(this);

		writer.write(str);
		writer.close();
	}

	/**
	 * 向文件写内容
	 *
	 * @param chars
	 * 		待写入内容
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public void write(final char[] chars) throws IOException{
		FileWriter writer = new FileWriter(this);

		writer.write(chars);
		writer.close();
	}

	/**
	 * 向文件写内容
	 *
	 * @param bytes
	 * 		待写入内容
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public void write(final byte[] bytes) throws IOException{
		write(new String(bytes));
	}

}
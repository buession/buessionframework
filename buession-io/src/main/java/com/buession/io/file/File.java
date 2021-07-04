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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.io.file;

import com.buession.core.utils.StringUtils;
import com.buession.io.MimeType;
import com.buession.lang.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * @author Yong.Teng
 */
public class File extends java.io.File {

	private final static long serialVersionUID = 1512573860637989192L;

	private MimeType mimeType;

	private String extension;

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

	/**
	 * 获取文件 MimeType
	 *
	 * @return MimeType
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public MimeType getMimeType() throws IOException{
		if(mimeType == null){
			Tika tika = new Tika();
			mimeType = MimeType.parse(tika.detect(this));
		}

		return mimeType;
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
	public byte[] read() throws FileNotFoundException, IOException{
		int size = 4096;
		BufferedInputStream bis = new FileBufferedInputStream(this);
		byte[] tempChars = new byte[size];
		int num = 0;
		ArrayList<Byte> bytes = new ArrayList<>();

		while((num = bis.read(tempChars)) != -1){
			for(int i = 0; i < num; i++){
				bytes.add(tempChars[i]);
			}
		}
		bis.close();

		Byte[] oBytes = bytes.toArray(new Byte[0]);
		byte[] result = new byte[oBytes.length];

		for(int i = 0; i < oBytes.length; i++){
			result[i] = oBytes[i];
		}

		return result;
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
		write(str.getBytes());
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
		BufferedOutputStream bos = new FileBufferedOutputStream(this);

		for(char c : chars){
			byte[] b = new byte[2];
			b[0] = (byte) ((c & 0xFF00) >> 8);
			b[1] = (byte) (c & 0xFF);

			bos.write(b);
		}

		bos.flush();
		bos.close();
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
		BufferedOutputStream bos = new FileBufferedOutputStream(this);

		bos.write(bytes);
		bos.flush();
		bos.close();
	}

	/**
	 * 向文件写内容
	 *
	 * @param str
	 * 		待写入内容
	 * @param append
	 * 		是否追加写入
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public void write(final String str, boolean append) throws IOException{
		write(str.getBytes(), append);
	}

	/**
	 * 向文件写内容
	 *
	 * @param chars
	 * 		待写入内容
	 * @param append
	 * 		是否追加写入
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public void write(final char[] chars, boolean append) throws IOException{
		BufferedOutputStream bos = new FileBufferedOutputStream(this, append);

		for(char c : chars){
			byte[] b = new byte[2];
			b[0] = (byte) ((c & 0xFF00) >> 8);
			b[1] = (byte) (c & 0xFF);

			bos.write(b);
		}

		bos.flush();
		bos.close();
	}

	/**
	 * 向文件写内容
	 *
	 * @param bytes
	 * 		待写入内容
	 * @param append
	 * 		是否追加写入
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public void write(final byte[] bytes, boolean append) throws IOException{
		BufferedOutputStream bos = new FileBufferedOutputStream(this, append);

		bos.write(bytes);
		bos.flush();
		bos.close();
	}

	/**
	 * 获取文件 MD5 值
	 *
	 * @return 文件 MD5 值
	 *
	 * @throws FileNotFoundException
	 * 		当文件不存在
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public String getMd5() throws IOException{
		if(exists() == false){
			throw new FileNotFoundException(getPath() + " not found.");
		}

		if(isFile() == false){
			throw new IOException(getPath() + " is not a file.");
		}

		FileInputStream fs = new FileInputStream(this);
		String result = DigestUtils.md5Hex(fs);

		fs.close();

		return result;
	}

	/**
	 * 获取文件 SHA1 值
	 *
	 * @return 文件 SHA1 值
	 *
	 * @throws FileNotFoundException
	 * 		当文件不存在
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public String getSha1() throws IOException{
		if(exists() == false){
			throw new FileNotFoundException(getPath() + " not found.");
		}

		if(isFile() == false){
			throw new IOException(getPath() + " is not a file.");
		}

		FileInputStream fs = new FileInputStream(this);
		String result = DigestUtils.sha1Hex(fs);

		fs.close();

		return result;
	}

	/**
	 * 获取文件扩展名
	 *
	 * @return 文件扩展名
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 1.2.0
	 */
	public String getExtension() throws IOException{
		if(isFile() == false){
			throw new IOException(getPath() + " is not a file.");
		}

		if(extension == null){
			String fileName = getName();

			if(StringUtils.endsWith(fileName, ".tar.gz")){
				extension = "tar.gz";
			}else{
				int i = fileName.lastIndexOf('.');
				extension = i == fileName.length() - 1 ? Constants.EMPTY_STRING :
						fileName.substring(i + 1).toLowerCase();
			}
		}

		return extension;
	}

	private final static class FileBufferedInputStream extends BufferedInputStream {

		public FileBufferedInputStream(java.io.File file) throws FileNotFoundException{
			super(new DataInputStream(new FileInputStream(file)));
		}

	}

	private final static class FileBufferedOutputStream extends BufferedOutputStream {

		public FileBufferedOutputStream(java.io.File file) throws FileNotFoundException{
			super(new DataOutputStream(new FileOutputStream(file)));
		}

		public FileBufferedOutputStream(java.io.File file, boolean append) throws FileNotFoundException{
			super(new DataOutputStream(new FileOutputStream(file, append)));
		}

	}

}
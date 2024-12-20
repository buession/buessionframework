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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.io.file;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.io.MimeType;
import com.buession.lang.Constants;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@link java.io.File} 扩展
 *
 * @author Yong.Teng
 */
public class File extends java.io.File {

	private final static long serialVersionUID = 1512573860637989192L;

	private static MimeTypeDetector mimeTypeDetector;

	private MimeType mimeType;

	private String extension;

	/**
	 * @param parent
	 * 		父目录
	 * @param child
	 * 		子文件
	 */
	public File(java.io.File parent, String child) {
		super(parent, child);
	}

	/**
	 * @param path
	 * 		文件路径
	 */
	public File(String path) {
		super(path);
	}

	/**
	 * @param file
	 * 		java.io.File
	 */
	public File(java.io.File file) {
		super(file.getPath());
	}

	/**
	 * @param parent
	 * 		父目录
	 * @param child
	 * 		子文件
	 */
	public File(String parent, String child) {
		super(parent, child);
	}

	/**
	 * @param uri
	 * 		URI
	 */
	public File(URI uri) {
		super(uri);
	}

	/**
	 * 获取文件 MimeType
	 *
	 * @return MimeType
	 *
	 * @since 1.2.0
	 */
	public MimeType getMimeType() {
		if(mimeType == null){
			if(mimeTypeDetector == null){
				mimeTypeDetector = new DefaultMimeTypeDetector();
			}

			mimeType = mimeTypeDetector.probe(this.toPath());
		}

		return mimeType;
	}

	/**
	 * 读取文件内容
	 *
	 * @return 文件内容
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public byte[] read() throws IOException {
		final BufferedInputStream bis = new FileBufferedInputStream(this);
		final byte[] result = new byte[(int) this.length()];

		bis.read(result);
		bis.close();

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
	public void write(final String str) throws IOException {
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
	public void write(final char[] chars) throws IOException {
		final BufferedOutputStream bos = new FileBufferedOutputStream(this);
		write(bos, chars);
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
	public void write(final byte[] bytes) throws IOException {
		final BufferedOutputStream bos = new FileBufferedOutputStream(this);

		bos.write(bytes);
		afterWrite(bos);
	}

	/**
	 * 将输入流写入文件
	 *
	 * @param stream
	 * 		输入流
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 3.0.0
	 */
	public void write(final InputStream stream) throws IOException {
		final BufferedOutputStream bos = new FileBufferedOutputStream(this);
		write(bos, stream);
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
	public void write(final String str, boolean append) throws IOException {
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
	public void write(final char[] chars, boolean append) throws IOException {
		final BufferedOutputStream bos = new FileBufferedOutputStream(this, append);
		write(bos, chars);
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
	public void write(final byte[] bytes, boolean append) throws IOException {
		final BufferedOutputStream bos = new FileBufferedOutputStream(this, append);

		bos.write(bytes);
		afterWrite(bos);
	}

	/**
	 * 将输入流写入文件
	 *
	 * @param stream
	 * 		输入流
	 * @param append
	 * 		是否追加写入
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @since 3.0.0
	 */
	public void write(final InputStream stream, boolean append) throws IOException {
		final BufferedOutputStream bos = new FileBufferedOutputStream(this, append);
		write(bos, stream);
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
	public String getMd5() throws IOException {
		if(exists() == false){
			throw new FileNotFoundException(getPath() + " not found.");
		}

		if(isFile() == false){
			throw new IOException(getPath() + " is not a file.");
		}

		final FileInputStream fis = new FileInputStream(this);
		String result = DigestUtils.md5Hex(fis);

		fis.close();

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
	public String getSha1() throws IOException {
		if(exists() == false){
			throw new FileNotFoundException(getPath() + " not found.");
		}

		if(isFile() == false){
			throw new IOException(getPath() + " is not a file.");
		}

		final FileInputStream fis = new FileInputStream(this);
		String result = DigestUtils.sha1Hex(fis);

		fis.close();

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
	public String getExtension() throws IOException {
		if(isFile() == false){
			throw new IOException(getPath() + " is not a file.");
		}

		if(extension == null){
			String fileName = getName();

			if(StringUtils.endsWith(fileName, ".tar.gz")){
				extension = "tar.gz";
			}else{
				int i = fileName.lastIndexOf('.');
				extension =
						i == fileName.length() - 1 ? Constants.EMPTY_STRING : fileName.substring(i + 1).toLowerCase();
			}
		}

		return extension;
	}

	/**
	 * 重命名文件
	 *
	 * @param newName
	 * 		新文件名
	 *
	 * @return true / false
	 *
	 * @since 2.3.2
	 */
	public boolean rename(String newName) {
		return super.renameTo(new File(this.getParent() + '/' + newName));
	}

	/**
	 * 创建软连接
	 *
	 * @param dest
	 * 		软连接目标地址
	 *
	 * @throws IOException
	 * 		if an I/O error occurs
	 * @since 2.3.3
	 */
	public void createSymbolicLink(final java.io.File dest) throws IOException {
		createSymbolicLink(dest, false);
	}

	/**
	 * 创建软连接
	 *
	 * @param dest
	 * 		软连接目标地址
	 * @param overwrite
	 * 		如果软连接目录路径存在，且为软连接，是否进行覆盖操作
	 *
	 * @throws IOException
	 * 		if an I/O error occurs
	 * @since 2.3.3
	 */
	public void createSymbolicLink(final java.io.File dest, final boolean overwrite) throws IOException {
		Assert.isNull(dest, "Destination path cloud not be null.");

		final Path destPath = dest.toPath();

		if(overwrite){
			if(java.nio.file.Files.exists(destPath) && java.nio.file.Files.isSymbolicLink(destPath)){
				java.nio.file.Files.delete(destPath);
				Files.createSymbolicLink(destPath, this.toPath());
				return;
			}
		}

		if(Files.exists(destPath) && Files.isSymbolicLink(destPath)){
			Path realDestPath = Files.readSymbolicLink(destPath);

			if(realDestPath != null && realDestPath.getFileName().equals(this.toPath().getFileName())){
				return;
			}
		}

		Files.createSymbolicLink(destPath, this.toPath());
	}

	private void write(final BufferedOutputStream bos, final char[] chars) throws IOException {
		for(char c : chars){
			byte[] b = new byte[2];
			b[0] = (byte) ((c & 0xFF00) >> 8);
			b[1] = (byte) (c & 0xFF);

			bos.write(b);
		}

		afterWrite(bos);
	}

	private void write(final BufferedOutputStream bos, final InputStream stream) throws IOException {
		final byte[] buffer = new byte[1024];
		int readSize;

		while((readSize = stream.read(buffer)) != -1){
			bos.write(buffer, 0, readSize);
		}

		afterWrite(bos);
	}

	protected static void afterWrite(final BufferedOutputStream bos) throws IOException {
		bos.flush();
		bos.close();
	}

	private final static class FileBufferedInputStream extends BufferedInputStream {

		public FileBufferedInputStream(java.io.File file) throws FileNotFoundException {
			super(new DataInputStream(new FileInputStream(file)));
		}

	}

	private final static class FileBufferedOutputStream extends BufferedOutputStream {

		public FileBufferedOutputStream(java.io.File file) throws FileNotFoundException {
			super(new DataOutputStream(new FileOutputStream(file)));
		}

		public FileBufferedOutputStream(java.io.File file, boolean append) throws FileNotFoundException {
			super(new DataOutputStream(new FileOutputStream(file, append)));
		}

	}

}
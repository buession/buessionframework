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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 可重复 {@link InputStream}
 *
 * @author Yong.Teng
 * @since 5.0.1
 */
public class RereadableInputStream extends InputStream {

	/**
	 * Default value for buffer size = 500M
	 */
	private final static int DEFAULT_MAX_BYTES_IN_MEMORY = 512 * 1024 * 1024;

	/**
	 * Input stream originally passed to the constructor.
	 */
	private final InputStream originalInputStream;

	/**
	 * The inputStream currently being used by this object to read contents;
	 * may be the original stream passed in, or a stream that reads
	 * the saved copy from a memory buffer or file.
	 */
	private InputStream inputStream;

	/**
	 * Maximum number of bytes that can be stored in memory before
	 * storage will be moved to a temporary file.
	 */
	private final int maxBytesInMemory;

	/**
	 * Whether or not we are currently reading from the byte buffer in memory
	 * Bytes are read until we've exhausted the buffered bytes and then we proceed to read from the original input stream.
	 * If the numbers of bytes read from the original stream eventually exceed maxBytesInMemory, then we'll switch to reading from a file.
	 */
	private boolean readingFromBuffer;

	/**
	 * The buffer used to store the stream's content; this storage is moved
	 * to a file when the stored data's size exceeds maxBytesInMemory.
	 * Set to null once we start writing to a file.
	 */
	private byte[] byteBuffer;

	/**
	 * The current pointer when reading from memory
	 */
	private int bufferPointer;

	/**
	 * Maximum size of the buffer that was written in previous pass(s)
	 */
	private int bufferHighWaterMark;

	/**
	 * File used to store the stream's contents; is null until the stored
	 * content's size exceeds maxBytesInMemory.
	 */
	private File storeFile;

	/**
	 * OutputStream used to save the content of the input stream in a temporary file.
	 */
	private OutputStream storeOutputStream;

	/**
	 * Specifies whether or not to close the original input stream
	 * when close() is called. Defaults to true.
	 */
	private final boolean closeOriginalStreamOnClose;

	/**
	 * Specifies whether the stream has been closed
	 */
	private boolean closed;

	private final static Logger logger = LoggerFactory.getLogger(RereadableInputStream.class);

	/**
	 * Creates a rereadable input stream  with defaults of 512*1024*1024 bytes (500M) for maxBytesInMemory
	 * and both readToEndOfStreamOnFirstRewind and closeOriginalStreamOnClose set to true
	 *
	 * @param inputStream
	 * 		stream containing the source of data
	 */
	public RereadableInputStream(InputStream inputStream) {
		this(inputStream, DEFAULT_MAX_BYTES_IN_MEMORY, true);
	}

	/**
	 * Creates a rereadable input stream defaulting to 512*1024*1024 bytes (500M) for maxBytesInMemory
	 *
	 * @param inputStream
	 * 		stream containing the source of data
	 */
	public RereadableInputStream(InputStream inputStream, boolean closeOriginalStreamOnClose) {
		this(inputStream, DEFAULT_MAX_BYTES_IN_MEMORY, closeOriginalStreamOnClose);
	}

	/**
	 * Creates a rereadable input stream  with closeOriginalStreamOnClose set to true
	 *
	 * @param inputStream
	 * 		stream containing the source of data
	 * @param maxBytesInMemory
	 * 		maximum number of bytes to use to store
	 * 		the stream's contents in memory before switching to disk; note that
	 * 		the instance will preallocate a byte array whose size is
	 * 		maxBytesInMemory.  This byte array will be made available for
	 * 		garbage collection (i.e. its reference set to null) when the
	 * 		content size exceeds the array's size, when close() is called, or
	 * 		when there are no more references to the instance.
	 */
	public RereadableInputStream(InputStream inputStream, int maxBytesInMemory) {
		this(inputStream, maxBytesInMemory, true);
	}

	/**
	 * Creates a rereadable input stream.
	 *
	 * @param inputStream
	 * 		stream containing the source of data
	 * @param maxBytesInMemory
	 * 		maximum number of bytes to use to store
	 * 		the stream's contents in memory before switching to disk; note that
	 * 		the instance will preallocate a byte array whose size is
	 * 		maxBytesInMemory.  This byte array will be made available for
	 * 		garbage collection (i.e. its reference set to null) when the
	 * 		content size exceeds the array's size, when close() is called, or
	 * 		when there are no more references to the instance.
	 */
	public RereadableInputStream(InputStream inputStream, int maxBytesInMemory, boolean closeOriginalStreamOnClose) {
		this.inputStream = inputStream;
		this.originalInputStream = inputStream;
		this.maxBytesInMemory = maxBytesInMemory;
		byteBuffer = new byte[maxBytesInMemory];
		this.closeOriginalStreamOnClose = closeOriginalStreamOnClose;
	}

	/**
	 * Reads a byte from the stream, saving it in the store if it is being read from the original stream.
	 * Implements the abstract {@link InputStream#read()}.
	 *
	 * @return the read byte, or -1 on end of stream.
	 */
	public int read() throws IOException {
		if(closed){
			throw new IOException("Stream is already closed");
		}

		int inputByte = inputStream.read();
		if(inputByte == -1 && inputStream != originalInputStream){
			// If we got EOF reading from buffer or file, switch to the original stream and get the next byte from there instead
			if(readingFromBuffer){
				readingFromBuffer = false;
				inputStream.close();  // Close the input byte stream
			}else{
				inputStream.close();  // Close the input file stream
				// start appending to the file
				storeOutputStream = new BufferedOutputStream(new FileOutputStream(storeFile, true));
			}
			// The original stream is now the current stream
			inputStream = originalInputStream;
			inputByte = inputStream.read();
		}

		if(inputByte != -1 && inputStream == originalInputStream){
			// If not EOF and reading from original stream, save the bytes we read
			saveByte(inputByte);
		}

		return inputByte;
	}

	/**
	 * "Rewinds" the stream to the beginning for rereading.
	 */
	public void rewind() throws IOException {
		if(closed){
			throw new IOException("Stream is already closed");
		}

		if(storeOutputStream != null){
			storeOutputStream.close();
			storeOutputStream = null;
		}

		// Close the byte input stream or file input stream
		if(inputStream != originalInputStream){
			inputStream.close();
		}

		bufferHighWaterMark = Math.max(bufferPointer, bufferHighWaterMark);
		bufferPointer = bufferHighWaterMark;

		if(bufferHighWaterMark > 0){
			// If we have a buffer, then we'll read from it
			if(byteBuffer != null){
				readingFromBuffer = true;
				inputStream = new ByteArrayInputStream(byteBuffer, 0, bufferHighWaterMark);
			}else{
				// No buffer, which means we've switched to a file
				inputStream = new BufferedInputStream(new FileInputStream(storeFile));
			}
		}else{
			inputStream = originalInputStream;
		}
	}

	/**
	 * Closes the input stream and removes the temporary file if one was created.
	 */
	public void close() throws IOException {
		closeStream();

		super.close();
		if(storeFile != null){
			if(storeFile.delete() == false){
				if(logger.isWarnEnabled()){
					logger.warn("Failed to delete temporary file: {}", storeFile.getAbsolutePath());
				}
			}
		}
		closed = true;
	}

	/**
	 * Saves the bytes read from the original stream to buffer or file
	 */
	private void saveByte(int inputByte) throws IOException {
		if(byteBuffer != null){
			if(bufferPointer == maxBytesInMemory){
				// Need to switch to file
				storeFile = File.createTempFile("hugfs_streamstore_", ".tmp");
				storeOutputStream = new BufferedOutputStream(new FileOutputStream(storeFile));
				// Save what we have so far in buffer
				storeOutputStream.write(byteBuffer, 0, bufferPointer);
				// Write the new byte
				storeOutputStream.write(inputByte);
				byteBuffer = null; // release for garbage collection
			}else{
				// Continue writing to buffer
				byteBuffer[bufferPointer++] = (byte) inputByte;
			}
		}else{
			storeOutputStream.write(inputByte);
		}
	}

	/**
	 * Closes the input stream currently used for reading (may either be
	 * the original stream or a memory or file stream after the first pass).
	 */
	private void closeStream() throws IOException {
		if(originalInputStream != inputStream){
			// Close the byte input stream or file input stream, if either is the current one
			inputStream.close();
		}

		if(closeOriginalStreamOnClose){
			originalInputStream.close();
		}

		if(storeOutputStream != null){
			storeOutputStream.close();
			storeOutputStream = null;
		}
	}

}

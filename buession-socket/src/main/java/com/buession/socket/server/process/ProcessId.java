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
 * | License: http://buession.buession.com.cn/LICENSE 												|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2015 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.socket.server.process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buession.core.Status;
import com.buession.core.validator.Validate;
import com.buession.io.file.File;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public final class ProcessId implements Comparable<Integer> {

	private File pathFile = null;

	private int pid = 0;

	private final static Logger logger = LoggerFactory.getLogger(ProcessId.class);

	public ProcessId(final int pid, final String path) {
		if (Validate.hasText(path) == false) {
			throw new IllegalArgumentException("PID path must be have text.");
		}

		this.pid = pid;
		pathFile = new File(path);
	}

	public ProcessId(final int pid, final Path path) {
		if (Validate.isNull(path) == true) {
			throw new IllegalArgumentException("PID path could not bue null.");
		}

		this.pid = pid;
		pathFile = new File(path.toString());
	}

	/**
	 * 获取 PID
	 * 
	 * @return PID
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * 判断进程是否就绪
	 * 
	 * @return 进程就绪，返回 true；否则，返回 false
	 */
	public boolean isReady() {
		return pathFile != null ? pathFile.exists() == true && pathFile.isFile() == true : false;
	}

	/**
	 * 获取当前进程 ID
	 * 
	 * @return 进程 ID
	 */
	public int read() {
		String pid = null;

		try {
			pid = pathFile.read();
		} catch (FileNotFoundException e) {
			logger.error("Could not read PID from file {}, {}.", pathFile, e.getMessage());
		} catch (IOException e) {
			logger.error("Could not read PID from file {}, {}.", pathFile, e.getMessage());
		}

		return pid == null ? 0 : Integer.parseInt(pid.toString());
	}

	/**
	 * 写进程 ID
	 * 
	 * @return 写入状态
	 */
	public Status write() {
		try {
			logger.debug("PID {} write to {} success.", pid, pathFile);
			return pathFile.write(toString());
		} catch (IOException e) {
			logger.error("PID write failure: {}.", e.getMessage());
		}

		return Status.FAILURE;
	}

	/**
	 * 杀死当前进程
	 * 
	 * @return 状态
	 */
	public Status kill() {
		pid = read();
		pathFile.delete();

		logger.debug("Ready kill pid {}.", pid);
		if (pid > 0) {
			final String command = "kill -9 " + read();

			synchronized (this) {
				java.lang.Process process;

				try {
					process = Runtime.getRuntime().exec(command);

					try {
						process.waitFor();
						logger.info("Kill PID: {} success.", pid);

						return Status.SUCCESS;
					} catch (InterruptedException e) {
						logger.error("Kill PID: {} failure: {}.", pid, e.getMessage());
					}
				} catch (IOException e) {
					logger.error("Kill PID: {} failure: {}", pid, e.getMessage());
				}
			}
		}

		return Status.FAILURE;
	}

	@Override
	public int compareTo(Integer pid) {
		return Integer.compare(this.pid, pid);
	}

	@Override
	public int hashCode() {
		Integer PID = new Integer(pid);
		return PID.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		ProcessId that = (ProcessId) obj;
		return pid == that.getPid();
	};

	@Override
	public String toString() {
		return pid + "";
	}

}
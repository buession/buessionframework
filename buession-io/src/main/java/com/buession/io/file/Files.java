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

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * {@link java.nio.file.Files} 扩展
 *
 * @author Yong.Teng
 */
public class Files {

	/**
	 * 更改文件所属用户组
	 *
	 * @param path
	 * 		文件路径对象
	 * @param group
	 * 		用户组
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void changeGroup(final Path path, final String group) throws IOException {
		chgrp(path, group);
	}

	/**
	 * 更改文件所属用户组
	 *
	 * @param file
	 * 		文件对象
	 * @param group
	 * 		用户组
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void changeGroup(final File file, final String group) throws IOException {
		chgrp(file, group);
	}

	/**
	 * 更改文件所属用户组
	 *
	 * @param path
	 * 		文件路径
	 * @param group
	 * 		用户组
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void changeGroup(final String path, final String group) throws IOException {
		chgrp(path, group);
	}

	/**
	 * 更改文件所属用户组
	 *
	 * @param path
	 * 		文件路径对象
	 * @param group
	 * 		用户组
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chgrp(final Path path, final String group) throws IOException {
		Assert.isNull(path, "File path cloud not be null.");
		Assert.isNull(group, "Group cloud not be null.");

		GroupPrincipal groupPrincipal = FileSystems.getDefault().getUserPrincipalLookupService()
				.lookupPrincipalByGroupName(group);
		PosixFileAttributeView view = java.nio.file.Files.getFileAttributeView(path, PosixFileAttributeView.class,
				LinkOption.NOFOLLOW_LINKS);

		Assert.isNull(view, UnsupportedOperationException::new);

		view.setGroup(groupPrincipal);
	}

	/**
	 * 更改文件所属用户组
	 *
	 * @param file
	 * 		文件对象
	 * @param group
	 * 		用户组
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chgrp(final File file, final String group) throws IOException {
		Assert.isNull(file, "File cloud not be null.");
		chgrp(file.toPath(), group);
	}

	/**
	 * 更改文件所属用户组
	 *
	 * @param path
	 * 		文件路径
	 * @param group
	 * 		用户组
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chgrp(final String path, final String group) throws IOException {
		Assert.isBlank(path, "File path must be contain entity string.");
		chgrp(new File(path), group);
	}

	/**
	 * 更改文件所属所有者
	 *
	 * @param path
	 * 		文件路径对象
	 * @param owner
	 * 		所有者名称
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void changeOwner(final Path path, final String owner) throws IOException {
		chown(path, owner);
	}

	/**
	 * 更改文件所属所有者
	 *
	 * @param file
	 * 		文件对象
	 * @param owner
	 * 		所有者名称
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void changeOwner(final File file, final String owner) throws IOException {
		chown(file, owner);
	}

	/**
	 * 更改文件所属所有者
	 *
	 * @param path
	 * 		文件路径
	 * @param owner
	 * 		所有者名称
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void changeOwner(final String path, final String owner) throws IOException {
		chown(path, owner);
	}

	/**
	 * 更改文件所属所有者
	 *
	 * @param path
	 * 		文件路径对象
	 * @param owner
	 * 		所有者名称
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chown(final Path path, final String owner) throws IOException {
		Assert.isNull(path, "File path cloud not be null.");
		Assert.isNull(owner, "Owner coult not be null.");

		java.nio.file.Files.setOwner(path,
				FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(owner));
	}

	/**
	 * 更改文件所属所有者
	 *
	 * @param file
	 * 		文件对象
	 * @param owner
	 * 		所有者名称
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chown(final File file, final String owner) throws IOException {
		Assert.isNull(file, "File cloud not be null.");
		chown(file.toPath(), owner);
	}

	/**
	 * 更改文件所属所有者
	 *
	 * @param path
	 * 		文件路径
	 * @param owner
	 * 		所有者名称
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chown(final String path, final String owner) throws IOException {
		Assert.isBlank(path, "File path must be contain entity string.");
		chown(new File(path), owner);
	}

	/**
	 * 更改文件模式，参数包含三个八进制数按顺序分别指定了所有者、所有者所在的组以及所有人的访问限制；
	 * 每一部分都可以通过加入所需的权限来计算出所要的权限；
	 * 数字 1 表示使文件可执行，数字 2 表示使文件可写，数字 4 表示使文件可读；
	 * 加入这些数字来制定所需要的权限；有关 UNIX 系统的文件权限可以阅读手册“man 1 chmod”和“man 2 chmod”；
	 * mode 不会被自动当成八进制数值，需要给 mode 前面加上 0
	 *
	 * @param path
	 * 		文件路径对象
	 * @param mode
	 * 		定文件的模式
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chmod(final Path path, final int mode) throws IOException {
		Assert.isNull(path, "File path cloud not be null.");
		chmod(path, mode2perms(mode));
	}

	/**
	 * 更改文件模式，参数包含三个八进制数按顺序分别指定了所有者、所有者所在的组以及所有人的访问限制；
	 * 每一部分都可以通过加入所需的权限来计算出所要的权限；
	 * 数字 1 表示使文件可执行，数字 2 表示使文件可写，数字 4 表示使文件可读；
	 * 加入这些数字来制定所需要的权限；有关 UNIX 系统的文件权限可以阅读手册“man 1 chmod”和“man 2 chmod”；
	 * mode 不会被自动当成八进制数值，需要给 mode 前面加上 0
	 *
	 * @param file
	 * 		文件对象
	 * @param mode
	 * 		定文件的模式
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chmod(final File file, final int mode) throws IOException {
		Assert.isNull(file, "File cloud not be null.");
		chmod(file.toPath(), mode);
	}

	/**
	 * 更改文件模式，参数包含三个八进制数按顺序分别指定了所有者、所有者所在的组以及所有人的访问限制；
	 * 每一部分都可以通过加入所需的权限来计算出所要的权限；
	 * 数字 1 表示使文件可执行，数字 2 表示使文件可写，数字 4 表示使文件可读；
	 * 加入这些数字来制定所需要的权限；有关 UNIX 系统的文件权限可以阅读手册“man 1 chmod”和“man 2 chmod”；
	 * mode 不会被自动当成八进制数值，需要给 mode 前面加上 0
	 *
	 * @param path
	 * 		文件路径
	 * @param mode
	 * 		定文件的模式
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	public static void chmod(final String path, final int mode) throws IOException {
		Assert.isBlank(path, "File path must be contain entity string.");
		chmod(new File(path), mode);
	}

	/**
	 * 更改文件权限
	 *
	 * @param path
	 * 		文件路径对象
	 * @param perms
	 * 		文件权限
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @see java.nio.file.attribute.PosixFilePermissions
	 */
	public static void chmod(final Path path, final String perms) throws IOException {
		Assert.isNull(path, "File path cloud not be null.");
		Assert.isBlank(perms, "File permission not be empty or null.");

		Set<PosixFilePermission> filePermissions = PosixFilePermissions.fromString(perms);
		java.nio.file.Files.setPosixFilePermissions(path, filePermissions);
	}

	/**
	 * 更改文件权限
	 *
	 * @param file
	 * 		文件对象
	 * @param perms
	 * 		文件权限
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @see java.nio.file.attribute.PosixFilePermissions
	 */
	public static void chmod(final File file, final String perms) throws IOException {
		Assert.isNull(file, "File cloud not be null.");
		chmod(file.toPath(), perms);
	}

	/**
	 * 更改文件权限
	 *
	 * @param path
	 * 		文件路径
	 * @param perms
	 * 		文件权限
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @see java.nio.file.attribute.PosixFilePermissions
	 */
	public static void chmod(final String path, final String perms) throws IOException {
		Assert.isBlank(path, "File path must be contain entity string.");
		chmod(new File(path), perms);
	}

	/**
	 * 将文件 mode 转换为文件权限表示
	 *
	 * @param mode
	 * 		文件 mode
	 *
	 * @return 文件权限表示
	 */
	public static String mode2perms(final int mode) {
		char[] perms = new char[9];

		perms[0] = getFilePerm(mode, 0x0100, FilePermission.READ);
		perms[1] = getFilePerm(mode, 0x0080, FilePermission.WRITE);
		if((mode & 0x0040) != 0){
			perms[2] = getFilePerm(mode, 0x0800, FilePermission.SGUID, FilePermission.EXECUTE);
		}else{
			perms[2] = (mode & 0x0800) != 0 ? 'S' : FilePermission.NONE.getValue();
		}

		perms[3] = getFilePerm(mode, 0x0020, FilePermission.READ);
		perms[4] = getFilePerm(mode, 0x0010, FilePermission.WRITE);
		if((mode & 0x0008) != 0){
			perms[5] = getFilePerm(mode, 0x0400, FilePermission.SGUID, FilePermission.EXECUTE);
		}else{
			perms[5] = (mode & 0x0400) != 0 ? 'S' : FilePermission.NONE.getValue();
		}

		perms[6] = getFilePerm(mode, 0x0004, FilePermission.READ);
		perms[7] = getFilePerm(mode, 0x0002, FilePermission.WRITE);
		if((mode & 0x0001) != 0){
			perms[8] = getFilePerm(mode, 0x0200, FilePermission.STICKY, FilePermission.EXECUTE);
		}else{
			perms[8] = (mode & 0x0200) != 0 ? 'T' : FilePermission.NONE.getValue();
		}

		return new String(perms);
	}

	private static char getFilePerm(final int mode, final int factor, final FilePermission defaultValue) {
		return getFilePerm(mode, factor, defaultValue, FilePermission.NONE);
	}

	private static char getFilePerm(final int mode, final int factor, final FilePermission t, final FilePermission f) {
		return (mode & factor) != 0 ? t.getValue() : f.getValue();
	}

}
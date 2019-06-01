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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
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
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * @author Yong.Teng
 */
public class Files {

    private final static UserPrincipalLookupService lookupService = FileSystems.getDefault()
            .getUserPrincipalLookupService();

    /**
     * 更改文件所属用户组
     *
     * @param path
     *         文件路径对象
     * @param group
     *         用户组
     *
     * @throws IOException
     *         IO 异常
     */
    public static void changeGroup(final Path path, final String group) throws IOException{
        Assert.isNull(path, "File path cloud not be null.");
        Assert.isNull(group, "Group cloud not be null.");

        GroupPrincipal groupPrincipal = lookupService.lookupPrincipalByGroupName(group);
        PosixFileAttributeView view = java.nio.file.Files.getFileAttributeView(path, PosixFileAttributeView.class,
                LinkOption.NOFOLLOW_LINKS);

        if(view == null){
            throw new UnsupportedOperationException();
        }

        view.setGroup(groupPrincipal);
    }

    /**
     * 更改文件所属用户组
     *
     * @param file
     *         文件对象
     * @param group
     *         用户组
     *
     * @throws IOException
     *         IO 异常
     */
    public static void changeGroup(final File file, final String group) throws IOException{
        Assert.isNull(file, "File cloud not be null.");
        changeGroup(file.toPath(), group);
    }

    /**
     * 更改文件所属用户组
     *
     * @param path
     *         文件路径
     * @param group
     *         用户组
     *
     * @throws IOException
     *         IO 异常
     */
    public static void changeGroup(final String path, final String group) throws IOException{
        Assert.isBlank(path, "File path must be contain entity string.");
        changeGroup(new File(path), group);
    }

    /**
     * 更改文件所属所有者
     *
     * @param path
     *         文件路径对象
     * @param owner
     *         所有者名称
     *
     * @throws IOException
     *         IO 异常
     */
    public static void changeOwner(final Path path, final String owner) throws IOException{
        Assert.isNull(path, "File path cloud not be null.");
        Assert.isNull(owner, "Owner coult not be null.");

        UserPrincipal userPrincipal = lookupService.lookupPrincipalByName(owner);

        java.nio.file.Files.setOwner(path, userPrincipal);
    }

    /**
     * 更改文件所属所有者
     *
     * @param file
     *         文件对象
     * @param owner
     *         所有者名称
     *
     * @throws IOException
     *         IO 异常
     */
    public static void changeOwner(final File file, final String owner) throws IOException{
        Assert.isNull(file, "File cloud not be null.");
        changeOwner(file.toPath(), owner);
    }

    /**
     * 更改文件所属所有者
     *
     * @param path
     *         文件路径
     * @param owner
     *         所有者名称
     *
     * @throws IOException
     *         IO 异常
     */
    public static void changeOwner(final String path, final String owner) throws IOException{
        Assert.isBlank(path, "File path must be contain entity string.");
        changeOwner(new File(path), owner);
    }

}
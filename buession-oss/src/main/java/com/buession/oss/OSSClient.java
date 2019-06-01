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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.oss;

import com.buession.oss.core.Result;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public interface OSSClient {

    String PATH_SEPARATOR = "/";

    /**
     * 上传文件
     *
     * @param bucketName
     *         Bucket 名字
     * @param file
     *         文件
     * @param path
     *         上传路径
     *
     * @return 上传结果
     */
    Result upload(final String bucketName, final File file, final String path);

    /**
     * 上传文件
     *
     * @param bucketName
     *         Bucket 名字
     * @param file
     *         文件
     * @param path
     *         上传路径
     *
     * @return 上传结果
     */
    Result upload(final String bucketName, final Path file, final String path);

    /**
     * 上传文件
     *
     * @param bucketName
     *         Bucket 名字
     * @param stream
     *         文件流
     * @param path
     *         上传路径
     *
     * @return 上传结果
     */
    Result upload(final String bucketName, final InputStream stream, final String path);

    /**
     * 图片裁剪
     *
     * @param bucketName
     *         Bucket 名字
     * @param path
     *         图片路径
     * @param width
     *         宽度
     * @param height
     *         高度
     * @param x
     *         X 坐标起点
     * @param y
     *         Y 坐标起点
     *
     * @return 裁剪结果
     */
    Result crop(final String bucketName, final String path, final int width, final int height, final int x, final int
            y);

    void close();

}

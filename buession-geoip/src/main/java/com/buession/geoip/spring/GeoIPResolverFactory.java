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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.spring;

import com.buession.core.utils.Assert;
import com.buession.geoip.DatabaseResolver;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public class GeoIPResolverFactory {

    public final static InputStream DEFAULT_STREAM = DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb");

    private File dbPath;

    private InputStream stream = DEFAULT_STREAM;

    private LoadMode loadMode = LoadMode.STREAM;

    private boolean enableCache = true;

    public File getDbPath(){
        return dbPath;
    }

    public void setDbPath(Resource dbPath) throws IOException{
        Assert.isNull(dbPath, "Ip database path cloud not be null.");

        this.dbPath = dbPath.getFile();
        this.loadMode = LoadMode.FILE;
    }

    public void setDbPath(File dbPath) throws IOException{
        Assert.isNull(dbPath, "Ip database path cloud not be null.");

        this.dbPath = dbPath;
        this.loadMode = LoadMode.FILE;
    }

    public void setDbPath(Path dbPath) throws IOException{
        Assert.isNull(dbPath, "Ip database path cloud not be null.");

        this.dbPath = dbPath.toFile();
        this.loadMode = LoadMode.FILE;
    }

    public void setDbPath(String dbPath) throws IOException{
        Assert.isBlank(dbPath, "Ip database path cloud not be null or empty.");

        setDbPath(new File(dbPath));
    }

    public InputStream getStream(){
        return stream;
    }

    public void setStream(InputStream stream){
        Assert.isNull(dbPath, "Ip database stream cloud not be null.");

        this.stream = stream;
        this.loadMode = LoadMode.STREAM;
    }

    public LoadMode getLoadMode(){
        return loadMode;
    }

    public boolean isEnableCache(){
        return enableCache;
    }

    public void setEnableCache(boolean enableCache){
        this.enableCache = enableCache;
    }

    protected enum LoadMode {

        STREAM,

        FILE

    }

}

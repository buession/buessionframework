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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.maxmind.db.CHMCache;
import com.maxmind.db.Reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Yong.Teng
 */
public class CacheDatabaseResolver extends DatabaseResolver {

	public CacheDatabaseResolver(final String database) throws IOException{
		super(database, new CHMCache());
	}

	public CacheDatabaseResolver(final String database, final Reader.FileMode fileMode) throws IOException{
		super(database, fileMode, new CHMCache());
	}

	public CacheDatabaseResolver(final File database) throws IOException{
		super(database, new CHMCache());
	}

	public CacheDatabaseResolver(final File database, final Reader.FileMode fileMode) throws IOException{
		super(database, fileMode, new CHMCache());
	}

	public CacheDatabaseResolver(final Path database) throws IOException{
		super(database, new CHMCache());
	}

	public CacheDatabaseResolver(final Path database, final Reader.FileMode fileMode) throws IOException{
		super(database, fileMode, new CHMCache());
	}

	public CacheDatabaseResolver(final InputStream source) throws IOException{
		super(source, new CHMCache());
	}

	public CacheDatabaseResolver(final InputStream source, final Reader.FileMode fileMode) throws IOException{
		super(source, fileMode, new CHMCache());
	}

}

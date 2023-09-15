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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.dao.mybatis.utils;

import com.buession.core.utils.Assert;
import com.buession.dao.mybatis.dialect.DbType;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.parsing.ParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 * @since 2.3.1
 */
public class JdbcUtils {

	private final static Map<String, DbType> JDBC_DB_TYPE_CACHE = new ConcurrentHashMap<>(4);

	private final static Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	public static DbType getDbType(Executor executor) {
		try{
			Connection conn = executor.getTransaction().getConnection();
			return JDBC_DB_TYPE_CACHE.computeIfAbsent(conn.getMetaData().getURL(),
					JdbcUtils::getDbType);
		}catch(SQLException e){
			throw new ParsingException(e);
		}
	}

	public static DbType getDbType(String jdbcUrl) {
		Assert.isBlank(jdbcUrl, "The jdbc url is empty or null, cannot read database type");

		String url = jdbcUrl.toLowerCase();
		if(url.contains(":mysql:") || url.contains(":cobar:")){
			return DbType.MYSQL;
		}else if(url.contains(":mariadb:")){
			return DbType.MARIADB;
		}else if(url.contains(":oracle:")){
			return DbType.ORACLE;
		}else if(url.contains(":sqlserver:") || url.contains(":microsoft:")){
			return DbType.SQLSERVER_2005;
		}else if(url.contains(":sqlserver2012:")){
			return DbType.SQLSERVER;
		}else if(url.contains(":postgresql:")){
			return DbType.POSTGRESQL;
		}else if(url.contains(":hsqldb:")){
			return DbType.HSQL;
		}else if(url.contains(":db2:")){
			return DbType.DB2;
		}else if(url.contains(":sqlite:")){
			return DbType.SQLITE;
		}else if(url.contains(":h2:")){
			return DbType.H2;
		}else if(url.contains(":lealone:")){
			return DbType.LEALONE;
		}else if(regexTest(":dm\\d*:", url)){
			return DbType.DM;
		}else if(url.contains(":xugu:")){
			return DbType.XU_GU;
		}else if(regexTest(":kingbase\\d*:", url)){
			return DbType.KINGBASE_ES;
		}else if(url.contains(":phoenix:")){
			return DbType.PHOENIX;
		}else if(url.contains(":zenith:")){
			return DbType.GAUSS;
		}else if(url.contains(":gbase:")){
			return DbType.GBASE;
		}else if(url.contains(":gbasedbt-sqli:") || url.contains(":informix-sqli:")){
			return DbType.GBASE_8S;
		}else if(url.contains(":ch:") || url.contains(":clickhouse:")){
			return DbType.CLICKHOUSE;
		}else if(url.contains(":oscar:")){
			return DbType.OSCAR;
		}else if(url.contains(":sybase:")){
			return DbType.SYBASE;
		}else if(url.contains(":oceanbase:")){
			return DbType.OCEANBASE;
		}else if(url.contains(":highgo:")){
			return DbType.HIGH_GO;
		}else if(url.contains(":cubrid:")){
			return DbType.CUBRID;
		}else if(url.contains(":goldilocks:")){
			return DbType.GOLDILOCKS;
		}else if(url.contains(":csiidb:")){
			return DbType.CSIIDB;
		}else if(url.contains(":sap:")){
			return DbType.SAP_HANA;
		}else if(url.contains(":impala:")){
			return DbType.IMPALA;
		}else if(url.contains(":vertica:")){
			return DbType.VERTICA;
		}else if(url.contains(":xcloud:")){
			return DbType.XCLOUD;
		}else if(url.contains(":firebirdsql:")){
			return DbType.FIREBIRD;
		}else if(url.contains(":redshift:")){
			return DbType.REDSHIFT;
		}else if(url.contains(":opengauss:")){
			return DbType.OPENGAUSS;
		}else if(url.contains(":taos:") || url.contains(":taos-rs:")){
			return DbType.TDENGINE;
		}else if(url.contains(":informix")){
			return DbType.INFORMIX;
		}else if(url.contains(":sinodb")){
			return DbType.SINODB;
		}else if(url.contains(":uxdb:")){
			return DbType.UXDB;
		}else{
			logger.warn("The jdbc url: {}, cannot read database type or the database not supported!", jdbcUrl);
			return DbType.OTHER;
		}
	}

	private static boolean regexTest(final String regex, final CharSequence str) {
		return str == null ? false : Pattern.compile(regex).matcher(str).find();
	}

}

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
package com.buession.dao.mybatis.utils;

import com.buession.core.utils.Assert;
import com.buession.dao.mybatis.dialect.*;

/**
 * 数据库方言工具类
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public class DialectUtils {

	/**
	 * 根据数据库类型获取数据库方言
	 *
	 * @param dbType
	 * 		数据库类型
	 *
	 * @return 数据库方言
	 */
	public static Dialect getDialect(final DbType dbType) {
		Assert.isNull(dbType, "DbType cloud not be null");

		return switch(dbType){
			case MYSQL -> new MySQLDialect();
			case MARIADB -> new MariaDBDialect();
			case GBASE -> new GBaseDialect();
			case GBASE_8S -> new GBase8sDialect();
			case OSCAR -> new OscarDialect();
			case XU_GU -> new XuGuDialect();
			case CLICKHOUSE -> new ClickHouseDialect();
			case OCEANBASE -> new OceanBaseDialect();
			case CUBRID -> new CubridDialect();
			case GOLDILOCKS -> new GoldiLocksDialect();
			case CSIIDB -> new CsiiDBDialect();
			case ORACLE -> new OracleDialect();
			case DM -> new DmDialect();
			case GAUSS -> new GaussDialect();
			case POSTGRESQL -> new PostgreSQLDialect();
			case H2 -> new H2Dialect();
			case LEALONE -> new LealoneDialect();
			case SQLITE -> new SQLLiteDialect();
			case HSQL -> new HSQLDialect();
			case KINGBASE_ES -> new KingBaseEsDialect();
			case PHOENIX -> new PhoenixDialect();
			case SAP_HANA -> new SAPHanaDialect();
			case IMPALA -> new ImpalaDialect();
			case HIGH_GO -> new HighGoDialect();
			case VERTICA -> new VerticaDialect();
			case REDSHIFT -> new RedShiftDialect();
			case OPENGAUSS -> new OpenGaussDialect();
			case TDENGINE -> new TDengineDialect();
			case UXDB -> new UxDBDialect();
			case FIREBIRD -> new FirebirdDialect();
			case SQLSERVER_2005 -> new SQLServer2005Dialect();
			case SQLSERVER -> new SQLServerDialect();
			case SINODB -> new SinodbDialect();
			case XCLOUD -> new XCloudDialect();
			case DB2 -> new DB2Dialect();
			case SYBASE -> new SybaseDialect();
			case INFORMIX -> new InformixDialect();
			default -> new OtherDialect();
		};
	}

	/**
	 * 根据 JDBC URL 获取数据库方言
	 *
	 * @param jdbcUrl
	 * 		JDBC URL
	 *
	 * @return 数据库方言
	 */
	public static Dialect getDialect(final String jdbcUrl) {
		return getDialect(JdbcUtils.getDbType(jdbcUrl));
	}

}

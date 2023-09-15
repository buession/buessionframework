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

		switch(dbType){
			case MYSQL:
				return new MySQLDialect();
			case MARIADB:
				return new MariaDBDialect();
			case GBASE:
				return new GBaseDialect();
			case GBASE_8S:
				return new GBase8sDialect();
			case OSCAR:
				return new OscarDialect();
			case XU_GU:
				return new XuGuDialect();
			case CLICKHOUSE:
				return new ClickHouseDialect();
			case OCEANBASE:
				return new OceanBaseDialect();
			case CUBRID:
				return new CubridDialect();
			case GOLDILOCKS:
				return new GoldiLocksDialect();
			case CSIIDB:
				return new CsiiDBDialect();
			case ORACLE:
				return new OracleDialect();
			case DM:
				return new DmDialect();
			case GAUSS:
				return new GaussDialect();
			case POSTGRESQL:
				return new PostgreSQLDialect();
			case H2:
				return new H2Dialect();
			case LEALONE:
				return new LealoneDialect();
			case SQLITE:
				return new SQLLiteDialect();
			case HSQL:
				return new HSQLDialect();
			case KINGBASE_ES:
				return new KingBaseEsDialect();
			case PHOENIX:
				return new PhoenixDialect();
			case SAP_HANA:
				return new SAPHanaDialect();
			case IMPALA:
				return new ImpalaDialect();
			case HIGH_GO:
				return new HighGoDialect();
			case VERTICA:
				return new VerticaDialect();
			case REDSHIFT:
				return new RedShiftDialect();
			case OPENGAUSS:
				return new OpenGaussDialect();
			case TDENGINE:
				return new TDengineDialect();
			case UXDB:
				return new UxDBDialect();
			case FIREBIRD:
				return new FirebirdDialect();
			case SQLSERVER_2005:
				return new SQLServer2005Dialect();
			case SQLSERVER:
				return new SQLServerDialect();
			case SINODB:
				return new SinodbDialect();
			case XCLOUD:
				return new XCloudDialect();
			case DB2:
				return new DB2Dialect();
			case SYBASE:
				return new SybaseDialect();
			case INFORMIX:
				return new InformixDialect();
			default:
				return new OtherDialect();
		}
	}

}

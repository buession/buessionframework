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
package com.buession.dao.mybatis.dialect;

/**
 * 数据库类型
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public enum DbType {

	/**
	 * MySQL
	 */
	MYSQL("MySQL"),

	/**
	 * MariaDB
	 */
	MARIADB("MariaDB"),

	/**
	 * ORACLE
	 */
	ORACLE("Oracle"),

	/**
	 * DB2
	 */
	DB2("DB2"),

	/**
	 * H2
	 */
	H2("h2"),

	/**
	 * HSQL
	 */
	HSQL("hsql"),

	/**
	 * SQLLite
	 */
	SQLITE("SQLLite"),

	/**
	 * PostgreSQL
	 */
	POSTGRESQL("PostgreSQL"),

	/**
	 * Access
	 */
	ACCESS("Access"),

	/**
	 * SqlServer2005
	 */
	SQLSERVER_2005("SqlServer2005"),

	/**
	 * SqlServer
	 */
	SQLSERVER("SqlServer"),

	/**
	 * 达梦
	 */
	DM("dm"),

	/**
	 * xugu
	 */
	XU_GU("xugu"),

	/**
	 * Kingbase
	 */
	KINGBASE_ES("kingbasees"),

	/**
	 * Phoenix
	 */
	PHOENIX("phoenix"),

	/**
	 * Gauss
	 */
	GAUSS("gauss"),

	/**
	 * ClickHouse
	 */
	CLICKHOUSE("ClickHouse"),

	/**
	 * GBase
	 */
	GBASE("GBase"),

	/**
	 * GBase-8s
	 */
	GBASE_8S("GBase-8s"),

	/**
	 * Sinodb
	 */
	SINODB("Sinodb"),

	/**
	 * Oscar
	 */
	OSCAR("Oscar"),

	/**
	 * Sybase
	 */
	SYBASE("Sybase"),

	/**
	 * OceanBase
	 */
	OCEANBASE("OceanBase"),

	/**
	 * Firebird
	 */
	FIREBIRD("Firebird"),

	/**
	 * HighGo
	 */
	HIGH_GO("HighGo"),

	/**
	 * CUBRID
	 */
	CUBRID("cubrid"),

	/**
	 * GOLDILOCKS
	 */
	GOLDILOCKS("goldilocks"),

	/**
	 * CSIIDB
	 */
	CSIIDB("csiidb"),

	/**
	 * SAP Hana
	 */
	SAP_HANA("hana"),

	/**
	 * Impala
	 */
	IMPALA("impala"),

	/**
	 * Vertica
	 */
	VERTICA("vertica"),

	/**
	 * xCloud
	 */
	XCLOUD("xcloud"),

	/**
	 * RedShift
	 */
	REDSHIFT("redshift"),

	/**
	 * openGauss
	 */
	OPENGAUSS("openGauss"),

	/**
	 * TDengine
	 */
	TDENGINE("TDengine"),

	/**
	 * Informix
	 */
	INFORMIX("Informix"),

	/**
	 * uxdb
	 */
	UXDB("uxdb"),

	/**
	 * Lealone
	 */
	LEALONE("lealone"),

	/**
	 * Other
	 */
	OTHER("other");

	/**
	 * 数据库名称
	 */
	private final String type;

	DbType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return getType();
	}

}

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
package io.lettuce.core.support;

import io.lettuce.core.api.StatefulConnection;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ConnectionUtils {

	public static <C> C wrapConnection(C connection, ObjectPoolWrapper<C> pool){
		return ConnectionWrapping.wrapConnection(connection, pool);
	}

	@SuppressWarnings("unchecked")
	public static <C extends StatefulConnection<?, ?>> C getOriginalConnection(C connection){
		if(connection instanceof ConnectionWrapping.HasTargetConnection){
			return (C) ((ConnectionWrapping.HasTargetConnection) connection).getTargetConnection();
		}else{
			return connection;
		}
	}

}

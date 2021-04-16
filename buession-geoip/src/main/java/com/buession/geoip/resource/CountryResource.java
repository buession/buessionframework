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
package com.buession.geoip.resource;

import com.buession.core.utils.KeyValueParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class CountryResource {

	private final static Map<String, String> data = new HashMap<>(255);

	private final static Logger logger = LoggerFactory.getLogger(CountryResource.class);

	public Map<String, String> getData(){
		if(data.size() == 0){
			InputStream stream = CountryResource.class.getResourceAsStream("/country.db");
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			try{
				KeyValueParser keyValueParser;

				while(reader.ready()){
					keyValueParser = new KeyValueParser(reader.readLine(), ':');
					data.put(keyValueParser.getKey(), keyValueParser.getValue());
				}
			}catch(IOException e){
				logger.error("Load dict error.", e);
			}

			try{
				stream.close();
				reader.close();
			}catch(IOException e){

			}
		}

		return data;
	}

}

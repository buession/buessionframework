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

import com.buession.geoip.model.Country;
import com.buession.geoip.model.District;
import com.buession.geoip.model.Location;
import org.junit.Test;

/**
 * @author Yong.Teng
 * @since 1.3.0
 */
public class DatabaseResolverTest {

	@Test
	public void country() throws Exception{
		DatabaseResolver resolver = new DatabaseResolver(
				DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb"));
		Country country = resolver.country("114.114.114.114");
		System.out.println(country);
	}

	@Test
	public void country1() throws Exception{
		DatabaseResolver resolver = new DatabaseResolver(
				DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb"));
		Country country = resolver.country(3739974408L);
		System.out.println(country);
	}

	@Test
	public void district() throws Exception{
		DatabaseResolver resolver = new DatabaseResolver(
				DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb"));
		District district = resolver.district("114.114.114.114");
		System.out.println(district);
	}

	@Test
	public void district1() throws Exception{
		DatabaseResolver resolver = new DatabaseResolver(
				DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb"));
		District district = resolver.district(3739974408L);
		System.out.println(district);
	}

	@Test
	public void location() throws Exception{
		DatabaseResolver resolver = new DatabaseResolver(
				DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb"));
		Location location = resolver.location("114.114.114.114");
		System.out.println(location);
	}

	@Test
	public void location1() throws Exception{
		DatabaseResolver resolver = new DatabaseResolver(
				DatabaseResolver.class.getResourceAsStream("/maxmind/City.mmdb"));
		Location location = resolver.location(3739974408L);
		System.out.println(location);
	}

}

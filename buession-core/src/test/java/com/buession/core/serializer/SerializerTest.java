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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.serializer;

import com.buession.core.serializer.type.TypeReference;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author Yong.Teng
 */
public class SerializerTest {

	private final static User user = new User();

	static{
		user.setId(1001);
		user.setUsername("buession");
	}

	@Test
	public void byteArray() throws SerializerException{
		DefaultByteArraySerializer serializer = new DefaultByteArraySerializer();

		String strRet = serializer.serialize(user);
		byte[] byteRet = serializer.serializeAsBytes(user);

		System.out.println(strRet);
		System.out.println(new String(byteRet));
		System.out.println(serializer.deserialize(strRet).toString());
		System.out.println(serializer.deserialize(byteRet).toString());
	}

	@Test
	public void fastjson() throws SerializerException{
		FastJsonJsonSerializer serializer = new FastJsonJsonSerializer();

		String strRet = serializer.serialize(user);
		byte[] byteRet = serializer.serializeAsBytes(user);

		System.out.println(strRet);
		System.out.println(new String(byteRet));

		User u11 = serializer.deserialize(strRet);
		System.out.println(u11.toString());

		User u12 = serializer.deserialize(byteRet);
		System.out.println(u12.toString());

		User u21 = serializer.deserialize(strRet, User.class);
		System.out.println(u21.toString());

		User u22 = serializer.deserialize(byteRet, User.class);
		System.out.println(u22.toString());

		User u31 = serializer.deserialize(strRet, new TypeReference<User>() {

		});
		System.out.println(u31.toString());

		User u32 = serializer.deserialize(byteRet, new TypeReference<User>() {

		});
		System.out.println(u32.toString());
	}

	@Test
	public void jsckson() throws SerializerException{
		JacksonJsonSerializer serializer = new JacksonJsonSerializer();

		String strRet = serializer.serialize(user);
		byte[] byteRet = serializer.serializeAsBytes(user);

		System.out.println(strRet);
		System.out.println(new String(byteRet));

		User u11 = serializer.deserialize(strRet);
		System.out.println(u11.toString());

		User u12 = serializer.deserialize(byteRet);
		System.out.println(u12.toString());

		User u21 = serializer.deserialize(strRet, User.class);
		System.out.println(u21.toString());

		User u22 = serializer.deserialize(byteRet, User.class);
		System.out.println(u22.toString());

		User u31 = serializer.deserialize(strRet, new TypeReference<User>() {

		});
		System.out.println(u31.toString());
	}

	@Test
	public void gson() throws SerializerException{
		GsonJsonSerializer serializer = new GsonJsonSerializer();

		String strRet = serializer.serialize(user);
		byte[] byteRet = serializer.serializeAsBytes(user);

		System.out.println(strRet);
		System.out.println(new String(byteRet));

		//User u11 = serializer.deserialize(strRet);
		// System.out.println(u11.toString());

		//User u12 = serializer.deserialize(byteRet);
		//System.out.println(u12.toString());

		User u21 = serializer.deserialize(strRet, User.class);
		System.out.println(u21.toString());

		User u22 = serializer.deserialize(byteRet, User.class);
		System.out.println(u22.toString());

		User u31 = serializer.deserialize(strRet, new TypeReference<User>() {

		});
		System.out.println(u31.toString());
	}

	private final static class User implements Serializable {

		private final static long serialVersionUID = 3932608636651582245L;

		private int id;

		private String username;

		public int getId(){
			return id;
		}

		public void setId(int id){
			this.id = id;
		}

		public String getUsername(){
			return username;
		}

		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public String toString(){
			return "User{" + "id=" + id + ", username='" + username + '\'' + '}';
		}
	}

}

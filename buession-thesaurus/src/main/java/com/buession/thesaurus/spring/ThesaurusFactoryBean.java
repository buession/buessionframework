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
package com.buession.thesaurus.spring;

import com.buession.core.utils.Assert;
import com.buession.thesaurus.Parser;
import com.buession.thesaurus.core.Type;
import com.buession.thesaurus.execption.ThesaurusTypeNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;

/**
 * @author Yong.Teng
 */
public class ThesaurusFactoryBean extends ThesaurusFactory implements FactoryBean<Parser>, InitializingBean {

	private Parser parser;

	public ThesaurusFactoryBean() {
		super();
	}

	public ThesaurusFactoryBean(Type type) {
		super(type);
	}

	public ThesaurusFactoryBean(String type) {
		super(type);
	}

	@Override
	public Parser getObject() throws Exception {
		return parser;
	}

	@Override
	public Class<? extends Parser> getObjectType() {
		return parser.getClass();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.isNull(getType(), "Thesaurus key could not be null.");

		if(parser == null){
			parser = createParser();
		}
	}

	@SuppressWarnings({"unchecked"})
	private Parser createParser() throws ThesaurusTypeNotFoundException {
		char first = getType().getId().charAt(0);

		StringBuilder sb = new StringBuilder(64);

		sb.append(Parser.class.getPackage().getName());
		sb.append('.');

		if(first >= 'a' && first <= 'z'){
			sb.append((char) (first - 32)).append(getType().getId().substring(1));
		}else{
			sb.append(getType());
		}

		sb.append("Parser");

		try{
			return BeanUtils.instantiateClass((Class<? extends Parser>) ClassUtils.forName(sb.toString(), null));
		}catch(ClassNotFoundException e){
			throw new ThesaurusTypeNotFoundException("The " + getType() + " thesaurus not be found.");
		}
	}

}

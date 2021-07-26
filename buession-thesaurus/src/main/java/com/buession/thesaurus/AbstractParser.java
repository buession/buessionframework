/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.thesaurus;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.thesaurus.core.Word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractParser implements Parser {

	@Override
	public Set<Word> parse(File file) throws IOException{
		Assert.isNull(file, "Thesaurus path is null.");
		return parse(new FileInputStream(file));
	}

	@Override
	public Set<Word> parse(Path path) throws IOException{
		Assert.isNull(path, "Thesaurus path is null.");
		return parse(path.toFile());
	}

	@Override
	public Set<Word> parse(String path) throws IOException{
		Assert.isBlank(path, "Thesaurus path is null or empty.");
		return parse(new File(path));
	}

	@Override
	public Set<Word> parse(InputStream inputStream) throws IOException{
		Assert.isNull(inputStream, "Thesaurus stream is null.");
		return doParse(inputStream);
	}

	protected abstract Set<Word> doParse(InputStream inputStream) throws IOException;

	protected static char[] parseInitials(final String str, final char delimiter){
		if(Validate.isBlank(str)){
			return null;
		}

		String[] temp = StringUtils.split(str, delimiter);
		char[] ch = new char[temp.length];

		for(int i = 0; i < temp.length; i++){
			ch[i] = temp[i].charAt(0);
		}

		return ch;
	}

}

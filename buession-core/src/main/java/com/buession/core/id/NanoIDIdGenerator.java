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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.id;

import com.buession.core.utils.Assert;

import java.security.SecureRandom;
import java.util.Random;

/**
 * jnanoid ID 生成器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class NanoIDIdGenerator implements IdGenerator<String> {

	/**
	 * 默认随机生成器
	 */
	public final static SecureRandom DEFAULT_RANDOM = new SecureRandom();

	/**
	 * 默认字符
	 */
	public final static char[] DEFAULT_ALPHABET = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/**
	 * 默认生成大小
	 */
	public final static int DEFAULT_LENGTH = 32;

	/**
	 * 随机生成器
	 *
	 * @see Random
	 */
	private Random random = DEFAULT_RANDOM;

	/**
	 * 字符
	 */
	private char[] alphabet = DEFAULT_ALPHABET;

	/**
	 * 生成长度
	 */
	private int length = DEFAULT_LENGTH;

	/**
	 * 构造函数
	 */
	public NanoIDIdGenerator(){
	}

	/**
	 * 构造函数
	 *
	 * @param random
	 * 		随机生成器
	 */
	public NanoIDIdGenerator(final Random random){
		setRandom(random);
	}

	/**
	 * 构造函数
	 *
	 * @param alphabet
	 * 		字符
	 */
	public NanoIDIdGenerator(final char[] alphabet){
		setAlphabet(alphabet);
	}

	/**
	 * 构造函数
	 *
	 * @param length
	 * 		生成长度
	 */
	public NanoIDIdGenerator(final int length){
		setLength(length);
	}

	/**
	 * 构造函数
	 *
	 * @param random
	 * 		随机生成器
	 * @param alphabet
	 * 		字符
	 */
	public NanoIDIdGenerator(final Random random, final char[] alphabet){
		this(random);
		setAlphabet(alphabet);
	}

	/**
	 * 构造函数
	 *
	 * @param random
	 * 		随机生成器
	 * @param length
	 * 		生成长度
	 */
	public NanoIDIdGenerator(final Random random, final int length){
		this(random);
		setLength(length);
	}

	/**
	 * 构造函数
	 *
	 * @param alphabet
	 * 		字符
	 * @param length
	 * 		生成长度
	 */
	public NanoIDIdGenerator(final char[] alphabet, final int length){
		this(alphabet);
		setLength(length);
	}

	/**
	 * 构造函数
	 *
	 * @param random
	 * 		随机生成器
	 * @param alphabet
	 * 		字符
	 * @param length
	 * 		生成长度
	 */
	public NanoIDIdGenerator(final Random random, final char[] alphabet, final int length){
		this(random, alphabet);
		setLength(length);
	}

	@Override
	public String nextId(){
		int mask = (2 << (int) Math.floor(Math.log((alphabet.length - 1)) / Math.log(2.0D))) - 1;
		int step = (int) Math.ceil(1.6D * (double) mask * (double) length / (double) alphabet.length);
		StringBuilder idBuilder = new StringBuilder();

		while(true){
			byte[] bytes = new byte[step];
			random.nextBytes(bytes);

			for(int i = 0; i < step; ++i){
				int alphabetIndex = bytes[i] & mask;

				if(alphabetIndex < alphabet.length){
					idBuilder.append(alphabet[alphabetIndex]);
					if(idBuilder.length() == length){
						return idBuilder.toString();
					}
				}
			}
		}
	}

	private void setRandom(final Random random){
		Assert.isNull(random, "Random cloud not be null.");
		this.random = random;
	}

	private void setAlphabet(final char[] alphabet){
		Assert.isNull(alphabet, "Alphabet cloud not be null.");

		if(alphabet.length <= 0 || alphabet.length > 255){
			throw new IllegalArgumentException("Alphabet must contain between 1 and 255 symbols.");
		}

		this.alphabet = alphabet;
	}

	private void setLength(final int length){
		Assert.isZeroNegative(length, "Size must be greater than zero.");
		this.length = length;
	}

}

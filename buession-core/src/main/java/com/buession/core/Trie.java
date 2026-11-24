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
package com.buession.core;

import com.buession.core.utils.Assert;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		类型
 *
 * @author Yong.Teng
 * @since 5.0.1
 */
public class Trie<K, V> {

	private final TrieNode<K, V> root = new TrieNode<>();

	private int maxDepth;

	/**
	 * Return the most specific value stored for this byte sequence.
	 * If not found, returns <code>null</code> or a default values as specified by
	 * calling {@link Trie#setDefaultValue}.
	 *
	 * @param keys
	 * 		-
	 *
	 * @return The most specific value stored for this byte sequence.
	 */
	@Nullable
	public V find(K[] keys) {
		return find(keys, 0, keys.length);
	}

	/**
	 * Return the most specific value stored for this byte sequence.
	 * If not found, returns <code>null</code> or a default values as specified by
	 * calling {@link Trie#setDefaultValue}.
	 *
	 * @param keys
	 * 		-
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		总数
	 *
	 * @return The most specific value stored for this byte sequence.
	 */
	@Nullable
	public V find(K[] keys, int offset, int count) {
		int maxIndex = offset + count;
		if(maxIndex > keys.length){
			throw new IndexOutOfBoundsException();
		}

		TrieNode<K, V> node = root;
		V value = node.value;
		for(int i = offset; i < maxIndex; i++){
			TrieNode<K, V> child = node.children.get(keys[i]);
			if(child == null){
				break;
			}

			node = child;
			if(node.value != null){
				value = node.value;
			}
		}

		return value;
	}

	/**
	 * Store the given value at the specified path.
	 *
	 * @param value
	 * 		值
	 * @param parts
	 * 		-
	 */
	public void addPath(V value, K[]... parts) {
		int depth = 0;
		TrieNode<K, V> node = root;

		for(K[] part : parts){
			for(K b : part){
				TrieNode<K, V> child = node.children.get(b);
				if(child == null){
					child = new TrieNode<>();
					node.children.put(b, child);
				}
				node = child;
				depth++;
			}
		}

		if(depth == 0){
			throw new IllegalArgumentException("Parts must contain at least one byte.");
		}

		node.setValue(value);
		maxDepth = Math.max(maxDepth, depth);
	}

	/**
	 * Sets the default value to use in {@link Trie#find(K[])} when no path matches.
	 *
	 * @param defaultValue
	 * 		The default value
	 */
	public void setDefaultValue(V defaultValue) {
		root.setValue(defaultValue);
	}

	/**
	 * Gets the maximum depth stored in this trie.
	 *
	 * @return The maximum depth stored in this trie.
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * A node in the trie. Has children and may have an associated value.
	 */
	static class TrieNode<K, V> {

		private final Map<K, TrieNode<K, V>> children = new HashMap<>();

		private V value = null;

		public void setValue(V value) {
			Assert.notNull(this.value, "Value already set for this trie node");
			this.value = value;
		}

	}

}

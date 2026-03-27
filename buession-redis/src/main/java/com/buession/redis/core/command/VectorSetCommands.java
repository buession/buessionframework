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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.RawVector;
import com.buession.redis.core.VSimScoreAttribs;
import com.buession.redis.core.VectorInfo;
import com.buession.redis.core.command.args.vectorset.VAddArgument;
import com.buession.redis.core.command.args.vectorset.VSimArgument;

import java.util.List;
import java.util.Map;

/**
 * Vector Set 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=vector_set" target="_blank">https://redis.io/docs/latest/commands/?group=vector_set</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface VectorSetCommands extends RedisCommands {

	/**
	 * Add a new element into the vector set specified by key
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vadd/" target="_blank">https://redis.io/docs/latest/commands/vadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 *
	 * @return 添加结果
	 */
	Status vAdd(final String key, final double[] vectors, final String element);

	/**
	 * Add a new element into the vector set specified by key
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vadd/" target="_blank">https://redis.io/docs/latest/commands/vadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 *
	 * @return 添加结果
	 */
	Status vAdd(final byte[] key, final double[] vectors, final byte[] element);

	/**
	 * Add a new element into the vector set specified by key
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vadd/" target="_blank">https://redis.io/docs/latest/commands/vadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 * @param argument
	 * 		参数
	 *
	 * @return 添加结果
	 */
	Status vAdd(final String key, final double[] vectors, final String element, final VAddArgument argument);

	/**
	 * Add a new element into the vector set specified by key
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vadd/" target="_blank">https://redis.io/docs/latest/commands/vadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 * @param argument
	 * 		参数
	 *
	 * @return 添加结果
	 */
	Status vAdd(final byte[] key, final double[] vectors, final byte[] element, final VAddArgument argument);

	/**
	 * Return the number of elements in the specified vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vcard/" target="_blank">https://redis.io/docs/latest/commands/vcard/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The number of elements in the specified vector set.
	 */
	Long vCard(final String key);

	/**
	 * Return the number of elements in the specified vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vcard/" target="_blank">https://redis.io/docs/latest/commands/vcard/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The number of elements in the specified vector set.
	 */
	Long vCard(final byte[] key);

	/**
	 * Return the number of dimensions of the vectors in the specified vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vdim/" target="_blank">https://redis.io/docs/latest/commands/vdim/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The number of dimensions of the vectors in the specified vector set.
	 */
	Long vDim(final String key);

	/**
	 * Return the number of dimensions of the vectors in the specified vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vdim/" target="_blank">https://redis.io/docs/latest/commands/vdim/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The number of dimensions of the vectors in the specified vector set.
	 */
	Long vDim(final byte[] key);

	/**
	 * Return the approximate vector associated with a given element in the vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vemb/" target="_blank">https://redis.io/docs/latest/commands/vemb/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The approximate vector associated with a given element in the vector set.
	 */
	List<Double> vEmb(final String key, final String element);

	/**
	 * Return the approximate vector associated with a given element in the vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vemb/" target="_blank">https://redis.io/docs/latest/commands/vemb/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The approximate vector associated with a given element in the vector set.
	 */
	List<Double> vEmb(final byte[] key, final byte[] element);

	/**
	 * Return the approximate vector associated with a given element in the vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vemb/" target="_blank">https://redis.io/docs/latest/commands/vemb/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The approximate vector associated with a given element in the vector set.
	 */
	RawVector vembRaw(final String key, final String element);

	/**
	 * Return the approximate vector associated with a given element in the vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vemb/" target="_blank">https://redis.io/docs/latest/commands/vemb/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The approximate vector associated with a given element in the vector set.
	 */
	RawVector vembRaw(final byte[] key, final byte[] element);

	/**
	 * Return the JSON attributes associated with an element in a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vgetattr/" target="_blank">https://redis.io/docs/latest/commands/vgetattr/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The JSON attributes associated with an element in a vector set.
	 */
	String vGetAttr(final String key, final String element);

	/**
	 * Return the JSON attributes associated with an element in a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vgetattr/" target="_blank">https://redis.io/docs/latest/commands/vgetattr/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The JSON attributes associated with an element in a vector set.
	 */
	byte[] vGetAttr(final byte[] key, final byte[] element);

	/**
	 * Return metadata and internal details about a vector set, including size, dimensions, quantization type, and graph structure.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vinfo/" target="_blank">https://redis.io/docs/latest/commands/vinfo/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The metadata and internal details about a vector set, including size, dimensions, quantization type, and graph structure.
	 */
	VectorInfo vInfo(final String key);

	/**
	 * Return metadata and internal details about a vector set, including size, dimensions, quantization type, and graph structure.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vinfo/" target="_blank">https://redis.io/docs/latest/commands/vinfo/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The metadata and internal details about a vector set, including size, dimensions, quantization type, and graph structure.
	 */
	VectorInfo vInfo(final byte[] key);

	/**
	 * Check if an element exists in a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vismember/" target="_blank">https://redis.io/docs/latest/commands/vismember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return True / False
	 */
	Boolean vIsMember(final String key, final String element);

	/**
	 * Check if an element exists in a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vismember/" target="_blank">https://redis.io/docs/latest/commands/vismember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return True / False
	 */
	Boolean vIsMember(final byte[] key, final byte[] element);

	/**
	 * Return the neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vlinks/" target="_blank">https://redis.io/docs/latest/commands/vlinks/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 */
	List<String> vLinks(final String key, final String element);

	/**
	 * Return the neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vlinks/" target="_blank">https://redis.io/docs/latest/commands/vlinks/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 */
	List<byte[]> vLinks(final byte[] key, final byte[] element);

	/**
	 * Return the neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vlinks/" target="_blank">https://redis.io/docs/latest/commands/vlinks/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 */
	Map<String, Double> vLinksWithScores(final String key, final String element);

	/**
	 * Return the neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vlinks/" target="_blank">https://redis.io/docs/latest/commands/vlinks/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element whose vector you want to retrieve.
	 *
	 * @return The neighbors of a specified element in a vector set. The command shows the connections for each layer of the HNSW graph.
	 */
	Map<byte[], Double> vLinksWithScores(final byte[] key, final byte[] element);

	/**
	 * Return one or more random elements from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrandmember/" target="_blank">https://redis.io/docs/latest/commands/vrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The random elements from a vector set.
	 */
	String vRandMember(final String key);

	/**
	 * Return one or more random elements from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrandmember/" target="_blank">https://redis.io/docs/latest/commands/vrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The random elements from a vector set.
	 */
	byte[] vRandMember(final byte[] key);

	/**
	 * Return one or more random elements from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrandmember/" target="_blank">https://redis.io/docs/latest/commands/vrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 *
	 * @return The random elements from a vector set.
	 */
	List<String> vRandMember(final String key, final int count);

	/**
	 * Return one or more random elements from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrandmember/" target="_blank">https://redis.io/docs/latest/commands/vrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 *
	 * @return The random elements from a vector set.
	 */
	List<byte[]> vRandMember(final byte[] key, final int count);

	/**
	 * The VRANGE command provides a stateless iterator for the elements inside a vector set.
	 * It allows you to retrieve all the elements inside a vector set in small amounts for each call, without an explicit cursor,
	 * and with guarantees about what you will miss in case the vector set is changing (elements added and/or removed) during the iteration.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrange/" target="_blank">https://redis.io/docs/latest/commands/vrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		The starting point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range (e.g., [Redis)
	 * 		2).A string prefixed with ( for exclusive range (e.g., (a7)
	 * 		3).The special symbol - to indicate the minimum element
	 * @param end
	 * 		The ending point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range
	 * 		2).A string prefixed with ( for exclusive range
	 * 		3).The special symbol + to indicate the maximum element
	 *
	 * @return The elements in lexicographical order, using byte-by-byte comparison (like memcmp()) to establish a total order among elements
	 *
	 */
	List<String> vRange(final String key, final String start, final String end);

	/**
	 * The VRANGE command provides a stateless iterator for the elements inside a vector set.
	 * It allows you to retrieve all the elements inside a vector set in small amounts for each call, without an explicit cursor,
	 * and with guarantees about what you will miss in case the vector set is changing (elements added and/or removed) during the iteration.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrange/" target="_blank">https://redis.io/docs/latest/commands/vrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		The starting point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range (e.g., [Redis)
	 * 		2).A string prefixed with ( for exclusive range (e.g., (a7)
	 * 		3).The special symbol - to indicate the minimum element
	 * @param end
	 * 		The ending point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range
	 * 		2).A string prefixed with ( for exclusive range
	 * 		3).The special symbol + to indicate the maximum element
	 *
	 * @return The elements in lexicographical order, using byte-by-byte comparison (like memcmp()) to establish a total order among elements
	 */
	List<byte[]> vRange(final byte[] key, final byte[] start, final byte[] end);

	/**
	 * The VRANGE command provides a stateless iterator for the elements inside a vector set.
	 * It allows you to retrieve all the elements inside a vector set in small amounts for each call, without an explicit cursor,
	 * and with guarantees about what you will miss in case the vector set is changing (elements added and/or removed) during the iteration.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrange/" target="_blank">https://redis.io/docs/latest/commands/vrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		The starting point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range (e.g., [Redis)
	 * 		2).A string prefixed with ( for exclusive range (e.g., (a7)
	 * 		3).The special symbol - to indicate the minimum element
	 * @param end
	 * 		The ending point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range
	 * 		2).A string prefixed with ( for exclusive range
	 * 		3).The special symbol + to indicate the maximum element
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements in lexicographical order, using byte-by-byte comparison (like memcmp()) to establish a total order among elements
	 *
	 */
	List<String> vRange(final String key, final String start, final String end, final int count);

	/**
	 * The VRANGE command provides a stateless iterator for the elements inside a vector set.
	 * It allows you to retrieve all the elements inside a vector set in small amounts for each call, without an explicit cursor,
	 * and with guarantees about what you will miss in case the vector set is changing (elements added and/or removed) during the iteration.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrange/" target="_blank">https://redis.io/docs/latest/commands/vrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		The starting point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range (e.g., [Redis)
	 * 		2).A string prefixed with ( for exclusive range (e.g., (a7)
	 * 		3).The special symbol - to indicate the minimum element
	 * @param end
	 * 		The ending point of the lexicographical range. Can be:
	 * 		1).A string prefixed with [ for inclusive range
	 * 		2).A string prefixed with ( for exclusive range
	 * 		3).The special symbol + to indicate the maximum element
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements in lexicographical order, using byte-by-byte comparison (like memcmp()) to establish a total order among elements
	 */
	List<byte[]> vRange(final byte[] key, final byte[] start, final byte[] end, final int count);

	/**
	 * Remove an element from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrem/" target="_blank">https://redis.io/docs/latest/commands/vrem/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 *
	 * @return 操作结果
	 */
	Status vRem(final String key, final String element);

	/**
	 * Remove an element from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vrem/" target="_blank">https://redis.io/docs/latest/commands/vrem/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 *
	 * @return 操作结果
	 */
	Status vRem(final byte[] key, final byte[] element);

	/**
	 * Remove an element from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsetattr/" target="_blank">https://redis.io/docs/latest/commands/vsetattr/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 * @param value
	 * 		is a valid JSON string. Use an empty string ("") to delete the attributes.
	 *
	 * @return 操作结果
	 */
	Status vSetAttr(final String key, final String element, final String value);

	/**
	 * Remove an element from a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsetattr/" target="_blank">https://redis.io/docs/latest/commands/vsetattr/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		is the name of the element that is being added to the vector set
	 * @param value
	 * 		is a valid JSON string. Use an empty string ("") to delete the attributes.
	 *
	 * @return 操作结果
	 */
	Status vSetAttr(final byte[] key, final byte[] element, final byte[] value);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final double... vectors);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final double... vectors);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final String element);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final byte[] element);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final double[] vectors, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final double[] vectors, final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final double[] vectors, final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final double[] vectors, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final double[] vectors, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final String element, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final String element, final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final byte[] element, final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<String> vSim(final String key, final String element, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	List<byte[]> vSim(final byte[] key, final byte[] element, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final double... vectors);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final double... vectors);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final String element);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final double[] vectors, final VSimArgument argument,
									   final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final VSimArgument argument,
									   final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final double[] vectors, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final double[] vectors, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final String element, final VSimArgument argument,
									   final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final VSimArgument argument,
									   final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, Double> vSimWithScores(final String key, final String element, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], Double> vSimWithScores(final byte[] key, final byte[] element, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double... vectors);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double... vectors);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
															final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
															final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors,
															final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors,
															final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final double[] vectors, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param vectors
	 * 		-
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final double[] vectors, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
															final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
															final VSimArgument argument);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element,
															final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element,
															final VSimArgument argument, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param count
	 * 		返回数量
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<String, VSimScoreAttribs> vSimWithScoresWithAttribs(final String key, final String element, final int count);

	/**
	 * Return elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/vsim/" target="_blank">https://redis.io/docs/latest/commands/vsim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param element
	 * 		-
	 * @param count
	 * 		返回数量
	 * 		-
	 *
	 * @return The elements similar to a given vector or element. Use this command to perform approximate or exact similarity searches within a vector set.
	 */
	Map<byte[], VSimScoreAttribs> vSimWithScoresWithAttribs(final byte[] key, final byte[] element, final int count);

}

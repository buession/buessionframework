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
package com.buession.core.collect;

import com.buession.core.utils.StringUtils;
import com.buession.lang.Constants;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数组工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Arrays extends org.apache.commons.lang3.ArrayUtils {

	public final static String DEFAULT_GLUE = ", ";

	/**
	 * 检查 byte 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final byte[] a, final byte value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 byte 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final byte[] a, final short value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 byte 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final byte[] a, final int value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 byte 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final byte[] a, final long value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 char 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final char[] a, final char value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 short 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final short[] a, final short value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 short 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final short[] a, final int value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 short 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final short[] a, final long value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 int 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final int[] a, final short value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 int 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final int[] a, final int value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 int 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final int[] a, final long value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 long 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final long[] a, final short value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 long 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final long[] a, final int value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 long 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final long[] a, final long value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 float 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final float[] a, final float value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 float 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final float[] a, final double value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 double 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final double[] a, final float value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 double 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final double[] a, final double value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查 boolean 数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final boolean[] a, final boolean value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 检查数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 数组中存在该值，则返回 true；否则，返回 false
	 */
	public static boolean contains(final Object[] a, final Object value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 byte 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final byte value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final short value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final int value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final long value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final byte value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final short value, int startIndex){
		return indexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final int value, int startIndex){
		return indexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final byte[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = startIndex; i < a.length; i++){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 char 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final char[] a, final char value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 char 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final char[] a, final char value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 short 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final short[] a, final short value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 short 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final short[] a, final int value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 short 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final short[] a, final long value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 short 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final short[] a, final short value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 short 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final short[] a, final int value, int startIndex){
		return indexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 short 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final short[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Short.MIN_VALUE && value <= Short.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = startIndex; i < a.length; i++){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 int 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final int[] a, final short value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 int 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final int[] a, final int value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 int 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final int[] a, final long value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 int 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final int[] a, final short value, int startIndex){
		return indexOf(a, (int) value, startIndex);
	}

	/**
	 * 获取指定值在 int 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final int[] a, final int value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 int 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final int[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = startIndex; i < a.length; i++){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 long 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final long[] a, final short value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 long 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final long[] a, final int value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 long 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final long[] a, final long value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 long 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final long[] a, final short value, int startIndex){
		return indexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 long 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final long[] a, final int value, int startIndex){
		return indexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 long 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final long[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 float 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final float[] a, final float value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 float 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final float[] a, final double value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 float 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final float[] a, final float value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 float 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final float[] a, final double value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Float.MIN_VALUE && value <= Float.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = startIndex; i < a.length; i++){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 double 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final double[] a, final float value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 double 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final double[] a, final double value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 double 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final double[] a, final float value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 double 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final double[] a, final double value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 boolean 数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final boolean[] a, final boolean value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 boolean 数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static int indexOf(final boolean[] a, final boolean value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = startIndex; i < a.length; i++){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 byte 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final byte value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final short value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final int value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final long value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final byte value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final short value, int startIndex){
		return lastIndexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final int value, int startIndex){
		return lastIndexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 byte 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final byte[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = a.length - 1; i >= startIndex; i--){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 char 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final char[] a, final char value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 char 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final char[] a, final char value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 short 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final short[] a, final short value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 short 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final short[] a, final int value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 short 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final short[] a, final long value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 short 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final short[] a, final short value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 short 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final short[] a, final int value, int startIndex){
		return lastIndexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 short 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final short[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Short.MIN_VALUE && value <= Short.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = a.length - 1; i >= startIndex; i--){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 int 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final int[] a, final short value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 int 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final int[] a, final int value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 int 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final int[] a, final long value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 int 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final int[] a, final short value, int startIndex){
		return lastIndexOf(a, (int) value, startIndex);
	}

	/**
	 * 获取指定值在 int 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final int[] a, final int value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 int 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final int[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = a.length - 1; i >= startIndex; i--){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 long 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final long[] a, final short value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 long 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final long[] a, final int value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 long 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final long[] a, final long value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 long 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final long[] a, final short value, int startIndex){
		return lastIndexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 long 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final long[] a, final int value, int startIndex){
		return lastIndexOf(a, (long) value, startIndex);
	}

	/**
	 * 获取指定值在 long 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final long[] a, final long value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 float 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final float[] a, final float value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 float 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final float[] a, final double value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 float 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final float[] a, final float value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 float 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final float[] a, final double value, int startIndex){
		if(a != null && a.length > 0){
			if(value >= Float.MIN_VALUE && value <= Float.MAX_VALUE){
				if(startIndex < 0){
					startIndex = 0;
				}

				for(int i = a.length - 1; i >= startIndex; i--){
					if(value == a[i]){
						return i;
					}
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 double 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final double[] a, final float value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 double 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final double[] a, final double value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 double 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final double[] a, final float value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 double 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final double[] a, final double value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在 boolean 数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final boolean[] a, final boolean value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在 boolean 数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static int lastIndexOf(final boolean[] a, final boolean value, int startIndex){
		if(a != null && a.length > 0){
			if(startIndex < 0){
				startIndex = 0;
			}

			for(int i = a.length - 1; i >= startIndex; i--){
				if(value == a[i]){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 将 byte 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final byte[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 byte 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final byte[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 char 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final char[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 char 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final char[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 short 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final short[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 short 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final short[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 int 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final int[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 int 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final int[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 long 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final long[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 long 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final long[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringUtils.join(a, glue);
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 float 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final float[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 float 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final float[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 double 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final double[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 double 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final double[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 boolean 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final boolean[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 boolean 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final boolean[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				if(i > 0){
					sb.append(glue);
				}
				sb.append(a[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * 将 O 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final O[] a){
		return StringUtils.join(a, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final O[] a, final String glue){
		return StringUtils.join(a, glue);
	}

	/**
	 * 将 byte 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Byte> toList(final byte[] a){
		if(a == null){
			return null;
		}

		Byte[] temp = new Byte[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return java.util.Arrays.asList(temp);
	}

	/**
	 * 将 char 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Character> toList(final char[] a){
		if(a == null){
			return null;
		}

		Character[] temp = new Character[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return java.util.Arrays.asList(temp);
	}

	/**
	 * 将 short 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Short> toList(final short[] a){
		if(a == null){
			return null;
		}

		Short[] temp = new Short[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return java.util.Arrays.asList(temp);
	}

	/**
	 * 将 int 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Integer> toList(final int[] a){
		return a == null ? null : java.util.Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	/**
	 * 将 long 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Long> toList(final long[] a){
		return a == null ? null : java.util.Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	/**
	 * 将 float 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Float> toList(final float[] a){
		if(a == null){
			return null;
		}

		Float[] temp = new Float[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return java.util.Arrays.asList(temp);
	}

	/**
	 * 将 double 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Double> toList(final double[] a){
		return a == null ? null : java.util.Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	/**
	 * 将 boolean 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Boolean> toList(final boolean[] a){
		if(a == null){
			return null;
		}

		Boolean[] temp = new Boolean[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return java.util.Arrays.asList(temp);
	}

	/**
	 * 将数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 转换结果
	 */
	public static <O> List<O> toList(final O[] a){
		return a == null ? null : java.util.Arrays.asList(a);
	}

	/**
	 * 将 byte 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Byte> toSet(final byte[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 char 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Character> toSet(final char[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 short 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Short> toSet(final short[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 int 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Integer> toSet(final int[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 long 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Long> toSet(final long[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 float 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Float> toSet(final float[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 double 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Double> toSet(final double[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 boolean 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Boolean> toSet(final boolean[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 转换结果
	 */
	public static <O> Set<O> toSet(final O[] a){
		return a == null ? null : new LinkedHashSet<>(java.util.Arrays.asList(a));
	}

	/**
	 * 将 byte 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		byte 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final byte[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 char 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		char 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final char[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 short 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		short 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final short[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 int 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		int 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final int[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 long 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		long 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final long[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 float 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final float[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 double 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final double[] a){
		return toStringArray(toObject(a));
	}

	/**
	 * 将 boolean 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final boolean[] a){
		return toStringArray(toObject(a));
	}

}

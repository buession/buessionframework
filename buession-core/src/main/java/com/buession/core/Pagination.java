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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 *
 * @param <E>
 * 		数据类型
 *
 * @author Yong.Teng
 */
public class Pagination<E> implements Serializable {

	private final static long serialVersionUID = 1135345190741903064L;

	/**
	 * 默认每页大小
	 */
	public final static int PAGESIZE = 15;

	/**
	 * 当前页码
	 */
	private int page = 1;

	/**
	 * 每页大小
	 */
	private int pagesize = PAGESIZE;

	/**
	 * 前一页页码
	 */
	private int previousPage = 1;

	/**
	 * 下一页页码
	 */
	private int nextPage = 1;

	/**
	 * 总页码
	 */
	private int totalPages = 1;

	/**
	 * 总记录数
	 */
	private long totalRecords = 0;

	/**
	 * 数据
	 */
	private List<E> data = null;

	/**
	 * Constructs with default configuration.
	 */
	public Pagination() {
	}

	/**
	 * Constructs with page and pagesize.
	 *
	 * @param page
	 * 		当前页码
	 * @param pagesize
	 * 		每页大小
	 */
	public Pagination(int page, int pagesize) {
		setPagesize(pagesize);
		setPage(page);
	}

	/**
	 * Constructs with page, pagesize and totalRecords.
	 *
	 * @param page
	 * 		当前页码
	 * @param pagesize
	 * 		每页大小
	 * @param totalRecords
	 * 		总记录数
	 */
	public Pagination(int page, int pagesize, long totalRecords) {
		setPagesize(pagesize);
		setTotalRecords(totalRecords);
		setPage(page);
	}

	/**
	 * 返回当前页码
	 *
	 * @return 当前页码
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置当前页码
	 *
	 * @param page
	 * 		当前页码
	 */
	public void setPage(int page) {
		this.page = page < 1 ? 1 : (totalPages > 1 && page > totalPages ? totalPages : page);
	}

	/**
	 * 返回每页大小
	 *
	 * @return 每页大小
	 */
	public int getPagesize() {
		return pagesize;
	}

	/**
	 * 设置每页大小
	 *
	 * @param pagesize
	 * 		每页大小
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize < 1 ? PAGESIZE : pagesize;
	}

	/**
	 * 返回前一页页码
	 *
	 * @return 前一页页码
	 */
	public int getPreviousPage() {
		return previousPage;
	}

	/**
	 * 设置前一页页码
	 *
	 * @param previousPage
	 * 		前一页页码
	 */
	public void setPreviousPage(int previousPage) {
		this.previousPage = (page <= 1 ? 1 : previousPage);
	}

	/**
	 * 返回下一页页码
	 *
	 * @return 下一页页码
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * 设置下一页页码
	 *
	 * @param nextPage
	 * 		下一页页码
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = totalPages < page ? page : nextPage;
	}

	/**
	 * 返回总页码
	 *
	 * @return 总页码
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * 设置总页码
	 *
	 * @param totalPages
	 * 		总页码
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = Math.max(totalPages, 1);
	}

	/**
	 * 返回总记录数
	 *
	 * @return 总记录数
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 设置总记录数
	 *
	 * @param totalRecords
	 * 		总记录数
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords < 0 ? 0 : totalRecords;

		setTotalPages((int) Math.ceil((double) this.totalRecords / pagesize));
		setPreviousPage(page - 1);
		setNextPage(page + 1);
	}

	/**
	 * 返回结果数据
	 *
	 * @return 结果数据
	 */
	public List<E> getData() {
		return data;
	}

	/**
	 * 设置结果数据
	 *
	 * @param data
	 * 		结果数据
	 */
	public void setData(List<E> data) {
		this.data = data;
	}

}
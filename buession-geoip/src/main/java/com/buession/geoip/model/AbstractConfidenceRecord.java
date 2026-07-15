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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.model;

/**
 * Geo Confidence 基类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractConfidenceRecord extends AbstractNameRecord implements ConfidenceRecord {

	private final static long serialVersionUID = 3508859347813651644L;

	/**
	 * A value from 0-100 indicating MaxMind's confidence that the item is correct.
	 */
	private final Integer confidence;

	/**
	 * 构造函数
	 *
	 * @param geoNameId
	 * 		名称 ID
	 * @param originalName
	 * 		原始名称
	 * @param name
	 * 		名称
	 * @param confidence
	 * 		A value from 0-100 indicating MaxMind's confidence that the item is correct.
	 */
	public AbstractConfidenceRecord(final Long geoNameId, final String originalName, final String name,
									final Integer confidence) {
		super(geoNameId, originalName, name);
		this.confidence = confidence;
	}

	@Override
	public Integer getConfidence() {
		return confidence;
	}

}

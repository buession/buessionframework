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
package com.buession.velocity.servlet;

import com.buession.core.converter.mapper.PropertyMapper;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * @author Yong.Teng
 */
public class VelocityLayoutViewResolver extends VelocityViewResolver {

	private String layoutUrl;

	private String layoutKey;

	private String screenContentKey;

	public String getLayoutUrl() {
		return layoutUrl;
	}

	public void setLayoutUrl(String layoutUrl) {
		this.layoutUrl = layoutUrl;
	}

	public String getLayoutKey() {
		return layoutKey;
	}

	public void setLayoutKey(String layoutKey) {
		this.layoutKey = layoutKey;
	}

	public String getScreenContentKey() {
		return screenContentKey;
	}

	public void setScreenContentKey(String screenContentKey) {
		this.screenContentKey = screenContentKey;
	}

	@Override
	protected Class<?> requiredViewClass() {
		return VelocityLayoutView.class;
	}

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final VelocityLayoutView view = (VelocityLayoutView) super.buildView(viewName);

		propertyMapper.from(layoutKey).to(view::setLayoutKey);
		propertyMapper.from(layoutUrl).to(view::setLayoutUrl);
		propertyMapper.from(screenContentKey).to(view::setScreenContentKey);

		return view;
	}

}

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
package com.buession.velocity.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.util.Optional;

/**
 * @author Yong.Teng
 */
public class VelocityViewResolver extends AbstractTemplateViewResolver {

	private String encoding;

	private String toolboxConfigLocation;

	private String dateToolAttribute;

	private String numberToolAttribute;

	private final static Logger logger = LoggerFactory.getLogger(VelocityViewResolver.class);

	public VelocityViewResolver() {
		setViewClass(requiredViewClass());
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getToolboxConfigLocation() {
		return toolboxConfigLocation;
	}

	public void setToolboxConfigLocation(String toolboxConfigLocation) {
		this.toolboxConfigLocation = toolboxConfigLocation;
	}

	public String getDateToolAttribute() {
		return dateToolAttribute;
	}

	public void setDateToolAttribute(String dateToolAttribute) {
		this.dateToolAttribute = dateToolAttribute;
	}

	public String getNumberToolAttribute() {
		return numberToolAttribute;
	}

	public void setNumberToolAttribute(String numberToolAttribute) {
		this.numberToolAttribute = numberToolAttribute;
	}

	@Override
	protected Class<?> requiredViewClass() {
		return VelocityView.class;
	}

	@Override
	protected void initApplicationContext() {
		super.initApplicationContext();

		if(toolboxConfigLocation == null){
			return;
		}

		if(VelocityView.class.isAssignableFrom(getViewClass())){
			logger.info("Using VelocityToolboxView instead of default VelocityView due to specified " +
					"toolboxConfigLocation.");
			setViewClass(VelocityToolboxView.class);
		}else if(VelocityToolboxView.class.isAssignableFrom(getViewClass()) == false){
			String message = String.format(
					"Given view class [%s] is not of type [%s], which it needs to be in case of" +
							" a specified toolboxConfigLocation.", getViewClass().getName(),
					VelocityToolboxView.class.getName());
			throw new IllegalArgumentException(message);
		}
	}

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityView view = (VelocityView) super.buildView(viewName);

		view.setDateToolAttribute(dateToolAttribute);
		view.setNumberToolAttribute(numberToolAttribute);

		Optional.ofNullable(toolboxConfigLocation).ifPresent(((VelocityToolboxView) view)::setToolboxConfigLocation);

		return view;
	}

}

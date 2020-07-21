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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.servlet;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;

import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.Locale;

/**
 * @author Yong.Teng
 */
public class VelocityLayoutView extends VelocityToolboxView {

	/**
	 * The default {@link #setLayoutUrl(String) layout url}.
	 */
	public final static String DEFAULT_LAYOUT_URL = "layout.vm";

	/**
	 * The default {@link #setLayoutKey(String) layout key}.
	 */
	public final static String DEFAULT_LAYOUT_KEY = "layout";

	/**
	 * The default {@link #setScreenContentKey(String) screen content key}.
	 */
	public final static String DEFAULT_SCREEN_CONTENT_KEY = "screen_content";

	private String layoutUrl = DEFAULT_LAYOUT_URL;

	private String layoutKey = DEFAULT_LAYOUT_KEY;

	private String screenContentKey = DEFAULT_SCREEN_CONTENT_KEY;

	private final static Logger logger = LoggerFactory.getLogger(VelocityLayoutView.class);

	public String getLayoutUrl(){
		return layoutUrl;
	}

	public void setLayoutUrl(String layoutUrl){
		this.layoutUrl = layoutUrl;
	}

	public String getLayoutKey(){
		return layoutKey;
	}

	public void setLayoutKey(String layoutKey){
		this.layoutKey = layoutKey;
	}

	public String getScreenContentKey(){
		return screenContentKey;
	}

	public void setScreenContentKey(String screenContentKey){
		this.screenContentKey = screenContentKey;
	}

	@Override
	public boolean checkResource(Locale locale) throws Exception{
		if(super.checkResource(locale) == false){
			return false;
		}

		try{
			getTemplate(layoutUrl);
			return true;
		}catch(ResourceNotFoundException ex){
			throw new NestedIOException("Cannot find Velocity template for URL [" + layoutUrl + "]: Did you specify " +
					"the correct resource loader path?", ex);
		}catch(Exception ex){
			throw new NestedIOException("Could not load Velocity template for URL [" + layoutUrl + "]", ex);
		}
	}

	@Override
	protected void doRender(Context context, HttpServletResponse response) throws Exception{
		renderScreenContent(context);

		String layoutUrlToUse = (String) context.get(layoutKey);
		if(layoutUrlToUse != null){
			logger.debug("Screen content template has requested layout [{}]", layoutUrlToUse);
		}else{
			layoutUrlToUse = layoutUrl;
		}

		mergeTemplate(getTemplate(layoutUrlToUse), context, response);
	}

	private void renderScreenContent(Context velocityContext) throws Exception{
		logger.debug("Rendering screen content template [{}]", getUrl());

		StringWriter writer = new StringWriter();
		Template screenContentTemplate = getTemplate(getUrl());

		screenContentTemplate.merge(velocityContext, writer);

		velocityContext.put(screenContentKey, writer.toString());
	}

}

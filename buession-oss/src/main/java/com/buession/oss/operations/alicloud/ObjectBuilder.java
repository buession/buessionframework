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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.operations.alicloud;

import com.buession.core.validator.Validate;
import com.buession.oss.core.ImageResizeMode;
import com.buession.oss.operations.Builder;

/**
 * @author Yong.Teng
 */
abstract class ObjectBuilder implements Builder<String> {

	private ObjectBuilder(){

	}

	/**
	 * 图片缩放 Builder
	 */
	final static class ObjectResizeBuilder extends ObjectBuilder {

		/**
		 * 缩放模式
		 */
		private ImageResizeMode mode;

		/**
		 * 缩放百分比
		 */
		private Double percent;

		/**
		 * 目标缩略图的宽度
		 */
		private Integer width;

		/**
		 * 目标缩略图的高度
		 */
		private Integer height;

		/**
		 * 目标缩略图的最长边
		 */
		private Integer l;

		/**
		 * 目标缩略图的最短边
		 */
		private Integer s;

		/**
		 * 当目标缩略图大于原图时是否处理
		 */
		private Boolean limit;

		/**
		 * 填充颜色
		 */
		private String padColor;

		public final static ObjectResizeBuilder getInstance(){
			return new ObjectResizeBuilder();
		}

		/**
		 * 指定缩放的模式
		 *
		 * @param mode
		 * 		缩放模式
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder mode(ImageResizeMode mode){
			this.mode = mode;
			return this;
		}

		/**
		 * 指定目标缩略图的缩放百分比
		 *
		 * @param percent
		 * 		目标缩略图缩放百分比
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder percent(Double percent){
			this.percent = percent;
			return this;
		}

		/**
		 * 指定目标缩略图的宽度
		 *
		 * @param width
		 * 		目标缩略图的宽度
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder width(int width){
			this.width = width;
			return this;
		}

		/**
		 * 指定目标缩略图的高度
		 *
		 * @param height
		 * 		目标缩略图的高度
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder height(int height){
			this.height = height;
			return this;
		}

		/**
		 * 指定目标缩略图的最长边
		 *
		 * @param l
		 * 		目标缩略图的最长边
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder l(int l){
			this.l = l;
			return this;
		}

		/**
		 * 指定目标缩略图的最短边
		 *
		 * @param s
		 * 		目标缩略图的最短边
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder s(int s){
			this.s = s;
			return this;
		}

		/**
		 * 指定当目标缩略图大于原图时是否处理
		 *
		 * @param limit
		 * 		目标缩略图大于原图时是否处理
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder limit(boolean limit){
			this.limit = limit;
			return this;
		}

		/**
		 * 当缩放模式选择为pad（缩放填充）时，可以选择填充的颜色
		 *
		 * @param padColor
		 * 		填充颜色
		 *
		 * @return ObjectResizeBuilder
		 */
		public ObjectResizeBuilder padColor(String padColor){
			this.padColor = padColor;
			return this;
		}

		@Override
		public String build(){
			final StringBuilder sb = new StringBuilder(64);
			boolean isPercent = true;

			sb.append("image/resize");

			if(mode != null){
				sb.append(",m_").append(mode.getValue());
				isPercent = false;
			}

			if(width != null){
				sb.append(",w_").append(width);
				isPercent = false;
			}

			if(height != null){
				sb.append(",h_").append(height);
				isPercent = false;
			}

			if(l != null){
				sb.append(",l_").append(l);
				isPercent = false;
			}

			if(s != null){
				sb.append(",s_").append(s);
				isPercent = false;
			}

			if(limit != null){
				sb.append(",limit_").append(limit ? 1 : 0);
				isPercent = false;
			}

			if(Validate.hasText(padColor)){
				sb.append(",color_").append(padColor);
				isPercent = false;
			}

			if(percent != null && isPercent){
				sb.append(",p_").append(percent);
			}

			return sb.toString();
		}

	}

	final static class ObjectCropBuilder extends ObjectBuilder {

		private Integer width;

		private Integer height;

		private Integer x;

		private Integer y;

		public final static ObjectCropBuilder getInstance(){
			return new ObjectCropBuilder();
		}

		public ObjectCropBuilder width(int width){
			this.width = width;
			return this;
		}

		public ObjectCropBuilder height(int height){
			this.height = height;
			return this;
		}

		public ObjectCropBuilder x(int x){
			this.x = x;
			return this;
		}

		public ObjectCropBuilder y(int y){
			this.y = y;
			return this;
		}

		@Override
		public String build(){
			final StringBuilder sb = new StringBuilder(64);

			sb.append("image/crop");

			if(width != null){
				sb.append(",w_").append(width);
			}

			if(height != null){
				sb.append(",h_").append(height);
			}

			if(x != null){
				sb.append(",x_").append(x);
			}

			if(y != null){
				sb.append(",h_").append(y);
			}

			sb.append(",r_1");

			return sb.toString();
		}

	}

	final static class ObjectRotateBuilder extends ObjectBuilder {

		private Integer degrees;

		private Boolean autoOrient;

		public final static ObjectRotateBuilder getInstance(){
			return new ObjectRotateBuilder();
		}

		public ObjectRotateBuilder degrees(int degrees){
			this.degrees = degrees;
			return this;
		}

		public ObjectRotateBuilder autoOrient(boolean autoOrient){
			this.autoOrient = autoOrient;
			return this;
		}

		@Override
		public String build(){
			final StringBuilder sb = new StringBuilder(64);

			sb.append("image/rotate");

			if(degrees != null){
				sb.append(',').append(degrees);
			}else{
				if(autoOrient != null){
					sb.append(',').append(autoOrient ? 1 : 0);
				}
			}

			return sb.toString();
		}

	}

}

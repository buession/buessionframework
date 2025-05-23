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
package com.buession.core.validator.constraintvalidators;

import com.buession.core.validator.Validate;
import com.buession.core.validator.annotation.Alpha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Yong.Teng
 */
public abstract class AlphaConstraintValidator<T> implements ConstraintValidator<Alpha, T> {

	protected boolean validWhenNull;

	@Override
	public void initialize(Alpha alpha){
		this.validWhenNull = alpha.whenNull();
	}

	public final static class CharSequenceAlphaConstraintValidator extends AlphaConstraintValidator<CharSequence> {

		@Override
		public boolean isValid(CharSequence value, ConstraintValidatorContext context){
			return validWhenNull == false || Validate.isAlpha(value);
		}

	}

	public final static class CharAlphaConstraintValidator extends AlphaConstraintValidator<Character> {

		@Override
		public boolean isValid(Character value, ConstraintValidatorContext context){
			return validWhenNull == false || Validate.isAlpha(value);
		}

	}

}

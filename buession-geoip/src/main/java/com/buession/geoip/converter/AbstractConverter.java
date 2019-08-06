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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.converter;

import com.buession.core.validator.Validate;
import com.maxmind.geoip2.model.AbstractResponse;
import com.maxmind.geoip2.record.AbstractRecord;

import java.util.Locale;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractConverter<M, S extends AbstractRecord, R extends AbstractResponse> implements
        Converter<M, S, R> {

    @Override
    public M converter(S s){
        return converter(s, (Locale) null);
    }

    @Override
    public M converter(S s, R response){
        return converter(s, response, null);
    }

    protected static String getName(final Map<String, String> names, Locale locale){
        if(Validate.isEmpty(names)){
            return null;
        }

        if(locale == null){
            locale = Locale.getDefault();
        }

        String result = names.get(locale.getLanguage());
        if(result != null){
            return result;
        }

        result = names.get(getLanguageTag(locale, '-'));
        if(result != null){
            return result;
        }

        result = names.get(getLanguageTag(locale, '_'));
        if(result != null){
            return result;
        }

        return null;
    }

    private final static String getLanguageTag(final Locale locale, final char separator){
        StringBuffer sb = new StringBuffer();

        sb.append(locale.getLanguage());
        sb.append(separator);
        sb.append(locale.getCountry());

        return sb.toString();
    }

}

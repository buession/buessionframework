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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class VelocityEngineUtils {

    public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, Map<String, Object>
            model, Writer writer) throws VelocityException{
        mergeTemplate(velocityEngine, templateLocation, Charset.defaultCharset(), model, writer);
    }

    public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, String encoding,
                                     Map<String, Object> model, Writer writer) throws VelocityException{
        VelocityContext velocityContext = new VelocityContext(model);
        velocityEngine.mergeTemplate(templateLocation, encoding, velocityContext, writer);
    }

    public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, Charset encoding,
                                     Map<String, Object> model, Writer writer) throws VelocityException{
        mergeTemplate(velocityEngine, templateLocation, encoding.name(), model, writer);
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, Map<String,
            Object> model) throws VelocityException{
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, model, result);
        return result.toString();
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, String
            encoding, Map<String, Object> model) throws VelocityException{
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, encoding, model, result);
        return result.toString();
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, Charset
            encoding, Map<String, Object> model) throws VelocityException{
        return mergeTemplateIntoString(velocityEngine, templateLocation, encoding.name(), model);
    }

}

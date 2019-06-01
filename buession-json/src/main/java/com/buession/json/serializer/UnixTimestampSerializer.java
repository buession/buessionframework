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
 * | Copyright @ 2013-2018 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author Yong.Teng
 */
@JacksonStdImpl
public class UnixTimestampSerializer extends DateTimeSerializerBase<Date> {

    public static final UnixTimestampSerializer instance = new UnixTimestampSerializer();

    public UnixTimestampSerializer(){
        this(null, null);
    }

    public UnixTimestampSerializer(Boolean useTimestamp, DateFormat customFormat){
        super(Date.class, useTimestamp, customFormat);
    }

    @Override
    public UnixTimestampSerializer withFormat(Boolean timestamp, DateFormat customFormat){
        return new UnixTimestampSerializer(timestamp, customFormat);
    }

    @Override
    protected long _timestamp(Date value){
        return value == null ? 0L : value.getTime() / 1000L;
    }

    @Override
    public void serialize(Date value, JsonGenerator g, SerializerProvider provider) throws IOException{
        if(_asTimestamp(provider)){
            g.writeNumber(_timestamp(value));
        }else{
            _serializeAsString(value, g, provider);
        }
    }

}

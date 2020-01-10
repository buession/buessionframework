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
package com.buession.json.deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class DateDeserializers extends com.fasterxml.jackson.databind.deser.std.DateDeserializers {

    @JacksonStdImpl
    public static class UnixTimestampDeserializer extends DateDeserializers.DateBasedDeserializer<Date> {

        public final static DateDeserializer instance = new DateDeserializer();

        public UnixTimestampDeserializer(){
            super(Date.class);
        }

        public UnixTimestampDeserializer(UnixTimestampDeserializer base, DateFormat df, String formatString){
            super(base, df, formatString);
        }

        @Override
        protected UnixTimestampDeserializer withDateFormat(DateFormat df, String formatString){
            return new UnixTimestampDeserializer(this, df, formatString);
        }

        @Override
        public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException{
            return _parseDate(parser, context);
        }

        @Override
        protected Date _parseDate(JsonParser p, DeserializationContext context) throws IOException{
            if(_customFormat != null && p.hasToken(JsonToken.VALUE_STRING)){
                return super._parseDate(p, context);
            }else{
                switch(p.getCurrentTokenId()){
                    case 3:
                        return _parseDateFromArray(p, context);
                    case 4:
                    case 5:
                    case 8:
                    case 9:
                    case 10:
                    default:
                        return (Date) context.handleUnexpectedToken(_valueClass, p);
                    case 6:
                        return _parseDate(p.getText().trim(), context);
                    case 7:
                        long timestamp;

                        try{
                            timestamp = p.getLongValue();
                        }catch(JsonParseException e){
                            Number v = (Number) context.handleWeirdNumberValue(_valueClass, p.getNumberValue(), "not " +
                                    "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                                    "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                                    "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                                    "" + "" + "" + "" + "" + "" + "" + "a " + "valid " + "64-bit " + "" + "" + "long " +
                                    "" + "" + "" + "for " + "creating " + "`java" + "" + "" + ".util" + "" + "" + ""
                                    + "" + ".Date`", new Object[0]);
                            timestamp = v.longValue();
                        }

                        return new Date(timestamp * 1000);
                    case 11:
                        return getNullValue(context);
                }
            }
        }
    }

}

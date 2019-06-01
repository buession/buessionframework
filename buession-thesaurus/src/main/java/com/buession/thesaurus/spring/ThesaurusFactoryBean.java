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
package com.buession.thesaurus.spring;

import com.buession.core.utils.Assert;
import com.buession.thesaurus.Parser;
import com.buession.thesaurus.execption.ThesaurusTypeNotFoundException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yong.Teng
 */
public class ThesaurusFactoryBean extends ThesaurusFactory implements FactoryBean<Parser>, InitializingBean {

    private Parser parser;

    private Class<? extends Parser> clazz;

    @Override
    public Parser getObject() throws Exception{
        return parser;
    }

    @Override
    public Class<? extends Parser> getObjectType(){
        return clazz;
    }

    @Override
    public boolean isSingleton(){
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        Assert.isBlank(getKey(), "Thesaurus key could not be null or empty.");

        char first = getKey().charAt(0);

        StringBuffer sb = new StringBuffer();

        sb.append("com.buession.thesaurus.");

        if(first >= 'a' && first <= 'z'){
            sb.append((char) (getKey().charAt(0) - 32));
            sb.append(getKey().substring(1));
        }else{
            sb.append(getKey());
        }

        sb.append("Parser");

        try{
            clazz = (Class<? extends Parser>) Class.forName(sb.toString());
            parser = clazz.newInstance();
        }catch(ClassNotFoundException e){
            throw new ThesaurusTypeNotFoundException("The " + getKey() + " thesaurus not be found.");
        }
    }
}

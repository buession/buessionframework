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
package com.buession.redis.core;

import com.buession.redis.utils.ByteArrayComparator;
import com.buession.redis.utils.SafeEncoder;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class Tuple implements Comparable<Tuple> {

    private byte[] element;

    private Double score;

    public Tuple(String element, Double score){
        this(SafeEncoder.encode(element), score);
    }

    public Tuple(byte[] element, Double score){
        super();
        this.element = element;
        this.score = score;
    }

    public String getElement(){
        return element == null ? null : SafeEncoder.encode(element);
    }

    public byte[] getBinaryElement(){
        return element;
    }

    public double getScore(){
        return score;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;

        result = prime * result;

        if(null != element){
            for(final byte b : element){
                result = prime * result + b;
            }
        }

        long temp = Double.doubleToLongBits(score);

        return prime * result + (int) (temp ^ (temp >>> 32));
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }else if(obj == null){
            return false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }

        Tuple that = (Tuple) obj;
        return Arrays.equals(element, that.element) == false ? false : Objects.equals(score, that.score);
    }

    @Override
    public int compareTo(Tuple that){
        return compare(this, that);
    }

    public static int compare(Tuple t1, Tuple t2){
        int compScore = Double.compare(t1.score, t2.score);
        return compScore != 0 ? compScore : ByteArrayComparator.compare(t1.element, t2.element);
    }

    @Override
    public String toString(){
        return "Tuple{" + "element=" + Arrays.toString(element) + ", score=" + score + '}';
    }
}

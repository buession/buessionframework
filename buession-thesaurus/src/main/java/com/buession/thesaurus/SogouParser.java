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
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.thesaurus;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.thesaurus.core.Word;
import com.buession.thesaurus.sogou.SogouType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class SogouParser extends AbstractParser<SogouType> {

    protected final static Charset ENCODING = StandardCharsets.UTF_16LE;

    @Override
    public SogouType getType(){
        return new SogouType();
    }

    private final static Logger logger = LoggerFactory.getLogger(SogouParser.class);

    @Override
    protected Set<Word> doParse(InputStream inputStream) throws IOException{
        SogouScelModel model = read(inputStream);
        Set<Word> words = new LinkedHashSet<>();

        logger.debug("words entry info is: name => {}, type => {}, description => {}, sample => {}.", model.getName()
                , model.getType(), model.getDescription(), model.getSample());

        Map<String, Set<String>> tempWords = model.getWordMap();

        if(tempWords == null){
            return null;
        }else if(tempWords.size() == 0){
            return words;
        }

        tempWords.forEach((key, value)->{
            if(value == null){
                return;
            }

            final String pinyin = StringUtils.replace(key, "'", "");
            final char[] initials = parseInitials(key, "'");

            value.forEach((val)->{
                Word word = new Word();

                word.setPinyin(pinyin);
                word.setValue(val);
                word.setInitials(initials);

                if(Validate.isEmpty(initials) == false){
                    word.setInitial(initials[0]);
                }

                words.add(word);
            });
        });

        tempWords.clear();

        return words;
    }

    private final SogouScelModel read(InputStream inputStream) throws IOException{
        SogouScelModel model = new SogouScelModel();
        DataInputStream input = new DataInputStream(inputStream);
        byte[] bytes = new byte[4];
        int read;

        try{
            input.readFully(bytes);

            assert (bytes[0] == 0x40 && bytes[1] == 0x15 && bytes[2] == 0 && bytes[3] == 0);

            input.readFully(bytes);

            int flag1 = bytes[0];

            assert (bytes[1] == 0x43 && bytes[2] == 0x53 && bytes[3] == 0x01);

            int[] reads = new int[]{8};

            model.setName(readString(input, 0x130, reads));
            model.setType(readString(input, 0x338, reads));
            model.setDescription(readString(input, 0x540, reads));
            model.setSample(readString(input, 0xd40, reads));

            read = reads[0];
            input.skip(0x1540 - read);
            read = 0x1540;
            input.readFully(bytes);
            read += 4;

            assert (bytes[0] == (byte) 0x9D && bytes[1] == 0x01 && bytes[2] == 0 && bytes[3] == 0);

            bytes = new byte[128];
            Map<Integer, String> pinyinMap = new LinkedHashMap<>();

            while(true){
                int mark = readUnsignedShort(input);
                int size = input.readUnsignedByte();

                input.skip(1);
                read += 4;

                assert (size > 0 && (size % 2) == 0);

                input.readFully(bytes, 0, size);
                read += size;

                String pinyin = new String(bytes, 0, size, ENCODING);

                pinyinMap.put(mark, pinyin);
                if("zuo".equals(pinyin)){
                    break;
                }
            }

            if(flag1 == 0x44){
                input.skip(0x2628 - read);
            }else if(flag1 == 0x45){
                input.skip(0x26C4 - read);
            }else{
                throw new RuntimeException();
            }

            StringBuffer buffer = new StringBuffer();
            Map<String, Set<String>> wordMap = new LinkedHashMap<>();

            while(true){
                int size = readUnsignedShort(input);
                if(size < 0){
                    break;
                }

                int count = readUnsignedShort(input);
                int len = count / 2;

                assert (len * 2 == count);
                buffer.setLength(0);

                for(int i = 0; i < len; i++){
                    int key = readUnsignedShort(input);
                    buffer.append(pinyinMap.get(key)).append('\'');
                }

                buffer.setLength(buffer.length() - 1);
                String pinyin = buffer.toString();

                Set<String> list = wordMap.get(pinyin);
                if(list == null){
                    list = new LinkedHashSet<>();
                    wordMap.put(pinyin, list);
                }

                for(int i = 0; i < size; i++){
                    count = readUnsignedShort(input);
                    if(count > bytes.length){
                        bytes = new byte[count];
                    }

                    input.readFully(bytes, 0, count);
                    String word = new String(bytes, 0, count, StandardCharsets.UTF_16LE);
                    //接下来12个字节可能是词频或者类似信息
                    input.skip(12);
                    list.add(word);
                }
            }

            model.setWordMap(wordMap);

            return model;
        }finally{
            if(input != null){
                input.close();
            }
            inputStream.close();
        }
    }

    protected final String readString(DataInputStream input, int pos, int[] reads) throws IOException{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int read = reads[0];

        input.skip(pos - read);
        read = pos;
        output.reset();

        while(true){
            int c1 = input.read();
            int c2 = input.read();

            read += 2;
            if(c1 == 0 && c2 == 0){
                break;
            }else{
                output.write(c1);
                output.write(c2);
            }
        }

        reads[0] = read;

        return new String(output.toByteArray(), ENCODING);
    }

    protected final static int readUnsignedShort(InputStream in) throws IOException{
        int ch1 = in.read();
        int ch2 = in.read();

        return (ch1 | ch2) < 0 ? Integer.MIN_VALUE : (ch2 << 8) + (ch1 << 0);
    }

    private final static class SogouScelModel implements Serializable {

        private static final long serialVersionUID = -7930700607679364157L;

        private String name;

        private String type;

        private String description;

        private String sample;

        private Map<String, Set<String>> wordMap;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getType(){
            return type;
        }

        public void setType(String type){
            this.type = type;
        }

        public String getDescription(){
            return description;
        }

        public void setDescription(String description){
            this.description = description;
        }

        public String getSample(){
            return sample;
        }

        public void setSample(String sample){
            this.sample = sample;
        }

        public Map<String, Set<String>> getWordMap(){
            return wordMap;
        }

        public void setWordMap(Map<String, Set<String>> wordMap){
            this.wordMap = wordMap;
        }

    }

}

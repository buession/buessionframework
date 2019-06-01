package com.buession.core.utils;

import com.buession.core.mcrypt.MD5Mcrypt;
import com.buession.core.mcrypt.Sha1Mcrypt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author Yong.Teng
 */
public class StringUtilsTest {

    @Test
    public void substr() throws Exception{
        String str = "abc";

        System.out.println(StringUtils.substr(str, -1));

        MD5Mcrypt md5Mcrypt = new MD5Mcrypt();
        System.out.println(StringUtils.substr(md5Mcrypt.encode(str), 16, 16));
    }

    @Test
    public void uuid(){
        final StringBuilder sb = new StringBuilder();
        final Sha1Mcrypt sha1Mcrypt = new Sha1Mcrypt(StandardCharsets.UTF_8, "1");
        final String s = sha1Mcrypt.encode(UUID.randomUUID());

        sb.append("avatar/");
        sb.append(s, 0, 2);
        sb.append('/');
        sb.append(s, 3, 5);
        sb.append('/');
        sb.append(s.substring(6));
        sb.append(".jpg");

        System.out.println(sb.length());
    }

}
package com.buession.core.mcrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yong.Teng
 */
public class DiscuzMycryptTest {

    @Test
    public void encode() throws Exception{
        String str = "abc";

        DiscuzMycrypt discuzMycrypt = new DiscuzMycrypt();
        System.out.println(discuzMycrypt.encode(str));
    }

    @Test
    public void decode() throws Exception{
        String str = "551dBQkDVlYDBQFUUgNSBgReBFZUUwFWBV0FCFgCAVUJVAI";

        DiscuzMycrypt discuzMycrypt = new DiscuzMycrypt();
        System.out.println(discuzMycrypt.decode(str));

    }

}
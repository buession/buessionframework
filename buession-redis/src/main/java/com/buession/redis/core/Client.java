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
package com.buession.redis.core;

import redis.clients.jedis.Protocol;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class Client implements Serializable {

    private final static long serialVersionUID = -8794975805619333960L;

    /**
     * 客户端 ID
     */
    private int id;

    /**
     * 客户端名称
     */
    private String name;

    /**
     * 客户端的地址和端口
     */
    private String addr;

    /**
     * 客户端的地址
     */
    private String host;

    /**
     * 客户端端口
     */
    private int port;

    /**
     * 套接字所使用的文件描述符
     */
    private int fd;

    /**
     * 已连接时长（单位：秒）
     */
    private int age;

    /**
     * 空闲时长（单位：秒）
     */
    private int idle;

    /**
     * 客户端 flag
     */
    private Set<Flag> flags;

    /**
     * 该客户端正在使用的数据库 ID
     */
    private int db;

    /**
     * 已订阅频道的数量
     */
    private int sub;

    /**
     * 已订阅模式的数量
     */
    private int psub;

    /**
     * 在事务中被执行的命令数量
     */
    private int multi;

    /**
     * 查询缓冲区的长度（字节为单位， 0 表示没有分配查询缓冲区）
     */
    private int qBuf;

    /**
     * 查询缓冲区剩余空间的长度（字节为单位， 0 表示没有剩余空间）
     */
    private int qBufFree;

    /**
     * 输出缓冲区的长度（字节为单位， 0 表示没有分配输出缓冲区）
     */
    private int obl;

    /**
     * 输出列表包含的对象数量（当输出缓冲区没有剩余空间时，命令回复会以字符串对象的形式被入队到这个队列里）
     */
    private int oll;

    /**
     * 输出缓冲区和输出列表占用的内存总量
     */
    private int omem;

    /**
     * 文件描述符事件（见下文）
     */
    private Event events;

    /**
     * 最近一次执行的命令
     */
    private Protocol.Command cmd;

    /**
     * 获取客户端 ID
     *
     * @return 客户端 ID
     */
    public int getId(){
        return id;
    }

    /**
     * 设置客户端 ID
     *
     * @param id
     *         客户端 ID
     */
    public void setId(final int id){
        this.id = id;
    }

    /**
     * 获取客户端名称
     *
     * @return 客户端名称
     */
    public String getName(){
        return name;
    }

    /**
     * 设置客户端名称
     *
     * @param name
     *         客户端名称
     */
    public void setName(final String name){
        this.name = name;
    }

    /**
     * 获取客户端的地址和端口
     *
     * @return 客户端的地址和端口
     */
    public String getAddr(){
        return addr;
    }

    /**
     * 设置客户端的地址和端口
     *
     * @param addr
     *         客户端的地址和端口
     */
    public void setAddr(String addr){
        this.addr = addr;
    }

    /**
     * 获取客户端的地址
     *
     * @return 客户端的地址
     */
    public String getHost(){
        return host;
    }

    /**
     * 设置客户端的地址
     *
     * @param host
     *         客户端的地址
     */
    public void setHost(String host){
        this.host = host;
    }

    /**
     * 获取客户端的端口
     *
     * @return 客户端的端口
     */
    public int getPort(){
        return port;
    }

    /**
     * 设置客户端的端口
     *
     * @param port
     *         客户端的端口
     */
    public void setPort(int port){
        this.port = port;
    }

    /**
     * 获取套接字所使用的文件描述符
     *
     * @return 套接字所使用的文件描述符
     */
    public int getFd(){
        return fd;
    }

    /**
     * 设置套接字所使用的文件描述符
     *
     * @param fd
     *         套接字所使用的文件描述符
     */
    public void setFd(int fd){
        this.fd = fd;
    }

    /**
     * 获取已连接时长（单位：秒）
     *
     * @return 已连接时长（单位：秒）
     */
    public int getAge(){
        return age;
    }

    /**
     * 设置已连接时长（单位：秒）
     *
     * @param age
     *         已连接时长（单位：秒）
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * 获取空闲时长（单位：秒）
     *
     * @return 空闲时长（单位：秒）
     */
    public int getIdle(){
        return idle;
    }

    /**
     * 设置空闲时长（单位：秒）
     *
     * @param idle
     *         空闲时长（单位：秒）
     */
    public void setIdle(int idle){
        this.idle = idle;
    }

    /**
     * 获取客户端 flag
     *
     * @return 客户端 flag
     */
    public Set<Flag> getFlags(){
        return flags;
    }

    /**
     * 设置客户端 flag
     *
     * @param flags
     *         客户端 flag
     */
    public void setFlags(Set<Flag> flags){
        this.flags = flags;
    }

    /**
     * 获取该客户端正在使用的数据库 ID
     *
     * @return 该客户端正在使用的数据库 ID
     */
    public int getDb(){
        return db;
    }

    /**
     * 设置该客户端正在使用的数据库 ID
     *
     * @param db
     *         该客户端正在使用的数据库 ID
     */
    public void setDb(int db){
        this.db = db;
    }

    /**
     * 获取已订阅频道的数量
     *
     * @return 已订阅频道的数量
     */
    public int getSub(){
        return sub;
    }

    /**
     * 设置已订阅频道的数量
     *
     * @param sub
     *         已订阅频道的数量
     */
    public void setSub(int sub){
        this.sub = sub;
    }

    /**
     * 获取已订阅模式的数量
     *
     * @return 已订阅模式的数量
     */
    public int getPsub(){
        return psub;
    }

    /**
     * 设置已订阅模式的数量
     *
     * @param psub
     *         已订阅模式的数量
     */
    public void setPsub(int psub){
        this.psub = psub;
    }

    /**
     * 获取 在事务中被执行的命令数量
     *
     * @return 在事务中被执行的命令数量
     */
    public int getMulti(){
        return multi;
    }

    /**
     * 设置 在事务中被执行的命令数量
     *
     * @param multi
     *         在事务中被执行的命令数量
     */
    public void setMulti(int multi){
        this.multi = multi;
    }

    /**
     * 获取 查询缓冲区的长度（字节为单位， 0 表示没有分配查询缓冲区）
     *
     * @return 查询缓冲区的长度（字节为单位， 0 表示没有分配查询缓冲区）
     */
    public int getQBuf(){
        return qBuf;
    }

    /**
     * 设置 查询缓冲区的长度（字节为单位， 0 表示没有分配查询缓冲区）
     *
     * @param qBuf
     *         查询缓冲区的长度（字节为单位， 0 表示没有分配查询缓冲区）
     */
    public void setQBuf(int qBuf){
        this.qBuf = qBuf;
    }

    /**
     * 获取查询缓冲区剩余空间的长度（字节为单位， 0 表示没有剩余空间）
     *
     * @return 查询缓冲区剩余空间的长度（字节为单位， 0 表示没有剩余空间）
     */
    public int getQBufFree(){
        return qBufFree;
    }

    /**
     * 设置查询缓冲区剩余空间的长度（字节为单位， 0 表示没有剩余空间）
     *
     * @param qBufFree
     *         查询缓冲区剩余空间的长度（字节为单位， 0 表示没有剩余空间）
     */
    public void setQBufFree(int qBufFree){
        this.qBufFree = qBufFree;
    }

    /**
     * 获取输出缓冲区的长度（字节为单位， 0 表示没有分配输出缓冲区）
     *
     * @return 输出缓冲区的长度（字节为单位， 0 表示没有分配输出缓冲区）
     */
    public int getObl(){
        return obl;
    }

    /**
     * 设置输出缓冲区的长度（字节为单位， 0 表示没有分配输出缓冲区）
     *
     * @param obl
     *         输出缓冲区的长度（字节为单位， 0 表示没有分配输出缓冲区）
     */
    public void setObl(int obl){
        this.obl = obl;
    }

    /**
     * 获取输出列表包含的对象数量（当输出缓冲区没有剩余空间时，命令回复会以字符串对象的形式被入队到这个队列里）
     *
     * @return 输出列表包含的对象数量（当输出缓冲区没有剩余空间时，命令回复会以字符串对象的形式被入队到这个队列里）
     */
    public int getOll(){
        return oll;
    }

    /**
     * 设置输出列表包含的对象数量（当输出缓冲区没有剩余空间时，命令回复会以字符串对象的形式被入队到这个队列里）
     *
     * @param oll
     *         输出列表包含的对象数量（当输出缓冲区没有剩余空间时，命令回复会以字符串对象的形式被入队到这个队列里）
     */
    public void setOll(int oll){
        this.oll = oll;
    }

    /**
     * 获取输出缓冲区和输出列表占用的内存总量
     *
     * @return 输出缓冲区和输出列表占用的内存总量
     */
    public int getOmem(){
        return omem;
    }

    /**
     * 设置输出缓冲区和输出列表占用的内存总量
     *
     * @param omem
     *         输出缓冲区和输出列表占用的内存总量
     */
    public void setOmem(int omem){
        this.omem = omem;
    }

    /**
     * 获取文件描述符事件
     *
     * @return 文件描述符事件
     */
    public Event getEvents(){
        return events;
    }

    /**
     * 设置文件描述符事件
     *
     * @param events
     *         文件描述符事件
     */
    public void setEvents(Event events){
        this.events = events;
    }

    /**
     * 获取最近一次执行的命令
     *
     * @return 最近一次执行的命令
     */
    public Protocol.Command getCmd(){
        return cmd;
    }

    /**
     * 设置最近一次执行的命令
     *
     * @param cmd
     *         最近一次执行的命令
     */
    public void setCmd(Protocol.Command cmd){
        this.cmd = cmd;
    }

    @Override
    public String toString(){
        return "Client{" + "id=" + id + ", name='" + name + '\'' + ", addr='" + addr + '\'' + ", host='" + host +
                '\'' + ", port=" + port + ", fd=" + fd + ", age=" + age + ", idle=" + idle + ", flags=" + flags + ", " +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "db="
                + db + ", " + "" + "" + "" + "" + "sub=" + sub + ", " + "psub=" + psub + "," + "" + "" + "" + "" + ""
                + "" + "" + "" + " " + "" + "multi=" + multi + "," + " " + "" + "qBuf=" + qBuf + "," + "" + "" + "" +
                "" + "" + " " + "qBufFree=" + qBufFree + "," + "" + " " + "obl=" + obl + ", " + "" + "oll=" + oll +
                ", " + "" + "" + "" + "omem=" + omem + "," + "" + " " + "" + "" + "" + "events=" + events + "," + " "
                + "" + "" + "" + "cmd=" + cmd + '}';
    }

    public enum Flag {

        /**
         * 客户端是 MONITOR 模式下的附属节点（slave）
         */
        O,

        /**
         * 客户端是一般模式下（normal）的附属节点
         */
        S,

        /**
         * 客户端是主节点（master）
         */
        M,

        /**
         * 客户端正在执行事务
         */
        x,

        /**
         * 客户端正在等待阻塞事件
         */
        b,

        /**
         * 客户端正在等待 VM I/O 操作（已废弃）
         */
        @Deprecated i,

        /**
         * 一个受监视（watched）的键已被修改， EXEC 命令将失败
         */
        d,

        /**
         * 在将回复完整地写出之后，关闭链接
         */
        c,

        /**
         * 客户端未被阻塞（unblocked）
         */
        u,

        /**
         * 尽可能快地关闭连接
         */
        A,

        /**
         * 未设置任何 flag
         */
        N
    }

    public enum Event {

        R,

        W

    }

}

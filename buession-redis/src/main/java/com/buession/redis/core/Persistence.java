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

import com.buession.lang.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * RDB 持久化和 AOF 持久化有关的信息
 *
 * @author Yong.Teng
 */
public class Persistence implements Serializable {

    private final static long serialVersionUID = -7657298714606549180L;

    /**
     * 服务器是否正在载入持久化文件
     */
    private boolean loading;

    /**
     * 距离最近一次成功创建持久化文件之后，经过了多少秒
     */
    private int rdbChangesSinceLastSave;

    /**
     * 服务器是否正在创建 RDB 文件
     */
    private boolean rdbBgSaveInProgress;

    /**
     * 最近一次成功创建 RDB 文件的时戳
     */
    private Date rdbLastSaveTime;

    /**
     * 最近一次创建 RDB 文件的结果是成功还是失败
     */
    private Status rdbLastBgSaveStatus;

    /**
     * 最近一次创建 RDB 文件耗费的秒数
     */
    private int rdbLastBgSaveTimeSec;

    /**
     * 如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     */
    private int rdbCurrentBgSaveTimeSec;

    /**
     * AOF 是否处于打开状态
     */
    private boolean aofEnabled;

    /**
     * 服务器是否正在创建 AOF 文件
     */
    private boolean aofRewriteInProgress;

    /**
     * 在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     */
    private boolean aofRewriteScheduled;

    /**
     * 最近一次创建 AOF 文件耗费的时长
     */
    private int aofLastRewriteTimeSec;

    /**
     * 如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     */
    private int aofCurrentRewriteTimeSec;

    /**
     * 最近一次创建 AOF 文件的结果是成功还是失败
     */
    private Status aofLastBgRewriteStatus;

    /**
     *
     */
    private Status aofLastWriteStatus;

    /**
     * AOF 文件目前的大小
     */
    private int aofCurrentSize;

    /**
     * 服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
     */
    private int aofBaseSize;

    /**
     * 是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     */
    private boolean aofPendingRewrite;

    /**
     * AOF 缓冲区的大小
     */
    private int aofBufferLength;

    /**
     * AOF 重写缓冲区的大小
     */
    private int aofRewriteBufferLength;

    /**
     * 后台 I/O 队列里面，等待执行的 fsync 调用数量
     */
    private int aofPendingBioFsync;

    /**
     * 被延迟的 fsync 调用数量
     */
    private int aofDelayedFsync;

    /**
     * 获取服务器是否正在载入持久化文件
     *
     * @return 服务器是否正在载入持久化文件
     */
    public boolean isLoading(){
        return getLoading();
    }

    /**
     * 获取服务器是否正在载入持久化文件
     *
     * @return 服务器是否正在载入持久化文件
     */
    public boolean getLoading(){
        return loading;
    }

    /**
     * 设置服务器是否正在载入持久化文件
     *
     * @param loading
     *         服务器是否正在载入持久化文件
     */
    public void setLoading(boolean loading){
        this.loading = loading;
    }

    /**
     * 获取距离最近一次成功创建持久化文件之后，经过了多少秒
     *
     * @return 距离最近一次成功创建持久化文件之后，经过了多少秒
     */
    public int getRdbChangesSinceLastSave(){
        return rdbChangesSinceLastSave;
    }

    /**
     * 设置距离最近一次成功创建持久化文件之后，经过了多少秒
     *
     * @param rdbChangesSinceLastSave
     *         距离最近一次成功创建持久化文件之后，经过了多少秒
     */
    public void setRdbChangesSinceLastSave(int rdbChangesSinceLastSave){
        this.rdbChangesSinceLastSave = rdbChangesSinceLastSave;
    }

    /**
     * 获取服务器是否正在创建 RDB 文件
     *
     * @return 服务器是否正在创建 RDB 文件
     */
    public boolean isRdbBgSaveInProgress(){
        return getRdbBgSaveInProgress();
    }

    /**
     * 获取服务器是否正在创建 RDB 文件
     *
     * @return 服务器是否正在创建 RDB 文件
     */
    public boolean getRdbBgSaveInProgress(){
        return rdbBgSaveInProgress;
    }

    /**
     * 设置服务器是否正在创建 RDB 文件
     *
     * @param rdbBgSaveInProgress
     *         服务器是否正在创建 RDB 文件
     */
    public void setRdbBgSaveInProgress(boolean rdbBgSaveInProgress){
        this.rdbBgSaveInProgress = rdbBgSaveInProgress;
    }

    /**
     * 获取最近一次成功创建 RDB 文件的时间
     *
     * @return 最近一次成功创建 RDB 文件的时间
     */
    public Date getRdbLastSaveTime(){
        return rdbLastSaveTime;
    }

    /**
     * 设置最近一次成功创建 RDB 文件的时间
     *
     * @param rdbLastSaveTime
     *         最近一次成功创建 RDB 文件的时间
     */
    public void setRdbLastSaveTime(Date rdbLastSaveTime){
        this.rdbLastSaveTime = rdbLastSaveTime;
    }

    /**
     * 获取最近一次创建 RDB 文件的结果是成功还是失败
     *
     * @return 最近一次创建 RDB 文件的结果是成功还是失败
     */
    public Status getRdbLastBgSaveStatus(){
        return rdbLastBgSaveStatus;
    }

    /**
     * 设置最近一次创建 RDB 文件的结果是成功还是失败
     *
     * @param rdbLastBgSaveStatus
     *         最近一次创建 RDB 文件的结果是成功还是失败
     */
    public void setRdbLastBgSaveStatus(Status rdbLastBgSaveStatus){
        this.rdbLastBgSaveStatus = rdbLastBgSaveStatus;
    }

    /**
     * 获取最近一次创建 RDB 文件耗费的秒数
     *
     * @return 最近一次创建 RDB 文件耗费的秒数
     */
    public int getRdbLastBgSaveTimeSec(){
        return rdbLastBgSaveTimeSec;
    }

    /**
     * 设置最近一次创建 RDB 文件耗费的秒数
     *
     * @param rdbLastBgSaveTimeSec
     *         最近一次创建 RDB 文件耗费的秒数
     */
    public void setRdbLastBgSaveTimeSec(int rdbLastBgSaveTimeSec){
        this.rdbLastBgSaveTimeSec = rdbLastBgSaveTimeSec;
    }

    /**
     * 获取如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     *
     * @return 如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     */
    public int getRdbCurrentBgSaveTimeSec(){
        return rdbCurrentBgSaveTimeSec;
    }

    /**
     * 设置如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     *
     * @param rdbCurrentBgSaveTimeSec
     *         如果服务器正在创建 RDB 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     */
    public void setRdbCurrentBgSaveTimeSec(int rdbCurrentBgSaveTimeSec){
        this.rdbCurrentBgSaveTimeSec = rdbCurrentBgSaveTimeSec;
    }

    /**
     * 获取AOF 是否处于打开状态
     *
     * @return AOF 是否处于打开状态
     */
    public boolean isAofEnabled(){
        return getAofEnabled();
    }

    /**
     * 获取AOF 是否处于打开状态
     *
     * @return AOF 是否处于打开状态
     */
    public boolean getAofEnabled(){
        return aofEnabled;
    }

    /**
     * 设置AOF 是否处于打开状态
     *
     * @param aofEnabled
     *         AOF 是否处于打开状态
     */
    public void setAofEnabled(boolean aofEnabled){
        this.aofEnabled = aofEnabled;
    }

    /**
     * 获取服务器是否正在创建 AOF 文件
     *
     * @return 服务器是否正在创建 AOF 文件
     */
    public boolean isAofRewriteInProgress(){
        return getAofRewriteInProgress();
    }

    /**
     * 获取服务器是否正在创建 AOF 文件
     *
     * @return 服务器是否正在创建 AOF 文件
     */
    public boolean getAofRewriteInProgress(){
        return aofRewriteInProgress;
    }

    /**
     * 设置服务器是否正在创建 AOF 文件
     *
     * @param aofRewriteInProgress
     *         服务器是否正在创建 AOF 文件
     */
    public void setAofRewriteInProgress(boolean aofRewriteInProgress){
        this.aofRewriteInProgress = aofRewriteInProgress;
    }

    /**
     * 获取在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     *
     * @return 在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     */
    public boolean isAofRewriteScheduled(){
        return getAofRewriteScheduled();
    }

    /**
     * 获取在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     *
     * @return 在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     */
    public boolean getAofRewriteScheduled(){
        return aofRewriteScheduled;
    }

    /**
     * 设置在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     *
     * @param aofRewriteScheduled
     *         在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作
     */
    public void setAofRewriteScheduled(boolean aofRewriteScheduled){
        this.aofRewriteScheduled = aofRewriteScheduled;
    }

    /**
     * 获取最近一次创建 AOF 文件耗费的时长
     *
     * @return 最近一次创建 AOF 文件耗费的时长
     */
    public int getAofLastRewriteTimeSec(){
        return aofLastRewriteTimeSec;
    }

    /**
     * 设置最近一次创建 AOF 文件耗费的时长
     *
     * @param aofLastRewriteTimeSec
     *         最近一次创建 AOF 文件耗费的时长
     */
    public void setAofLastRewriteTimeSec(int aofLastRewriteTimeSec){
        this.aofLastRewriteTimeSec = aofLastRewriteTimeSec;
    }

    /**
     * 获取如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     *
     * @return 如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     */
    public int getAofCurrentRewriteTimeSec(){
        return aofCurrentRewriteTimeSec;
    }

    /**
     * 设置如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     *
     * @param aofCurrentRewriteTimeSec
     *         如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数
     */
    public void setAofCurrentRewriteTimeSec(int aofCurrentRewriteTimeSec){
        this.aofCurrentRewriteTimeSec = aofCurrentRewriteTimeSec;
    }

    /**
     * 获取最近一次创建 AOF 文件的结果是成功还是失败
     *
     * @return 最近一次创建 AOF 文件的结果是成功还是失败
     */
    public Status getAofLastBgRewriteStatus(){
        return aofLastBgRewriteStatus;
    }

    /**
     * 设置最近一次创建 AOF 文件的结果是成功还是失败
     *
     * @param aofLastBgRewriteStatus
     *         最近一次创建 AOF 文件的结果是成功还是失败
     */
    public void setAofLastBgRewriteStatus(Status aofLastBgRewriteStatus){
        this.aofLastBgRewriteStatus = aofLastBgRewriteStatus;
    }

    public Status getAofLastWriteStatus(){
        return aofLastWriteStatus;
    }

    public void setAofLastWriteStatus(Status aofLastWriteStatus){
        this.aofLastWriteStatus = aofLastWriteStatus;
    }

    /**
     * 获取AOF 文件目前的大小
     *
     * @return AOF 文件目前的大小
     */
    public int getAofCurrentSize(){
        return aofCurrentSize;
    }

    /**
     * 设置AOF 文件目前的大小
     *
     * @param aofCurrentSize
     *         AOF 文件目前的大小
     */
    public void setAofCurrentSize(int aofCurrentSize){
        this.aofCurrentSize = aofCurrentSize;
    }

    /**
     * 获取服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
     *
     * @return 服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
     */
    public int getAofBaseSize(){
        return aofBaseSize;
    }

    /**
     * 设置服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
     *
     * @param aofBaseSize
     *         服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小
     */
    public void setAofBaseSize(int aofBaseSize){
        this.aofBaseSize = aofBaseSize;
    }

    /**
     * 获取是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     *
     * @return 是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     */
    public boolean isAofPendingRewrite(){
        return getAofPendingRewrite();
    }

    /**
     * 获取是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     *
     * @return 是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     */
    public boolean getAofPendingRewrite(){
        return aofPendingRewrite;
    }

    /**
     * 设置是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     *
     * @param aofPendingRewrite
     *         是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行
     */
    public void setAofPendingRewrite(boolean aofPendingRewrite){
        this.aofPendingRewrite = aofPendingRewrite;
    }

    /**
     * 获取AOF 缓冲区的大小
     *
     * @return AOF 缓冲区的大小
     */
    public int getAofBufferLength(){
        return aofBufferLength;
    }

    /**
     * 设置AOF 缓冲区的大小
     *
     * @param aofBufferLength
     *         AOF 缓冲区的大小
     */
    public void setAofBufferLength(int aofBufferLength){
        this.aofBufferLength = aofBufferLength;
    }

    /**
     * 获取AOF 重写缓冲区的大小
     *
     * @return AOF 重写缓冲区的大小
     */
    public int getAofRewriteBufferLength(){
        return aofRewriteBufferLength;
    }

    /**
     * 设置AOF 重写缓冲区的大小
     *
     * @param aofRewriteBufferLength
     *         AOF 重写缓冲区的大小
     */
    public void setAofRewriteBufferLength(int aofRewriteBufferLength){
        this.aofRewriteBufferLength = aofRewriteBufferLength;
    }

    /**
     * 获取后台 I/O 队列里面，等待执行的 fsync 调用数量
     *
     * @return 后台 I/O 队列里面，等待执行的 fsync 调用数量
     */
    public int getAofPendingBioFsync(){
        return aofPendingBioFsync;
    }

    /**
     * 设置后台 I/O 队列里面，等待执行的 fsync 调用数量
     *
     * @param aofPendingBioFsync
     *         后台 I/O 队列里面，等待执行的 fsync 调用数量
     */
    public void setAofPendingBioFsync(int aofPendingBioFsync){
        this.aofPendingBioFsync = aofPendingBioFsync;
    }

    /**
     * 获取被延迟的 fsync 调用数量
     *
     * @return 被延迟的 fsync 调用数量
     */
    public int getAofDelayedFsync(){
        return aofDelayedFsync;
    }

    /**
     * 设置被延迟的 fsync 调用数量
     *
     * @param aofDelayedFsync
     *         被延迟的 fsync 调用数量
     */
    public void setAofDelayedFsync(int aofDelayedFsync){
        this.aofDelayedFsync = aofDelayedFsync;
    }

    @Override
    public String toString(){
        return "Persistence{" + "loading=" + loading + ", rdbChangesSinceLastSave=" + rdbChangesSinceLastSave + ", "
                + "rdbBgSaveInProgress=" + rdbBgSaveInProgress + ", rdbLastSaveTime=" + rdbLastSaveTime + ", " +
                "rdbLastBgSaveStatus=" + rdbLastBgSaveStatus + ", rdbLastBgSaveTimeSec=" + rdbLastBgSaveTimeSec + ", " +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "rdbCurrentBgSaveTimeSec=" + rdbCurrentBgSaveTimeSec + ", " + "aofEnabled=" + aofEnabled +
                ", " + "aofRewriteInProgress=" + aofRewriteInProgress + ", " + "aofRewriteScheduled=" +
                aofRewriteScheduled + ", " + "" + "aofLastRewriteTimeSec=" + aofLastRewriteTimeSec + ", " +
                "aofCurrentRewriteTimeSec=" + aofCurrentRewriteTimeSec + ", " + "aofLastBgRewriteStatus=" +
                aofLastBgRewriteStatus + ", " + "aofLastWriteStatus=" + aofLastWriteStatus + ", aofCurrentSize=" +
                aofCurrentSize + ", aofBaseSize=" + aofBaseSize + ", " + "aofPendingRewrite=" + aofPendingRewrite +
                ", " + "aofBufferLength=" + aofBufferLength + ", " + "aofRewriteBufferLength=" +
                aofRewriteBufferLength + ", " + "aofPendingBioFsync=" + aofPendingBioFsync + ", aofDelayedFsync=" +
                aofDelayedFsync + '}';
    }

}

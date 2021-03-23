package com.servermonitor.snmp.app.service;

import java.io.IOException;
import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;

/**
 * 这个类是从服务器获取监控信息类
 *
 * @author chenval 2021/3/22
 */
public interface ServerMessageService {
    /**
     * 获得cpu使用率
     *
     * @param serverMessage snmp信息 用于访问服务器
     * @return 使用率
     */
    int getCpuUtilization(ServerMessage serverMessage);

    /**
     * 获得cpu核心详细信息
     * @param serverMessage 服务器信息
     * @return 正常返回cpu的集合 失败返回空
     */
    List<String> getCpuCount(ServerMessage serverMessage);

    /**
     * 获取Memory占用率
     *
     * @param serverMessage 目标服务器信息
     * @return 正常返回当前内存使用率，否则返回-1
     */
    int getMemoryUtilization(ServerMessage serverMessage);

    /**
     * 在已知可用内存和总内存情况直接计算
     *
     * @param avail 当前可用内存
     * @param total 总内存
     * @return 内存使用率
     */
    int getMemoryUtilization(long avail, long total);

    /**
     * 获取可用Memory大小
     *
     * @param serverMessage
     * @return 可使用的内存大小，否则返回-1
     */
    long getAvailMemoryLinux(ServerMessage serverMessage);

    /**
     * 获得总内存大小
     *
     * @param serverMessage
     * @return 总内存大小
     */
    long getTotalMemory(ServerMessage serverMessage);

    /**
     * 测网络通不通 类似 ping ip
     *
     * @param serverMessage 目标服务器信息
     * @return 成功为true 失败为false
     * @throws IOException
     */
    boolean isEthernetConnection(ServerMessage serverMessage) throws IOException;

    /**
     * windows下获得所用内存
     * @param serverMessage 目标服务器信息
     * @return 内存使用大小单位Mb
     */
    long getUsedMemWindows(ServerMessage serverMessage);


}

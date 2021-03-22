package com.servermonitor.snmp.infra.helper;


import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.vo.LinuxSnmp;
import com.servermonitor.snmp.domain.vo.WindowsSnmp;
import com.servermonitor.snmp.app.service.impl.ServerMessageServiceImpl;


import java.io.IOException;

/**
 * @author chenval
 * @date 2020/6/18 17:49
 */
public class SnmpUtil {


    public static void msgFromServer(String ip, String communityName, int port, int version, String operatingSystem) {
        /**
         * 为snmp对象赋值 注入目标IP 登录口令等
         * */
        ServerMessageServiceImpl serverMessageServiceImpl = new ServerMessageServiceImpl();
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setCommunityName(communityName);
        serverMessage.setIp(ip);
        serverMessage.setPort(port);
        try {
            System.out.println("是否连接：" + serverMessageServiceImpl.isEthernetConnection(serverMessage));

        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerMonitorData serverMonitorData;

        /**
         * 如果没有指定操作系统，默认linux
         * */
        String win = "windows";
        if (win.equals(operatingSystem)) {
            serverMonitorData = WindowsSnmp.getMessageWindows(serverMessage, serverMessageServiceImpl);
        } else {
            serverMonitorData = LinuxSnmp.getMessageLinux(serverMessage, serverMessageServiceImpl);
        }
        System.out.println("内存使用率:" + serverMonitorData.getMemUtilization());
        System.out.println("cpu使用率:" + serverMonitorData.getCpuUtilization());
        // todo 存储
    }
}

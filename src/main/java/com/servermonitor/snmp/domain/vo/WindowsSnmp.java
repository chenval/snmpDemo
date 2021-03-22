package com.servermonitor.snmp.domain.vo;

import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.app.service.impl.ServerMessageServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenval
 * @date 2020/6/18 16:38
 */
public class WindowsSnmp {
    public static ServerMonitorData getMessageWindows(ServerMessage serverMessage, ServerMessageServiceImpl serverMessageServiceImpl){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        int cpu = serverMessageServiceImpl.getCpuUtilization(serverMessage);
        long totalMem = serverMessageServiceImpl.getTotalMemory(serverMessage);
        long usedMem = serverMessageServiceImpl.getUsedMemWindows(serverMessage);
        long mem = 100 - (100 * usedMem) / totalMem;
        System.out.println(new Date() + "   cpu利用率："+ cpu);
        System.out.println("内存使用率："+ mem);
        ServerMonitorData serverMonitorData = new ServerMonitorData();
        Date date = new Date();
        /**
         * 为javaBean赋值
         * */
        serverMonitorData.setDate(dateFormat.format(date));

        serverMonitorData.setCpuCount(serverMessageServiceImpl.getCpuCount(serverMessage).size());

        /**
         * 将cpu详细信息转换为String
         * */
        StringBuffer cpuDe= new StringBuffer();
        int count = 1;
        List<String> cpus = serverMessageServiceImpl.getCpuCount(serverMessage);
        Iterator iterator = cpus.iterator();
        while(iterator.hasNext()){
            cpuDe.append(iterator.next());
            if(count != cpus.size()){
                cpuDe.append(",");
            }
            count++;
        }
        serverMonitorData.setCpuDetail(cpuDe.toString());

        serverMonitorData.setCpuUtilization(cpu);

        serverMonitorData.setAvailMem(totalMem - usedMem);

        serverMonitorData.setTotalMem(totalMem);

        serverMonitorData.setMemUtilization((int)mem);

        serverMonitorData.setOperatingSystem("windows");

        return serverMonitorData;
    }
}

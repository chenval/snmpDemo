package com.servermonitor.snmp.domain.vo;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.app.service.impl.ServerMessageServiceImpl;

/**
 * @author chenval
 * @date 2020/6/18 16:39
 */
public class LinuxSnmp {
    public static ServerMonitorData getMessageLinux(ServerMessage serverMessage, ServerMessageServiceImpl serverMessageServiceImpl){
        SimpleDateFormat  dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        int cpu = serverMessageServiceImpl.getCpuUtilization(serverMessage);
        long totalMem = serverMessageServiceImpl.getTotalMemory(serverMessage);
        long availMem = serverMessageServiceImpl.getAvailMemoryLinux(serverMessage);
        int mem = serverMessageServiceImpl.getMemoryUtilization(availMem,totalMem);

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
        for(String x :cpus){
            cpuDe.append(x);
            if(count < cpus.size()){
                cpuDe.append(",");
            }
            count++;
        }
        serverMonitorData.setCpuDetail(cpuDe.toString());

        serverMonitorData.setCpuUtilization(cpu);

        serverMonitorData.setAvailMem(availMem);

        serverMonitorData.setTotalMem(totalMem);

        serverMonitorData.setMemUtilization(mem);

        serverMonitorData.setOperatingSystem("linux");
        return serverMonitorData;
    }
}

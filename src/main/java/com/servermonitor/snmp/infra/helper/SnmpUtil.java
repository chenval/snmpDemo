package com.servermonitor.snmp.infra.helper;


import com.servermonitor.snmp.app.service.ServerMessageService;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.infra.constant.ConstantCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author chenval
 * @date 2020/6/18 17:49
 */
@Component
public class SnmpUtil {

    @Autowired
    ServerMessageService serverMessageService;

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");

    public ServerMonitorData getMessage(ServerMessage serverMessage) {
        ServerMonitorData result = new ServerMonitorData();
        result.setIp(serverMessage.getIp());
        result.setServerName(serverMessage.getName());
        StringBuilder cpuDe = new StringBuilder();
        int count = 1;
        List<String> cpus = serverMessageService.getCpuCount(serverMessage);
        for (String x : cpus) {
            cpuDe.append(x);
            if (count < cpus.size()) {
                cpuDe.append(",");
            }
            count++;
        }
        result.setDate(dateFormat.format(date));
        result.setCpuDetail(cpuDe.toString());

        int cpu = serverMessageService.getCpuUtilization(serverMessage);
        result.setCpuUtilization(cpu);

        long totalMem = serverMessageService.getTotalMemory(serverMessage);
        result.setTotalMem(totalMem);

        result.setCpuCount(serverMessageService.getCpuCount(serverMessage).size());
        int mem = 0;
        if (ConstantCode.windows.equals(serverMessage.getOperationSystem())) {
            long usedMem = serverMessageService.getUsedMemWindows(serverMessage);
            mem = (int) (100 - (100 * usedMem) / totalMem);
            result.setMemUtilization(mem);
            result.setAvailMem(totalMem - usedMem);
            result.setOperatingSystem(ConstantCode.windows);
        } else {
            long availMem = serverMessageService.getAvailMemoryLinux(serverMessage);
            mem = serverMessageService.getMemoryUtilization(availMem, totalMem);
            result.setAvailMem(availMem);
            result.setMemUtilization(mem);
            result.setOperatingSystem(ConstantCode.linux);
        }
        return result;
    }

//    public ServerMonitorData getMessageLinux(ServerMessage serverMessage) {
//
//        int cpu = serverMessageService.getCpuUtilization(serverMessage);
//        long totalMem = serverMessageService.getTotalMemory(serverMessage);
//        long availMem = serverMessageService.getAvailMemoryLinux(serverMessage);
//        int mem = serverMessageService.getMemoryUtilization(availMem, totalMem);
//        /**
//         * 为javaBean赋值
//         * */
//        serverMonitorData.setDate(dateFormat.format(date));
//
//        serverMonitorData.setCpuCount(serverMessageService.getCpuCount(serverMessage).size());
//
//        /**
//         * 将cpu详细信息转换为String
//         * */
//        StringBuilder cpuDe = new StringBuilder();
//        int count = 1;
//        List<String> cpus = serverMessageService.getCpuCount(serverMessage);
//        for (String x : cpus) {
//            cpuDe.append(x);
//            if (count < cpus.size()) {
//                cpuDe.append(",");
//            }
//            count++;
//        }
//        serverMonitorData.setCpuDetail(cpuDe.toString());
//
//        serverMonitorData.setCpuUtilization(cpu);
//
//        serverMonitorData.setAvailMem(availMem);
//
//        serverMonitorData.setTotalMem(totalMem);
//
//        serverMonitorData.setMemUtilization(mem);
//
//        serverMonitorData.setOperatingSystem("linux");
//        return serverMonitorData;
//    }
//
//    public ServerMonitorData getMessageWindows(ServerMessage serverMessage) {
//        int cpu = serverMessageService.getCpuUtilization(serverMessage);
//        long totalMem = serverMessageService.getTotalMemory(serverMessage);
//        long usedMem = serverMessageService.getUsedMemWindows(serverMessage);
//        long mem = 100 - (100 * usedMem) / totalMem;
//        /**
//         * 为javaBean赋值
//         * */
//        serverMonitorData.setDate(dateFormat.format(date));
//        serverMonitorData.setCpuCount(serverMessageService.getCpuCount(serverMessage).size());
//
//        /**
//         * 将cpu详细信息转换为String
//         * */
//        StringBuffer cpuDe = new StringBuffer();
//        int count = 1;
//        List<String> cpus = serverMessageService.getCpuCount(serverMessage);
//        Iterator iterator = cpus.iterator();
//        while (iterator.hasNext()) {
//            cpuDe.append(iterator.next());
//            if (count != cpus.size()) {
//                cpuDe.append(",");
//            }
//            count++;
//        }
//
//        serverMonitorData.setCpuDetail(cpuDe.toString());
//
//        serverMonitorData.setCpuUtilization(cpu);
//
//        serverMonitorData.setAvailMem(totalMem - usedMem);
//
//        serverMonitorData.setTotalMem(totalMem);
//
//        serverMonitorData.setMemUtilization((int) mem);
//
//        serverMonitorData.setOperatingSystem("windows");
//
//        return serverMonitorData;
//    }

}

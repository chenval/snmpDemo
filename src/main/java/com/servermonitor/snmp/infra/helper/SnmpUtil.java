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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

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
        if (ConstantCode.WINDOWS.equals(serverMessage.getOperationSystem())) {
            long usedMem = serverMessageService.getUsedMemWindows(serverMessage);
            mem = (int) (100 - (100 * usedMem) / totalMem);
            result.setMemUtilization(mem);
            result.setAvailMem(totalMem - usedMem);
            result.setOperationSystem(ConstantCode.WINDOWS);
        } else {
            long availMem = serverMessageService.getAvailMemoryLinux(serverMessage);
            mem = serverMessageService.getMemoryUtilization(availMem, totalMem);
            result.setAvailMem(availMem);
            result.setMemUtilization(mem);
            result.setOperationSystem(ConstantCode.LINUX);
        }
        return result;
    }

}

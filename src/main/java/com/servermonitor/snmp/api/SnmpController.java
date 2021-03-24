package com.servermonitor.snmp.api;

import java.util.List;
import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.domain.repository.MonitorMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenval 2021/3/22
 */
@RestController
@RequestMapping("/sysMonitor")
public class SnmpController {

    @Autowired
    GetMonitorMessageService getMonitorMessageService;
    @Autowired
    MonitorMessageRepository monitorMessageRepository;
    @GetMapping("/getAllServerMessageNow")
    public List<ServerMonitorData> getAllMessageNow() {
        return getMonitorMessageService.getAllServerMessageNow();
    }

    @GetMapping("/getServerMessageNow")
    public ServerMonitorData getServerMessage(@RequestParam String ip) {
        return getMonitorMessageService.getServerMessageNow(ip);
    }
    @GetMapping("/saveAll")
    public int saveAll() {
        return monitorMessageRepository.insertAllData(getMonitorMessageService.getAllServerMessageNow());
    }
    @GetMapping("/getServerMessageByIp")
    public List<ServerMonitorData> getMessageInPastHalfHour(@RequestParam String ip){
        return monitorMessageRepository.getMonitorMessage(ip);
    }
}

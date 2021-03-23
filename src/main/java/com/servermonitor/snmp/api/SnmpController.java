package com.servermonitor.snmp.api;

import java.util.List;
import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.domain.repository.MonitorMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/getServerMessage")
    public ServerMonitorData getServerMessage(@RequestParam(required = false) String ip) {
        return getMonitorMessageService.getServerMessageNow(ip);
    }
    @GetMapping("/saveAll")
    public int saveAll() {
        return monitorMessageRepository.insertAllData(getMonitorMessageService.getAllServerMessageNow());
    }
}

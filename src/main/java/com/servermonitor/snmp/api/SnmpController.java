package com.servermonitor.snmp.api;

import java.util.List;
import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenval 2021/3/22
 */
@RestController
@RequestMapping("/sysMonitor")
public class SnmpController {

    @Autowired
    GetMonitorMessageService getMonitorMessageService;
    @GetMapping("/getAllServerMessageNow")
    public List<ServerMonitorData> getAllMessageNow(){
        return getMonitorMessageService.getAllServerMessageNow();
    }
}

package com.servermonitor.snmp.config;

import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.repository.MonitorMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author chenval 2021-03-24
 */
@Configuration
@EnableScheduling
public class SaveMonitorDataScheduleTask {
    @Autowired
    GetMonitorMessageService getMonitorMessageService;
    @Autowired
    MonitorMessageRepository monitorMessageRepository;
    @Scheduled(cron = "0 */1 * * * ?")
    public void save(){
        monitorMessageRepository.insertAllData(getMonitorMessageService.getAllServerMessageNow());
    }
}

package com.servermonitor.snmp.app.job;

import java.util.List;
import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.domain.repository.MonitorMessageRepository;
import com.servermonitor.snmp.domain.repository.ServerMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author yuanyang.chen@hand-china.com 2021-03-23
 */
//@Configuration
//@EnableScheduling
public class SaveMonitorMessage {
//    @Autowired
//    MonitorMessageRepository monitorMessageRepository;
//    @Autowired
//    GetMonitorMessageService getMonitorMessageService;
//
//    @Scheduled(cron = "0/5 * * * * ? ")
//    private void configureTasks() {
//        List<ServerMonitorData> list = getMonitorMessageService.getAllServerMessageNow();
//        monitorMessageRepository.insertAllData(list);
//    }
}

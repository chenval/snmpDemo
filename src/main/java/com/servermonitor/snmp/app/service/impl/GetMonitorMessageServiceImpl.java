package com.servermonitor.snmp.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.domain.repository.ServerMessageRepository;
import com.servermonitor.snmp.infra.helper.SnmpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenval 2021/3/22
 */
@Service
public class GetMonitorMessageServiceImpl implements GetMonitorMessageService {

    @Autowired
    ServerMessageRepository serverMessageRepository;
    /**
     * 这里获取当前最新状态就不走数据库了直接查询。
     *
     * @return
     */
    @Autowired
    SnmpUtil snmpUtil;
    @Override
    public List<ServerMonitorData> getAllServerMessageNow() {
        List<ServerMessage> servers = serverMessageRepository.getAllServer();
        List<ServerMonitorData> result = new ArrayList<>();
        for (ServerMessage serverMessage : servers) {
            result.add(snmpUtil.getMessage(serverMessage));
        }
        return result;
    }

    @Override
    public ServerMonitorData getServerMessageNow(String ip) {
        return snmpUtil.getMessage(serverMessageRepository.getOneServer(ip));
    }
}

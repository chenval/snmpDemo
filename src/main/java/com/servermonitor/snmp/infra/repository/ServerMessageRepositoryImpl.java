package com.servermonitor.snmp.infra.repository;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.repository.ServerMessageRepository;
import com.servermonitor.snmp.infra.mapper.ServerMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenval 2021/3/22
 */
public class ServerMessageRepositoryImpl implements ServerMessageRepository {

    @Autowired
    ServerMapper serverMapper;
    @Override
    public List<ServerMessage> getAllserver() {
        return serverMapper.getAllServer();
    }
}

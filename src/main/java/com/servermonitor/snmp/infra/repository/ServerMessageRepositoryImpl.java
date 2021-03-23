package com.servermonitor.snmp.infra.repository;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.domain.repository.ServerMessageRepository;
import com.servermonitor.snmp.infra.mapper.ServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author chenval 2021/3/22
 */
@Repository
public class ServerMessageRepositoryImpl implements ServerMessageRepository {

    @Autowired
    ServerMapper serverMapper;
    @Override
    public List<ServerMessage> getAllServer() {
        return serverMapper.getAllServer();
    }

    @Override
    public ServerMessage getOneServer(String ip) {
        return serverMapper.getOneServer(ip);
    }
}

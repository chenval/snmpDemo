package com.servermonitor.snmp.domain.repository;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;

/**
 * @author chenval 2021/3/22
 */
public interface ServerMessageRepository {
    /**
     * 获得所有服务器
     * @return
     */
    List<ServerMessage> getAllserver();

}

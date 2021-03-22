package com.servermonitor.snmp.app.service;


import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;

/**
 * @author chenval 2021/3/22
 */
public interface GetMonitorMessageService {
    /**
     * 获得当前所有服务器的监控信息
     * @return 所有服务器的监控信息
     */
    List<ServerMonitorData> getAllServerMessageNow();

}

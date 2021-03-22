package com.servermonitor.snmp.app.service.impl;

import java.util.List;
import com.servermonitor.snmp.app.service.GetMonitorMessageService;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;

/**
 * @author chenval 2021/3/22
 */
public class GetMonitorMessageServiceImpl implements GetMonitorMessageService {
    /**
     * 这里获取当前最新状态就不走数据库了直接查询。
     * @return
     */
    @Override
    public List<ServerMonitorData> getAllServerMessageNow() {
        return null;
    }
}

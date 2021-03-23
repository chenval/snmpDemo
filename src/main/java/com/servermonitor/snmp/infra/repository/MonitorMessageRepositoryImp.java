package com.servermonitor.snmp.infra.repository;

import com.servermonitor.snmp.domain.repository.MonitorMessageRepository;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import com.servermonitor.snmp.infra.mapper.MonitorMessageMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/17 10:08
 */
@Repository
public class MonitorMessageRepositoryImp implements MonitorMessageRepository {

    @Autowired
    MonitorMessageMapper monitorMessageMapper;
    @Override
    public ServerMonitorData queryDataById(String id) {
        return null;
    }

    @Override
    public List<ServerMonitorData> queryAllData() {
        return null;
    }

    @Override
    public void
    insertData( ServerMonitorData serverMonitorData) {
       return ;
    }

    @Override
    public int insertAllData(List<ServerMonitorData> list) {
        return monitorMessageMapper.saveMonitorMessageAllServerNow(list);
    }

}

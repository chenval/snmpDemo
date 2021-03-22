package com.servermonitor.snmp.infra.repository;

import com.servermonitor.snmp.domain.repository.MonitorMessageRepository;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/17 10:08
 */
public class MonitorMessageRepositoryImp implements MonitorMessageRepository {
    public SqlSession sqlSession;

    public MonitorMessageRepositoryImp(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }



    @Override
    public ServerMonitorData queryDataById(String id) {
        return this.sqlSession.selectOne("Data.queryDataById",id);
    }

    @Override
    public List<ServerMonitorData> queryAllData() {
        return this.sqlSession.selectList("Data.queryDataAll");
    }

    @Override
    public void insertData( ServerMonitorData serverMonitorData) {
        this.sqlSession.insert("Data.insertData", serverMonitorData);
    }

}

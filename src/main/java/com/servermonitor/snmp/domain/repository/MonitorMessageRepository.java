package com.servermonitor.snmp.domain.repository;

import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/17 10:05
 */
public interface MonitorMessageRepository {
    /**
    * 根据id获得信息
    * @param id 数据的主键
    * @return 返回找到的数据
    */
    ServerMonitorData queryDataById(@Param("id")String id);

    /**
     *  查找所有信息
     * @param
     * @return Data的集合
     * */
    List<ServerMonitorData> queryAllData();

    /**
     * fetch data by rule id
     *
     * @param serverMonitorData rule id
     * @return 无返回值
     */
    void insertData(ServerMonitorData serverMonitorData);

    /**
     *
     */


}

package com.servermonitor.snmp.infra.mapper;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMonitorData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chenval 2021/3/22
 */
@Mapper
public interface MonitorMessageMapper {
    /**
     * 存储监控信息
     * @param serverMonitorData 存储实体
     * @return 成功数量
     */
    int saveMonitorMessageAllServerNow(@Param("datas") List<ServerMonitorData> serverMonitorData);
}
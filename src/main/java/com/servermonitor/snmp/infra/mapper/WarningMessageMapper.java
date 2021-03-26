package com.servermonitor.snmp.infra.mapper;

import java.util.List;
import com.servermonitor.snmp.domain.entity.WarningMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenval 2021-03-26
 */
@Mapper
public interface WarningMessageMapper {

    /**
     * 插入告警信息
     * @return 成功条数
     * @param warningMessage
     */
    int insertWarningMessage(@Param("warningMessage") WarningMessage warningMessage);

    /**
     * 更新告警信息
     */
    int updateWarningMessage(@Param("warningMessage")WarningMessage warningMessage);
    /**
     * 查找最近num条告警信息
     */
    List<WarningMessage> selectWarningMessageList(@Param("num") int num,@Param("ip") String ip);

    /**
     * 处理警告
     */
    int handleWarningMessage(String ip);
}

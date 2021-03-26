package com.servermonitor.snmp.domain.repository;

import java.util.List;
import com.servermonitor.snmp.domain.entity.WarningMessage;

/**
 * @author chenval 2021-03-26
 */
public interface WarningMessageRepository {
    int updateWarningMessage(WarningMessage warningMessage);
    List<WarningMessage> selectWarningMessageList(int num,String ip);
    int insertWarningMessage(WarningMessage warningMessage);
}

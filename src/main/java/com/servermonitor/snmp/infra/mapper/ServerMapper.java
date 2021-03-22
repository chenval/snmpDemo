package com.servermonitor.snmp.infra.mapper;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chenval 2021/3/22
 */
@Mapper
public interface ServerMapper {
    /**
     * 获得所有服务器的信息。
     * @return
     */
    List<ServerMessage> getAllServer();
}

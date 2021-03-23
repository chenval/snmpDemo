package com.servermonitor.snmp.infra.mapper;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenval 2021/3/22
 */
@Mapper
public interface ServerMapper {
    /**
     * 获得所有服务器的信息。
     * @return 所有服务器信息
     */
    List<ServerMessage> getAllServer();

    /**
     * 根据信息查找指定服务器
     * @param ip 查询实体
     * @return 指定服务器
     */
    ServerMessage getOneServer(@Param("ip") String ip);
}

package com.servermonitor.snmp.app.service;

import java.util.List;
import com.servermonitor.snmp.domain.entity.ServerMessage;

/**
 * 从指定服务器获取信息的两种方式。
 * @author chenval
 */
public interface SnmpMethodService {
    /**
     * 通过get的方式获得oid的信息
     * @param oid 查询指令
     * @param serverMessage 服务器信息
     * @return List<String> 将结果返回为list集合
     */
    List<String> getByTable(String oid, ServerMessage serverMessage);
    /**
     * 通过walk查找oid对应的信息并返回list集合
     * @param serverMessage 服务器信息
     * @param oid 查询指令
     * @return list<string>
     */
    List<String> walkByTable(String oid, ServerMessage serverMessage);

}

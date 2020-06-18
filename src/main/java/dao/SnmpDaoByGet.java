package dao;

import entry.SnmpMsg;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/18 9:02
 */
public interface SnmpDaoByGet {
    /**
     * 通过get的方式获得oid的信息
     * @param oid
     * @param snmpMsg
     * @return List<String> 将结果返回为list集合
     */
    List<String> getByTable(String oid, SnmpMsg snmpMsg);
}

package dao;

import entry.SnmpMsg;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/18 9:03
 */

public interface SnmpDaoByWalk {

    /**
     * 通过walk查找oid对应的信息并返回list集合
     * @param snmpMsg
     * @param oid
     * @return list<string>
     */
    List<String> walkByTable(String oid, SnmpMsg snmpMsg);
}

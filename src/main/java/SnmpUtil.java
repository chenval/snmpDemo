import dao.DataDao;
import entry.Data;
import entry.SnmpMsg;
import operatingsystem.LinuxSnmp;
import operatingsystem.WindowsSnmp;
import org.apache.ibatis.session.SqlSession;
import service.SnmpService;
import util.DataBase;

import java.io.IOException;

/**
 * @author chenval
 * @date 2020/6/18 17:49
 */
public class SnmpUtil {
    public static void msgFromServer(String ip, String communityName, int port, int version, String operatingSystem) {
        /**
         * 为snmp对象赋值 注入目标IP 登录口令等
         * */
        SnmpService snmpService = new SnmpService();
        SnmpMsg snmpMsg = new SnmpMsg();
        snmpMsg.setIp(ip);
        snmpMsg.setCommunityName(communityName);
        snmpMsg.setHostIp(ip);
        snmpMsg.setPort(port);
        snmpMsg.setVersion(version);
        try {
            System.out.println("是否连接：" + snmpService.isEthernetConnection(snmpMsg));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Data data;

        /**
         * 如果没有指定操作系统，默认linux
         * */
        String win = "windows";
        if (win.equals(operatingSystem)) {
            data = WindowsSnmp.getMessageWindows(snmpMsg, snmpService);
        } else {
            data = LinuxSnmp.getMessageLinux(snmpMsg, snmpService);
        }
        System.out.println("内存使用率:" + data.getMemUtilization());
        System.out.println("cpu使用率:" + data.getCpuUtilization());
        try {
            SqlSession session = DataBase.getConnection();
            DataDao dataDao = session.getMapper(DataDao.class);
            dataDao.insertData(data);
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

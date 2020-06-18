package dto;

import dao.SnmpDaoByWalk;
import entry.SnmpMsg;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenval
 * @date 2020/6/10 23:51
 * @Description: snmp功能的实现类
 */
public class SnmpDaoByWalkImp implements SnmpDaoByWalk {
    /**
     * 团体名
     * @Description:
     * 团体（community）是基本的安全机制，用于实现SNMP网络管理员访问SNMP管理代理时的身份验证。
     * 类似于密码，默认值为 public。
     * 团体名（Community name）是管理代理的口令，
     * 管理员被允许访问数据对象的前提就是网络管理员知道网络代理的口令
     * */
    private String communityName;
    /**
     * 主机IP
     * */
    private String hostIp;
    /**
     * 端口
     * */
    private Integer port;
    /**
     * 版本
     * */
    private int version;

    /**
    *@Description 通过walk请求得到消息
    *@param  oid : 指令代号 snmpMsg :传入的实体
    *@return 返回监控结果List集合
    */
    @Override
    public List<String> walkByTable(String oid, SnmpMsg snmpMsg){
        Snmp snmp = null;
        /**
         * CommunityTarget类实现了Target接口，target是目标主机的相关设置
         * */
        CommunityTarget target = null;
        List<String> result = new ArrayList<>();

        communityName = snmpMsg.getCommunityName();
        hostIp = snmpMsg.getHostIp();
        port = snmpMsg.getPort();
        version = snmpMsg.getVersion();
        try {
            /**
             * 构造一个默认udp
             * */
            DefaultUdpTransportMapping dm = new DefaultUdpTransportMapping();
            /**
             * 对snmp连接进行监听
             * */
            snmp = new Snmp(dm);
            snmp.listen();

            /**
             * 对监听目标进行赋值
             * */
            target = new CommunityTarget();
            target.setCommunity(new OctetString(communityName));
            target.setVersion(version);
            target.setAddress(new UdpAddress(hostIp+"/"+port));
            target.setTimeout(1000);
            target.setRetries(1);

            TableUtils tutils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
            OID[] columns = new OID[1];
            columns[0] = new VariableBinding(new OID(oid)).getOid();
            List<TableEvent> list =  tutils.getTable(target, columns, null, null);
            for(TableEvent e : list){
                VariableBinding[] vb = e.getColumns();
                if(null == vb){
                    continue;
                }
                result.add(vb[0].getVariable().toString());
            }
            /**
             * 关闭连接
             * */
            snmp.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                if(snmp != null)
                {
                    snmp.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }


}

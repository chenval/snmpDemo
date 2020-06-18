package dto;

import dao.SnmpDaoByGet;
import entry.SnmpMsg;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

/**
 * @author chenval
 * @date 2020/6/11 14:04
 * @Description 通过get的方式获得信息
 */
public class SnmpDaoByGetImp implements SnmpDaoByGet {
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
     *具体地址
     * */
    private Address targetAddress;
    /**
     * snmp设置
     * */
    private Snmp snmp;


    /**
     * 异步获取返回的信息
     * */
    @SuppressWarnings("unchecked")
    public void getByTbleAsnc(String oid, SnmpMsg snmpMsg){
        List<String> result = new ArrayList<>();
        communityName = snmpMsg.getCommunityName();
        hostIp = snmpMsg.getHostIp();
        port = snmpMsg.getPort();
        version = snmpMsg.getVersion();
        targetAddress = GenericAddress.parse("udp:" + hostIp + "/" + port);
        try {
            TransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();


            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            // 操作类型
            pdu.setType(PDU.GET);


            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(snmpMsg.getCommunityName()));
            target.setAddress(targetAddress);
            // 通信不成功时的重试次数 N+1次
            target.setRetries(2);
            // 超时时间
            target.setTimeout(2 * 1000);
            // SNMP 版本
            target.setVersion(SnmpConstants.version2c);

            // 设置监听对象
            ResponseListener listener = (event)-> {
                    if(null != event && event.getResponse() != null){
                        Vector<VariableBinding> variableBindings = (Vector<VariableBinding>) event.getResponse().getVariableBindings();
                        for(int i = 0; i < variableBindings.size(); i++){
                            VariableBinding variableBinding = variableBindings.elementAt(i);
                            System.out.println(variableBinding.getVariable());
                            result.add(variableBinding.getVariable().toString());
                        }
                    }
            };
            // 发送报文
            snmp.send(pdu, target, null, listener);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 同步获取
     * */
    @SuppressWarnings("checked")
    @Override
    public List<String> getByTable(String oid, SnmpMsg snmpMsg){
        List<String> result = new ArrayList<>();
        communityName = snmpMsg.getCommunityName();
        hostIp = snmpMsg.getHostIp();
        port = snmpMsg.getPort();
        version = snmpMsg.getVersion();
        targetAddress = GenericAddress.parse("udp:" + hostIp + "/" + port);
        try {
            TransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();


            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            // 操作类型
            pdu.setType(PDU.GET);


            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(snmpMsg.getCommunityName()));
            target.setAddress(targetAddress);

            // 通信不成功时的重试次数 N+1次
            target.setRetries(2);

            // 超时时间
            target.setTimeout(2 * 1000);

            // SNMP 版本
            target.setVersion(SnmpConstants.version2c);

            //解析snmp返回结果
            ResponseEvent event = snmp.send(pdu,target);

            //处理因为网络问题得到结果为空的情况
            Optional<PDU> isNull = Optional.ofNullable(event.getResponse());
            if(isNull.isPresent()){
                VariableBinding variableBinding = event.getResponse().get(0);
                result.add(variableBinding.getVariable().toString());
            }else {
                //结果为空后放入-1，避免导出数据产生错误
                result.add("-1");
            }
            snmp.close();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
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

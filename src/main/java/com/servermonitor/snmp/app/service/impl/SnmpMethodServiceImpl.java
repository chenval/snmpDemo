package com.servermonitor.snmp.app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.servermonitor.snmp.app.service.SnmpMethodService;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.infra.constant.ConstantCode;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;
import org.springframework.stereotype.Service;

/**
 * @author chenval 2021/3/22
 */
@Service
public class SnmpMethodServiceImpl implements SnmpMethodService {

    private String communityName;
    /**
     * 主机IP
     * */
    private String ip;
    /**
     * 端口
     * */
    private Integer port;
    /**
     * snmp设置
     * */
    private Snmp snmp;
    /**
     * 同步获取
     * */
    @SuppressWarnings("checked")
    @Override
    public List<String> getByTable(String oid, ServerMessage serverMessage){
        List<String> result = new ArrayList<>();
        communityName = serverMessage.getCommunityName();
        ip = serverMessage.getIp();
        port = serverMessage.getPort();
        /**
         *具体地址
         * */
        Address targetAddress = GenericAddress.parse("udp:" + ip + "/" + port);
        try {
            TransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();


            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            // 操作类型
            pdu.setType(PDU.GET);


            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(serverMessage.getCommunityName()));
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


    /**
     *@Description 通过walk请求得到消息
     *@param  oid : 指令代号 snmpMsg :传入的实体
     *@return 返回监控结果List集合
     */
    @Override
    public List<String> walkByTable(String oid, ServerMessage serverMessage){
        Snmp snmp = null;
        /**
         * CommunityTarget类实现了Target接口，target是目标主机的相关设置
         * */
        CommunityTarget target = null;
        List<String> result = new ArrayList<>();

        communityName = serverMessage.getCommunityName();
        ip = serverMessage.getIp();
        port = serverMessage.getPort();
        try {
            DefaultUdpTransportMapping dm = new DefaultUdpTransportMapping();
            snmp = new Snmp(dm);
            snmp.listen();

            target = new CommunityTarget();
            target.setCommunity(new OctetString(communityName));
            target.setVersion(ConstantCode.COMMUNITY_TARGET_VERSION);
            target.setAddress(new UdpAddress(ip +"/"+port));
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

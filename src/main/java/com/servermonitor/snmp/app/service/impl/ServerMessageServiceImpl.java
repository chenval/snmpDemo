package com.servermonitor.snmp.app.service.impl;


import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import com.servermonitor.snmp.app.service.ServerMessageService;
import com.servermonitor.snmp.app.service.SnmpMethodService;
import com.servermonitor.snmp.domain.entity.ServerMessage;
import com.servermonitor.snmp.infra.constant.code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenval
 * @date 2020/6/11 0:01
 * @Description snmp指令查询服务
 */
@Service
public class ServerMessageServiceImpl implements ServerMessageService {

    @Autowired
    SnmpMethodService snmpMethodService;

    /**
     * 获取CPU使用率
     *
     * @param serverMessage
     * @return 正常返回CPU当前使用率，否则返回-1
     */
    @Override
    public int getCpuUtilization(ServerMessage serverMessage) {
        List<String> result = snmpMethodService.walkByTable(
            code.ALL_CPU_DETAIL_WALK.getCode(), serverMessage);
        if (result == null || result.size() == 0) {
            return -1;
        }
        int sum = 0;
        for (String s : result) {
            sum += Integer.parseInt(s);
        }
        sum /= result.size();
        return sum;
    }

    /**
     * @param serverMessage
     * @return 正常返回cpu的集合 失败返回空
     * @Description
     */
    @Override
    public List<String> getCpuCount(ServerMessage serverMessage) {
        List<String> result = snmpMethodService.walkByTable(code.ALL_CPU_DETAIL_WALK.getCode(), serverMessage);
        if (result.size() == 0) {
            result.add("-1");
            return result;
        }
        return result;
    }

    /**
     * 获取Memory占用率
     *
     * @param serverMessage
     * @return 正常返回当前内存使用率，否则返回-1
     * @throws IOException
     */
    @Override
    public int getMemoryUtilization(ServerMessage serverMessage) {
        /**
         * 获取空闲内存
         * */
        long freeMen = getAvailMemoryLinux(serverMessage);
        /**
         * 目标地址总的内存
         * */
        long allresultList = getTotalMemory(serverMessage);

        return 100 - (int) ((freeMen * 100) / allresultList);
    }

    /**
     * @param avail total
     * @return 内存使用率
     * @Description 在已知可用内存和总内存情况直接计算
     */
    @Override
    public int getMemoryUtilization(long avail, long total) {
        return 100 - (int) (100 * avail / total);
    }

    /**
     * 获取可用Memory大小
     *
     * @param serverMessage
     * @return 可使用的内存大小，否则返回-1
     */
    @Override
    public long getAvailMemoryLinux(ServerMessage serverMessage) {
        /**
         * linux的部分oid和window的不同 该内存查询oid只能在linux上使用
         * */
        List<String> freeMen = snmpMethodService.getByTable(code.FREE_MEM_GET.getCode(), serverMessage);
        List<String> cacheMem = snmpMethodService.getByTable(code.CACHE_MEM_GET.getCode(), serverMessage);
        List<String> bufferMem = snmpMethodService.getByTable(code.BUFFER_MEM_GET.getCode(), serverMessage);
        /**
         * 内存实际大小很可能超过int范围所以使用long，long有8字节完全能放下
         * */
        long used = Long.parseLong(freeMen.get(0));
        long buffer = Long.parseLong(bufferMem.get(0));
        long cache = Long.parseLong(cacheMem.get(0));
        return used + buffer + cache;
    }

    /**
     * 获得总内存大小
     *
     * @param serverMessage
     * @return 总内存大小
     */
    @Override
    public long getTotalMemory(ServerMessage serverMessage) {
        /**
         * 获得总内存的大小
         * */
        List<String> allresultList = snmpMethodService.getByTable(code.ALL_MEM_GET.getCode(), serverMessage);
        return Long.parseLong(allresultList.get(0));
    }

    /**
     * 测网络通不通 类似 ping ip
     *
     * @param serverMessage
     * @return 成功为true 失败为false
     * @throws IOException
     */
    @Override
    public boolean isEthernetConnection(ServerMessage serverMessage) throws IOException {

        InetAddress ad = InetAddress.getByName(serverMessage.getIp());
        /**
         * 如果2秒达不到就是超时
         * */
        return ad.isReachable(2000);
    }

    /**
     * windows下获得所用内存
     */
    @Override
    public long getUsedMemWindows(ServerMessage serverMessage) {
        List<String> test = snmpMethodService.walkByTable(code.EVERY_PROCESS_MEM_WALK.getCode(), serverMessage);
        long res = 0;
        for (String x : test) {
            res += Long.parseLong(x);
        }
        return res;
    }

}


package service;

import dto.SnmpDaoByGetImp;
import dto.SnmpDaoByWalkImp;
import listencode.code;
import entry.SnmpMsg;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * @author  chenval
 * @date  2020/6/11 0:01
 * @Description snmp指令查询服务
 */
public class SnmpService {
    /**
     * 通过walk方式查询的dao层对象
     * */
    private SnmpDaoByWalkImp snmpDaoByWalkImp = new SnmpDaoByWalkImp();
    /**
     * 通过get方式查询的dao层对象
     * */
    private SnmpDaoByGetImp snmpDaoByGetImp = new SnmpDaoByGetImp();

    private SnmpDaoByWalkImp getInstanceSnmpDaoWalk() {
        return snmpDaoByWalkImp;
    }
    private SnmpDaoByGetImp getInstanceSnmpDaoGet() {
        return snmpDaoByGetImp;
    }
    /**
     * 获取CPU使用率
     *
     * @param snmpMsg
     * @return 正常返回CPU当前使用率，否则返回-1
     */
    public int getCpuUtilization(SnmpMsg snmpMsg) {
        List<String> result = getInstanceSnmpDaoWalk().walkByTable(
                code.ALL_CPU_DETAIL_WALK.getCode(), snmpMsg);
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
    *@Description
    *@param snmpMsg
    *@return 正常返回cpu的集合 失败返回空
    */
    public List<String> getCpuCount(SnmpMsg snmpMsg){
        List<String> result = getInstanceSnmpDaoWalk().walkByTable(code.ALL_CPU_DETAIL_WALK.getCode(),snmpMsg);
        if(result.size() == 0){
            result.add("-1");
            return result;
        }
        return result;
    }
    /**
     * 获取Memory占用率
     *
     * @param snmpMsg
     * @return 正常返回当前内存使用率，否则返回-1
     * @throws IOException
     */
    public int getMemoryUtilization(SnmpMsg snmpMsg) {
        /**
         * 获取空闲内存
         * */
        long freeMen = getAvailMemoryLinux(snmpMsg);
        /**
         * 目标地址总的内存
         * */
        long allresultList = getTotalMemory(snmpMsg);

        return 100 - (int)((freeMen * 100)/allresultList);
    }
    /**
    *@Description 在已知可用内存和总内存情况直接计算
    *@param avail total
    *@return 内存使用率
    */
    public int getMemoryUtilization(long avail,long total){
        return 100 - (int)(100 * avail / total);
    }
    /**
     * 获取可用Memory大小
     * @param snmpMsg
     * @return 可使用的内存大小，否则返回-1
     * */
    public long getAvailMemoryLinux(SnmpMsg snmpMsg){
        /**
         * linux的部分oid和window的不同 该内存查询oid只能在linux上使用
         * */
        List<String> freeMen = getInstanceSnmpDaoGet().getByTable(code.FREE_MEM_GET.getCode(),snmpMsg);
        List<String> cacheMem = getInstanceSnmpDaoGet().getByTable(code.CACHE_MEM_GET.getCode(),snmpMsg);
        List<String> bufferMem = getInstanceSnmpDaoGet().getByTable(code.BUFFER_MEM_GET.getCode(),snmpMsg);
        /**
         * 内存实际大小很可能超过int范围所以使用long，long有8字节完全能放下
         * */
        long used = Long.valueOf(freeMen.get(0));
        long buffer = Long.valueOf(bufferMem.get(0));
        long cache = Long.valueOf(cacheMem.get(0));
        System.out.println(used + buffer + cache+"sss");
        return used + buffer + cache;
    }
    /**
     * 获得总内存大小
     * @param snmpMsg
     * @return 总内存大小
     * */
    public long getTotalMemory(SnmpMsg snmpMsg){
        /**
         * 获得总内存的大小
         * */
        List<String> allresultList = getInstanceSnmpDaoGet().getByTable(code.ALL_MEM_GET.getCode(), snmpMsg);
        System.out.println(allresultList.get(0));
        return Long.valueOf(allresultList.get(0));
    }
    /**
     * 测网络通不通 类似 ping ip
     *
     * @param snmpMsg
     * @return 成功为true 失败为false
     * @throws IOException
     */
    public boolean isEthernetConnection(SnmpMsg snmpMsg) throws IOException {

        InetAddress ad = InetAddress.getByName(snmpMsg.getHostIp());
        /**
         * 如果2秒达不到就是超时
         * */
        boolean state = ad.isReachable(2000);
        return state;
    }

    /**
     * windows下获得所用内存
     * */
    public long getUsedMemWindows(SnmpMsg snmpMsg){
        List<String> test = getInstanceSnmpDaoWalk().walkByTable(code.EVERY_PROCESS_MEM_WALK.getCode(),snmpMsg);
        long res = 0;
        for(String x : test){
            res += Long.valueOf(x);
        }
        return res;
    }

}


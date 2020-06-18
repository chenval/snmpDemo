package operatingsystem;

import entry.Data;
import entry.SnmpMsg;
import service.SnmpService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenval
 * @date 2020/6/18 16:38
 */
public class WindowsSnmp {
    public static Data getMessageWindows(SnmpMsg snmpMsg, SnmpService snmpService){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        int cpu = snmpService.getCpuUtilization(snmpMsg);
        long totalMem = snmpService.getTotalMemory(snmpMsg);
        long usedMem = snmpService.getUsedMemWindows(snmpMsg);
        long mem = 100 - (100 * usedMem) / totalMem;
        System.out.println(new Date() + "   cpu利用率："+ cpu);
        System.out.println("内存使用率："+ mem);
        Data data = new Data();
        Date date = new Date();
        /**
         * 为javaBean赋值
         * */
        data.setDate(dateFormat.format(date));

        data.setCpuCount(snmpService.getCpuCount(snmpMsg).size());

        /**
         * 将cpu详细信息转换为String
         * */
        StringBuffer cpuDe= new StringBuffer();
        int count = 1;
        List<String> cpus = snmpService.getCpuCount(snmpMsg);
        Iterator iterator = cpus.iterator();
        while(iterator.hasNext()){
            cpuDe.append(iterator.next());
            if(count != cpus.size()){
                cpuDe.append(",");
            }
            count++;
        }
        data.setCpuDetail(cpuDe.toString());

        data.setCpuUtilization(cpu);

        data.setAvailMem(totalMem - usedMem);

        data.setTotalMem(totalMem);

        data.setMemUtilization((int)mem);

        data.setOperatingSystem("windows");

        return data;
    }
}

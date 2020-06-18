package operatingsystem;

import entry.Data;
import entry.SnmpMsg;
import service.SnmpService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author chenval
 * @date 2020/6/18 16:39
 */
public class LinuxSnmp {
    public static Data getMessageLinux(SnmpMsg snmpMsg, SnmpService snmpService){
        SimpleDateFormat  dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        int cpu = snmpService.getCpuUtilization(snmpMsg);
        long totalMem = snmpService.getTotalMemory(snmpMsg);
        long availMem = snmpService.getAvailMemoryLinux(snmpMsg);
        int mem = snmpService.getMemoryUtilization(availMem,totalMem);

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
        for(String x :cpus){
            cpuDe.append(x);
            if(count < cpus.size()){
                cpuDe.append(",");
            }
            count++;
        }
        data.setCpuDetail(cpuDe.toString());

        data.setCpuUtilization(cpu);

        data.setAvailMem(availMem);

        data.setTotalMem(totalMem);

        data.setMemUtilization(mem);

        data.setOperatingSystem("linux");
        return data;
    }
}

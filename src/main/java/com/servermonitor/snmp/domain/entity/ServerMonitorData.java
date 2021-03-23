package com.servermonitor.snmp.domain.entity;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenval
 * @date 2020/6/12 10:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerMonitorData {
    /**
     * cpu数量
     * */
    private int cpuCount;
    /**
     * 每个cpu的使用情况
     * */
    private String cpuDetail;
    /**
     * 总的cpu使用率，cpuAllUsed / cpuCount
     * */
    private int cpuUtilization;
    /**
     * 可用的内存
     * */
    private long availMem;
    /**
     * 总内存
     * */

    private long totalMem;
    /**
     * 内存使用率
     * */
    private int memUtilization;
    /**
     * 时间
     * */
    private Date date;

    /**
     * 操作系统的类型
     * */
    private String operatingSystem;
    /**
     * ip
     */
    private String ip;
    /**
     * name
     */
    private String serverName;


}

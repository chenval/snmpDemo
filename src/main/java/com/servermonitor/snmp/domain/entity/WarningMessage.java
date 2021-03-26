package com.servermonitor.snmp.domain.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenval 2021-03-26
 */
@Data
@Builder
public class WarningMessage {
    /**
     * ip地址
     */
    String ip;
    /**
     * 告警信息
     */
    String message;
    /**
     * 发生时间时间
     * */
    private String createDate;
    /**
     * 最后一次预警时间
     */
    private String lastUpdateTime;
    /**
     * 预警次数
     */
    private int warningCount;
    /**
     * 是否处理
     */
    private boolean isHandle;
}

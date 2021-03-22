package com.servermonitor.snmp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenval
 * @date 2020/6/10 16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerMessage {
    /**
     * snmp 服务组名
     */
    private String communityName;
    /**
     * ip地址
     */
    private String ip;
    /**
     * snmp端口
     */
    private Integer port;
    /**
     * 受监控服务器名称
     * */
    private String name;
    /**
     * 受监控服务器操作系统
     * */
    private String operationSystem;
    /**
     * 状态(1为可用，0为不可用，默认为1)，用于是否对这个服务器进行监控
     * */
    private Integer statusId;
    


}

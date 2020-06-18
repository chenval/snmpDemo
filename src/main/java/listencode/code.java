package listencode;


/**
 * @author chenval
 * @date 2020/6/11 15:31
 * @Description snmp通信协议的指令枚举类型
 */
public enum code {
    /**
     * 空闲CPU linux能用
     * */
    IDLE_CPU_GET(".1.3.6.1.4.1.2021.11.11.0"),



    /**
     *获得cpu所有核的使用情况 window linux都能用
     * */
    ALL_CPU_DETAIL_WALK(".1.3.6.1.2.1.25.3.3.1.2"),



    /**
     * 获得空闲内存的大小 linux可用
     * */
    FREE_MEM_GET(".1.3.6.1.4.1.2021.4.11.0"),



    /**
     * 得到缓存区大小 linux可用
     * */
    CACHE_MEM_GET(".1.3.6.1.4.1.2021.4.15.0"),



    /**
     * 得到缓冲区大小 linux可用
     * */
    BUFFER_MEM_GET(".1.3.6.1.4.1.2021.4.14.0"),



    /**
     * 获得所有内存大小.1.3.6.1.2.1.25.2.2.0能在window 和 linux使用 .1.3.6.1.4.1.2021.4.5.0只能在linux上
     * */
    ALL_MEM_GET(".1.3.6.1.2.1.25.2.2.0"),



    /**
     * 得到各个进程所分配的内存大小 标识是id
     * */
    EVERY_PROCESS_MEM_WALK(".1.3.6.1.2.1.25.5.1.1.2"),



    /**
     * 得到每个进程所占用的cpu时间 标识是id
     * */
    EVERY_PROCESS_CPU_WALK(".1.3.6.1.2.1.25.5.1.1.1"),



    /**
     * 获得每个运行进程的id
     * */
    EVERY_PROCESS_ID_WALK(".1.3.6.1.2.1.25.4.2.1.1"),


    /**
     * 获得所有运行进程的名字
     * */
    EVERY_PROCESS_NAME_WALK(".1.3.6.1.2.1.25.4.2.1.2");


    String code;
    code(String code){
         this.code = code;
    }
    public String getCode(){
         return code;
    }
}

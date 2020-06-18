package entry;


/**
 * @author chenval
 * @date 2020/6/12 10:08
 */

public class Data {
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
    private String date;

    /**
     * 操作系统的类型
     * */
    private String operatingSystem;

    public Data(int cpuCount, int cpuUtilization, long availMem, long totalMem, int mem, String cpuDetail,String operatingSystem) {
        this.cpuCount = cpuCount;
        this.operatingSystem = operatingSystem;
        this.cpuDetail = cpuDetail;
        this.cpuUtilization = cpuUtilization;
        this.availMem = availMem;
        this.totalMem = totalMem;
        memUtilization = mem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Data() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCpuDetail() {
        return cpuDetail;
    }

    public void setCpuDetail(String cpuDetail) {
        this.cpuDetail = cpuDetail;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(int cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public long getAvailMem() {
        return availMem;
    }

    public void setAvailMem(long availMem) {
        this.availMem = availMem;
    }

    public long getTotalMem() {
        return totalMem;
    }

    public void setTotalMem(long totalMem) {
        this.totalMem = totalMem;
    }

    public int getMemUtilization() {
        return memUtilization;
    }

    public void setMemUtilization(int mem) {
        memUtilization = mem;
    }

//    @Override
//    public String toString() {
//        StringBuffer a = new StringBuffer();
//        for(String x : cpuDetail){
//            a.append(x);
//            a.append(",");
//        }
//        return this.getDate()+
//                "cpu:数量" + cpuCount +
//                "cpu使用详细:" + a.toString() +
//                "cpu使用率:" + cpuUtilization +
//                "可用内存:" + availMem +
//                "总内存" + totalMem +
//                "内存使用率:" + memUtilization;
//
//    }
}

package dao;

import entry.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/17 10:05
 */
public interface DataDao {
    /**
    * 根据id获得信息
    * @param id 数据的主键
    * @return 返回找到的数据
    */
    Data queryDataById(@Param("id")String id);

    /**
     *  查找所有信息
     * @param
     * @return Data的集合
     * */
    List<Data> queryAllData();

    /**
     * fetch data by rule id
     *
     * @param data rule id
     * @return 无返回值
     */
    void insertData(@Param("data")Data data);


}

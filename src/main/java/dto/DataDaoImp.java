package dto;

import dao.DataDao;
import entry.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author chenval
 * @date 2020/6/17 10:08
 */
public class DataDaoImp implements DataDao {
    public SqlSession sqlSession;

    public DataDaoImp(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }



    @Override
    public Data queryDataById(String id) {
        return this.sqlSession.selectOne("Data.queryDataById",id);
    }

    @Override
    public List<Data> queryAllData() {
        return this.sqlSession.selectList("Data.queryDataAll");
    }

    @Override
    public void insertData( Data data) {
        this.sqlSession.insert("Data.insertData",data);
    }

}

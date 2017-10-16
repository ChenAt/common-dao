package top.chenat.commondao.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import top.chenat.commondao.bean.Example;
import top.chenat.commondao.paging.PageInfo;
import top.chenat.commondao.paging.QueryInterceptor;

import java.util.List;

/**
 * Created by ChenAt 2017/10/14.
 * desc:
 */
public class DaoSupport {


    private SelectSupport selectSupport;

    private UpdateSupport updateSupport;

    private DeleteSupport deleteSupport;

    private InsertSupport insertSupport;

    @Autowired
    public DaoSupport(NamedParameterJdbcTemplate jdbcTemplate) {
        selectSupport = new SelectSupport(jdbcTemplate);
        updateSupport = new UpdateSupport(jdbcTemplate);
        deleteSupport = new DeleteSupport(jdbcTemplate);
        insertSupport = new InsertSupport(jdbcTemplate);

        try {
            QueryInterceptor.intercept(jdbcTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertSelective(Object record){
        insertSupport.insertSelective(record);
    }

    public <T> T selectByPrimaryKey(Object primaryKey, Class<T> entityClass) {
        return selectSupport.selectByPrimaryKey(primaryKey, entityClass);
    }


    public <T> int deleteByPrimaryKey(Object primaryKey, Class<T> entityClass) {
        return deleteSupport.deleteByPrimaryKey(primaryKey, entityClass);
    }

    public <T> int updateByPrimaryKeySelective(T record) {
        return updateSupport.updateByPrimaryKeySelective(record);
    }


    public <T> List<T> select(T record) {
        return selectSupport.select(record);
    }

    public <T> T selectOne(T record) {
        return selectSupport.selectOne(record);
    }

    public <T> PageInfo<T> selectByPage(int pageNum, int pageSize, T record) {
        return selectSupport.selectByPage(pageNum, pageSize, record);
    }

    public  List<?> selectByExample(Example example) {
        return selectSupport.selectByExample(example);
    }
}

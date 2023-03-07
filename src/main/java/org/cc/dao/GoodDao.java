package org.cc.dao;

import org.cc.pojo.Good;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author cc
 * @Date 2022/10/15 10:53
 * @PackageName:org.cc.dao
 * @ClassName: GoodDao
 * @Description: TODO
 * @Version 1.0
 */
public interface GoodDao {
    void addGood(Good good);
    List<Good> queryByCri(Map map) throws SQLException;
    List<Good> queryByPage() throws SQLException;
    int queryByCount() throws SQLException;
    void delete(int id) throws Exception;
    Good findById(int id) throws SQLException;
    void update(Good good) throws Exception;
    List<String> queryTypes() throws SQLException;
    List<Good> queryByType(String type) throws SQLException;
}

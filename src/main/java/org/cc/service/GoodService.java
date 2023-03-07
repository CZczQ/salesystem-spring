package org.cc.service;

import org.cc.pojo.Good;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author cc
 * @Date 2022/10/15 10:49
 * @PackageName:org.cc.service
 * @ClassName: GoodService
 * @Description: TODO
 * @Version 1.0
 */
public interface GoodService {
    void addGood(Good good);
    List<Good> queryByCri(String id, String goodname, String goodtype) throws SQLException;
    List<Good> queryByPage(int pageSize, int pageNow) throws SQLException;
    int queryByCount() throws SQLException;
    void delete(int id) throws Exception;
    Good findById(int id) throws SQLException;
    void update(Good good) throws Exception;
    List<String> queryTypes() throws SQLException;
    List<Good> queryByType(String type) throws SQLException;
}

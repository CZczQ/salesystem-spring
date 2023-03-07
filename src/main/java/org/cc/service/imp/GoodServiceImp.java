package org.cc.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.cc.dao.GoodDao;

import org.cc.pojo.Good;
import org.cc.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cc
 * @Date 2022/10/15 10:53
 * @PackageName:org.cc.service.imp
 * @ClassName: GoodServiceImp
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class GoodServiceImp implements GoodService {
    @Autowired
    private GoodDao goodDao;
    //@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void addGood(Good good){
        goodDao.addGood(good);
        if (good.getGoodname().trim().equals("")||good.getGoodtype().trim().equals("")){
            throw new RuntimeException("商品名或分类为空！");
        }

    }


    public List<Good> queryByCri(String id, String goodname, String goodtype) throws SQLException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("id",id);
        map.put("goodname",goodname);
        map.put("goodtype",goodtype);
        return goodDao.queryByCri(map);
    }


    public List<Good> queryByPage(int pageSize, int pageNow) throws SQLException {
        PageHelper.startPage(pageNow, pageSize);
        return goodDao.queryByPage();
    }


    public int queryByCount() throws SQLException {
        return goodDao.queryByCount();
    }


    public void delete(int id) throws Exception {
        goodDao.delete(id);
    }


    public Good findById(int id) throws SQLException {
        return goodDao.findById(id);
    }


    public void update(Good good) throws Exception {
        goodDao.update(good);
    }


    public List<String> queryTypes() throws SQLException {
        return goodDao.queryTypes();
    }


    public List<Good> queryByType(String type) throws SQLException {
        return goodDao.queryByType(type);
    }

}

package org.cc.pojo;

import java.util.*;

/**
 * @Author cc
 * @Date 2022/10/22 14:54
 * @PackageName:org.cc.pojo
 * @ClassName: Car
 * @Description: TODO
 * @Version 1.0
 */
public class Car {
    private Map<Integer,Good> map;
    public void addGood(Good good){
        if (map == null){
            map = new HashMap();
        }
        good.setAmount(1);//默认买一个

        map.put(good.getId(),good);

    }

    public Map<Integer, Good> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Good> map) {
        this.map = map;
    }
    public List<Good> toList(){
        List<Good> goods = new ArrayList();
        Set<Map.Entry<Integer, Good>> entries = map.entrySet();
        for (Map.Entry<Integer, Good> e : entries) {
            goods.add((Good) e.getValue());
        }
        return goods;
    }
    //删除商品
    public void delGood(int id){
        map.remove(id);
    }
    //清空
    public void clearGood(){
        map.clear();
    }
    //更新
    public void modGood(String[] ids,String[] amounts){
        for (int i = 0; i < ids.length; i++) {
            Integer id = Integer.parseInt(ids[i]);
            Integer amount = Integer.parseInt(amounts[i]);
            Good good = map.get(id);
            good.setAmount(amount);

        }
    }
    public Double calcBalance(){
        Double balance = 0.0;
        Set<Map.Entry<Integer, Good>> entries = map.entrySet();
        for (Map.Entry<Integer, Good> e : entries) {
            Good good = e.getValue();
            balance+=good.getPrice()*good.getAmount();


        }
        return balance;
    }
    public Integer calcAmount(){
        Integer amounts = 0;
        Set<Map.Entry<Integer, Good>> entries = map.entrySet();
        for (Map.Entry<Integer, Good> e : entries) {
            Good good = e.getValue();
            amounts+=good.getAmount();


        }
        return amounts;
    }

}

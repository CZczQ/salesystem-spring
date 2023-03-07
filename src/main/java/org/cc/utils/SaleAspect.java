package org.cc.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.cc.pojo.Good;
import org.cc.pojo.Vip;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author cc
 * @Date 2022/11/24 8:32
 * @PackageName:org.cc.utils
 * @ClassName: SaleAspect
 * @Description: TODO
 * @Version 1.0
 */
@Aspect
@Component
public class SaleAspect {
    private String username;
    private String vipname;

    @After("execution(* org.cc.service.UserService.*(..))")
    public void UserAfter(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        if (name.equals("login")){
            List<Object> list = Arrays.asList(joinPoint.getArgs());
            username = (String)list.get(0);
            System.out.println("管理员用户——“"+username+"”登录了！");
        } else if (name.equals("register")) {
            Vip vip = (Vip)joinPoint.getArgs()[0];
            System.out.println("新用户——“"+vip.getUsername()+"”注册了");
        }else if(name.equals("QiantaiLogin")){
            List<Object> list = Arrays.asList(joinPoint.getArgs());
            vipname = (String)list.get(0);
            System.out.println("vip用户——“"+vipname+"”登录了！");
        }
    }
    @After("execution(* org.cc.service.GoodService.*(..))")
    public void GoodAfter(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        if (name.equals("addGood")){
            Good good = (Good)joinPoint.getArgs()[0];
            System.out.println("管理员“"+username+"”添加了产品——"+good.getGoodname());
        }else if (name.equals("queryByCri")){
            String pro = "";
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                if (joinPoint.getArgs()[i]!=null){
                    pro += ((String) joinPoint.getArgs()[i]+" ");
                }
            }
            System.out.println("管理员“"+username+"”通过"+pro+"查找了商品！");

        }else if (name.equals("queryByPage")){
            System.out.println("管理员“"+username+"”查找了所有商品！");
        }else if (name.equals("delete")){
            System.out.println("管理员“"+username+"”删除了商品标号为"+joinPoint.getArgs()[0]+"的商品！");
        }else if (name.equals("update")){
            Good good = (Good)joinPoint.getArgs()[0];
            System.out.println("管理员“"+vipname+"”修改了商品标号为"+good.getId()+"的商品！");
        }else if (name.equals("queryTypes")){
            System.out.println("vip用户“"+vipname+"”访问了前台首页！");
        }
    }
}

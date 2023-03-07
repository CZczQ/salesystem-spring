package org.cc.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.cc.pojo.Car;
import org.cc.pojo.Good;
import org.cc.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author cc
 * @Date 2022/12/18 10:40
 * @PackageName:org.cc.web
 * @ClassName: QiantaiController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class QiantaiController {
    @Autowired
    GoodService goodService;

    @RequestMapping("/qiantai/svl/open")
    public String open(String type, HttpServletRequest request, HttpServletResponse response, Model model){

        if (type!=null){
            try {
                type =new String(type.getBytes("iso-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try {
            List<String> types = goodService.queryTypes();
            model.addAttribute("types",types);
            //取types中第一个分类作为首要的查询条件
            if (type == null && types.size() > 0){
                type = types.get(0);
            }
            //计算用户当前的商品数量和总金额
            HttpSession session = request.getSession();
            Car car = (Car)session.getAttribute("car");
            if (car != null){
                Integer amounts = car.calcAmount();
                Double balances = car.calcBalance();
                model.addAttribute("amounts",amounts);
                model.addAttribute("balances",balances);
            }


            List<Good> goods = goodService.queryByType(type);
            model.addAttribute("goods",goods);
            return "forward:/qiantai/index.jsp";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/qiantai/svl/img")
    public void outImg(String pic,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = pic;
        String path = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(path + "/" + filename);
        ServletOutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = fis.read(b))!=-1){
            os.write(b,0,len);
        }
        fis.close();
        os.flush();
        os.close();
    }

    @RequestMapping("/qiantai/svl/addGood")
    public String addCar(String id,HttpServletRequest request, HttpServletResponse response,Model model){
        Integer id1 = Integer.parseInt(id);
        try {
            Good good = goodService.findById(id1);
            HttpSession session = request.getSession();
            if (session.getAttribute("car") == null){
                Car car = new Car();
                session.setAttribute("car",car);
            }
            Car car = (Car) session.getAttribute("car");
            car.addGood(good);
            return openFlow(request,response,car,model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/qiantai/svl/delGood")
    public String delGood(String id,HttpServletRequest request, HttpServletResponse response,Model model){
        Integer id1 = Integer.parseInt(id);
        HttpSession session = request.getSession();
        Car car = (Car) session.getAttribute("car");
        car.delGood(id1);
        return openFlow(request,response,car,model);
    }

    @RequestMapping("/qiantai/svl/clearGood")
    public String clearGood(HttpServletRequest request, HttpServletResponse response,Model model){
        HttpSession session = request.getSession();
        Car car = (Car) session.getAttribute("car");
        car.clearGood();
        return "forward:/qiantai/flow.jsp";
    }

    @RequestMapping("/qiantai/svl/modGood")
    public String modCar(String[] id,String[] amount,HttpServletRequest request, HttpServletResponse response,Model model){
        HttpSession session = request.getSession();
        Car car = (Car) session.getAttribute("car");
        car.modGood(id,amount);
        return openFlow(request,response,car,model);
    }

    @RequestMapping("/qiantai/svl/openFlow")
    public String openFlow(HttpServletRequest request, HttpServletResponse response,Car car,Model model) {
        if (car.getMap() == null){
            car = (Car) request.getSession().getAttribute("car");
        }
        List<Good> goods = null;
        try {
            goods = car.toList();
        }catch (NullPointerException e){
            return "redirect:/qiantai/flow.jsp";
        }

        Double balance = car.calcBalance();
        model.addAttribute("balance", balance);
        model.addAttribute("goods", goods);
        return "forward:/qiantai/flow.jsp";
    }
}

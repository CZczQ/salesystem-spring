package org.cc.web;

import org.cc.pojo.Good;
import org.cc.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @Author cc
 * @Date 2022/12/17 14:56
 * @PackageName:org.cc.web
 * @ClassName: GoodController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class GoodController {
    @Autowired
    private GoodService goodService;

    @RequestMapping("/houtai/goodsvl/querybycri")
    public String queryByCri(String pid, String pname, String ptype, Model model){
        try {
            List<Good> goods = goodService.queryByCri(pid, pname, ptype);
            model.addAttribute("goods",goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "forward:/houtai/productListUI.jsp";
    }

    @RequestMapping("/houtai/goodsvl/queryall")
    public String queryByPage(String pageNow,Model model){
        int pageSize = 14;
        int pageNow1 = Integer.parseInt(pageNow);

        if (pageNow1 < 1)
            pageNow1 = 1;
        //得到当页的数据
        List<Good> goods = null;
        try {
            goods = goodService.queryByPage(pageSize, pageNow1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //得到总数
        int totalcount = 0;
        try {
            totalcount = goodService.queryByCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //得到总页数
        int pagecount = 0;
        if (totalcount%pageSize == 0){
            pagecount = totalcount/pageSize;
        }else {
            pagecount = totalcount/pageSize +1;
        }

        model.addAttribute("goods",goods);
        model.addAttribute("totalcount",totalcount);
        model.addAttribute("pagecount",pagecount);
        model.addAttribute("pagenow",pageNow1);
        return "forward:/houtai/productListUI.jsp";

    }

    @RequestMapping("/houtai/goodsvl/delete")
    public String delete(String id,String pageNow,Model model){
        Integer id1 = Integer.parseInt(id);
        Integer pageNow1 = Integer.parseInt(pageNow);


        try {
            goodService.delete(id1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/houtai/goodsvl/queryall?pageNow=" + pageNow;
    }

    @RequestMapping("/houtai/goodsvl/mod")
    public String mod(String id,String pageNow,Model model){
        Integer id1 = Integer.parseInt(id);
        Integer pageNow1 = Integer.parseInt(pageNow);
        try {
            Good good = goodService.findById(id1);
            model.addAttribute("good",good);
            model.addAttribute("pageNow",pageNow1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward:/houtai/updateproduct.jsp";
    }

    @RequestMapping("/houtai/goodsvl/update")
    public String update(String pid,String pageNow,String pname,String ptype,String pprice,Model model){
        Integer id = Integer.parseInt(pid);
        Integer pageNow1 = Integer.parseInt(pageNow);
        String goodname = pname;
        String goodtype = ptype;
        Double price = Double.parseDouble(pprice);
        Good good = new Good(id, goodname, goodtype, price,null);
        try {
            goodService.update(good);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/houtai/goodsvl/queryall?pageNow="+pageNow1;
    }

    @RequestMapping("/houtai/fileupload/add")
    public String add(String pname, String ptype, String pprice, MultipartFile pimg, Model model, HttpServletRequest request, HttpServletResponse response){
        String path = request.getServletContext().getRealPath("/WEB-INF/upload");
        System.out.println(path);

        Good good = new Good();
        good.setGoodname(pname);
        good.setGoodtype(ptype);
        good.setPrice(Double.parseDouble(pprice));
        String filename = pimg.getOriginalFilename();
        int start = filename.lastIndexOf(".");
        filename = filename.substring(start+1);
        filename = UUID.randomUUID() + "." + filename;
        File file1 = new File(path+"/"+filename);
        try {
            pimg.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        good.setPic(filename);
        goodService.addGood(good);
        return "redirect:/houtai/addnewproduct.jsp";
    }


}

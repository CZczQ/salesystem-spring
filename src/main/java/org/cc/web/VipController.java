package org.cc.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.cc.pojo.User;
import org.cc.pojo.Vip;
import org.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Random;

/**
 * @Author cc
 * @Date 2022/12/18 10:12
 * @PackageName:org.cc.web
 * @ClassName: VipController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class VipController {
    @Autowired
    private UserService userService;
    private static String codeChars = "1234567890abcdefghijklmnopqrstuvwxyz";

    private static Color getRandomColor(int minColor, int maxColor){
        Random random = new Random();
        if(minColor>255)
            minColor = 255;
        if(maxColor>255)
            maxColor = 255;
        int red = minColor  + random.nextInt(maxColor - minColor);
        int green = minColor + random.nextInt(maxColor - minColor);
        int blue = minColor + random.nextInt(maxColor - minColor);
        return new Color(red,green,blue);
    }

    @RequestMapping("/qiantai/validate")
    public void validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //�����֤�뼯�ϵĳ���
        int charsLength = codeChars.length();
        //����3���ǹرտͻ���������Ļ�����
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cach-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //����ͼ����֤��ĳ���
        int width = 90, height = 20;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();//���������ֵ�graphics����
        Random random = new Random();
        g.setColor(getRandomColor(180, 250));//������ɫ
        g.fillRect(0, 0, width, height);
        //���ó�ʼ����
        g.setFont(new Font("Times New Roman",Font.ITALIC,height));
        g.setColor(getRandomColor(120, 180));//������ɫ
        StringBuilder validationCode = new StringBuilder();
        //��֤����������
        String[] fontNames = {"Times New Roman","Book antiqua","Arial"};
        //�������3-5����֤��
        for (int i = 0; i < 3+random.nextInt(3); i++) {
            //������õ�ǰ��֤����ַ�������
            g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
            //�����õ�ǰ��֤����ַ�
            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);
            //������õ�ǰ��֤���ַ�����ɫ
            g.setColor(getRandomColor(10, 100));
            //��ͼ���������֤���ַ���x y�������
            g.drawString(String.valueOf(codeChar), 16*i+random.nextInt(7), height-random.nextInt(6));
        }
        //���session����
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5*60);
        //����֤�뱣����session�����У�keyΪvalidation_code
        session.setAttribute("validation_code", validationCode.toString());
        g.dispose();
        OutputStream os = response.getOutputStream();
        ImageIO.write(image,"JPEG",os);//��JPEG��ʽ��ͻ��˷���ͼ����֤��
    }

    @PostMapping("/qiantai/vipsvl/login")
    public String vipLogin(String username, String password, String validate,HttpServletRequest request, Model model){

        String validation_code_session = (String)request.getSession().getAttribute("validation_code");

        if (!validate.equals(validation_code_session)){
            model.addAttribute("errinfo","验证码错误");
            return "forward:/qiantai/login.jsp";
        }
        try {
            Vip vip = userService.QiantaiLogin(username, password);
            if (vip == null){
                model.addAttribute("errinfo","用户名或密码错误");
                return "forward:/qiantai/login.jsp";
            }else {//登陆成功,请求servlet，让他为页面准备初始数据（商品分类列表，第一个类别的商品列表）
                return "redirect:/qiantai/svl/open";    //sdsdsd
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/qiantai/vipsvl/register")
    public String register(String username, String email, String password, String confirm_password, String qqcode, String agreement, Model model){

        String errinfo = null;
        //后台条件检查
        if (agreement==null || !agreement.equals("1")){
            errinfo = "请同意协议内容！";
        } else if (!password.equals(confirm_password)) {
            errinfo = "两次密码不相同！";
        }
        if (errinfo != null){
            model.addAttribute("errinfo",errinfo);
            return "forward:/qiantai/register.jsp";
        }
        try {
            userService.register(new Vip(null,username,email,password,qqcode));
            return "redirect:/qiantai/login.jsp";
        }catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/qiantai/register.jsp";
        }
    }



}

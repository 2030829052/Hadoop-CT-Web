package org.fengyue.hadoopctweb.controller;

import com.alibaba.fastjson.JSONObject;
import org.fengyue.hadoopctweb.dao.HDFSDao;
import org.fengyue.hadoopctweb.entity.User;
import org.fengyue.hadoopctweb.service.UserService;
import org.fengyue.hadoopctweb.entity.utils.EmailUtis;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String getIndex() {
        return "/login.jsp";
    }

    @RequestMapping("/sign")
    public String getSign() {
        return "/register.jsp";
    }

    @RequestMapping("/pass1")
    public String getPass1() {
        return "/email.jsp";
    }

    /**
     * 处理注册
     *
     * @param user
     * @param response
     * @throws Exception
     */
    @RequestMapping("/register")
    public void getRegister(User user, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        //1.获取用户输入的账号  密码  邮箱
        String email = user.getEmail();
        boolean mail = EmailUtis.isMail(email);
        if (user.getUsername().length() >= 6 && user.getPassword().length() >= 6) {
            if (mail) {
                //2.把获取到的数据写入到数据库中
                userService.addUser(user);
                writer.println("<script>alert('注册成功！！！');window.location='/';</script>");
            } else {
                writer.println("<script>alert('该邮箱格式不对！！！');window.location='/sign';</script>");
            }
        } else {
            writer.println("<script>alert('账户名或密码不足6位！！！');window.location='/sign';</script>");
            //验证码验证
        }
    }

    /**
     * ajax的认证
     * 返回的是数据
     */
    @RequestMapping("/ajaxAuth")
    @ResponseBody
    public JSONObject getAjaxAuth(@RequestParam String uname) {
        JSONObject jsonObject = new JSONObject();
        //查看该用户 再数据库中是否存在
        User user = userService.findByUsername(uname);
        //把用户是否存在的信息  封装到json
        if (user != null) {
            jsonObject.put("userExits", true);
        } else {
            jsonObject.put("userExits", false);
        }
        return jsonObject;
    }

    /**
     * 处理 密码找回的
     */
    @RequestMapping("/emailBack")
    public void getEmailBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //先把乱码解决
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //获取写出对象  （把内容写到html页面）
        PrintWriter writer = response.getWriter();

        //获取用户输入的邮箱
        String email = request.getParameter("email");

        //通过邮箱，找到跟邮箱绑定的用户名与密码
        User user = userService.findByEmail(email);

        if (user != null) {
            //完成邮箱找回密码的代码
            EmailUtis.sendEmail(email, user.getUsername(), user.getPassword());

            //发送成功的  响应
            writer.println("<script>alert('密码已发送至您的邮箱，请查收！！！');window.location='/';</script>");
        } else {
            writer.println("<script>alert('您的邮箱没有注册过，谢谢！！！');window.location='/pass1';</script>");
        }
    }

    /**
     * 处理登录的问题
     */
    @RequestMapping("/login")
    public String getLogin(User user, Model model) {
//        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("utf-8");
//        //获取写出对象  （把内容写到html页面）
//        PrintWriter writer = response.getWriter();
        //判断账号跟密码是否正确
        FileStatus[] fileStatuses = HDFSDao.listStatus();

        //设置一个作用域
        model.addAttribute("filelist", fileStatuses);
//        session.setAttribute("user", user);
        //自己去完成登录判断的逻辑   密码错误怎么办   账号密码正确代码怎么走？？？？
//
        return "index.jsp";
        //
    }

}

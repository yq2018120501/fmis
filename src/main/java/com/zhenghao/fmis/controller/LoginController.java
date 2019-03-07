package com.zhenghao.fmis.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/index")
    public String index() {
        System.out.println("进入index方法。。。。");
        return "login";
    }

    @RequestMapping("/login")
    public String index(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req) {
        System.out.println("进入登陆方法。。。。。");
        System.out.println("name:" + username + ";pwd:" + password);
        String message = null;
        // 1.创建subject对象
        Subject currentUser = SecurityUtils.getSubject();

        // 2.判断当前用户是否登录
        if (currentUser.isAuthenticated() == false) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                req.setAttribute("username", username);
            }catch (UnknownAccountException e) {
                message = "账号不存在！";
                req.setAttribute("msg", message);
                return "login";
            } catch (IncorrectCredentialsException e) {
                message = "密码错误！";
                req.setAttribute("msg", message);
                return "login";
            } catch (LockedAccountException e) {
                message = "用户已锁死！";
                req.setAttribute("msg", message);
                return "login";
            } catch(AuthenticationException e) {
                message = "认证异常！";
                req.setAttribute("msg", message);
                return "login";
            }
        }
        return "success";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "login";
    }


    public static void main(String[] args) {
        Md5Hash md5 = new Md5Hash("1111","admin", 3);
        System.out.println(md5);
    }


}

package com.example.shirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * TODO
 *
 * @author admin
 * @version 1.0
 * @date 2021/2/23 17:36
 */
@RestController
public class UserController {

    Subject subject;
    //主页
    @GetMapping("/mainPage")
    public String mainPage(HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getCookies());
        if (httpServletRequest.getCookies() != null){
            for (javax.servlet.http.Cookie cookie : httpServletRequest.getCookies()){
                if (cookie.getName().equals("remeberMe")){
                    if (cookie.getValue().equals("ok")){
                        //.........
                        return "主页面";
                    }
                }
            }
        }
        return "/login";
    }

    //登录认证
    @GetMapping("/login")
    public String login(String username, String password,HttpServletResponse response,HttpServletRequest request){


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            if (subject.isAuthenticated()){
                token.setRememberMe(true);
                subject.getSession().setAttribute("token",token);
                SimpleCookie simpleCookie = new SimpleCookie();
                simpleCookie.setName("remeberMe");
                simpleCookie.setValue("ok");
                Cookie cookie = simpleCookie;
                cookie.setMaxAge(60);
                cookie.saveTo(request, response);
            }
        }catch(UnknownAccountException e){
            e.printStackTrace();
        }catch(IncorrectCredentialsException e){
            e.printStackTrace();
        }
        return "登录结果"+subject.isAuthenticated();
    }
    //认证结果
    @GetMapping("/isAuthencated")
    public String isAuthencated(){
        subject = SecurityUtils.getSubject();


        return "认证结果"+subject.isAuthenticated()+"  session结果"+subject.getSession().getAttribute("token");
    }

    //登出
    @GetMapping("/loginout")
    public String loginout(){
        subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
            return "登出结果"+subject.isAuthenticated();
        }
        return "未登录";
    }
}

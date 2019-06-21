package com.changan.securitydemo.web;

import com.changan.securitydemo.common.Msg;
import com.changan.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }


    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('oauth2-resource') and  hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "hello admin";
    }

    @RequestMapping("/hello")
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('open') and  hasAuthority('ROLE_ADMIN')")
    public String hello() {
        return "hello";
    }


}

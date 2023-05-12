package com.skyworth.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Description LoginController
 * @Author sky
 * @Date 2023/5/12 10:48
 * @Version 1.0
 **/
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String toLoginPage() {
        return "login";
    }
}

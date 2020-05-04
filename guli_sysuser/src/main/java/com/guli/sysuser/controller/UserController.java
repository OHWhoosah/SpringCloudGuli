package com.guli.sysuser.controller;

import com.guli.common.result.R;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    /**
     * 1、请求登陆的login
     */
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    /**
     * 2、根据token获取用户信息
     */
    @GetMapping("info")
    public R info(String token){
        /**
         * {"code":20000,
         * "data":
         *  {
         *      "roles":["admin"],
         *      "name":"admin",
         *      "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
         */
        return R.ok()
                    .data("roles","[\"admin\"]")
                    .data("name","admin")
                    .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}

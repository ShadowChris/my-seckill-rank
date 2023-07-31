package com.shadow006.myseckillrank.controller;

import com.shadow006.myseckillrank.service.IUserService;
import com.shadow006.myseckillrank.vo.LoginVo;
import com.shadow006.myseckillrank.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caizimo
 * @date 2023/4/16 12:26
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Resource
    IUserService userService;

    @RequestMapping("/dologin")
    public RespBean doLogin(@RequestBody LoginVo loginVo) {
        return userService.doLogin(loginVo);
    }
}

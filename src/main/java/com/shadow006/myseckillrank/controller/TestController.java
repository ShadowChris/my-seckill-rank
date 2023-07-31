package com.shadow006.myseckillrank.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author caizimo
 * @date 2023/4/6 22:13
 */
@Controller
@Log4j
public class TestController {
    @GetMapping(value = "/test")
    public void testRedis() {

    }
}

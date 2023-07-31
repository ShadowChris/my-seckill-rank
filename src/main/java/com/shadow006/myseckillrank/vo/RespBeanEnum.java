package com.shadow006.myseckillrank.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回公共对象枚举
 * @author caizimo
 * @date 2023/4/16 16:42
 */
@ToString
@AllArgsConstructor
@Getter
public enum RespBeanEnum {
    //通用枚举
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),
    //登录模块
    LOGIN_ERROR(500210, "用户名或者密码不正确");


    private final Integer code;
    private final String message;


}

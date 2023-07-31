package com.shadow006.myseckillrank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow006.myseckillrank.pojo.User;
import com.shadow006.myseckillrank.vo.LoginVo;
import com.shadow006.myseckillrank.vo.RespBean;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shadow006
 * @since 2023-04-16
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo);
}

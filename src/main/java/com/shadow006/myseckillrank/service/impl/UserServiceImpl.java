package com.shadow006.myseckillrank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow006.myseckillrank.mapper.UserMapper;
import com.shadow006.myseckillrank.pojo.User;
import com.shadow006.myseckillrank.service.IUserService;
import com.shadow006.myseckillrank.utils.MD5Util;
import com.shadow006.myseckillrank.vo.LoginVo;
import com.shadow006.myseckillrank.vo.RespBean;
import com.shadow006.myseckillrank.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shadow006
 * @since 2023-04-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    UserMapper userMapper;

    @Override
    public RespBean doLogin(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验非空：StringUtils.isEmpty()就是判断输入的字符串是否为null或者长度为0。减少代码量，使代码清晰易懂。
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // 校验手机号格式（待开发）
        // TO DO

        // 查询用户
        User user = userMapper.selectById(mobile);
        // 查询用户是否存在
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // 验证密码是否正确
        if (!MD5Util.inputPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        return RespBean.success(user);
    }
}

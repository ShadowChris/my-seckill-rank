package com.shadow006.myseckillrank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow006.myseckillrank.mapper.OrderMapper;
import com.shadow006.myseckillrank.pojo.Order;
import com.shadow006.myseckillrank.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shadow006
 * @since 2023-04-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}

package com.zhr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.mapper.OrderDetailMapper;
import com.zhr.mapper.OrderInfoMapper;
import com.zhr.model.order.OrderDetail;
import com.zhr.model.order.OrderInfo;
import com.zhr.service.OrderDetailService;
import com.zhr.service.OrderInfoService;
import org.springframework.stereotype.Service;

/**
 * @author jjking
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}

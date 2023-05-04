package com.zhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.model.order.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}

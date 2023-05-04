package com.zhr.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.model.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 订单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-04-28
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}

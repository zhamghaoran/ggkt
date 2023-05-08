package com.zhr.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.model.activity.CouponInfo;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.ManagedBean;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author jjking
 * @since 2023-05-08
 */
@Mapper
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

}

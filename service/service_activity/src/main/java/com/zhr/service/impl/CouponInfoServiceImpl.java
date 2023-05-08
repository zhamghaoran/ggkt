package com.zhr.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhr.clientUser.UserInfoFeignClient;
import com.zhr.mapper.CouponInfoMapper;
import com.zhr.model.activity.CouponInfo;
import com.zhr.model.activity.CouponUse;
import com.zhr.model.user.UserInfo;
import com.zhr.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.service.CouponUseService;
import com.zhr.vo.activity.CouponUseQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author jjking
 * @since 2023-05-08
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponUseService couponUseService;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParma, CouponUseQueryVo couponUseQueryVo) {
        // 获取条件值
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();
        // 条件封装
        LambdaQueryWrapper<CouponUse> couponUseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(couponId)) {
            couponUseLambdaQueryWrapper.eq(CouponUse::getCouponId, couponId);
        }
        if (!StringUtils.isEmpty(couponStatus)) {
            couponUseLambdaQueryWrapper.eq(CouponUse::getCouponStatus, couponStatus);
        }
        if (!StringUtils.isEmpty(getTimeBegin)) {
            couponUseLambdaQueryWrapper.ge(CouponUse::getGetTime, getTimeBegin);
        }
        if (!StringUtils.isEmpty(getTimeEnd)) {
            couponUseLambdaQueryWrapper.le(CouponUse::getGetTime, getTimeEnd);
        }
        // 调用方法分页查询
        IPage<CouponUse> pageModel = couponUseService.page(pageParma, couponUseLambdaQueryWrapper);
        List<CouponUse> couponUseList = pageModel.getRecords();
        // 遍历
        couponUseList.forEach(this::getUserInfoById);
        return pageModel;
    }

    private void getUserInfoById(CouponUse couponUse) {
        // 先获取用户id
        Long userId = couponUse.getUserId();
        if (ObjectUtils.isNotEmpty(userId)) {
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            if (userInfo != null) {
                couponUse.getParam().put("nickName", userInfo.getNickName());
                couponUse.getParam().put("phone", userInfo.getPhone());
            }
        }
    }
}

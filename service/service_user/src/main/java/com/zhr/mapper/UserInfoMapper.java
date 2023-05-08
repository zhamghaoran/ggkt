package com.zhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}

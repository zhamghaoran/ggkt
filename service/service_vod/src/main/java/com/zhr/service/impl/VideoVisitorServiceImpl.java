package com.zhr.service.impl;

import com.zhr.mapper.VideoVisitorMapper;
import com.zhr.model.vod.VideoVisitor;
import com.zhr.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.vo.vod.VideoVisitorCountVo;
import com.zhr.vo.vod.VideoVisitorVo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author jjking
 * @since 2023-04-21
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {
    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        List<VideoVisitorCountVo> VideoVisitorList = baseMapper.findCount(courseId, startDate, endDate);
        // 创建map集合
        // 创建两个list 一个代表所有日期，一个代表日期对应的数量
        // 封装数据
        List<String> datelist = VideoVisitorList.stream().map(VideoVisitorCountVo::getJoinTime).collect(Collectors.toList());
        List<Integer> countList = VideoVisitorList.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());
        //放到map集合
        map.put("xData", datelist);
        map.put("yData", countList);
        return map;
    }
}

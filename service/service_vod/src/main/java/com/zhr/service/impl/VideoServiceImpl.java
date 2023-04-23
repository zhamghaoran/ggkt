package com.zhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhr.mapper.VideoMapper;
import com.zhr.model.vod.Video;
import com.zhr.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodService vodService;

    @Override
    public void removeVideoByCourseId(Long id) {
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId, id);
        baseMapper.delete(videoLambdaQueryWrapper);
        // 根据课程id查询所有小节
        List<Video> videos = baseMapper.selectList(videoLambdaQueryWrapper);
        // 遍历所有小节的集合，获取每个小节的视频id
        videos.forEach(i -> {
            String videoSourceId = i.getVideoSourceId();
            if (!StringUtils.isBlank(videoSourceId)) {
                // 删除视频
                vodService.removeVideo(videoSourceId);
            }
        });
        baseMapper.delete(videoLambdaQueryWrapper);

    }

    @Override
    public void removeVideoById(Long id) {
        // id查询小节信息
        Video video = baseMapper.selectById(id);
        // 获取video里面的视频id
        String videoSourceId = video.getVideoSourceId();
        // 判断视频id是否为空
        if (!StringUtils.isBlank(videoSourceId)) {
            // 视频id不为空，删除视频
            vodService.removeVideo(videoSourceId);
        }
        // 删除小节
        baseMapper.deleteById(id);
    }
}

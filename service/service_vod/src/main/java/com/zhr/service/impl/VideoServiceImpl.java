package com.zhr.service.impl;

import com.zhr.mapper.VideoMapper;
import com.zhr.model.vod.Video;
import com.zhr.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

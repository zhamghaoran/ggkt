package com.zhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhr.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(Long id);

    // 删除小节的时候删除视频
    void removeVideoById(Long id);
}

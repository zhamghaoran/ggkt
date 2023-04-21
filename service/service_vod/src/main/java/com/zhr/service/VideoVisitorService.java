package com.zhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhr.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author jjking
 * @since 2023-04-21
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}

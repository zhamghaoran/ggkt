package com.zhr.service;

import com.zhr.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhr.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getTreeList(Long courseId);

    void removeChapterByCourseId(Long id);
}

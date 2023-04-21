package com.zhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.entity.Chapter;
import com.zhr.mapper.ChapterMapper;
import com.zhr.model.vod.Video;
import com.zhr.service.ChapterService;
import com.zhr.service.VideoService;
import com.zhr.vo.vod.ChapterVo;
import com.zhr.vo.vod.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getTreeList(Long courseId) {
        // 定义最终数据的list集合
        List<ChapterVo> finalChapterList = new ArrayList<>();
        // 1.根据课程id获取到课程里面的所有章节
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, courseId);
        List<Chapter> chapters = baseMapper.selectList(chapterLambdaQueryWrapper);
        // 根据课程id获取所有的小节部分
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId, courseId);
        List<Video> videos = videoService.list(videoLambdaQueryWrapper);
        // 封装章节
        chapters.forEach(i -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(i, chapterVo);
            // 封装章节里面的小节
            // 创建一个list集合来封装所有用户小节
            List<VideoVo> videoVoList = new ArrayList<>();
            videos.forEach(j -> {
                // 判断小节是哪个章节下面的小节
                if (chapterVo.getId().equals(j.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(j, videoVo);
                    videoVoList.add(videoVo);
                }
            });
            // 把章节里面的所有小节集合放到每个章节里面去
            chapterVo.setChildren(videoVoList);
            finalChapterList.add(chapterVo);
        });
        return finalChapterList;
    }

    // 根据课程id删除章节

    @Override
    public void removeChapterByCourseId(Long id) {
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, id);
        baseMapper.delete(chapterLambdaQueryWrapper);
    }
}

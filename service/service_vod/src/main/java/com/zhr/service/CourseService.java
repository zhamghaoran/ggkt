package com.zhr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhr.model.vod.Course;
import com.zhr.vo.vod.CourseFormVo;
import com.zhr.vo.vod.CourseProgressVo;
import com.zhr.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    CourseFormVo getCourseInfoById(Long id);

    void updateCourseId(CourseFormVo courseFormVo);

    CourseProgressVo getCoursePublishVo(Long id);

    void publishCourse(Long id);
}

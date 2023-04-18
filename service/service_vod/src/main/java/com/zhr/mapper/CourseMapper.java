package com.zhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.model.vod.Course;
import com.zhr.vo.vod.CourseProgressVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    CourseProgressVo selectCoursePublishVoById(Long id);
}

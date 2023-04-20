package com.zhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.entity.CourseProgressVo;
import com.zhr.model.vod.Course;
import com.zhr.vo.vod.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select(" select c.id,\n" +
            "               c.title,\n" +
            "               c.cover,\n" +
            "               c.lesson_num AS lessonNum,\n" +
            "               c.price,\n" +
            "               t.name       AS teacherName,\n" +
            "               s1.title     AS subjectParentTitle,\n" +
            "               s2.title     AS subjectTitle\n" +
            "        from glkt_vod.course c\n" +
            "                 left outer join glkt_vod.teacher t on c.teacher_id = t.id\n" +
            "                 left outer join glkt_vod.subject s1 on c.subject_id = s1.id\n" +
            "                 left outer join `glkt_vod`.subject s2 on c.subject_id = s2.id\n" +
            "        where c.id = #{id}")
    CoursePublishVo selectCoursePublishVoById(Long id);
}

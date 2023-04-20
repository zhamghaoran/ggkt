package com.zhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.entity.CourseProgressVo;
import com.zhr.mapper.CourseMapper;
import com.zhr.model.vod.Course;
import com.zhr.model.vod.CourseDescription;
import com.zhr.model.vod.Subject;
import com.zhr.model.vod.Teacher;
import com.zhr.service.CourseDescriptionService;
import com.zhr.service.CourseService;
import com.zhr.service.SubjectService;
import com.zhr.service.TeacherService;
import com.zhr.vo.vod.CourseFormVo;
import com.zhr.vo.vod.CoursePublishVo;
import com.zhr.vo.vod.CourseQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    // 点播课程列表
    @Override
    public Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        // 获取条件
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();  // 二层分类
        Long subjectParentId = courseQueryVo.getSubjectParentId(); // 一层分类
        Long teacherId = courseQueryVo.getTeacherId();

        // 判断条件是否为空
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isBlank(title)) {
            courseLambdaQueryWrapper.like(Course::getTitle, title);
        }
        if (!ObjectUtils.isEmpty(subjectId)) {
            courseLambdaQueryWrapper.eq(Course::getSubjectId, subjectId);
        }
        if (!ObjectUtils.isEmpty(subjectParentId)) {
            courseLambdaQueryWrapper.eq(Course::getSubjectParentId, subjectParentId);
        }
        if (!ObjectUtils.isEmpty(teacherId)) {
            courseLambdaQueryWrapper.eq(Course::getTeacherId, teacherId);
        }
        // 调用方法实现条件查询
        Page<Course> coursePage = baseMapper.selectPage(pageParam, courseLambdaQueryWrapper);
        long totalCount = coursePage.getTotal();  // 总记录数
        long totalPage = coursePage.getPages();  // 总页数
        long currentPage = coursePage.getCurrent(); // 当前页
        long size = coursePage.getSize(); // 每页记录数
        // 每页数据集合
        List<Course> records = coursePage.getRecords();
        // 遍历封装讲师和分类名称
        records.forEach(this::getTeacherOrSubjectName);
        // 封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;
    }

    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        // 添加课程的基本信息 course 表
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.insert(course);
        // 添加课程的描述信息 course_description

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        courseDescriptionService.save(courseDescription);
        return course.getId();

    }


    // 根据id查询课程信息
    @Override
    public CourseFormVo getCourseInfoById(Long id) {
        // 课程的基本信息
        Course course = baseMapper.selectById(id);
        if (course == null) {
            return null;
        }
        // 课程的描述信息
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        // 数据的封装
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course, courseFormVo);
        if (courseDescription != null) {
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }


    // 修改课程信息
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        // 修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.updateById(course);
        // 修改课程的描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    // 课程最终发布
    @Override
    public void publishCourse(Long id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(1); // 课程已经发布
        course.setPublishTime(new Date());
        baseMapper.updateById(course);
    }

    // 获取讲师和分类名称
    private void getTeacherOrSubjectName(Course i) {
        // 查询讲师名称
        Teacher teacher = teacherService.getById(i.getTeacherId());
        if (teacher != null) {
            i.getParam().put("teacherName", teacher.getName());
        }
        // 查询分类名称
        Subject subject = subjectService.getById(i.getSubjectParentId());
        if (subject != null) {
            i.getParam().put("subjectParentTitle", subject.getTitle());
        }
        Subject subject1 = subjectService.getById(i.getSubjectId());
        if (subject1 != null) {
            i.getParam().put("subjectTitle", subject1.getTitle());
        }
    }
}

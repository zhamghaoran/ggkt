package com.zhr.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhr.entity.CourseProgressVo;
import com.zhr.model.vod.Course;
import com.zhr.result.Result;
import com.zhr.service.CourseService;
import com.zhr.vo.vod.CourseFormVo;
import com.zhr.vo.vod.CoursePublishVo;
import com.zhr.vo.vod.CourseQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.ProgressBarUI;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/admin/vod/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 添加课程的基本信息
    @ApiOperation("添加课程基本信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long id = courseService.saveCourseInfo(courseFormVo);
        return Result.success(id);
    }

    @ApiOperation("点播课程")
    @GetMapping("{page}/{limit}")
    public Result courseList(@PathVariable Long page,
                             @PathVariable Long limit,
                             CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String, Object> map = courseService.findPageCourse(pageParam, courseQueryVo);
        return Result.success(map);
    }

    // 根据id获取课程信息
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CourseFormVo courseFormVo = courseService.getCourseInfoById(id);
        return Result.success(courseFormVo);
    }

    // 修改课程信息
    @PostMapping("update")
    public Result update(@RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseId(courseFormVo);
        return Result.success(courseFormVo.getId());
    }

    // 根据课程id 查询发布的信息
    @ApiOperation("id 查询发布的信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@PathVariable Long id) {
        CoursePublishVo courseProgressVo = courseService.getCoursePublishVo(id);
        return Result.success(courseProgressVo);
    }

    // 课程最终发布
    @ApiOperation("课程最终发布")
    @PutMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        courseService.publishCourse(id);
        return Result.success(null);
    }
}


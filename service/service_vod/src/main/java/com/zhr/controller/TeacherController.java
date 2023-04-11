package com.zhr.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhr.exception.GGKTException;
import com.zhr.model.vod.Teacher;
import com.zhr.result.Result;
import com.zhr.service.TeacherService;
import com.zhr.vo.vod.TeacherQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhr
 * @since 2023-03-20
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/admin/vod/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * @return 返回所有讲师列表
     */
    // 查询所有讲师
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        // 调用service接口
        return Result.success(teacherService.list());
    }

    /**
     * 删除讲师
     *
     * @param id 讲师id
     */
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/remove/{id}")
    public Result removeTeacher(@PathVariable("id") Integer id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.success(teacherService.removeById(id));
        } else {
            return Result.fail();
        }
    }

    // 条件查询分页
    @ApiOperation(value = "获取分页列表")
    @PostMapping("/findQueryPage/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "查询对象", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherVo", value = "查询对象")
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        // 创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(page, limit);
        if (teacherQueryVo == null) {
            Page<Teacher> pageModel = teacherService.page(pageParam, null);
            return Result.success(pageModel);
        }
        // 获取条件值
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 封装条件
        if (!ObjectUtils.isEmpty(name)) {
            teacherLambdaQueryWrapper.like(Teacher::getName, name);
        }
        if (!ObjectUtils.isEmpty(level)) {
            teacherLambdaQueryWrapper.eq(Teacher::getLevel, level);
        }
        if (!ObjectUtils.isEmpty(joinDateBegin)) {
            teacherLambdaQueryWrapper.ge(Teacher::getJoinDate, joinDateBegin);
        }
        if (!ObjectUtils.isEmpty(joinDateEnd)) {
            teacherLambdaQueryWrapper.le(Teacher::getJoinDate, joinDateEnd);
        }
        // 调用方法得到分页查询的结果
        Page<Teacher> pageModel = teacherService.page(pageParam, teacherLambdaQueryWrapper);
        return Result.success(pageModel);
    }

    // 添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.save(teacher);
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    // 修改根据id查询
    @ApiOperation(value = "根据id查询")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher byId = teacherService.getById(id);
        return Result.success(byId);
    }

    // 修改-最终实现
    @ApiOperation(value = "修改最终实现")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "批量删除讲师")
    @DeleteMapping("/removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }
}








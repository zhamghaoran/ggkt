package com.zhr.controller;

import com.zhr.model.vod.Subject;
import com.zhr.result.Result;
import com.zhr.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 20179
 */
@RestController
@RequestMapping("admin/vod/subject")
//@CrossOrigin
@Api(tags = "课程分类")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    // 课程分类列表
    // 懒加载，每次查询一层数据

    @ApiOperation("课程分类列表")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable("id") Long id) {
        List<Subject> list =  subjectService.selectSubjectList(id);
        return Result.success(list);
    }

    /**
     * 课程分类导出
     */
    @ApiOperation(value = "课程分类导出")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse httpServletResponse) {
        subjectService.exportData(httpServletResponse);
    }

    @ApiOperation("课程分类导入")
    @PostMapping("/importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.success();
    }
}

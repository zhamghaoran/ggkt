package com.zhr.controller;


import com.zhr.entity.Chapter;
import com.zhr.result.Result;
import com.zhr.service.ChapterService;
import com.zhr.vo.vod.ChapterVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/admin/vod/chapter")
//@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    // 大纲列表（章节和小节列表）
    @ApiOperation("大纲列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId) {
        List<ChapterVo> list = chapterService.getTreeList(courseId);
        return Result.success(list);
    }

    // 添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.success(null);
    }

    // 修改-根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter byId = chapterService.getById(id);
        return Result.success(byId);
    }

    // 修改-最终实现
    @PostMapping("update")
    public Result update(@RequestBody  Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.success(null);
    }

    // 删除章节
    @DeleteMapping("remove/{id}")
    public Result update(@PathVariable Long id) {
        chapterService.removeById(id);
        return Result.success();
    }

}


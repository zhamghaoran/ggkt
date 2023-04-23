package com.zhr.controller;


import com.zhr.model.vod.Video;
import com.zhr.result.Result;
import com.zhr.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author jjking
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/admin/vod/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.success(video);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody Video video) {
        videoService.save(video);
        return Result.success(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody Video video) {
        videoService.updateById(video);
        return Result.success(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        videoService.removeVideoById(id);
        return Result.success(null);
    }
}


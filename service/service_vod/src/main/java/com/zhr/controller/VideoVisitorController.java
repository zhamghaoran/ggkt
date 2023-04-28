package com.zhr.controller;


import com.zhr.result.Result;
import com.zhr.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author jjking
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/admin/vod/videoVisitor")
//@CrossOrigin
@Api("video-visitor-controller")
public class VideoVisitorController {
    @Autowired
    private VideoVisitorService videoVisitorService;

    // 课程统计接口
    @ApiOperation("显示统计数据")
    @GetMapping("/findCount/{courseId}/{startDate}/{endDate}")
    public Result findCount(@PathVariable Long courseId,
                            @PathVariable String startDate,
                            @PathVariable String endDate) {
        Map<String,Object> map = videoVisitorService.findCount(courseId,startDate,endDate);
        return Result.success(map);
    }

}


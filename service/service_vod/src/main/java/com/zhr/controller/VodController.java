package com.zhr.controller;

import com.zhr.result.Result;
import com.zhr.service.VodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 20179
 */
@RestController
@Api(tags = "腾讯云点播")
//@CrossOrigin
@RequestMapping("/admin/vod")
public class VodController {
    @Autowired
    private VodService vodService;

    // 返回客户端上传视频的签名
    @GetMapping("sign")
    public Result sign() {
        String sign = vodService.sign();
        return Result.success(sign);
    }

    // 上传视频的接口
    @PostMapping("/upload")
    public Result upload() {
        String fileId = vodService.uploadVideo();
        return Result.success(fileId);
    }
    // 删除视频
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId) {
        vodService.removeVideo(fileId);
        return Result.success();
    }

}

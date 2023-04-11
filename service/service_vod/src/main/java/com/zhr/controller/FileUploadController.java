package com.zhr.controller;

import com.mysql.cj.protocol.ResultsetRow;
import com.zhr.result.Result;
import com.zhr.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 20179
 */
@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result uploadFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file")
            MultipartFile file) throws IOException {
        String url = fileService.upload(file);
        return Result.success(url).message("上传文件成功");
    }

}

package com.zhr.exception;

import com.zhr.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 20179
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        return Result.fail().message("执行了全局异常处理");
    }


    @ExceptionHandler(GGKTException.class)
    @ResponseBody
    public Result error(GGKTException e) {
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }
}

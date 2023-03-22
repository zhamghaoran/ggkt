package com.zhr.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 20179
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    // 状态码 200 成功
    private Integer code;
    // 返回的状态信息
    private String message;
    // 返回的数据
    private T data;


    // 成功的方法
    public static <T> Result<T> success(T data) {
        return Result.success(data, "成功");
    }
    public static <T> Result<T> success(T data,String message) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static <T> Result<T> success() {
        return Result.success(null);
    }

    // 失败的方法
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail() {
        return fail(null);
    }
    public Result<T> message(String message) {
        this.message = message;
        return this;
    }
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}

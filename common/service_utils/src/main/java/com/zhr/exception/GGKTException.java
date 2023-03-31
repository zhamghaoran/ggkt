package com.zhr.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GGKTException extends RuntimeException{
    private Integer code;
    private String msg;
}

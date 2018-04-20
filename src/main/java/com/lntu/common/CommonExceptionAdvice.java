package com.lntu.common;

import com.lntu.exception.MallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * Created by Administrator on 2018/3/27.
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    private String sysErrorInfo = "System Error";

    @ExceptionHandler(MallException.class)
    public JsonData mallExceptionHandler(MallException mallException){
        if(mallException.getParams() != null){
            log.error("state={},message={},params={}",mallException.getState(),mallException.getMessage(),mallException.getParams());
        }else {
            log.error("state={},message={}",mallException.getState(),mallException.getMessage());
        }
        return new JsonData(mallException.getState(),mallException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JsonData exceptionHandler(Exception exception){
        log.error("message={}",exception.getMessage());
        return new JsonData(500,sysErrorInfo);
    }

}

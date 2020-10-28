package com.example.seckill.controller;

import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 统用错误拦截
 */
@Controller
public class BaseController {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    //定义exceptionhandle解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)//什么类型的异常会被拦截
    @ResponseStatus(HttpStatus.OK)//处理状态
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> responseData=new HashMap<String,Object>();
        if(ex instanceof BusinessException){
            //如果类型相同
            //强转异常类型
            BusinessException businessException =(BusinessException)ex;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        logger.info("异常为：{}",ex);
        return CommonReturnType.create(responseData,"fail");

     /*   //强转异常类型
        BusinessException businessException =(BusinessException)ex;
        //定义通用返回结果
        CommonReturnType returnType=new CommonReturnType();
        returnType.setStatus("fail");
        Map<String,Object> responseData=new HashMap<String,Object>();
        responseData.put("errCode",businessException.getErrCode());
        responseData.put("errMsg",businessException.getErrMsg());
        returnType.setData(responseData);
        return returnType;*/
    }

}

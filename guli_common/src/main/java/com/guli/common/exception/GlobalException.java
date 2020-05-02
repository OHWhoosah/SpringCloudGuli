package com.guli.common.exception;

import com.guli.common.result.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    /**
     * 异常处理：捕获 Exception 异常的
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
//        这个错误信息：日志；
//        运维/开发人员 邮件通知 短信通知
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R errorArithmetic(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("除数不能为0");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R errorArithmetic(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}

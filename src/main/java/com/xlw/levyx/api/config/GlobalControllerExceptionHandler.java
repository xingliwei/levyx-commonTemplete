package com.xlw.levyx.api.config;

import com.xlw.levyx.api.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.MessageFormat;

/**
 * Created by levyx on 2017/7/14.
 *  全局异常处理类
 *  接口不直接返回异常信息，而是捕获处理后返回给前端
 *  解释几个注解：
 *      @ExceptionHandler:  异常处理器，此注解的作用是当出现其定义的异常时进行处理的方法，
 *                          其可以使用springmvc提供的数据绑定，比如注入HttpServletRequest等，还可以接受一个当前抛出的Throwable对象。
 *      @ControllerAdvice:  是spring3.2提供的新注解，主要是用来Controller的一些公共的需求的低侵入性增强提供辅助，作用于@RequestMapping标注的方法上。
 *                          和此注解配合使用的其他注解有：
                             @ExceptionHandler   自定义的错误处理器
                             @ModelAttribute     全局的对所有的controller的Model添加属性
                             @InitBinder  对表单数据绑定
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final String PATTERN = "parameter: {0},value:{1},requireType:{2}";
    private static final String PATTERN2 = "parameter: {0},requireType:{1}";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Response exception(MethodArgumentTypeMismatchException e){
        Response response = new Response();
        response.setMessage(MessageFormat.format(PATTERN,e.getName(),e.getValue(),e.getRequiredType()));
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Response exception(MissingServletRequestParameterException e){
        Response response = new Response();
        response.setMessage(MessageFormat.format(PATTERN2,e.getParameterName(),e.getParameterType()));
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return response;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Response exception(HttpRequestMethodNotSupportedException e) {

        Response r = new Response();
        r.setMessage(e.getMessage());
        r.setStatus(HttpStatus.BAD_REQUEST.value());

        return r;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exception(Exception e) {

        Response r = new Response();
        r.setMessage("服务端错误!");
        r.setStatus(HttpStatus.BAD_REQUEST.value());

        e.printStackTrace();

        return r;
    }
}

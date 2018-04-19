package com.company.project.web.exception;

import com.company.project.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * spring mvc 全局异常处理
 *
 * @author flatychen
 */
@ControllerAdvice
@RestController
public class DefalutExceptionHandler {

    private static Logger log = LoggerFactory
            .getLogger(DefalutExceptionHandler.class);

    /**
     * 添加404
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<? extends Object> notFound(
            Exception ex) {
        return Result.fail("404 not found");
    }


    /**
     * 统一异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<? extends Object> handleException(
            Exception ex) {
        log.error("Exception", ex);
        return Result.serverError();
    }

    /**
     * 统一异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {BindException.class, MissingServletRequestParameterException.class})
    public Result<? extends Object> requestNotValid(
            Exception ex) {
        log.error("BindException:{}", ex.getMessage());
        return Result.fail(ex.getMessage());
    }


}

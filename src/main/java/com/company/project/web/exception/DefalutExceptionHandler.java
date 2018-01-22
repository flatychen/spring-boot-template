package com.company.project.web.exception;

import com.company.project.base.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<? extends Object> handleException(
            Exception ex) {
        log.error("Exception", ex);
        return Response.serverError();
    }


}

package com.company.project.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class Result<T> {

    private int code;
    private String msg = "";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public Result() {
        this(ResultCodeEnum.SUCCESS, null);
    }


    public Result(T data) {
        this(ResultCodeEnum.SUCCESS, data);
    }


    public Result(ResultCodeEnum responseCodeEnum) {
        this(responseCodeEnum, null);
    }

    public Result(ResultCodeEnum responseCodeEnum, T data) {
        this.code = responseCodeEnum.code();
        this.msg = responseCodeEnum.msg();
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this(code, msg, null);
    }


    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }


    public static <T> Result<T> fail() {
        return fail(null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultCodeEnum.FAIL.code(), msg,null);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(ResultCodeEnum.FAIL.code(), msg, data);
    }


    public static <T> Result<T> serverError() {
        return serverError(null);
    }

    public static <T> Result<T> serverError(String msg) {
        return new Result<>(ResultCodeEnum.SERVER_ERROR.code(), msg, null);
    }

    public static <T> Result<T> serverError(String msg, T data) {
        return new Result<>(ResultCodeEnum.SERVER_ERROR.code(), msg, data);
    }

}

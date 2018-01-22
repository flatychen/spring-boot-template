package com.company.project.base;

import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class Response<T> {

    private int code;
    private String msg = "";
    private T data;


    public Response() {
        this(ResponseCodeEnum.SUCCESS, null);
    }


    public Response(T data) {
        this(ResponseCodeEnum.SUCCESS, data);
    }


    public Response(ResponseCodeEnum responseCodeEnum) {
        this(responseCodeEnum, null);
    }

    public Response(ResponseCodeEnum responseCodeEnum, T data) {
        this.code = responseCodeEnum.code();
        this.msg = responseCodeEnum.msg();
        this.data = data;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(int code, String msg) {
        this(code, msg, null);
    }


    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }


    public static <T> Response<T> fail() {
        return fail(null);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(ResponseCodeEnum.FAIL, data);
    }

    public static <T> Response<T> fail(String msg, T data) {
        return new Response<>(ResponseCodeEnum.FAIL.code(), msg, data);
    }


    public static <T> Response<T> serverError() {
        return serverError(null);
    }

    public static <T> Response<T> serverError(T data) {
        return new Response<>(ResponseCodeEnum.SERVER_ERROR, data);
    }

    public static <T> Response<T> serverError(String msg, T data) {
        return new Response<>(ResponseCodeEnum.SERVER_ERROR.code(), msg, data);
    }


}

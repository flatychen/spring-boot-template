package com.company.project.base;


/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCodeEnum {
    SUCCESS(200, "success"),//成功
    // 4xx为客户端错误
    FAIL(400, "request not valid"),//失败,客户端不合法
    // 5xx为服务端错误
    SERVER_ERROR(500, "server error");//服务器内部错误


    // 1000+ 为业务逻辑错误


    private final int code;
    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    public <T> Result<T> response() {
        return new Result<>(this);
    }



}

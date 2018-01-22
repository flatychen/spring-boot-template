package com.company.project.web.aop;

/**
 * Created by flaty on 2017-01-10.
 */
public class IpLimitException extends Exception {
    public IpLimitException(String message) {
        super(message);
    }
}

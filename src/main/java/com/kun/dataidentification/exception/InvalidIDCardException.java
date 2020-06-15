package com.kun.dataidentification.exception;


/**
 * 身份证无效异常
 * @author niulibing
 * @since 4.0.1
 */
public class InvalidIDCardException extends RuntimeException {
    public InvalidIDCardException(String message) {
        super(message);
    }
}

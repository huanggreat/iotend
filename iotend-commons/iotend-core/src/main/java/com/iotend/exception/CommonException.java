package com.iotend.exception;

/**
 * @description: 非业务异常
 * @author: huang
 * @create: 2020-11-26 16:30
 */
public class CommonException extends BaseCheckedException {


    public CommonException(int code, String message) {
        super(code, message);
    }

    public CommonException(int code, String format, Object... args) {
        super(code, String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    public CommonException wrap(int code, String format, Object... args) {
        return new CommonException(code, format, args);
    }

    @Override
    public String toString() {
        return "BizException [message=" + message + ", code=" + code + "]";
    }
}

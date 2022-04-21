package com.plixiaofei.community.enumeration;

/**
 * Created on 2022/4/9 by plixiaofei
 */
public enum ResultCode {
    SUCCESS(200, "请求成功"),
    FAIL(400, "请求失败"),
    INTERNAL_ERROR(-1, "系统错误，请联系管理员"),
    USERNAME_EXISTS(10000, "用户名已存在"),
    CREATE_USER_FAIL(10001, "创建用户失败，请重试"),
    PASSWORD_WRONG(10002, "用户密码错误"),
    USER_NOT_EXISTS(10003, "用户不存在"),
    CHANGE_PASSWORD_FAIL(10004, "用户修改密码失败"),
    CHANGE_PERSONAL_INFO_FAIL(10005, "用户修改个人资料失败"),
    PUB_QUESTION_FAIL(20001, "发布问题失败"),
    QUESTION_UNSEARCHABLE(20002, "相关问题无法被检索到");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}

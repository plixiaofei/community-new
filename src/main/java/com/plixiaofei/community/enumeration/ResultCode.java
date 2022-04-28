package com.plixiaofei.community.enumeration;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2022/4/9 by plixiaofei
 */
public enum ResultCode {
    SUCCESS(200, "请求成功"),
    FAIL(400, "请求失败"),
    INTERNAL_ERROR(-1, "系统错误，请联系管理员"),
    USERNAME_EXISTS(10001, "用户名已存在"),
    CREATE_USER_FAIL(10002, "创建用户失败，请重试"),
    PASSWORD_WRONG(10003, "用户密码错误"),
    USER_NOT_EXISTS(10004, "用户不存在"),
    CHANGE_PASSWORD_FAIL(10005, "用户修改密码失败"),
    CHANGE_PERSONAL_INFO_FAIL(10006, "用户修改个人资料失败"),
    PUB_QUESTION_FAIL(20001, "发布问题失败"),
    QUESTION_UNSEARCHABLE(20002, "相关问题无法被检索到"),
    INSERT_COMMENT_FAIL(30001, "用户评论失败"),
    COMMENT_QUESTION_NOT_EXISTS(30002, "您评论的问题不存在或已被删除"),
    TO_USER_NOT_EXISTS(30003, "您@的用户不存在"), PAGE_OUT_OF_RANGE(40001, "页面超出");

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

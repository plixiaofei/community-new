package com.plixiaofei.community.domain.vo;

/**
 * Created on 2022/4/10 by plixiaofei
 */
public class UserVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String icon;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}

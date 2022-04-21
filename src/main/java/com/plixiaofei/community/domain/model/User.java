package com.plixiaofei.community.domain.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 是否被锁
     */
    private Integer isLocked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date loginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 用户头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 用户头像
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 逻辑删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 逻辑删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 是否被锁
     */
    public Integer getIsLocked() {
        return isLocked;
    }

    /**
     * 是否被锁
     */
    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 上次登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 上次登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getIsLocked() == null ? other.getIsLocked() == null : this.getIsLocked().equals(other.getIsLocked()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getIsLocked() == null) ? 0 : getIsLocked().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", icon=").append(icon);
        sb.append(", nickName=").append(nickName);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", isLocked=").append(isLocked);
        sb.append(", createTime=").append(createTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.plixiaofei.community.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 帖子表格
 * @TableName question
 */
@TableName(value ="question")
public class Question implements Serializable {
    /**
     * 帖子自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 帖子id
     */
    private String title;

    /**
     * 发帖用户名
     */
    private String username;

    /**
     * 问题简单描述
     */
    private String shortDescription;

    /**
     * 详细问题描述
     */
    private String fullDescription;

    /**
     * 发帖时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删帖时间
     */
    private Date deleteTime;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 帖子自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 帖子自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 发帖用户id
     */
    public String getUsername() {
        return username;
    }

    /**
     * 发帖用户id
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 问题简单描述
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * 问题简单描述
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * 详细问题描述
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * 详细问题描述
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * 发帖时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 发帖时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 删帖时间
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * 删帖时间
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getShortDescription() == null ? other.getShortDescription() == null : this.getShortDescription().equals(other.getShortDescription()))
            && (this.getFullDescription() == null ? other.getFullDescription() == null : this.getFullDescription().equals(other.getFullDescription()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteTime() == null ? other.getDeleteTime() == null : this.getDeleteTime().equals(other.getDeleteTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getShortDescription() == null) ? 0 : getShortDescription().hashCode());
        result = prime * result + ((getFullDescription() == null) ? 0 : getFullDescription().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteTime() == null) ? 0 : getDeleteTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
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
        sb.append(", title=").append(title);
        sb.append(", shortDescription=").append(shortDescription);
        sb.append(", fullDescription=").append(fullDescription);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleteTime=").append(deleteTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
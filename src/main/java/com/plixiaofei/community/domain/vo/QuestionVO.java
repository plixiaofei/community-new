package com.plixiaofei.community.domain.vo;

import java.util.Date;

/**
 * Created on 2022/4/19 by plixiaofei
 */
public class QuestionVO {
    private Long id;
    /**
     * 帖子名称
     */
    private String title;
    /**
     * 发帖用户用户名
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username=" + username +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}


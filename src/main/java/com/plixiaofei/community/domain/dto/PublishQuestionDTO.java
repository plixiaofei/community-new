package com.plixiaofei.community.domain.dto;

/**
 * Created on 2022/4/19 by plixiaofei
 */
public class PublishQuestionDTO {

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

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    @Override
    public String toString() {
        return "PublishQuestionDTO{" +
                "username='" + username + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

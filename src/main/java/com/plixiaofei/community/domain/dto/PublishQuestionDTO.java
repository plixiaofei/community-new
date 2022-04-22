package com.plixiaofei.community.domain.dto;

/**
 * Created on 2022/4/19 by plixiaofei
 */
public class PublishQuestionDTO {

    private String title;
    /**
     * 问题简单描述
     */
    private String shortDescription;

    /**
     * 详细问题描述
     */
    private String fullDescription;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

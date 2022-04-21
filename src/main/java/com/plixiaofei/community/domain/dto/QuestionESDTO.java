package com.plixiaofei.community.domain.dto;

/**
 * Created on 2022/4/20 by plixiaofei
 */
public class QuestionESDTO {
    private Long id;
    private String title;
    private String shortDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "QuestionESDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}

package com.plixiaofei.community.domain.dto;

/**
 * Created on 2022/4/22 by plixiaofei
 */
public class CommentDTO {
    /**
     * 问题Id
     */
    private Long questionId;

    /**
     * 评论目标用户
     */
    private String toUsername;

    /**
     * 评论内容
     */
    private String content;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "questionId=" + questionId +
                ", toUsername='" + toUsername + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

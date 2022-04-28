package com.plixiaofei.community.domain.vo;

import java.util.Date;

/**
 * Created on 2022/4/24 by plixiaofei
 */
public class CommentVO {
    private Long id;

    private Long questionId;
    /**
     * 评论用户
     */
    private String fromUsername;

    /**
     * 评论目标用户
     */
    private String toUsername;

    /**
     * 评论内容
     */
    private String content;

    private Date commentTime;

    private UserVO userVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
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

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", fromUsername='" + fromUsername + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                ", userVO=" + userVO +
                '}';
    }
}

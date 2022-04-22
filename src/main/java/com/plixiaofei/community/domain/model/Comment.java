package com.plixiaofei.community.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 用户评论表
 * @TableName comment
 */
@TableName(value ="comment")
public class Comment implements Serializable {
    /**
     * 评论Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 问题Id
     */
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 评论Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 评论Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 问题Id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 问题Id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 评论用户
     */
    public String getFromUsername() {
        return fromUsername;
    }

    /**
     * 评论用户
     */
    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    /**
     * 评论目标用户
     */
    public String getToUsername() {
        return toUsername;
    }

    /**
     * 评论目标用户
     */
    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    /**
     * 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     */
    public void setContent(String content) {
        this.content = content;
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
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestionId() == null ? other.getQuestionId() == null : this.getQuestionId().equals(other.getQuestionId()))
            && (this.getFromUsername() == null ? other.getFromUsername() == null : this.getFromUsername().equals(other.getFromUsername()))
            && (this.getToUsername() == null ? other.getToUsername() == null : this.getToUsername().equals(other.getToUsername()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestionId() == null) ? 0 : getQuestionId().hashCode());
        result = prime * result + ((getFromUsername() == null) ? 0 : getFromUsername().hashCode());
        result = prime * result + ((getToUsername() == null) ? 0 : getToUsername().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", questionId=").append(questionId);
        sb.append(", fromUsername=").append(fromUsername);
        sb.append(", toUsername=").append(toUsername);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
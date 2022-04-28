package com.plixiaofei.community.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 通知表
 * @TableName notification
 */
@TableName(value ="notification")
public class Notification implements Serializable {
    /**
     * 通知 id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 问题 id
     */
    private Long questionId;

    /**
     * 评论 id
     */
    private Long commentId;

    /**
     * 发起通知用户
     */
    private String fromUser;

    /**
     * 目标用户
     */
    private String toUser;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 通知 id
     */
    public Long getId() {
        return id;
    }

    /**
     * 通知 id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 问题 id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 问题 id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 评论 id
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 评论 id
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 发起通知用户
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * 发起通知用户
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * 目标用户
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * 目标用户
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * 通知内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 通知内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 通知时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 通知时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        Notification other = (Notification) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestionId() == null ? other.getQuestionId() == null : this.getQuestionId().equals(other.getQuestionId()))
            && (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getFromUser() == null ? other.getFromUser() == null : this.getFromUser().equals(other.getFromUser()))
            && (this.getToUser() == null ? other.getToUser() == null : this.getToUser().equals(other.getToUser()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestionId() == null) ? 0 : getQuestionId().hashCode());
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getFromUser() == null) ? 0 : getFromUser().hashCode());
        result = prime * result + ((getToUser() == null) ? 0 : getToUser().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", commentId=").append(commentId);
        sb.append(", fromUser=").append(fromUser);
        sb.append(", toUser=").append(toUser);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
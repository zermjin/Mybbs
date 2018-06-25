package com.bbs.bean;

import java.util.Date;

public class BbsComment {
	@Override
	public String toString() {
		return "BbsComment [id=" + id + ", topicOrCommentId=" + topicOrCommentId + ", isTopic=" + isTopic + ", content="
				+ content + ", userid=" + userid + ", commentTime=" + commentTime + ", agreeNum=" + agreeNum
				+ ", isAccept=" + isAccept + "]";
	}

	private int id;//评论ID
	private int topicOrCommentId;//回复对象的ID
	private int isTopic;//回复的是否是话题（0代表主题，1代表评论）
	private String content;//回复内容
	private int userid;//回复人ID
	private Date commentTime;//评论时间
	private int agreeNum;//赞数
	private int isAccept;//是否被采纳
	//private int state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTopicOrCommentId() {
		return topicOrCommentId;
	}

	public void setTopicOrCommentId(int topicOrCommentId) {
		this.topicOrCommentId = topicOrCommentId;
	}

	public int getIsTopic() {
		return isTopic;
	}

	public void setIsTopic(int isTopic) {
		this.isTopic = isTopic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public int getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(int agreeNum) {
		this.agreeNum = agreeNum;
	}

	public int getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(int isAccept) {
		this.isAccept = isAccept;
	}

}

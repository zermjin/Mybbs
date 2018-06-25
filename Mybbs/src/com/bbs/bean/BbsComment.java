package com.bbs.bean;

import java.util.Date;

public class BbsComment {
	@Override
	public String toString() {
		return "BbsComment [id=" + id + ", topicOrCommentId=" + topicOrCommentId + ", isTopic=" + isTopic + ", content="
				+ content + ", userid=" + userid + ", commentTime=" + commentTime + ", agreeNum=" + agreeNum
				+ ", isAccept=" + isAccept + "]";
	}

	private int id;//����ID
	private int topicOrCommentId;//�ظ������ID
	private int isTopic;//�ظ����Ƿ��ǻ��⣨0�������⣬1�������ۣ�
	private String content;//�ظ�����
	private int userid;//�ظ���ID
	private Date commentTime;//����ʱ��
	private int agreeNum;//����
	private int isAccept;//�Ƿ񱻲���
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

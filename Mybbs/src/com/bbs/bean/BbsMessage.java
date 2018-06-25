package com.bbs.bean;

public class BbsMessage {
	@Override
	public String toString() {
		return "BbsMessage [id=" + id + ", commentId=" + commentId + "]";
	}

	private int id;//ÏûÏ¢ID
	private int commentId;//ÆÀÂÛID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
}

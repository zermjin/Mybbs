package com.bbs.bean;


import java.util.List;

public class BbsCommentEX extends BbsComment {
	@Override
	public String toString() {
		return "BbsCommentEX [nickname=" + nickname + ", head_url=" + head_url + ", title=" + title + "]";
	}

	private String nickname;
	private String head_url;
	private String title;
	private List<BbsCommentEX> commentlist;

	public List<BbsCommentEX> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<BbsCommentEX> commentlist) {
		this.commentlist = commentlist;
	}
	
	public String getHead_url() {
		return head_url;
	}
	
	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

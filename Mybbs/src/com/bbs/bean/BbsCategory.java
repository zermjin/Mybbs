package com.bbs.bean;

public class BbsCategory {
	@Override
	public String toString() {
		return "BbsCategory [id=" + id + ", nickname=" + nickname + "]";
	}

	private int id;// ���ID
	private String nickname;// ��������

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

package com.bbs.bean;

public class BbsUser {
	@Override
	public String toString() {
		return "BbsUser [id=" + id + ", email=" + email + ", nickname=" + nickname + ", city=" + city + ", sex=" + sex
				+ ", headUrl=" + headUrl + ", password=" + password + ", signName=" + signName + ", kissNum=" + kissNum
				+ "]";
	}

	private int id;//�û�ID
	private String email;//���䣨������¼��
	private String nickname;//�ǳ�
	private String city;//����
	private int sex;//�Ա� --1 �� --0 Ů
	private String headUrl;//ͷ��·��
	private String password;//����
	private String signName;//ǩ��
	private int kissNum;//������
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public int getKissNum() {
		return kissNum;
	}

	public void setKissNum(int kissNum) {
		this.kissNum = kissNum;
	}
}

package com.bbs.bean;

public class BbsUser {
	@Override
	public String toString() {
		return "BbsUser [id=" + id + ", email=" + email + ", nickname=" + nickname + ", city=" + city + ", sex=" + sex
				+ ", headUrl=" + headUrl + ", password=" + password + ", signName=" + signName + ", kissNum=" + kissNum
				+ "]";
	}

	private int id;//用户ID
	private String email;//邮箱（用来登录）
	private String nickname;//昵称
	private String city;//城市
	private int sex;//性别 --1 男 --0 女
	private String headUrl;//头像路径
	private String password;//密码
	private String signName;//签名
	private int kissNum;//飞吻数
	
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

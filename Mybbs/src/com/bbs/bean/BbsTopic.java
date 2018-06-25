package com.bbs.bean;

import java.util.Date;

public class BbsTopic {
	@Override
	public String toString() {
		return "BbsTopic [id=" + id + ", title=" + title + ", content=" + content + ", createtime=" + createtime
				+ ", categoryId=" + categoryId + ", viewCount=" + viewCount + ", userid=" + userid + ", isGood="
				+ isGood + ", isEnd=" + isEnd + "]";
	}

	private int id;//话题ID
	private String title;//话题标题
	private String content;//话题正文
	private Date createtime;//创建时间
	private int categoryId;//所属类别ID
	private int viewCount;//浏览人数
	private int userid;//发表人ID
	private int isGood;//是否加精
	private int isEnd;//是否结贴
	private int isTop;
	private int kiss;//悬赏数

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getKiss() {
		return kiss;
	}

	public void setKiss(int kiss) {
		this.kiss = kiss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getIsGood() {
		return isGood;
	}

	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}

	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
}

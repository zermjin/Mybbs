package com.bbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbs.bean.BbsTopic;
import com.bbs.bean.BbsTopicEX;
import com.bbs.bean.BbsUser;
import com.bbs.util.SqlDate;

public class Test {

	public static void main(String[] args) {
		UserService us = new UserService();
		TopicService ts = new TopicService();
		CommentService cs = new CommentService();
		BbsUser bu = new BbsUser();
		BbsTopic bt =new BbsTopic();
		BbsTopicEX bte =new BbsTopicEX();
//		bt.setCategoryId(1);
//		bt.setContent("管理员专用，禁止水贴");
//		Date d = new Date();
//		bt.setCreatetime(d);
//		bt.setIsEnd(0);
//		bt.setIsGood(1);
//		bt.setTitle("测试楼");
//		bt.setUserid(1);
//		bt.setViewCount(0);
//		int x = ts.addTopic(bt);
//		System.out.println(x);
//		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
//		list =  ts.selectTopic();
//		System.out.println(list.size());
//		System.out.println(list.get(0));
//		bte= ts.getTopicByID(2);
//		System.out.println(bte);
//		int x = ts.insertTopicViewCounts(1);
//		System.out.println(x);
//		list = ts.selectTopicById(1);
//		System.out.println(list.size());
//		System.out.println(list.get(0));
//		int x = ts.getTotalCount();
//		System.out.println(x);
		//=========================
//		bu.setEmail("xxx@xxx.com");
//		bu.setNickname("HaHa");
//		bu.setPassword("123456");
//		bu.setSignName("happy day");
//		bu.setCity("New York");
//		bu.setSex(1);
//		bu.setId(1);
//		int x=us.addUser(bu);
//		System.out.println(x);
//		bu = us.userLogin("xxx@xxx.com", "123456");
//		System.out.println(bu);
//		int x = us.updateUser(bu);
//		System.out.println(x);
//		int x = us.updatePassword(1, "123456", "000000");
//		System.out.println(x);
//		boolean f = us.IsUserExist("xx@xxx.com");
//		System.out.println(f);
//		int x = us.saveUserHeaderPicPath(1, "20171123.jpg");
//		bu = us.selectUser(1);
//		System.out.println(bu);
	}

}

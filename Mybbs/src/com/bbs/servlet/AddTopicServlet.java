package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bean.BbsTopic;
import com.bbs.bean.BbsUser;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;

@WebServlet("/AddTopicServlet")
public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession Session = request.getSession();
		// BbsUser bu = new BbsUser();
		// bu = (BbsUser) Session.getAttribute("loginuser");
		// System.out.println(bu);
		BbsTopic bt = new BbsTopic();
		TopicService ts = new TopicService();
		//int kiss = Integer.parseInt(request.getParameter("kiss"));
		int id = Integer.parseInt(request.getParameter("userid"));
		bt.setCategoryId(Integer.parseInt(request.getParameter("category")));
		bt.setContent(request.getParameter("content"));
		bt.setTitle(request.getParameter("title"));
		bt.setUserid(id );
		bt.setKiss(Integer.parseInt(request.getParameter("kiss")));
		// bt.setUserid(bu.getId());
		bt.setViewCount(0);
		bt.setIsEnd(0);
		bt.setIsGood(0);
		ts.addTopic(bt);
		
		UserService us = new UserService();
		BbsUser user = us.selectUser(id);
		HttpSession session =request.getSession();
		session.setAttribute("loginuser", user);
	//	System.out.println("addTopic:" + x);
		response.sendRedirect(request.getContextPath() + "/TopicIndexListServlet");

	}

}

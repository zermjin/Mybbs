package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bean.BbsTopicEX;
import com.bbs.bean.BbsUser;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;


@WebServlet("/EditTopicServlet")
public class EditTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String id = request.getParameter("id");
		HttpSession session = request.getSession();
		BbsTopicEX bt = (BbsTopicEX)session.getAttribute("topic");
		TopicService ts = new TopicService();
		int kiss = Integer.parseInt(request.getParameter("oldkiss"));
		//int kiss = Integer.parseInt(request.getParameter("kiss"));
		bt.setCategoryId(Integer.parseInt(request.getParameter("category")));
		bt.setContent(request.getParameter("content"));
		bt.setTitle(request.getParameter("title"));
		bt.setKiss(Integer.parseInt(request.getParameter("kiss")));
		// bt.setUserid(bu.getId());
		int x = ts.updateTopic(bt,kiss);
		//System.out.println("update"+x);
		
		UserService us = new UserService();
		BbsUser user = us.selectUser(bt.getUserid());
		session.setAttribute("loginuser", user);
		session.setAttribute("topic", bt);
	//	System.out.println("addTopic:" + x);
		response.sendRedirect(request.getContextPath() + "/TopicIndexListServlet");
	}

}

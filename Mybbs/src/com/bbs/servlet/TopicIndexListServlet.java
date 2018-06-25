package com.bbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bean.BbsTopicEX;
import com.bbs.bean.BbsUser;
import com.bbs.bean.BbsUserEX;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;

/**
 * Servlet implementation class TopicIndexListServlet
 */
@WebServlet("/TopicIndexListServlet")
public class TopicIndexListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TopicService ts=new TopicService();
		List<BbsTopicEX> list=new ArrayList<BbsTopicEX>();
		List<BbsTopicEX> vhotlist=new ArrayList<BbsTopicEX>();
		List<BbsTopicEX> ahotlist=new ArrayList<BbsTopicEX>();
		List<BbsUserEX> userlist=new ArrayList<BbsUserEX>();
		UserService us = new UserService();
		userlist=us.getHotUser();
		vhotlist=ts.selectVHotTopic();
		ahotlist=ts.selectAHotTopic();
		list=ts.selectTopic();
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		session.setAttribute("vlist", vhotlist);
		session.setAttribute("alist", ahotlist);
		session.setAttribute("ulist", userlist);
		BbsUser bu = (BbsUser)session.getAttribute("loginuser");
		BbsUser user = null;
		if(bu!=null)
			user = us.selectUser(bu.getId());
		session.setAttribute("loginuser", user);
		//request.setAttribute("list", list);
	//	System.out.println("跳转前");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	//	System.out.println("跳转后");
	//	request.getRequestDispatcher("/index.jsp").forward(request,response);
	}

}

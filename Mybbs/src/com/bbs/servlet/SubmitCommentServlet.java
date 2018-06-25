package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bean.BbsComment;
import com.bbs.bean.BbsUser;
import com.bbs.service.CommentService;
import com.bbs.service.UserService;


@WebServlet("/SubmitCommentServlet")
public class SubmitCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService cs = new CommentService();
		BbsComment bc = new BbsComment();
		int id = Integer.parseInt(request.getParameter("userid"));
		bc.setContent(request.getParameter("content"));
		bc.setUserid(id);
		int istopic = Integer.parseInt(request.getParameter("istopic"));
		int topicid=Integer.parseInt(request.getParameter("topicid"));
		bc.setIsTopic(istopic);
		if(istopic == 0){
			bc.setTopicOrCommentId(topicid);
		}else if(istopic == 1){
			bc.setTopicOrCommentId(Integer.parseInt(request.getParameter("commentid")));
		}
		
		cs.addComment(bc);
		
		UserService us = new UserService();
		BbsUser user = us.selectUser(id);
		HttpSession session =request.getSession();
		session.setAttribute("loginuser", user);
		response.sendRedirect(request.getContextPath() + "/TopicDetailServlet?id="+topicid);
	}

}

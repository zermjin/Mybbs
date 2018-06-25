package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bean.BbsUser;
import com.bbs.service.CommentService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;

/**
 * Servlet implementation class AdminCommentServlet
 */
@WebServlet("/AdminCommentServlet")
public class AdminCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = request.getParameter("action");
		String ids = str.split(",")[0];
		String flag = str.split(",")[1];
		String tids = str.split(",")[2];
		CommentService cs = new CommentService();
		TopicService ts = new TopicService();
		int id = Integer.parseInt(ids);
		int tid = Integer.parseInt(tids);
		
		// System.out.println(bt);
		if (flag.equals("delete")) {
			cs.deleteCommentById(id);
			response.sendRedirect(request.getContextPath() + "/TopicIndexListServlet");
			return;
		} else if (flag.equals("accept")) {
			String uid = str.split(",")[3];
			String kiss = str.split(",")[4];
			cs.acceptCommentById(id,Integer.parseInt(uid),Integer.parseInt(kiss));
			UserService us = new UserService();
			HttpSession session = request.getSession();
			BbsUser user = us.selectUser(Integer.parseInt(uid));
			session.setAttribute("loginuser", user);
			ts.endTopicById(tid);
			
		}
		response.sendRedirect(request.getContextPath() + "/TopicDetailServlet?id=" + tid);

	}

}

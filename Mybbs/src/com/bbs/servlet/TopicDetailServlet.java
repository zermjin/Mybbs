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

import com.bbs.bean.BbsCommentEX;
import com.bbs.bean.BbsTopicEX;
import com.bbs.service.CommentService;
import com.bbs.service.TopicService;

/**
 * Servlet implementation class TopicDetailServlet
 */
@WebServlet("/TopicDetailServlet")
public class TopicDetailServlet extends HttpServlet {
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
		// 获得当前贴子 的详细信息
		String id = request.getParameter("id");
		List<BbsTopicEX> vhotlist=new ArrayList<BbsTopicEX>();
		List<BbsTopicEX> ahotlist=new ArrayList<BbsTopicEX>();
		TopicService ts = new TopicService();
		BbsTopicEX btie = ts.ViewTopicDetail(Integer.parseInt(id));
		//System.out.println(btie);
		vhotlist=ts.selectVHotTopic();
		ahotlist=ts.selectAHotTopic();
		//System.out.println("btie:"+btie);
		// 转发给提取自详情 jsp--detail.jsp
		CommentService cs = new CommentService();
		List<BbsCommentEX> lst = cs.getAllCommentByTopicId(Integer.parseInt(id));
		//System.out.println(lst.size());
		HttpSession session =request.getSession();
		session.setAttribute("vlist", vhotlist);
		session.setAttribute("alist", ahotlist);
		session.setAttribute("topic", btie);
	//	request.setAttribute("topic", btie);
		session.setAttribute("comment", lst);
	//	request.setAttribute("comment", lst);
		response.sendRedirect(request.getContextPath()+"/jie/detail.jsp");
	//	request.getRequestDispatcher("/jie/detail.jsp").forward(request, response);
	}

}

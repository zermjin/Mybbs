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
import com.bbs.service.TopicService;

/**
 * Servlet implementation class TopicByPageServlet
 */
@WebServlet("/TopicByPageServlet")
public class TopicByPageServlet extends HttpServlet {
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
		TopicService ts = new TopicService();
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		String pageNum = request.getParameter("pageNum");
		String action = request.getParameter("action");
		
		if(action==null||action.equals("")){
			// 如果路径中带着pageNum进来的那就是按数字提交的
			//System.out.println("路径pageNum:"+pageNum);
			int pageSize = 10;
			if (pageNum != null && !"".equals(pageNum)) {
				//pageSize = (Integer) request.getSession().getAttribute("pageSize");
			} else {
				//pageSize = (Integer) request.getSession().getAttribute("pageSize");
				pageNum = "1";
			}
			int datacount = ts.getTotalCount();
			int pageCount = 0;
			if (datacount % pageSize == 0) {
				pageCount = datacount / pageSize;
			} else {
				pageCount = (datacount / pageSize) + 1;
			}
			list = ts.getPagedTopics(pageSize,Integer.parseInt(pageNum));
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			session.setAttribute("pageSize", pageSize);
			session.setAttribute("pageNum", pageNum);
			session.setAttribute("pageCount", pageCount);
			response.sendRedirect(request.getContextPath()+"/jie/index.jsp");
			return;
		}else {
			int pageSize = 10;
			if (pageNum != null && !"".equals(pageNum)) {
				//pageSize = (Integer) request.getSession().getAttribute("pageSize");
			} else {
				//pageSize = (Integer) request.getSession().getAttribute("pageSize");
				pageNum = "1";
			}
			int datacount = 0;
			int pageCount = 0;
			if(action.equals("1")){
				datacount = ts.getTotalCount(1);
				pageCount = 0;
				if (datacount % pageSize == 0) {
					pageCount = datacount / pageSize;
				} else {
					pageCount = (datacount / pageSize) + 1;
				}
				list = ts.getPagedNotEndTopics(pageSize,Integer.parseInt(pageNum));
			}else if(action.equals("2")){
				datacount = ts.getTotalCount(2);
				pageCount = 0;
				if (datacount % pageSize == 0) {
					pageCount = datacount / pageSize;
				} else {
					pageCount = (datacount / pageSize) + 1;
				}
				list = ts.getPagedEndTopics(pageSize,Integer.parseInt(pageNum));
			}else if(action.equals("3")){
				datacount = ts.getTotalCount(3);
				pageCount = 0;
				if (datacount % pageSize == 0) {
					pageCount = datacount / pageSize;
				} else {
					pageCount = (datacount / pageSize) + 1;
				}
				list = ts.getPagedGoodTopics(pageSize,Integer.parseInt(pageNum));
			}
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			session.setAttribute("pageSize", pageSize);
			session.setAttribute("pageNum", pageNum);
			session.setAttribute("pageCount", pageCount);
			response.sendRedirect(request.getContextPath()+"/jie/index.jsp?action="+action);
		}
		
	//	request.setAttribute("list", list);
	//	request.getRequestDispatcher("/jie/index.jsp").forward(request, response);
	}

}

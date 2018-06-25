package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.bean.BbsTopic;
import com.bbs.service.TopicService;

@WebServlet("/AdminTopicServlet")
public class AdminTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = request.getParameter("action");
		String ids = str.split(",")[0];
		String flag = str.split(",")[1];
		TopicService ts = new TopicService();
		int id = Integer.parseInt(ids);
		BbsTopic bt = ts.getTopicByID(id);
		//System.out.println(bt);
		if (flag.equals("delete")) {
			ts.deleteTopicById(id);
			response.sendRedirect(request.getContextPath() + "/TopicIndexListServlet");
			return;
		} else if (flag.equals("good")) {
			if (bt.getIsGood() == 0)
				ts.goodTopicById(id, true);
			else
				ts.goodTopicById(id, false);
		} else if (flag.equals("top")) {
			if (bt.getIsTop() == 0)
				ts.topTopicById(id, true);
			else
				ts.topTopicById(id, false);
		}
		response.sendRedirect(request.getContextPath() + "/TopicDetailServlet?id="+id);
	}

}

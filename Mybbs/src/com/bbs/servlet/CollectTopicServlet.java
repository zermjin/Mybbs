package com.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.TopicService;

/**
 * Servlet implementation class CollectTopicServlet
 */
@WebServlet("/CollectTopicServlet")
public class CollectTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = request.getParameter("action");
		// System.out.println(str);
		String topicid = str.split(",")[0];
		String userid = str.split(",")[1];
		TopicService ts = new TopicService();
		int x = ts.collectTopic(Integer.parseInt(userid), Integer.parseInt(topicid));
		// System.out.println(x);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (x == 1) {
			out.println(1);
		} else if (x == 2) {
			out.println(2);
		} else {
			out.println(0);
		}
		out.flush();
		out.close();
	}

}

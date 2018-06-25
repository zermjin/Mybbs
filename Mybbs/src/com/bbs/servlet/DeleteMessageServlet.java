package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.CommentService;

@WebServlet("/DeleteMessageServlet")
public class DeleteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = request.getParameter("id");
		String ids = str.split(",")[0];
		String flag = str.split(",")[1];
		CommentService cs = new CommentService();
		int x = 0;
		if (flag.equals("one")) {
			x = cs.deleteMessageById(Integer.parseInt(ids));
			 System.out.println("one"+x);
		} else if (flag.equals("all")) {
			x = cs.deleteAllMessageById(Integer.parseInt(ids));
			 System.out.println("all"+x);
		}
		response.sendRedirect(request.getContextPath()+"/user/message.jsp");
	}

}

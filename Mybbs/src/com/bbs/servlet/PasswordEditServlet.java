package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.service.UserService;

@WebServlet("/PasswordEditServlet")
public class PasswordEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
		String nowPass = request.getParameter("nowpass");
		String newPass = request.getParameter("pass");
		int x = us.updatePassword(id, nowPass, newPass);
		HttpSession session = request.getSession();
		if(x>0){
			//session.setAttribute("message", "ÃÜÂëĞŞ¸Ä³É¹¦");
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
		}else{
			//session.setAttribute("message", "ÃÜÂë´íÎó");
			response.sendRedirect(request.getContextPath()+"/404.jsp");
		}
	}

}

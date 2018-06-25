package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bean.BbsUser;
import com.bbs.service.UserService;


@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		HttpSession session = request.getSession();
		BbsUser bu = (BbsUser)session.getAttribute("loginuser");
		bu.setEmail(request.getParameter("email"));
		bu.setCity(request.getParameter("city"));
		bu.setSex(Integer.parseInt(request.getParameter("sex")));
		bu.setNickname(request.getParameter("username"));
		bu.setSignName(request.getParameter("sign"));
		us.updateUser(bu);
		
		BbsUser user = us.selectUser(bu.getId());
		session.setAttribute("loginuser", user);
		response.sendRedirect(request.getContextPath()+"/user/set.jsp");
	}

}

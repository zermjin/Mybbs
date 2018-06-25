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


@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		BbsUser bu = new BbsUser();
		String email = request.getParameter("email");
		bu.setEmail(email);
		
		if(us.IsUserExist(email)){
			System.out.println("” œ‰“—±ª◊¢≤·");
			HttpSession session = request.getSession();
			session.setAttribute("errorEmail", "” œ‰“—±ª◊¢≤·");
			response.sendRedirect(request.getContextPath()+"/user/reg.jsp");
		}else{
			bu.setPassword(request.getParameter("pass"));
			bu.setNickname(request.getParameter("username"));
			us.addUser(bu);
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
		}
		
	}

}

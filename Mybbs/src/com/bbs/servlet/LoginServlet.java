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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strEmail=request.getParameter("email");
		String strPwd=request.getParameter("pass");
		BbsUser bu= new BbsUser();
		UserService us=new UserService();
		//System.out.println(strEmail);
		//System.out.println(strPwd);
		bu=us.userLogin(strEmail, strPwd);
		if(bu!=null){
			System.out.println("µÇÂ¼³É¹¦");
			HttpSession httpSession=request.getSession();
			httpSession.setAttribute("loginuser", bu);
//			request.getRequestDispatcher("/TopicIndexListServlet").forward(request,response);
			response.sendRedirect(request.getContextPath()+"/TopicIndexListServlet");
		//	System.out.println("µÇÂ¼Ìø×ªºó");
		}
		else{
			System.out.println("µÇÂ¼Ê§°Ü");
			request.getRequestDispatcher("/404.jsp").forward(request,response);
		}
	}

}

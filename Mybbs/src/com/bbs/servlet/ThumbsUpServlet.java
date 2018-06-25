package com.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.CommentService;

/**
 * Servlet implementation class ThumbsUpServlet
 */
@WebServlet("/ThumbsUpServlet")
public class ThumbsUpServlet extends HttpServlet {
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

		CommentService cs = new CommentService();
		String str = request.getParameter("id");
		String ids = str.split(",")[0];
		String flag = str.split(",")[1];
		int x = 0;
		if(flag.equals("no")){
			x = cs.addAgreeNum(Integer.parseInt(ids),true);
		//	System.out.println(x);
		}else if(flag.equals("ok")){
			x = cs.addAgreeNum(Integer.parseInt(ids),false);
		//	System.out.println(x);
		}

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		if (x > 0) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.flush();
		out.close();
	}

}

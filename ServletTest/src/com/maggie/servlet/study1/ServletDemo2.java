package com.maggie.servlet.study1;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo2
 */
@WebServlet("/ServletDemo2")
public class ServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDemo2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//	获取整个WEB的初始化参数	<context-param>
		ServletContext context = this.getServletContext();
		String contextInitParam = context.getInitParameter("database");
		System.out.println(contextInitParam);
		
		String shareData = (String) context.getAttribute("sharedata");
		// 要想拿到shareData的值，一定要先运行Demo1来赋值，才能拿到，否则是null
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("+++++++" + shareData+"+++database+"+contextInitParam);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

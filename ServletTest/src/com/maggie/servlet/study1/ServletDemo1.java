package com.maggie.servlet.study1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Servlet implementation class ServletDemo1
 */
// @WebServlet("/ServletDemo1")
// 注解或.xml里面配置
public class ServletDemo1 extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	@Override
	public void init(ServletConfig config) throws ServletException {
//		在servlet初始化的时候就把config传个servlet
		this.config = config;
		super.init(config);//这句话一定不能删，不然会抛空指针异常
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDemo1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		ServletConfig可以配置的初始化参数<init-param>
		String encoding = this.config.getInitParameter("charset");
		System.out.println("encoding   "+encoding);
		
		
		
		
//		ServletContext的context域，可实现域内数据共享，每个servlet只有一个Context--上下文对象
		ServletContext context = this.getServletContext();
		
//		获取请求转发对象（RequestDispatcher）
		RequestDispatcher rDispatcher  = context.getRequestDispatcher("/ServletDemo2");//相对路径
		rDispatcher.forward(request, response);//调用forward方法请求转发
		//请求转发跟跳转的区别是：请求转发的URL不会改变
		
		context.setAttribute("sharedata", "this is servletcontext");
		
		response.setContentType("text/html");
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("<HTML><HEAD><TITLE>A Servlet</TITLE><BODY>AAAAAAsdas <br/>encoding"+encoding+"</BODY></HTML>");
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

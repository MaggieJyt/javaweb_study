package com.maggie.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemo
 */
@WebServlet("/RequestDemo")
public class RequestDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 获取Client的信息
		 */
		String requestUrl = request.getRequestURL().toString();//得到请求的url
		String requestURI = request.getRequestURI();//得到请求资源
		String queryString = request.getQueryString();//得到请求的URL地址中附带的参数  只适用于GET 
		String getParameter = request.getParameter("aa");//GET和POST都可以使用
		String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String  method = request.getMethod();
		String pathInfo = request.getPathInfo();
		String localAddr = request.getLocalAddr();//web服务器的IP
		String localName = request.getLocalName();//web服务器的主机名
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		response.setHeader("content-type", "text/html;charset=UTF-8");
		
		
		
		/*
		 * 获取客户的请求头信息
		 */
		Enumeration<String> reqHeadInfos = request.getHeaderNames();//获取到的客户端所有的请求头信息
		String value = request.getHeader("Accept-Encoding");
		Enumeration<String> enumeration = request.getHeaders("Accept-Encoding");
		
		
		
		
		PrintWriter out = response.getWriter();
        out.write("获取到的客户机信息如下：");
        out.write("<hr/>");
        out.write("请求的URL地址request.getRequestURL()："+requestUrl);
        out.write("<br/>");
        out.write("请求的资源request.getRequestURI()："+requestURI);
        out.write("<br/>");
        out.write("请求的URL地址中附带的参数queryString："+queryString);
        out.write("<br/>");
        out.write("请求的URL地址中附带的参数getParameter："+getParameter);
        out.write("<br/>");
        out.write("来访者的IP地址remoteAddr："+remoteAddr);
        out.write("<br/>");
        out.write("来访者的主机名remoteHost："+remoteHost);
        out.write("<br/>");
        out.write("使用的端口号remotePort："+remotePort);
        out.write("<br/>");
        out.write("remoteUser："+remoteUser);
        out.write("<br/>");
        out.write("请求使用的方法request.getMethod："+method);
        out.write("<br/>");
        out.write("pathInfo request.getPathInfo："+pathInfo);
        out.write("<br/>");
        out.write("localAddr："+localAddr);
        out.write("<br/>");
        out.write("localName："+localName);
        out.write("<br/>");out.write("<br/>");out.write("<br/>");
        
        while(reqHeadInfos.hasMoreElements()) {
        		String headNmae = reqHeadInfos.nextElement();
        		String headValue = request.getHeader(headNmae);//根据请求头的名字获取对应的请求头的值
        		out.write(headNmae+" : "+headValue);
        		out.write("<br/>");
        }
        out.write("<br/>");out.write("<br/>");out.write("<br/>");
        out.write("Accept-Encoding :  "+value);
        out.write("<br/>");
        while(enumeration.hasMoreElements()) {
        	String string = (String) enumeration.nextElement();
    		out.write(string);
    }
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



















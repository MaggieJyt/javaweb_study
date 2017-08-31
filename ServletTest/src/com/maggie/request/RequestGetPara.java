package com.maggie.request;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetPara
 */
@WebServlet("/RequestGetPara")
public class RequestGetPara extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetPara() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//客户端是以UTF-8编码提交表单数据的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
		request.setCharacterEncoding("utf-8");
		/*
		 * 用getParameter(s)方法接受参数
		 */
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		String sex = request.getParameter("sex");
		String dept = request.getParameter("dept");
		String[] insts = request.getParameterValues("inst");
		//insts的tostring的方法没有重新
		String note = request.getParameter("note");
		String hidden = request.getParameter("hiddenField");
		
		//转成自己需要的string，最好用strinbuffer
		String instStr = "";
		for(int i = 0; insts!=null && i<insts.length;i++) {
			if (i == insts.length-1) {
				instStr += insts[i];
			}else {
				instStr += insts[i]+",";
			}
		}
		
		String htmlStr = "<table>" +
                "<tr><td>填写的编号：</td><td>{0}</td></tr>" +
                "<tr><td>填写的用户名：</td><td>{1}</td></tr>" +
                "<tr><td>填写的密码：</td><td>{2}</td></tr>" +
                "<tr><td>选中的性别：</td><td>{3}</td></tr>" +
                "<tr><td>选中的部门：</td><td>{4}</td></tr>" +
                "<tr><td>选中的兴趣：</td><td>{5}</td></tr>" +
                "<tr><td>填写的说明：</td><td>{6}</td></tr>" +
                "<tr><td>隐藏域的内容：</td><td>{7}</td></tr>" +
            "</table>";
		 htmlStr = MessageFormat.format(htmlStr, userid,username,userpass,sex,dept,instStr,note,hidden);
	        
	        response.setCharacterEncoding("UTF-8");//设置服务器端以UTF-8编码输出数据到客户端
	        response.setContentType("text/html;charset=UTF-8");//设置客户端浏览器以UTF-8编码解析数据
	        response.getWriter().write(htmlStr);//输出htmlStr里面的内容到客户端浏览器显示
	        
	     /*
	      * 使用getParameterNames方法接收表单参数
	      */
	       Enumeration<String> paramNames = request.getParameterNames();
	       while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();//得到参数名
			String value = request.getParameter(name);//数组就只能得到一个
			System.out.println(MessageFormat.format("{0}={1}", name,value));
			
		}
	       System.out.println();
	       System.out.println();
	       System.out.println();
	       System.out.println();
	       
	       /*
	        * getParameterMap方法接收表单参数
	        */
	       Map<String, String[]> paramMap = request.getParameterMap();
	       for(Map.Entry<String, String[]> entry : paramMap.entrySet()) {
	    	   String paramName = entry.getKey();
	            String paramValue = "";
	            String[] paramValueArr = entry.getValue();
	            for (int i = 0; paramValueArr!=null && i < paramValueArr.length; i++) {
	                if (i == paramValueArr.length-1) {
	                    paramValue+=paramValueArr[i];
	                }else {
	                    paramValue+=paramValueArr[i]+",";
	                }
	            }
	            System.out.println(MessageFormat.format("{0}={1}", paramName,paramValue));
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

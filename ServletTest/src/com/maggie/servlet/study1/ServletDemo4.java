package com.maggie.servlet.study1;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo4
 * 通过类装载器读取资源文件的注意事项:不适合装载大文件，否则会导致jvm内存溢出
 */
@WebServlet("/ServletDemo4")
public class ServletDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("content-type","text/html;charset=UTF-8");
		 test1(response);
		 test2(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void test1(HttpServletResponse response) throws IOException {
        //获取到装载当前类的类装载器
        ClassLoader loader = ServletDemo4.class.getClassLoader();
        //用类装载器读取src目录下的db1.properties配置文件
        InputStream in = loader.getResourceAsStream("db4.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("name");
        String password = prop.getProperty("password");
        response.getWriter().println("用类装载器读取src目录下的db4.properties配置文件：");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}", 
                        driver,url, username, password));
    }
	
	 private void test2(HttpServletResponse response) throws IOException {
	        //获取到装载当前类的类装载器
	        ClassLoader loader = ServletDemo4.class.getClassLoader();
	        //用类装载器读取src目录下的com.maggie.servlet.study1包中的db2.properties配置文件
	        InputStream in = loader.getResourceAsStream("com/maggie/servlet/study1/db2.properties"); //相对路径
	        Properties prop = new Properties();
	        prop.load(in);
	        String driver = prop.getProperty("driver");
	        String url = prop.getProperty("url");
	        String username = prop.getProperty("name");
	        String password = prop.getProperty("password");
	        response.getWriter().println("用类装载器读取src目录下的gacl.servlet.study包中的db2.properties配置文件：");
	        response.getWriter().println(
	                MessageFormat.format(
	                        "driver={0},url={1},username={2},password={3}", 
	                        driver,url, username, password));
	    }
	    

}
